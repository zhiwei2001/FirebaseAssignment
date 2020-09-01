package com.example.firebaseassignment;

public class Upload {
    String NameProd1,PriceProd1;
    String ImageProd1;
    public Upload(){

    }
    public Upload(String name, String price, String image){
        NameProd1=name;
        PriceProd1=price;
        ImageProd1=image;
    }

    public String getNameProd1() {
        return NameProd1;
    }

    public void setNameProd1(String name) {
        NameProd1 = name;
    }

    public String getPriceProd1() {
        return PriceProd1;
    }

    public void setPriceProd1(String price) {
        PriceProd1 = price;
    }

    public String getImageProd1() {
        return ImageProd1;
    }

    public void setImageProd1(String image) {
        ImageProd1 = image;
    }
}
