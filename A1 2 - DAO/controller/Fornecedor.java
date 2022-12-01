package ultrabusinessmegatop.controller;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Fornecedor {
    private String nome;
    private String cnpj;
    
    private Endereco endereco;
    private ArrayList<Telefone> tel = new ArrayList<>();
    
    Scanner ler = new Scanner(System.in);
    //Construtor

    public Fornecedor(String nome, String cnpj, Endereco endereco,Telefone tel) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.tel.add(tel);
    }
    
    public Fornecedor(){
    }
    //Metodos
    public Fornecedor addFornecedor()throws NullPointerException{
        String nom;
        String codCnpj;
        String logr;
        String cid;
        String num;
        String ddd;
        
        System.out.println("-Adicionando Fornecedor-");
        System.out.println("Digite o nome do Fornecedor:");
        nom = ler.nextLine();
        System.out.println("Digite o cnpj:");
        codCnpj = ler.nextLine();
        
        System.out.println("Digite o nome da Cidade:");
        cid = ler.nextLine();
        System.out.println("Digite o Logradouro:");
        logr = ler.nextLine();
        Endereco end = new Endereco(cid,logr);
        
        System.out.println("Digite o DDD:");
        ddd = ler.nextLine();
        System.out.println("Digite o Numero:");
        num = ler.nextLine();
        Telefone telef = new Telefone(ddd,num);
        
        Fornecedor forn = new Fornecedor(nom,codCnpj,end,telef);
        return forn;
    }
    
   public void addTelefone(){
        String num;
        String ddd;

        System.out.println("Digite o DDD do Cliente");
        ddd = ler.nextLine();
        System.out.println("Digite o numero do Cliente");
        num = ler.nextLine();
        Telefone telef = new Telefone(ddd, num); 
        this.tel.add(telef);
    }
    
    //GSetters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Telefone> getTel() {
        return tel;
    }

    public void setTel(ArrayList<Telefone> tel) {
        this.tel = tel;
    }

    //Override padrão

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.cnpj);
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
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fornecedor{nome=").append(nome);
        sb.append(", cnpj=").append(cnpj);
        sb.append('}');
        return sb.toString();
    }
    
    public void print(){
        System.out.println(this);
    }
}
