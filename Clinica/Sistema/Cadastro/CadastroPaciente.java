package Clinica.Sistema.Cadastro;

import java.util.Scanner;
import java.util.Vector;
import Clinica.Beans.Paciente;
import Clinica.Sistema.Exceptions.LancaExceptions;
import Clinica.Sistema.Exceptions.PacienteJaCadastradoException;

public abstract class CadastroPaciente {
    private static Vector<Paciente> pacientesCadastrados = new Vector<Paciente>();

    public static void setVectorPacientes(Paciente paciente){
        pacientesCadastrados.add(paciente);
    }
    public static Vector<Paciente> getVectorPacientes(){
        return pacientesCadastrados;
    }
    public static boolean checkCadastro(String CPF){
        for(Paciente paciente : pacientesCadastrados){
            if(paciente.getCPF().equals(CPF)){
                return true;
            } 
        }
        return false;
    }
    public static String cadastraPaciente() throws PacienteJaCadastradoException{
        Scanner in = new Scanner(System.in);
        System.out.println("Digite as informações para cadastrar o paciente");
        System.out.println("Digite o nome: ");
        String nome = in.nextLine();
        System.out.println("Digite o CPF: ");
        String CPF = in.nextLine();
        boolean check = checkCadastro(CPF);
        if(check){
            in.close();
            LancaExceptions.lancaPacienteJaCadastradoException();
        }
        else{
            System.out.println("Digite o Celular: ");
            String celular = in.nextLine();
            System.out.println("Possui e-mail? 1. Sim / 2. Não");
            int temEmail = Integer.parseInt(in.nextLine());
            String email = null;
            if(temEmail == 1){
                System.out.println("Digite o E-mail: ");
                email = in.nextLine();   
            }
            System.out.println("Digite a Opção de Pagamento: ");
            String opcaoPagamento = in.nextLine();
            if(email == null){
                pacientesCadastrados.add(new Paciente(nome, CPF, celular, opcaoPagamento));
            }
            else{
                pacientesCadastrados.add(new Paciente(nome, CPF, celular, email, opcaoPagamento));
            }
        }
        return CPF;
    }
}
