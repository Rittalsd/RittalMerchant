package sd.rittal.qrsdk.beans;

import com.google.gson.annotations.SerializedName;

public class PurchaseResponse{

	@SerializedName("acqTranFee")
	private int acqTranFee;

	@SerializedName("toAccount")
	private Object toAccount;

	@SerializedName("dynamicFees")
	private int dynamicFees;

	@SerializedName("fromAccount")
	private String fromAccount;

	@SerializedName("accountCurrency")
	private String accountCurrency;

	@SerializedName("expDate")
	private Object expDate;

	@SerializedName("responseCode")
	private int responseCode;

	@SerializedName("fromAccountType")
	private Object fromAccountType;

	@SerializedName("balance")
	private String balance;

	@SerializedName("issuerTranFee")
	private int issuerTranFee;

	@SerializedName("UUID")
	private String uUID;

	@SerializedName("tranDateTime")
	private String tranDateTime;

	@SerializedName("last4PANDigits")
	private Object last4PANDigits;

	@SerializedName("toAccountType")
	private Object toAccountType;

	@SerializedName("tranCurrency")
	private Object tranCurrency;

	@SerializedName("entityType")
	private Object entityType;

	@SerializedName("checkDuplicate")
	private boolean checkDuplicate;

	@SerializedName("entityId")
	private Object entityId;

	@SerializedName("responseStatus")
	private String responseStatus;

	@SerializedName("userName")
	private Object userName;

	@SerializedName("merchantID")
	private Object merchantID;

	@SerializedName("authenticationType")
	private Object authenticationType;

	@SerializedName("responseMessage")
	private String responseMessage;

	@SerializedName("responseMessageArabic")
	private String responseMessageArabic;

	@SerializedName("applicationId")
	private Object applicationId;

	@SerializedName("PAN")
	private String pAN;

	@SerializedName("mbr")
	private Object mbr;

	@SerializedName("tranAmount")
	private int tranAmount;

	public int getAcqTranFee(){
		return acqTranFee;
	}

	public Object getToAccount(){
		return toAccount;
	}

	public int getDynamicFees(){
		return dynamicFees;
	}

	public String getFromAccount(){
		return fromAccount;
	}

	public String getAccountCurrency(){
		return accountCurrency;
	}

	public Object getExpDate(){
		return expDate;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public Object getFromAccountType(){
		return fromAccountType;
	}

	public String getBalance(){
		return balance;
	}

	public int getIssuerTranFee(){
		return issuerTranFee;
	}

	public String getUUID(){
		return uUID;
	}

	public String getTranDateTime(){
		return tranDateTime;
	}

	public Object getLast4PANDigits(){
		return last4PANDigits;
	}

	public Object getToAccountType(){
		return toAccountType;
	}

	public Object getTranCurrency(){
		return tranCurrency;
	}

	public Object getEntityType(){
		return entityType;
	}

	public boolean isCheckDuplicate(){
		return checkDuplicate;
	}

	public Object getEntityId(){
		return entityId;
	}

	public String getResponseStatus(){
		return responseStatus;
	}

	public Object getUserName(){
		return userName;
	}

	public Object getMerchantID(){
		return merchantID;
	}

	public Object getAuthenticationType(){
		return authenticationType;
	}

	public String getResponseMessage(){
		return responseMessage;
	}

	public String getResponseMessageArabic(){
		return responseMessageArabic;
	}

	public Object getApplicationId(){
		return applicationId;
	}

	public String getPAN(){
		return pAN;
	}

	public Object getMbr(){
		return mbr;
	}

	public int getTranAmount(){
		return tranAmount;
	}
}