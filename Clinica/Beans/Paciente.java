package Beans;

import Sistema.StatusAtendimento;

public class Paciente extends Pessoa {
    private static final long serialVersionUID = 6326713741392272817L;
    private String opcaoPagamento;
    private StatusAtendimento status;

    public Paciente(String nome, String CPF, String telefone, String opcaoPagamento){
        super(nome, CPF, telefone);
        this.opcaoPagamento = opcaoPagamento;
    }
    public Paciente(String nome, String CPF, String telefone, String email, String opcaoPagamento){
        super(nome, CPF, telefone, email);
        this.opcaoPagamento = opcaoPagamento;
    }
    public String getOpcaoPagamento(){
        return this.opcaoPagamento;
    }
    public void setStatusAtendimento(StatusAtendimento status){
        this.status = status;
    }
    public StatusAtendimento getStatus(){
        return this.status;
    }
}
