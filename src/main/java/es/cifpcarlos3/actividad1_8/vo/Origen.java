package es.cifpcarlos3.actividad1_8.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Origen {
    private String pais;
    private String region;

    @Override
    public String toString() {
        return "Origen{" + "pais=" + pais + ", region=" + region + '}';
    }
}