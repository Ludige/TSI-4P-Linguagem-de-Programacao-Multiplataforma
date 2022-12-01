package businesshc;

import java.util.Objects;
import java.util.Scanner;

public class Telefone {
    private String ddd;
    private String numero;
   
    Scanner ler = new Scanner(System.in);
    //Construtor
    public Telefone(String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }
    
    public Telefone(){
    }
    //Metodos 

    //GSetters
    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    //Override padrão
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.ddd);
        hash = 41 * hash + Objects.hashCode(this.numero);
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
        final Telefone other = (Telefone) obj;
        if (!Objects.equals(this.ddd, other.ddd)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Telefone: (" + ddd+")" + numero;
    }
    
    public void print(){
        System.out.println(this);
    }
}
