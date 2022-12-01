package businesshc;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
    
        ArrayList<Cliente> cliente = new ArrayList<>();
        ArrayList<Fornecedor> fornecedor = new ArrayList<>();
       
        Scanner ler = new Scanner(System.in);
        int escolha;
        int codigoCli = 0;
        int codigoForn = 0;
        int codBusca;
        do {  
            System.out.println("*******************");
            System.out.println("Escolha uma opção:");
            System.out.println("1: Adicionar Cliente");
            System.out.println("2: Adicionar Fornecedor");
            System.out.println("3: Verificar Cliente");
            System.out.println("4: Verificar Fornecedor");
            System.out.println("5: Adicionar Telefone para Cliente Existente");
            System.out.println("6: Adicionar Telefone para Fornecedor Existente");
            System.out.println("0: Sair");
            System.out.println("*******************");
            
            escolha = ler.nextInt();
            
            switch(escolha){
                case 1:
                    Cliente cl = new Cliente();
                    cliente.add(cl.addCliente());
                    
                    System.out.println("Codigo do cliente: "+codigoCli);
                    codigoCli++;
                    break;
                case 2:
                    Fornecedor fn = new Fornecedor();
                    fornecedor.add(fn.addFornecedor());
                    
                    System.out.println("Codigo do forncedor: "+codigoForn);
                    codigoForn++;
                    break;
                case 3:
                    System.out.println("Escolha o codigo do cliente");//baseado no array
                    
                    codBusca = ler.nextInt();
                    if(codBusca > cliente.size()-1 || codBusca < 0){
                        System.out.println("Numero de Cliente disponivel");
                    }else{
                        cliente.get(codBusca).print(); 
                    }
                    break;
                case 4:
                    System.out.println("Escolha o codigo do Fornecedor");

                    codBusca = ler.nextInt();
                    if(fornecedor.size() < 0){
                        System.out.println("Nenhum Fornecedor Cadastrado");
                    }else if(codBusca > fornecedor.size()-1 || codBusca < 0){
                        System.out.println("Numero de Fornecedor disponivel");
                    }else{
                        fornecedor.get(codBusca).print();
                    }
                    break;
                case 5:                    

                    if(!cliente.isEmpty()){
                        System.out.println("Escolha o codigo do Cliente");
                        codBusca = ler.nextInt();
                        
                        if(codBusca > cliente.size()-1 || codBusca < 0){
                            System.out.println("Numero de Cliente indisponivel");
                        }else{
                            Telefone tel = new Telefone();
                            cliente.get(codBusca).addTelefone();
                        }
                    }else{
                        System.out.println("Nenhum Cliente Disponivel");
                    }
                    break;
                case 6: 
                    if(!cliente.isEmpty()){    
                        System.out.println("Escolha o codigo do Forncedor");
                        codBusca = ler.nextInt();
                        
                        if(codBusca > fornecedor.size()-1 || codBusca < 0){
                            System.out.println("Numero de Fornecedor indisponivel");
                        }else{
                            Telefone tel = new Telefone();
                            fornecedor.get(codBusca).addTelefone();
                        }
                    }else{
                        System.out.println("Nenhum Fornecedor Disponivel");
                    }
                    break;
                case 0: 
                    System.out.println("Saindo... obrigado por utilizar :)");
                    break;
                default:
                    System.out.println("Opção Invalida, tente novamente");
                    break;
            }
        } while (escolha != 0);

        /*
        Testes que deixei como comentario pq n tem nada haver com a minha main, mas se o Chuchu quiser testar ta ai
            Endereco ender = new Endereco("Morrinhos","ABC");
            ender.print();
        
            Telefone telefones = new Telefone("64","92925252");
            telefones.print();
        */
    }
    
}
