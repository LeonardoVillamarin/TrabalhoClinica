package Sistema.Cadastro;

import java.util.Vector;
import java.util.Scanner;
import Beans.*;
import Sistema.Exceptions.LancaExceptions;
import Sistema.Exceptions.MedicoJaCadastradoException;

public class CadastroMedico {
    private static Vector<Medico> medicos = new Vector<Medico>();

    public static Vector<Medico> getVectorMedicos(){
        return medicos;
    }
    public static void setVectorMedicos(Medico medico){
        medicos.add(medico);
    }
    public static void getMedicos(){
        System.out.println("---------------------------------------------");
        System.out.println("Médicos disponíveis:");
        for(Medico medico : medicos){
            System.out.println(medico.getEspecialidade() + ": "+ medico.getNome() + " CRM: " + medico.getCRM());
        }
        System.out.println("---------------------------------------------");
    }
    public static void removeMedico(String CRM){
        if(medicos.size() > 1){
            for(Medico medico : medicos){
                if(medico.getCRM().equals(CRM)){
                    medicos.remove(medico);
                }
            }
        }
        else{
            if(medicos.get(0).getCRM().equals(CRM)){
                medicos.remove(0);
            }
        }
    }
    public static void cadastraMedico() throws MedicoJaCadastradoException {
        Scanner in = new Scanner(System.in);
        System.out.println("Digite as informações para cadastrar um novo médico");
        System.out.println("Digite o nome: ");
        String nome = in.nextLine();
        System.out.println("Digite o CPF: ");
        String CPF = in.nextLine();
        System.out.println("Digite o Celular: ");
        String celular = in.nextLine();
        int temEmail = 0;
        String email;
        do{
            System.out.println("Possui e-mail? 1. Sim / 2. Não");
            temEmail = Integer.parseInt(in.nextLine());
            email = null;
            if(temEmail == 1){
                System.out.println("Digite o E-mail: ");
                email = in.nextLine();   
            }
            if(temEmail != 1 && temEmail != 2){
                System.out.println("Opção inválida");
            }
        }while(temEmail != 1 && temEmail != 2);
        System.out.println("Digite o CRM: ");
        String CRM = in.nextLine();
        for(Medico m : medicos){
            if(m.getCRM().equals(CRM)){
                LancaExceptions.lancaMedicoJaCadastradoException();
            }
        }
        String escolha = "0";
        do{
            System.out.println("Escolha a Especialidade: ");
            System.out.println("1. Ortopedista");
            System.out.println("2. Clínico Geral");
            System.out.println("3. Cardiologista");
            escolha = in.nextLine();
            if(!(escolha.equals("1")) && !(escolha.equals("2")) && !(escolha.equals("3"))){
                System.out.println("Escolha inválida");
            }
        }while(!(escolha.equals("1")) && !(escolha.equals("2")) && !(escolha.equals("3")));
        if(escolha.equals("1")){
            if(email == null){
                medicos.add(new Ortopedista(nome, CPF, celular, CRM, "Ortopedista"));
            }
            else{
                medicos.add(new Ortopedista(nome, CPF, celular, email, CRM, "Ortopedista"));
            }
        }
        else if(escolha.equals("2")){
            if(email == null){
                medicos.add(new ClinicoGeral(nome, CPF, celular, CRM, "Clínico Geral"));
            }
            else{
                medicos.add(new ClinicoGeral(nome, CPF, celular, email, CRM, "Clínico Geral"));
            }
        }
        else if(escolha.equals("3")){
            if(email == null){
                medicos.add(new Cardiologista(nome, CPF, celular, CRM, "Cardiologista"));
            }
            else{
                medicos.add(new Cardiologista(nome, CPF, celular, email, CRM, "Cardiologista"));
            }
        }
    }
}

