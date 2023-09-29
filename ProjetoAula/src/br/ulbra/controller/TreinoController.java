package br.ulbra.controller;

import br.ulbra.model.Treino;
import br.ulbra.model.TreinoDAO;
import br.ulbra.model.Usuario;
import br.ulbra.model.UsuarioDAO;
import java.util.List;

public class TreinoController {
    private TreinoDAO treinoDAO;
    public TreinoController() {
        treinoDAO = new TreinoDAO();
    }
    
    public boolean adicionarTreino(String nome, String treino, int CodAluno, int ativo) {
            return treinoDAO.adicionarTreino(nome, treino, CodAluno, ativo);
    }
    
    public boolean alterarTreino(Treino t) {
            return treinoDAO.alterarTreino(t);
    }
    
    public List<Treino> readForDesc (String desc) {
        return treinoDAO.readForDesc(desc);
    }
    
    public boolean excluirUsuario(int pkUsuario) {
        return treinoDAO.excluirUsuario(pkUsuario);
    }
}
