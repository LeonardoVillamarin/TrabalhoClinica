package Clinica.Sistema.Exceptions;

public class MedicoJaCadastradoException extends Exception {
    public MedicoJaCadastradoException(){
        super("Médico já está cadastrado");
    }
}
