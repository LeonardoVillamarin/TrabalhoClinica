package Clinica.Sistema;

import Clinica.Sistema.Cadastro.*;
import Clinica.Sistema.Persistencia.ManipulaArquivo;

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
