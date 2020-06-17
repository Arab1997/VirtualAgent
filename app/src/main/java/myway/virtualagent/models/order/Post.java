package myway.virtualagent.models.order;

import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import myway.virtualagent.models.products.Results;

/*{
        "product": null,
        "type": null,
        "quantity": null
        }*/
public class Post {
    @SerializedName("product")
    private int product;

    @SerializedName("type")
    private int type;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("body")
    private String body;

    public Post(int product, int type, int quantity) {
        this.product = product;
        this.type = type;
        this.body = body;
        this.quantity = quantity;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
