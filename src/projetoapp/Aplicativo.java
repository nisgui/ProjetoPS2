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
public class Aplicativo {
    
    private long idApp;
    private String nomeApp;
    private int numDownloads;
    
    public Aplicativo(){}
    
    public Aplicativo(long idApp, String nome, int d){
        this.idApp = idApp;
        nomeApp = nome;
        numDownloads = d;
        
        
    }

    public long getIdApp() {
        return idApp;
    }

    public void setIdApp(long idApp) {
        this.idApp = idApp;
    }

    public String getNomeApp() {
        return nomeApp;
    }

    public void setNomeApp(String nomeApp) {
        this.nomeApp = nomeApp;
    }

    public int getNumDownloads() {
        return numDownloads;
    }

    public void setNumDownloads(int numDownloads) {
        this.numDownloads = numDownloads;
    }
    
}
