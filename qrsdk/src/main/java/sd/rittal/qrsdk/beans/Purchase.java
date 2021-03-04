package sd.rittal.qrsdk.beans;

import com.google.gson.annotations.SerializedName;

public class Purchase{


	public Purchase(String expiryDate, String last4PanDigit, String fromCard, String qrCode, String merchantId, String service, String customerID, String tranAmount, String channelId, String ipin, String uuid) {
		this.expiryDate = expiryDate;
		this.last4PanDigit = last4PanDigit;
		this.fromCard = fromCard;
		this.qrCode = qrCode;
		this.merchantId = merchantId;
		this.service = service;
		this.customerID = customerID;
		this.tranAmount = tranAmount;
		this.channelId = channelId;
		this.ipin = ipin;
		this.uuid = uuid;
	}

	@SerializedName("expiryDate")
	private String expiryDate;

	@SerializedName("last4PanDigit")
	private String last4PanDigit;

	@SerializedName("fromCard")
	private String fromCard;

	@SerializedName("qrCode")
	private String qrCode;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("service")
	private String service;

	@SerializedName("customerID")
	private String customerID;

	@SerializedName("tranAmount")
	private String tranAmount;

	@SerializedName("channel_id")
	private String channelId;

	@SerializedName("ipin")
	private String ipin;

	@SerializedName("uuid")
	private String uuid;

	public String getExpiryDate(){
		return expiryDate;
	}

	public String getLast4PanDigit(){
		return last4PanDigit;
	}

	public String getFromCard(){
		return fromCard;
	}

	public String getQrCode(){
		return qrCode;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public String getService(){
		return service;
	}

	public String getCustomerID(){
		return customerID;
	}

	public String getTranAmount(){
		return tranAmount;
	}

	public String getChannelId(){
		return channelId;
	}

	public String getIpin(){
		return ipin;
	}

	public String getUuid(){
		return uuid;
	}
}