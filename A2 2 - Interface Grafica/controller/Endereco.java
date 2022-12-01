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
    
    public static final int INALTERADO = 0;
    public static final int ALTERADO = 1;
    public static final int EXCLUIDO = 2;
    
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    
    private int pk;
    private int fk;
    
    private int status = INALTERADO;

    public Endereco() {
    }

    public Endereco(String logradouro, String bairro, String cidade, String estado, String pais, String cep) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
    }

    public Endereco(String logradouro, String bairro, String cidade, String estado, String pais, String cep, int pk, int fk) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
        this.pk = pk;
        this.fk = fk;
    }

    public Endereco(int pk, String logradouro, String bairro, String cidade, String estado, String pais, String cep) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
        this.pk = pk;
    }
    
    public Endereco(String logradouro, String bairro, String cidade, String estado, String pais, String cep, int fk) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
        this.fk = fk;
    }    

    public int getStatus() {
        return status;
    }
    
    public void markAsDeleted(){
        this.status = EXCLUIDO;
    }
    
    public void resetStatus(){
        this.status = INALTERADO;
    }
    
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.status = ALTERADO;               
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.status = ALTERADO;
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.status = ALTERADO;
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.status = ALTERADO;
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.status = ALTERADO;
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.status = ALTERADO;
        this.cep = cep;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        if (pk==0){
            this.pk = pk;
        }
    }

    public int getFk() {
        return fk;
    }

    public void setFk(int fk) {
        this.status = ALTERADO;
        this.fk = fk;
    }

    @Override
    public String toString() {
        return "Endereco{" + "logradouro=" + logradouro + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", pais=" + pais + ", cep=" + cep + ", pk=" + pk + ", fk=" + fk + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.logradouro);
        hash = 83 * hash + Objects.hashCode(this.bairro);
        hash = 83 * hash + Objects.hashCode(this.cidade);
        hash = 83 * hash + Objects.hashCode(this.estado);
        hash = 83 * hash + Objects.hashCode(this.pais);
        hash = 83 * hash + Objects.hashCode(this.cep);
        hash = 83 * hash + this.pk;
        hash = 83 * hash + this.fk;
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
        if (this.pk != other.pk) {
            return false;
        }
        if (this.fk != other.fk) {
            return false;
        }
        if (!Objects.equals(this.logradouro, other.logradouro)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        return true;
    }
    
    
    
}