package JerseyTom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {
   public Connection conn;

    public AlienRepository() {
        String url ="jdbc:mysql://localhost:3306/restdb?useTimezone=true&serverTimezone=UTC";
        String username = "root";
        String password = "mikeadekunle";
        try {
            try {

                Class.forName("com.mysql.jdbc.Driver");

            } catch (ClassNotFoundException e) {
                System.out.println("Something went wrong "+ e.getMessage());
                e.printStackTrace();
            }
            conn = DriverManager.getConnection(url,username, password);
            System.out.println(conn);
        } catch (SQLException e) {
            System.out.println("Something went wrong "+e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Alien> getAliens(){
        List<Alien> aliens = new ArrayList<>();
        String sql ="SELECT * FROM alien";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Alien a = new Alien();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setPoints(rs.getInt(3));
                aliens.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong "+ e.getMessage());
            e.printStackTrace();
        }
        return aliens;
    }

    public Alien getAlien(int id){
        String sql = "SELECT * FROM alien WHERE id="+id;
        Alien a = new Alien();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                a.setName(rs.getString(2));
                a.setId(rs.getInt(1));
                a.setPoints(rs.getInt(3));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public void create(Alien a1) {
        String sql = "INSERT INTO alien VALUES (?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,a1.getId());
            ps.setString(2,a1.getName());
            ps.setInt(3,a1.getPoints());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Alien a1){
        String sql="UPDATE alien SET name=?, points=? where id = ?";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(2,a1.getPoints());
            ps.setString(1,a1.getName());
            ps.setInt(3,a1.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void delete(int id) {
        String sql = "DELETE FROM alien WHERE id = ?";

            try {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1,id);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e);
            }


    }
}
