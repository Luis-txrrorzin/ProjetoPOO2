package br.ulbra.model;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class GerenciadorConexao {
   private static final String URL = "jdbc:mysql://localhost:3306/dbprojeto";
   private static final String USER = "root";
   private static final String PASSWORD = "admin";
   private static GerenciadorConexao instancia;
   private Connection conexao;
   private GerenciadorConexao()
   {
    try {
        conexao = DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) {
    JOptionPane.showMessageDialog(null, e.getMessage().toString());
    }
}

public static synchronized GerenciadorConexao getInstancia() {
       try {
           if ((instancia == null) || (instancia.conexao.isClosed())) {
               instancia = new GerenciadorConexao();
           }  } catch (SQLException ex) {
           Logger.getLogger(GerenciadorConexao.class.getName()).log(Level.SEVERE, null, ex);
       }
    return instancia;
}
    public Connection getConexao() {
        return conexao;
    }
    
    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        
        try {
        
            if (stmt != null) {
                stmt.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
    
        closeConnection(con, stmt);
        
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    

