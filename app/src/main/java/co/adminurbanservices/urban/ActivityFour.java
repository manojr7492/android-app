package co.adminurbanservices.urban;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
public class ActivityFour extends AppCompatActivity implements ProductsAdapterfour.OnItemClickListener {

    public Context abb;
    TextView name,email,phone;
    SharedPrefManager mInstance;
    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
   // public static final String URL_PRODUCTSFOUR = "http://192.168.0.4/MyApi/Profilelist.php";

    //a list to store all the products
    List<Productfour> productList;

    //the recyclerview
    RecyclerView recyclerView;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        mInstance = new SharedPrefManager(this);


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        String mmmname = mInstance.getUsername();
        String mmmemail = mInstance.getUserEmail();
        String mmmphone = mInstance.getMobile();

        name.setText(mmmname);
        email.setText(mmmemail);
        phone.setText(mmmphone);

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerViewfour);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar nmToolbar = findViewById(R.id.toolbar);

       // nmToolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        nmToolbar.setTitle("My Profile");

        setSupportActionBar(nmToolbar);

        //name = findViewById(R.id.name);
        //email = findViewById(R.id.email);
       // phone = findViewById(R.id.phone);

        //initializing the productlist
        productList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProductsfour();



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:
                        Intent intent0 = new Intent(ActivityFour.this, MainActivity.class);
                        intent0.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent0);
                        finish();
                        break;

                    case R.id.ic_android:
                        Intent intent1 = new Intent(ActivityFour.this, ActivityOne.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                        finish();
                        break;

                    case R.id.ic_books:
                        Intent intent2 = new Intent(ActivityFour.this, ActivityTwo.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent2);
                        finish();
                        break;
/*
                    case R.id.ic_center_focus:
                        Intent intent3 = new Intent(ActivityFour.this, ActivityThree.class);
                        startActivity(intent3);
                        break;
*/
                    case R.id.ic_backup:
                        break;
                }


                return false;
            }
        });

        Button logout = findViewById(R.id.button);
        logout.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        SharedPrefManager.getInstance(abb).logout();
                        Intent i = new Intent(v.getContext(),Login.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();

                    }
                }
        );

    }
    private void loadProductsfour() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_PRODUCTSFOUR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Productfour(
                                        product.getInt("id"),
                                        product.getString("service"),
                                        product.getString("image")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            ProductsAdapterfour adapter = new ProductsAdapterfour(ActivityFour.this, productList);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(ActivityFour.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
  /*
    public void onBackPressed(){
    Intent i = new Intent(this,MainActivity.class);
    startActivity(i);
    }
    */


    @Override
    public void onItemClick(int position) {
      //  Productfour clickedItem = productList.get(position);


        switch(position)
        {
            case 0:

                Intent detailIntent = new Intent(this, ActivityFourDetailB.class);
               // detailIntent.putExtra(URL_PRODUCTSFOUR, clickedItem.getShortdesc());
               // detailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               // detailIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(detailIntent);
                break;
            case 1:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http://codepath.com");
                startActivity(Intent.createChooser(shareIntent, "Share link using"));
                break;
            case 2:
                Intent detailaIntent = new Intent(this, ActivityFourDetail.class);
              //  detailaIntent.putExtra(URL_PRODUCTSFOUR, clickedItem.getShortdesc());
                startActivity(detailaIntent);
                break;
            case 3:

                Intent sharebIntent = new Intent(Intent.ACTION_SEND);
                sharebIntent.setType("text/plain");
                sharebIntent.putExtra(Intent.EXTRA_TEXT, "http://codepath.com");
                startActivity(Intent.createChooser(sharebIntent, "Share link using"));
                break;
            case 4:

                Intent shareaIntent = new Intent(Intent.ACTION_SEND);
                shareaIntent.setType("text/plain");
                shareaIntent.putExtra(Intent.EXTRA_TEXT, "http://codepath.com");
                startActivity(Intent.createChooser(shareaIntent, "Share link using"));
                break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onBackPressed(){
        Intent i = new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
}
