package es.cifpcarlos3.actividad1_3.vo;

import lombok.*;

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
