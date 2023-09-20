
package br.ulbra.model;

public class Treino {
    private int treino_pk;
    private String nomeTre;
    private String descricaoTre;
    private String dataTre;
    private int ativoTre;

    public int getTreino_pk() {
        return treino_pk;
    }

    public void setTreino_pk(int treino_pk) {
        this.treino_pk = treino_pk;
    }

    public String getNomeTre() {
        return nomeTre;
    }

    public void setNomeTre(String nomeTre) {
        this.nomeTre = nomeTre;
    }

    public String getDescricaoTre() {
        return descricaoTre;
    }

    public void setDescricaoTre(String descricaoTre) {
        this.descricaoTre = descricaoTre;
    }

    public String getDataTre() {
        return dataTre;
    }

    public void setDataTre(String dataTre) {
        this.dataTre = dataTre;
    }

    public int getAtivoTre() {
        return ativoTre;
    }

    public void setAtivoTre(int ativoTre) {
        this.ativoTre = ativoTre;
    }

    public String ativoToString() {
        if (this.ativoTre == 1)
            return "Ativo";
        else
            return "Inativo";
    }

    @Override
    public String toString() {
        return "Treino{" + "treino_pk=" + treino_pk + ", nomeTre=" + nomeTre 
                + ", descricaoTre=" + descricaoTre + ", dataTre=" + dataTre 
                + ", ativoTre=" + ativoTre + '}';
    }

    public Object getCodigo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
