package es.cifpcarlos3.actividad1_8.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receta {
    @JsonProperty("nombre")
    private String nombre;
    private String tipo;
    private Origen origen;
    @JsonProperty("ingredientes")
    private List<Ingrediente> ingredientes;

    @Override
    public String toString() {
        return "Receta\nNombre: " + nombre + "\nTipo: " + tipo + "\nOrigen: "+ origen +"\nIngredientes: " + ingredientes;
    }
}

