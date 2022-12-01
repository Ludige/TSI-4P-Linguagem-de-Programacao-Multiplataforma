/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultrabusinessmegatop;

import java.sql.*;
import ultrabusinessmegatop.controller.Cliente;
import ultrabusinessmegatop.controller.Endereco;
import ultrabusinessmegatop.model.ClienteDAO;
import ultrabusinessmegatop.model.ClienteEnderecoDAO;

/**
 *
 * @author lcarl
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Cliente c = ClienteDAO.retreave(2);
        c.getEnderecos().get(1).markAsDeleted();
        c.getEnderecos().get(2).setCidade("Joviânia");
        c.getEnderecos().add(new Endereco("Rua ZZZ", "XYZ", "Centralina", "MG", "BR", "44444"));
        
        ClienteDAO.update(c);
        
        System.out.println(c);
        
        
    }

}
