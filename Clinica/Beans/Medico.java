package Beans;

import Sistema.StatusAtendimento;

public abstract class Medico extends Pessoa{
    private static final long serialVersionUID = 3769602414875702428L;
    private String CRM;
    private String especialidade;
    private StatusAtendimento status;
    
    public Medico(String nome, String CPF, String telefone, String CRM, String especialidade){
        super(nome, CPF, telefone);
        this.CRM = CRM;
        this.especialidade = especialidade;
        this.status = StatusAtendimento.AGUARDANDO;
    }
    public Medico(String nome, String CPF, String telefone, String email, String CRM, String especialidade){
        super(nome, CPF, telefone, email);
        this.CRM = CRM;
        this.especialidade = especialidade;
        this.status = StatusAtendimento.AGUARDANDO;
    }
    public StatusAtendimento getStatus(){
        return this.status;
    }
    public void setStatus(StatusAtendimento status){
        this.status = status;
    }
    public void diagnostica(Paciente paciente){

    }
    public String getEspecialidade(){
        return this.especialidade;
    }
    public String getCRM(){
        return this.CRM;
    }
}
