package es.cifpcarlos3.actividad1_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class App1_1 {
    public static void main(String[] args) {
        File path = new File("./src/main/java/es/cifpcarlos3/actividad1_1" +
                "/informacion_ciclos.txt");

        if(path.exists()) {
            try(FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader(fr)) {
                String linea;
                while((linea = br.readLine()) != null) {
                    if(linea.startsWith("'")) {
                        linea = linea.trim();
                        String[] partes = linea.split(",");
                        if(partes.length == 4) {
                            for (int i = 0; i < partes.length; i++) {
                                partes[i] = partes[i].trim();
                                if(partes[i].startsWith("'") && partes[i].endsWith("'")) {
                                    partes[i] = partes[i].substring(1, partes[i].length()-1);
                                }
                            }
                            System.out.println("INSERT INTO T_CICLO VALUES(" + partes[0] + ", " + partes[1] + ", " + partes[2] + ", " + partes[3] + ");");
                        }
                    }
                    /**
                     * if(linea.matches("^'[A-Za-z]{3}[0-9]{2}'.*$")) {
                     *      System.out.println("INSERT INTO T_CICLO VALUES(" + linea + ");")
                     * }
                     * */
                }
            } catch (IOException ex) {
                System.out.println("Error al leer el archivo");
            }
        }
    }
}
