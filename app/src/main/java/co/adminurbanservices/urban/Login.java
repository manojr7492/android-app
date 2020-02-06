package co.adminurbanservices.urban;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private int backpress;
    private EditText editTextMobile, editTextPassword;
    private Button buttonLogin;
    private TextView forget;
    private ProgressDialog progressDialog;
    int mob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }

        editTextMobile = findViewById(R.id.mobile);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.login);
        TextView buttonRegister = findViewById(R.id.register);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        forget = findViewById(R.id.forgot_password);

        buttonLogin.setOnClickListener(this);
        forget.setOnClickListener(this);
        buttonRegister.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), Signup.class);
                        startActivity(i);
                    }
                }
        );

    }

    private void userLogin(){
        final String mobile = editTextMobile.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                obj.getInt("id"),
                                                obj.getString("mobile"),
                                                obj.getString("email"),
                                                obj.getString("username")
                                        );
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("mobile", mobile);
                params.put("password", password);
                return params;
            }

        };

        RequestHandle.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void userExistanceverify() {



        final String mobile = editTextMobile.getText().toString().trim();


        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.FORGET_MOBILE_VERIFY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            mob = jsonObject.getInt("active_mobile");

                            if(mob == 0){
                                Toast.makeText(getApplicationContext(), "This Mobile Number is not registered", Toast.LENGTH_LONG).show();
                            }else{
                                Intent i = new Intent(Login.this, Forget.class);
                                startActivity(i);
                                finish();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), "Please check your Internet and Try Again", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("mobile", mobile);
                return params;
            }
        };


        RequestHandle.getInstance(this).addToRequestQueue(stringRequest);


    }


    @Override
    public void onClick(View view) {
        final String mobile = editTextMobile.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if(view == buttonLogin){

            if (mobile.length() == 0) {
                editTextMobile.setError("Eneter Mobile Number");
                editTextMobile.requestFocus();
            } else if (mobile.length() != 10) {
                editTextMobile.setError("Enter Correct Mobile Number");
                editTextMobile.requestFocus();
            } else if (password.length() == 0) {
                editTextPassword.setError("Eneter Password");
                editTextPassword.requestFocus();
            }else {
                userLogin();
            }

        }

        if (view == forget){

            if (mobile.length() == 0) {
                editTextMobile.setError("Eneter Mobile Number");
                editTextMobile.requestFocus();
            } else if (mobile.length() != 10) {
                editTextMobile.setError("Enter Correct Mobile Number");
                editTextMobile.requestFocus();
            }else {
                userExistanceverify();
            }
        }
    }


    public void onBackPressed(){

        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();
        if (backpress>1) {

            moveTaskToBack(true);
            Process.killProcess(Process.myPid());
            System.exit(1);
            finish();
        }

    }


}
