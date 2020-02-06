package co.adminurbanservices.urban;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import static co.adminurbanservices.urban.Constants.EXTRA_URLAWW;

public class ActivityOneDetailDetailDetailDetail extends AppCompatActivity implements  View.OnClickListener {

   // public static final String EXTRA_URLAWWEEE = "http://192.168.0.4/MyApi/levellevellevel.php";

    Button btnDatePicker, btnTimePicker, timeend;
    private Button submit;
    private ProgressDialog progressDialog;
    TextView txtDate, txtTime, timeeeend;
    SharedPrefManager mInstance;
    private int mYear, mMonth, mDay, mHour, mMinute;
    EditText editname,editmobile,editpincode,editflat,edittown,editcolony,editlandmark,editstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_detail_detail_detail_detail);




        btnDatePicker = findViewById(R.id.btn_date);
        btnTimePicker = findViewById(R.id.btn_time_start);
        timeend = findViewById(R.id.btn_time_end);
        timeeeend = findViewById(R.id.end);
        txtDate = findViewById(R.id.date);
        txtTime = findViewById(R.id.start);
        editname = findViewById(R.id.name);
        editmobile = findViewById(R.id.mobile);
        editpincode = findViewById(R.id.pincode);
        editflat = findViewById(R.id.flat);
        edittown = findViewById(R.id.town);
        editcolony = findViewById(R.id.colony);
        editlandmark = findViewById(R.id.landmark);
        editstate = findViewById(R.id.state);

        submit = findViewById(R.id.submit);

        progressDialog = new ProgressDialog(this);

        submit.setOnClickListener(this);





        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        timeend.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (v == timeend) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            timeeeend.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String mName = editname.getText().toString().trim();
                final String mMobile = editmobile.getText().toString().trim();
                final String mPincode = editpincode.getText().toString().trim();
                final String mTown = edittown.getText().toString().trim();
                final String mColony = editcolony.getText().toString().trim();
                final String mLandmark = editlandmark.getText().toString().trim();
                final String mState = editstate.getText().toString().trim();
                final String mDate = txtDate.getText().toString().trim();
                final String mTime = txtTime.getText().toString().trim();
                final String mEnd = timeeeend.getText().toString().trim();


                if (!mName.isEmpty() && !mMobile.isEmpty() && !mPincode.isEmpty() && !mTown.isEmpty() && !mColony.isEmpty() && !mLandmark.isEmpty() && !mState.isEmpty() && !mDate.isEmpty() && !mTime.isEmpty() && !mEnd.isEmpty()){
                    registerUser();
                }else if (mName.isEmpty()){
                    editname.setError("Please insert Name");
                }else if (mMobile.isEmpty()){
                    editmobile.setError("Please insert Mobile number");
                }else if (mPincode.isEmpty()){
                    editpincode.setError("Please insert Pincode");
                }else if (mTown.isEmpty()){
                    edittown.setError("Please insert City/Town");
                }else if (mColony.isEmpty()){
                    editcolony.setError("Please insert Colony");
                }else if (mLandmark.isEmpty()){
                    editlandmark.setError("Please insert Landmark");
                }else if (mState.isEmpty()){
                    editstate.setError("Please insert State");
                }else if (mDate.isEmpty()){
                    //txtDate.setError("Please select date");
                    Toast.makeText(getApplicationContext(), "Please select date", Toast.LENGTH_SHORT).show();
                }else if (mTime.isEmpty()){
                    //txtTime.setError("Please select start time");
                    Toast.makeText(getApplicationContext(), "Please select Start time", Toast.LENGTH_SHORT).show();
                }else {
                    //timeeeend.setError("Please select end time");
                    Toast.makeText(getApplicationContext(), "Please select end time", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void registerUser() {
        Intent intent = getIntent();
        final String asdf = intent.getStringExtra(EXTRA_URLAWW);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
        final String orddate = sdf.format(c.getTime());
        SimpleDateFormat sdfa = new SimpleDateFormat("hh:mm:ss aaa");
        final String ordtime = sdfa.format(c.getTime());

       // Toast.makeText(getApplicationContext(), "vlaue is "+orddate, Toast.LENGTH_LONG).show();
       // Toast.makeText(getApplicationContext(), "vlaue is "+ordtime, Toast.LENGTH_LONG).show();



        mInstance = new SharedPrefManager(this);
        final String emailuser = mInstance.getUserEmail();

        final String name = editname.getText().toString().trim();
        final String mobile = editmobile.getText().toString().trim();
        final String pincode = editpincode.getText().toString().trim();
        final String addressline1 = editflat.getText().toString().trim();
        final String addressline2 = edittown.getText().toString().trim();
        final String addressline3 = editcolony.getText().toString().trim();
        final String addressline4 = editlandmark.getText().toString().trim();
        final String addressline5 = editstate.getText().toString().trim();
        final String date = txtDate.getText().toString().trim();
        final String start_time = txtTime.getText().toString().trim();
        final String end_time = timeeeend.getText().toString().trim();
      //  final String servicename = editTextPassword.getText().toString().trim();

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.FINAL_ORDER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                           // Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            Intent i = new Intent(ActivityOneDetailDetailDetailDetail.this, ActivityTwoDetail.class);
                            i.putExtra(Constants.EXTRA_URLAWWEEE, asdf);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("mobile_number", mobile);
                params.put("pincode", pincode);
                params.put("addressline1", addressline1);
                params.put("addressline2", addressline2);
                params.put("addressline3", addressline3);
                params.put("addressline4", addressline4);
                params.put("addressline5", addressline5);
                params.put("date", date);
                params.put("start_time", start_time);
                params.put("end_time", end_time);
                params.put("servicename", asdf);
                params.put("email", emailuser);
                params.put("order_date", orddate);
                params.put("order_time", ordtime);
                return params;
            }
        };


        RequestHandle.getInstance(this).addToRequestQueue(stringRequest);


    }

  /*
    public void onBackPressed(){
    Intent i = new Intent(this,ActivityOne.class);
    startActivity(i);
    }
    */
}
