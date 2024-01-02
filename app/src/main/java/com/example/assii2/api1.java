package com.example.assii2;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class api1 extends AppCompatActivity {

private TextView txtview ;
private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api1);
   txtview = findViewById(R.id.newfoodtxt);

        queue = Volley.newRequestQueue(this);

    }


    public void click(View view) {
        String url = "https://www.themealdb.com/api/json/v1/1/random.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray mealsArray = response.getJSONArray("meals");

                            if (mealsArray != null && mealsArray.length() > 0) {
                                JSONObject mealObject = mealsArray.getJSONObject(0);
                                String mealName = mealObject.getString("strMeal");
                                txtview.setText(mealName);
                            } else {
                                // Handle the case where there are no meals
                                txtview.setText("No meal found");
                            }


                        } catch (JSONException exception) {
                            Log.d("volley_error", exception.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley_error", error.toString());
            }
        });

        queue.add(request);
    }


    public void nextclick(View view) {
        Intent intent = new Intent(api1.this, api2.class);
        startActivity(intent);
    }


}
