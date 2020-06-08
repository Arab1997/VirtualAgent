package myway.virtualagent.models.products;

import com.google.gson.annotations.SerializedName;

public  class Photos {

    @SerializedName("image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String images) {
        this.image = images;
    }

}
