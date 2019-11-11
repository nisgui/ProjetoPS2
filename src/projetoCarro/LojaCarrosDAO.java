/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoCarro;


import Conexao.ConexaoPS2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class LojaCarrosDAO {
    
    private PreparedStatement stmtCreate;
    private PreparedStatement stmtRead;
    private PreparedStatement stmtReadById;
    private PreparedStatement stmtUpdate;
    private PreparedStatement stmtDelete;
    
    
    private static LojaCarrosDAO instance;
    
    public static LojaCarrosDAO getInstance() {
        if(instance == null) {
            instance = new LojaCarrosDAO();
        }
        return instance;
    }
    
    public LojaCarrosDAO() {
        try {
            Connection con = ConexaoPS2.getConnection();
            
            stmtCreate = con.prepareStatement("INSERT INTO lojacarros(nomeLoja) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            stmtRead = con.prepareStatement("SELECT * FROM lojacarros");
            stmtReadById = con.prepareStatement("SELECT * FROM lojacarros WHERE idLoja=?");
            stmtUpdate = con.prepareStatement("UPDATE lojacarros SET nomeLoja=? WHERE idLoja=?");
            stmtDelete = con.prepareStatement("DELETE FROM lojacarros WHERE idLoja=?");
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public LojaCarros create(LojaCarros l) {
        try{
            stmtCreate.setString(1, l.getNomeLoja());
            
            stmtCreate.executeUpdate();
            ResultSet rs = stmtCreate.getGeneratedKeys();
            
            if(rs.next()) {
                long idLoja = rs.getLong(1);
                l.setIdLoja(idLoja);
                return l;
            }
            
            return null;
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<LojaCarros> read() {
        try {
            ResultSet rs = stmtRead.executeQuery();
            List<LojaCarros> resultado = new ArrayList();
            
            while(rs.next()) {
                LojaCarros l = new LojaCarros();
                l.setIdLoja(rs.getLong("idLoja"));
                l.setNomeLoja(rs.getString("nomeLoja"));
               
                resultado.add(l);
            }
            
            return resultado;
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public LojaCarros readOne(long idLoja) {
        try {
            stmtReadById.setLong(1, idLoja);
            ResultSet rs = stmtReadById.executeQuery();
            
            if(rs.next()) {
                LojaCarros l = new LojaCarros();
                l.setIdLoja(rs.getLong("idLoja"));
                l.setNomeLoja(rs.getString("nomeLoja"));
                return l;
            }
            return null;
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void update(LojaCarros l) { 
        try{
            stmtUpdate.setString(1, l.getNomeLoja());
            stmtUpdate.setLong(2, l.getIdLoja());
            stmtUpdate.executeUpdate();
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(long idLoja) { 
        try{
            stmtDelete.setLong(1, idLoja);
            stmtDelete.executeUpdate();
            
        }catch(Exception e) {
            e.printStackTrace();
            
        }
        
      
                
    }
    public void close(){
        try{
            stmtCreate.close();
            stmtRead.close();
            stmtUpdate.close();
            stmtDelete.close();
            
        }catch(Exception e) {
            e.printStackTrace();
            
        }
    }
    
}
    

