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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ultrabusinessmegatop.controller.Fornecedor;
import ultrabusinessmegatop.controller.Endereco;
import ultrabusinessmegatop.model.FornecedorDAO;

/**
 * FXML Controller class
 *
 * @author luigg
 */
public class TelaFornecedorController implements Initializable {
    private boolean novoFornecedor = true;
    private Fornecedor c = new Fornecedor();
    private Endereco e;

    @FXML
    private ComboBox<String> cmbEstado;

    @FXML
    private ComboBox<String> cmbPais;

    @FXML
    private ListView<Fornecedor> lvFornecedor;

    @FXML
    private TableColumn<Endereco, String> tcBairro;

    @FXML
    private TableColumn<Endereco, String> tcCep;

    @FXML
    private TableColumn<Endereco, String> tcCidade;

    @FXML
    private TableColumn<Endereco, String> tcEstado;

    @FXML
    private TableColumn<Endereco, String> tcLogradouro;

    @FXML
    private TableColumn<Endereco, String> tcPais;

    @FXML
    private TabPane tpDados;

    @FXML
    private TextField tfBairro;

    @FXML
    private TextField tfCep;

    @FXML
    private TextField tfCidade;

    @FXML
    private TextField tfLogradouro;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfNome;

    @FXML
    private TableView<Endereco> tvEndereco;

    @FXML
    void salvarFornecedor(ActionEvent event) {

        c.setNome(tfNome.getText());
        c.setCpf(tfCpf.getText());

        try {
            if (novoFornecedor) {
                FornecedorDAO.create(c);
                lvFornecedor.getItems().add(c);
            }
        } catch (SQLException ex) {
            AlertaErro.Alertar("Impossivel Continuar", ex.toString());
        }

        Limpar();
    }

    @FXML
    void cancelarFornecedor(ActionEvent event) {

        Limpar();
    }

    @FXML
    void selecionarFornecedor(MouseEvent event) {
        if (event.getClickCount() == 2) {
            novoFornecedor = false;

            c = lvFornecedor.getSelectionModel().getSelectedItem();

            tfNome.setText(c.getNome());
            tfCpf.setText(c.getCpf());

            tvEndereco.getItems().addAll(c.getEnderecos());

            lvFornecedor.setDisable(true);
        }
    }

    @FXML
    void selecionarEndereco(MouseEvent event) {
        if (event.getClickCount() == 2 && !tvEndereco.getItems().isEmpty()) {
            e = tvEndereco.getSelectionModel().getSelectedItem();

            tvEndereco.setDisable(true);

            tfLogradouro.setText(e.getLogradouro());
            tfBairro.setText(e.getBairro());
            tfCep.setText(e.getCep());
            tfCidade.setText(e.getCidade());
            cmbEstado.getSelectionModel().select(e.getEstado());
            cmbPais.getSelectionModel().select(e.getPais());

        }
    }

    @FXML
    void confirmarEndereco(ActionEvent event) {
        boolean inserir = false;

        if (e == null) {
            e = new Endereco();
            inserir = true;
        }

        e.setLogradouro(tfLogradouro.getText());
        e.setBairro(tfBairro.getText());
        e.setCep((tfCep.getText()));
        e.setCidade((tfCidade.getText()));
        e.setPais(cmbPais.getValue());
        e.setEstado(cmbEstado.getValue());

        if (inserir) {
            c.getEnderecos().add(e);
            tvEndereco.getItems().add(e);
        }

        cancelaEndereco(null);
        tvEndereco.setDisable(false);
        tvEndereco.refresh();
    }

    @FXML
    void cancelaEndereco(ActionEvent event) {
        tfBairro.clear();
        tfCep.clear();
        tfCidade.clear();
        tfLogradouro.clear();
        cmbEstado.getSelectionModel().clearSelection();
        cmbPais.getSelectionModel().clearSelection();

        tvEndereco.setDisable(false);
        e = null;
    }

    private void Limpar() {
        tfNome.clear();
        tfCpf.clear();
        lvFornecedor.setDisable(false);
        tvEndereco.getItems().clear();

        tpDados.getSelectionModel().selectFirst();

        c = null;
        novoFornecedor = true;

        cancelaEndereco(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lvFornecedor.getItems().addAll(FornecedorDAO.retreaveAll());

            cmbPais.getItems().addAll(Pais.arrayPaises());
            cmbEstado.getItems().addAll(Estado.arrayEstados());

            tcCidade.setCellValueFactory(new PropertyValueFactory<>("Cidade"));
            tcLogradouro.setCellValueFactory(new PropertyValueFactory<>("Logradouro"));
            tcBairro.setCellValueFactory(new PropertyValueFactory<>("Bairro"));
            tcCep.setCellValueFactory(new PropertyValueFactory<>("CEP"));
            tcEstado.setCellValueFactory(new PropertyValueFactory<>("Estado"));
            tcPais.setCellValueFactory(new PropertyValueFactory<>("Pais"));

        } catch (SQLException ex) {
            AlertaErro.Alertar("Impossivel Continuar", ex.toString());
        }

    }
}
