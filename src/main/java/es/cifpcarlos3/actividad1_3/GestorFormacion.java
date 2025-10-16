package es.cifpcarlos3.actividad1_3;

import es.cifpcarlos3.actividad1_3.vo.Ciclo;
import es.cifpcarlos3.actividad1_3.vo.FamiliaProfesional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorFormacion {
    public static void main(String[] args) {
        FamiliaProfesional familiaProfesional = new FamiliaProfesional();
        List<Ciclo> ciclos = new ArrayList<Ciclo>();
        File pathFP = new File("./src/main/java/es/cifpcarlos3/actividad1_3" +
                "/familia_profesional.dat");
        File pathCiclos = new File("./src/main/java/es/cifpcarlos3/actividad1_3" +
                "/informacion_ciclos.txt");

        if(pathFP.exists()) {
            try(FileReader fr = new FileReader(pathFP);
                BufferedReader br = new BufferedReader(fr)) {
                String linea;
                while((linea = br.readLine()) != null) {
                    String[] partes = linea.split("=");
                    if(partes[0].equals(args[0])) {
                        familiaProfesional.setCodigo(partes[0]);
                        familiaProfesional.setNombre(partes[1]);
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error al leer el archivo");
            }
        }

        if(pathCiclos.exists()) {
            try(FileReader fr = new FileReader(pathCiclos);
                BufferedReader br = new BufferedReader(fr)) {
                String linea;
                while((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",");
                    for(int i = 0; i < partes.length; i++) {
                        partes[i] = partes[i].trim();
                    }
                    if(partes[3].equals("'" + familiaProfesional.getCodigo() + "'")) {
                        try {
                            Ciclo ciclo = new Ciclo(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3]);
                            ciclos.add(ciclo);
                        } catch (Exception e) {
                            System.err.println("Error, se esperaba un número");
                        }

                    }
                }
            } catch (IOException ex) {
                System.out.println("Error al leer el archivo");
            }

            System.out.println("Familia profesional: " + familiaProfesional.toString() + "\n");
            System.out.println("Listado de ciclos: \n");
            for(Ciclo ciclo : ciclos) {
                System.out.println(ciclo.toString() + "\n");
            }

        }
    }
}
