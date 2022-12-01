
package ultrabusinessmegatop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ultrabusinessmegatop.controller.Fornecedor;

/**
 *
 * @author luigg
 */
public class FornecedorDAO {
       public static void create(Fornecedor f) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                        "INSERT INTO fornecedor (nome,cpf) VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS
                        );
        stm.setString(1, f.getNome());
        stm.setString(2, f.getCpf());
        stm.execute();
        
        ResultSet pks = stm.getGeneratedKeys();//recupera as chaves primárias geradas, nese caso apenas uma
        pks.next();//vai para a primeira posição válida
        f.setPk_fornecedor(pks.getInt(1));//recuperando a pk que acabaou de ser gerada

        stm.close();
    }
    
    public static Fornecedor retreave(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fornecedor WHERE pk_cargo = " + pk);
        
        rs.next();
        
        Fornecedor f = new Fornecedor(rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getInt("pk_cargo")
        );
        
        return f;    
    }
    

    /**
     * Retornar todos os cargos armazenados no banco de dados
     * @return 
     */
    public static ArrayList<Fornecedor> retreaveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fornecedor ORDER BY nome");
        
        ArrayList<Fornecedor> aux = new ArrayList<>();
        
        while(rs.next()){                        
            aux.add(new Fornecedor(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getInt("pk_fornecedor")));            
        }
        return aux;
    }
    
    public static void update(Fornecedor c) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement("UPDATE fornecedor SET nome = ?, cpf=? WHERE pk_fornecedor = ?");
        stm.setString(1, c.getNome());
        stm.setString(2, c.getCpf());
        stm.setInt(3, c.getPk_fornecedor());
        
        stm.execute();
        stm.close();
    }
    
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();       
        conn.createStatement().execute("DELETE FROM fornecedor WHERE pk_fornecedor = " + pk);
    }
    
    public static void delete(Fornecedor f) throws SQLException{
        delete(f.getPk_fornecedor());
    }
    
}
