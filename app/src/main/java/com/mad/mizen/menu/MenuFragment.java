package com.mad.mizen.menu;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    List<Item> items;

    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    private MenuRecyclerAdapter adapter;

    private String category = "all";

    public MenuFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

        tabLayout = view.findViewById(R.id.tab_layout);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new MenuRecyclerAdapter(getContext(), new ArrayList<>(), MenuFragment.this);

        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        this.configureDagger();
        this.configureViewModel();

        tabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {

                int currentTab = tab.getPosition();

                switch (currentTab) {
                    case 0:
                        category = "all";
                    case 1:
                        category = "entree";
                        break;
                    case 2:
                        category = "side";
                        break;
                    case 3:
                        category = "main";
                        break;
                    case 4:
                        category = "dessert";
                        break;
                    case 5:
                        category = "drink";
                        break;
                }

                filterMenu(category);
            }

            @Override
            public void onTabUnselected(Tab tab) { }

            @Override
            public void onTabReselected(Tab tab) { }
        });
    }

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MenuViewModel.class);

        viewModel.init();

        viewModel.getItems().observe(this, this::updateItems);
    }

    private void updateItems(List<Item> items) {
        if (items != null) {
            this.items = items;

            filterMenu(category);
        }
    }

    private void filterMenu(String category) {
        List<Item> filteredItems = new ArrayList<>();

        if (!category.equals("all")){
            for (Item i : items) {
                if (i.getCategory().equals(category)) {
                    filteredItems.add(i);
                }
            }
            adapter.updateFilter(filteredItems);
        } else {
            adapter.updateFilter(items);
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

    public void addItemToOrder(Item item) {
        viewModel.addItemToOrder(item);
    }
}
