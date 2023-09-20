package br.ulbra.controller;

import br.ulbra.model.Treino;
import br.ulbra.model.TreinoDAO;
import java.util.List;
import javax.swing.JOptionPane;

public class TreinoController {
    private TreinoDAO treinoDAO;

    public TreinoController() {
        treinoDAO = new TreinoDAO();
    }

    public boolean adicionarTreino(String nome, String descricao, String data, int ativo) {
        return treinoDAO.adicionarTreino(nome, descricao, data, ativo);
    }

    public List<Treino> readForDesc(String desc) {
        return treinoDAO.readForDesc(desc);
    }

    public Treino readForPk(int pk) {
        return treinoDAO.readForPk(pk);
    }

    public boolean alterarTreino(Treino t) {
        return treinoDAO.alterarTreino(t);
    }

    public boolean excluirTreino(int pkTreino) {
        return treinoDAO.excluirTreino(pkTreino);
    }

}
