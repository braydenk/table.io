package com.mad.mizen.pay;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.mad.mizen.R;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Inject;

public class PaymentActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    PayViewModel payViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }
}
