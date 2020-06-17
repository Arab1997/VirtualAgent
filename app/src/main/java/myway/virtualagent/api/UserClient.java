package myway.virtualagent.api;


import java.util.List;

import myway.virtualagent.dialogs.OrderSheetDialog2;
import myway.virtualagent.models.login.Login;
import myway.virtualagent.models.login.User;
import myway.virtualagent.models.manfacturer.Manufacturer;
import myway.virtualagent.models.order.OrderList;
import myway.virtualagent.models.order.Post;
import myway.virtualagent.models.products.Products;
import retrofit2.Call;
import retrofit2.http.Body;
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
    Call<OrderList> getOrder(@Query("page") int pageIndex);

    //http://178.128.194.133/api/v1/order/create
    @POST("order/create/")
    Call<Post> createPost(@Body Post post);

    @GET("products/?filter[name]=")
    Call<Products> getProductSearch(
            @Query("filter[name]") String keyword

    );
    //http://178.128.194.133/api/v1/products/?filter[name]=
       /*  @GET("everything")
   http://newsapi.org/v2/everything?q=apple&from=2020-06-14&to=2020-06-14&sortBy=popularity&apiKey=b10f00b05bd34c23b05ec574a67ef2c0
    Call<News> getNewsSearch(

            @Query("q") String keyword,
            @Query("language") String language,
            @Query("sortBY") String sortBy,
            @Query("apiKey") String apiKey

    );*/

 /*   @POST("order/create/")
    @FormUrlEncoded
    Call<Post> createPost(@Body("product") String product,
                          @Body("type") String type,
                          @Body("quantity") String quantity);*/
}