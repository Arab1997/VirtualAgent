package myway.virtualagent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import myway.virtualagent.R;
import myway.virtualagent.fragments.HomeFragment;
import myway.virtualagent.models.order.Post;
import myway.virtualagent.utils.OnClickListener;
import retrofit2.Callback;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<Post> posts;
    private Context context;
    private OnClickListener listener;


    public PostAdapter(List<Post> posts, HomeFragment ordersListFragment) {
        this.posts = posts;
        this.context = context;
    }

    public PostAdapter(List<Post> posts, Callback<Post> postCallback) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog, parent, false);
        return new PostAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holders, int position) {
        final PostAdapter.MyViewHolder holder = holders;
        Post post = posts.get(position);
        holder.productorder.setText(post.getProduct());
        holder.typeorder.setText(post.getType());
        holder.quantityorder.setText(post.getQuantity());

    }
    @Override
    public int getItemCount() {
        return posts == null ? 0 : posts.size();
    }

    public void addItems(List<Post> items) {
        posts.addAll(items);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        Adapter.OnItemClickListener onItemClickListener;
        public EditText productorder;
        public EditText typeorder;
        public EditText quantityorder;
        public Button orderbtn;

        public MyViewHolder(View itemView) {
            super(itemView);
           /* productorder = itemView.findViewById(R.id.productorder);
            typeorder = itemView.findViewById(R.id.typeorder);*/
           // quantityorder = itemView.findViewById(R.id.quantityorder);
           // orderbtn = itemView.findViewById(R.id.orderbtn);

            {
                itemView.findViewById(R.id.orderbutton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClickListener(getAdapterPosition());
                    }
                });
            }
        }

    }
}

