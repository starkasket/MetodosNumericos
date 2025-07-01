package mx.edu.itses.ojdl.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.ojdl.MetodosNumericos.domain.NewtonRaphson;
import mx.edu.itses.ojdl.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.ojdl.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Secante;
import mx.edu.itses.ojdl.MetodosNumericos.domain.SecanteModificado;

public interface UnidadIIService {
   
      public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion);
      public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa regulafalsi);
      public ArrayList<PuntoFijo> AlgoritmoPuntoFijo (PuntoFijo fixedpoint);
      public ArrayList<NewtonRaphson> AlgoritmoNewtonRaphon (NewtonRaphson newtonraphson);
      public ArrayList<Secante> AlgoritmoSecante (Secante secant);
      public ArrayList<SecanteModificado> AlgoritmoSecanteModificado  (SecanteModificado modsecant);
      
}
