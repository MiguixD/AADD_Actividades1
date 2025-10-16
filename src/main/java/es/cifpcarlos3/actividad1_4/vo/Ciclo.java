package es.cifpcarlos3.actividad1_4.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ciclo {
    String codigo;
    String descripcion;
    int numeroHoras;
    String familiaProfesional;
    String codigoCiclo;

    @Override
    public String toString() {
        return codigo + ", " + descripcion + ", " + numeroHoras + ", " + familiaProfesional;
    }
}
