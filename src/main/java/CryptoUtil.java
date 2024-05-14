import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.util.Base64;

public final class CryptoUtil {
    private static final String DF_ALGORITHM = "PBEwithSHA1AndDESede";

    private static final byte[] DF_SALT = {75, 64, 116, 97, 108, 48, 110, 32, 83, 84, 117, 100, 108, 79};

    private static final byte[] DF_SECRET_KEY = {83, 51, 99, 82, 101, 84, 32, 75, 51, 105};

    private static final int DF_ITERATION = 20;

    private static final String DF_ENCODING = "UTF-8";

    public static String decode(String data) throws GeneralSecurityException, IOException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DF_ALGORITHM);
        String privateKeyString = new String(DF_SECRET_KEY, DF_ENCODING);
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(privateKeyString.toCharArray()));
        Cipher pbeCipher = Cipher.getInstance(DF_ALGORITHM);
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(DF_SALT, DF_ITERATION));
        return new String(pbeCipher.doFinal(Base64.getDecoder().decode(data)), DF_ENCODING);
    }

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        System.out.println(decode(args.length > 0 ? args[0] : "GklqZBguAPQ="));
    }
}