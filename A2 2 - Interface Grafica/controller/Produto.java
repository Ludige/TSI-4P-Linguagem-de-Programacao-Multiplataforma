
package ultrabusinessmegatop.controller;

/**
 *
 * @author luigg
 */
import java.util.Objects;

public class Produto {
    private String nome;
    private int estoqueMinimo;
    private int qtdEstoque;
    
    private int pkProduto;
    
    //Construtor

    public Produto() {
    }

    public Produto(String nome, int estoqueMinimo, int qtdEstoque) {
        this.nome = nome;
        this.estoqueMinimo = estoqueMinimo;
        this.qtdEstoque = qtdEstoque;
    }

    public Produto(String nome, int estoqueMinimo, int qtdEstoque, int pkProduto) {
        this.nome = nome;
        this.estoqueMinimo = estoqueMinimo;
        this.qtdEstoque = qtdEstoque;
        this.pkProduto = pkProduto;
    }

    //GSetters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getPkProduto() {
        return pkProduto;
    }

    public void setPkProduto(int pkProduto) {
        this.pkProduto = pkProduto;
    }
    
    //Default

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + this.estoqueMinimo;
        hash = 29 * hash + this.qtdEstoque;
        hash = 29 * hash + this.pkProduto;
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
        final Produto other = (Produto) obj;
        if (this.estoqueMinimo != other.estoqueMinimo) {
            return false;
        }
        if (this.qtdEstoque != other.qtdEstoque) {
            return false;
        }
        if (this.pkProduto != other.pkProduto) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "Produto{" + "nome=" + nome + ", estoqueMinimo=" + estoqueMinimo + ", qtdEstoque=" + qtdEstoque+"}";
        return this.nome;
    }
    
    
}
