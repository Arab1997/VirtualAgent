package myway.virtualagent.api;


import myway.virtualagent.models.login.Login;
import myway.virtualagent.models.login.User;
import myway.virtualagent.models.manfacturer.Manufacturer;
import myway.virtualagent.models.order.Orderlist;
import myway.virtualagent.models.order.Post;
import myway.virtualagent.models.products.Products;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserClient {
    //http://178.128.194.133/auth/token/login
    @POST("login")
    Call<User> login(@Body Login login);

    // http://178.128.194.133/api/v1/products/
    @Headers("token: d1cb7fe42c63d70cd6ad17475cbc78deab72f066")
    @GET("products/")
    Call<Products> getResults();

    //http://178.128.194.133/api/v1/manufacturer/
    @Headers("token: b28014bb53cef1dedbdbb178f9f6a1d26f264acd")
    @GET("manufacturer/")
    Call<Manufacturer> getResult();


    //http://178.128.194.133/api/v1/order/
    @Headers("token: b28014bb53cef1dedbdbb178f9f6a1d26f264acd")
    @GET("order/")
    Call<Orderlist> getOrder(@Query("page") int pageIndex);

    //http://178.128.194.133/api/v1/order/
    @Headers("token: b28014bb53cef1dedbdbb178f9f6a1d26f264acd")
    @POST("order/create/")
    Call<Orderlist> getCreate();

    //http://178.128.194.133/api/v1/order/create
/*    @POST("order/create/")
    Call<Post> createPost(@Body Post post);*/


    @POST("order/create/")
    @FormUrlEncoded
    Call<Post> createPost(@Field("product") String product,
                          @Field("type") String type,
                          @Field("quantity") String quantity

    );
}