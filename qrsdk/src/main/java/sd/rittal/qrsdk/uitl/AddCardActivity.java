package sd.rittal.qrsdk.uitl;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import sd.rittal.qrsdk.R;
import sd.rittal.qrsdk.beans.UserCard;

public class AddCardActivity extends AppCompatActivity {

    Button buttonSubmit;

    EditText editTextPanNumber, editTextExpireDate,
            editText_pan_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);


        editTextPanNumber = findViewById(R.id.editText_pan_number);
        editTextExpireDate = findViewById(R.id.editText_expire_date);

        editText_pan_name = findViewById(R.id.editText_pan_name);

        buttonSubmit = findViewById(R.id.buttonSubmit);

        editTextExpireDate.setOnClickListener(vvv -> {
            showExpireDateDialogFragment();
        });


        buttonSubmit.setOnClickListener(bV -> {
            if (checkFields()) {
                addCard();
            }
        });
    }

    private void addCard() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();

        String json = preferences.getString("strList", "");

        if (!json.isEmpty()) {
            Type type = new TypeToken<ArrayList<UserCard>>() {
            }.getType();
            ArrayList<UserCard> arrayList = gson.fromJson(json, type);
            UserCard card = new UserCard();
            card.setCardName(editText_pan_name.getText().toString());
            card.setCardNumber(editTextPanNumber.getText().toString());
            card.setExpireDate(editTextExpireDate.getText().toString());
            arrayList.add(card);
            String strList = gson.toJson(arrayList);
            editor.putString("strList", strList);
            editor.apply();
            Toast.makeText(this, getString(R.string.successfully), Toast.LENGTH_SHORT).show();
        } else {
            // add new card
            ArrayList<UserCard> userCards = new ArrayList<>();
            UserCard card = new UserCard();
            card.setCardName(editText_pan_name.getText().toString());
            card.setCardNumber(editTextPanNumber.getText().toString());
            card.setExpireDate(editTextExpireDate.getText().toString());
            userCards.add(card);
            String strList = gson.toJson(userCards);
            editor.putString("strList", strList);
            editor.apply();
            Toast.makeText(this, getString(R.string.successfully), Toast.LENGTH_SHORT).show();
        }
        finish();



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


        if (editText_pan_name.getText().toString().isEmpty() || editText_pan_name.getText().toString().length() < 4) {
            Toast.makeText(this, getString(R.string.pan_name), Toast.LENGTH_LONG).show();
            return false;
        }


        return true;
    }
}