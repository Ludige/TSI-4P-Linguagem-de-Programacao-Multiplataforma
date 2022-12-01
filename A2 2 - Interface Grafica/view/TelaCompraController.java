/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultrabusinessmegatop.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ultrabusinessmegatop.controller.FinanceiroSaida;
import ultrabusinessmegatop.controller.Fornecedor;
import ultrabusinessmegatop.controller.Produto;
import ultrabusinessmegatop.controller.Compra;
import ultrabusinessmegatop.controller.CompraItem;
import ultrabusinessmegatop.model.FornecedorDAO;
import ultrabusinessmegatop.model.ProdutoDAO;
import ultrabusinessmegatop.model.CompraDAO;

/**
 * FXML Controller class
 *
 * @author luigg
 */
public class TelaCompraController implements Initializable {
    private boolean novaCompra = true;
    private Compra c;
    private Fornecedor f = new Fornecedor();
    private FinanceiroSaida fs = new FinanceiroSaida();
    private CompraItem ci = new CompraItem();
    private Produto p;
    
    private Double valorParcela = 0.0;
    public Double valor = 0.0;

    @FXML
    private ComboBox<String> cmbMetodoPagamento;

    @FXML
    private ComboBox<Produto> cmbProduto;

    @FXML
    private Label lbTotalCompra;

    @FXML
    private ListView<Compra> lvCompra;

    @FXML
    private ComboBox<Fornecedor> cmbFornecedor;

    //Produto
    @FXML
    private TableColumn<CompraItem, Produto> tcProduto;

    @FXML
    private TableColumn<CompraItem, Double> tcValorUnitario;

    @FXML
    private TableColumn<CompraItem, Integer> tcQuantidade;

    @FXML
    private TableColumn<CompraItem, Double> tcTotal;

    //Saida
    @FXML
    private TableColumn<FinanceiroSaida, Double> tcValor;

    @FXML
    private TableColumn<FinanceiroSaida, String> tcFormaPagamento;

    @FXML
    private TableColumn<FinanceiroSaida, String> tcDataBaixa;

    @FXML
    private TableColumn<FinanceiroSaida, String> tcDataVencimento;

    @FXML
    private TextField tfData;

    @FXML
    private TextField tfDataBaixa;

    @FXML
    private TextField tfDataVencimento;

    @FXML
    private TextField tfNumero;

    @FXML
    private TextField tfQuantidade;

    @FXML
    private TextField tfTotalParcelas;

    @FXML
    private TextField tfValorParcela;

    @FXML
    private TextField tfValorUnitario;

    @FXML
    private TableView<FinanceiroSaida> tvFinanceiroSaida;

    @FXML
    private TableView<Produto> tvProdutos;

    @FXML
    private TabPane tpCompras;
        
    @FXML
    void selecionarCompra(MouseEvent event) {
        try{
            if (event.getClickCount() == 2 && !lvCompra.getItems().isEmpty()) {
                c = lvCompra.getSelectionModel().getSelectedItem();

                lvCompra.setDisable(true);

                tfNumero.setText(String.valueOf(c.getNumero()));
                tfData.setText(c.getData());
                cmbFornecedor.getSelectionModel().select(c.getFornecedor());

                cmbProduto.getSelectionModel().select(ci.getProduto());
                tfValorUnitario.setText(String.valueOf(ci.getValorUnitario()));
                tfQuantidade.setText(String.valueOf(ci.getQtd()));

                tfValorParcela.setText(String.valueOf(fs.getValor()));
                tfDataBaixa.setText(fs.getDataBaixa());
                tfDataVencimento.setText(fs.getDataVencimento());
            }
        }catch(Exception e){
            AlertaErro.Alertar("Algo Errado", e.toString());
        }
    }

    @FXML
    void salvarCompra(ActionEvent event) {//
        c.setNumero(Integer.parseInt(tfNumero.getText()));
        c.setData(tfData.getText());
        c.setFornecedor(cmbFornecedor.getSelectionModel().getSelectedItem());
        ///////////////
        ci = new CompraItem(cmbProduto.getSelectionModel().getSelectedItem(),
                Integer.parseInt(tfQuantidade.getText()),Double.parseDouble(tfValorUnitario.getText())
        );
        ArrayList<CompraItem> alVI = new ArrayList<>();
        alVI.add(ci);
        
        c.setItems(alVI);
        
        fs = new FinanceiroSaida(cmbMetodoPagamento.getSelectionModel().getSelectedItem(),
                    Double.parseDouble(tfValorParcela.getText()), tfDataBaixa.getText(), tfDataVencimento.getText()
        );
        ArrayList<FinanceiroSaida> alFE = new ArrayList<>();
        alFE.add(fs);
        
        c.setValoresSaida(alFE);

        try {
            if (novaCompra) {
                CompraDAO.create(c);
                lvCompra.getItems().add(c);
            }
        } catch (SQLException ex) {
            AlertaErro.Alertar("Impossivel Prosseguir", ex.toString());
        }

        limpar();
    }

    @FXML
    void cancelarCompra(ActionEvent event) {//
        limpar();
    }

    @FXML
    void selecionarFinanceiro(MouseEvent event) {//
        if (event.getClickCount() == 2 && !tvFinanceiroSaida.getItems().isEmpty()) {
            fs = tvFinanceiroSaida.getSelectionModel().getSelectedItem();

            tvFinanceiroSaida.setDisable(true);

            tfValorParcela.setText(String.valueOf(fs.getValor()));
            tfDataBaixa.setText(fs.getDataBaixa());
            tfDataVencimento.setText(fs.getDataVencimento());
            cmbMetodoPagamento.setValue(fs.getFormaRecebimento());
        }
    }
    
    @FXML
    void adicionarFinanceiro(ActionEvent event) {//
        boolean inserir = false;
        
        if (fs == null) {
            fs = new FinanceiroSaida();
            inserir = true;
        }

        try {
            fs.setValor(Double.parseDouble(tfValorParcela.getText()));
            fs.setDataVencimento(tfDataVencimento.getText());
            fs.setDataBaixa(tfDataVencimento.getText());
            fs.setFormaRecebimento(cmbMetodoPagamento.getSelectionModel().getSelectedItem());
            
            valorParcela += Double.parseDouble(tfValorParcela.getText());
            tfTotalParcelas.setText(String.valueOf(valorParcela));
        } catch (Exception e) {
            AlertaErro.Alertar("Impossivel Prosseguir", e.toString());
        }

    }

    @FXML
    void cancelarFinanceiro(ActionEvent event) {//
        tfValorParcela.clear();
        tfDataBaixa.clear();
        tfDataVencimento.clear();
        tfValorParcela.setText("0.0");
        cmbMetodoPagamento.setItems(null);
        
        tvFinanceiroSaida.setDisable(false);
        fs = null;
    }

    @FXML
    void adicionarProduto(ActionEvent event) {//
        boolean inserir = false;
        
        if (ci == null) {
            ci = new CompraItem();
            inserir = true;
        }

        
        ci.setProduto(cmbProduto.getValue());
        ci.setQtd(Integer.parseInt(tfQuantidade.getText()));
        ci.setValorUnitario(Double.parseDouble(tfValorUnitario.getText()));
        ci.setCompra(c);
        
        valor += ci.getTotal();
        
        lbTotalCompra.setText("Valor Total da Compra:"+ valor);
    }

    @FXML
    void selecionarProduto(MouseEvent event) {//
        if (event.getClickCount() == 2 && !tvProdutos.getItems().isEmpty()) {
            p = tvProdutos.getSelectionModel().getSelectedItem();
            
            tvProdutos.setDisable(true);
            
            cmbProduto.getSelectionModel().select(ci.getProduto());
            tfValorUnitario.setText(String.valueOf(ci.getValorUnitario()));
            tfQuantidade.setText(String.valueOf(ci.getQtd()));
        }

    }

    @FXML
    void cancelarProduto(ActionEvent event) {//
        tfQuantidade.clear();
        tfTotalParcelas.clear();
        
        tfValorUnitario.clear();
        tvProdutos.setDisable(false);
        ci = null;
    }
    
    private void limpar(){//
        tfNumero.clear();
        tfData.clear();
        
        tpCompras.getSelectionModel().selectFirst();
        
        tvFinanceiroSaida.getItems().clear();
        tvProdutos.getItems().clear();
        
        lvCompra.setDisable(false);
        
        c = null;
        novaCompra = true;
        
        valor = 0.0;
        
        cancelarFinanceiro(null);
        cancelarProduto(null);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lvCompra.getItems().addAll(CompraDAO.retreaveAll());

            cmbFornecedor.getItems().addAll(FornecedorDAO.retreaveAll());
            
            cmbProduto.getItems().addAll(ProdutoDAO.retreaveAll());
            
            cmbMetodoPagamento.getItems().addAll("Pix","Debito","Credito","Boleto","Paypal");

            tcProduto.setCellValueFactory(new PropertyValueFactory<>("Produto"));
            tcValorUnitario.setCellValueFactory(new PropertyValueFactory<>("ValorUnitario"));
            tcQuantidade.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
            tcTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
            
            tcValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
            tcFormaPagamento.setCellValueFactory(new PropertyValueFactory<>("Forma de Pagamento"));
            tcDataBaixa.setCellValueFactory(new PropertyValueFactory<>("Data de Baixa"));
            tcDataVencimento.setCellValueFactory(new PropertyValueFactory<>("Data de Vencimento"));
            
        } catch (SQLException ex) {
            AlertaErro.Alertar("Impossivel Iniciar", ex.toString());
        }
    }
}
