package Beans;

import Sistema.FilaAtendimento;
import Sistema.StatusAtendimento;
import java.util.Random;

public class Ortopedista extends Medico{
    private static final long serialVersionUID = 3894482732346504751L;
    public Ortopedista(String nome, String CPF, String telefone, String CRM, String especialidade){
        super(nome, CPF, telefone, CRM, especialidade);
    }
    public Ortopedista(String nome, String CPF, String telefone, String email, String CRM, String especialidade){
        super(nome, CPF, telefone, email, CRM, especialidade);
    }
    @Override
    public void diagnostica(Paciente paciente){
        new Thread(new Runnable() {
            @Override
            public void run() {
                setStatus(StatusAtendimento.EM_ATENDIMENTO);
                paciente.setStatusAtendimento(StatusAtendimento.EM_ATENDIMENTO);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Ortopedista " + getNome() + " atendendo paciente " + paciente.getNome());
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                paciente.setStatusAtendimento(StatusAtendimento.AGUARDANDO);
                setStatus(StatusAtendimento.AGUARDANDO);
                System.out.println("Ortopedista " + getNome() + " aguardando paciente.");
                Random aleatorio = new Random();
                int num = aleatorio.nextInt();
                if(num % 2 == 0){
                    FilaAtendimento.setPacienteNaFila(paciente.getCPF(), "RaioX");
                }
            }
        }).start();
    }
    @Override
    public String getEspecialidade(){
        return "Ortopedista";
    }
}
