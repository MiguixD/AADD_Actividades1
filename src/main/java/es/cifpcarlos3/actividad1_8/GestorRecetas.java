package es.cifpcarlos3.actividad1_8;

import es.cifpcarlos3.actividad1_8.vo.Receta;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GestorRecetas {
    public static void main(String[] args) {
        Path base = Path.of("src","main","java","es","cifpcarlos3","actividad1_8");
        Path json = base.resolve("receta1.json");

        leerJson(json);
    }
    public static void leerJson(Path path) {
        var mapper = JsonMapper.builder().build();
        try (var reader = Files.newBufferedReader(path)) {
            Receta receta = mapper.readValue(reader, Receta.class);
            System.out.println(receta.toString());

        } catch (IOException ex) {
            System.out.println("Error al leer el JSON");
        }
    }
}
