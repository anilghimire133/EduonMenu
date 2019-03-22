package com.bgtechsolution.expandablelistview;

import java.io.Serializable;

public class ChildPojo implements Serializable {

    public ChildPojo(String price, String menu_id){

        this.Menu_Id = menu_id;

        this.Price = price;
//        this.Name = name;
//        this.Image = image;
//        this.Description = desc;
    }

    private String Menu_Id, Price, Name, Likes, Image, Description;

    public String getPrice() {
        return Price;
    }

    public String setPrice(String price){
        return Price;
    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMenu_Id() {
        return Menu_Id;
    }

    public void setMenu_Id(String menu_Id) {
        Menu_Id = menu_Id;
    }
}
