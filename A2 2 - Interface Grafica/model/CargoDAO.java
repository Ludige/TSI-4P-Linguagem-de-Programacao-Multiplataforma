/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultrabusinessmegatop.model;

import ultrabusinessmegatop.controller.Cargo;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author lcarl
 */
public class CargoDAO {
    /**
     * insere uma linha na tabela cargo realizando o mor de Cargo
     * @param 
     * @throws SQLException 
     */    
    public static void create(Cargo c) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                        "INSERT INTO cargo (nome,descricao) VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS
                        );
        stm.setString(1, c.getNome());
        stm.setString(2, c.getDescricao());
        stm.execute();
        
        ResultSet pks = stm.getGeneratedKeys();//recupera as chaves primárias geradas, nese caso apenas uma
        pks.next();//vai para a primeira posição válida
        c.setPk_cargo(pks.getInt(1));//recuperando a pk que acabaou de ser gerada

        stm.close();
    }
    
    public static Cargo retreave(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cargo WHERE pk_cargo = " + pk);
        
        rs.next();
        
        Cargo c = new Cargo(rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getInt("pk_cargo")
        );
        
        return c;    
    }
    

    /**
     * Retornar todos os cargos armazenados no banco de dados
     * @return 
     */
    public static ArrayList<Cargo> retreaveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cargo ORDER BY nome");
        
        ArrayList<Cargo> aux = new ArrayList<>();
        
        while(rs.next()){                        
            aux.add(new Cargo(rs.getString("nome"),
                              rs.getString("descricao"),
                              rs.getInt("pk_cargo")));            
        }
        return aux;
    }
    
    public static void update(Cargo c) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement("UPDATE cargo SET nome = ?, descricao=? WHERE pk_cargo = ?");
        stm.setString(1, c.getNome());
        stm.setString(2, c.getDescricao());
        stm.setInt(3, c.getPk_cargo());
        
        stm.execute();
        stm.close();
    }
    
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();       
        conn.createStatement().execute("DELETE FROM cargo WHERE pk_cargo = " + pk);
    }
    
    public static void delete(Cargo c) throws SQLException{
        delete(c.getPk_cargo());
    }
    
}
