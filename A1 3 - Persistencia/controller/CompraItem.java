package ultrabusinessmegatop.controller;

/**
 *
 * @author luigg
 */
public class CompraItem {
    public static final int INALTERADO = 0;
    public static final int ALTERADO = 1;
    public static final int EXCLUIDO = 2;
    
    private int pk_item;
    private Compra compra;
    private Produto produto;
    private int qtd;
    private Double valorUnitario;
    
   private int status = INALTERADO;
    //Construtor

    public CompraItem() {
    }

    public CompraItem(Compra compra, Produto produto, int qtd, Double valorUnitario) {
        this.compra = compra;
        this.produto = produto;
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
    }

    public CompraItem(int pk_item, Compra compra, Produto produto, int qtd, Double valorUnitario) {
        this.pk_item = pk_item;
        this.compra = compra;
        this.produto = produto;
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
    }
   
    //Metodos

    //GSetters

    public int getStatus() {
        return status;
    }
    
    public void markAsDeleted(){
        this.status = EXCLUIDO;
    }
    
    public void resetStatus(){
        this.status = INALTERADO;
    }
    

    public int getPk_item() {
        return pk_item;
    }

    public void setPk_item(int pk_item) {
        if (pk_item == 0){
            this.pk_item = pk_item;
        }
        
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.status = ALTERADO;
        this.compra = compra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.status = ALTERADO;
        this.produto = produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.status = ALTERADO;
        this.qtd = qtd;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.status = ALTERADO;
        this.valorUnitario = valorUnitario;
    }
}
