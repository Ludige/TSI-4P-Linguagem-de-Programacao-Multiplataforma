
package ultrabusinessmegatop.controller;

import java.util.Objects;

/**
 *
 * @author luigg
 */
public class VendaItem {
    public static final int INALTERADO = 0;
    public static final int ALTERADO = 1;
    public static final int EXCLUIDO = 2;
    
    private int pk_item;
    private Venda venda;
    private Produto produto;
    private int qtd;
    private Double valorUnitario;
    
   private int status = INALTERADO;
    //Construtor

    public VendaItem() {
    }

    public VendaItem(Venda venda, Produto produto, int qtd, Double valorUnitario) {
        this.venda = venda;
        this.produto = produto;
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
    }

    public VendaItem(int pk_item, Venda venda, Produto produto, int qtd, Double valorUnitario) {
        this.pk_item = pk_item;
        this.venda = venda;
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

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.status = ALTERADO;
        this.venda = venda;
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

    //Default
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.pk_item);
        hash = 67 * hash + Objects.hashCode(this.venda);
        hash = 67 * hash + Objects.hashCode(this.produto);
        hash = 67 * hash + this.qtd;
        hash = 67 * hash + Objects.hashCode(this.valorUnitario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VendaItem other = (VendaItem) obj;
        if (this.qtd != other.qtd) {
            return false;
        }
        if (!Objects.equals(this.pk_item, other.pk_item)) {
            return false;
        }
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (!Objects.equals(this.valorUnitario, other.valorUnitario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VendaItem{" + "pk_item=" + pk_item + ", venda=" + venda + ", produto=" + produto + ", qtd=" + qtd + ", valorUnitario=" + valorUnitario + '}';
    }
    
}
