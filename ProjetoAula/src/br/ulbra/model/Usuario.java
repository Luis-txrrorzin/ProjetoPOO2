
package br.ulbra.model;

public class Usuario extends Pessoa {
    private String data_nascimentoUsu;
    private String senhaUsu;

    public String getData_nascimentoUsu() {
        return data_nascimentoUsu;
    }

    public void setData_nascimentoUsu(String data_nascimentoUsu) {
        this.data_nascimentoUsu = data_nascimentoUsu;
    }

    public String getSenhaUsu() {
        return senhaUsu;
    }

    public void setSenhaUsu(String senhaUsu) {
        this.senhaUsu = senhaUsu;
    }


    @Override
    public String toString() {
        return "Usuario{" + "usuario_pk=" + getPk() + ", nomeUsu=" + getNome() 
                + ", emailUsu=" + getEmail() + ", data_nascimentoUsu=" + data_nascimentoUsu 
                + ", ativoUsu=" + getAtivo() + ", senhaUsu=" + senhaUsu + '}';
    }

    
    
    
}
