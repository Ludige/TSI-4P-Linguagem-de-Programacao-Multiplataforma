/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultrabusinessmegatop.controller;

import java.util.Objects;

/**
 *
 * @author lcarl
 */
public class Endereco {
    private String logradouro;
    private String complemento;
    private int pk_endereco;
    private int fk;

    public Endereco() {
    }

    public Endereco(String logradouro, String complemento,int pk_endereco) {
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.pk_endereco = pk_endereco;
    }
    
    public Endereco(String logradouro, String complemento) {
        this.logradouro = logradouro;
        this.complemento = complemento;
    }
    
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getPk_endereco() {
        return pk_endereco;
    }

    public void setPk_endereco(int pk_endereco) {
        this.pk_endereco = pk_endereco;
    }

    @Override
    public String toString() {
        return "Endereco{" + "logradouro=" + logradouro + ", complemento=" + complemento + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.logradouro);
        hash = 59 * hash + Objects.hashCode(this.complemento);
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
        if (!Objects.equals(this.complemento, other.complemento)) {
            return false;
        }
        return true;
    }
    
    public void print(){
        System.out.println(this);
    }
    
    public Endereco clone(){
        return new Endereco(this.complemento, this.logradouro, this.pk_endereco);
    }
    
}
