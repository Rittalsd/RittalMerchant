package sd.rittal.qrsdk.beans;

import com.google.gson.annotations.SerializedName;

public class PublicKeyResponse{

	@SerializedName("acqTranFee")
	private int acqTranFee;

	@SerializedName("userPassword")
	private Object userPassword;

	@SerializedName("payees")
	private Object payees;

	@SerializedName("fromAccount")
	private Object fromAccount;

	@SerializedName("accountCurrency")
	private Object accountCurrency;

	@SerializedName("serviceInfo")
	private Object serviceInfo;

	@SerializedName("originalTranUUID")
	private Object originalTranUUID;

	@SerializedName("expDate")
	private Object expDate;

	@SerializedName("responseCode")
	private int responseCode;

	@SerializedName("balance")
	private Object balance;

	@SerializedName("billInfo")
	private Object billInfo;

	@SerializedName("financialInstitutionId")
	private Object financialInstitutionId;

	@SerializedName("voucherNumber")
	private Object voucherNumber;

	@SerializedName("issuerTranFee")
	private int issuerTranFee;

	@SerializedName("UUID")
	private Object uUID;

	@SerializedName("panCategory")
	private Object panCategory;

	@SerializedName("voucherCode")
	private Object voucherCode;

	@SerializedName("tranDateTime")
	private Object tranDateTime;

	@SerializedName("originalTranType")
	private Object originalTranType;

	@SerializedName("responseStatus")
	private Object responseStatus;

	@SerializedName("creationDate")
	private Object creationDate;

	@SerializedName("userName")
	private Object userName;

	@SerializedName("pubKeyValue")
	private String pubKeyValue;

	@SerializedName("originalTransaction")
	private Object originalTransaction;

	@SerializedName("IPIN")
	private Object iPIN;

	@SerializedName("PAN")
	private Object pAN;

	@SerializedName("responseMessage")
	private String responseMessage;

	@SerializedName("tranAmount")
	private int tranAmount;

	public int getAcqTranFee(){
		return acqTranFee;
	}

	public Object getUserPassword(){
		return userPassword;
	}

	public Object getPayees(){
		return payees;
	}

	public Object getFromAccount(){
		return fromAccount;
	}

	public Object getAccountCurrency(){
		return accountCurrency;
	}

	public Object getServiceInfo(){
		return serviceInfo;
	}

	public Object getOriginalTranUUID(){
		return originalTranUUID;
	}

	public Object getExpDate(){
		return expDate;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public Object getBalance(){
		return balance;
	}

	public Object getBillInfo(){
		return billInfo;
	}

	public Object getFinancialInstitutionId(){
		return financialInstitutionId;
	}

	public Object getVoucherNumber(){
		return voucherNumber;
	}

	public int getIssuerTranFee(){
		return issuerTranFee;
	}

	public Object getUUID(){
		return uUID;
	}

	public Object getPanCategory(){
		return panCategory;
	}

	public Object getVoucherCode(){
		return voucherCode;
	}

	public Object getTranDateTime(){
		return tranDateTime;
	}

	public Object getOriginalTranType(){
		return originalTranType;
	}

	public Object getResponseStatus(){
		return responseStatus;
	}

	public Object getCreationDate(){
		return creationDate;
	}

	public Object getUserName(){
		return userName;
	}

	public String getPubKeyValue(){
		return pubKeyValue;
	}

	public Object getOriginalTransaction(){
		return originalTransaction;
	}

	public Object getIPIN(){
		return iPIN;
	}

	public Object getPAN(){
		return pAN;
	}

	public String getResponseMessage(){
		return responseMessage;
	}

	public int getTranAmount(){
		return tranAmount;
	}
}