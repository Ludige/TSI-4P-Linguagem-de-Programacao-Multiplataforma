/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultrabusinessmegatop;

import java.sql.*;
import ultrabusinessmegatop.controller.Cargo;
import ultrabusinessmegatop.model.CargoDAO;

/**
 *
 * @author lcarl
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {


//        Cargo c1 = new Cargo("GUARDA PATRIMONIAL 2", "PROTEGE");
//        
//        CargoDAO.create(c1);
//        
//        System.out.println(c1.getPk_cargo());


//          for(Cargo aux: CargoDAO.retreaveAll()){
//             System.out.println(aux);
//          }

        //System.out.println(CargoDAO.retreave(3));
        
        
        Cargo x = CargoDAO.retreave(3);
        x.setNome("Secretário Geral da ONU");
        CargoDAO.update(x);

    }

}
