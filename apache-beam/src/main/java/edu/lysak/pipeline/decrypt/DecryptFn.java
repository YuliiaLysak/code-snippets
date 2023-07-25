package edu.lysak.pipeline.decrypt;

import org.apache.beam.sdk.transforms.DoFn;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DecryptFn extends DoFn<String, String> {

    private SecretKey key;
    private int KEY_SIZE = 128;
    private int T_LEN = 128;
    private byte[] IV;


    @ProcessElement
    public void processElement(ProcessContext c) throws Exception {
        initFromStrings("CHuO1Fjd8YgJqTyapibFBQ==", "e3IYYJC2hxe24/EO");
        String encryptedString = c.element();
        byte[] messageInBytes = decode(encryptedString);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);
        c.output(new String(decryptedBytes));
    }

    private void initFromStrings(String secretKey, String IV) {
        key = new SecretKeySpec(decode(secretKey), "AES");
        this.IV = decode(IV);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}
