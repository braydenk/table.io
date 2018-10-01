package com.mad.mizen.menu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.mad.mizen.R;
import com.mad.mizen.data.models.Item;
import java.util.List;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Item> items;

    private PopupWindow popupWindow;


    MenuRecyclerAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRecyclerAdapter.ViewHolder holder, int position) {

        Item item = items.get(position);

        String itemName = item.getName();
        String itemDescription = item.getDescription();
        String itemPrice = "$" + Double.toString(item.getPrice());

        holder.name.setText(itemName);
        holder.description.setText(itemDescription);
        holder.price.setText(itemPrice);

        holder.itemView.setOnClickListener((View view) -> {
            // TODO: Some click handling.
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView description;
        private TextView price;
        private Button addBtn;

        ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            addBtn = itemView.findViewById(R.id.add_button);
        }
    }
}
