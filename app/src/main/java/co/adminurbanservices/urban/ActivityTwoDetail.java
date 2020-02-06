package co.adminurbanservices.urban;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static co.adminurbanservices.urban.Constants.EXTRA_URLAWWEEE;
import static co.adminurbanservices.urban.Constants.EXTRA_URLBASDA;
import static co.adminurbanservices.urban.Constants.EXTRA_URLBBB;

public class ActivityTwoDetail extends AppCompatActivity {

    public static final String QQQQ = "ideaabb";
    public static final String RRRR = "ideaa";
   // public static final String EXTRA_URLBASDA = "http://192.168.43.202/Android/v1/final_submit.php";
    Button cancel,feedback,needhelp,vitail;
    TextView order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_detail);

        Intent intentxa = getIntent();
        final int intVariableNamea = intentxa.getIntExtra(EXTRA_URLBASDA, 0);
        final String numa = String.valueOf(intVariableNamea);

        order_id = findViewById(R.id.order_idaa);
        order_id.setText(numa);

         Intent intentx = getIntent();
        final String ideaa = intentx.getStringExtra(EXTRA_URLBBB);
       final String ideaabb = intentx.getStringExtra(EXTRA_URLAWWEEE);
     // String zaza = intentx.getStringExtra(EXTRA_URLBASDA);
       // int intVariableName = intentx.getIntExtra(EXTRA_URLBASDA, 0);
      // Toast.makeText(getApplicationContext(), "vlaue is "+intVariableName, Toast.LENGTH_LONG).show();
        TextView texthead = findViewById(R.id.texthead);

       // Button needhelp = findViewById(R.id.needhelp);
        if (ideaabb == null) {
            texthead.setText(ideaa);
        }else {
            texthead.setText(ideaabb);
        }

        Toolbar nmToolbar = findViewById(R.id.toolbar);

        nmToolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        setSupportActionBar(nmToolbar);

        Button feedback = findViewById(R.id.btn_feedback);
        Button needhelp = findViewById(R.id.needhelp);
        vitail = findViewById(R.id.view_detail);
        cancel = findViewById(R.id.cancel);
        buttonData();

        cancel.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        cancelOrder();
                        onBackPressed();

                    }
                }

        );


        vitail.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {

                        String ButtonText = vitail.getText().toString();
                        if(ButtonText.equals("Delete")) {
                            deleteData();
                            onBackPressed();
                            //code for save
                           // vitail.setText("Edit");
                        }
                        else{

                            Intent i = new Intent(v.getContext(),ActivityOneDetailDetail.class);
                            if (ideaabb == null) {
                                i.putExtra(RRRR,ideaa);
                            }else {
                                i.putExtra(QQQQ,ideaabb);
                            }

                            startActivity(i);

                        }
                       // Intent i = new Intent(v.getContext(),ActivityOneDetailDetail.class);
                       // if (ideaabb == null) {
                           // i.putExtra(RRRR,ideaa);
                       // }else {
                           // i.putExtra(QQQQ,ideaabb);
                       // }

                       // startActivity(i);

                    }
                }

        );

        needhelp.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(),ActivityFourDetailB.class);
                        startActivity(i);

                    }
                }

        );

        feedback.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(),Feedback.class);
                        i.putExtra(EXTRA_URLBASDA,intVariableNamea);
                        if (ideaabb == null) {
                           i.putExtra(EXTRA_URLBBB,ideaa);
                        }else {
                            i.putExtra(EXTRA_URLAWWEEE,ideaabb);
                        }
                        startActivity(i);

                    }
                }

        );


        nmToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });


    }

    public void onBackPressed(){
        Intent i = new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    public void cancelOrder(){

        Intent intentx = getIntent();
       int intVariableName = intentx.getIntExtra(EXTRA_URLBASDA, 0);
        final String num = String.valueOf(intVariableName);
      // Toast.makeText(getApplicationContext(), "vlaue is "+num, Toast.LENGTH_LONG).show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_CANCEL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);

                           // String cancel_order = jsonObject.getString("cancel_one");

                           // if (cancel_order == "1"){
                                //cancel.setEnabled(false);

                           // }

                           // Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();

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
                params.put("nnn", num);
               // params.put("email", email);
               // params.put("password", password);
                return params;
            }
        };


        RequestHandle.getInstance(this).addToRequestQueue(stringRequest);


    }



    public void buttonData(){

        Intent intentx = getIntent();
        int intVariableName = intentx.getIntExtra(EXTRA_URLBASDA, 0);
        final String num = String.valueOf(intVariableName);
       //   Toast.makeText(getApplicationContext(), "vlaue is "+num, Toast.LENGTH_LONG).show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_BUTTONHANDLE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            //JSONObject jsonObject = new JSONObject(response);

                             //String cancel_order = jsonObject.getString("cancel_one");

                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            String cancel_order = jsonObject.getString("cancel_one");
                            String delivered = jsonObject.getString("delivered");

                           // firstNameTV.setText(firstName);
                           // lastNameTV.setText(lastName);

                             if (cancel_order == "1"){
                             cancel.setText("Order Canceled");
                                   cancel.setEnabled(false);
                                 vitail.setText("Delete");


                            }
                            if(delivered == "1"){
                                cancel.setText("Order Delivered");
                                cancel.setEnabled(false);
                                 vitail.setText("Delete");
                                //feedback.setVisibility(View.VISIBLE);

                            }else {
                                //feedback.setVisibility(View.GONE);
                            }

                             //Toast.makeText(getApplicationContext(), "Try again"+cancel_order, Toast.LENGTH_SHORT).show();

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
                params.put("nnn", num);
                // params.put("email", email);
                // params.put("password", password);
                return params;
            }
        };


        RequestHandle.getInstance(this).addToRequestQueue(stringRequest);


    }
    public void deleteData(){

        Intent intentx = getIntent();
        int intVariableName = intentx.getIntExtra(EXTRA_URLBASDA, 0);
        final String num = String.valueOf(intVariableName);
        //   Toast.makeText(getApplicationContext(), "vlaue is "+num, Toast.LENGTH_LONG).show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_DELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {


                            JSONObject jsonObject = new JSONObject(response);
                            //Toast.makeText(getApplicationContext(), "Try again"+cancel_order, Toast.LENGTH_SHORT).show();

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
                params.put("nnn", num);
                // params.put("email", email);
                // params.put("password", password);
                return params;
            }
        };


        RequestHandle.getInstance(this).addToRequestQueue(stringRequest);


    }



}
