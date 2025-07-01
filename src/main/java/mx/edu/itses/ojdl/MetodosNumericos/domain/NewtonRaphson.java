/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itses.ojdl.MetodosNumericos.domain;

import lombok.Data;

/**
 *
 * @author StarC
 */
@Data
public class NewtonRaphson {
       private String FX; // Función a evaluar
    private double XI, FXI, FDXI, XII, Ea;
    private int IteracionesMaximas;
}
