package com.example.firebaseassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.io.ByteArrayOutputStream;

public class Cart extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    StorageReference storageReference;
    private StorageTask upload;
    private Uri imagUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mRecyclerView = findViewById(R.id.recyelerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        firebaseDatabase = FirebaseDatabase.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference("Cart");
        reference = firebaseDatabase.getReference("Cart");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Member, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Member, ViewHolder>(
                        Member.class,
                        R.layout.image,
                        ViewHolder.class,
                        reference
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Member data, int i) {

                        viewHolder.setdetails(getApplicationContext(), data.getNameProd1(), data.getPriceProd1(), data.getImageProd1());
                    }


//                    @Override
//                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//                        ViewHolder viewHolder = super.onCreateViewHolder(parent,viewType);
//                        viewHolder.setOnclickListener(new ViewHolder.ClickListener() {
//                            @Override
//                            public void onItemclick(View view, int position) {
//                                TextView mname=view.findViewById(R.id.name);
//                                TextView mprice=view.findViewById(R.id.price);
//                                ImageView mimageView= view.findViewById(R.id.rImageView);
//
//                                String name=mname.getText().toString();
//                                String price=mprice.getText().toString();
//                                Drawable mDrawable = mimageView.getDrawable();
//                                Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();
//                                Intent intent=new  Intent(view.getContext(),ProductDetailsActivity.class);
//                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                                mBitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
//                                byte[]bytes = stream.toByteArray();
//
//                                intent.putExtra("ImageProd1",bytes);
//                                intent.putExtra("NameProd1",name);
//                                intent.putExtra("PriceProd1",price);
//                                startActivity(intent);
//
//                            }
//
//                            @Override
//                            public void onItemLongclick(View view, int position) {
//
//                            }
//                        });
//
//                       return viewHolder;
//                    }
//                };
//
//
//


                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}