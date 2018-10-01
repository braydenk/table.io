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
import com.mad.mizen.R;
import com.mad.mizen.data.models.Item;
import com.mad.mizen.menu.MenuViewModel;
import dagger.android.support.AndroidSupportInjection;
import java.util.List;
import javax.inject.Inject;

public class OrderFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public OrderFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_fragment, container, false);

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
        OrderViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(OrderViewModel.class);
    }

    private void updateUI(List<Item> items) {
        if (items != null) {
            // TODO: Some UI stuff
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
