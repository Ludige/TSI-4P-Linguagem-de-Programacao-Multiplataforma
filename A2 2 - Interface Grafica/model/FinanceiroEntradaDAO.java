package ultrabusinessmegatop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ultrabusinessmegatop.controller.FinanceiroEntrada;

/**
 *
 * @author luigg
 */
public class FinanceiroEntradaDAO {
    
    public static void create(FinanceiroEntrada fe) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO financeiro_entrada (forma_recebimento,valor,data_baixa,data_vencimento, data_emissao, fk_venda) VALUES (?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        stm.setString(1, fe.getFormaRecebimento());
        stm.setDouble(2, fe.getValor());
        stm.setString(3, fe.getDataBaixa());
        stm.setString(4, fe.getDataVencimento());
        stm.setString(5, fe.getDataEmissao());
        stm.setInt(6, fe.getVenda().getPk_venda());
        stm.execute();

        ResultSet pks = stm.getGeneratedKeys();//recupera as chaves primárias geradas, nese caso apenas uma
        pks.next();//vai para a primeira posição válida
        fe.setPk_financeiro(pks.getInt(1));//recuperando a pk que acabaou de ser gerada

        stm.close();
    }

    public static FinanceiroEntrada retreave(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();

        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM financeiro_entrada where pk_financeiro=" + pk);
        rs.next();
        return new FinanceiroEntrada(
                rs.getString("forma_recebimento"),
                rs.getDouble("valor"),
                rs.getString("data_baixa"),
                rs.getString("data_vencimento"),
                rs.getString("data_emissao"),
                VendaDAO.retreave(rs.getInt("pk_venda")),
                rs.getInt("pk_financeiro")
        );

    }

    
    public static ArrayList<FinanceiroEntrada> retreaveAll() throws SQLException {
        Connection conn = BancoDados.createConnection();

        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM financeiro_entrada ORDER BY data_emissao");

        ArrayList<FinanceiroEntrada> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(new FinanceiroEntrada(
                rs.getString("forma_recebimento"),
                rs.getDouble("valor"),
                rs.getString("data_baixa"),
                rs.getString("data_vencimento"),
                rs.getString("data_emissao"),
                VendaDAO.retreave(rs.getInt("pk_venda")),
                rs.getInt("pk_financeiro")));
        }
        return aux;
    }
    
    public static ArrayList<FinanceiroEntrada> retreaveAll(int fk) throws SQLException {
        Connection conn = BancoDados.createConnection();

        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM financeiro_entrada where fk_venda =" + fk + " order by pk_financeiro");

        ArrayList<FinanceiroEntrada> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(new FinanceiroEntrada(
                rs.getString("forma_recebimento"),
                rs.getDouble("valor"),
                rs.getString("data_baixa"),
                rs.getString("data_vencimento"),
                rs.getString("data_emissao"),
                VendaDAO.retreave(rs.getInt("pk_venda")),
                rs.getInt("pk_financeiro")));
        }
        return aux;
    }


    public static void update(FinanceiroEntrada fe) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement("UPDATE financeiro_entrada SET "
                + "foma_recebimento = ?,"
                + "valor = ?,"
                + "data_baixa = ?,"
                + "data_vencimento = ?,"
                + "data_emissao = ?,"
                + "fk_venda = ?"
                + "WHERE pk_financeiro = ?");
        stm.setString(1, fe.getFormaRecebimento());
        stm.setDouble(2, fe.getValor());
        stm.setString(3, fe.getDataBaixa());
        stm.setString(4, fe.getDataVencimento());        
        stm.setString(5, fe.getDataEmissao());        
        stm.setInt(6, fe.getVenda().getPk_venda());        
        stm.setInt(7, fe.getPk_financeiro());
                                
        stm.execute();
        
        stm.close();
    }

    public static void delete(FinanceiroEntrada fe) throws SQLException {
        delete(fe.getPk_financeiro());
    }

    public static void delete(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();
        conn.createStatement().execute("DELETE FROM financeiro_entrada WHERE pk_financeiro = " + pk);
    }

}
