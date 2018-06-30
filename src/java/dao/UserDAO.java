package dao;

import entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author aline
 */
public class UserDAO {
    public static void saveUser(User u) throws ClassNotFoundException{
      Class.forName("org.apache.derby.jdbc.ClientDriver");
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/leilao; create=true;user=root;password=root");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO USER_LEILAO(NAME, EMAIL, CREDITS, CASH, PASSWORD) "
                    + "VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setInt(3, u.getCredits());
            ps.setDouble(4, u.getCash());
            ps.setString(5, u.getPassword());
            ps.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static void updateUser(User u) throws ClassNotFoundException{
      Class.forName("org.apache.derby.jdbc.ClientDriver");
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/leilao; create=true;user=root;password=root");
              PreparedStatement ps = conn.prepareStatement("UPDATE USER_LEILAO SET NAME = \'" + u.getName() + "\', "
                      + "CREDITS = " + u.getCredits() + ", CASH = " + u.getCash() + " WHERE EMAIL = \'" + u.getEmail() + "\'");
              ps.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static List<User> getUsers() throws ClassNotFoundException, ParseException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        User u = new User();
        List<User> list = new ArrayList();
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/leilao; create=true;user=root;password=root");
            Statement s = conn.createStatement();
            s.execute("SELECT * FROM USER_LEILAO");
            ResultSet rs = s.getResultSet();
            
            while(rs.next()){
                u = new User();
                
                if(!(rs.getString("NAME").equals(""))){
                    u.setName(rs.getString("NAME"));
                }
                
                if(!(rs.getString("EMAIL").equals(""))){
                    u.setEmail(rs.getString("EMAIL"));
                }
                
                if(!(rs.getString("CREDITS")).equals("")){
                    u.setCredits(Integer.parseInt(rs.getString("CREDITS")));
                }
                
                if(!(rs.getString("CASH")).equals("")){
                    u.setCash(Double.parseDouble(rs.getString("CASH")));
                }
                
                list.add(u);
            }
            
            return list;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }
    
    public static User getUser(String email) throws ClassNotFoundException, ParseException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        User u = new User();
        List<User> list = new ArrayList();
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/leilao; create=true;user=root;password=root");
            Statement s = conn.createStatement();
            s.execute("SELECT * FROM USER_LEILAO WHERE EMAIL = \'" + email + "\'");
            ResultSet rs = s.getResultSet();
            
            while(rs.next()){
                u = new User();
                
                if(!(rs.getString("NAME").equals(""))){
                    u.setName(rs.getString("NAME"));
                }
                
                if(!(rs.getString("EMAIL").equals(""))){
                    u.setEmail(rs.getString("EMAIL"));
                }
                
                if(!(rs.getString("CREDITS")).equals("")){
                    u.setCredits(Integer.parseInt(rs.getString("CREDITS")));
                }
                
                if(!(rs.getString("CASH")).equals("")){
                    u.setCash(Double.parseDouble(rs.getString("CASH")));
                }
                
                if(!(rs.getString("PASSWORD").equals(""))){
                    u.setPassword(rs.getString("PASSWORD"));
                }
                
                list.add(u);
            }
            if(!list.isEmpty())
                return list.get(0);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void removeUser(String email) throws ClassNotFoundException{
      Class.forName("org.apache.derby.jdbc.ClientDriver");
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/leilao; create=true;user=root;password=root");
              PreparedStatement ps = conn.prepareStatement("DELETE FROM USER_LEILAO WHERE ID = \'" + email + "\'");
              ps.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
