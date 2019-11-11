/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoapp;

/**
 *
 * @author Nicole
 */
public class Desenvolvedor {
    private long idDev;
    private String nome;
    
    public Desenvolvedor(){}
    
    public Desenvolvedor(long idDev, String n){
        this.idDev = idDev;
        nome = n;
    }

    public long getIdDev() {
        return idDev;
    }

    public void setIdDev(long idDev) {
        this.idDev = idDev;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
    
    
    
}
