package br.ulbra.model;

import br.ulbra.utils.Utils;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import javax.swing.Icon;


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
    
    
    
    public boolean adicionarUsuario(String nome, String email, String senha, String datan, int ativo, Icon icone) {
        String sql = "INSERT INTO TBUsuario (nomeUsu, emailUsu, senhaUsu, data_nascimentoUsu, ativoUsu, imagemUsu)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            byte[] iconBytes = Utils.iconToBytes(icone);
                
                
            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setString(4, datan);
            stmt.setInt(5, ativo);
            stmt.setBytes(6, iconBytes);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário: " + nome + "inserido com sucesso!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        } catch (IOException e) {
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
                
                usuario.setPk(rs.getInt("pkusuario"));
                usuario.setNome(rs.getString("nomeusu"));
                usuario.setEmail(rs.getString("emailusu"));
                usuario.setSenhaUsu(rs.getString("senhausu"));
                usuario.setData_nascimentoUsu(rs.getString("datanascusu"));
                usuario.setAtivo(rs.getInt("ativousu"));
                
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
        
        usuario.setPk(rs.getInt("usuario_pk"));
        usuario.setNome(rs.getString("nomeUsu"));
        usuario.setEmail(rs.getString("emailUsu"));
        usuario.setSenhaUsu(rs.getString("senhaUsu"));
        usuario.setData_nascimentoUsu(rs.getString("data_nascimentoUsu"));
        usuario.setAtivo(rs.getInt("ativoUsu"));
        usuarios.add(usuario);
    }
    
    } catch (SQLException ex) {
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        GerenciadorConexao.closeConnection(con, stmt, rs);
    }
    
    return usuarios;
    }
    
    public Usuario readForPk(int pk) {
    String sql = "SELECT * FROM tbusuario WHERE usuario_pk = ?";
    GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
    Connection con = gerenciador.getConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Usuario usuario = new Usuario();
    
    try {
    stmt = con.prepareStatement(sql);
    stmt.setInt(1, pk);
    
    rs = stmt.executeQuery();
    
    while (rs.next()) {
        usuario.setPk(rs.getInt("usuario_pk"));
        usuario.setNome(rs.getString("nomeUsu"));
        usuario.setEmail(rs.getString("emailUsu"));
        usuario.setSenhaUsu(rs.getString("senhaUsu"));
        usuario.setData_nascimentoUsu(rs.getString("data_nascimentoUsu"));
        usuario.setAtivo(rs.getInt("ativoUsu"));
    }
    
    } catch (SQLException ex) {
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        GerenciadorConexao.closeConnection(con, stmt, rs);
    }
    
    return usuario;
    }
    
    public boolean alterarUsuario (Usuario u) {
        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE tbusuario SET nomeusu = ?, " 
            + "emailUsu = ?, senhaUsu = ?, data_nascimentoUsu = ?, "
            + "ativoUsu = ? WHERE usuario_pk = ?");
            
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenhaUsu());
            stmt.setString(4, u.getData_nascimentoUsu());
            stmt.setInt(5, u.getAtivo());
            stmt.setInt(6, u.getPk());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            return true;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            GerenciadorConexao.closeConnection(con, stmt);
        }
            return false;
        }
    
        public boolean excluirUsuario(int pkUsuario) {
           GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
           Connection con = gerenciador.getConexao();
           PreparedStatement stmt = null;

           try {
               stmt = con.prepareStatement("DELETE FROM tbusuario "
                       + "WHERE usuario_pk = ?");
               stmt.setInt(1, pkUsuario);

               stmt.executeUpdate();

               JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
               return true;
           } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
           } finally {
               GerenciadorConexao.closeConnection(con, stmt);
           }
           return false;
       }
}

