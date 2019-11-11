/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoCarro;

/**
 *
 * @author Nicole
 */
public class Carro {
    
    private long idCarro;
    private String modelo;
    private String marca;
    private int ano;
    private String categoria;
    private String placa;
    
    public Carro(){
        
    }
    
    public Carro(long idCarro, String mo, String ma, int a, String c, String p){
        this.idCarro = idCarro;
        modelo = mo;
        marca = ma;
        ano = a;
        categoria = c;
        placa = p;
    }

    public long getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(long idCarro) {
        this.idCarro = idCarro;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
}
