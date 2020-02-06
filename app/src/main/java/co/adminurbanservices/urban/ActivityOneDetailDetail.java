package co.adminurbanservices.urban;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static co.adminurbanservices.urban.ActivityOneDetail.EXTRAA_URL;
import static co.adminurbanservices.urban.ActivityOneDetail.EXTRAA_URLB;
import static co.adminurbanservices.urban.MainActivity.MMMMM;
import static co.adminurbanservices.urban.MainActivity.MMMMMN;
import static co.adminurbanservices.urban.ActivityTwoDetail.RRRR;
import static co.adminurbanservices.urban.ActivityTwoDetail.QQQQ;
public class ActivityOneDetailDetail extends AppCompatActivity {

    List<ProductOneDetailDetaila> productListfour;
    List<ProductOneDetailDetailb> productListfourb;
    RecyclerView rrecyclerView;
    RecyclerView rrecyclerViewb;
    Button next;
   // Intent i;

   // public static final String EXTRA_URL = "http://192.168.0.4/MyApi/levellevellevel.php";
   // public static final String ERA_URL = "http://192.168.0.4/MyApi/multirecycler.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_detail_detail);
        rrecyclerView = findViewById(R.id.recyclerdetail);
        rrecyclerView.setHasFixedSize(true);
        rrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        rrecyclerViewb = findViewById(R.id.rrecyclerdetail);
        rrecyclerViewb.setHasFixedSize(true);
        rrecyclerViewb.setLayoutManager(new LinearLayoutManager(this));

        final Intent aa = getIntent();
        final String idea = aa.getStringExtra(EXTRAA_URL);

        next = findViewById(R.id.button);

        next.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(),ActivityOneDetailDetailDetail.class);
                        i.putExtra(Constants.ERA_URL, idea);
                        startActivity(i);

                    }
                }
        );

        productListfour = new ArrayList<>();
        productListfourb = new ArrayList<>();



        //this method will fetch and parse json
        //to display it in recyclerview

        loadProductsfour();
        loadProductsfourb();

        Intent intent = getIntent();
       // String idea = intent.getStringExtra(EXTRAA_URL);
        String imageUrl = intent.getStringExtra(EXTRAA_URLB);
        String axsad = intent.getStringExtra(MMMMMN);

        ImageView imageView = findViewById(R.id.imageView2);

        final Intent intentx = getIntent();
        final String ideaa = intentx.getStringExtra(EXTRAA_URL);
       // final Intent in = getIntent();
       // final String azaza = in.getStringExtra(MMMMM);


        if(ideaa == null){
            Glide.with(this).load(imageUrl).into(imageView);
        }else {
            Glide.with(this).load(axsad).into(imageView);
        }





    }


    private void loadProductsfour() {
        final Intent intentx = getIntent();
        final String ideaa = intentx.getStringExtra(EXTRAA_URL);
        final Intent in = getIntent();
        final String azaza = in.getStringExtra(MMMMM);
        final String ide;
        final Intent abaas = getIntent();
        final String ddda = abaas.getStringExtra(RRRR);
        final Intent abaasaaa = getIntent();
        final String dddaaa = abaasaaa.getStringExtra(QQQQ);

        if(ideaa != null){
            ide = ideaa;
        }else if (azaza != null){
            ide = azaza;
        }else if (ddda != null){
            ide = ddda;
        }else{
            ide = dddaaa;
        }
       // intent.putExtra(ERA_URL,ide);
       // startActivity(intent);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.EXTRA_URLA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                //getting product object from json array
                                JSONObject productfour = jsonArray.getJSONObject(i);

                                //adding the product to product list
                                productListfour.add(new ProductOneDetailDetaila(
                                        productfour.getInt("id"),
                                        productfour.getString("servicedetail")

                                ));

                             //   Toast.makeText(getApplicationContext(), "vlaue is "+i, Toast.LENGTH_LONG).show();
                            }

                            ProductsOneDetailDetailAdapterfoura adapter = new ProductsOneDetailDetailAdapterfoura(ActivityOneDetailDetail.this, productListfour);
                            rrecyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                       // Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
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
    }

    private void loadProductsfourb() {
        final Intent intentx = getIntent();
        final String ideaa = intentx.getStringExtra(EXTRAA_URL);
        final Intent in = getIntent();
        final String azaza = in.getStringExtra(MMMMM);
        final String ide;
        final Intent abaas = getIntent();
        final String ddda = abaas.getStringExtra(RRRR);
        final Intent abaasaaa = getIntent();
        final String dddaaa = abaasaaa.getStringExtra(QQQQ);

        if(ideaa != null){
            ide = ideaa;
        }else if (azaza != null){
            ide = azaza;
        }else if (ddda != null){
            ide = ddda;
        }else{
            ide = dddaaa;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.ERA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                //getting product object from json array
                                JSONObject productfour = jsonArray.getJSONObject(i);

                                //adding the product to product list
                                productListfourb.add(new ProductOneDetailDetailb(
                                        productfour.getInt("id"),
                                        productfour.getString("message"),
                                        productfour.getString("username"),
                                        productfour.getString("image"),
                                        productfour.getString("ratea"),
                                        productfour.getString("datea")

                                ));

                               // Toast.makeText(getApplicationContext(), "vlaue is "+i, Toast.LENGTH_LONG).show();
                            }

                            ProductsOneDetailDetailAdapterfourb adapter = new ProductsOneDetailDetailAdapterfourb(ActivityOneDetailDetail.this, productListfourb);
                            rrecyclerViewb.setAdapter(adapter);

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
    }


  /*
    public void onBackPressed(){
    Intent i = new Intent(this,ActivityOne.class);
    startActivity(i);
    }
    */


}
