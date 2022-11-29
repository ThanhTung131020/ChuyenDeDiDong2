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
import android.widget.TextView;
import android.widget.Toast;

//import com.example.chuyendedidong2.Adapter.ImageSliderAdapter;
import com.example.chuyendedidong2.Adapter.CategoryAdapter;
import com.example.chuyendedidong2.Adapter.ImageSliderAdapter;
import com.example.chuyendedidong2.Adapter.ProductsAdapter;
//import com.example.chuyendedidong2.Model.ImageSilder;
import com.example.chuyendedidong2.Adapter.ProductsLoginAdapter;
import com.example.chuyendedidong2.HomePageActivity;
import com.example.chuyendedidong2.Interface.IclickItemCategoryListener;
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
    //new product
    ArrayList<ProductModel> productModelList;
    private RecyclerView rvNewProduct;
    private ProductModel prd;
    private ProductsLoginAdapter newProductsAdapter;
    //spinner
    public  ProductsAdapter productsAdapter;
    private Spinner spinner;
    private SearchView searchView;
    private ProductsAdapter pro;
    static HomePageActivity homePageActivity;
    private TextView tvSp;

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
        rvNewProduct = view.findViewById(R.id.rvProducts);
        rv_imgSlider = view.findViewById(R.id.rv_viewPager_login);
        spinner = view.findViewById(R.id.spTinKiem);
        rvCat = view.findViewById(R.id.rv_category_login);
        searchView = view.findViewById(R.id.search_view_data);
        tvSp = view.findViewById(R.id.tv_sanPham);
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
        rvCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    private void setEvent() {
        //image slider
        creatImgSliderList();
        rv_imgSlider.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        imageSliderAdapter = new ImageSliderAdapter(getContext(), imageSliders);
        rv_imgSlider.setAdapter(imageSliderAdapter);

        //category
        creatCategoryList();
        rvCat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(getContext(), categoryModelList, new IclickItemCategoryListener() {
            @Override

            public void OnclickItemCategory(CategoryModel cate) {
                if (cate.getCatID() == "cat001") {
                    createNewProductPC("laptop" , "máy tính");
                    tvSp.setText("danh mục Laptop , máy tính");

                } else if (cate.getCatID() == "cat002") {
                    createNewProductphone("điện thoại");
                    tvSp.setText("danh mục điện thoại");
                } else if (cate.getCatID() == "cat003") {
                    createNewProductTBDT("máy giặt", "tủ lạnh", "ti vi", "điều hòa");
                    tvSp.setText("danh mục thiết bị sản phẩm");

                } else if (cate.getCatID() == "cat004") {
                    createNewProductALl();
                    tvSp.setText("tất cả sản phẩm");
                }
            }

        });
        rvCat.setAdapter(categoryAdapter);
        //product
        rvNewProduct.setLayoutManager(new GridLayoutManager(getContext(), 3));
        prd = new ProductModel();
        newProductsAdapter = new ProductsLoginAdapter(getContext(),createNewProduct() );
        rvNewProduct.setAdapter(newProductsAdapter);

        //spinner
        String[] spin = {"Mặc định", "sắp xếp theo giá sản phẩm", "sắp xếp theo tên sản phẩm", "sắp xếp theo đánh giá"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spin);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 1) {
                    newProductsAdapter.sortPrice();
                } else if (i == 2) {
                    newProductsAdapter.sort();

                } else if (i == 3) {
                    newProductsAdapter.sortStar();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public  ArrayList<ProductModel> createNewProduct() {



        productModelList = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("product").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productModelList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    prd  = snap.getValue(ProductModel.class);

                    productModelList.add(prd);
                }
                newProductsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return productModelList;


    }
    public ArrayList<ProductModel> createNewProductphone(String key) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("product").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productModelList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    prd = snap.getValue(ProductModel.class);
                    if (prd.getName().toLowerCase().contains(key.toLowerCase())) {
                        productModelList.add(prd);
                    }


                }
                newProductsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return (ArrayList<ProductModel>) productModelList;

    }

    public ArrayList<ProductModel> createNewProductPC(String key , String key1) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("product").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productModelList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    prd = snap.getValue(ProductModel.class);
                    if (prd.getName().toLowerCase().contains(key.toLowerCase())|| prd.getName().toLowerCase().contains(key1.toLowerCase()))

                        productModelList.add(prd);
                }
                newProductsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return (ArrayList<ProductModel>) productModelList;

    }
    //poduct theo danh muc thiet bi dien tu
    public ArrayList<ProductModel> createNewProductTBDT(String key, String key1, String key2, String key3) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("product").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productModelList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    prd = snap.getValue(ProductModel.class);
                    if (prd.getName().toLowerCase().contains(key.toLowerCase()) || prd.getName().toLowerCase().contains(key1) || prd.getName().toLowerCase().contains(key2)
                            || prd.getName().toLowerCase().contains(key3.toLowerCase())
                    ) {

                        productModelList.add(prd);
                    }
                }
                newProductsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return (ArrayList<ProductModel>) productModelList;

    }
    // get all product
    public ArrayList<ProductModel> createNewProductALl() {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("product").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productModelList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    ProductModel prd = snap.getValue(ProductModel.class);


                    productModelList.add(prd);
                }
                newProductsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return (ArrayList<ProductModel>) productModelList;

    }

    public void creatCategoryList() {
        categoryModelList = new ArrayList<>();
        categoryModelList.add(new CategoryModel("cat004", "https://img.freepik.com/free-vector/e-commerce-flat-items_1212-10.jpg?w=2000", "tất cả sản phẩm"));
        categoryModelList.add(new CategoryModel("cat001", "https://firebasestorage.googleapis.com/v0/b/chuyendedidong2-4ba31.appspot.com/o/Ellipse%2015%20(1).png?alt=media&token=f9fd9685-3073-46cd-9abe-e525a167d021", "laptop . máy tính"));
        categoryModelList.add(new CategoryModel("cat002", "data:image/jpeg;base64,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", "điện thoại"));
        categoryModelList.add(new CategoryModel("cat003", "https://e7.pngegg.com/pngimages/899/517/png-clipart-refrigerator-refrigeration-thermostat-u89e3u51bb-slim-simple-appearance-refrigerator-saving-electronics-thumbnail.png", "thiết bị điện tử"));
    }


    public void creatImgSliderList() {
        imageSliders = new ArrayList<>();
        imageSliders.add(new ImageSlider("is1", "https://namtienmobile.vn/wp-content/uploads/2018/12/banner-1-1.jpg"));
        imageSliders.add(new ImageSlider("is2", "https://fptshop.com.vn/Uploads/images/AvatarCustomer/PK_Banner_ver2.jpg"));
        imageSliders.add(new ImageSlider("is3", "https://canhcoupon.com/images/khuyen-mai/2017/08/laptop-gia-tot-dien-thoai-gia-tot-fptshop-com-vn-banner.jpg"));
    }
}