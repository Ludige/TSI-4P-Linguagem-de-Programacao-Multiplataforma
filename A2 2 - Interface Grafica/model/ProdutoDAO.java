
package ultrabusinessmegatop.model;

import ultrabusinessmegatop.controller.Produto;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author luigg
 */
public class ProdutoDAO {
        public static void create(Produto p) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                        "INSERT INTO produto (nome, estoque_minimo,qtd_estoque) VALUES (?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                        );
        stm.setString(1, p.getNome());
        stm.setInt(2, p.getEstoqueMinimo());
        stm.setInt(3, p.getQtdEstoque());
        stm.execute();
        
        ResultSet pks = stm.getGeneratedKeys();//recupera as chaves primárias geradas, nese caso apenas uma
        pks.next();//vai para a primeira posição válida
        p.setPkProduto(pks.getInt(1));//recuperando a pk que acabaou de ser gerada

        stm.close();
    }
    
    public static Produto retreave(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM produto WHERE pk_produto = " + pk);
        
        rs.next();
        
        Produto p = new Produto(rs.getString("nome"),
                            rs.getInt("estoque_minimo"),
                            rs.getInt("qtd_estoque"),
                            rs.getInt("pk_produto")
        );
        
        return p;    
    }
    

    /**
     * Retornar todos os cargos armazenados no banco de dados
     * @return 
     */
    public static ArrayList<Produto> retreaveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        //faz de conta que é uma tabela na sua cabeça
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM produto ORDER BY nome");
        
        ArrayList<Produto> aux = new ArrayList<>();
        
        while(rs.next()){                        
            aux.add(new Produto(rs.getString("nome"),
                              rs.getInt("estoque_minimo"),
                              rs.getInt("qtd_estoque"),
                              rs.getInt("pk_produto")));            
        }
        return aux;
    }
    
    public static void update(Produto p) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement("UPDATE produto SET nome = ?, estoque_minimo=?, qtd_estoque=? WHERE pk_produto = ?");
        stm.setString(1, p.getNome());
        stm.setInt(2, p.getEstoqueMinimo());
        stm.setInt(3, p.getQtdEstoque());
        stm.setInt(4, p.getPkProduto());
        
        stm.execute();
        stm.close();
    }
    
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();       
        conn.createStatement().execute("DELETE FROM produto WHERE pk_produto = " + pk);
    }
    
    public static void delete(Produto p) throws SQLException{
        delete(p.getPkProduto());
    }
}
