package com.rnfsoft.volleynetworking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = Volley.newRequestQueue(this);

        Button btIPAddress = (Button)findViewById(R.id.btIPAddress);
        btIPAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchJsonResponse();
            }
        });


    }

    private void fetchJsonResponse() {
        JsonObjectRequest req = new JsonObjectRequest("http://httpbin.org/ip", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String result = "Your IP Address is " + response.getString("origin");
                            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });


        mRequestQueue.add(req);

    }
}
