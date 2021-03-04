package sd.rittal.qrsdk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;

import sd.rittal.qrsdk.beans.PurchaseResponse;

public class PurchaseResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_result);


        TextView response_pan = findViewById(R.id.txt_response_pan);
        TextView response_fee = findViewById(R.id.txt_response_fee);
        TextView response_amount = findViewById(R.id.txt_response_amount);
        TextView response_code = findViewById(R.id.txt_response_code);
        TextView response_msg = findViewById(R.id.txt_response_msg);
        Button button_done = findViewById(R.id.dialog_button_done);
        Button button_exit = findViewById(R.id.dialog_button_exit);


        Intent intent = getIntent();
        if (intent != null) {
            String json = intent.getStringExtra("PayJson");
            PurchaseResponse purchaseResponse = new Gson().fromJson(json, PurchaseResponse.class);
            int totalFee = Math.abs(purchaseResponse.getAcqTranFee()) + Math.abs(purchaseResponse.getIssuerTranFee());


            response_pan.setText(purchaseResponse.getPAN());
            Double amount = Double.valueOf(purchaseResponse.getTranAmount());

            response_amount.setText(String.format(getString(R.string.currency), amount));
            response_fee.setText(String.format(getString(R.string.currency), Double.valueOf(totalFee)));

            response_msg.setText(purchaseResponse.getResponseMessageArabic());
            response_code.setText(String.valueOf(purchaseResponse.getResponseCode()));

        }


        button_done.setOnClickListener(view -> share());
        button_exit.setOnClickListener(vv -> {
            finish();
        });


    }

    public void share() {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        LinearLayout linearLayout = findViewById(R.id.layout_main_view);
        Bitmap bitmap = getBitmapFromView(linearLayout);
        try {
            File file = new File(getExternalCacheDir(), "NOORPAY.jpeg");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.putExtra(Intent.EXTRA_TITLE, getString(R.string.qr_purchase));
            intent.setType("image/jpeg");
            startActivity(Intent.createChooser(intent, "Share via"));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }
}
//0002010102115138000306301020002090000000440408881768435204822053039385802SD5906hsgfkd6005Nyala6304AFA5
//00020101021151370003063010200020900000004204077579de35204821153039385802SD5910awab ahmed6008Khartoum630446AD