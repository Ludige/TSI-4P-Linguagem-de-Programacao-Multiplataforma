
package ultrabusinessmegatop.controller;

import java.util.ArrayList;
import java.util.Objects;
/**
 *
 * @author luigg
 */
public class Venda {
    private int numero;
    private String data;
    private Cliente cliente;
    private Fornecedor vendedor;
    private int pk_venda;
    
    private ArrayList<VendaItem> items;
    private ArrayList<FinanceiroEntrada> ValoresEntrada;
    
    //Construtor

    public Venda() {
    }

    public Venda(int numero, String data, Cliente cliente, Fornecedor vendedor) {
        this.numero = numero;
        this.data = data;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public Venda(int numero, String data, Cliente cliente, Fornecedor vendedor, ArrayList<VendaItem> items, ArrayList<FinanceiroEntrada> ValoresEntrada) {
        this.numero = numero;
        this.data = data;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.items = items;
        this.ValoresEntrada = ValoresEntrada;
    }

    public Venda(int numero, String data, Cliente cliente, Fornecedor vendedor, int pk_venda, ArrayList<VendaItem> items, ArrayList<FinanceiroEntrada> ValoresEntrada) {
        this.numero = numero;
        this.data = data;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.pk_venda = pk_venda;
        this.items = items;
        this.ValoresEntrada = ValoresEntrada;
    }

    public Venda(int numero, String data, Cliente cliente, Fornecedor vendedor, int pk_venda) {
        this.numero = numero;
        this.data = data;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.pk_venda = pk_venda;
    }

//GSetters    

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Fornecedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Fornecedor vendedor) {
        this.vendedor = vendedor;
    }

    public int getPk_venda() {
        return pk_venda;
    }

    public void setPk_venda(int pk_venda) {
        this.pk_venda = pk_venda;
    }

    public ArrayList<VendaItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<VendaItem> items) {
        this.items = items;
    }

    public ArrayList<FinanceiroEntrada> getValoresEntrada() {
        return ValoresEntrada;
    }

    public void setValoresEntrada(ArrayList<FinanceiroEntrada> ValoresEntrada) {
        this.ValoresEntrada = ValoresEntrada;
    }
    
    //default

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.numero;
        hash = 89 * hash + Objects.hashCode(this.data);
        hash = 89 * hash + Objects.hashCode(this.cliente);
        hash = 89 * hash + Objects.hashCode(this.vendedor);
        hash = 89 * hash + this.pk_venda;
        hash = 89 * hash + Objects.hashCode(this.items);
        hash = 89 * hash + Objects.hashCode(this.ValoresEntrada);
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
        final Venda other = (Venda) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (this.pk_venda != other.pk_venda) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.vendedor, other.vendedor)) {
            return false;
        }
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        if (!Objects.equals(this.ValoresEntrada, other.ValoresEntrada)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venda{" + "numero=" + numero + ", data=" + data + ", cliente=" + cliente + ", vendedor=" + vendedor + ", pk_venda=" + pk_venda + ", items=" + items + ", ValoresEntrada=" + ValoresEntrada + '}';
    }
    
}
