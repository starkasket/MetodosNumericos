package mx.edu.itses.ojdl.MetodosNumericos.services;

import mx.edu.itses.ojdl.MetodosNumericos.domain.Gauss;
import mx.edu.itses.ojdl.MetodosNumericos.domain.GaussJordan;
import mx.edu.itses.ojdl.MetodosNumericos.domain.ReglaCramer;

public interface UnidadIIIService {
    public ReglaCramer AlgoritmoReglaCramer (ReglaCramer modelCramer);
    
    public Gauss AlgoritmoGauss (Gauss modelGauss);
    
    public GaussJordan AlgoritmoGaussJordan (GaussJordan modelGaussJordan);
    
}
