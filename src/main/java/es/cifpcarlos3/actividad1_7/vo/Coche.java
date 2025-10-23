package es.cifpcarlos3.actividad1_7.vo;


import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "coche")
public class Coche {
    private String marca;
    private String modelo;
    private String color;
    private int anio;
}
