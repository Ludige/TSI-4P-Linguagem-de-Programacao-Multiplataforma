
package ultrabusinessmegatop.controller;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author luigg
 */
public class Funcionario {
    private String nome;
    private String cpf;
    private int pk_funcionario;
    private ArrayList<Endereco> enderecos;
    private Cargo cargo;
    
    //Construtor

    public Funcionario() {
    }

    public Funcionario(String nome, String cpf, int pk_funcionario, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.pk_funcionario = pk_funcionario;
        this.cargo = cargo;
    }

    public Funcionario(String nome, String cpf, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public Funcionario(String nome, String cpf, ArrayList<Endereco> enderecos, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.enderecos = enderecos;
        this.cargo = cargo;
    }

    public Funcionario(String nome, String cpf, int pk_funcionario, ArrayList<Endereco> enderecos, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.pk_funcionario = pk_funcionario;
        this.enderecos = enderecos;
        this.cargo = cargo;
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

    public int getPk_funcionario() {
        return pk_funcionario;
    }

    public void setPk_funcionario(int pk_funcionario) {
        this.pk_funcionario = pk_funcionario;
    }

    public ArrayList<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    //Default

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.cpf);
        hash = 29 * hash + this.pk_funcionario;
        hash = 29 * hash + Objects.hashCode(this.enderecos);
        hash = 29 * hash + Objects.hashCode(this.cargo);
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
        final Funcionario other = (Funcionario) obj;
        if (this.pk_funcionario != other.pk_funcionario) {
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
        if (!Objects.equals(this.cargo, other.cargo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "nome=" + nome + ", cpf=" + cpf + ", pk_funcionario=" + pk_funcionario + ", enderecos=" + enderecos + ", cargo=" + cargo + '}';
    }
    
}
