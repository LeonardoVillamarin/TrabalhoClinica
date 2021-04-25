package Sistema.Exceptions;

public abstract class LancaExceptions {
    public static void lancaPacienteJaCadastradoException() throws PacienteJaCadastradoException{
        throw new PacienteJaCadastradoException();
    }
    public static void lancaMedicoJaCadastradoException() throws MedicoJaCadastradoException{
        throw new MedicoJaCadastradoException();
    }
}
