
package ultrabusinessmegatop.controller;

/**
 *
 * @author luigg
 */
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author lcarl
 */
public class Cliente {
     
    private String nome;
    private String cpf;
    private ArrayList<Endereco> enderecos;
    
    private int pk_cliente;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, int pk_cliente) {
        this.nome = nome;
        this.cpf = cpf;
        this.pk_cliente = pk_cliente;
    }

    public Cliente(String nome, String cpf, ArrayList<Endereco> enderecos, int pk_cliente) {
        this.nome = nome;
        this.cpf = cpf;
        this.enderecos = enderecos;
        this.pk_cliente = pk_cliente;
    }

    public Cliente(String nome, String cpf, ArrayList<Endereco> enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.enderecos = enderecos;
    }

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

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
        if (this.enderecos==null){
            this.enderecos = new ArrayList<>();
        }
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public int getPk_cliente() {
        return pk_cliente;
    }

    public void setPk_cliente(int pk_cliente) {
        this.pk_cliente = pk_cliente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.cpf);
        hash = 23 * hash + Objects.hashCode(this.enderecos);
        hash = 23 * hash + this.pk_cliente;
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
        final Cliente other = (Cliente) obj;
        if (this.pk_cliente != other.pk_cliente) {
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
        return "Cliente{" + "nome=" + nome + ", cpf=" + cpf + ", enderecos=" + enderecos + ", pk_cliente=" + pk_cliente + '}';
    }
    
    


    
    
    
}
