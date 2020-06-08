package myway.virtualagent.models.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class Orderlist {

    @Expose
    @SerializedName("results")
    private List<Results> results;
    @Expose
    @SerializedName("next")
    private String next;
    @Expose
    @SerializedName("count")
    private int count;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public  class Results {
        @Expose
        @SerializedName("created_time")
        private String created_time;
        @Expose
        @SerializedName("quantity")
        private int quantity;
        @Expose
        @SerializedName("cost")
        private String cost;
        @Expose
        @SerializedName("type")
        private int type;
        @Expose
        @SerializedName("status")
        private int status;
        @Expose
        @SerializedName("store")
        private Store store;
        @Expose
        @SerializedName("manufacturer")
        private Manufacturer manufacturer;
        @Expose
        @SerializedName("product")
        private Product product;
        @Expose
        @SerializedName("id")
        private int id;

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Store getStore() {
            return store;
        }

        public void setStore(Store store) {
            this.store = store;
        }

        public Manufacturer getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(Manufacturer manufacturer) {
            this.manufacturer = manufacturer;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public  class Store {
        @Expose
        @SerializedName("district")
        private int district;
        @Expose
        @SerializedName("address")
        private String address;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private int id;

        public int getDistrict() {
            return district;
        }

        public void setDistrict(int district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public  class Manufacturer {
        @Expose
        @SerializedName("slug")
        private String slug;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("logo")
        private String logo;
        @Expose
        @SerializedName("id")
        private int id;

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class Product {
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
    }

    public  class Images {
        @Expose
        @SerializedName("photos")
        private List<Photos> photos;

        public List<Photos> getPhotos() {
            return photos;
        }

        public void setPhotos(List<Photos> photos) {
            this.photos = photos;
        }
    }

    public  class Photos {
        @Expose
        @SerializedName("image")
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
