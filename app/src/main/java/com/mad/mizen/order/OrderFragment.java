package com.mad.mizen.order;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mad.mizen.R;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.pay.PaymentActivity;
import dagger.android.support.AndroidSupportInjection;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;

public class OrderFragment extends Fragment {

    @SuppressWarnings("unused")
    private static final String TAG = OrderFragment.class.getSimpleName();

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    OrderViewModel viewModel;

    RecyclerView recyclerView;
    OrderRecyclerAdapter adapter;

    Button orderButton;
    TextView totalPriceTextView;

    public OrderFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_fragment, container, false);

        recyclerView = view.findViewById(R.id.order_recycler_view);
        orderButton = view.findViewById(R.id.order_button);
        totalPriceTextView = view.findViewById(R.id.total_price);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new OrderRecyclerAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);


        this.configureDagger();
        this.configureViewModel();

        orderButton.setOnClickListener((View view) -> {

            if (orderButton.getText().toString().equals("Order")) {
                orderButton.setText(R.string.pay_now);
            } else {
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(intent);
            }
        });

    }

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OrderViewModel.class);
        viewModel.init();
        viewModel.getOrder().observe(this, this::updateOrder);
    }

    private void updateOrder(List<Item> orderedItems) {
        if (orderedItems != null) {
            adapter.updateOrder(orderedItems);

            double total = 0;
            for (Item i : orderedItems) {
                total += i.getPrice() * i.getQuantity();
            }

            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
            String totalPriceString = format.format(total);

            totalPriceTextView.setText(totalPriceString);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
