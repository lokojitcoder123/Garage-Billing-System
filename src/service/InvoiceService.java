package service;

import config.Dbconfig;
import entity.Invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InvoiceService {
    public void addInvoice(Invoice invoice) throws SQLException {
        String sql = "INSERT INTO invoices (customer_id, vehicle_id, service_id) VALUES (?, ?, ?)";
        try (Connection conn = Dbconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invoice.getCustomerId());
            ps.setInt(2, invoice.getVehicleId());
            ps.setInt(3, invoice.getServiceId());
            ps.executeUpdate();
        }
    }

    public List<Invoice> getAllInvoices() throws SQLException {
        List<Invoice> list = new ArrayList<>();
        try (Connection conn = Dbconfig.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * from invoices")) {
            while (rs.next()) {
                list.add(new Invoice(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getInt("vehicle_id"),
                        rs.getInt("service_id")));
            }
        }
        return list;
    }

    }
