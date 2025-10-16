package es.cifpcarlos3.actividad1_4;

import es.cifpcarlos3.actividad1_3.vo.Ciclo;
import es.cifpcarlos3.actividad1_4.vo.FamiliaProfesional;
import es.cifpcarlos3.actividad1_4.vo.Grado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorFormacion2 {
    public static void main(String[] args) {
        File pathFP = new File("./src/main/java/es/cifpcarlos3/actividad1_4" +
                "/familia_profesional.dat");
        File pathGrado = new File("./src/main/java/es/cifpcarlos3/actividad1_4" +
                "/grados.csv");
        File pathCiclo = new File("./src/main/java/es/cifpcarlos3/actividad1_4" +
                "/informacion_ciclos.txt");
        String codFP = args[0];
        String codGrado = args[1];

        FamiliaProfesional familiaProfesional = getFamiliaProfesional(pathFP, codFP);
        Grado grado = getGrado(pathGrado, codGrado);

    }
    public static FamiliaProfesional getFamiliaProfesional(File path, String codigo) {
        FamiliaProfesional familiaProfesional = null;
        if(path.exists()) {
            try(FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader(fr)) {
                String linea;
                while((linea = br.readLine()) != null) {
                    String[] partes = linea.split("=");
                    for(int i = 0; i < partes.length; i++) {
                        partes[i] = validarCadena(partes[i]);
                    }
                    if(partes[0].equals(codigo)) {
                        familiaProfesional.setCodigo(partes[0]);
                        familiaProfesional.setNombre(partes[1]);
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error al leer el archivo");
            }
        }
        return familiaProfesional;
    }

    public static Grado getGrado(File path, String codigo) {
        Grado grado = null;
        if(path.exists()) {
            try(FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader(fr)) {
                String linea;
                while((linea = br.readLine()) != null) {
                    String[] partes = linea.split("#");
                    for(int i = 0; i < partes.length; i++) {
                        partes[i] = validarCadena(partes[i]);
                    }
                    if(partes[0].equals(codigo)) {
                        grado.setCodigo(partes[0]);
                        grado.setNombre(partes[1]);
                        grado.setCategoria(partes[2]);
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error al leer el archivo");
            }
        }
        return grado;
    }

    public static List<Ciclo> getCiclos(File path, FamiliaProfesional familiaProfesional, Grado grado) {
        List<Ciclo> ciclos = new ArrayList<>();
        if(path.exists()) {
            try(FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader(fr)) {
                String linea;
                while((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",");
                    for(int i = 0; i < partes.length; i++) {
                        partes[i] = validarCadena(partes[i]);
                    }
                    if(partes[3].equals(familiaProfesional.getCodigo()) && partes[4].equals(grado.getCodigo())) {
                        Ciclo ciclo = new Ciclo
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error al leer el archivo");
            }
        }
        return ciclos;
    }

    public static String validarCadena(String cadena) {
        cadena = cadena.trim();
        if(cadena.startsWith("'")) {
            cadena = cadena.substring(1);
        } else if(cadena.endsWith("'")) {
            cadena = cadena.substring(0, cadena.length()-1);
        }
        return cadena;
    }
}
