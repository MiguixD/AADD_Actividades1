package es.cifpcarlos3.actividad1_7.vo;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "catalogo")
public class CatalogoCoches {
    @JacksonXmlElementWrapper(localName = "coches")
    @JacksonXmlProperty(localName = "coche")
    private List<Coche> coches;
}
