package org.example;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class Oferta {
    @Getter@Setter
    private Producto producto;
    @Getter@Setter
    private Descuento descuento;
    @Getter@Setter
    private String fechaDesde;
    @Getter@Setter
    private String fechaHasta;
}
