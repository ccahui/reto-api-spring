package com.reto.cliente.utils;

import com.reto.cliente.config.CargarData;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@Component
@Getter
public class CifrarRSA implements Cifrar{
    private final Logger log = LoggerFactory.getLogger(CifrarRSA.class);
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private Cipher cipher;

    public CifrarRSA() throws Exception{
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        cipher = Cipher.getInstance("RSA");
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();

        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());

        log.info("Private Key."+ privateKeyString );
        log.error("Public Key."+ publicKeyString );
    }

    public String privateKeyString(){
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }
    public String publicKeyString(){
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    @Override
    public String encriptar(String message) {
        String base64EncryptedMessage = "";

        try{
            byte[] data = message.getBytes("UTF-8");
            // Encrypt the message using the public key
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedData = cipher.doFinal(data);
            base64EncryptedMessage = Base64.getUrlEncoder().encodeToString(encryptedData);

        }
        catch (Exception exception){
            log.error("Error al encriptar.", exception.getMessage() );
        }
        return base64EncryptedMessage;
    }

    @Override
    public String desencriptar(String base64EncryptedMessage) {
        String decryptedMessage = "";
        // Decode the base64-encoded encrypted message

        try {
            byte[] decodedEncryptedData = Base64.getUrlDecoder().decode(base64EncryptedMessage);
            // Decrypt the message using the private key
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedData = cipher.doFinal(decodedEncryptedData);

            // Convert the decrypted data back to a string
            decryptedMessage = new String(decryptedData, "utf-8");
        } catch (Exception exception){
            log.error("Error al desencriptar. "+exception.getMessage() );
        }
        return decryptedMessage;
    }
}
