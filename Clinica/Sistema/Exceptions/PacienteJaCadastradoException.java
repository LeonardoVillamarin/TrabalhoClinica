package Clinica.Sistema.Exceptions;

public class PacienteJaCadastradoException extends Exception {
    public PacienteJaCadastradoException() {
        super("Paciente já está cadastrado");
    }
}
