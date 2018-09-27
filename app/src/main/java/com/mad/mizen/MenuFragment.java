package com.mad.mizen;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mad.mizen.data.models.Item;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MenuFragment extends Fragment {

    private static final String TAG = MenuFragment.class.getSimpleName();

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MenuViewModel viewModel;

    private RecyclerView recyclerView;

    public MenuFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MenuViewModel.class);
        viewModel.init();
        viewModel.getItems().observe(this, this::updateUI);
    }

    private void updateUI(List<Item> items) {
        if (items != null) {
            setupMenuAdapter(items);
        }
    }

    private void setupMenuAdapter(List<Item> items) {

        MenuRecyclerAdapter adapter = new MenuRecyclerAdapter(getContext(), new ArrayList<>());

        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        adapter.addItems(items);
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
