package Models;

import Utils.dbConnector;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginModel {

    public String authenticateLogin(String email, String password) {
        Connection conn;
        Statement statement;
        ResultSet resultSet = null;
        String DBemail = "";
        String DBpassword = "";
        String DBusername = "";
        String SHA256Password;

        try {
            conn = dbConnector.createConnection();
            SHA256Password=convertToSHA256(password);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users ");
            while (resultSet.next()) {
                DBemail = resultSet.getString("email");
                System.out.println(DBemail);
                DBpassword = resultSet.getString("password");
                System.out.println(DBpassword);
                DBusername = resultSet.getString("username");
                System.out.println(DBusername);

                //TO DO Register check for duplicate users
                if((email.equals(DBemail) && SHA256Password.equals(DBpassword)) ||
                        (email.equals(DBusername) && SHA256Password.equals(DBpassword))) {
                    return "SUCCESS";
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("W SQL EXCEPTIONA SYM");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "FAIL";
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