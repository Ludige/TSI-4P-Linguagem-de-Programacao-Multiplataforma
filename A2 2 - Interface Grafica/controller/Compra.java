
package ultrabusinessmegatop.controller;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author luigg
 */
public class Compra {
    private int pk_compra;
    private Fornecedor fornecedor;
    private int numero;
    private String data;
    
    private ArrayList<CompraItem> items;
    private ArrayList<FinanceiroSaida> ValoresSaida;
    //Construtor

    public Compra() {
    }

    public Compra(Fornecedor fornecedor, int numero, String data) {
        this.fornecedor = fornecedor;
        this.numero = numero;
        this.data = data;
    }

    public Compra(int pk_compra, Fornecedor fornecedor, int numero, String data) {
        this.pk_compra = pk_compra;
        this.fornecedor = fornecedor;
        this.numero = numero;
        this.data = data;
    }

    public Compra(int pk_compra, Fornecedor fornecedor, int numero, String data, ArrayList<CompraItem> items, ArrayList<FinanceiroSaida> ValoresSaida) {
        this.pk_compra = pk_compra;
        this.fornecedor = fornecedor;
        this.numero = numero;
        this.data = data;
        this.items = items;
        this.ValoresSaida = ValoresSaida;
    }

    public Compra(Fornecedor fornecedor, int numero, String data, ArrayList<CompraItem> items, ArrayList<FinanceiroSaida> ValoresSaida) {
        this.fornecedor = fornecedor;
        this.numero = numero;
        this.data = data;
        this.items = items;
        this.ValoresSaida = ValoresSaida;
    }
    
    //GSetters

    public int getPk_compra() {
        return pk_compra;
    }

    public void setPk_compra(int pk_compra) {
        this.pk_compra = pk_compra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<CompraItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CompraItem> items) {
        this.items = items;
    }

    public ArrayList<FinanceiroSaida> getValoresSaida() {
        return ValoresSaida;
    }

    public void setValoresSaida(ArrayList<FinanceiroSaida> ValoresSaida) {
        this.ValoresSaida = ValoresSaida;
    }
            
    //Default

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.pk_compra;
        hash = 53 * hash + Objects.hashCode(this.fornecedor);
        hash = 53 * hash + this.numero;
        hash = 53 * hash + Objects.hashCode(this.data);
        hash = 53 * hash + Objects.hashCode(this.items);
        hash = 53 * hash + Objects.hashCode(this.ValoresSaida);
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
        final Compra other = (Compra) obj;
        if (this.pk_compra != other.pk_compra) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.fornecedor, other.fornecedor)) {
            return false;
        }
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        if (!Objects.equals(this.ValoresSaida, other.ValoresSaida)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "Compra{" + "pk_compra=" + pk_compra + ", fornecedor=" + fornecedor + ", numero=" + numero + ", data=" + data + ", items=" + items + ", ValoresSaida=" + ValoresSaida + '}';
        return this.numero + "";
    }
    
}
