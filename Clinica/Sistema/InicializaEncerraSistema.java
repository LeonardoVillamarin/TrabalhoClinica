package Sistema;

import Sistema.Persistencia.ManipulaArquivo;
import Sistema.Cadastro.*;

public abstract class InicializaEncerraSistema {
    static boolean statusFuncionamento = true;  
    protected static void startSystem(){
        ManipulaArquivo.readFileMedicos();
        ManipulaArquivo.readFilePacientes();
    }
    protected static void closeSystem(){
        if(!CadastroMedico.getVectorMedicos().isEmpty()){
            ManipulaArquivo.writeFileMedicos();
        }
        if(!CadastroPaciente.getVectorPacientes().isEmpty()){
            ManipulaArquivo.writeFilePacientes();
        }
        statusFuncionamento = false;
    }
}
