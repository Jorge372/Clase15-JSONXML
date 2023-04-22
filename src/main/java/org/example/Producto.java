package org.example;

import lombok.*;

@RequiredArgsConstructor
public class Producto {
    @Getter@Setter@NonNull
    private String nombre;
    @Getter
    private Object[] peso =new Object[2];
    @Getter@Setter
    private float precio;
    public void setPeso(float p, String u){
        peso[0]=p;
        peso[1]=u;
    }
    @Override
    public String toString(){
        return nombre+" - "+peso[0]+peso[1]+" - "+precio;
    }
}
