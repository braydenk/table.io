package com.mad.mizen.order;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mad.mizen.R;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.data.models.Order;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ViewHolder> {

    @SuppressWarnings("unused")
    private static final String TAG = OrderRecyclerAdapter.class.getSimpleName();

    private Context context;
    private Order order;

    OrderRecyclerAdapter(Context context, Order order) {
        this.context = context;
        this.order = order;
    }

    @NonNull
    @Override
    public OrderRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerAdapter.ViewHolder holder, int position) {
        Item item = order.getItems().get(position);

        holder.itemQuantity.setText(item.getQuantity());
        holder.itemName.setText(item.getName());

        Log.d(TAG, "onBindViewHolder: " + item.getName());
    }

    @Override
    public int getItemCount() {
        return -1;
    }

    public void updateOrder(Order order) {
        this.order = order;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemQuantity;
        private TextView itemName;

        ViewHolder(View itemView) {
            super(itemView);

            itemQuantity = itemView.findViewById(R.id.order_item_quantity);
            itemName = itemView.findViewById(R.id.order_item_name);
        }
    }
}
