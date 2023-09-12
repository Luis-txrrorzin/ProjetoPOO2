
package br.ulbra.model;

public class Usuario {
    private int usuario_pk;
    private String nomeUsu;
    private String emailUsu;
    private String data_nascimentoUsu;
    private int ativoUsu;
    private String senhaUsu;

    public String getNomeUsu() {
        return nomeUsu;
    }

    public void setNomeUsu(String nomeUsu) {
        this.nomeUsu = nomeUsu;
    }
    
    
    public void setUsuario_pk(int usuario_pk) {
        this.usuario_pk = usuario_pk;
    }

    public String getEmailUsu() {
        return emailUsu;
    }

    public void setEmailUsu(String emailUsu) {
        this.emailUsu = emailUsu;
    }

    public String getData_nascimentoUsu() {
        return data_nascimentoUsu;
    }

    public void setData_nascimentoUsu(String data_nascimentoUsu) {
        this.data_nascimentoUsu = data_nascimentoUsu;
    }

    public int getAtivoUsu() {
        return ativoUsu;
    }

    public void setAtivoUsu(int ativoUsu) {
        this.ativoUsu = ativoUsu;
    }

    public String getSenhaUsu() {
        return senhaUsu;
    }

    public void setSenhaUsu(String senhaUsu) {
        this.senhaUsu = senhaUsu;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuario_pk=" + usuario_pk + ", nomeUsu=" + nomeUsu + ", emailUsu=" + emailUsu + ", data_nascimentoUsu=" + data_nascimentoUsu + ", ativoUsu=" + ativoUsu + ", senhaUsu=" + senhaUsu + '}';
    }

    
    
    
}
