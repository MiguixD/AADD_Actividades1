package es.cifpcarlos3.actividad1_4;

import es.cifpcarlos3.actividad1_4.vo.Ciclo;
import es.cifpcarlos3.actividad1_4.vo.FamiliaProfesional;
import es.cifpcarlos3.actividad1_4.vo.Grado;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
        File pathListaSerializada = new File("./src/main/java/es/cifpcarlos3/actividad1_4" +
                "/lista_ciclos.ser");
        String codFP = args[0];
        String codGrado = args[1];

        FamiliaProfesional familiaProfesional = getFamiliaProfesional(pathFP, codFP);
        Grado grado = getGrado(pathGrado, codGrado);

        List<Ciclo> ciclos = getCiclos(pathCiclo, familiaProfesional, grado);

        System.out.println("Familia profesional: " + familiaProfesional.getNombre() + "\n");
        escribirEnArchivoBinario(pathListaSerializada, "Familia profesional: " + familiaProfesional.getNombre() + "\n");
        System.out.println("Grado seleccionado: " +  grado.getNombre() + "\n");
        escribirEnArchivoBinario(pathListaSerializada, "Grado seleccionado: " +  grado.getNombre() + "\n");
        System.out.println("Ciclos encontrados:\n");
        escribirEnArchivoBinario(pathListaSerializada, "Ciclos encontrados:\n");

        for(Ciclo ciclo: ciclos) {
            System.out.println("El ciclo " + ciclo.getDescripcion() + " en la familia de " +
                    familiaProfesional.getNombre() + " es un " + grado.getNombre() + ".");
            escribirEnArchivoBinario(pathListaSerializada, "El ciclo " + ciclo.getDescripcion() + " en la familia de " +
                    familiaProfesional.getNombre() + " es un " + grado.getNombre() + ".");
        }

    }
    public static FamiliaProfesional getFamiliaProfesional(File path, String codigo) {
        FamiliaProfesional familiaProfesional = new FamiliaProfesional();
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
        Grado grado = new Grado();
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
                        Ciclo ciclo = new Ciclo(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], partes[4]);
                        ciclos.add(ciclo);
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error al leer el archivo");
            }
        }
        return ciclos;
    }

    public static void escribirEnArchivoBinario(File path, String texto) {
        if(!path.exists()) {
            try {
                path.createNewFile();
            } catch (IOException ex) {
                System.out.println("Error al crear el archivo");
            }
        }
        try(FileOutputStream fos = new FileOutputStream(path);
            BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
            bos.write(bytes);
        } catch (IOException ex) {
            System.out.println("Error al escribir archivo");
        }
    }

    public static String validarCadena(String cadena) {
        cadena = cadena.trim();
        if(cadena.startsWith("'")) {
            cadena = cadena.substring(1);
        }
        if(cadena.endsWith("'")) {
            cadena = cadena.substring(0, cadena.length()-1);
        }
        return cadena;
    }
}
