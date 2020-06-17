package myway.virtualagent.models.manufacturerProduct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ManufacturerProductList {

    @Expose
    @SerializedName("results")
    private List<ManufacturerProductList> results;
    @Expose
    @SerializedName("count")
    private int count;

    public List<ManufacturerProductList> getResults() {
        return results;
    }

    public void setResults(List<ManufacturerProductList> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public  class Results {
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
        private Manufacturer manufacturer;
        @Expose
        @SerializedName("category")
        private Category category;
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

        public Manufacturer getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(Manufacturer manufacturer) {
            this.manufacturer = manufacturer;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

    public  class Category {
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private int id;

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
}
