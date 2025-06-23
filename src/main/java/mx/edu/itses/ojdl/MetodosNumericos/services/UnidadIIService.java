package mx.edu.itses.ojdl.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.ojdl.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.ojdl.MetodosNumericos.domain.ReglaFalsa;

public interface UnidadIIService {
   
      public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion);
      public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa regulafalsi);
      public ArrayList<PuntoFijo> AlgoritmoPuntoFijo (PuntoFijo fixedpoint);
      
}
