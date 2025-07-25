package org.example.util;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class UserServiceUtil {

    // Generate random salt
    public static byte[] getSalt(){
        SecureRandom random  = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    // Hash password with PYKDF2
    public static String hashPassword(String password, byte[] salt) throws Exception{
        int iterations = 65536;
        int keyLength =256;
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(),salt,iterations,keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = skf.generateSecret(spec).getEncoded();

        //Encode salt and hash to Base64 for storing
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }

    //Verify password
    public static boolean verifyPassword(String password, String stored) throws Exception{
        String[] parts = stored.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] storedHash = Base64.getDecoder().decode(parts[1]);

        String hashedAttempt = hashPassword(password,salt).split(":")[1];
        byte[] attemptHash = Base64.getDecoder().decode(hashedAttempt);

        //compare hashes
        if(storedHash.length != attemptHash.length) return false;
        for(int i = 0 ; i< storedHash.length; i++){
            if(storedHash[i] != attemptHash[i]) return false;
        }return true;
    }
}
