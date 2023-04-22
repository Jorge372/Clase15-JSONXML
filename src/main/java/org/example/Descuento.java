package org.example;

import lombok.*;

@AllArgsConstructor
public class Descuento {
    @Getter@Setter
    private String tipo;
    @Getter@Setter
    private float cantidad;
    @Getter@Setter
    private float tope;

    @Override
    public String toString(){
        return tipo+" - "+cantidad+" - "+tope;
    }
}

