package es.cifpcarlos3.actividad1_6;

import es.cifpcarlos3.actividad1_6.vo.Cancion;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorCanciones {
    public static void main(String[] args) {
        Path dir_base =
                Path.of("src","main","java","es","cifpcarlos3","actividad1_6");
        Path txt = dir_base.resolve("canciones.txt");
        Path json = dir_base.resolve("canciones.json");

        List<Cancion> canciones = getCanciones(txt);

        writeCancionesInJSON(json, canciones);
    }

    public static List<Cancion> getCanciones(Path path) {
        List<Cancion> canciones = new ArrayList<>();
        int lineasLeidas = 0;
        int lineasValidas = 0;
        int lineasInvalidas = 0;
        try (var br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                Cancion cancion = createCancion(partes);
                if (cancion != null) {
                    canciones.add(cancion);
                    lineasLeidas++;
                    lineasValidas++;
                } else {
                    lineasLeidas++;
                    lineasInvalidas++;
                }
            }
        } catch(IOException e) {
            System.out.println("Error al leer el archivo");
        }

        System.out.println("Leidas: " +  lineasLeidas + "  |  Validas: " + lineasValidas + "  |  Invalidas: " + lineasInvalidas);

        return canciones;
    }

    public static Cancion createCancion(String[] partes) {
        if (partes.length != 5) {
            return null;
        }

        for (int i = 0; i < partes.length; i++) {
            partes[i] = partes[i].trim();
        }
        partes[4] = partes[4].toLowerCase();

        int anio;
        try {
            anio = Integer.parseInt(partes[0]);
        } catch(NumberFormatException e) {
            return null;
        }

        if(!partes[3].matches("^([0-5][0-9]):([0-5][0-9])$") && !partes[3].matches("^([0-9]):([0-5][0-9])$")) {
            return null;
        }

        boolean esEspanol;
        try {
            esEspanol = Boolean.parseBoolean(partes[4]);
        } catch(NumberFormatException e) {
            return null;
        }

        Cancion cancion = new Cancion(anio, partes[1], partes[2], partes[3], esEspanol);

        return cancion;
    }

    public static void writeCancionesInJSON (Path path, List<Cancion> canciones) {
        try {
            var mapper = JsonMapper.builder()
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .enable(SerializationFeature.WRAP_ROOT_VALUE)
                    .build();

            mapper.writeValue(path, canciones);

            System.out.println("JSON generado en:");
            System.out.println(path.toString());
        } catch (JacksonException e) {
            System.out.println("Error al generar JSON");
        }
    }
}
