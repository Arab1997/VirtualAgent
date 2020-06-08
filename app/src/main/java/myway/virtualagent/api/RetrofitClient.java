package myway.virtualagent.api;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String AUTH = "Basic " + Base64.encodeToString(("test:test").getBytes(), Base64.NO_WRAP);
    private static final String Token = "d1cb7fe42c63d70cd6ad17475cbc78deab72f066";


    private static final String BASE_URL = "http://178.128.194.133/auth/token/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;


    private RetrofitClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(
                new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder()
                                .addHeader("Authorization", "token d1cb7fe42c63d70cd6ad17475cbc78deab72f066")
                                .method(original.method(), original.body());
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                }).build();

        httpClient.interceptors().add(logging);

       retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://178.128.194.133/api/v1/")
                .client(httpClient.build())
                .build();

    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public UserClient getApi() {
        return retrofit.create(UserClient.class);
    }
}
