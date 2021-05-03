package Clinica.Sistema.Persistencia;

import java.io.*;
import java.util.Vector;

import Clinica.Beans.Medico;
import Clinica.Beans.Paciente;
import Clinica.Sistema.StatusAtendimento;
import Clinica.Sistema.Cadastro.*;

public class ManipulaArquivo {
    public static void writeFilePacientes(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Clinica\\Sistema\\Persistencia\\Pacientes.dat"));
            oos.writeObject(CadastroPaciente.getVectorPacientes());
            oos.flush();
            oos.close();
        }catch(IOException e){
            System.out.println();
        } 
    }
    public static void readFilePacientes(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Clinica\\Sistema\\Persistencia\\Pacientes.dat"));
            try {
                Vector<Paciente> p = (Vector<Paciente>)ois.readObject();
                ois.close();
                for(Paciente paciente : p){
                    CadastroPaciente.setVectorPacientes(paciente);
                }
            } catch (ClassNotFoundException e) {
                System.out.println();
            }
        }catch(IOException e){
            System.out.println();
        }
    }
    public static void writeFileMedicos(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Clinica\\Sistema\\Persistencia\\Medicos.dat"));
            oos.writeObject(CadastroMedico.getVectorMedicos());
            oos.flush();
            oos.close();
        }catch(IOException e){
            System.out.println("Não foi possível efetuar a escrita");
        } 
    }
    public static void readFileMedicos(){
        try{                                                                 
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Clinica\\Sistema\\Persistencia\\Medicos.dat"));
            try {
                Vector<Medico> m = (Vector<Medico>)ois.readObject();
                ois.close();
                for(Medico medico : m){
                    medico.setStatus(StatusAtendimento.AGUARDANDO);
                    CadastroMedico.setVectorMedicos(medico);
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Classe não encontrada");
            }
        }catch(IOException e){
            System.out.println("Não foi possível fazer a leitura do arquivo");
        }
    }
}
