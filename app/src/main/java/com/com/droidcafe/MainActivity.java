package com.com.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_ORDER_MESSAGE = "com.com.droidcafe.extra.EXTRA_ORDER_MESSAGE";

    private String mOrderMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState != null && savedInstanceState.containsKey(EXTRA_ORDER_MESSAGE)){
            mOrderMessage = savedInstanceState.getString(EXTRA_ORDER_MESSAGE);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check weather a dessert was picked
                if (mOrderMessage == null) {
                    displayToast(getString(R.string.no_dessert_was_picked));
                    return;
                }

                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra(EXTRA_ORDER_MESSAGE, mOrderMessage);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mOrderMessage != null) {
            outState.putString(EXTRA_ORDER_MESSAGE, mOrderMessage);
        }
    }

    public void showOrder(View view) {
        switch (view.getId()) {
            case R.id.donut:
                mOrderMessage = getString(R.string.donut_order_message);
                displayToast(mOrderMessage);
                break;
            case R.id.froyo:
                mOrderMessage = getString(R.string.froyo_order_message);
                displayToast(mOrderMessage);
                break;
            case R.id.ice_cream:
                mOrderMessage = getString(R.string.ice_cream_order_message);
                displayToast(mOrderMessage);
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
