/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoapp;

import projetoapp.Aplicativo;
import Conexao.ConexaoPS2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AplicativoDAO {
    
     private PreparedStatement stmtCreate;
    private PreparedStatement stmtRead;
    private PreparedStatement stmtReadById;
    private PreparedStatement stmtUpdate;
    private PreparedStatement stmtDelete;
    
    
    private static AplicativoDAO instance;
    
    public static AplicativoDAO getInstance() {
        if(instance == null) {
            instance = new AplicativoDAO();
        }
        return instance;
    }
    
    public AplicativoDAO() {
        try {
            Connection con = ConexaoPS2.getConnection();
            
            stmtCreate = con.prepareStatement("INSERT INTO aplicativo(nomeApp, numDownloads) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            stmtRead = con.prepareStatement("SELECT * FROM aplicativo");
            stmtReadById = con.prepareStatement("SELECT * FROM aplicativo WHERE idApp=?");
            stmtUpdate = con.prepareStatement("UPDATE aplicativo SET nomeApp=? WHERE idApp=?");
            stmtDelete = con.prepareStatement("DELETE FROM aplicativo WHERE idApp=?");
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public Aplicativo create(Aplicativo a) {
        try{
            stmtCreate.setString(1, a.getNomeApp());
            stmtCreate.setInt(2, a.getNumDownloads());
            
            
            stmtCreate.executeUpdate();
            ResultSet rs = stmtCreate.getGeneratedKeys();
            
            if(rs.next()) {
                long idApp = rs.getLong(1);
                a.setIdApp(idApp);
                return a;
            }
            
            return null;
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Aplicativo> read() {
        try {
            ResultSet rs = stmtRead.executeQuery();
            List<Aplicativo> resultado = new ArrayList();
            
            while(rs.next()) {
                Aplicativo a = new Aplicativo();
                a.setIdApp(rs.getLong("idApp"));
                a.setNomeApp(rs.getString("nomeApp"));
                a.setNumDownloads(rs.getInt("numDownloads"));
               
                resultado.add(a);
            }
            
            return resultado;
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Aplicativo readOne(long idApp) {
        try {
            stmtReadById.setLong(1, idApp);
            ResultSet rs = stmtReadById.executeQuery();
            
            if(rs.next()) {
                Aplicativo a = new Aplicativo();
                a.setIdApp(rs.getLong("idApp"));
                a.setNomeApp(rs.getString("nomeApp"));
                a.setNumDownloads(rs.getInt("numDownloads"));
                return a;
            }
            return null;
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void update(Aplicativo a) { 
        try{
            stmtUpdate.setString(1, a.getNomeApp());
            stmtUpdate.setLong(2, a.getIdApp());
            stmtUpdate.setInt(3, a.getNumDownloads());
            stmtUpdate.executeUpdate();
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(long idApp) { 
        try{
            stmtDelete.setLong(1, idApp);
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