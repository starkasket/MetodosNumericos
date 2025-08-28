
package mx.edu.itses.ojdl.MetodosNumericos.services;

import mx.edu.itses.ojdl.MetodosNumericos.domain.DDNewton;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Lagrange;


public interface UnidadIVService {
    public DDNewton algoritmoDDNewton(DDNewton DDNewton);
    
    public Lagrange algoritmoLagrange(Lagrange lagrange);
}
