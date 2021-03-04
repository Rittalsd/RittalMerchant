package sd.rittal.qrsdk.uitl;

import org.json.JSONException;
import org.json.JSONObject;

public class MerchantQrUtil {


    public static String getMerchantID(String qrCode) {
        if (getMerchantData(qrCode) != null) {

            if (!getMerchantData(qrCode).isNull("sudaneseMerchantAccountInformation")) {

                try {
                    return getMerchantData(qrCode).
                            getJSONObject("sudaneseMerchantAccountInformation")
                            .getJSONObject("elementValue").getJSONObject("merchantID").getString("elementValue");
                } catch (NumberFormatException | JSONException exception) {
                    return "";
                }
            } else {
                return "";
            }

        } else {
            return "";
        }

    }

    public static String getMerchantCity(String qrCode) {
        try {
            return getMerchantData(qrCode).
                    getJSONObject("merchantCity")
                    .getString("elementValue");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getMerchantName(String qrCode) {
        try {
            return getMerchantData(qrCode).
                    getJSONObject("merchantName")
                    .getString("elementValue");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }


    static JSONObject getMerchantData(String QrCodeText) {

        JSONObject QRCodeObj = null;
        //character pointer
        int posison = 0;
        int QrCodeLength = QrCodeText.length();
        String elementID = "";
        int elementLength = 0;
        String elementValue = "";
        try {
            QRCodeObj = new JSONObject();
            while (posison < QrCodeLength) {
                //get Element ID
                elementID = QrCodeText.substring(posison, posison + 2);
                //get Element value length
                elementLength = Integer.valueOf(QrCodeText.substring(posison + 2, posison + 4));
                // get element value
                elementValue = QrCodeText.substring(posison + 4, posison + 4 + elementLength);
                //move pointer to first character in the new (next) element
                posison = posison + 4 + elementLength;
                //convert element to JSON format
                JSONObject element = QRElementObjectBuilder(elementID, elementLength, elementValue);
                try {
                    QRCodeObj.put(element.getString("name"), element);
                } catch (JSONException e) {
                    //Logger("QRCodeObjBuilderExp",e.toString());


                }
            }
        } catch (Exception exception) {

        }
        return QRCodeObj;
    }

    static JSONObject QRElementObjectBuilder(String elementID, int elementLength, String elementValue) {
        JSONObject element = new JSONObject();

        try {
            switch (elementID) {
                case "00":
                    element.put("name", "formatIndicator");
                    element.put("description", "Payload Format Indicator");
                    element.put("elementID", elementID);
                    element.put("elementLength", elementLength);
                    element.put("elementValue", elementValue);
                    break;
                case "01":
                    element.put("name", "initiationMethod");
                    element.put("description", "Point of Initiation Method");
                    element.put("elementID", elementID);
                    element.put("elementLength", elementLength);
                    element.put("elementValue", elementValue);
                    break;
                case "51":
                    element.put("name", "sudaneseMerchantAccountInformation");
                    element.put("description", "Sudanese Merchant Account Information");
                    element.put("elementID", elementID);
                    element.put("elementLength", elementLength);


                    JSONObject subElements = new JSONObject();

                    int subPosison = 0;
                    int subQrCodeLength = elementValue.length();

                    String SubElementID = "";
                    int SubElementLength = 0;
                    String SubElementValue = "";

                    while (subPosison < subQrCodeLength) {
                        SubElementID = elementValue.substring(subPosison, subPosison + 2);
                        //log("SubElementID",SubElementID);
                        SubElementLength = Integer.valueOf(elementValue.substring(subPosison + 2, subPosison + 4));
                        //log("SubElementLength",SubElementLength+"");
                        SubElementValue = elementValue.substring(subPosison + 4, subPosison + 4 + SubElementLength);
                        //log("SubElementValue",SubElementValue);
                        subPosison = subPosison + 4 + SubElementLength;

                        JSONObject subElement = QRSubElementObjectBuilder(SubElementID, SubElementLength, SubElementValue);

                        subElements.put(subElement.getString("name"), subElement);

                    }

                    element.put("elementValue", subElements);
                    break;
                case "52":
                    element.put("name", "categoryCode");
                    element.put("description", "Merchant Category Code");
                    element.put("elementID", elementID);
                    element.put("elementLength", elementLength);
                    element.put("elementValue", elementValue);
                    break;
                case "53":
                    element.put("name", "currencyCode");
                    element.put("description", "Transaction Currency Code");
                    element.put("elementID", elementID);
                    element.put("elementLength", elementLength);
                    element.put("elementValue", elementValue);
                    break;
                case "58":
                    element.put("name", "countryCode");
                    element.put("description", "Country Code");
                    element.put("elementID", elementID);
                    element.put("elementLength", elementLength);
                    element.put("elementValue", elementValue);
                    break;
                case "59":
                    element.put("name", "merchantName");
                    element.put("description", "Merchant Name");
                    element.put("elementID", elementID);
                    element.put("elementLength", elementLength);
                    element.put("elementValue", elementValue);
                    break;
                case "60":
                    element.put("name", "merchantCity");
                    element.put("description", "Merchant City");
                    element.put("elementID", elementID);
                    element.put("elementLength", elementLength);
                    element.put("elementValue", elementValue);
                    break;
                case "63":
                    element.put("name", "CRC");
                    element.put("description", "Cyclic Redundancy Check (CRC)");
                    element.put("elementID", elementID);
                    element.put("elementLength", elementLength);
                    element.put("elementValue", elementValue);
                    break;
                default:
                    break;


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return element;
    }

    static JSONObject QRSubElementObjectBuilder(String elementID, int elementLength, String elementValue) {
        JSONObject element = new JSONObject();

        try {
            switch (elementID) {
                case "00":
                    element.put("name", "AID");
                    element.put("description", "Application Identifier (AID)");
                    element.put("elementID", elementID);
                    element.put("elementLength", elementLength);
                    element.put("elementValue", elementValue);
                    break;
                case "01":
                    element.put("name", "acquirer_ID");
                    element.put("description", "Acquirer ID");
                    element.put("elementID", elementID);
                    element.put("elementLength", elementLength);
                    element.put("elementValue", elementValue);
                    break;
                case "02":
                    element.put("name", "merchantID");
                    element.put("description", "Merchant ID");
                    element.put("elementID", elementID);
                    element.put("elementLength", elementLength);
                    element.put("elementValue", elementValue);
                    break;
                case "04":
                    element.put("name", "hashValue");
                    element.put("description", "Hash Value");
                    element.put("elementID", elementID);
                    element.put("elementLength", elementLength);
                    element.put("elementValue", elementValue);
                    break;
                default:
                    break;


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return element;
    }


}