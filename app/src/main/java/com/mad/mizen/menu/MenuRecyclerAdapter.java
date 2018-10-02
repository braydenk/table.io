package com.mad.mizen.menu;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.mad.mizen.MainActivity;
import com.mad.mizen.R;
import com.mad.mizen.data.models.Item;
import java.util.List;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder> {

    @SuppressWarnings("unused")
    private static final String TAG = MenuRecyclerAdapter.class.getSimpleName();

    private Context context;
    private List<Item> items;

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

        // TODO: Better formatting for currency.
        String itemPrice = "$" + Double.toString(item.getPrice());

        holder.name.setText(itemName);
        holder.description.setText(itemDescription);
        holder.price.setText(itemPrice);

        holder.addBtn.setOnClickListener((View view) -> {
            Log.d(TAG, "onBindViewHolder: clicked");

            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.item_popup);

            Button dialogButton = dialog.findViewById(R.id.close_popup_btn);
            dialogButton.setOnClickListener((View dialogView) -> dialog.dismiss());

            TextView popupName = dialog.findViewById(R.id.item_name);
            TextView popupDescription = dialog.findViewById(R.id.item_description);

            popupName.setText(itemName);
            popupDescription.setText(itemDescription);

            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateFilter(List<Item> items) {
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
