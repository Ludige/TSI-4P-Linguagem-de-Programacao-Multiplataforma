
package ultrabusinessmegatop.model;

/**
 *
 * @author luigg
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ultrabusinessmegatop.controller.Endereco;

public class FornecedorEnderecoDAO {
    
    public static void create(Endereco e) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO fornecedor_endereco ("
                + "logradouro,"
                + "bairro,"
                + "cidade,"
                + "estado,"
                + "pais,"
                + "cep,"
                + "fk_fornecedor) VALUES (?,?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        stm.setString(1, e.getLogradouro());
        stm.setString(2, e.getBairro());
        stm.setString(3, e.getCidade());
        stm.setString(4, e.getEstado());
        stm.setString(5, e.getPais());
        stm.setString(6, e.getCep());
        stm.setInt(7, e.getFk());
        stm.execute();

        ResultSet pks = stm.getGeneratedKeys();//recupera as chaves primárias geradas, nese caso apenas uma
        pks.next();//vai para a primeira posição válida
        e.setPk(pks.getInt(1));//recuperando a pk que acabaou de ser gerada

        stm.close();
    }

    public static Endereco retreave(int pk) throws SQLException {

        Connection conn = BancoDados.createConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fornecedor_endereco WHERE pk_endereco = " + pk);

        rs.next();

        return new Endereco(rs.getString("logradouro"),
                rs.getString("bairro"),
                rs.getString("cidade"),
                rs.getString("estado"),
                rs.getString("pais"),
                rs.getString("cep"),
                rs.getInt("pk_endereco"),
                rs.getInt("fk_fornecedor")
        );
    }

    public static ArrayList<Endereco> retreaveAll() throws SQLException {
        Connection conn = BancoDados.createConnection();

        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fornecedor_endereco ORDER BY estado, cidade");

        ArrayList<Endereco> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(new Endereco(rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_fornecedor")));
        }

        return aux;
    }

    public static ArrayList<Endereco> retreaveAll(int fk) throws SQLException {
        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fornecedor_endereco where fk_fornecedor =" + fk + " order by pk_endereco");

        ArrayList<Endereco> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(new Endereco(rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_fornecedor")));
        }

        return aux;

    }

    public static void update(Endereco e) throws SQLException {

        if (e.getStatus() == Endereco.ALTERADO) {

            Connection conn = BancoDados.createConnection();

            PreparedStatement stm = conn.prepareStatement(
                    "UPDATE fornecedor_endereco SET "
                    + "logradouro = ?,"
                    + "bairro=?,"
                    + "cidade=?,"
                    + "estado=?,"
                    + "pais=?,"
                    + "cep=?,"
                    + "fk_fornecedor = ? WHERE pk_endereco = ?");

            stm.setString(1, e.getLogradouro());
            stm.setString(2, e.getBairro());
            stm.setString(3, e.getCidade());
            stm.setString(4, e.getEstado());
            stm.setString(5, e.getPais());
            stm.setString(6, e.getCep());
            stm.setInt(7, e.getFk());
            stm.setInt(8, e.getPk());

            stm.execute();
            stm.close();
            
            e.resetStatus();//após efetivar as alterações, é necessário marcar o objeto como inalterado
        }
    }

    public static void delete(Endereco e) throws SQLException {
        delete(e.getPk());
    }

    public static void delete(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();
        conn.createStatement().execute("DELETE FROM fornecedor_endereco WHERE pk_endereco = " + pk);
    }

    public static void deleteAll(int fk) throws SQLException {
        Connection conn = BancoDados.createConnection();
        conn.createStatement().execute("DELETE FROM fornecedor_endereco WHERE fk_funcionario = " + fk);
    }
}
