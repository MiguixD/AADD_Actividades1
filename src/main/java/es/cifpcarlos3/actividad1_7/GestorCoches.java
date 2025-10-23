package es.cifpcarlos3.actividad1_7;

import es.cifpcarlos3.actividad1_6.vo.Cancion;
import es.cifpcarlos3.actividad1_7.vo.CatalogoCoches;
import es.cifpcarlos3.actividad1_7.vo.Coche;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorCoches {
    public static void main(String[] args) {
        Path dir_base =
                Path.of("src","main","java","es","cifpcarlos3","actividad1_7");
        Path txt = dir_base.resolve("coches.txt");
        Path xml = dir_base.resolve("coches.xml");

        CatalogoCoches catalogoCoches = new CatalogoCoches(getCoches(txt));

        writeCochesInXML(xml, catalogoCoches);
    }

    public static List<Coche> getCoches(Path path) {
        List<Coche> coches = new ArrayList<>();
        int lineasLeidas = 0;
        int lineasValidas = 0;
        int lineasInvalidas = 0;
        try (var br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                Coche coche = createCoche(partes);
                if (coche != null) {
                    coches.add(coche);
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

        return coches;
    }

    public static Coche createCoche(String[] partes) {
        if (partes.length != 4) {
            return null;
        }

        for (int i = 0; i < partes.length; i++) {
            partes[i] = partes[i].trim();
        }

        int anio;
        try {
            anio = Integer.parseInt(partes[3]);
        } catch(NumberFormatException e) {
            return null;
        }

        Coche coche = new Coche(partes[0], partes[1], partes[2], anio);

        return coche;
    }

    public static void writeCochesInXML (Path path, CatalogoCoches catalogoCoches) {
        try {
            var mapper = new XmlMapper();
            var writer = mapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(path, catalogoCoches);

            System.out.println("XML generado en:");
            System.out.println(path.toString());
        } catch (JacksonException e) {
            System.out.println("Error al generar XML");
        }
    }
}
