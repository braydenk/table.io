package com.mad.mizen.pay;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.mad.mizen.main.MainActivity;
import com.mad.mizen.R;
import com.mad.mizen.data.models.Item;
import com.stripe.android.model.Card;
import dagger.android.AndroidInjection;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;

public class PaymentActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    PayViewModel viewModel;

    RecyclerView recyclerView;
    PaymentRecyclerAdapter adapter;
    Button payBtn;

    // Stripe card model
    Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payBtn = findViewById(R.id.pay_btn);

        double total = getIntent().getDoubleExtra("TOTAL", 0);

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        String totalPriceString = "PAY: " + format.format(total);

        payBtn.setText(totalPriceString);

        recyclerView = findViewById(R.id.payment_recycler_view);

        adapter = new PaymentRecyclerAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        payBtn.setOnClickListener(this::submitCard);

        configureDagger();
        configureViewModel();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        toolbar.setNavigationOnClickListener((View view) -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        //setSupportActionBar(toolbar);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.call_staff:
                // TODO: Change this to payment activity
                Snackbar.make(findViewById(R.id.main_activity), "A staff member will be with you shortly", Snackbar.LENGTH_LONG).show();
                break;
            default:
                break;
        }

        return true;
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    private void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PayViewModel.class);
        viewModel.init();
        viewModel.getOrder().observe(this, this::updateOrder);
    }

    private void updateOrder(List<Item> orderedItems) {
        if (orderedItems != null) {
            adapter.updateOrder(orderedItems);
        }
    }

    public void submitCard(View view) {
        TextView cardNumberField = findViewById(R.id.card_number);
        TextView monthField = findViewById(R.id.card_exp_month);
        TextView yearField = findViewById(R.id.card_exp_year);
        TextView cvcField = findViewById(R.id.card_cvc);

        card = new Card(
                cardNumberField.getText().toString(),
                Integer.valueOf(monthField.getText().toString()),
                Integer.valueOf(yearField.getText().toString()),
                cvcField.getText().toString()
        );

        if (!card.validateCard()) {
            Snackbar.make(view, R.string.card_error, Snackbar.LENGTH_LONG).show();
        }
    }
}
