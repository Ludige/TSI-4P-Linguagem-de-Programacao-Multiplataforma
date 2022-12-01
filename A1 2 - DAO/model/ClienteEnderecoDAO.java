package ultrabusinessmegatop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ultrabusinessmegatop.controller.Endereco;

/**
 *
 * @author luigg
 */
public class ClienteEnderecoDAO {
    /*
        CHUCHU estou sem acesso ao banco de dados enquanto faço esse exercicio 
        (PgAdmin tava abrindo e decidiu que não queria mais)
        então ta faltando as informações extras
        Fiz só com as que estavam no exercicio anterior só pra deixar ali
    */
    public static void create(Endereco e) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                        "INSERT INTO endereco (logradouro, complemento) VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS
                        );
        stm.setString(1, e.getLogradouro());
        stm.setString(2, e.getComplemento());
        stm.execute();
        
        ResultSet pks = stm.getGeneratedKeys();//recupera as chaves primárias geradas, nese caso apenas uma
        pks.next();//vai para a primeira posição válida
        e.setPk_endereco(pks.getInt(1));//recuperando a pk que acabaou de ser gerada

        stm.close();
    }
        
    public static Endereco retreave(int fk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cliente_endereco WHERE fk_endereco = " + fk);
        
        rs.next();
        
        Endereco e = new Endereco(rs.getString("logradouro"),
                            rs.getString("complemento"),
                            rs.getInt("fk_endereco")
        );
        
        return e;    
    }
        
    //Fazer esse retorno pra Cliente 
    public static ArrayList<Endereco> retreaveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM endereco ORDER BY logradouro");
        
        ArrayList<Endereco> aux = new ArrayList<>();
        
        while(rs.next()){                        
            aux.add(new Endereco(rs.getString("logradouro"),
                            rs.getString("complemento"),
                            rs.getInt("pk_endereco")));            
        }
        return aux;
    }
    
    public static void update(Endereco e) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement("UPDATE endereco SET logradouro = ?,"
                                                + " complemento = ? WHERE pk_endereco = ?");
        stm.setString(1, e.getLogradouro());
        stm.setString(2, e.getComplemento());
        stm.setInt(3, e.getPk_endereco());
        
        stm.execute();
        stm.close();
    }
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();       
        conn.createStatement().execute("DELETE FROM endereco WHERE pk_endereco = " + pk);
    }
    
    public static void delete(Endereco e) throws SQLException{
        delete(e.getPk_endereco());
    }
}
