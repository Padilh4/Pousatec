
package pousatec;

import DAO.ApartamentoDAO;
import DAO.InquilinoDAO;
import DAO.ReservaDAO;
import Model.Apartamento;

import Model.Inquilino;
import Model.Reserva;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import java.util.Scanner;
import util.ExportadorCSV;


public class Main {

    public static void main(String[] args) throws SQLException, IOException {
    Scanner teclado = new Scanner(System.in);
    ReservaDAO Rdao = new ReservaDAO();
    InquilinoDAO Idao = new InquilinoDAO();
    ApartamentoDAO Adao = new ApartamentoDAO();    
    
    int opcao = 0;
    
    do{ 
        System.out.println(" === BEM VINDO A POUSADA LAGOA E MAR === ");
        
        
            System.out.println("\n #  Menu Principal  # ");
            System.out.println(" -- Sistema de Gerenciamento de Pousada -- ");
            System.out.println("1 - Cadastros");
            System.out.println("2 - Listas");
            
            System.out.println("3 - Gerenciar Apartamentos");
            System.out.println("4 - Gerenciar Reservas");
            System.out.println("0 - Sair do Programa");
            
            System.out.println("Escolha uma opcao:");
            
            opcao = teclado.nextInt();
            teclado.nextLine();
    
    switch(opcao){
        case 1:
            int opcao2 = 0;
           
            do{
            System.out.println("\n #  SubMenu Cadastro  # ");
            System.out.println("1- Cadastrar Inquilino");
            System.out.println("2- Cadastrar Apartamento");
            System.out.println("3- Cadastrar Reserva");
            System.out.println("0- Sair do Sub-menu");
            
                System.out.println("Escolha uma opcao: ");
                
                opcao2 = teclado.nextInt();
                teclado.nextLine();
                
                switch(opcao2) {
                    case 1:
                        System.out.println(" === Cadastro de Inquilinos === ");
                        System.out.print("Nome do Inquilino: ");
                        String NomeInquilino = teclado.nextLine();
                        System.out.print("telefone do Inquilino:");
                        String TelefoneInquilino = teclado.nextLine();
                        
                        
                        Inquilino InquilinoNovo = new Inquilino(NomeInquilino, TelefoneInquilino);
                        Idao.cadastrar(InquilinoNovo);
                        break;
                        
                    case 2:
                        System.out.println(" === Cadastro de Apartamentos === ");
                        System.out.println("Qual a situação do apartamento? \"Limpo\", \"Sujo\", \"Disponivel\", \"Alugado\" ");
                        String StatusApartamento = teclado.nextLine();
                        System.out.println("Qual a capacidade maxima do apartamento? ");
                        int CapacidadeApartamento = lerInteirosSeguro(teclado);
                        System.out.println("Qual o valor da diaria desse apartamento? ");
                        double DiariaApartamento = lerDoublesSeguro(teclado);
                        Apartamento ApartamentoNovo = new Apartamento(StatusApartamento, CapacidadeApartamento, DiariaApartamento);
                        Adao.cadastrar(ApartamentoNovo);
                        break;
                        
                    case 3: 
                            Reserva novaReserva = new Reserva();
                            Inquilino i = new Inquilino();
                            Apartamento a = new Apartamento();
   
    System.out.println("=== Cadastro de Reservas ===");

    System.out.println("Digite o ID do Inquilino: ");
    int idInq = lerInteirosSeguro(teclado);
    i = Idao.buscarPorId(idInq);
    novaReserva.setInquilino(i);
    
    System.out.println("DEBUG: Inquilino na reserva é: " + novaReserva.getInquilino().getNome());

    
    System.out.println("Digite o ID do Apartamento: ");
    int idApt = lerInteirosSeguro(teclado);
                       
    a = Adao.buscarPorId(idApt);
    novaReserva.setApartamento(a);
    
System.out.println("DEBUG: Apartamento na reserva é o apartamento: " + novaReserva.getApartamento().getId());

    System.out.println("Data de Check-in (AAAA-MM-DD): ");
    String in = teclado.next();
    LocalDate checkin = LocalDate.parse(in);

    System.out.println("Data de Check-out (AAAA-MM-DD): ");
    String out = teclado.next();
    LocalDate checkout = LocalDate.parse(out);

    System.out.println("Vai usar ar-condicionado? (true/false): ");
    boolean usarAr = teclado.nextBoolean();

    System.out.println("Total de adultos pagantes: ");
    int pagantes = lerInteirosSeguro(teclado);

    System.out.println("Total de crianças: ");
    int criancas = lerInteirosSeguro(teclado);
    
    

if (i == null || a == null) {
    System.out.println("ERRO: Inquilino ou Apartamento não encontrado no sistema!");
    break; 
}

 


    novaReserva.setInquilino(i);
    novaReserva.setApartamento(a);
    novaReserva.setDataCheckin(checkin);
    novaReserva.setDataCheckout(checkout);
    novaReserva.setArCondicionado(usarAr);
    novaReserva.setPagantes(pagantes);
    novaReserva.setCriancas(criancas);


    
    Rdao.cadastrar(novaReserva); 
    
    break;
                        
                    case 0:
                        System.out.println("Saindo do Sub-menu...");
                        break;
                        
                    default:
                        System.out.println("Valor invalido digite novamente...");
                        
                }
                
            }while(opcao2 != 0);
            break;
            
        case 2:
            int opcao3 = 0;
           
            do{
            System.out.println("\n #  SubMenu Listar  # ");
            System.out.println("1- Listar Inquilinos");
            System.out.println("2- Listar Apartamentos");
            System.out.println("3- Listar Reservas");
            System.out.println("0- Sair do Sub-menu");
            
                System.out.println("Escolha uma opcao: ");
                
                opcao3 = teclado.nextInt();
                teclado.nextLine();
                
                switch(opcao3) {
                    case 1: 
                        System.out.println("\n--- ACERVO DOS INQUILINOS ---");
                    List<Inquilino> listaI = Idao.listar();
                    if (listaI.isEmpty()) {
                        System.out.println("O acervo está vazio.");
                    } else {
                        for (Inquilino i : listaI) {
                            System.out.println(i.toString());
                        }
                    }
                    break;
                    
                  
                        
                        
                    case 2:
                       System.out.println("\n--- ACERVO DOS APARTAMENTOS ---");
                    List<Apartamento> listaA = Adao.listar();
                    if (listaA.isEmpty()) {
                        System.out.println("O acervo está vazio.");
                    } else {
                        for (Apartamento A : listaA) {
                            System.out.println(A.toString());
                        }
                    }
                    break;
                    
                    case 3:
                        System.out.println("\n--- ACERVO DAS RESERVAS ---");
                    List<Reserva> listaR = Rdao.listar();
                    if (listaR.isEmpty()) {
                        System.out.println("O acervo está vazio.");
                    } else {
                        for (Reserva r : listaR) {
                            System.out.println(r.toString());
                        }
                    }
                    break;
                    
               
                        
                    case 0:
                        System.out.println("Saindo do Sub-Menu Listar");
                        break;
                        
                    default:
                        System.out.println("Digito errado, tente novamente");
                        
                        
                        
                        
                    
                    
                    
                    
                    
                    
                    
                }
            }while(opcao3 != 0);
            
            break;
            
      
        case 3:
            int opcao5 = 0;
           
            do{
                 System.out.println(" === GERENCIADOR APARTAMENTOS === ");
            
            System.out.println("1. ATUALIZAR APARTAMENTO");
            System.out.println("0. Sair do Sub-Menu");
            System.out.println("Escolha uma opcao: ");
            opcao5 = lerInteirosSeguro(teclado);
                switch(opcao5){
                    case 1:
                        System.out.print("Digite o ID do Apartamento que deseja editar: ");
                    int idEditar = lerInteirosSeguro(teclado);
                    teclado.nextLine();
                    
                    Apartamento ApEdicao = Adao.buscarPorId(idEditar);
                    
                    if (ApEdicao == null) {
                        System.out.println("Erro: Livro não encontrado!");
                    } else {
                        System.out.println("Editando o Apartamento " + ApEdicao.getId());
                        


                        System.out.print("Novo Status (ou Enter para manter): ");
                        String NovoStatus = teclado.nextLine();
                        if (!NovoStatus.isEmpty()) {
                            ApEdicao.setStatus(NovoStatus);
                        }

                        System.out.print("Novo Valor de Diaria (ou 0 para manter): ");
                        Double ValorEdicao = lerDoublesSeguro(teclado);
                        
                        if (ValorEdicao != 0) {
                            ApEdicao.setValorDiaria(ValorEdicao);
                        }

                      
                        if (Adao.atualizar(ApEdicao)) {
                            System.out.println("Informações atualizadas com sucesso!");
                        }
                    }
                    break;
                        
                        
                        
                    case 0: 
                        System.out.println("Saindo do Sub-Menu");
                        
                        break;
                        
                        
                    default:
                        System.out.println("Digito errado! Tente novamente");
                        
                }
            }while (opcao5 != 0);
            break;
            
        case 4:
           
            int opcao4 = 0;
           
           
            do{
            System.out.println(" === GERENCIADOR RESERVAS");
            System.out.println("1. FATURAMENTO MENSAL");
            System.out.println("2. CANCELAR RESERVA");
                System.out.println("3. EXPORTAR RESERVAS");
            System.out.println("0. Sair do Sub-Menu");
            System.out.println("Escolha uma opcao: ");
             opcao4 = lerInteirosSeguro(teclado);
                switch(opcao4){
                   
                    case 1:
                        
                        System.out.println("De qual ano você deseja verificar o faturamento mensal? (2026)");
                        int ano = lerInteirosSeguro(teclado);
                        System.out.println("Qual mes de " + ano + " voce deseja verficar o faturamento? (2)");
                        int mes = lerInteirosSeguro(teclado);
                        
                        System.out.println("Faturamento total do mes " + mes + "  e de R$" + Rdao.calcularFaturamentoMensal(mes, ano));
                        
                        break;
                        
                    case 2:
                        System.out.println(" === CANCELAR RESERVA === \n");
                         Reserva Rcancel = null;
                    List<Reserva> listaCR = Rdao.listar();
                    if (listaCR.isEmpty()) {
                        System.out.println("Não há nenhuma reserva para cancelar");
                    } else {
                        for (Reserva R : listaCR) {
                            System.out.println(R.toString());
                        }
                    }
                        System.out.println("Qual o ID da reserva você deseja cancelar?");
                        int IDcancel = lerInteirosSeguro(teclado);
                        
                        Rcancel =  Rdao.buscarPorId(IDcancel);
                        if(Rcancel == null){
                            System.out.println("Reserva não existente, tente novamente");
                            break;
                        }
                        System.out.println(Rcancel.getCriancas());
                        
                        teclado.nextLine();
                        System.out.println("Você tem certeza que deseja cancelar a reserva do inquilino " + Rcancel.getInquilino().getNome());
                        String resp = teclado.nextLine();
                        if(resp.equals("sim")){
                            Rdao.cancelarReserva(IDcancel);
                        }else{
                            System.out.println("Tudo bem!");
                            break;
                        }
                        
                        
                        
                        
                        
                        
                        break;
                    case 3:
                        System.out.println(" - EXPORTAR INVENTÁRIO (EXCEL) - ");
                        teclado.nextLine();
                    System.out.println("Digite o nome do arquivo (ex:relatorio_reserva: ");
                    String nomeArquivo = teclado.nextLine();
                    
              
                    String caminhoCompleto = nomeArquivo + ".csv";
                    ExportadorCSV exportador = new ExportadorCSV();
                    exportador.exportarInventario(caminhoCompleto);
                        break;
                        
                        
                    case 0:
                        System.out.println("Saindo do Gerenciamento das Reservas...");
                        break;
                        
                    default:
                        System.out.println("digito errado, tente novamente");
                        
                        
                    
                    
                    
                    
                    
                }

            }while(opcao4 != 0);
            break;
            
        case 0:
            
            break;
            
        default:
            
            
            
            
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    }while(opcao != 0);
    }
    public static int lerInteirosSeguro (Scanner teclado){
      
        
        boolean dadosValidos = false;
        int numero = 0;
        
        while(!dadosValidos) {
            try{
                numero = teclado.nextInt();
                dadosValidos = true;
                
            }catch(java.util.InputMismatchException e){
                System.out.println(" Você digitou um texto. Digite apenas numeros");
                teclado.next(); 
            }
        }
        return numero;
                
                
                
    }
    
    public static Double lerDoublesSeguro (Scanner teclado){
        boolean dadosValidos = false;
        Double numero = 0.0;
        
        while(!dadosValidos) {
            try{
                numero = teclado.nextDouble();
                dadosValidos = true;
                
            }catch(java.util.InputMismatchException e){
                System.out.println("Você digitou um texto. Digite apenas numeros");
                teclado.next(); 
            }
        }
        return numero;
    }
}
