package Models;

import Utils.dbConnector;
import sun.misc.BASE64Encoder;

import javax.jms.Session;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


public class User {
    public boolean CreateRegistration(String email, String password, String username) throws SQLException {

        Connection conn;

        String SHA256Password;



        try {
            conn = dbConnector.createConnection();

            SHA256Password = convertToSHA256(password);
            String query = " insert into users (email,password,username)"
                    + " values (?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, SHA256Password);
            preparedStmt.setString(3,username);
            preparedStmt.executeUpdate();
            System.out.println(preparedStmt);
            System.out.println("VKARA USERA V BAZATA dANNI");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("NESHTO NE STAVA");
            return false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }


    }
    public static String convertToSHA256(String inputString) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(inputString.getBytes());
        byte byteData[] = md.digest();
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }


}

