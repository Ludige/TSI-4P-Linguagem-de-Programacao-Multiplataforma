package ultrabusinessmegatop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList; 
import ultrabusinessmegatop.controller.Venda;

/**
 *
 * @author luigg
 */
public class VendaDAO {
    public static void create(Venda v) throws SQLException {
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                        "INSERT INTO venda (numero,data,fk_cliente, fk_vendedor) VALUES (?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                        );
        stm.setInt(1, v.getNumero());
        stm.setString(2, v.getData());
        stm.setInt(3, v.getCliente().getPk_cliente());
        stm.setInt(4, v.getVendedor().getPk_fornecedor());
        stm.execute();
        
        ResultSet pks = stm.getGeneratedKeys();//recupera as chaves primárias geradas, nese caso apenas uma
        pks.next();//vai para a primeira posição válida
        v.setPk_venda(pks.getInt(1));//recuperando a pk que acabaou de ser gerada

        stm.close();
    }
    
    public static Venda retreave(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM venda WHERE pk_venda = " + pk);
        
        rs.next();
        
        Venda v = new Venda(
                rs.getInt("numero"),
                rs.getString("data"),
                ClienteDAO.retreave(rs.getInt("pk_cliente")),
                FornecedorDAO.retreave(rs.getInt("pk_fornecedor")),
                rs.getInt("pk_venda"),
                VendaItemDAO.retreaveAll(pk),
                FinanceiroEntradaDAO.retreaveAll(pk)
        );
        
        return v;    
    }

   
    public static ArrayList<Venda> retreaveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM venda ORDER BY data");
        
        ArrayList<Venda> aux = new ArrayList<>();
        
        while(rs.next()){                        
            aux.add(new Venda(
                rs.getInt("numero"),
                rs.getString("data"),
                ClienteDAO.retreave(rs.getInt("pk_cliente")),
                FornecedorDAO.retreave(rs.getInt("pk_fornecedor")),
                rs.getInt("pk_venda"),
                VendaItemDAO.retreaveAll(),
                FinanceiroEntradaDAO.retreaveAll()));        
        }
        return aux;
    }
    
    public static void update(Venda v) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement("UPDATE venda SET "
                + "data= ?,"
                + "numero=?,"
                + "fk_cliente,"
                + "fk_vendedor"
                + " WHERE pk_venda = ?");
        stm.setString(1, v.getData());
        stm.setInt(2, v.getNumero());
        stm.setInt(3, v.getCliente().getPk_cliente());
        stm.setInt(4, v.getVendedor().getPk_fornecedor());
        stm.setInt(5, v.getPk_venda());
        
        stm.execute();
        stm.close();
    }
    
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();       
        conn.createStatement().execute("DELETE FROM venda WHERE pk_venda = " + pk);
    }
    
    public static void delete(Venda v) throws SQLException{
        delete(v.getPk_venda());
    }
    
}

