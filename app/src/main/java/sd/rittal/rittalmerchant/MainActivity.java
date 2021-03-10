package sd.rittal.rittalmerchant;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import sd.rittal.qrsdk.uitl.RittalService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          RittalService.qrPurchase(getApplicationContext(),"192.168.1.1");

    }
}