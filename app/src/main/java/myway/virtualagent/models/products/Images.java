package myway.virtualagent.models.products;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class Images {

    @Expose
    @SerializedName("photos")
    private List<Photos> photos;


    @Expose
    @SerializedName("images")
    private String images;

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

/*  public static class Photos {
        @Expose
        @SerializedName("image")
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
        }*/

    }
