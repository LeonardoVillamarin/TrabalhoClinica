package Clinica.Beans;
import java.util.Random;

import Clinica.Sistema.StatusAtendimento;

public class Enfermeiro {
    private static final long serialVersionUID = -8169639865117588129L;
    private String COREN;
    private static StatusAtendimento statusRaioX = StatusAtendimento.AGUARDANDO;

    public static StatusAtendimento getStatusRaioX(){
        return statusRaioX;
    }
    public static void curativoExame(Paciente paciente, String tratamento){
        if(tratamento.equals("RaioX")){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    paciente.setStatusAtendimento(StatusAtendimento.EM_ATENDIMENTO);
                    statusRaioX = StatusAtendimento.EM_ATENDIMENTO;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(paciente.getNome() + " está fazendo Raio-x");
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(paciente.getNome() + " terminou o Raio-x");
                    statusRaioX = StatusAtendimento.AGUARDANDO;

                    Random aleatorio = new Random();
                    if(aleatorio.nextInt() % 2 == 0){
                        if(aleatorio.nextInt() % 7 == 0){
                            System.out.println("Curativo feito no pé luxado de " + paciente.getNome());
                        }
                        else if(aleatorio.nextInt() % 3 == 0){
                            System.out.println("Curativo feito no braço quebrado de " + paciente.getNome());
                        }
                        else if(aleatorio.nextInt() % 2 == 0){
                            System.out.println("Curativo feito no dedo que foi fraturado de " + paciente.getNome());
                        }
                    }
                    else{
                        System.out.println("Não houve fratura " + paciente.getNome());
                    }
                    paciente.setStatusAtendimento(StatusAtendimento.AGUARDANDO);
                }
            }).start();
        }
        else if(tratamento.equals("Exames")){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    paciente.setStatusAtendimento(StatusAtendimento.EM_ATENDIMENTO);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(paciente.getNome() + " está fazendo alguns exames");
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    paciente.setStatusAtendimento(StatusAtendimento.AGUARDANDO);
                    System.out.println(paciente.getNome() + " terminou os exames");
                }
            }).start();
        }
    }
    public String getCoren(){
        return this.COREN;
    }
}