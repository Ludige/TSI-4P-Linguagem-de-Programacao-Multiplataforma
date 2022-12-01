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
import ultrabusinessmegatop.controller.Cargo;
import ultrabusinessmegatop.controller.Funcionario;
import ultrabusinessmegatop.controller.Endereco;
import ultrabusinessmegatop.model.FuncionarioDAO;

/**
 * FXML Controller class
 *
 * @author luigg
 */
public class TelaFuncionarioController implements Initializable {
    private boolean novoFuncionario = true;
    private Funcionario f = new Funcionario();
    private Cargo c;
    private Endereco e;

    @FXML
    private ComboBox<String> cmbEstado;

    @FXML
    private ComboBox<String> cmbPais;

    @FXML
    private ListView<Funcionario> lvFuncionario;

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
    private TextField tfCargoDescricao;

    @FXML
    private TextField tfCargoNome;
       
    @FXML
    private TableView<Endereco> tvEndereco;

    @FXML
    void salvarFuncionario(ActionEvent event) {
        f.setNome(tfNome.getText());
        f.setCpf(tfCpf.getText());
        c = new Cargo(tfCargoNome.getText(),tfCargoDescricao.getText());
        f.setCargo(c);

        try {
            if (novoFuncionario) {
                FuncionarioDAO.create(f);
                lvFuncionario.getItems().add(f);
            }
        } catch (SQLException ex) {
            AlertaErro.Alertar("Impossivel Continuar", ex.toString());
        }

        Limpar();
    }

    @FXML
    void cancelarFuncionario(ActionEvent event) {

        Limpar();
    }

    @FXML
    void selecionarFuncionario(MouseEvent event) {
        if (event.getClickCount() == 2) {
            novoFuncionario = false;

            f = lvFuncionario.getSelectionModel().getSelectedItem();

            tfNome.setText(f.getNome());
            tfCpf.setText(f.getCpf());
            tfCargoNome.setText(f.getCargo().getNome());
            tfCargoDescricao.setText(f.getCargo().getNome());

            tvEndereco.getItems().addAll(f.getEnderecos());

            lvFuncionario.setDisable(true);
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
            f.getEnderecos().add(e);
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
        tfCargoNome.clear();
        tfCargoDescricao.clear();
        
        lvFuncionario.setDisable(false);
        tvEndereco.getItems().clear();

        tpDados.getSelectionModel().selectFirst();

        f = null;
        novoFuncionario = true;

        cancelaEndereco(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lvFuncionario.getItems().addAll(FuncionarioDAO.retreaveAll());

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