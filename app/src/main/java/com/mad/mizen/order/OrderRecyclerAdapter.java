package com.mad.mizen.order;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mad.mizen.R;
import com.mad.mizen.data.models.Item;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ViewHolder> {

    @SuppressWarnings("unused")
    private static final String TAG = OrderRecyclerAdapter.class.getSimpleName();

    private Context context;
    private List<Item> orderedItems;
    private OrderFragment orderFragment;

    OrderRecyclerAdapter(Context context, List<Item> orderedItems, OrderFragment orderFragment) {
        this.context = context;
        this.orderedItems = orderedItems;
        this.orderFragment = orderFragment;
    }

    @NonNull
    @Override
    public OrderRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerAdapter.ViewHolder holder, int position) {

        Item item = orderedItems.get(position);

        holder.itemQuantity.setText(String.format(Locale.US, "x%d", item.getQuantity()));
        holder.itemName.setText(item.getName());

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        String itemPrice = format.format(item.getPrice());

        holder.itemPrice.setText(itemPrice);

        holder.removeItem.setOnClickListener((View view) -> removeItem(item));

    }

    private void removeItem(Item item) {
        for (Item i : orderedItems) {
            if (i.getItemId() == item.getItemId()) {
                orderedItems.remove(i);
                break;
            }
        }

        orderFragment.removeItem(item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (orderedItems == null) {
            return 0;
        }

        return orderedItems.size();
    }

    public void updateOrder(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemQuantity;
        private TextView itemName;
        private TextView itemPrice;
        private Button removeItem;

        ViewHolder(View itemView) {
            super(itemView);

            itemQuantity = itemView.findViewById(R.id.order_item_quantity);
            itemName = itemView.findViewById(R.id.order_item_name);
            itemPrice = itemView.findViewById(R.id.order_item_price);
            removeItem = itemView.findViewById(R.id.close_btn);
        }
    }
}
