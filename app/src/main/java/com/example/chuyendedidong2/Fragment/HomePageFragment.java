package com.example.chuyendedidong2.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

//import com.example.chuyendedidong2.Adapter.ImageSliderAdapter;
import com.example.chuyendedidong2.Adapter.CategoryAdapter;
import com.example.chuyendedidong2.Adapter.ImageSliderAdapter;
import com.example.chuyendedidong2.Adapter.ProductsAdapter;
//import com.example.chuyendedidong2.Model.ImageSilder;
import com.example.chuyendedidong2.Adapter.ProductsLoginAdapter;
import com.example.chuyendedidong2.Model.CategoryModel;
import com.example.chuyendedidong2.Model.ImageSlider;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePageFragment extends Fragment {
    //img slider
    ArrayList<ImageSlider> imageSliders;
    private RecyclerView rv_imgSlider;
    private ImageSlider imageSlider;
    private ImageSliderAdapter imageSliderAdapter;
    //category
    ArrayList<CategoryModel> categoryModelList;
    private CategoryModel categoryModel;
    private RecyclerView rvCat;
    private CategoryAdapter categoryAdapter;
    //product
    ArrayList<ProductModel> productModelList;
    private RecyclerView rvNewProduct;
    private ProductModel productModel;
    private ProductsLoginAdapter newProductsAdapter;
    //spinner
    private Spinner spinner;
    private SearchView searchView;
    private ProductsAdapter productsAdapter;
    //firebase database
    private FirebaseDatabase database;
    public HomePageFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        database = FirebaseDatabase.getInstance();
        rvNewProduct = view.findViewById(R.id.rvProducts);
        rv_imgSlider = view.findViewById(R.id.rv_viewPager_login);
        spinner = view.findViewById(R.id.spTinKiem);
        rvCat = view.findViewById(R.id.rv_category_login);
        searchView = view.findViewById(R.id.search_view_data);
        //filter searchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                newProductsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newProductsAdapter.getFilter().filter(newText);
                return false;
            }
        });

        setEvent();
        return view;
    }

    private void setEvent() {
        //image slider
        creatImgSliderList();
        rv_imgSlider.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        imageSliderAdapter = new ImageSliderAdapter(getContext(),imageSliders);
        rv_imgSlider.setAdapter(imageSliderAdapter);

        //category
        categoryModelList = new ArrayList<>();
        getCategoryFromDataBase();
        rvCat.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        categoryAdapter = new CategoryAdapter(getContext(),categoryModelList);
        rvCat.setAdapter(categoryAdapter);
        //product
        productModelList = new ArrayList<>();
        getProductFromDataBase();
        rvNewProduct.setLayoutManager(new GridLayoutManager(getContext(),3));
        newProductsAdapter = new ProductsLoginAdapter(getContext(), productModelList);
        rvNewProduct.setAdapter(newProductsAdapter);
        //spinner
        String[] spin = {"Mặc định","Theo giá cao đến thấp","Theo hãng"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spin);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), spin[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void getCategoryFromDataBase(){
        DatabaseReference root = database.getReference("category");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (categoryModelList != null){
                    categoryModelList.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    CategoryModel category = dataSnapshot.getValue(CategoryModel.class);
                    categoryModelList.add(category);
                }
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getProductFromDataBase() {
        DatabaseReference root = database.getReference("product");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (productModelList != null){
                    productModelList.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ProductModel product = dataSnapshot.getValue(ProductModel.class);
                    productModelList.add(product);
                }
                newProductsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void creatCategoryList(){
        categoryModelList = new ArrayList<>();
        categoryModelList.add(new CategoryModel("cat001","https://firebasestorage.googleapis.com/v0/b/chuyendedidong2-4ba31.appspot.com/o/Ellipse%2015%20(1).png?alt=media&token=f9fd9685-3073-46cd-9abe-e525a167d021","Máy tính"));
        categoryModelList.add(new CategoryModel("cat002","https://firebasestorage.googleapis.com/v0/b/chuyendedidong2-4ba31.appspot.com/o/Ellipse%2014.png?alt=media&token=62bd7504-42a9-4a37-ab75-81b2745d7680","Laptop"));
        categoryModelList.add(new CategoryModel("cat003","https://firebasestorage.googleapis.com/v0/b/chuyendedidong2-4ba31.appspot.com/o/Ellipse%2013%20(1).png?alt=media&token=dcbc49ac-9027-4c1d-bcf4-adbee15c45aa","Điện thoại"));
    }
    public void creatImgSliderList(){
        imageSliders = new ArrayList<>();
        imageSliders.add(new ImageSlider("is1","https://th.bing.com/th/id/OIP.Vd1yGBbY7iX3REHs1jOCAgHaD6?pid=ImgDet&rs=1"));
        imageSliders.add(new ImageSlider("is2","https://th.bing.com/th/id/OIP.k5zEoNlXYKit6Frf84pvwwHaFL?w=271&h=190&c=7&r=0&o=5&dpr=1.7&pid=1.7"));
        imageSliders.add(new ImageSlider("is3","https://th.bing.com/th/id/OIP.bfwU6t0zn2j7_eX91M-6IQHaET?w=286&h=180&c=7&r=0&o=5&dpr=1.7&pid=1.7"));
    }
}