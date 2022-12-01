package businesshc;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Cliente {
    private String nome;
    private String cpf;
    
    private Endereco endereco;
    private ArrayList<Telefone> fones = new ArrayList<>();
    
    Scanner ler = new Scanner(System.in);
    
   //Construtores
    public Cliente(String nome, String cpf, Endereco end, Telefone tel) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = new Endereco(end.getCidade(),end.getLogradouro());
        this.fones.add(tel);
    }

    public Cliente(String nome, String cpf, Endereco end) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = new Endereco(end.getCidade(),end.getLogradouro());
    }
    
    public Cliente(){
    }
    //Metodos
    public Cliente addCliente() throws NullPointerException{
        String nom;
        String codCpf;
        String logr;
        String cid;
        
        System.out.println("-Adicionando Cliente-");
        System.out.println("Digite o nome do Cliente:");
        nom = ler.nextLine();
        System.out.println("Digite o cpf:");
        codCpf = ler.nextLine();
        
        System.out.println("Digite o nome da Cidade:");
        cid = ler.nextLine();
        System.out.println("Digite o Logradouro:");
        logr = ler.nextLine();

        Endereco end = new Endereco(cid,logr);
        
        String adicionar;
        System.out.println("Deseja adicionar telefone?");
        System.out.println("Digite 1 para sim e 2 para não");
        adicionar = ler.nextLine();

        System.out.println(adicionar);
     
        Cliente cliente;
        if(adicionar.equals("1")){
            String num;
            String ddd;
            System.out.println("Digite o DDD:");
            ddd = ler.nextLine();
            System.out.println("Digite o Numero:");
            num = ler.nextLine();
            Telefone telef = new Telefone(ddd,num);
            this.fones.add(telef);
            cliente = new Cliente(nom,codCpf,end,telef);
            System.out.println("Cliente cadastrado");
            return cliente;
        }else{
            cliente = new Cliente(nom,codCpf,end);
            System.out.println("Telefone não adicionado");
            System.out.println("Cliente cadastrado");
            return cliente;
        }
    }
    
    public void addTelefone(){
        String num;
        String ddd;

        System.out.println("Digite o DDD do Cliente");
        ddd = ler.nextLine();
        System.out.println("Digite o numero do Cliente");
        num = ler.nextLine();
        Telefone telef = new Telefone(ddd, num); 
        this.fones.add(telef);
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
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public ArrayList<Telefone> getFones() {
        return fones;
    }

    public void setFones(ArrayList<Telefone> fones) {
        this.fones = fones;
    }

    //Override padrão

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.nome);
        hash = 71 * hash + Objects.hashCode(this.cpf);
        hash = 71 * hash + Objects.hashCode(this.endereco);
        hash = 71 * hash + Objects.hashCode(this.fones);
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
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.fones, other.fones)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" + "Nome:" + nome + ", Cpf:" + cpf + ", Endereco:" + endereco + ", Fones:" + fones + '}';
    }
    
    public void print(){
        System.out.println(this);
    }
    
}

