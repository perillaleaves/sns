package project.config;

import project.domain.user.User;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;

public class GenerateToken {

    public static String generatedToken(User userId, String email) {
        String val = String.valueOf((int) (Math.random() * 1234567));
        String str = userId + val + email;
        String stringToHex = getStringToHex(str);
        return stringToHex;
    }

    private static String getStringToHex(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        return DatatypeConverter.printHexBinary(bytes);
    }

}