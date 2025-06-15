package mx.edu.itses.ojdl.MetodosNumericos.domain;

import lombok.Data;

@Data
public class Biseccion {
    private String FX; // Función a evaluar
    private double XL, XU, XR, FXL, FXU, FXR, Ea;
    private int IteracionesMaximas; 
    
    
}
