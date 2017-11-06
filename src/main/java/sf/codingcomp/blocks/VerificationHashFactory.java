package sf.codingcomp.blocks;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class VerificationHashFactory {

    public static String buildVerificationHash(Block<?> block) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        byte[] hash = digest.digest(ByteBuffer.allocate(4).putInt(block.hashCode()).array());
        StringBuffer hexString = new StringBuffer();

        for(int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
