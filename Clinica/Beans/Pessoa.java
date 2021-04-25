package Beans;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = -3170410619400014900L;
    private String nome;
    private String CPF;
    private String telefone;
    private String email;

    public Pessoa(String nome, String CPF, String telefone){
        this.nome = nome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.email = null;
    }
    public Pessoa(String nome, String CPF, String telefone, String email){
        this.nome = nome; 
        this.CPF = CPF;
        this.telefone = telefone;
        this.email = email;
    }
    public String getNome(){
        return this.nome;
    }
    public String getCPF(){
        return CPF;
    }    
}