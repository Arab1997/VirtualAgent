package myway.virtualagent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import myway.virtualagent.MainActivity;
import myway.virtualagent.R;
import myway.virtualagent.fragments.ManufacturerFragment;
import myway.virtualagent.models.manfacturer.Manufacturer;

public  class MAdapter extends RecyclerView.Adapter<MAdapter.MyViewHolder> {
    private String ImageUrl;
    private List<Manufacturer.ManfacturerResults> results;
    private MainActivity profileActivity;
    private Adapter.OnItemClickListener onItemClickListener;
    private Context context;
    private LayoutInflater inflater;

    public MAdapter(List<Manufacturer.ManfacturerResults> results, ManufacturerFragment manufacturerFragment) {
        this.results = results;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new MAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MAdapter.MyViewHolder holders, int position) {
        final MAdapter.MyViewHolder holder = holders;
        Manufacturer.ManfacturerResults result = results.get(position);
        holder.name.setText(result.getName());
        holder.slug.setText(result.getSlug());

        Picasso.get().load(String.valueOf(result.getLogo()))
                .into(holder.logo);
    }
    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        ProgressBar progressBar;

        public TextView name;
        public TextView slug;
        public ImageView logo;


        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.manname);
            slug = itemView.findViewById(R.id.manslug);
            logo = itemView.findViewById(R.id.manlogo);
          //  progressBar = itemView.findViewById(R.id.prograss_load_photo);

        }


    }
}
