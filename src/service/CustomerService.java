package service;

import config.Dbconfig;
import entity.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {


    public void addCustomer(Customer customers5) throws SQLException {
        Connection conn = Dbconfig.getConnection();
        PreparedStatement ps =
                conn.prepareStatement("INSERT INTO customers5 (name,phone) VALUES (?,?)");
        ps.setString(1, customers5.getName());
        ps.setString(2, customers5.getPhone());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> list = new ArrayList<>();
        Connection conn =Dbconfig.getConnection();
        Statement  st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from customers5");
        while(rs.next()){
            list.add(new Customer(rs.getInt("id"),
                   rs.getString("name"),rs.getString("phone")));
        }
        return list;
    }

}















