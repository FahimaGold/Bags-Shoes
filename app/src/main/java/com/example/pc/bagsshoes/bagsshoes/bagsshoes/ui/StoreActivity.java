package com.example.pc.bagsshoes.bagsshoes.bagsshoes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;

import android.os.Bundle;

import com.example.pc.bagsshoes.R;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.helpers.AppEphemeralKeyProvider;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.CartViewModel;
import com.example.pc.bagsshoes.bagsshoes.bagsshoes.viewmodel.PurchaseViewModel;
import com.example.pc.bagsshoes.databinding.ActivityCartBinding;
import com.example.pc.bagsshoes.databinding.ActivityStoreBinding;
import com.stripe.android.CustomerSession;
import com.stripe.android.PaymentSessionConfig;
import com.stripe.android.model.Address;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.model.ShippingInformation;
import com.stripe.android.model.ShippingMethod;
import com.stripe.android.view.ShippingInfoWidget;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

@AndroidEntryPoint
public class StoreActivity extends AppCompatActivity {

    private ActivityStoreBinding binding;
    private PurchaseViewModel purchaseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityStoreBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );
        purchaseViewModel = new ViewModelProvider(this).get( PurchaseViewModel.class);
        CustomerSession.initCustomerSession(
                this,
                new AppEphemeralKeyProvider(purchaseViewModel)
        );
    }

    @NonNull
    private PaymentSessionConfig createPaymentSessionConfig() {
        return new PaymentSessionConfig.Builder()

                // hide the phone field on the shipping information form
                .setHiddenShippingInfoFields(
                        ShippingInfoWidget.CustomizableShippingField.valueOf( "PHONE_FIELD" )
                )

                // make the address line 2 field optional
                .setOptionalShippingInfoFields(
                        ShippingInfoWidget.CustomizableShippingField.valueOf( "ADDRESS_LINE_TWO_FIELD" )
                )

                // specify an address to pre-populate the shipping information form
                .setPrepopulatedShippingInfo( new ShippingInformation(
                        new Address.Builder()
                                .setLine1("123 Market St")
                                .setCity("San Francisco")
                                .setState("CA")
                                .setPostalCode("94107")
                                .setCountry("US")
                                .build(),
                        "Jenny Rosen",
                        "4158675309"
                ) )

                // collect shipping information
                .setShippingInfoRequired(true)

                // collect shipping method
                .setShippingMethodsRequired(true)

                // specify the payment method types that the customer can use;
                // defaults to PaymentMethod.Type.Card
                .setPaymentMethodTypes(
                        Arrays.asList( PaymentMethod.Type.Card)
                )

                // only allow US and Canada shipping addresses
                .setAllowedShippingCountryCodes(new HashSet<>(
                        Arrays.asList("US", "CA")
                ))

                // specify a layout to display under the payment collection form
                .setAddPaymentMethodFooter( R.layout.add_payment_method_footer)

                // specify the shipping information validation delegate
                .setShippingInformationValidator(new AppShippingInformationValidator())

                // specify the shipping methods factory delegate
                .setShippingMethodsFactory(new AppShippingMethodsFactory())

                // if `true`, will show "Google Pay" as an option on the
                // Payment Methods selection screen
                .setShouldShowGooglePay(true)

                .build();
    }

    private static class AppShippingInformationValidator
            implements PaymentSessionConfig.ShippingInformationValidator {

        @Override
        public boolean isValid(
                @NonNull ShippingInformation shippingInformation
        ) {
            final Address address = shippingInformation.getAddress();
            return address != null && Locale.US.getCountry() == address.getCountry();
        }

        @NonNull
        public String getErrorMessage(
                @NonNull ShippingInformation shippingInformation
        ) {
            return "A US address is required";
        }
    }

    private static class AppShippingMethodsFactory
            implements PaymentSessionConfig.ShippingMethodsFactory {

        @Override
        public List<ShippingMethod> create(
                @NonNull ShippingInformation shippingInformation
        ) {
            return Arrays.asList(
                    new ShippingMethod(
                            "UPS Ground",
                            "ups-ground",
                            0,
                            "USD",
                            "Arrives in 3-5 days"
                    ),
                    new ShippingMethod(
                            "FedEx",
                            "fedex",
                            599,
                            "USD",
                            "Arrives tomorrow"
                    )
            );
        }
    }
}