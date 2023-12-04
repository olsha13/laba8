package dbHandler;

import exception.CustomException;

import java.sql.*;

public class RoomDbService {
    private final static String DB_URL = "jdbc:postgresql://localhost:5432/Psp8LabDb";
    private final static String DB_USER = "postgres";
    private final static String DB_PASSWORD = "19091970Ig";
    public static void addRoomToDb(String name,String password,int roomNumber) throws CustomException {
        try {
            int usId = UserDbService.getIdByNameAndPassword(name,password);
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement("INSERT INTO public .\"rooms_data\""+"(us_id,room_number) " +"VALUES (?,?);");
            ps.setInt(1,usId);
            ps.setInt(2,roomNumber);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }
    }
    public static Integer getIdByNumber(Integer number) throws CustomException {
        int resId =0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM public .\"rooms_data\" ;");
            while (rs.next()){
                if(rs.getInt(3)==number){
                    resId = rs.getInt(2);
                    break;
                }
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }
        return resId;
    }
    public static void delAnyRoomFromDb(int roomNumber) throws CustomException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement("DELETE FROM public .\"rooms_data\""+"WHERE room_number = ?;");
            ps.setInt(1,roomNumber);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }
    }
    public static boolean checkIfRoomIsOrdered(int roomNumber) throws CustomException {
        boolean roomExists = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            ResultSet rs = con.createStatement().executeQuery("SELECT room_number FROM public .\"rooms_data\" ;");
            while (rs.next()){
                if(rs.getInt(1) ==roomNumber){
                    roomExists=true;
                }
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return roomExists;
    }
    public static ResultSet showAllDataForAdm() throws CustomException {
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            rs = con.createStatement().executeQuery("SELECT * FROM public .\"rooms_data\" INNER JOIN public .\"user_data\" ON " +
                    "public .\"rooms_data\".us_id =public .\"user_data\".id;");
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return rs;
    }
}
