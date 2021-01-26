package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.pc.bagsshoes.databinding.ActivityHostBinding;
import com.example.pc.bagsshoes.databinding.ActivityStoreBinding;
import com.stripe.android.PaymentSession;
import com.stripe.android.PaymentSessionConfig;
import com.stripe.android.PaymentSessionData;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.model.ShippingInformation;

import org.jetbrains.annotations.NotNull;

@AndroidEntryPoint
public class HostActivity extends AppCompatActivity {

    private ActivityHostBinding binding;
    private PaymentSession paymentSession;
    private Button startPaymentFlowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityHostBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );
        // Create the PaymentSession
        paymentSession = new PaymentSession(
                this,
                createPaymentSessionConfig()
        );

        // Attach your listener
        paymentSession.init(createPaymentSessionListener());
    }

    @NonNull
    private PaymentSession.PaymentSessionListener createPaymentSessionListener() {
        return new PaymentSession.PaymentSessionListener() {
            @Override
            public void onCommunicatingStateChanged(
                    boolean isCommunicating
            ) {
                // update UI, such as hiding or showing a progress bar
            }

            @Override
            public void onError(
                    int errorCode,
                    @NotNull String errorMessage
            ) {
                // handle error
            }

            @Override
            public void onPaymentSessionDataChanged(
                    @NotNull PaymentSessionData data
            ) {
                data.getPaymentMethod();
            }
        };
    }

    @NonNull
    private PaymentSessionConfig createPaymentSessionConfig() {
        return new PaymentSessionConfig.Builder()
                .build();
    }
    }
