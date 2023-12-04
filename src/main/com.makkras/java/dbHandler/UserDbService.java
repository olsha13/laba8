package dbHandler;

import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserDbService {
    private final static String DB_URL = "jdbc:postgresql://localhost:5432/Psp8LabDb";
    private final static String DB_USER = "postgres";
    private final static String DB_PASSWORD = "19091970Ig";
    private static Logger logger = LogManager.getLogger();
    public static boolean checkIfUsExists(String name,String password) throws CustomException {
        boolean usExists = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM public .\"user_data\" ;");
            while (rs.next()){
                if(rs.getString(2).equals(name) && rs.getString(3).equals(password)){
                    usExists=true;
                }
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return usExists;
    }
    public static boolean checkIfUsIsAdm(String name,String password, boolean isAdm) throws CustomException {
        boolean isAdmRes = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM public .\"user_data\" ;");
            while (rs.next()){
                if(rs.getString(2).equals(name) && rs.getString(3).equals(password) && rs.getBoolean(4) == isAdm){
                    isAdmRes=true;
                }
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return isAdmRes;
    }
    public static ResultSet getUsersFromDb() throws CustomException {
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            rs = con.createStatement().executeQuery("SELECT * FROM public .\"user_data\" ;");
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return rs;
    }
    public static Integer getIdByNameAndPassword(String name,String password) throws CustomException {
        int resId =0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM public .\"user_data\" ;");
            while (rs.next()){
                if(rs.getString(2).equals(name) && rs.getString(3).equals(password)){
                    resId = rs.getInt(1);
                    break;
                }
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return resId;
    }
    public static void addUsToDb(String name,String password, boolean isAdm) throws CustomException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement("INSERT INTO public .\"user_data\""+"(name,password,is_admin) " +"VALUES (?,?,?);");
            ps.setString(1,name);
            ps.setString(2,password);
            ps.setBoolean(3,isAdm);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }
    }
    public static void delUserFromDb(String name,String password) throws CustomException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement("DELETE FROM public .\"user_data\""+"WHERE name = ? AND password = ?;");
            ps.setString(1,name);
            ps.setString(2,password);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }
    }
}
