
package ultrabusinessmegatop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ultrabusinessmegatop.controller.Funcionario;
import ultrabusinessmegatop.controller.Endereco;

/**
 *
 * @author luigg
 */
public class FuncionarioDAO {

    public static void create(Funcionario f) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO funcionario (nome, cpf, fk_cargo) VALUES (?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        stm.setString(1, f.getNome());
        stm.setString(2, f.getCpf());
        stm.setInt(3, f.getCargo().getPk_cargo());
        stm.execute();

        ResultSet pks = stm.getGeneratedKeys();//recupera as chaves primárias geradas, nese caso apenas uma
        pks.next();//vai para a primeira posição válida
        f.setPk_funcionario(pks.getInt(1));//recuperando a pk que acabaou de ser gerada

        for (Endereco e : f.getEnderecos()) {
            e.setFk(f.getPk_funcionario());//configurar a fk do endereço com a pk do cliente
            FuncionarioEnderecoDAO.create(e);
        }

        stm.close();
    }

    public static Funcionario retreave(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();

        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM funcionario where pk_funcionario=" + pk);
        rs.next();
        return new Funcionario(
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getInt("pk_funcionario"),
                FuncionarioEnderecoDAO.retreaveAll(pk),//retorna um vetor de enderecos do cliente pk
                CargoDAO.retreave(rs.getInt("pk_cargo"))

        );

    }

    public static ArrayList<Funcionario> retreaveAll() throws SQLException {
        Connection conn = BancoDados.createConnection();

        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM funcionario ORDER BY nome");

        ArrayList<Funcionario> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(new Funcionario(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getInt("pk_funcionario"),
                    FuncionarioEnderecoDAO.retreaveAll(rs.getInt("pk_funcionario")),
                    CargoDAO.retreave(rs.getInt("pk_funcionario "))
            ));
        }
        return aux;
    }


    public static void update(Funcionario c) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement("UPDATE funcionario SET nome = ?, cpf=?, fk_cargo=? WHERE pk_funcionario = ?");
        stm.setString(1, c.getNome());
        stm.setString(2, c.getCpf());
        stm.setInt(3, c.getCargo().getPk_cargo());
        stm.setInt(4, c.getPk_funcionario());

        stm.execute();
        
        Endereco e;
        
        for (int i=0; i<c.getEnderecos().size();i++) {
            e = c.getEnderecos().get(i);
            
            if (e.getStatus() == Endereco.EXCLUIDO) {
                FuncionarioEnderecoDAO.delete(e);
                c.getEnderecos().remove(i);
                i--;
            } else {
                if (e.getPk() == 0) {
                    e.setFk(c.getPk_funcionario());
                    FuncionarioEnderecoDAO.create(e);
                } else {
                    FuncionarioEnderecoDAO.update(e);
                }
            }
        }
        stm.close();
    }

    public static void delete(Funcionario c) throws SQLException {
        delete(c.getPk_funcionario());
    }

    public static void delete(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();
        conn.createStatement().execute("DELETE FROM funcionario WHERE pk_funcionario = " + pk);
    }

}
