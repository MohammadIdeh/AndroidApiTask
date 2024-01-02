package com.example.assii2;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class api2 extends AppCompatActivity {
    private RequestQueue queue;
    private ListView lstTodos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api2);
        queue = Volley.newRequestQueue(this);
        lstTodos = findViewById(R.id.lstTodos);
    }

    public void btn_OnClick(View view) {

        String url = "https://jsonplaceholder.typicode.com/posts";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> todos = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        todos.add(obj.getString("title"));
                    }catch(JSONException exception){
                        Log.d("volley_error", exception.toString());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(api2.this,
                        android.R.layout.simple_list_item_1, todos);
                lstTodos.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley_error", error.toString());
            }
        });

        queue.add(request);


    }

    public void back(View view) {
        Intent intent = new Intent(api2.this, api1.class);
        startActivity(intent);
    }

    public void logout(View view) {
        Intent intent = new Intent(api2.this, MainActivity.class);
        startActivity(intent);
    }
}