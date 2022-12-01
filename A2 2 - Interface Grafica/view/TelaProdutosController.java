/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultrabusinessmegatop.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import ultrabusinessmegatop.controller.Produto;
import ultrabusinessmegatop.model.ProdutoDAO;

/**
 * FXML Controller class
 *
 * @author luigg
 */
public class TelaProdutosController implements Initializable {
    private Produto p = new Produto();
    private boolean novoProduto = true;
    private int aux;
    
@FXML
    private ListView<Produto> lvProdutos;

    @FXML
    private TextField tfEstoqueMinimo;

    @FXML
    private TextField tfEstoqueAtual;

    @FXML
    private TextField tfNome;
    
    @FXML
    private TextField tfAdicional;
    
    @FXML
    void selecionarProduto(MouseEvent event) {
        if (event.getClickCount() == 2 && !lvProdutos.getItems().isEmpty()) {
            novoProduto = false;
            
            p = lvProdutos.getSelectionModel().getSelectedItem();
            
            tfNome.setText(p.getNome());
            
            tfEstoqueAtual.setText(String.valueOf(p.getQtdEstoque()));
            tfEstoqueMinimo.setText(String.valueOf(p.getEstoqueMinimo()));
            
            lvProdutos.setDisable(false);
        }   
    }

    @FXML
    void adicionarEstoque(ActionEvent event) {
        aux = Integer.parseInt(tfEstoqueAtual.getText()) + Integer.parseInt(tfAdicional.getText());
        tfEstoqueAtual.setText(String.valueOf(aux));
        tfAdicional.setText("0.0");
    }
    
    @FXML
    void subtrairEstoque(ActionEvent event) {
        aux = Integer.parseInt(tfEstoqueAtual.getText()) - Integer.parseInt(tfAdicional.getText());
        tfEstoqueAtual.setText(String.valueOf(aux));
        tfAdicional.setText("0.0");
    }

    @FXML
    void salvar(ActionEvent event) {
        p.setNome(tfNome.getText());
        p.setQtdEstoque(Integer.parseInt(tfEstoqueAtual.getText()));
        p.setEstoqueMinimo(Integer.parseInt(tfEstoqueMinimo.getText()));
        
        try {
            if(novoProduto){
                ProdutoDAO.create(p);
                lvProdutos.getItems().add(p);
            }
        } catch (SQLException ex) {
            AlertaErro.Alertar("Impossivel criar novo produto", ex.toString());
        }
        
        limpar();
    }
    
    @FXML
    void cancelar(ActionEvent event) {
        limpar();
    }

    private void limpar(){
        lvProdutos.setDisable(false);
        tfNome.clear();
        tfEstoqueAtual.clear();
        tfEstoqueMinimo.clear();
        tfAdicional.setText("0.0");
        
        p = null;
        novoProduto = true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        lvProdutos.getItems().addAll(ProdutoDAO.retreaveAll());
        
    } catch (SQLException ex) {
        AlertaErro.Alertar("Nenhum Produto Encontrado", ex.toString());
    }

    }
}