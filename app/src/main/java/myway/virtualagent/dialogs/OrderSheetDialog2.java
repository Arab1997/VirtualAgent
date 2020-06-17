package myway.virtualagent.dialogs;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import co.ceryle.segmentedbutton.SegmentedButtonGroup;
import myway.virtualagent.R;
import myway.virtualagent.adapters.PostAdapter;
import myway.virtualagent.api.RetrofitClient;
import myway.virtualagent.models.login.Login;
import myway.virtualagent.models.order.Post;
import myway.virtualagent.models.products.Results;
import myway.virtualagent.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderSheetDialog2 extends BottomSheetDialog {
    private List<Post> posts;
    private List<Results> product;
    private PostAdapter postAdapter;
    private RecyclerView recyclerView;
    Results results;

    ProgressBar progressBar;
    private SegmentedButtonGroup type;
    ImageView minus, plus, productImage;
    private TextView productname, price, qty, productprice, textBack, text;
    private Button orderbtn;
    int quantity = 1;


    public OrderSheetDialog2(@NonNull Context context, @NonNull Results results) {
        super(context, R.style.BottomSheetDialogTheme);
        this.results = results;
        setContentView(R.layout.persistent_bottomsheet);


        populate();
    }

    @SuppressLint("CutPasteId")
    private void populate() {
        progressBar = findViewById(R.id.progressbar);
        productImage = findViewById(R.id.orderimage);
        productname = findViewById(R.id.productname);
        price = findViewById(R.id.productprice);
        progressBar = findViewById(R.id.progressbar);

        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);

        qty = findViewById(R.id.qty);
        productprice = findViewById(R.id.quantityprice);
        type = findViewById(R.id.type);
        text = findViewById(R.id.text);

        orderbtn = findViewById(R.id.orderbutton);
        textBack = findViewById(R.id.orderback);

        Picasso.get()
                .load(results.getImages().getPhotos().get(0).getImage())
                .placeholder(R.drawable.ic_plus)
                .into(productImage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d("Error : ", e.getMessage());
                    }
                });

        productname.setText(results.getName());
        price.setText(results.getCost() + "cум");
        productprice.setText(results.getCost());
        final Double priceValue = Double.valueOf(results.getCost());

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                results.setQuantity(quantity);
                qty.setText(String.valueOf(quantity));
                productprice.setText(Utils.formatDouble((priceValue * quantity)) + "cум");

                qty.getText().toString();
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity != 1) {
                    quantity--;
                    results.setQuantity(quantity);
                    qty.setText(String.valueOf(quantity));
                    productprice.setText(Utils.formatDouble((priceValue * quantity)) + "cум");

                    qty.getText().toString();
                }
            }
        });
        type.setOnClickedButtonListener(new SegmentedButtonGroup.OnClickedButtonListener() {
            @Override
            public void onClickedButton(int position) {

                if (position == 0) {
                    int x = ++position;
                    Log.d("check", String.valueOf(x));

                } else if (position == 1) {
                    int x = ++position;
                    Log.d("check", String.valueOf(x));

                } else if (position == 2) {
                    int x = ++position;
                    Log.d("check", String.valueOf(x));

                }
            }
        });

        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 createPost();
            }
        });
        textBack.setOnClickListener(v -> dismiss());
    }

    private void createPost() {
        int type1 = type.getPosition() + 1;
        int qty1 = Integer.parseInt(qty.getText().toString());
        int product = results.getId();

        Post post = new Post(product, type1, qty1);
        Call<Post> call = RetrofitClient.getInstance().getApi()
                .createPost(post);
        call.enqueue(new Callback<Post>() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.code() == 201) {
                    // 1. Success message
                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Спасибо за покупкы")
                            .setContentText("ЗАКАЗ ОТПРАВЛЕНЬ")
                            .show();
                } else if (response.code() == 400) {
                    Log.d(getClass().getName(), response.message() + " " + response.body());
                    new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("ЗАКАЗ НЕ ОТПРАВЛЕНЬ")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Сервер не работает")
                        .show();
            }
        });
    }

}