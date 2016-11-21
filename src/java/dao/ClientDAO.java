/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DBConnectionFactory;
import entity.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JohnMichael
 */
public class ClientDAO {
      public ArrayList<Client> getAllClients() {
        try {
            ArrayList<Client> clientList = new ArrayList();
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select * from client";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Client client = new Client();

                int clientID = rs.getInt("clientID");
                String clientName = rs.getString("clientName");
                String contactPerson = rs.getString("contactPerson");
                int contactNumber = rs.getInt("contactNumber");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int numberOfGuards = rs.getInt("numberOfGuards");
                String type = rs.getString("type");

                client.setClientID(clientID);
                client.setClientName(clientName);
                client.setContactPerson(contactPerson);
                client.setContactNumber(contactNumber);
                client.setEmail(email);
                client.setAddress(address);
                client.setNumberOfGuards(numberOfGuards);
                client.setType(type);

                clientList.add(client);
            }
            conn.close();
            return clientList;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
      
      public Client searchClient(int clientID) {
        try {
            ArrayList<Client> clientList = new ArrayList();
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select * from client where clientID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, clientID);
            ResultSet rs = pstmt.executeQuery();
            
            Client client = new Client();
            
            while (rs.next()) {
                
                
                String clientName = rs.getString("clientName");
                String contactPerson = rs.getString("contactPerson");
                int contactNumber = rs.getInt("contactNumber");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int numberOfGuards = rs.getInt("numberOfGuards");
                String type = rs.getString("type");

                
                client.setClientName(clientName);
                client.setContactPerson(contactPerson);
                client.setContactNumber(contactNumber);
                client.setEmail(email);
                client.setAddress(address);
                client.setNumberOfGuards(numberOfGuards);
                client.setType(type);

                
            }
            conn.close();
            return client;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}