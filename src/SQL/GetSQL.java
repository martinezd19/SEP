/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class GetSQL {
    public Connection conn = null;
    private String server = null;
    private String username = null;
    private String password = null;
    public boolean connectionGood = false;
    public SQLException exMessage;
    
    public GetSQL(String server, String username, String password) {
        this.server = server;
        this.username = username;
        this.password = password;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {}
        try {
            conn = DriverManager.getConnection(server, username, password);
            connectionGood = true;
        } catch (SQLException ex) {
            // handle any errors
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
            exMessage = ex;
            connectionGood = false;
        }
    }
}
