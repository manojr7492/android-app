package co.adminurbanservices.urban;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static co.adminurbanservices.urban.Constants.EXTRA_URLAWWEEE;
import static co.adminurbanservices.urban.Constants.EXTRA_URLBASDA;
import static co.adminurbanservices.urban.Constants.EXTRA_URLBBB;

public class Feedback extends AppCompatActivity {
    Spinner s1;
    SharedPrefManager mInstance;
    //Button btn_feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        Toolbar nmToolbar = findViewById(R.id.toolbar);

        nmToolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        setSupportActionBar(nmToolbar);

        s1 = findViewById(R.id.spinner1);

        mInstance = new SharedPrefManager(this);
        final String email = mInstance.getUserEmail();
        final String name = mInstance.getUsername();

        Intent intentx = getIntent();
        int intVariableName = intentx.getIntExtra(EXTRA_URLBASDA, 0);
        final String num = String.valueOf(intVariableName);
        final String servicenameb = intentx.getStringExtra(EXTRA_URLAWWEEE);
        final String servicenamea = intentx.getStringExtra(EXTRA_URLBBB);


        Toast.makeText(getApplicationContext(), "vlaue is "+num, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "vlaue is "+email, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "vlaue is "+name, Toast.LENGTH_LONG).show();

        if (servicenamea == null){
            Toast.makeText(getApplicationContext(), "vlaue is "+servicenameb, Toast.LENGTH_LONG).show();
        }else if (servicenameb == null){
            Toast.makeText(getApplicationContext(), "vlaue is "+servicenamea, Toast.LENGTH_LONG).show();
        }

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
        final String orddate = sdf.format(c.getTime());
        SimpleDateFormat sdfa = new SimpleDateFormat("hh:mm:ss aaa");
        final String ordtime = sdfa.format(c.getTime());

        Toast.makeText(getApplicationContext(), "vlaue is "+orddate, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "vlaue is "+ordtime, Toast.LENGTH_LONG).show();

       // s1.setOnItemSelectedListener(this);


        nmToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

    }

}
