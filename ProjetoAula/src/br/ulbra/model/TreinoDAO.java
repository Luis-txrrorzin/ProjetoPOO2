/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ulbra.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import br.ulbra.model.Treino;


/**
 *
 * @author beeme
 */
public class TreinoDAO {
    
    private GerenciadorConexao gerenciador;
    public TreinoDAO(){
        this.gerenciador = GerenciadorConexao.getInstancia();
    }
    
    public boolean adicionarTreino (String nome, String treino, int CodAluno, int ativo) {
        String sql = "INSERT INTO usuarioTreinos (nomeTreino, descTreino, codTreino, ativoTreino)"
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, treino);
            stmt.setInt(3, CodAluno);
            stmt.setInt(4, ativo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Treino: " + nome + "inserido com sucesso!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }  
            return false;
    }
    
    public List<Treino> readForDesc(String desc) {
    String sql = "SELECT * FROM usuarioTreinos WHERE nomeTreino LIKE ?";
    GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
    Connection con = gerenciador.getConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Treino> treinos = new ArrayList<>();
    
    
    try {
    stmt = con.prepareStatement(sql);
    stmt.setString(1, "%"+desc+"%");
    
    rs = stmt.executeQuery();
    
    while (rs.next()) {
    
        Treino treino = new Treino();
        
        treino.setTreino_pk(rs.getInt("codTreino"));
        treino.setNomeTreino(rs.getString("nomeTreino"));
        treino.setDescTreino(rs.getString("descTreino"));
        treino.setAtivoTreino(rs.getInt("ativoTreino"));
        treinos.add(treino);

    }
    
    
    } catch (SQLException ex) {
        Logger.getLogger(TreinoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        GerenciadorConexao.closeConnection(con, stmt, rs);
    }
    
    return treinos;
    }
    
    
    public Treino readForPk(int pk) {
    String sql = "SELECT * FROM usuarioTreinos WHERE codTreino = ?";
    GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
    Connection con = gerenciador.getConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Treino treino = new Treino();
    
    try {
    stmt = con.prepareStatement(sql);
    stmt.setInt(1, pk);
    
    rs = stmt.executeQuery();
    
    while (rs.next()) {
        treino.setTreino_pk(rs.getInt("treino_pk"));
        treino.setNomeTreino(rs.getString("nomeTreino"));
        treino.setDescTreino(rs.getString("descTreino"));
        treino.setAtivoTreino(rs.getInt("ativoTreino"));
    }
    
    } catch (SQLException ex) {
        Logger.getLogger(TreinoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        GerenciadorConexao.closeConnection(con, stmt, rs);
    }
        return treino;
    }
    
    public boolean excluirUsuario(int Treino_pk) {
           GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
           Connection con = gerenciador.getConexao();
           PreparedStatement stmt = null;

           try {
               stmt = con.prepareStatement("DELETE FROM usuarioTreinos "
                       + "WHERE treino_pk = ?");
               stmt.setInt(1, Treino_pk);

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
    
    public boolean alterarTreino (Treino t) {
        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE usuarioTreinos SET nomeTreino = ?, " 
            + "descTreino = ?, " + "ativoTreino = ? WHERE treino_pk = ?");
            
            stmt.setString(1, t.getNomeTreino());
            stmt.setString(2, t.getDescTreino());
            stmt.setInt(3, t.getAtivoTreino());
            stmt.setInt(4, t.getTreino_pk());
            
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
    
    }

