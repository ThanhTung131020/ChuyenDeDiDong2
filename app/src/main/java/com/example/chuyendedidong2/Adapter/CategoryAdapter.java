package com.example.chuyendedidong2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.HomePageActivity;
import com.example.chuyendedidong2.Interface.IclickItemCategoryListener;
import com.example.chuyendedidong2.Model.CategoryModel;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context context;
    private ArrayList<CategoryModel> list;
    private IclickItemCategoryListener iclickItemCategoryListener;


    public CategoryAdapter(Context context, ArrayList<CategoryModel> list , IclickItemCategoryListener iclickItemCategoryListener) {
        this.context = context;
        this.list = list;
        this.iclickItemCategoryListener = iclickItemCategoryListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_layout, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryModel categoryModel = list.get(position);
        Glide.with(context).load(categoryModel.getCatImg()).into(holder.img_cat);
        holder.tvCat.setText(categoryModel.getCatName());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // danh muc dien thoai
                iclickItemCategoryListener.OnclickItemCategory(categoryModel);

            }
        });

    }

    @Override
    public int getItemCount() {
//        if (list != null){
//           ;
//        }
        return list.size();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView img_cat;
        TextView tvCat;
        CardView item;
        LinearLayout layout;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            img_cat = itemView.findViewById(R.id.iv_cat);
            tvCat = itemView.findViewById(R.id.tv_cat);
            item = itemView.findViewById(R.id.layout_item_category);

        }
    }
}
