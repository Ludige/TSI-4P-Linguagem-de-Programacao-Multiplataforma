
package ultrabusinessmegatop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ultrabusinessmegatop.controller.Compra;

/**
 *
 * @author luigg
 */
public class CompraDAO {
    public static void create(Compra v) throws SQLException {
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                        "INSERT INTO compra (numero,data,fk_fornecedor) VALUES (?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                        );
        stm.setInt(1, v.getNumero());
        stm.setString(2, v.getData());
        stm.setInt(3, v.getFornecedor().getPk_fornecedor());
        stm.execute();
        
        ResultSet pks = stm.getGeneratedKeys();//recupera as chaves primárias geradas, nese caso apenas uma
        pks.next();//vai para a primeira posição válida
        v.setPk_compra(pks.getInt(1));//recuperando a pk que acabaou de ser gerada

        stm.close();
    }
    
    public static Compra retreave(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM compra WHERE pk_compra = " + pk);
        
        rs.next();
        
        Compra c = new Compra(
                rs.getInt("pk_compra"),
                FornecedorDAO.retreave(rs.getInt("pk_fornecedor")),
                rs.getInt("numero"),
                rs.getString("data"),
                CompraItemDAO.retreaveAll(pk),
                FinanceiroSaidaDAO.retreaveAll(pk)
        );
        
        return c;    
    }

   
    public static ArrayList<Compra> retreaveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM compra ORDER BY NOME");
        
        ArrayList<Compra> aux = new ArrayList<>();
        
        while(rs.next()){                        
            aux.add(new Compra(
                rs.getInt("pk_compra"),
                FornecedorDAO.retreave(rs.getInt("pk_fornecedor")),
                rs.getInt("numero"),
                rs.getString("data"),
                CompraItemDAO.retreaveAll(),
                FinanceiroSaidaDAO.retreaveAll()
            ));   
        }
        return aux;
    }
    
    public static void update(Compra c) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement("UPDATE CARGO SET "
                + "fk_fornecedor = ?, "
                + "numero=?,"
                + "data=? WHERE pk_compra = ?");
        stm.setInt(1, c.getFornecedor().getPk_fornecedor());
        stm.setInt(2, c.getNumero());
        stm.setString(3, c.getData());
        stm.setInt(3, c.getPk_compra());
        
        stm.execute();
        stm.close();
    }
    
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();       
        conn.createStatement().execute("DELETE FROM compra WHERE pk_compra = " + pk);
    }
    
    public static void delete(Compra c) throws SQLException{
        delete(c.getPk_compra());
    }
    
}

