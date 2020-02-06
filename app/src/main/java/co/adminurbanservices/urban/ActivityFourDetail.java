package co.adminurbanservices.urban;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
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
import java.util.List;

public class ActivityFourDetail extends AppCompatActivity implements ProductsFourDetailAdapter.OnItemClickListener {


    public static final String EXTRAAAA_URL = "servicename";
    //  public static final String EXTRAA_URLB = "image";


    List<ProductFourDetail> productFourDetailList;
    RecyclerView rrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_detail);


        Toolbar nmToolbar = findViewById(R.id.toolbar);

        nmToolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        setSupportActionBar(nmToolbar);


        nmToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });


        rrecyclerView = findViewById(R.id.rrrecylcerView);
        rrecyclerView.setHasFixedSize(true);
        rrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        productFourDetailList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview

        loadProductsfour();
    }

    private void loadProductsfour() {
        // Intent intent = getIntent();
        // final String ide = intent.getStringExtra(URL_PRODUCTSFOUR);

        //  if (ide != null) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.ABOUT_METROSERVICES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                //getting product object from json array
                                JSONObject productfour = jsonArray.getJSONObject(i);

                                //adding the product to product list
                                productFourDetailList.add(new ProductFourDetail(
                                        productfour.getInt("id"),
                                        productfour.getString("servicename")
                                ));
                            }

                            ProductsFourDetailAdapter adapter = new ProductsFourDetailAdapter(ActivityFourDetail.this, productFourDetailList);
                            rrecyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(ActivityFourDetail.this);
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
          /*  @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nnn", ide);
                return params;
            }*/
        };
        Volley.newRequestQueue(this).add(stringRequest);
        // }

    }


    @Override
    public void onItemClick(int position) {
        ProductFourDetail clickedItem = productFourDetailList.get(position);


        switch (position) {
            case 0:
                Intent detailIntent = new Intent(this, ActivityFourDetailDetail.class);
                detailIntent.putExtra(EXTRAAAA_URL, clickedItem.getServicename());
                startActivity(detailIntent);
                break;
            case 1:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http://codepath.com");
                startActivity(Intent.createChooser(shareIntent, "Share link using"));
                break;
            case 2:
                Intent detailaIntent = new Intent(this, ActivityFourDetailDetail.class);
                 detailaIntent.putExtra(EXTRAAAA_URL, clickedItem.getServicename());
                startActivity(detailaIntent);
                break;
            case 3:

                Intent detailxIntent = new Intent(this, ActivityFourDetailDetail.class);
                detailxIntent.putExtra(EXTRAAAA_URL, clickedItem.getServicename());
               // detailxIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(detailxIntent);
                break;

        }




    }
    public void onBackPressed(){
        Intent i = new Intent(this,ActivityFour.class);
        //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}
