/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoapp;

import projetoapp.Desenvolvedor;
import Conexao.ConexaoPS2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DesenvolvedorDAO {
    
     private PreparedStatement stmtCreate;
    private PreparedStatement stmtRead;
    private PreparedStatement stmtReadById;
    private PreparedStatement stmtUpdate;
    private PreparedStatement stmtDelete;
    
    
    private static DesenvolvedorDAO instance;
    
    public static DesenvolvedorDAO getInstance() {
        if(instance == null) {
            instance = new DesenvolvedorDAO();
        }
        return instance;
    }
    
    public DesenvolvedorDAO() {
        try {
            Connection con = ConexaoPS2.getConnection();
            
            stmtCreate = con.prepareStatement("INSERT INTO desenvolvedor(nome) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            stmtRead = con.prepareStatement("SELECT * FROM desenvolvedor");
            stmtReadById = con.prepareStatement("SELECT * FROM desenvolvedor WHERE idDev=?");
            stmtUpdate = con.prepareStatement("UPDATE desenvolvedor SET nome=? WHERE idDev=?");
            stmtDelete = con.prepareStatement("DELETE FROM desenvolvedor WHERE idDev=?");
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public Desenvolvedor create(Desenvolvedor d) {
        try{
            stmtCreate.setString(1, d.getNome());
            
            stmtCreate.executeUpdate();
            ResultSet rs = stmtCreate.getGeneratedKeys();
            
            if(rs.next()) {
                long idDev = rs.getLong(1);
                d.setIdDev(idDev);
                return d;
            }
            
            return null;
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Desenvolvedor> read() {
        try {
            ResultSet rs = stmtRead.executeQuery();
            List<Desenvolvedor> resultado = new ArrayList();
            
            while(rs.next()) {
                Desenvolvedor d = new Desenvolvedor();
                d.setIdDev(rs.getLong("idDev"));
                d.setNome(rs.getString("nome"));
               
                resultado.add(d);
            }
            
            return resultado;
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Desenvolvedor readOne(long idDev) {
        try {
            stmtReadById.setLong(1, idDev);
            ResultSet rs = stmtReadById.executeQuery();
            
            if(rs.next()) {
                Desenvolvedor d = new Desenvolvedor();
                d.setIdDev(rs.getLong("idDev"));
                d.setNome(rs.getString("nome"));
                return d;
            }
            return null;
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void update(Desenvolvedor d) { 
        try{
            stmtUpdate.setString(1, d.getNome());
            stmtUpdate.setLong(2, d.getIdDev());
            stmtUpdate.executeUpdate();
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(long idDev) { 
        try{
            stmtDelete.setLong(1, idDev);
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
