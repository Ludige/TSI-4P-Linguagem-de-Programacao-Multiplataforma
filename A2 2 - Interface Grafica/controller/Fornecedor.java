package ultrabusinessmegatop.controller;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author luigg
 */
public class Fornecedor {
    private String nome;
    private String cpf;
    private ArrayList<Endereco> enderecos;
    private int pk_fornecedor;
    
    //Construtor
    public Fornecedor() {
    }

    public Fornecedor(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Fornecedor(String nome, String cpf, ArrayList<Endereco> enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.enderecos = enderecos;
    }

    public Fornecedor(String nome, String cpf, int pk_fornecedor) {
        this.nome = nome;
        this.cpf = cpf;
        this.pk_fornecedor = pk_fornecedor;
    }

    public Fornecedor(String nome, String cpf, ArrayList<Endereco> enderecos, int pk_fornecedor) {
        this.nome = nome;
        this.cpf = cpf;
        this.enderecos = enderecos;
        this.pk_fornecedor = pk_fornecedor;
    }
    
    //GSetters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public int getPk_fornecedor() {
        return pk_fornecedor;
    }

    public void setPk_fornecedor(int pk_fornecedor) {
        this.pk_fornecedor = pk_fornecedor;
    }
    
    //Defaults

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.cpf);
        hash = 79 * hash + Objects.hashCode(this.enderecos);
        hash = 79 * hash + this.pk_fornecedor;
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
        final Fornecedor other = (Fornecedor) obj;
        if (this.pk_fornecedor != other.pk_fornecedor) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.enderecos, other.enderecos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "Fornecedor{" + "nome=" + nome + ", cpf=" + cpf + ", enderecos=" + enderecos + '}';
        return this.nome + " - " + this.cpf;
    }
    
}
