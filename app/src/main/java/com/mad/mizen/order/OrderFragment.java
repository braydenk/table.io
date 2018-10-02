package com.mad.mizen.order;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mad.mizen.R;
import com.mad.mizen.data.models.Item;
import dagger.android.support.AndroidSupportInjection;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class OrderFragment extends Fragment {

    @SuppressWarnings("unused")
    private static final String TAG = OrderFragment.class.getSimpleName();

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    OrderViewModel viewModel;

    RecyclerView recyclerView;
    OrderRecyclerAdapter adapter;

    public OrderFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_fragment, container, false);

        recyclerView = view.findViewById(R.id.order_recycler_view);

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
            Log.d(TAG, "updateOrder: called" );
            adapter.updateOrder(orderedItems);
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
