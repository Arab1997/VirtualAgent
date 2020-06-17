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



import java.util.List;

import myway.virtualagent.R;
import myway.virtualagent.fragments.OrdersListFragment;
import myway.virtualagent.models.order.OrderList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {


    private List<OrderList.OrderResults> results;
    private Context context;

    public OrderAdapter(List<OrderList.OrderResults> results, OrdersListFragment ordersListFragment) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holders, int position) {
        final OrderAdapter.MyViewHolder holder = holders;
        OrderList.OrderResults result = results.get(position);
        holder.productname.setText(result.getProduct().getName());
        holder.productcost.setText(result.getProduct().getCost());
        holder.manufactname.setText(result.getManufacturer().getName());
     //   holder.storename.setText(result.getQuantity());
        holder.storeaddress.setText(result.getStore().getAddress());
        holder.status.setText(result.getCreated_time());

      //  holder.storedistrict.setText(result.getStore().getDistrict());
//        holder.status.setText(result.getStatus());
//        holder.statustype.setText(result.getType());
//        holder.statuscost.setText(result.getCost());
//        holder.statusquantity.setText(result.getQuantity());
//        holder.statuscreated_time.setText(result.getCreated_time());



    }
    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }

    public void addItems(List<OrderList.OrderResults> items) {
        results.addAll(items);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        Adapter.OnItemClickListener onItemClickListener;
        public TextView productname;
        public ImageView productimage;
        public TextView productcost;
        public TextView productquantity;
        public ImageView manufactlogo;
        public TextView manufactname;
        public TextView storename;
        public TextView storeaddress;
        public TextView storedistrict;
        public TextView status;
        public TextView statustype;
        public TextView statuscost;
        public TextView statusquantity;
        public TextView statuscreated_time;


        public MyViewHolder(View itemView) {
            super(itemView);
            productname = itemView.findViewById(R.id.proname);
            manufactname = itemView.findViewById(R.id.manufactname);
            productcost = itemView.findViewById(R.id.productcost);
            storename = itemView.findViewById(R.id.storename);
            storeaddress = itemView.findViewById(R.id.storeaddress);
            status = itemView.findViewById(R.id.status);
            productimage = itemView.findViewById(R.id.images);

        }

    }
}
