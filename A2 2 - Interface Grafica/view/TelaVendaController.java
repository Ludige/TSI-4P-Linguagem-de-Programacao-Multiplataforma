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
import ultrabusinessmegatop.controller.Cliente;
import ultrabusinessmegatop.controller.FinanceiroEntrada;
import ultrabusinessmegatop.controller.Funcionario;
import ultrabusinessmegatop.controller.Produto;
import ultrabusinessmegatop.controller.Venda;
import ultrabusinessmegatop.controller.VendaItem;
import ultrabusinessmegatop.model.ClienteDAO;
import ultrabusinessmegatop.model.FuncionarioDAO;
import ultrabusinessmegatop.model.ProdutoDAO;
import ultrabusinessmegatop.model.VendaDAO;

/**
 * FXML Controller class
 *
 * @author luigg
 */
public class TelaVendaController implements Initializable {
    private boolean novaVenda = true;
    private Venda v;
    private Funcionario f = new Funcionario();
    private FinanceiroEntrada fe = new FinanceiroEntrada();
    private VendaItem vi = new VendaItem();
    private Produto p;
    
    private Double valorParcela = 0.0;
    public Double valor = 0.0;
    
    @FXML
    private ComboBox<Cliente> cmbCliente;

    @FXML
    private ComboBox<String> cmbMetodoPagamento;

    @FXML
    private ComboBox<Produto> cmbProduto;

    @FXML
    private Label lbTotalVenda;

    @FXML
    private ListView<Venda> lvVenda;

    @FXML
    private ComboBox<Funcionario> cmbVendedor;

    //Produto
    @FXML
    private TableColumn<VendaItem, Produto> tcProduto;

    @FXML
    private TableColumn<VendaItem, Double> tcValorUnitario;

    @FXML
    private TableColumn<VendaItem, Integer> tcQuantidade;

    @FXML
    private TableColumn<VendaItem, Double> tcTotal;

    //Saida
    @FXML
    private TableColumn<FinanceiroEntrada, Double> tcValor;

    @FXML
    private TableColumn<FinanceiroEntrada, String> tcFormaPagamento;

    @FXML
    private TableColumn<FinanceiroEntrada, String> tcDataBaixa;

    @FXML
    private TableColumn<FinanceiroEntrada, String> tcDataVencimento;

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
    private TableView<FinanceiroEntrada> tvFinanceiroEntrada;

    @FXML
    private TableView<Produto> tvProdutos;

    @FXML
    private TabPane tpVendas;
        
    @FXML
    void selecionarVenda(MouseEvent event) {
        try{
            if (event.getClickCount() == 2 && !lvVenda.getItems().isEmpty()) {
                v = lvVenda.getSelectionModel().getSelectedItem();

                lvVenda.setDisable(true);

                tfNumero.setText(String.valueOf(v.getNumero()));
                tfData.setText(v.getData());
                cmbCliente.getSelectionModel().select(v.getCliente());
                cmbVendedor.getSelectionModel().select(v.getVendedor());

                cmbProduto.getSelectionModel().select(vi.getProduto());
                tfValorUnitario.setText(String.valueOf(vi.getValorUnitario()));
                tfQuantidade.setText(String.valueOf(vi.getQtd()));

                tfValorParcela.setText(String.valueOf(fe.getValor()));
                tfDataBaixa.setText(fe.getDataBaixa());
                tfDataVencimento.setText(fe.getDataVencimento());
            }
        }catch(Exception e){
            AlertaErro.Alertar("Algo Errado", e.toString());
        }
    }

    @FXML
    void salvarVenda(ActionEvent event) {//
        v.setNumero(Integer.parseInt(tfNumero.getText()));
        v.setData(tfData.getText());
        v.setCliente(cmbCliente.getSelectionModel().getSelectedItem());
        v.setVendedor(cmbVendedor.getSelectionModel().getSelectedItem());
        
        vi = new VendaItem(cmbProduto.getSelectionModel().getSelectedItem(),Integer.parseInt(tfQuantidade.getText())
                , Double.parseDouble(tfValorUnitario.getText()));
        ArrayList<VendaItem> alVI = new ArrayList<>();
        alVI.add(vi);
        
        v.setItems(alVI);
        
        fe = new FinanceiroEntrada(cmbMetodoPagamento.getSelectionModel().getSelectedItem(),
                                    Double.parseDouble(tfValorParcela.getText()), tfDataBaixa.getText(), tfDataVencimento.getText()
        );
        ArrayList<FinanceiroEntrada> alFE = new ArrayList<>();
        alFE.add(fe);
        
        v.setValoresEntrada(alFE);

        try {
            if (novaVenda) {
                VendaDAO.create(v);
                lvVenda.getItems().add(v);
            }
        } catch (SQLException ex) {
            AlertaErro.Alertar("Impossivel Prosseguir", ex.toString());
        }

        limpar();
    }

    @FXML
    void cancelarVenda(ActionEvent event) {//
        limpar();
    }

    @FXML
    void selecionarFinanceiro(MouseEvent event) {//
        if (event.getClickCount() == 2 && !tvFinanceiroEntrada.getItems().isEmpty()) {
            fe = tvFinanceiroEntrada.getSelectionModel().getSelectedItem();

            tvFinanceiroEntrada.setDisable(true);

            tfValorParcela.setText(String.valueOf(fe.getValor()));
            tfDataBaixa.setText(fe.getDataBaixa());
            tfDataVencimento.setText(fe.getDataVencimento());
            cmbMetodoPagamento.setValue(fe.getFormaRecebimento());
        }
    }
    
    @FXML
    void adicionarFinanceiro(ActionEvent event) {//
        boolean inserir = false;
        
        if (fe == null) {
            fe = new FinanceiroEntrada();
            inserir = true;
        }

        try {
            fe.setValor(Double.parseDouble(tfValorParcela.getText()));
            fe.setDataVencimento(tfDataVencimento.getText());
            fe.setDataBaixa(tfDataVencimento.getText());
            fe.setFormaRecebimento(cmbMetodoPagamento.getSelectionModel().getSelectedItem());
            
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
        
        tvFinanceiroEntrada.setDisable(false);
        fe = null;
    }

    @FXML
    void adicionarProduto(ActionEvent event) {//
        boolean inserir = false;
        
        if (vi == null) {
            vi = new VendaItem();
            inserir = true;
        }

        
        vi.setProduto(cmbProduto.getValue());
        vi.setQtd(Integer.parseInt(tfQuantidade.getText()));
        vi.setValorUnitario(Double.parseDouble(tfValorUnitario.getText()));
        vi.setVenda(v);
        
        valor += vi.getTotal();
        
        lbTotalVenda.setText("Valor Total da Venda:"+ valor);
    }

    @FXML
    void selecionarProduto(MouseEvent event) {//
        if (event.getClickCount() == 2 && !tvProdutos.getItems().isEmpty()) {
            p = tvProdutos.getSelectionModel().getSelectedItem();
            
            tvProdutos.setDisable(true);
            
            cmbProduto.getSelectionModel().select(vi.getProduto());
            tfValorUnitario.setText(String.valueOf(vi.getValorUnitario()));
            tfQuantidade.setText(String.valueOf(vi.getQtd()));
        }

    }

    @FXML
    void cancelarProduto(ActionEvent event) {//
        tfQuantidade.clear();
        tfTotalParcelas.clear();
        
        tfValorUnitario.clear();
        tvProdutos.setDisable(false);
        vi = null;
    }
    
    private void limpar(){//
        tfNumero.clear();
        tfData.clear();
        
        tpVendas.getSelectionModel().selectFirst();
        
        tvFinanceiroEntrada.getItems().clear();
        tvProdutos.getItems().clear();
        
        lvVenda.setDisable(false);
        
        v = null;
        novaVenda = true;
        
        valor = 0.0;
        
        cancelarFinanceiro(null);
        cancelarProduto(null);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lvVenda.getItems().addAll(VendaDAO.retreaveAll());

            cmbCliente.getItems().addAll(ClienteDAO.retreaveAll());
            cmbVendedor.getItems().addAll(FuncionarioDAO.retreaveVendedor());
            
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
