package com.example.chuyendedidong2.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

//import com.example.chuyendedidong2.Adapter.ImageSliderAdapter;
import com.example.chuyendedidong2.Adapter.NewProductsAdapter;
import com.example.chuyendedidong2.HomePageActivity;
import com.example.chuyendedidong2.LoginActivity;
//import com.example.chuyendedidong2.Model.ImageSilder;
import com.example.chuyendedidong2.Model.NewProductModel;
import com.example.chuyendedidong2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class HomePageFragment extends Fragment {
    //img slider
    private ViewPager viewPager;
    //private ImageSliderAdapter imageSliderAdapter;
    //new product
    ArrayList<NewProductModel> newProductModelList;
    private RecyclerView rvNewProduct;
    private NewProductModel newProductModel;
    private NewProductsAdapter newProductsAdapter;
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
        newProductModel = new NewProductModel();
        newProductsAdapter = new NewProductsAdapter(getContext(),newProductModel.createNewProduct());
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
    public void createNewProduct(){
        newProductModelList = new ArrayList<>();
        newProductModelList.add(new NewProductModel("latop",1000,5,"https://th.bing.com/th/id/OIP.IJzazGh2VeCw8let2ORy6gHaFj?pid=ImgDet&rs=1"));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
    }
}