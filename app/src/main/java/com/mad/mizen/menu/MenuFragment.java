package com.mad.mizen.menu;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mad.mizen.R;
import com.mad.mizen.data.models.Item;
import dagger.android.support.AndroidSupportInjection;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MenuFragment extends Fragment {

    @SuppressWarnings("unused")
    private static final String TAG = MenuFragment.class.getSimpleName();

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    MenuViewModel viewModel;

    private RecyclerView recyclerView;

    public MenuFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        tabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                // TODO: Send info to fragment.

                if (tab.getText().equals("Entree")) {
                    viewModel.filterCategory("entree");
                } else if (tab.getText().equals("Sides")) {
                    viewModel.filterCategory("sides");
                } else if (tab.getText().equals("Main")) {
                    viewModel.filterCategory("main");
                } else if (tab.getText().equals("Desserts")) {
                    viewModel.filterCategory("dessert");
                } else if (tab.getText().equals("Drinks")) {
                    viewModel.filterCategory("drinks");
                }
            }

            @Override
            public void onTabUnselected(Tab tab) {

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });

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
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MenuViewModel.class);
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
