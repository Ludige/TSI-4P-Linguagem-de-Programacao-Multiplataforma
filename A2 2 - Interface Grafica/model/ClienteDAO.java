/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultrabusinessmegatop.model;

import ultrabusinessmegatop.controller.Cliente;
import ultrabusinessmegatop.controller.Endereco;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author lcarl
 */
public class ClienteDAO {

    public static void create(Cliente c) throws SQLException {
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

        for (Endereco e : c.getEnderecos()) {
            e.setFk(c.getPk_cliente());//configurar a fk do endereço com a pk do cliente
            ClienteEnderecoDAO.create(e);
        }

        stm.close();
    }

    public static Cliente retreave(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();

        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cliente where pk_cliente=" + pk);
        rs.next();
        return new Cliente(rs.getString("nome"),
                rs.getString("cpf"),
                ClienteEnderecoDAO.retreaveAll(pk),//retorna um vetor de enderecos do cliente pk
                rs.getInt("pk_cliente")
        );

    }

    public static ArrayList<Cliente> retreaveAll() throws SQLException {
        Connection conn = BancoDados.createConnection();

        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cliente ORDER BY nome");

        ArrayList<Cliente> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(new Cliente(rs.getString("nome"),
                    rs.getString("cpf"),
                    ClienteEnderecoDAO.retreaveAll(rs.getInt("pk_cliente")),//retorna um vetor de enderecos do cliente pk
                    rs.getInt("pk_cliente")));
        }
        return aux;
    }

    /**
     * Atualiza os dados do cliente informado
     *
     * @param c o cliente que se quer atualizar
     * @param updateEnderecos se for verdadeiro, também atualizará os endereços
     * cadastrados nesse cliente
     * @throws SQLException
     */
    public static void update(Cliente c) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement("UPDATE cliente SET nome = ?, cpf=? WHERE pk_cliente = ?");
        stm.setString(1, c.getNome());
        stm.setString(2, c.getCpf());
        stm.setInt(3, c.getPk_cliente());

        stm.execute();
        
        Endereco e;
        
        for (int i=0; i<c.getEnderecos().size();i++) {
            e = c.getEnderecos().get(i);
            
            if (e.getStatus() == Endereco.EXCLUIDO) {
                ClienteEnderecoDAO.delete(e);
                c.getEnderecos().remove(i);
                i--;
            } else {
                if (e.getPk() == 0) {
                    e.setFk(c.getPk_cliente());
                    ClienteEnderecoDAO.create(e);
                } else {
                    ClienteEnderecoDAO.update(e);
                }
            }
        }
        stm.close();
    }

    public static void delete(Cliente c) throws SQLException {
        delete(c.getPk_cliente());
    }

    public static void delete(int pk) throws SQLException {
        Connection conn = BancoDados.createConnection();
        conn.createStatement().execute("DELETE FROM cliente WHERE pk_cliente = " + pk);
    }

}
