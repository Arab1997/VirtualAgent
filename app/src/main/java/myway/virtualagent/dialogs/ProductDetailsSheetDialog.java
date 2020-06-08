package myway.virtualagent.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import myway.virtualagent.R;
import myway.virtualagent.models.order.Orderlist;
import myway.virtualagent.models.products.Results;

public class ProductDetailsSheetDialog extends BottomSheetDialog {

    private Context context;
    private LayoutInflater inflater;
    private Results results;
    private Orderlist orderlist;
    private Results productInfo;

    public ProductDetailsSheetDialog(@NonNull Context context, @NonNull Results results){
        super(context, R.style.BottomSheetDialogTheme);
        this.results = results;

        setContentView(R.layout.bottom_sheet);
        populate();
    }

    private void populate() {

         ImageView tvProductImage = findViewById(R.id.images);

         TextView tvProductName = findViewById(R.id.name);
         TextView tvProductCost = findViewById(R.id.cost);
         TextView tvProductCategory = findViewById(R.id.category);
    //     TextView tvProductNumber = findViewById(R.id.number);
         TextView tvProductQuantity = findViewById(R.id.quantity);
         TextView tvProductManufactName = findViewById(R.id.manfactname);
         TextView textBack = findViewById(R.id.back);

        Picasso.get()
                .load(results.getImages().getPhotos().get(0).getImage())
                .placeholder(R.drawable.ic_plus)
                .into(tvProductImage);

        tvProductName.setText(results.getName());
        tvProductCost.setText(results.getCost() + "cум" + "");
        tvProductCategory.setText(results.getCategory().getName());
        tvProductManufactName.setText(results.getManufacturer().getName());


        textBack.setOnClickListener(v -> dismiss());
    }


}
