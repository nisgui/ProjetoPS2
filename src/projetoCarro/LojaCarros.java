/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoCarro;

/**
 *
 * @author Milena
 */
public class LojaCarros {
    
    private long idLoja;
    private String nomeLoja;
    
    public LojaCarros(){}
    
    public LojaCarros(long idLoja, String nome){
        this.idLoja = idLoja;
        nomeLoja = nome;
      
        
        
    }

    public long getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(long idLoja) {
        this.idLoja = idLoja;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }
}
