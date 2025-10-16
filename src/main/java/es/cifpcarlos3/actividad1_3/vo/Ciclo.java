package es.cifpcarlos3.actividad1_3.vo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ciclo {
    String codigo;
    String descripcion;
    int numeroHoras;
    String familiaProfesional;

    @Override
    public String toString() {
        return codigo + ", " + descripcion + ", " + numeroHoras + ", " + familiaProfesional;
    }
}
