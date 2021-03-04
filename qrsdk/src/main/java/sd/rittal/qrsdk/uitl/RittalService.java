package sd.rittal.qrsdk.uitl;

import android.content.Context;
import android.content.Intent;

public class RittalService {


    /**
     * <p>
     * This method allows you to Purchase with QR Code , just call it from an Activity or a fragment
     * <p>
     * example :
     * <p>
     * RittalService.qrPurchase(this,"172.217.14.196");
     * </p>
     *
     * @param context context.
     */


    public static void qrPurchase(Context context, String url) {
        Intent intent = null;
        try {
            intent = new Intent(context,
                    Class.forName("sd.rittal.qrsdk.PurchaseActivity"));
            intent.putExtra("url", url);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
