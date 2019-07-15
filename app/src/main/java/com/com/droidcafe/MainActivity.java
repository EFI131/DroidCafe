package com.com.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
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

        if (savedInstanceState != null && savedInstanceState.containsKey(EXTRA_ORDER_MESSAGE)) {
            mOrderMessage = savedInstanceState.getString(EXTRA_ORDER_MESSAGE);
        }


        // setting context menu
        TextView textView = findViewById(R.id.textView);
        registerForContextMenu(textView);

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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_order:
                //displayToast(getString(R.string.action_order_message));
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra(EXTRA_ORDER_MESSAGE, mOrderMessage);
                startActivity(intent);
                return true;

            case R.id.action_status:
                displayToast(getString(R.string.action_status_message));
                return true;

            case R.id.action_favorites:
                displayToast(getString(R.string.action_favorites_message));
                return true;

            case R.id.action_contact:
                displayToast(getString(R.string.action_contact_message));
                return true;

            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.context_contact:
                displayToast("contact");
                return true;
            case R.id.context_order:
                displayToast("order");
                return true;
            case R.id.context_favorites:
                displayToast("favorites");
                return true;
            case R.id.context_status:
                displayToast("status");
                return true;
        }

        return super.onContextItemSelected(item);
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
