
package ultrabusinessmegatop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ultrabusinessmegatop.controller.Endereco;
import ultrabusinessmegatop.controller.VendaItem;

/**
 *
 * @author luigg
 */
public class VendaItemDAO {
       
    public static void create(VendaItem vi) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO venda_item ("
                + "qtd,"
                + "valor_unitario,"
                + "fk_produto,"
                + "fk_venda) VALUES (?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        stm.setInt(1, vi.getQtd());
        stm.setDouble(2, vi.getValorUnitario());
        stm.setInt(3, vi.getProduto().getPkProduto());
        stm.setInt(4, vi.getVenda().getPk_venda());
        stm.execute();

        ResultSet pks = stm.getGeneratedKeys();//recupera as chaves primárias geradas, nese caso apenas uma
        pks.next();//vai para a primeira posição válida
        vi.setPk_item(pks.getInt(1));//recuperando a pk que acabaou de ser gerada

        stm.close();
    }

    public static VendaItem retreave(int pk) throws SQLException {

        Connection conn = BancoDados.createConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM venda_item WHERE pk_item = " + pk);

        rs.next();

        return new VendaItem(
                rs.getInt("pk_item"),
                VendaDAO.retreave(rs.getInt("pk_venda")),
                ProdutoDAO.retreave(rs.getInt("pk_produto")),
                rs.getInt("qtd"),
                rs.getDouble("valor_unitario")
                
        );
    }

    public static ArrayList<VendaItem> retreaveAll() throws SQLException {
        Connection conn = BancoDados.createConnection();

        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM venda_item ORDER BY valor_unitario");

        ArrayList<VendaItem> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(new VendaItem(
                rs.getInt("pk_item"),
                VendaDAO.retreave(rs.getInt("pk_venda")),
                ProdutoDAO.retreave(rs.getInt("pk_produto")),
                rs.getInt("qtd"),
                rs.getDouble("valor_unitario")));
        }

        return aux;
    }

    public static ArrayList<VendaItem> retreaveAll(int fk) throws SQLException {
        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM venda_item where fk_venda =" + fk + " order by pk_item");

        ArrayList<VendaItem> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(new VendaItem(
                rs.getInt("pk_item"),
                VendaDAO.retreave(rs.getInt("pk_venda")),
                ProdutoDAO.retreave(rs.getInt("pk_produto")),
                rs.getInt("qtd"),
                rs.getDouble("valor_unitario")));
        }

        return aux;

    }

    public static void update(VendaItem vi) throws SQLException {

        if (vi.getStatus() == Endereco.ALTERADO) {

            Connection conn = BancoDados.createConnection();

            PreparedStatement stm = conn.prepareStatement(
                    "UPDATE venda_item SET "
                    + "fk_venda = ?,"
                    + "fk_produto=?,"
                    + "qtd=?,"
                    + "valor_unitario=? WHERE pk_item = ?");

            stm.setInt(1, vi.getVenda().getPk_venda());
            stm.setInt(2, vi.getProduto().getPkProduto());
            stm.setInt(3, vi.getQtd());
            stm.setDouble(4, vi.getValorUnitario());
            stm.setInt(5, vi.getPk_item());

            stm.execute();
            stm.close();
            
            vi.resetStatus();//após efetivar as alterações, é necessário marcar o objeto como inalterado
        }
    }

    public static void delete(VendaItem vi) throws SQLException {
        delete(vi.getPk_item());
    }

    public static void delete(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();
        conn.createStatement().execute("DELETE FROM venda_item WHERE pk_item = " + pk);
    }

}
