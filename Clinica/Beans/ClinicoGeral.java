package Beans;

import Sistema.StatusAtendimento;

public class ClinicoGeral extends Medico {
    private static final long serialVersionUID = 5012286997872995337L;
    public ClinicoGeral(String nome, String CPF, String telefone, String CRM, String especialidade){
        super(nome, CPF, telefone, CRM, especialidade);
    }
    public ClinicoGeral(String nome, String CPF, String telefone, String email, String CRM, String especialidade){
        super(nome, CPF, telefone, email, CRM, especialidade);
    }
    @Override
    public void diagnostica(Paciente paciente){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                paciente.setStatusAtendimento(StatusAtendimento.EM_ATENDIMENTO);
                setStatus(StatusAtendimento.EM_ATENDIMENTO);
                System.out.println("Clínico Geral " + getNome() + " atendendo paciente " + paciente.getNome());
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                paciente.setStatusAtendimento(StatusAtendimento.AGUARDANDO);
                setStatus(StatusAtendimento.AGUARDANDO);
                System.out.println("Clínico Geral " + getNome() + " aguardando paciente");
            }
        }).start();
    }
    @Override
    public String getEspecialidade(){
        return "Clínico Geral";
    }
}
