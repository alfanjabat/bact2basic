package com.ppob.client.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RequestProductListActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_product_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_request_product_list, menu);
        return true;
    }
}
