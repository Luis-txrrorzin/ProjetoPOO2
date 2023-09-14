package br.ulbra.model;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;


public class UsuarioDAO {
    private GerenciadorConexao gerenciador;
    public UsuarioDAO(){
        this.gerenciador = GerenciadorConexao.getInstancia();
    }

    public boolean autenticar(String email, String senha) {
    String sql = "SELECT * FROM TBUsuario WHERE emailUsu = ? AND senhaUsu = ? AND ativoUsu = 1";
    try {
        PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return true;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    return false;
    }
    
    public boolean adicionarUsuario(String nome, String email, String senha, String datan, int ativo) {
        String sql = "INSERT INTO TBUsuario (nomeUsu, emailUsu, senhaUsu, data_nascimentoUsu, ativoUsu)"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setString(4, datan);
            stmt.setInt(5, ativo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário: " + nome + "inserido com sucesso!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }  
            return false;
    }
    
    public List<Usuario> read() {
        String sql = "SELECT * FROM  TBUsuario";
        List<Usuario> usuarios = new ArrayList<>();
        
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Usuario usuario = new Usuario();
                
                usuario.setUsuario_pk(rs.getInt("pkusuario"));
                usuario.setNomeUsu(rs.getString("nomeusu"));
                usuario.setEmailUsu(rs.getString("emailusu"));
                usuario.setSenhaUsu(rs.getString("senhausu"));
                usuario.setData_nascimentoUsu(rs.getString("datanascusu"));
                usuario.setAtivoUsu(rs.getInt("ativousu"));
                
                usuarios.add(usuario);
            }
        }   catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                GerenciadorConexao.closeConnection(con, stmt, rs);
        }
        
        return usuarios;
    } 
    
    public List<Usuario> readForDesc(String desc) {
    String sql = "SELECT * FROM tbusuario WHERE nomeusu LIKE ?";
    GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
    Connection con = gerenciador.getConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Usuario> usuarios = new ArrayList<>();
    
    
    try {
    stmt = con.prepareStatement(sql);
    stmt.setString(1, "%"+desc+"%");
    
    rs = stmt.executeQuery();
    
    while (rs.next()) {
    
        Usuario usuario = new Usuario();
        
        usuario.setUsuario_pk(rs.getInt("pkusuario"));
        usuario.setNomeUsu(rs.getString("nomeusu"));
        usuario.setEmailUsu(rs.getString("emailusu"));
        usuario.setSenhaUsu(rs.getString("senhausu"));
        usuario.setData_nascimentoUsu(rs.getString("dtnascusu"));
        usuario.setAtivoUsu(rs.getInt("ativousu"));
        usuarios.add(usuario);
    }
    
    } catch (SQLException ex) {
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        GerenciadorConexao.closeConnection(con, stmt, rs);
    }
    
    return usuarios;
    }
}
