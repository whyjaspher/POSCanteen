package com.example.poscanteen;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CustomDialogue extends Dialog {

    private Context context;
    private TextView quantityValue;
    private int quantity = 1; // Default quantity
    private boolean toastShown = false;
    Button editButton;
    // Update the constructor to accept productId
    public CustomDialogue(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customize_order);

        LinearLayout addsOnContainer = findViewById(R.id.addsOnContainer);
        LinearLayout sizesContainer = findViewById(R.id.sizesContainer);
        Button confirmButton = findViewById(R.id.button_confirm);
        Button cancelButton = findViewById(R.id.button_cancel);
        Button decreaseQuantity = findViewById(R.id.decrease_quantity);
        Button increaseQuantity = findViewById(R.id.increase_quantity);
        quantityValue = findViewById(R.id.quantity_value);
        editButton = findViewById(R.id.button_edit);

        // Update the quantity display
        quantityValue.setText(String.valueOf(quantity));

        // Set click listeners for quantity buttons
        decreaseQuantity.setOnClickListener(v -> {
            if (quantity > 1) {
                // Decrease quantity and update display
                quantity--;
                quantityValue.setText(String.valueOf(quantity));
                // Reset the toast flag because quantity is now > 1
                toastShown = false;
            } else if (!toastShown) {
                // Show the toast only if it hasn't been shown already
                Toast.makeText(context, "Quantity cannot be less than 1", Toast.LENGTH_SHORT).show();
                // Set the flag to true to prevent future toasts
                toastShown = true;
            }
        });


        increaseQuantity.setOnClickListener(v -> {
            quantity++;
            quantityValue.setText(String.valueOf(quantity));
        });

        fetchProductDetails(addsOnContainer, sizesContainer);

        confirmButton.setOnClickListener(v -> dismiss());
        cancelButton.setOnClickListener(v -> dismiss());

        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditProductActivity.class);
            context.startActivity(intent);
        });

    }

    private void fetchProductDetails(LinearLayout addsOnContainer, LinearLayout sizesContainer) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Fetching product details logic here (if needed)
    }
}
