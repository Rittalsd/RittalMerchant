package sd.rittal.qrsdk.uitl;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import sd.rittal.qrsdk.R;

public class ProgressATM {

    ProgressDialog progDialog;
    Context contextt;

    public ProgressATM(Context context) {
        contextt = context;
        progDialog = ProgressDialog.show(context, null, null, false, true);
        progDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progDialog.setContentView(R.layout.progressdialog);
        progDialog.setCanceledOnTouchOutside(false);
        progDialog.dismiss();
        progDialog.setCancelable(false);
    }

    public void startATM() {

        progDialog.show();
    }

    public void closeATM() {
        progDialog.dismiss();
    }
}