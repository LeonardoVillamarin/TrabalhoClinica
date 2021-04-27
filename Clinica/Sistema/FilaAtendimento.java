package Sistema;

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
                if(isAbsent != null){
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
            System.out.println(contador + ". " + paciente.getNome() + "    Enfermidade: " + filaAtendimento.get(paciente));
            contador++;
        }
        if(filaAtendimento.isEmpty()){
            System.out.println("Fila de atendimento vazia");
        }
    }
    public static Paciente getPacienteFila(String dado){
        for(Entry<Paciente, String> p : filaAtendimento.entrySet()){
            if(dado.equals(p.getValue())){
                return p.getKey();
            }
        }
        return null;
    }
    public static void EncaminhaPacienteFila(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(InicializaEncerraSistema.statusFuncionamento){
                    for(Medico medico : CadastroMedico.getVectorMedicos()){
                        if(medico.getStatus() == StatusAtendimento.AGUARDANDO){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            if(medico.getEspecialidade().equals("Clínico Geral")){
                                Paciente g = getPacienteFila("Geral");
                                if(g != null){
                                    medico.diagnostica(g);
                                    try{
                                        filaAtendimento.remove(g);
                                    }catch(Exception e){
                                    }
                                }
                            }
                            else if(medico.getEspecialidade().equals("Ortopedista")){
                                Paciente o = getPacienteFila("Ortopédica");
                                if(o != null){
                                    medico.diagnostica(o);
                                    try{
                                        filaAtendimento.remove(o);
                                    }catch(Exception e){    
                                    }
                                }
                            }
                            else if(medico.getEspecialidade().equals("Cardiologista")){ 
                                Paciente c = getPacienteFila("Cardíaca");
                                if(c != null){
                                    medico.diagnostica(c);
                                    try{
                                        filaAtendimento.remove(c);
                                    }catch(Exception e){
                                    }
                                }
                            }
                            if(Enfermeiro.getStatusRaioX().equals(StatusAtendimento.AGUARDANDO)){
                                Paciente r = getPacienteFila("RaioX");
                                if(r != null){
                                    Enfermeiro.curativoExame(r, "RaioX");
                                    try{
                                        filaAtendimento.remove(r);
                                    }catch(Exception e){
                                    }
                                }
                            }
                            if(filaAtendimento.size() > 0){
                                Paciente r = getPacienteFila("Exames");
                                if(r != null){
                                    Enfermeiro.curativoExame(r, "Exames");
                                    try{
                                        filaAtendimento.remove(r);
                                    }catch(Exception e){
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
