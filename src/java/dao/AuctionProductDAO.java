package dao;

import entity.AuctionProduct;
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
public class AuctionProductDAO {
    public static void saveProduct(AuctionProduct ap) throws ClassNotFoundException{
      Class.forName("org.apache.derby.jdbc.ClientDriver");
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/leilao; create=true;user=root;password=root");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO PRODUCT(ID, NAME, DESCRIPTION, INITIAL_VALUE, CURRENT_VALUE, USER_NAME, "
                    + "INITIAL_DATE, PRODUCT_IMAGE, HAS_STARTED, HAS_FINISHED, EXPIRATION_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, ap.getId());
            ps.setString(2, ap.getName());
            ps.setString(3, ap.getDescription());
            ps.setInt(4, ap.getInitialValue());
            ps.setInt(5, ap.getCurrentValue());
            ps.setString(6, ap.getUserName());
            ps.setString(7, ap.getInitialDate());
            ps.setString(8, ap.getProductImage());
            ps.setBoolean(9, ap.isHasStarted());
            ps.setBoolean(10, ap.isHasFinished());
            ps.setInt(11, ap.getExpirationTime());
            ps.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static void updateProduct(AuctionProduct ap) throws ClassNotFoundException{
      Class.forName("org.apache.derby.jdbc.ClientDriver");
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/leilao; create=true;user=root;password=root");
              PreparedStatement ps = conn.prepareStatement("UPDATE PRODUCT SET NAME = \'" + ap.getName() + "\', "
                      + "DESCRIPTION = \'" + ap.getDescription() + "\', INITIAL_VALUE = " + ap.getInitialValue() + ", "
                      + "CURRENT_VALUE = " + ap.getCurrentValue() + ", USER_NAME = \'" + ap.getUserName() + "\', "
                      + "INITIAL_DATE = \'" + ap.getInitialDate() + "\', PRODUCT_IMAGE = \'" + ap.getProductImage() + "\', "
                      + "HAS_STARTED = " + ap.isHasStarted() + ", HAS_FINISHED = " + ap.isHasFinished() + ", "
                      + "EXPIRATION_TIME = " + ap.getExpirationTime() + " WHERE ID = \'" + ap.getId() + "\'");
              ps.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static List<AuctionProduct> getProducts() throws ClassNotFoundException, ParseException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        AuctionProduct ap = new AuctionProduct();
        List<AuctionProduct> list = new ArrayList();
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/leilao; create=true;user=root;password=root");
            Statement s = conn.createStatement();
            s.execute("SELECT * FROM PRODUCT");
            ResultSet rs = s.getResultSet();
            
            while(rs.next()){
                ap = new AuctionProduct();
                
                if(!(rs.getString("ID").equals(""))){
                    ap.setId(Integer.parseInt(rs.getString("ID")));
                }
                
                if(!(rs.getString("NAME").equals(""))){
                    ap.setName(rs.getString("NAME"));
                }
                
                if(!(rs.getString("DESCRIPTION").equals(""))){
                    ap.setDescription(rs.getString("DESCRIPTION"));
                }
                
                if(!(rs.getString("INITIAL_VALUE")).equals("")){
                    ap.setInitialValue(Integer.parseInt(rs.getString("INITIAL_VALUE")));
                }
                
                if(!(rs.getString("CURRENT_VALUE")).equals("")){
                    ap.setCurrentValue(Integer.parseInt(rs.getString("CURRENT_VALUE")));
                }
                
                if(!(rs.getString("USER_NAME").equals(""))){
                    ap.setUserName(rs.getString("USER_NAME"));
                }
                
                if(!(rs.getString("INITIAL_DATE").equals(""))){
                    ap.setInitialDate(rs.getString("INITIAL_DATE"));
                }
                
                if(!(rs.getString("PRODUCT_IMAGE").equals(""))){
                    ap.setProductImage(rs.getString("PRODUCT_IMAGE"));
                }
                
                if(!(rs.getString("HAS_STARTED")).equals("")){
                    ap.setHasStarted(Boolean.parseBoolean(rs.getString("HAS_STARTED")));
                }
                if(!(rs.getString("HAS_FINISHED")).equals("")){
                    ap.setHasFinished(Boolean.parseBoolean(rs.getString("HAS_FINISHED")));
                }
                if(rs.getString("EXPIRATION_TIME") != null && !(rs.getString("EXPIRATION_TIME")).equals("")){
                    ap.setExpirationTime(Integer.parseInt(rs.getString("EXPIRATION_TIME")));
                }
                
                list.add(ap);
            }
            
            return list;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }
    
    public static AuctionProduct getProduct(int ID) throws ClassNotFoundException, ParseException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        AuctionProduct ap = new AuctionProduct();
        List<AuctionProduct> list = new ArrayList();
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/leilao; create=true;user=root;password=root");
            Statement s = conn.createStatement();
            s.execute("SELECT * FROM PRODUCT WHERE ID = \'" + ID + "\'");
            ResultSet rs = s.getResultSet();
            
            while(rs.next()){
                ap = new AuctionProduct();
                
                if(!(rs.getString("ID").equals(""))){
                    ap.setId(Integer.parseInt(rs.getString("ID")));
                }
                
                if(!(rs.getString("NAME").equals(""))){
                    ap.setName(rs.getString("NAME"));
                }
                
                if(!(rs.getString("DESCRIPTION").equals(""))){
                    ap.setDescription(rs.getString("DESCRIPTION"));
                }
                
                if(!(rs.getString("INITIAL_VALUE")).equals("")){
                    ap.setInitialValue(Integer.parseInt(rs.getString("INITIAL_VALUE")));
                }
                
                if(!(rs.getString("CURRENT_VALUE")).equals("")){
                    ap.setCurrentValue(Integer.parseInt(rs.getString("CURRENT_VALUE")));
                }
                
                if(!(rs.getString("USER_NAME").equals(""))){
                    ap.setUserName(rs.getString("USER_NAME"));
                }
                
                if(!(rs.getString("INITIAL_DATE").equals(""))){
                    ap.setInitialDate(rs.getString("INITIAL_DATE"));
                }
                
                if(!(rs.getString("PRODUCT_IMAGE").equals(""))){
                    ap.setProductImage(rs.getString("PRODUCT_IMAGE"));
                }
                
                if(!(rs.getString("HAS_STARTED")).equals("")){
                    ap.setHasStarted(Boolean.parseBoolean(rs.getString("HAS_STARTED")));
                }
                if(!(rs.getString("HAS_FINISHED")).equals("")){
                    ap.setHasFinished(Boolean.parseBoolean(rs.getString("HAS_FINISHED")));
                }
                if(rs.getString("EXPIRATION_TIME") != null && !(rs.getString("EXPIRATION_TIME")).equals("")){
                    ap.setExpirationTime(Integer.parseInt(rs.getString("EXPIRATION_TIME")));
                }
                
                list.add(ap);
            }
            return list.get(0);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void removeProduct(int ID) throws ClassNotFoundException{
      Class.forName("org.apache.derby.jdbc.ClientDriver");
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/leilao; create=true;user=root;password=root");
              PreparedStatement ps = conn.prepareStatement("DELETE FROM PRODUCT WHERE ID = \'" + ID + "\'");
              ps.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
