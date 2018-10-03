package com.mad.mizen.pay;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class PaymentRecyclerAdapter extends RecyclerView.Adapter<PaymentRecyclerAdapter.ViewHolder> {

    private List<Item> orderedItems;

    PaymentRecyclerAdapter(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.order_item_row, parent, false);

        return new PaymentRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = orderedItems.get(position);

        holder.itemQuantity.setText(String.format(Locale.US, "x%d", item.getQuantity()));
        holder.itemName.setText(item.getName());

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        String itemPrice = format.format(item.getPrice());

        holder.itemPrice.setText(itemPrice);


        holder.removeItem.setEnabled(false);
        holder.removeItem.setVisibility(View.INVISIBLE);
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
