/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultrabusinessmegatop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ultrabusinessmegatop.controller.FinanceiroSaida;

/**
 *
 * @author luigg
 */
public class FinanceiroSaidaDAO {
    public static void create(FinanceiroSaida fs) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO financeiro_saida (forma_recebimento,valor,data_baixa,data_vencimento, data_emissao, fk_compra) VALUES (?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        stm.setString(1, fs.getFormaRecebimento());
        stm.setDouble(2, fs.getValor());
        stm.setString(3, fs.getDataBaixa());
        stm.setString(4, fs.getDataVencimento());
        stm.setString(5, fs.getDataEmissao());
        stm.setInt(6, fs.getCompra().getPk_compra());
        stm.execute();

        ResultSet pks = stm.getGeneratedKeys();//recupera as chaves primárias geradas, nese caso apenas uma
        pks.next();//vai para a primeira posição válida
        fs.setPk_financeiro(pks.getInt(1));//recuperando a pk que acabaou de ser gerada

        stm.close();
    }

    public static FinanceiroSaida retreave(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();

        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM financeiro_saida where pk_financeiro=" + pk);
        rs.next();
        return new FinanceiroSaida(
                rs.getString("forma_recebimento"),
                rs.getDouble("valor"),
                rs.getString("data_baixa"),
                rs.getString("data_vencimento"),
                rs.getString("data_emissao"),
                CompraDAO.retreave(rs.getInt("pk_compra")),
                rs.getInt("pk_financeiro")
        );

    }

    
    public static ArrayList<FinanceiroSaida> retreaveAll() throws SQLException {
        Connection conn = BancoDados.createConnection();

        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM financeiro_saida ORDER BY data_emissao");

        ArrayList<FinanceiroSaida> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(new FinanceiroSaida(
                rs.getString("forma_recebimento"),
                rs.getDouble("valor"),
                rs.getString("data_baixa"),
                rs.getString("data_vencimento"),
                rs.getString("data_emissao"),
                CompraDAO.retreave(rs.getInt("pk_compra")),
                rs.getInt("pk_financeiro")));
        }
        return aux;
    }
    
    public static ArrayList<FinanceiroSaida> retreaveAll(int fk) throws SQLException {
        Connection conn = BancoDados.createConnection();

        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM financeiro_saida where fk_compra =" + fk + " order by pk_financeiro");

        ArrayList<FinanceiroSaida> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(new FinanceiroSaida(
                rs.getString("forma_recebimento"),
                rs.getDouble("valor"),
                rs.getString("data_baixa"),
                rs.getString("data_vencimento"),
                rs.getString("data_emissao"),
                CompraDAO.retreave(rs.getInt("pk_venda")),
                rs.getInt("pk_financeiro")));
        }
        return aux;
    }


    public static void update(FinanceiroSaida fs) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement("UPDATE financeiro_saida SET "
                + "foma_recebimento = ?,"
                + "valor = ?,"
                + "data_baixa = ?,"
                + "data_vencimento = ?,"
                + "data_emissao = ?,"
                + "fk_compra = ?"
                + "WHERE pk_financeiro = ?");
        stm.setString(1, fs.getFormaRecebimento());
        stm.setDouble(2, fs.getValor());
        stm.setString(3, fs.getDataBaixa());
        stm.setString(4, fs.getDataVencimento());        
        stm.setString(5, fs.getDataEmissao());        
        stm.setInt(6, fs.getCompra().getPk_compra());        
        stm.setInt(7, fs.getPk_financeiro());
                                
        stm.execute();
        
        stm.close();
    }

    public static void delete(FinanceiroSaida fs) throws SQLException {
        delete(fs.getPk_financeiro());
    }

    public static void delete(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();
        conn.createStatement().execute("DELETE FROM financeiro_saida WHERE pk_financeiro = " + pk);
    }
}
