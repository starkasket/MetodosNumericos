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
public class Secante {
    private String FX; // Función a evaluar
    private double XMI, XI, XII, FXMI, FXI, Ea; //XMI = Xi - 1; XII = Xi + 1
    private int IteracionesMaximas;
}
