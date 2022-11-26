package com.example.chuyendedidong2.Adapter;



import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;
import androidx.recyclerview.widget.SortedListAdapterCallback;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Interface.IclickItemCategoryListener;
import com.example.chuyendedidong2.Model.ProductModel;

import com.example.chuyendedidong2.ProductActivity;
import com.example.chuyendedidong2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> implements Filterable  {

    private Context context;
    private ArrayList<ProductModel> list;
    private ArrayList<ProductModel> listOld;
    private IclickItemCategoryListener iclickItemCategoryListener;



    public ProductsAdapter(Context context, ArrayList<ProductModel> list) {
        this.context = context;
        this.list = list;
        this.listOld = list;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_layout, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductModel productModel = list.get(position);
        Glide.with(context).load(productModel.getImg_url()).into(holder.imageView);
        holder.newName.setText(productModel.getName());
        holder.newRating.setRating( productModel.getNumStar());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.newPrice.setText(decimalFormat.format( productModel.getPrice()));


//        holder.itemView.setOnClickListener(view -> {
//            Intent intent = new Intent(context,ProductActivity.class);
//
//            intent.putExtra("image",newProductModel.getImg_url());
//            intent.putExtra("name",newProductModel.getName());
//            intent.putExtra("price",String.valueOf(newProductModel.getPrice()));
//            intent.putExtra("rating",String.valueOf(newProductModel.getNumStar()));
//            intent.putExtra("des",newProductModel.getDesciption());
//            intent.putExtra("nameShop",newProductModel.getNameShop());
//            context.startActivity(intent);

//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("image", productModel.getImg_url());
                intent.putExtra("name", productModel.getName());
                intent.putExtra("price", String.valueOf(productModel.getPrice()));
                intent.putExtra("rating", String.valueOf(productModel.getNumStar()));
                intent.putExtra("des", productModel.getDesciption());
                intent.putExtra("nameShop", productModel.getNameShop());
                intent.putExtra("sl", productModel.getSoLuong());
                context.startActivity(intent);
            }
        });
        // Get the data from an ImageView as bytes

    }
    public final void setImage(ImageView imageView, String avatar) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images" + avatar);
        try {
            final File file = File.createTempFile("ảnh", "jpg");
            storageReference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    imageView.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("Notify", "Load Image Fail");

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sortPrice() {
        Collections.sort(list, new Comparator<ProductModel>() {
            @Override
            public int compare(ProductModel productModel, ProductModel t1) {
                return productModel.getPrice() - t1.getPrice();
            }

        });
        notifyDataSetChanged();

    }
    public void sort() {
        Collections.sort(list, new Comparator<ProductModel>() {
            @Override
            public int compare(ProductModel productModel, ProductModel t1) {
                return productModel.getName().toLowerCase().compareTo(t1.getName().toLowerCase());
            }

        });
        notifyDataSetChanged();

    }
    public void sortStar() {
        Collections.sort(list, new Comparator<ProductModel>() {
            @Override
            public int compare(ProductModel productModel, ProductModel t1) {
                return (int) (productModel.getNumStar() - t1.getNumStar());
            }

        });
        notifyDataSetChanged();

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String str = charSequence.toString();

                if (str.isEmpty()) {
                    list = listOld;
                } else {
                    List<ProductModel> pro = new ArrayList<>();
                    for (ProductModel item : listOld) {
                        if (item.getName().toLowerCase().contains(str.toLowerCase())) {
                            pro.add(item);
                        }

                    }
                    list = (ArrayList<ProductModel>) pro;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;


                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (ArrayList<ProductModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }






    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView newName, newPrice;
        RatingBar newRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivNewProduct);
            newName = itemView.findViewById(R.id.tvNPName);
            newPrice = itemView.findViewById(R.id.tvNPPrice);
            newRating = itemView.findViewById(R.id.ratingBarNP);
        }

    }

}
