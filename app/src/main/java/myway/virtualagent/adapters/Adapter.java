package myway.virtualagent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import myway.virtualagent.MainActivity;
import myway.virtualagent.R;
import myway.virtualagent.fragments.HomeFragment;
import myway.virtualagent.models.products.Results;
import myway.virtualagent.utils.OnClickListener;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private String ImageUrl;
    private List<Results> results;
    private MainActivity profileActivity;
    private OnItemClickListener onItemClickListener;
    private Context context;
    private LayoutInflater inflater;
    private ListItemClickListener itemClickListener;
    private OnClickListener listener;

    public Adapter(Context context, List<Results> results) {
        this.context = context;
        this.results = results;
     //   inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Adapter(ListItemClickListener listener) {
        itemClickListener = listener;
        results = new ArrayList<>();
    }

    public Adapter(List<Results> results, HomeFragment profileActivity, ListItemClickListener listener) {
        this.results = results;
        this.context = context;
        itemClickListener = listener;
        results = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.useitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, final int position) {
        final MyViewHolder holder = holders;
        Results result = results.get(position);
        holder.name.setText(result.getName());
        holder.manfactname.setText(result.getManufacturer().getName());
        holder.cost.setText(result.getCost() + "сум");

        Picasso.get().load(String.valueOf(result.getImages().getPhotos().get(0).getImage()))
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }

      public Results getItem(int item) {
      return results.get(item);
    }



    public interface ListItemClickListener {
        void onItemClick(Results results);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        OnItemClickListener onItemClickListener;
        public TextView catname;
        public TextView manfactname;
        public TextView name;
        public TextView cost;
        public ImageView image;
        public ImageView manfactlogo;
        public Button order;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            manfactname = itemView.findViewById(R.id.manfactname);
            cost = itemView.findViewById(R.id.cost1);
            image = itemView.findViewById(R.id.images);
            order = itemView.findViewById(R.id.order);
                itemView.findViewById(R.id.order).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onOrderClick(getAdapterPosition());
                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onClickListener(getAdapterPosition());
                    }
                });
        }
    }

}
