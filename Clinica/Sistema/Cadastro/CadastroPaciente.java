package Sistema.Cadastro;

import java.util.Scanner;
import java.util.Vector;
import Beans.Paciente;
import Sistema.Exceptions.LancaExceptions;
import Sistema.Exceptions.PacienteJaCadastradoException;

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
        Scanner resposta = new Scanner(System.in);
        System.out.println("Digite as informações para cadastrar o paciente");
        System.out.println("Digite o nome: ");
        String nome = resposta.nextLine();
        System.out.println("Digite o CPF: ");
        String CPF = resposta.nextLine();
        boolean check = checkCadastro(CPF);
        if(check){
            resposta.close();
            LancaExceptions.lancaPacienteJaCadastradoException();
        }
        else{
            System.out.println("Digite o Celular: ");
            String celular = resposta.nextLine();
            System.out.println("Possui e-mail? 1. Sim / 2. Não");
            int temEmail = Integer.parseInt(resposta.nextLine());
            String email = null;
            if(temEmail == 1){
                System.out.println("Digite o E-mail: ");
                email = resposta.nextLine();   
            }
            System.out.println("Digite a Opção de Pagamento: ");
            String opcaoPagamento = resposta.nextLine();
            if(email == null){
                pacientesCadastrados.add(new Paciente(nome, CPF, celular, opcaoPagamento));
            }
            else{
                pacientesCadastrados.add(new Paciente(nome, CPF, celular, email, opcaoPagamento));
            }
        }
        resposta.close();
        return CPF;
    }
}
