package com.example.weddingplanner;

public class Holder {
    private String title;
    private String description;
    private String image_url;
    private String location;
    private double rating;
    private int review;
    private int price;
    private int pplLimit;
    private boolean fav=false;

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setReview(int review) {
        this.review = review;
    }


    public Holder(String title, String description, String image_url, String location, double rating, int review, int price, int pplLimit) {
        this.title = title;
        this.description = description;
        this.image_url = image_url;
        this.location = location;
        this.rating = rating;
        this.review = review;
        this.price=price;
        this.pplLimit=pplLimit;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getPplLimit() {
        return pplLimit;
    }

    public Holder(String title, String location, double rating, int review) {
        this.title = title;
        this.location = location;
        this.rating = rating;
        this.review = review;
    }

    public String getSm() {
        return title;
    }
    public String getLocation() {
        return location;
    }

    public double getRating() {
        return rating;
    }

    public int getReview() {
        return review;
    }
    public String getDescription() {
        return description;
    }

    public String getImage_url() {
        return image_url;
    }

}
