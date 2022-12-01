package businesshc;

import java.util.Objects;

public class Endereco {
    private String logradouro;
    private String cidade;
    
    //Construtor

    public Endereco(String cidade, String logradouro) {
        this.logradouro = logradouro;
        this.cidade = cidade;
    }

    public Endereco() {
    }
    
    //Metodos

    //GSetters
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    //Override padrão

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.logradouro);
        hash = 67 * hash + Objects.hashCode(this.cidade);
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
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.logradouro, other.logradouro)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Endereco{" + "Logradouro:" + logradouro + ", Complemento:" + cidade + '}';
    }

    public void print(){
        System.out.println(this);
    }
    
}
 