package com.mad.mizen.order;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mad.mizen.R;
import com.mad.mizen.data.models.Order;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

public class OrderFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    OrderViewModel viewModel;

    TextView textView;

    public OrderFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_fragment, container, false);

        textView = view.findViewById(R.id.order_item_name);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.configureDagger();
        this.configureViewModel();
    }

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OrderViewModel.class);
        viewModel.init();
        viewModel.getOrder().observe(this, this::updateOrder);
    }

    private void updateOrder(Order order) {
        if (order != null) {
            textView.setText(order.getItems().getName());
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
