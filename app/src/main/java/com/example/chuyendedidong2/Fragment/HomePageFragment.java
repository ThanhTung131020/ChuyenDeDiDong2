package com.example.chuyendedidong2.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.chuyendedidong2.Adapter.ProductsAdapter;
//import com.example.chuyendedidong2.Model.ImageSilder;
import com.example.chuyendedidong2.Adapter.ProductsLoginAdapter;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;

public class HomePageFragment extends Fragment {
    //img slider
    private ViewPager viewPager;
    //private ImageSliderAdapter imageSliderAdapter;
    //new product
    ArrayList<ProductModel> productModelList;
    private RecyclerView rvNewProduct;
    private ProductModel productModel;
    private ProductsLoginAdapter newProductsAdapter;
    //spinner
    private Spinner spinner;
    public HomePageFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        rvNewProduct = view.findViewById(R.id.rvProducts);
        viewPager = view.findViewById(R.id.viewPager);
        spinner = view.findViewById(R.id.spTinKiem);
        setEvent();
        return view;
    }
//    private List<ImageSilder> getListImageSlider() {
//        List<ImageSilder> imageSilders = new ArrayList<>();
//        imageSilders.add(new ImageSilder(R.drawable.img));
//        imageSilders.add(new ImageSilder(R.drawable.img_1));
//        return imageSilders;
//    }

    private void setEvent() {
        //image slider
//        imageSliderAdapter = new ImageSliderAdapter(getContext(),getListImageSlider());
//        viewPager.setAdapter(imageSliderAdapter);
        //newProductModelList = new ArrayList<>();
        rvNewProduct.setLayoutManager(new GridLayoutManager(getContext(),3));
        productModel = new ProductModel();
        newProductsAdapter = new ProductsLoginAdapter(getContext(), productModel.createNewProduct());
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
}