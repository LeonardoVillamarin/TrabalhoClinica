package Clinica.Beans;

import Clinica.Sistema.StatusAtendimento;

public class Cardiologista extends Medico{
    private static final long serialVersionUID = -4406097203785118177L;
    public Cardiologista(String nome, String CPF, String telefone, String CRM, String especialidade){
        super(nome, CPF, telefone, CRM, especialidade);
    }
    public Cardiologista(String nome, String CPF, String telefone, String email, String CRM, String especialidade){
        super(nome, CPF, telefone, email, CRM, especialidade);
    }
    @Override
    public void diagnostica(Paciente paciente){
        new Thread(new Runnable() {
            @Override
            public void run() {
                paciente.setStatusAtendimento(StatusAtendimento.EM_ATENDIMENTO);
                setStatus(StatusAtendimento.EM_ATENDIMENTO);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } 
                System.out.println("Cardiologista " + getNome() + " atendendo paciente " + paciente.getNome());
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setStatus(StatusAtendimento.AGUARDANDO);
                paciente.setStatusAtendimento(StatusAtendimento.AGUARDANDO);
                System.out.println("Cardiologista " + getNome() + " aguardando paciente.");
            }
        }).start();
    }
    @Override
    public String getEspecialidade(){
        return "Cardiologista";
    }
}
