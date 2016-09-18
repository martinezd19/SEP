/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author bmndo
 */
public class test {
    public static void main(String[] args) throws SQLException {
       GetSQL sql = new GetSQL("jdbc:mysql://127.0.0.1", "root", "");
       Statement stmt = sql.conn.createStatement();
       stmt.execute("USE test");
       stmt.executeUpdate("INSERT INTO test VALUES(TRUE)");
       stmt.executeUpdate("INSERT INTO test VALUES(FALSE)");
       ResultSet rs = stmt.executeQuery("SELECT * FROM test");
       rs.next();
       System.out.println(rs.getInt("id"));
       rs.next();
       System.out.println(rs.getInt("id"));
    }
}
