package mx.edu.itses.ojdl.MetodosNumericos.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.ojdl.MetodosNumericos.domain.NewtonRaphson;
import mx.edu.itses.ojdl.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.ojdl.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Secante;
import mx.edu.itses.ojdl.MetodosNumericos.domain.SecanteModificado;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadIIServiceImpl implements UnidadIIService {

    @Override
    public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion) {
        ArrayList<Biseccion> respuesta = new ArrayList<>();
        double XL, XU, XRa, XRn, FXL, FXU, FXR, Ea;

        XL = biseccion.getXL();
        XU = biseccion.getXU();
        XRa = 0;
        Ea = 100;
        // Verificamos que en el intervalo definido haya un cambio de signo
        FXL = Funciones.Ecuacion(biseccion.getFX(), XL);
        FXU = Funciones.Ecuacion(biseccion.getFX(), XU);
        if (FXL * FXU < 0) {
            for (int i = 1; i <= biseccion.getIteracionesMaximas(); i++) {
                XRn = (XL + XU) / 2;
                FXL = Funciones.Ecuacion(biseccion.getFX(), XL);
                FXU = Funciones.Ecuacion(biseccion.getFX(), XU);
                FXR = Funciones.Ecuacion(biseccion.getFX(), XRn);
                if (i != 1) {
                    Ea = Funciones.ErrorRelativo(XRn, XRa);
                }
                Biseccion renglon = new Biseccion();
                renglon.setXL(XL);
                renglon.setXU(XU);
                renglon.setXR(XRn);
                renglon.setFXL(FXL);
                renglon.setFXU(FXU);
                renglon.setFXR(FXR);
                renglon.setEa(Ea);
                if (FXL * FXR < 0) {
                    XU = XRn;
                } else if (FXL * FXR > 0) {
                    XL = XRn;
                } else if (FXL * FXR == 0) {
                    break;
                }
                XRa = XRn;
                respuesta.add(renglon);
                if (Ea <= biseccion.getEa()) {
                    break;
                }
            }
        } else {
            Biseccion renglon = new Biseccion();
            //renglon.setIntervaloInvalido(true);
            respuesta.add(renglon);
        }

        return respuesta;
    }

    @Override
    public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa regulafalsi) {
        ArrayList<ReglaFalsa> respuesta = new ArrayList<>();
        double XL, XU, XRa, XRn, FXL, FXU, FXR, Ea;

        XL = regulafalsi.getXL();
        XU = regulafalsi.getXU();
        XRa = 0;
        Ea = 100;
        FXL = Funciones.Ecuacion(regulafalsi.getFX(), XL);
        FXU = Funciones.Ecuacion(regulafalsi.getFX(), XU);
        System.out.println(Funciones.Derivada(regulafalsi.getFX(), 2));

        if (FXL * FXU < 0) {
            for (int i = 1; i <= regulafalsi.getIteracionesMaximas(); i++) {
                FXL = Funciones.Ecuacion(regulafalsi.getFX(), XL);
                FXU = Funciones.Ecuacion(regulafalsi.getFX(), XU);
                XRn = XU - ((FXU * (XL - XU)) / (FXL - FXU));
                FXR = Funciones.Ecuacion(regulafalsi.getFX(), XRn);
                if (i != 1) {
                    Ea = Funciones.ErrorRelativo(XRn, XRa);
                }
                ReglaFalsa renglon = new ReglaFalsa();
                renglon.setXL(XL);
                renglon.setXU(XU);
                renglon.setXR(XRn);
                renglon.setFXL(FXL);
                renglon.setFXU(FXU);
                renglon.setFXR(FXR);

                renglon.setEa(Ea);
                if (FXL * FXR < 0) {
                    XU = XRn;
                } else if (FXL * FXR > 0) {
                    XL = XRn;
                } else if (FXL * FXR == 0) {
                    break;
                }
                XRa = XRn;
                System.out.println(XRa);
                respuesta.add(renglon);
                if (Ea <= regulafalsi.getEa()) {
                    break;
                }
            }
        } else {
            ReglaFalsa renglon = new ReglaFalsa();
            //renglon.setIntervaloInvalido(true);
            respuesta.add(regulafalsi);
        }

        return respuesta;
    }

    @Override
    public ArrayList<PuntoFijo> AlgoritmoPuntoFijo(PuntoFijo fixedpoint) {
        ArrayList<PuntoFijo> respuesta = new ArrayList<>();
        double XI, GXI, Ea;
        XI = fixedpoint.getXI();
        //     GXI = Funciones.Ecuacion(fixedpoint.getFX(), XI);
        Ea = 100;
        for (int i = 1; i < fixedpoint.getIteracionesMaximas(); i++) {
            GXI = Funciones.Ecuacion(fixedpoint.getFX(), XI);
            if (i != 1) {
                Ea = Funciones.ErrorRelativo(GXI, XI);
            }
            PuntoFijo renglon = new PuntoFijo();
            renglon.setXI(XI);
            renglon.setGXI(GXI);
            renglon.setEa(Ea);
            XI = GXI;
            respuesta.add(renglon);
            if (Ea <= fixedpoint.getEa()) {
                break;
            }
        }
        return respuesta;
    }

    @Override
    public ArrayList<NewtonRaphson> AlgoritmoNewtonRaphon(NewtonRaphson newtonraphson) {
        ArrayList<NewtonRaphson> respuesta = new ArrayList<>();
        double XI, FXI, FDXI, XII, Ea;

        XI = newtonraphson.getXI();
        FXI = Funciones.Ecuacion(newtonraphson.getFX(), XI);
        FDXI = Funciones.Derivada(newtonraphson.getFX(), XI);

        Ea = 100;
        for (int i = 1; i < newtonraphson.getIteracionesMaximas(); i++) {
            FXI = Funciones.Ecuacion(newtonraphson.getFX(), XI);
            FDXI = Funciones.Derivada(newtonraphson.getFX(), XI);
            XII = XI - (FXI / FDXI);
            if (i != 1) {
                Ea = Funciones.ErrorRelativo(XII, XI);
            }
            NewtonRaphson renglon = new NewtonRaphson();
            renglon.setXI(XI);
            renglon.setFXI(FXI);
            renglon.setFDXI(FDXI);
            renglon.setXII(XII);
            renglon.setEa(Ea);
            XI = XII;

            respuesta.add(renglon);
            if (Ea <= newtonraphson.getEa()) {
                break;
            }

        }
        return respuesta;

    }

    @Override
    public ArrayList<Secante> AlgoritmoSecante(Secante secant) {
        ArrayList<Secante> respuesta = new ArrayList<>();
        double XMI, XI, XII, FXMI, FXI, Ea;
        XMI = secant.getXMI();
        XI = secant.getXI();
        FXI = Funciones.Ecuacion(secant.getFX(), XI);
        FXMI = Funciones.Ecuacion(secant.getFX(), XMI);
        // XII = XI - ((FXI * (XMI - XI)) / (FXMI - FXI));
        Ea = 100;
        for (int i = 1; i < secant.getIteracionesMaximas(); i++) {
            FXI = Funciones.Ecuacion(secant.getFX(), XI);
            FXMI = Funciones.Ecuacion(secant.getFX(), XMI);
            XII = XI - ((FXI * (XMI - XI)) / (FXMI - FXI));
          
                Ea = Funciones.ErrorRelativo(XII, XI);
            
            Secante renglon = new Secante();
            renglon.setXI(XI);
            renglon.setXMI(XMI);
            renglon.setXII(XII);
            renglon.setFXI(FXI);
            renglon.setFXMI(FXMI);
            renglon.setEa(Ea);
            XMI = XI;
            XI = XII;
            respuesta.add(renglon);
            if (Ea <= secant.getEa()) {
                break;
            }
        }
        return respuesta;

    }

    @Override
    public ArrayList<SecanteModificado> AlgoritmoSecanteModificado(SecanteModificado modsecant) {
        ArrayList<SecanteModificado> respuesta = new ArrayList<>();
        double Sigma, XI, XII, FXIS, FXI, Ea;
        Sigma = modsecant.getSigma();
        XI = modsecant.getXI();
        FXI = Funciones.Ecuacion(modsecant.getFX(), XI);
        FXIS = Funciones.Ecuacion(modsecant.getFX(), (XI + XI * Sigma));
        XII = XI - ((Sigma * XI * FXI) / (FXIS - FXI));
         System.out.println(XI + (XI * Sigma));
        Ea = 100;
        for (int i = 1; i < modsecant.getIteracionesMaximas(); i++) {
            FXI = Funciones.Ecuacion(modsecant.getFX(), XI);
           
            FXIS = Funciones.Ecuacion(modsecant.getFX(), (XI + XI * Sigma));
            XII = XI - ((Sigma * XI * FXI) / (FXIS - FXI));
        
                Ea = Funciones.ErrorRelativo(XII, XI);
            
            SecanteModificado renglon = new SecanteModificado();
            renglon.setXI(XI);
            renglon.setXII(XII);
            renglon.setSigma(Sigma);
            renglon.setFXI(FXI);
            renglon.setFXIS(FXIS);
            renglon.setEa(Ea);
            XI = XII;
            respuesta.add(renglon);
            if (Ea<= modsecant.getEa()) {
                break;
            }
        }
return respuesta;
    }

}
