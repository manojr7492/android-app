package co.adminurbanservices.urban;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String MOBILE = "mobile";
    public static final String PASSWORD = "password";



    private EditText editTextUsername, editTextEmail, editTextPassword, editTextMobile, editTextCnfPassword;
    private Button buttonRegisterMain;
    private static CheckBox terms_conditions;
    int mob,eml;
    private ProgressDialog progressDialog;



    private TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        editTextEmail = findViewById(R.id.email);
        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        editTextMobile = findViewById(R.id.mobileNumber);
        editTextCnfPassword = findViewById(R.id.cnfpassword);
        progressDialog = new ProgressDialog(this);

        textViewLogin = findViewById(R.id.textViewLogin);

        buttonRegisterMain = findViewById(R.id.signup);
        terms_conditions = findViewById(R.id.terms_conditions);


        buttonRegisterMain.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);


        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            terms_conditions.setTextColor(csl);
        } catch (Exception ignored) {
        }
    }


    private void registerUserverified() {



        final String mobile = editTextMobile.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();

        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.USER_VERIFICATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            mob = jsonObject.getInt("active_mobile");
                            eml = jsonObject.getInt("active_email");

                            if(mob == 1 && eml == 1){
                                Toast.makeText(getApplicationContext(), "Both Mobile Number and Email ID are already registered", Toast.LENGTH_LONG).show();
                           }else if(mob == 1 && eml == 0){
                                Toast.makeText(getApplicationContext(), "This Mobile Number is already registered", Toast.LENGTH_LONG).show();
                            }else if(mob == 0 && eml == 1){
                                Toast.makeText(getApplicationContext(), "This Email ID is already registered", Toast.LENGTH_LONG).show();
                            }else{
                                final String email = editTextEmail.getText().toString().trim();
                                final String username = editTextUsername.getText().toString().trim();
                                final String password = editTextPassword.getText().toString().trim();
                                final String mobile = editTextMobile.getText().toString().trim();

                                Intent detaildetailIntent = new Intent(Signup.this, VerificationActivity.class);
                                detaildetailIntent.putExtra(NAME, username);
                                detaildetailIntent.putExtra(EMAIL, email);
                                detaildetailIntent.putExtra(MOBILE, mobile);
                                detaildetailIntent.putExtra(PASSWORD, password);
                                startActivity(detaildetailIntent);
                                finish();


                            }






                            //Toast.makeText(getApplicationContext(), "value is "+aaa, Toast.LENGTH_LONG).show();

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
                params.put("email", email);
                return params;
            }
        };


        RequestHandle.getInstance(this).addToRequestQueue(stringRequest);


    }

    @Override
    public void onClick(View view) {

        final String email = editTextEmail.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String mobile = editTextMobile.getText().toString().trim();
        final String cnfmpass = editTextCnfPassword.getText().toString().trim();

        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(email);
        if (view == buttonRegisterMain) {
            if (username.length() == 0) {
                editTextUsername.setError("Eneter Your Full Name");
                editTextUsername.requestFocus();
            } else if (email.length() == 0) {
                editTextEmail.setError("Eneter Your Email");
                editTextEmail.requestFocus();
            } else if (!m.find()) {
                editTextEmail.setError("Eneter Correct Email");
                editTextEmail.requestFocus();
            } else if (mobile.length() == 0) {
                editTextMobile.setError("Eneter Your Mobile Number");
                editTextMobile.requestFocus();
            } else if (mobile.length() != 10) {
                editTextMobile.setError("Enter Correct Mobile Number");
                editTextMobile.requestFocus();
            } else if (password.length() == 0) {
                editTextPassword.setError("Eneter Password");
                editTextPassword.requestFocus();
            } else if (password.length() < 6) {
                editTextPassword.setError("Eneter Atleast 6 digit Password");
                editTextPassword.requestFocus();
            } else if (cnfmpass.length() == 0) {
                editTextCnfPassword.setError("Enter Confirm Password");
                editTextCnfPassword.requestFocus();
            } else if (!cnfmpass.equals(password)) {
                editTextCnfPassword.setError("Not Matched! Enter Same Password");
                editTextCnfPassword.requestFocus();
            } else if (!terms_conditions.isChecked()) {
                Toast.makeText(getApplicationContext(), "Accept Term & Conditions", Toast.LENGTH_LONG).show();
            }else {
                registerUserverified();
            }
        }

       if(view == textViewLogin)
           startActivity(new Intent(this, Login.class));

    }

    public void onBackPressed(){

        Intent i = new Intent(this,Login.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();


    }
}
