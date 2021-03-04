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
     * RittalService.qrPurchase(this);
     * </p>
     *
     * @param context context.
     */


    public static void qrPurchase(Context context, String url) {
        Intent intent = null;
        try {
            intent = new Intent(context,
                    Class.forName("sd.rittal.qrsdk.PurchaseActivity"));

        /*    intent.putExtra("orgName", orgName);
            intent.putExtra("orgPass", orgPass);
            intent.putExtra("appName", appName);*/
            intent.putExtra("url", url);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * <p>
     * This method execute payment operations , just call it from an Activity or a fragment
     * <p>
     * example :
     * <p>
     * CallRittalService.doPayment(getApplicationContext(), "RittalTest", "PTest_Rittal", "RittalPgApp", "18560f9b-c1b1-42d4-9832-9edc1ea30b40");
     * </p>
     *
     * @param context    context.
     * @param orgName    organization name
     * @param appName    application name
     * @param orgPass    organization password
     * @param checkOutID checkOutID its given from service provider
     */

    public static void doPayment(Context context, String orgName, String orgPass, String appName, String checkOutID) {
        Intent intent = null;
        try {
            intent = new Intent(context,
                    Class.forName("sd.rittal.merhant.views.PurchaseActivity"));

            intent.putExtra("orgName", orgName);
            intent.putExtra("orgPass", orgPass);
            intent.putExtra("appName", appName);
            intent.putExtra("checkOutID", checkOutID);

            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
