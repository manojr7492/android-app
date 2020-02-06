package co.adminurbanservices.urban;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

public class ActivityTwo extends AppCompatActivity implements ProductsAdaptertwo.OnItemClickListener {


    List<Producttwo> productListfour;
    RecyclerView rrecyclerView;
    SharedPrefManager mInstance;



  //  public static final String EXTRA_URL = "http://192.168.0.4/MyApi/order_list.php";
    //public static final String EXTRA_URLBBB = "http://192.168.0.4/MyApi/order_list.php";
    //public static final String EXTRA_URLBASDA = "http://192.168.0.4/Android/v1/final_submit.php";
   // private SectionsPageAdapter mSectionsPageAdapter;

    //private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        rrecyclerView = findViewById(R.id.recyclerdetail);
        rrecyclerView.setHasFixedSize(true);
        rrecyclerView.setLayoutManager(new LinearLayoutManager(this));


        Toolbar nmToolbar = findViewById(R.id.toolbar);

        //  nmToolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        nmToolbar.setTitle("My Booking");

        setSupportActionBar(nmToolbar);



        // Intent intent = getIntent();
        // String ide = intent.getStringExtra(URL_PRODUCTS);

        //initializing the productlist
        productListfour = new ArrayList<>();




        //this method will fetch and parse json
        //to display it in recyclerview

        loadProductsfour();




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:
                        Intent intent0 = new Intent(ActivityTwo.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_android:
                        Intent intent1 = new Intent(ActivityTwo.this, ActivityOne.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_books:

                        break;

              /*      case R.id.ic_center_focus:
                        Intent intent3 = new Intent(ActivityTwo.this, ActivityThree.class);
                        startActivity(intent3);
                        break;
*/
                    case R.id.ic_backup:
                        Intent intent4 = new Intent(ActivityTwo.this, ActivityFour.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }
        });
    }

  /*
    public void onBackPressed(){
    Intent i = new Intent(this,MainActivity.class);
    startActivity(i);
    }
    */

    private void loadProductsfour() {

        mInstance = new SharedPrefManager( this);
        final String ide = mInstance.getUserEmail();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.EXTRA_URLSS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                //getting product object from json array
                                JSONObject productfour = jsonArray.getJSONObject(i);

                                //adding the product to product list
                                productListfour.add(new Producttwo(
                                        productfour.getInt("id"),
                                        productfour.getString("servicename"),
                                        productfour.getString("start_time"),
                                        productfour.getString("end_time"),
                                        productfour.getString("date")

                                ));
                            }

                            ProductsAdaptertwo adapter = new ProductsAdaptertwo(ActivityTwo.this, productListfour);
                            rrecyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(ActivityTwo.this);
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nnn", ide);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, ActivityTwoDetail.class);
        Producttwo clickedItem = productListfour.get(position);
        detailIntent.putExtra(Constants.EXTRA_URLBBB, clickedItem.getServicename());
        detailIntent.putExtra(Constants.EXTRA_URLBASDA, clickedItem.getId());
        startActivity(detailIntent);
    }


    public void onBackPressed(){
        Intent i = new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
}
