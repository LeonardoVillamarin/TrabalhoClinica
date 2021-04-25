package Beans;

import Sistema.StatusAtendimento;

public class Enfermeiro extends Pessoa {
    private static final long serialVersionUID = -8169639865117588129L;
    private String COREN;
    private static StatusAtendimento statusRaioX = StatusAtendimento.AGUARDANDO;

    public Enfermeiro(String nome, String CPF, String telefone, String COREN){
        super(nome, CPF, telefone);
        this.COREN = COREN;
    }
    public Enfermeiro(String nome, String CPF, String telefone, String email, String COREN){
        super(nome, CPF, telefone, email);
        this.COREN = COREN;
    }
    public static StatusAtendimento getStatusRaioX(){
        return statusRaioX;
    }
    public static void curativoExame(Paciente paciente, String tratamento){
        if(tratamento.equals("RaioX")){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    statusRaioX = StatusAtendimento.EM_ATENDIMENTO;
                    paciente.setStatusAtendimento(StatusAtendimento.EM_ATENDIMENTO);
                    System.out.println(paciente.getNome() + " está fazendo Raio-x");
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    paciente.setStatusAtendimento(StatusAtendimento.AGUARDANDO);
                    System.out.println(paciente.getNome() + " terminou o Raio-x");
        
                    int num = (int)Math.random();
                    if(num % 7 == 0){
                        num = (int)Math.random();
                        if(num % 7 == 0){
                            System.out.println("Curativo feito no pé luxado de " + paciente.getNome());
                        }
                        else if(num % 3 == 0){
                            System.out.println("Curativo feito no braço quebrado de " + paciente.getNome());
                        }
                        else if(num % 2 == 0){
                            System.out.println("Curativo feito no dedo que foi fraturado de " + paciente.getNome());
                        }
                    }
                    else{
                        System.out.println("Não houve fratura " + paciente.getNome());
                    }
                }
            }).start();
        }
    }
    public String getCoren(){
        return this.COREN;
    }
}