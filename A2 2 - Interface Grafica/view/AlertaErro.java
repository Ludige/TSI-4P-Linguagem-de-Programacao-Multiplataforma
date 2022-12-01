/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultrabusinessmegatop.view;

import javafx.scene.control.Alert;

/**
 *
 * @author luigg
 */
public class AlertaErro {
    public static void Alertar(String cabecalho, String conteudo){
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("Invalido");
            erro.setHeaderText(cabecalho);
            erro.setContentText(conteudo);
            erro.showAndWait();
            
    }
}
