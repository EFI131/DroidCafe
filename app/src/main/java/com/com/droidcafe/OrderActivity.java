package com.com.droidcafe;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        String message = getIntent().getStringExtra(MainActivity.EXTRA_ORDER_MESSAGE);
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(message);

    }
}
