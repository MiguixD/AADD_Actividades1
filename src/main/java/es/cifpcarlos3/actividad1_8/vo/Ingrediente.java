package es.cifpcarlos3.actividad1_8.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {
    private String nombre;
    private String cantidad;

    @Override
    public String toString() {
        return "Ingrediente{" + "nombre=" + nombre + ", cantidad=" + cantidad + '}';
    }
}
