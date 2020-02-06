package co.adminurbanservices.urban;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static co.adminurbanservices.urban.ActivityFourDetail.EXTRAAAA_URL;

public class ActivityFourDetailDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_detail_detail);

        Intent intent = getIntent();
        final String ide = intent.getStringExtra(EXTRAAAA_URL);
        TextView abcd = findViewById(R.id.ttt);
        abcd.setText(ide);
    }
}
