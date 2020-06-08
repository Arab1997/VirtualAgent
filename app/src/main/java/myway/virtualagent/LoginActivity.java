package myway.virtualagent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cn.pedant.SweetAlert.SweetAlertDialog;
import myway.virtualagent.api.UserClient;
import myway.virtualagent.models.login.Login;
import myway.virtualagent.models.login.User;
import myway.virtualagent.utils.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
private EditText editTextUsername;
private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://178.128.194.133/auth/token/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    UserClient userClient = retrofit.create(UserClient.class);
    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    private static String token;
    private void login() {


        User user = SharedPrefManager.getInstance(this).getToken();
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        Login login = new Login(username, password);
        Call<User> call = userClient.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                SweetAlertDialog pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Загрузка ...");
                pDialog.setCancelable(true);
                //  pDialog.show();
                User user = response.body();

                if (response.isSuccessful()) {
                    //     Toast.makeText(LoginActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                    token = response.body().getToken();

                    SharedPrefManager.getInstance(LoginActivity.this)
                            .saveUser(user);

                    SharedPrefManager.getInstance(LoginActivity.this)
                            .saveToken(token);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                } else {
                    // Toast.makeText(LoginActivity.this, "Логин или пароль не правильный", Toast.LENGTH_SHORT).show();
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Логин или пароль не правильный")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //   Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Сервер не работает\n")
                        .show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                login();
                break;

        }
    }
}