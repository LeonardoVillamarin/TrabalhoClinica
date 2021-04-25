package Sistema;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import Beans.Paciente;
import Beans.Medico;
import Beans.Enfermeiro;
import Sistema.Cadastro.*;

public abstract class FilaAtendimento {
    private static LinkedHashMap<Paciente, String> filaAtendimento = new LinkedHashMap<Paciente, String>();
    private static boolean statusThreadFila = false;

    public static void setPacienteNaFila(String CPF){
        Scanner resposta = new Scanner(System.in);
        for(Paciente paciente : CadastroPaciente.getVectorPacientes()){
            if(paciente.getCPF().equals(CPF)){
                int opcao;
                String enfermidade = null;
                do{
                    System.out.println("Escolha a enfermidade:");
                    System.out.println("1. Ortopédica");
                    System.out.println("2. Cardíaca");
                    System.out.println("3. Geral/Indefinida");
                    opcao = Integer.parseInt(resposta.nextLine());
                    if(opcao == 1){
                        enfermidade = "Ortopédica";
                    }
                    else if(opcao == 2){
                        enfermidade = "Cardíaca";
                    }
                    else if(opcao == 3){
                        enfermidade = "Geral";
                    }
                    else{
                        System.out.println("Opção inválida");
                    }
                }while(opcao != 1 && opcao != 2 && opcao != 3);
                resposta.close();
                String isAbsent = filaAtendimento.putIfAbsent(paciente, enfermidade);
                if(isAbsent == null){
                    System.out.println("Paciente já está na fila");
                }
            }
            else{
                System.out.println("Paciente não está cadastrado");
            }
        }
    }
    public static void setPacienteNaFila(String CPF, String enfermidade){
        for(Paciente paciente : CadastroPaciente.getVectorPacientes()){
            if(paciente.getCPF().equals(CPF)){ 
                String isAbsent = filaAtendimento.putIfAbsent(paciente, enfermidade);
                if(isAbsent == null){
                    System.out.println("Paciente já está na fila");
                }
            }
        }
        if(!statusThreadFila){
            EncaminhaPacienteFila();
            statusThreadFila = true;
        }
    }
    public static void getFila(){
        int contador = 1;
        for(Paciente paciente : filaAtendimento.keySet()){
            System.out.println(contador + ". " + paciente.getNome() + "Enfermidade: " + filaAtendimento.get(paciente));
            contador++;
        }
        if(filaAtendimento.isEmpty()){
            System.out.println("Fila de atendimento vazia");
        }
    }
    synchronized static Paciente getPacienteFila(String CPF){
        for(Paciente p : filaAtendimento.keySet()){
            if(CPF.equals(p.getCPF())){
                return p;
            }
        }
        return null;
    }
    public static void EncaminhaPacienteFila(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(InicializaEncerraSistema.statusFuncionamento){
                    Iterator<Entry<Paciente, String>> it = filaAtendimento.entrySet().iterator();
                    for(Medico medico : CadastroMedico.getVectorMedicos()){
                        while(it.hasNext()){
                            Entry<Paciente, String> paciente = (Entry<Paciente, String>)it.next();
                            if(medico.getStatus() == StatusAtendimento.AGUARDANDO){
                                if(filaAtendimento.size() > 0){
                                    if(medico.getEspecialidade().equals("Clínico Geral") && paciente.getValue().equals("Geral")){
                                        medico.diagnostica(getPacienteFila(paciente.getKey().getCPF()));
                                        try{
                                            filaAtendimento.remove(paciente.getKey());
                                        }catch(Exception e){
                                        }
                                    }
                                }
                                if(filaAtendimento.size() > 0){
                                    if(medico.getEspecialidade().equals("Ortopedista") && paciente.getValue().equals("Ortopédica")){
                                        medico.diagnostica(getPacienteFila(paciente.getKey().getCPF()));
                                        try{
                                            filaAtendimento.remove(paciente.getKey());
                                        }catch(Exception e){    
                                        }
                                    }
                                }
                                if(filaAtendimento.size() > 0){ 
                                    if(medico.getEspecialidade().equals("Cardiologista") && paciente.getValue().equals("Cardiológica")){
                                        medico.diagnostica(getPacienteFila(paciente.getKey().getCPF()));
                                        try{
                                            filaAtendimento.remove(paciente.getKey());
                                        }catch(Exception e){
                                        }
                                    }
                                }
                                if(filaAtendimento.size() > 0){ 
                                    if(Enfermeiro.getStatusRaioX().equals(StatusAtendimento.AGUARDANDO) && paciente.getValue().equals("RaioX")){
                                        Enfermeiro.curativoExame(getPacienteFila(paciente.getKey().getCPF()), paciente.getValue());
                                        try{
                                            filaAtendimento.remove(paciente.getKey());
                                        }catch(Exception e){
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }).start();
    }
}
