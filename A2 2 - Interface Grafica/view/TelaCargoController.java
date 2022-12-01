/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultrabusinessmegatop.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import ultrabusinessmegatop.controller.Cargo;
import ultrabusinessmegatop.model.CargoDAO;

/**
 * FXML Controller class
 *
 * @author luigg
 */
public class TelaCargoController implements Initializable {

    private Cargo c;
    
    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfNome;

    @FXML
    private ListView<Cargo> lvCargos;
        
    @FXML
    void cancelar(ActionEvent event) {
        Limpar();
    }

    @FXML
    void salvar(ActionEvent event) {
        boolean inserir = false;
        
        if(c == null){
            c = new Cargo();
            inserir = true;
        }
        
        c.setNome(tfNome.getText());
        c.setDescricao(tfDescricao.getText());
        try {
            if(inserir){
                CargoDAO.create(c);
                lvCargos.getItems().add(c);
            }else{
                CargoDAO.update(c);
            }
            
            Limpar();
        } catch (SQLException ex) {
            AlertaErro.Alertar("Impossivel prosseguir", ex.toString());
            Logger.getLogger(TelaCargoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    void selecionar(MouseEvent event) {
        if(event.getClickCount()==2){
            c = lvCargos.getSelectionModel().getSelectedItem();
            
            tfNome.setText(c.getNome());
            tfDescricao.setText(c.getDescricao());
            
            lvCargos.setDisable(true);
        }
    }
    
    private void Limpar(){
        tfNome.clear();
        tfDescricao.clear();
        c = null;
        lvCargos.refresh();
        lvCargos.setDisable(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lvCargos.getItems().addAll(CargoDAO.retreaveAll());
        } catch (SQLException ex) {
            Logger.getLogger(TelaCargoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
