package org.generation.ecommercedb.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class SHAUtil {

    public static String salt="YHLQMDLG";
    public static String createHash(String value){
        String res = value+salt;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(res.getBytes());
            byte[] resultado = md.digest();
            int max = resultado.length;
            String tmp;
            res = "";
            for (int i=0; i<max; i++) {
                tmp = Integer.toHexString(0xFF & resultado[i]);
                res += (tmp.length()<2)?0+tmp:tmp;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public static boolean verifyHash(String original, String hash){
        String res = createHash(original);
        return res.equalsIgnoreCase(hash);
    }

    public static boolean verifyPassword(String original, String newPassword){
        return createHash(original).equalsIgnoreCase(createHash(newPassword));
    }
}
