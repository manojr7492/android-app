package co.adminurbanservices.urban;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.adminurbanservices.urban.Constants.URL_PRODUCTS;

public class ActivityOneDetail extends AppCompatActivity implements ProductsOneDetailAdapter.OnItemClickListener {


    public static final String EXTRAA_URL = "servicename";
    public static final String EXTRAA_URLB = "image";


    List<ProductOneDetail> productOneDetailList;
    RecyclerView rrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_detail);

        rrecyclerView = findViewById(R.id.rrrecylcerView);
        rrecyclerView.setHasFixedSize(true);
        rrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        productOneDetailList = new ArrayList<>();
      //  Intent i = new Intent(ActivityOneDetail.this, ActivityOneDetailDetail.class);
       // i.putExtra("key",EXTRAA_URL);
       // startActivity(i);
       // Intent x = new Intent(ActivityOneDetail.this, ActivityOneDetailDetailDetailDetail.class);
       // i.putExtra("keya",EXTRAA_URL);
        //startActivity(x);

        //this method will fetch and parse json
        //to display it in recyclerview

        loadProductsfour();
    }

    private void loadProductsfour() {
        Intent intent = getIntent();
        final String ide = intent.getStringExtra(URL_PRODUCTS);

      //  if (ide != null) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.EXTRA_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    //getting product object from json array
                                    JSONObject productfour = jsonArray.getJSONObject(i);

                                    //adding the product to product list
                                    productOneDetailList.add(new ProductOneDetail(
                                            productfour.getInt("id"),
                                            productfour.getString("servicename"),
                                            productfour.getString("image")
                                    ));
                                }

                                ProductsOneDetailAdapter adapter = new ProductsOneDetailAdapter(ActivityOneDetail.this, productOneDetailList);
                                rrecyclerView.setAdapter(adapter);
                                adapter.setOnItemClickListener(ActivityOneDetail.this);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("nnn", ide);
                    return params;
                }
            };
            Volley.newRequestQueue(this).add(stringRequest);
       // }

    }


    @Override
    public void onItemClick(int position) {
        Intent detaildetailIntent = new Intent(this, ActivityOneDetailDetail.class);
        ProductOneDetail clickedItem = productOneDetailList.get(position);
        detaildetailIntent.putExtra(EXTRAA_URL, clickedItem.getServicename());
        detaildetailIntent.putExtra(EXTRAA_URLB, clickedItem.getImage());
        startActivity(detaildetailIntent);
    }


    public void onBackPressed(){
    Intent i = new Intent(this,ActivityOne.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(i);
    }

}
