package com.example.chuyendedidong2;import androidx.activity.result.ActivityResultCallback;import androidx.activity.result.ActivityResultLauncher;import androidx.activity.result.contract.ActivityResultContracts;import androidx.annotation.NonNull;import androidx.annotation.Nullable;import androidx.appcompat.app.AppCompatActivity;import android.Manifest;import android.content.ContentResolver;import android.content.Intent;import android.content.pm.PackageManager;import android.graphics.Bitmap;import android.graphics.BitmapFactory;import android.net.Uri;import android.os.Build;import android.os.Bundle;import android.text.TextUtils;import android.util.Log;import android.view.View;import android.webkit.MimeTypeMap;import android.widget.Button;import android.widget.EditText;import android.widget.ImageView;import android.widget.Toast;import com.example.chuyendedidong2.Model.ProductModel;import com.google.android.gms.tasks.OnCompleteListener;import com.google.android.gms.tasks.OnFailureListener;import com.google.android.gms.tasks.OnSuccessListener;import com.google.android.gms.tasks.Task;import com.google.firebase.auth.FirebaseAuth;import com.google.firebase.database.DataSnapshot;import com.google.firebase.database.DatabaseError;import com.google.firebase.database.DatabaseReference;import com.google.firebase.database.FirebaseDatabase;import com.google.firebase.database.ValueEventListener;import com.google.firebase.storage.FileDownloadTask;import com.google.firebase.storage.FirebaseStorage;import com.google.firebase.storage.OnProgressListener;import com.google.firebase.storage.StorageReference;import com.google.firebase.storage.UploadTask;import java.io.File;import java.io.IOException;import java.util.UUID;import de.hdodenhof.circleimageview.CircleImageView;public class ActivityQuanLySanPham extends AppCompatActivity {    ImageView ivHinh;    ImageView ivPic1;    ImageView ivPic2;    ImageView ivPic3;    EditText edtTen, edtGia, edtSl, edtDes;    Button btnThem;    DiaLogLoanding diaLogLoanding;    FirebaseDatabase database;    FirebaseAuth auth;    FirebaseStorage storage;    Uri imageHinh, pic1, pic2, pic3;    Bitmap hinh;    public static final int IMAGE_PICK_CODE = 1000;    public static final int PERMISSION_CODE = 1001;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_quan_ly_san_pham);        diaLogLoanding = new DiaLogLoanding(this);        database = FirebaseDatabase.getInstance();        auth = FirebaseAuth.getInstance();        storage = FirebaseStorage.getInstance();        setControl();        setEvent();    }    private void setEvent() {        ivHinh.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                openGalley();            }        });        btnThem.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                themSanPham(view);            }        });    }    private void openGalley() {        Intent intent = new Intent();        intent.setType("image/*");        intent.setAction(Intent.ACTION_GET_CONTENT);        startActivityForResult(intent,100);    }    @Override    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {        super.onActivityResult(requestCode, resultCode, data);        if (requestCode == 100 && resultCode == RESULT_OK && data != null){            imageHinh = data.getData();            ivHinh.setImageURI(imageHinh);        }    }    private void themSanPham(View view) {        String ten = edtTen.getText().toString();        String gia = edtGia.getText().toString();        String des = edtDes.getText().toString();        diaLogLoanding.ShowDiaLog("Đang thêm sản phẩm...");        if (TextUtils.isEmpty(ten)){            diaLogLoanding.HideDialog();            edtTen.setError("Nhập tên sản phẩm!");            return;        }else if (TextUtils.isEmpty(gia)){            diaLogLoanding.HideDialog();            edtGia.setError("Nhập giá sản phẩm!");            return;        }else if (TextUtils.isEmpty(des)){            diaLogLoanding.HideDialog();            edtDes.setError("Nhập chi tiết sản phẩm!");            return;        }        if (imageHinh != null){            //upLoadToFirebase(imageHinh);        }else {            Toast.makeText(diaLogLoanding, "Vui lòng chọn hình!", Toast.LENGTH_SHORT).show();        }        DatabaseReference root = database.getReference("product");        String productId = root.push().getKey();        StorageReference storageReference = FirebaseStorage.getInstance().getReference("images/"+auth.getUid()+"/"+productId+"."+getFileExtension(imageHinh));        storageReference.putFile(imageHinh).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {            @Override            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {                storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {                    @Override                    public void onComplete(@NonNull Task<Uri> task) {                        ProductModel product = new ProductModel(productId,task.getResult().toString(),0,ten,Integer.parseInt(gia),des,auth.getUid(),"shopName","","","");                        assert productId != null;                        root.child(productId).setValue(product, new DatabaseReference.CompletionListener() {                            @Override                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {                                diaLogLoanding.HideDialog();                                Toast.makeText(ActivityQuanLySanPham.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();                                startActivity(new Intent(ActivityQuanLySanPham.this,HomePageCuaHangActivity.class));                            }                        });                    }                });            }        }).addOnFailureListener(new OnFailureListener() {            @Override            public void onFailure(@NonNull Exception e) {                diaLogLoanding.HideDialog();                Toast.makeText(ActivityQuanLySanPham.this, "Up ảnh lỗi", Toast.LENGTH_SHORT).show();            }        });    }    private void upLoadToFirebase(Uri uri) {    }    private String getFileExtension(Uri uri){        ContentResolver cr = getContentResolver();        MimeTypeMap mime = MimeTypeMap.getSingleton();        return mime.getExtensionFromMimeType(cr.getType(uri));    }    public final void setImage(ImageView imageView, String avatar) {        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/user/" + avatar);        try {            final File file = File.createTempFile("ảnh", "jpg");            storageReference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {                @Override                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());                    imageView.setImageBitmap(bitmap);                    hinh = bitmap;                }            }).addOnFailureListener(new OnFailureListener() {                @Override                public void onFailure(@NonNull Exception e) {                    Log.d("Notify", "Load Image Fail");                }            });        } catch (IOException e) {            e.printStackTrace();        }    }    private void setControl() {        ivHinh = findViewById(R.id.ivThemHinhSP);        edtTen = findViewById(R.id.edtThemTenSP);        edtGia = findViewById(R.id.edtThemGiaSP);        edtDes = findViewById(R.id.edtThemChiTietSP);        btnThem = findViewById(R.id.btnThemSP);        ivPic1 = findViewById(R.id.ivPic1);        ivPic2 = findViewById(R.id.ivPic2);        ivPic3 = findViewById(R.id.ivPic3);    }}