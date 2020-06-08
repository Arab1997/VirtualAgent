package myway.virtualagent.models.order;

import com.google.gson.annotations.SerializedName;

/*{
        "product": null,
        "type": null,
        "quantity": null
        }*/
public class Post {
    @SerializedName("product")
    private String product;

    @SerializedName("type")
    private int type;

    @SerializedName("quantity")
    private CharSequence quantity;

    @SerializedName("body")
    private String body;

    public Post(String product, int type, CharSequence quantity) {
        this.product = product;
        this.type = type;
        this.body = body;
        this.quantity = quantity;

    }





    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CharSequence getQuantity() {
        return quantity;
    }

    public void setQuantity(CharSequence quantity) {
        this.quantity = quantity;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
