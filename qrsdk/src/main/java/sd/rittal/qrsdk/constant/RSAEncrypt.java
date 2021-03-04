/*
 * Copyright (c) 2020.
 * Rittal SDK
 * developed by Mohammed Imam 249123935893
 */

package sd.rittal.qrsdk.constant;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/*
 * Copyright (c) 2020.
 * Rittal SDK
 * developed by Mohammed Imam 249123935893
 */

public class RSAEncrypt {


    private String orgnalTxt;


    public RSAEncrypt(String orgnal) {
        this.orgnalTxt = orgnal;
    }

    public static String getIPINBlock(String orgnalTxt, String publicKey) {
        // clear ipin = uuid +  IPIN
        String txtencode = "";

        // prepare public key, get public key from its String representation as
        // base64
        byte[] keyByte = Base64.decode(publicKey, Base64.DEFAULT);
        // generate public key
        X509EncodedKeySpec s = new X509EncodedKeySpec(keyByte);
        KeyFactory factory = null;
        try {
            factory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Key pubKey = null;
        try {
            if (factory != null) {
                pubKey = factory.generatePublic(s);
            }
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            // construct Cipher with encryption algrithm:RSA, cipher mode:ECB and padding:PKCS1Padding
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            // calculate ipin, encryption then encoding to base64
            txtencode = (new String(Base64.encode(cipher.doFinal(orgnalTxt.getBytes()), Base64.DEFAULT)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return txtencode;
    }


    public String rsa_encrypt() {
        String pub_KEY;
        String fake = "";


        return getIPINBlock(orgnalTxt, fake);
    }

    public String rsa_EBS_encrypt(String uuid) {
        String pub_KEY = "";


        String clearipin = uuid + orgnalTxt;

        return getIPINBlock(clearipin, pub_KEY);
    }

    public String rsa_EBS_encrypt(String uuid, String Key) {
        String clearipin = uuid + orgnalTxt;
        return getIPINBlock(clearipin, Key);
    }


}
