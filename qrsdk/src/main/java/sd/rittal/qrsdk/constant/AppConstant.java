package sd.rittal.qrsdk.constant;

import java.util.UUID;

public class AppConstant {

    public static final String service = "21";
    public static final String channel_id = "2";
    public static final int responseCode = 0;

    public static final String customerID = "303300c4-33f9-4c50-8a85-586e469a2f9f";


    public static String consumer_services = "consumer_services.aspx";
    public static String get_pKey = "get_pKey.aspx";

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getJsonObject(String json) {
        return json.substring(0, json.indexOf('<'));
    }
}
