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


public class CarroDAO {
    
    private PreparedStatement stmtCreate;
    private PreparedStatement stmtRead;
    private PreparedStatement stmtReadById;
    private PreparedStatement stmtUpdate;
    private PreparedStatement stmtDelete;
    
    
    private static CarroDAO instance;
    
    public static CarroDAO getInstance() {
        if(instance == null) {
            instance = new CarroDAO();
        }
        return instance;
    }
    
    public CarroDAO() {
        try {
            Connection con = ConexaoPS2.getConnection();
            
            stmtCreate = con.prepareStatement("INSERT INTO carro(modelo, marca, ano, categoria, placa) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmtRead = con.prepareStatement("SELECT * FROM carro");
            stmtReadById = con.prepareStatement("SELECT * FROM carro WHERE idCarro=?");
            stmtUpdate = con.prepareStatement("UPDATE carro SET modelo=?, marca=?, ano=?, categoria=?, placa=? WHERE idCarro=?");
            stmtDelete = con.prepareStatement("DELETE FROM carro WHERE idCarro=?");
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public Carro create(Carro c) {
        try{
            stmtCreate.setString(1, c.getModelo());
            stmtCreate.setString(2, c.getMarca());
            stmtCreate.setInt(3, c.getAno());
            stmtCreate.setString(4, c.getCategoria());
            stmtCreate.setString(5, c.getPlaca());
            
            
            stmtCreate.executeUpdate();
            ResultSet rs = stmtCreate.getGeneratedKeys();
            
            if(rs.next()) {
                long idCarro = rs.getLong(1);
                c.setIdCarro(idCarro);
                return c;
            }
            
            return null;
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Carro> read() {
        try {
            ResultSet rs = stmtRead.executeQuery();
            List<Carro> resultado = new ArrayList();
            
            while(rs.next()) {
                Carro c = new Carro();
                c.setIdCarro(rs.getLong("idCarro"));
                c.setModelo(rs.getString("modelo"));
                c.setMarca(rs.getString("marca"));
                c.setAno(rs.getInt("ano"));
                c.setCategoria(rs.getString("categoria"));
                c.setPlaca(rs.getString("placa"));
               
                resultado.add(c);
            }
            
            return resultado;
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Carro readOne(long idCarro) {
        try {
            stmtReadById.setLong(1, idCarro);
            ResultSet rs = stmtReadById.executeQuery();
            
            if(rs.next()) {
                Carro c = new Carro();
                c.setIdCarro(rs.getLong("idCarro"));
                c.setModelo(rs.getString("modelo"));
                c.setMarca(rs.getString("marca"));
                c.setAno(rs.getInt("ano"));
                c.setCategoria(rs.getString("categoria"));
                c.setPlaca(rs.getString("placa"));
                return c;
            }
            return null;
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void update(Carro c) { 
        try{
            stmtUpdate.setString(1, c.getModelo());
            stmtUpdate.setLong(2, c.getIdCarro());
            stmtUpdate.setString(3, c.getMarca());
            stmtUpdate.setInt(4, c.getAno());
            stmtUpdate.setString(5, c.getCategoria());
            stmtUpdate.setString(6, c.getPlaca());
            stmtUpdate.executeUpdate();
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(long idCarro) { 
        try{
            stmtDelete.setLong(1, idCarro);
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
