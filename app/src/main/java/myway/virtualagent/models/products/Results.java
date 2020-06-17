package myway.virtualagent.models.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {
    @Expose
    @SerializedName("slug")
    private String slug;
    @Expose
    @SerializedName("created")
    private String created;
    @Expose
    @SerializedName("quantity")
    private int quantity;
    @Expose
    @SerializedName("cost")
    private String cost;
    @Expose
    @SerializedName("images")
    private Images images;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("manufacturer")
    private Products.Manufacturer manufacturer;
    @Expose
    @SerializedName("category")
    private Products.Category category;
    @Expose
    @SerializedName("id")
    private int id;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Products.Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Products.Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Products.Category getCategory() {
        return category;
    }

    public void setCategory(Products.Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}