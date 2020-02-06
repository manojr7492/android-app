package co.adminurbanservices.urban;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

import static co.adminurbanservices.urban.Constants.ERA_URL;


public class ActivityOneDetailDetailDetail extends AppCompatActivity {

   // public static final String EXTRA_URLAWW = "http://192.168.0.4/MyApi/levellevellevel.php";
    List<ProductOneDetailDetailDetail> productOneDetailList;
    RecyclerView rrecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_detail_detail_detail);

        Toolbar nmToolbar = findViewById(R.id.toolbar);

        nmToolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        setSupportActionBar(nmToolbar);


        nmToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });


        TextView abcd = findViewById(R.id.toptextaa);
        Intent intent = getIntent();
        final String ideaaaa = intent.getStringExtra(ERA_URL);
        abcd.setText(ideaaaa);

        rrecyclerView = findViewById(R.id.rrecyclerdetailb);
        rrecyclerView.setHasFixedSize(true);
        rrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productOneDetailList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProductsfour();


        Button next = findViewById(R.id.button);

        next.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(),ActivityOneDetailDetailDetailDetail.class);
                        i.putExtra(Constants.EXTRA_URLAWW, ideaaaa);
                        startActivity(i);

                    }
                }
        );

    }


    private void loadProductsfour() {
        Intent intent = getIntent();
        final String ide = intent.getStringExtra(ERA_URL);

        //  if (ide != null) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.PRICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                //getting product object from json array
                                JSONObject productfour = jsonArray.getJSONObject(i);

                                //adding the product to product list
                                productOneDetailList.add(new ProductOneDetailDetailDetail(
                                        productfour.getInt("id"),
                                        productfour.getString("time"),
                                        productfour.getString("rate")
                                ));
                            }

                            ProductsOneDetailDetailDetailAdapter adapter = new ProductsOneDetailDetailDetailAdapter(ActivityOneDetailDetailDetail.this, productOneDetailList);
                            rrecyclerView.setAdapter(adapter);
                           // adapter.setOnItemClickListener(ActivityOneDetail.this);
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


}
