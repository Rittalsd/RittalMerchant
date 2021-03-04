package sd.rittal.qrsdk.uitl;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import sd.rittal.qrsdk.R;


public class MonthYearPickerFragment extends DialogFragment {


    private DatePickerDialog.OnDateSetListener listener;

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        Calendar calendar = Calendar.getInstance();
        View dialog = layoutInflater.inflate(R.layout.fragment_month_year_picker, null);


        final NumberPicker monthPicker = dialog.findViewById(R.id.picker_month);
        final NumberPicker yearPicker = dialog.findViewById(R.id.picker_year);

        final Button btnSubmit = dialog.findViewById(R.id.btn_picker_submit);
        final Button btnCancel = dialog.findViewById(R.id.btn_picker_cancel);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(calendar.get(Calendar.MONTH) + 1);
        int year = calendar.get(Calendar.YEAR);
        yearPicker.setMinValue(2018);
        yearPicker.setMaxValue(2099);
        yearPicker.setValue(year);
        builder.setView(dialog);

        btnSubmit.setOnClickListener(view -> {
            listener.onDateSet(null, yearPicker.getValue(), monthPicker.getValue(), 0);
            MonthYearPickerFragment.this.getDialog().cancel();

        });

        btnCancel.setOnClickListener(vview -> {

            MonthYearPickerFragment.this.getDialog().cancel();
        });


        return builder.create();
    }
}