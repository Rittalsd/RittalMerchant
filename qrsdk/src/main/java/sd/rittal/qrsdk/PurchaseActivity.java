package sd.rittal.qrsdk;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sd.rittal.qrsdk.api.ApiBuilder;
import sd.rittal.qrsdk.beans.PublicKeyResponse;
import sd.rittal.qrsdk.beans.Purchase;
import sd.rittal.qrsdk.beans.PurchaseResponse;
import sd.rittal.qrsdk.beans.UserCard;
import sd.rittal.qrsdk.beans.UserCardsOnClickListener;
import sd.rittal.qrsdk.constant.AppConstant;
import sd.rittal.qrsdk.constant.RSAEncrypt;
import sd.rittal.qrsdk.uitl.DateSplite;
import sd.rittal.qrsdk.uitl.MerchantQrUtil;
import sd.rittal.qrsdk.uitl.MonthYearPickerFragment;
import sd.rittal.qrsdk.uitl.ProgressATM;

public class PurchaseActivity extends AppCompatActivity implements UserCardsOnClickListener {

    ProgressATM progressBar;
    LinearLayout qrFailedLayout;
    ScrollView paymentLayout;
    Button buttonReReadQr, buttonSubmit;

    EditText editTextPanNumber, editTextExpireDate,
            editTextAmount, editTextIpin;

    ImageView imageView_cards;

    String merchantId, qrCode;
    TextView merchant_name, merchant_city;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);


        progressBar = new ProgressATM(PurchaseActivity.this);

        qrFailedLayout = findViewById(R.id.qr_failed_layout);
        paymentLayout = findViewById(R.id.payment_layout);

        buttonReReadQr = findViewById(R.id.button_re_read_qr);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        editTextPanNumber = findViewById(R.id.editText_pan_number);
        editTextExpireDate = findViewById(R.id.editText_expire_date);
        editTextAmount = findViewById(R.id.editText_amount);
        editTextIpin = findViewById(R.id.editText_ipin);

        merchant_name = findViewById(R.id.merchant_name);
        merchant_city = findViewById(R.id.merchant_city);

        imageView_cards = findViewById(R.id.imageView_cards);

        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");

        }

        handelUI();

        new IntentIntegrator(this).setPrompt("أمسح وأدفع").initiateScan();

    }

    private void handelUI() {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("strList", "");
        Log.wtf("strList", json);

        buttonReReadQr.setOnClickListener(v -> {
            startQr();
        });


        buttonSubmit.setOnClickListener(vv -> {

            if (checkFields()) {
                getKey();
            }
        });

        imageView_cards.setOnClickListener(imV -> {

            showCardDal();

        });


        editTextExpireDate.setOnClickListener(vvv -> {
            showExpireDateDialogFragment();
        });


    }

    private void showCardDal() {
        FragmentManager fm = getSupportFragmentManager();
        UserCardsDialogFragment alertDialog = new UserCardsDialogFragment(this);
        alertDialog.show(fm, "fragment_alert");
    }

    private void startQr() {

        new IntentIntegrator(this).setPrompt("أمسح وأدفع").initiateScan();
    }

    private void getKey() {
        progressBar.startATM();

        ApiBuilder.callAPI(url).getKey(url + AppConstant.get_pKey).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressBar.closeATM();
                Log.wtf("onResponse", response.body());
                if (response.isSuccessful()) {
                    PublicKeyResponse keyResponse = new Gson().fromJson(AppConstant.getJsonObject(response.body()), PublicKeyResponse.class);
                    if (keyResponse.getResponseCode() == AppConstant.responseCode) {
                        doQrPurchase(keyResponse.getPubKeyValue());
                    } else {
                        showDialogError(keyResponse.getResponseMessage());
                    }

                } else {
                    Toast.makeText(PurchaseActivity.this, getString(R.string.server_error), Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.wtf("onFailure", t.getMessage());
                progressBar.closeATM();
                Toast.makeText(PurchaseActivity.this, getString(R.string.went_error), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void doQrPurchase(String pubKeyValue) {
        progressBar.startATM();
        String uuid = AppConstant.getUUID();
        String ipin = new RSAEncrypt(editTextIpin.getText().toString()).rsa_EBS_encrypt(uuid,
                pubKeyValue).replace("\n", "").replace("\r", "");

        Purchase purchase = new Purchase(editTextExpireDate.getText().toString(),
                editTextPanNumber.getText().toString().substring(editTextPanNumber.getText().toString().length() - 4),
                editTextPanNumber.getText().toString(),
                qrCode, merchantId,
                AppConstant.service,
                AppConstant.customerID,
                editTextAmount.getText().toString(),
                AppConstant.channel_id,
                ipin,
                uuid);


        String gson = new Gson().toJson(purchase);

        Log.wtf("StringGson", gson);

        ApiBuilder.callAPI(url).qrPurchase(url + AppConstant.consumer_services, gson).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressBar.closeATM();
                Log.wtf("onResponse", response.body());

                if (response.isSuccessful()) {
                    Log.wtf("onResponse", response.body());

                    String serverResponse = response.body();

                    String jsonData = serverResponse.substring(0, serverResponse.indexOf("}")).concat("}");
                    PurchaseResponse purchaseResponse = new Gson().fromJson(jsonData, PurchaseResponse.class);

                    if (purchaseResponse.getResponseCode() == AppConstant.responseCode) {
                        openResultUI(jsonData);
                    } else {
                        showDialogError(purchaseResponse.getResponseMessageArabic());
                    }

                } else {
                    Toast.makeText(PurchaseActivity.this, getString(R.string.server_error), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.wtf("onFailure", t.getMessage());
                progressBar.closeATM();
                Toast.makeText(PurchaseActivity.this, getString(R.string.went_error), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void openResultUI(String json) {

        Intent intent = null;
        try {
            intent = new Intent(this,
                    Class.forName("sd.rittal.qrsdk.PurchaseResultActivity"));
            intent.putExtra("PayJson", json);

            startActivity(intent);
            finish();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void showDialogError(String error) {

        Dialog dialog = new Dialog(PurchaseActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_error);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        TextView txt_msg = dialog.findViewById(R.id.txt_result_content_r);

        txt_msg.setText(error);

        Button button_done = dialog.findViewById(R.id.dialog_button_error);


        button_done.setOnClickListener(ppp -> {
            dialog.dismiss();
        });

    }


    private void showLayout() {
        merchant_name.setText(MerchantQrUtil.getMerchantName(qrCode));
        merchant_city.setText(MerchantQrUtil.getMerchantCity(qrCode));

        qrFailedLayout.setVisibility(View.GONE);
        paymentLayout.setVisibility(View.VISIBLE);
    }


    private String getMerchantID(String qrResult) {
        return MerchantQrUtil.getMerchantID(qrResult);
    }

    private void showExpireDateDialogFragment() {
        MonthYearPickerFragment pickerFragment = new MonthYearPickerFragment();
        pickerFragment.setListener((datePicker, year, month, i2) -> {

            String monthYearStr = year + "/" + month + "/" + i2;
            editTextExpireDate.setText(new DateSplite().expDate(monthYearStr));

        });

        pickerFragment.show(getSupportFragmentManager(), "MonthYearPickerDialog");
    }


    private boolean checkFields() {

        if (editTextPanNumber.getText().toString().isEmpty() || editTextPanNumber.getText().toString().length() < 16) {
            Toast.makeText(this, getString(R.string.pan_error), Toast.LENGTH_LONG).show();
            return false;
        }

        if (editTextExpireDate.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.expire_error), Toast.LENGTH_LONG).show();
            return false;
        }


        if (editTextIpin.getText().toString().isEmpty() || editTextIpin.getText().toString().length() < 4) {
            Toast.makeText(this, getString(R.string.ipin_erro), Toast.LENGTH_LONG).show();
            return false;
        }

        if (editTextAmount.getText().toString().isEmpty() || Integer.parseInt(editTextAmount.getText().toString()) < 1) {
            Toast.makeText(this, getString(R.string.amount_error), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {

            if (result.getContents() != null) {
                String qrResult = result.getContents();
                Log.wtf("qrResult", qrResult);
                String id = getMerchantID(qrResult);

                if (id.isEmpty() || id.length() < 8) {
                    Log.wtf("qrResultID", id);
                    Toast.makeText(this, getString(R.string.unvalid_qr), Toast.LENGTH_LONG).show();
                    qrFailedLayout.setVisibility(View.VISIBLE);
                } else {

                    qrCode = qrResult;
                    merchantId = id;

                    showLayout();

                    Log.wtf("qrResultFinal", "ID ->>" + id + " qrResult" + qrResult);

                }
            } else {
                Toast.makeText(this, R.string.qr_not_found, Toast.LENGTH_LONG).show();
                qrFailedLayout.setVisibility(View.VISIBLE);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void itemCustomerFavorite(UserCard card) {
        editTextExpireDate.setText(card.getExpireDate());
        editTextPanNumber.setText(card.getCardNumber());

    }

    @Override
    public void onAddCard() {
        Intent intent = null;
        try {
            intent = new Intent(this,
                    Class.forName("sd.rittal.qrsdk.uitl.AddCardActivity"));

            startActivity(intent);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

