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

public class TreinoDAO {
    private GerenciadorConexao gerenciador;

    public TreinoDAO() {
        this.gerenciador = GerenciadorConexao.getInstancia();
    }

    public boolean adicionarTreino(String nome, String descricao, String data, int ativo) {
        String sql = "INSERT INTO tbtreinos (nomeTre, descricaoTre, dataTre, ativoTre) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.setString(3, data);
            stmt.setInt(4, ativo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Treino: " + nome + " inserido com sucesso!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }
        return false;
    }

    public List<Treino> listarTreinos() {
        String sql = "SELECT * FROM tbtreinos";
        List<Treino> treinos = new ArrayList<>();

        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Treino treino = new Treino();

                treino.setTreino_pk(rs.getInt("treino_pk"));
                treino.setNomeTre(rs.getString("nomeTre"));
                treino.setDescricaoTre(rs.getString("descricaoTre"));
                treino.setDataTre(rs.getString("dataTre"));
                treino.setAtivoTre(rs.getInt("ativoTre"));

                treinos.add(treino);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);
        }

        return treinos;
    }

    public Treino buscarTreinoPorPK(int pk) {
        String sql = "SELECT * FROM tbtreinos WHERE treino_pk = ?";
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
                treino.setNomeTre(rs.getString("nomeTre"));
                treino.setDescricaoTre(rs.getString("descricaoTre"));
                treino.setDataTre(rs.getString("dataTre"));
                treino.setAtivoTre(rs.getInt("ativoTre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);
        }

        return treino;
    }

    public boolean atualizarTreino(Treino t) {
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tbtreinos SET nomeTre = ?, descricaoTre = ?, dataTre = ?, ativoTre = ? WHERE treino_pk = ?");
            stmt.setString(1, t.getNomeTre());
            stmt.setString(2, t.getDescricaoTre());
            stmt.setString(3, t.getDataTre());
            stmt.setInt(4, t.getAtivoTre());
            stmt.setInt(5, t.getTreino_pk());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Treino atualizado com sucesso!");
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            GerenciadorConexao.closeConnection(con, stmt);
        }
        return false;
    }

    public boolean excluirTreino(int treinoPk) {
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tbtreinos WHERE treino_pk = ?");
            stmt.setInt(1, treinoPk);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Treino excluído com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            GerenciadorConexao.closeConnection(con, stmt);
        }
        return false;
    }

    public List<Treino> readForDesc(String desc) {
    String sql = "SELECT * FROM tbtreinos WHERE nomeTre like ?";
    Connection con = gerenciador.getConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Treino> treinos = new ArrayList<>();

    try {
        stmt = con.prepareStatement(sql);
        stmt.setString(1, "%" + desc + "%");
        rs = stmt.executeQuery();

        while (rs.next()) {
            Treino treino = new Treino();
            treino.setTreino_pk(rs.getInt("treino_pk"));
            treino.setNomeTre(rs.getString("nomeTre"));
            treino.setDescricaoTre(rs.getString("descricaoTre"));
            treino.setDataTre(rs.getString("dataTre"));
            treino.setAtivoTre(rs.getInt("ativoTre"));
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
         String sql = "SELECT * FROM tbusuario WHERE usuario_pk = ?";
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
        treino.setNomeTre(rs.getString("nomeTre"));
        treino.setDescricaoTre(rs.getString("descricaoTre"));
        treino.setDataTre(rs.getString("dataTre"));
        treino.setAtivoTre(rs.getInt("ativoTre"));
    }
    
    } catch (SQLException ex) {
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        GerenciadorConexao.closeConnection(con, stmt, rs);
    }
    
    return treino;
    }
    

    public boolean alterarTreino(Treino t) {
    Connection con = gerenciador.getConexao();
    PreparedStatement stmt = null;

    try {
        stmt = con.prepareStatement("UPDATE tbtreino SET nomeTre = ?, descricaoTre = ?, dataTreino = ?, ativoTre = ? WHERE treino_pk = ?");

        stmt.setString(1, t.getNomeTre());
        stmt.setString(2, t.getDescricaoTre());
        stmt.setString(3, t.getDataTre());
        stmt.setInt(4, t.getAtivoTre());
        stmt.setInt(5, t.getTreino_pk());

        int rowsUpdated = stmt.executeUpdate();

        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum registro atualizado.");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
    } finally {
        GerenciadorConexao.closeConnection(con, stmt);
    }
    return false;
}
    
        public boolean excluirUsuario(int treino_pk) {
           Connection con = gerenciador.getConexao();
           PreparedStatement stmt = null;

           try {
               stmt = con.prepareStatement("DELETE FROM tbusuario "
                       + "WHERE treino_pk = ?");
               stmt.setInt(1, treino_pk);

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

