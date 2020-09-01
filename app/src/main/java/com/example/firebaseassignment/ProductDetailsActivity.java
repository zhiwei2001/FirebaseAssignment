package com.example.firebaseassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class ProductDetailsActivity extends AppCompatActivity {
    EditText edittext;
    TextView gname;
    TextView gprice;
    ImageView gimage;
    TextView name;
    TextView price;
    Button buttonAddtocart;
    ImageView image;
    FirebaseDatabase database;
    DatabaseReference ref;
    int prod =0;
    Member member;
    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);



        name=findViewById(R.id.name2);
        price=findViewById(R.id.price2);
        image=findViewById(R.id.rImageView2);

        byte[] bytes = getIntent().getByteArrayExtra("ImageProd1");
//        int mimage = getIntent().getIntExtra("ImageProd1", 0);
        String mname = getIntent().getStringExtra("NameProd1");
        String mprice = getIntent().getStringExtra("PriceProd1");

        Bitmap bmp = BitmapFactory.decodeByteArray(bytes,0,bytes.length);

        name.setText(mname);
        price.setText(mprice);
        image.setImageBitmap(bmp);
        Button button=findViewById(R.id.gocart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetailsActivity.this, Cart.class));
            }
        });


        buttonAddtocart=findViewById(R.id.btnAddtoCart);

        member=new Member();
        ref=database.getInstance().getReference().child("Cart");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    prod=(int) dataSnapshot.getChildrenCount();

                }
                else{
                    ///
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        String aimage = Base64.encodeToString(bytes,Base64.DEFAULT);
        buttonAddtocart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                member.setImageProd1(image.getDrawable().toString());
                member.setNameProd1(name.getText().toString());
                member.setPriceProd1(price.getText().toString());
                ref.child(String.valueOf(prod+1)).setValue(member);
                Toast.makeText(ProductDetailsActivity.this,"Added",Toast.LENGTH_SHORT);
            }

        });
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.send_message, Toast.LENGTH_SHORT);
        toast.show();
        edittext=findViewById(R.id.txtComment);
        edittext.setText("");
    }



    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}