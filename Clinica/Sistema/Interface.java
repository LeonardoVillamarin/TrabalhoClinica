package Sistema;

import java.util.*;
import Sistema.Cadastro.*;
import Sistema.Exceptions.MedicoJaCadastradoException;
import Sistema.Exceptions.PacienteJaCadastradoException;

public class Interface{
    public static void main(String[] args) {
        InicializaEncerraSistema.startSystem();
        Scanner resposta = new Scanner(System.in);
        int opcao = 0;
        do{
            System.out.println("====================================");
            System.out.println("O que deseja realizar?");
            System.out.println("1. Cadastrar um médico");
            System.out.println("2. Remover um médico");
            System.out.println("3. Consultar se o paciente está cadastrado");
            System.out.println("4. Cadastrar paciente");
            System.out.println("5. Adicionar paciente na fila");
            System.out.println("6. Consultar fila");
            System.out.println("7. Consultar médicos disponíveis");
            System.out.println("0. Sair do Sistema");
            System.out.println("====================================");
            opcao = Integer.parseInt(resposta.nextLine());
            if(opcao == 1){
                try{
                    CadastroMedico.cadastraMedico();
                }catch(MedicoJaCadastradoException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(opcao == 2){
                System.out.println("Digite o CRM do médico que deseja remover:");
                String CRM = resposta.nextLine();
                CadastroMedico.removeMedico(CRM);
            }
            else if(opcao == 3){
                System.out.println("Digite o CPF do paciente:");
                String CPF = resposta.nextLine();
                if(CadastroPaciente.checkCadastro(CPF)){
                    System.out.println("Paciente está cadastrado");
                }
                else{
                    System.out.println("Paciente não está cadastrado");
                }
            }
            else if(opcao == 4){
                try{
                    CadastroPaciente.cadastraPaciente();
                }catch(PacienteJaCadastradoException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(opcao == 5){
                System.out.println("Paciente está cadastrado? 1. Sim / 2. Não");
                int cadastrado = Integer.parseInt(resposta.nextLine());
                if(cadastrado == 1){
                    System.out.println("Digite o CPF do paciente: ");
                    String CPF = resposta.nextLine();
                    if(CadastroPaciente.checkCadastro(CPF)){
                        do{
                            System.out.println("Escolha a enfermidade:");
                            System.out.println("1. Ortopédica");
                            System.out.println("2. Cardíaca");
                            System.out.println("3. Geral/Indefinida");
                            opcao = Integer.parseInt(resposta.nextLine());
                            if(opcao == 1){
                                FilaAtendimento.setPacienteNaFila(CPF, "Ortopédica");
                            }
                            else if(opcao == 2){
                                FilaAtendimento.setPacienteNaFila(CPF, "Cardíaca");
                            }
                            else if(opcao == 3){
                                FilaAtendimento.setPacienteNaFila(CPF, "Geral");
                            }
                            else{
                                System.out.println("Opção inválida");
                            }
                        }while(opcao != 1 && opcao != 2 && opcao != 3);
                    }
                    else{
                        System.out.println("Paciente não está cadastrado");
                        do{
                            System.out.println("Gostaria de cadastrá-lo? 1.Sim / 2.Não");
                            opcao = Integer.parseInt(resposta.nextLine());
                            if(opcao == 1){
                                try{
                                    CadastroPaciente.cadastraPaciente();
                                }catch(PacienteJaCadastradoException e){
                                    System.out.println(e.getMessage());
                                } 
                                FilaAtendimento.setPacienteNaFila(CPF);
                            }
                        }while(opcao != 1 && opcao != 2);
                    }
                }
                else{
                    do{
                        System.out.println("Gostaria de cadastrá-lo? 1.Sim / 2.Não");
                        opcao = Integer.parseInt(resposta.nextLine());
                        if(opcao == 1){
                            String CPF = null;
                            try{
                                CPF = CadastroPaciente.cadastraPaciente();
                            }catch(Exception e){
                                System.out.println(e.getMessage());
                            }
                            FilaAtendimento.setPacienteNaFila(CPF);
                        }
                    }while(opcao != 1 && opcao != 2);
                }
            }
            else if(opcao == 6){
                FilaAtendimento.getFila();
            }
            else if(opcao == 7){
                CadastroMedico.getMedicos();
            }
        }while(opcao != 0);
        InicializaEncerraSistema.closeSystem();
        resposta.close();
    }
}
