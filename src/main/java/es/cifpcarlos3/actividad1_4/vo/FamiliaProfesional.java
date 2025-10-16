package es.cifpcarlos3.actividad1_4.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamiliaProfesional {
    String codigo;
    String nombre;

    @Override
    public String toString() {
        return codigo + ", " + nombre;
    }
}
