package ultrabusinessmegatop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ultrabusinessmegatop.controller.Cliente;

/**
 *
 * @author luigg
 */
public class ClienteDAO {
        public static void create(Cliente c) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                        "INSERT INTO cliente (nome, cpf) VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS
                        );
        stm.setString(1, c.getNome());
        stm.setString(2, c.getCpf());
        stm.execute();
        
        ResultSet pks = stm.getGeneratedKeys();//recupera as chaves primárias geradas, nese caso apenas uma
        pks.next();//vai para a primeira posição válida
        c.setPk_cliente(pks.getInt(1));//recuperando a pk que acabaou de ser gerada

        stm.close();
    }
        
    public static ArrayList<Cliente> retreaveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cliente ORDER BY nome");
        
        ArrayList<Cliente> aux = new ArrayList<>();
        
        while(rs.next()){                        
            aux.add(new Cliente(rs.getString("nome"),
                              rs.getString("cpf"),
                    //ClienteEnderecoDAO.retreaveAll(pk)
                              rs.getInt("pk_cliente")));            
        }
        return aux;
    }
    
    public static void update(Cliente c) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement("UPDATE cliente SET nome = ?, cpf = ? WHERE pk_cliente = ?");
        stm.setString(1, c.getNome());
        stm.setString(2, c.getCpf());
        stm.setInt(3, c.getPk_cliente());
        
        stm.execute();
        stm.close();
    }
    
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();       
        conn.createStatement().execute("DELETE FROM cliente WHERE pk_cliente = " + pk);
    }
    
    public static void delete(Cliente c) throws SQLException{
        delete(c.getPk_cliente());
    }
    
}

