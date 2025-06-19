package mx.edu.itses.ojdl.MetodosNumericos.web;

import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.ojdl.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.ojdl.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.ojdl.MetodosNumericos.services.Funciones;
import mx.edu.itses.ojdl.MetodosNumericos.services.UnidadIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class Unit2Controller {

    @Autowired
    private UnidadIIService bisectionservice;
    @Autowired
    private UnidadIIService regulafalsiservice;

    @GetMapping("/unit2/formbisection")
    public String formBisection(Model model) {

        Biseccion bisection = new Biseccion();

        model.addAttribute("bisection", bisection);

        return "unit2/bisection/formbisection";
    }

    @PostMapping("/unit2/solvebisection")
    public String solvebisection(Biseccion bisection, Model model) {

        /* double valorFX = Funciones.Ecuacion(bisection.getFX(), bisection.getXL());
         log.info("Valor de FX: " + valorFX);
         */
        var solveBisection = bisectionservice.AlgoritmoBiseccion(bisection);

        model.addAttribute("solveBisection", solveBisection);
        return "/unit2/bisection/solvebisection";
        // return "index";
    }

    @GetMapping("/unit2/formregulafalsi")
    public String formRegulaFalsi(Model model) {

        ReglaFalsa regulafalsi = new ReglaFalsa();

        model.addAttribute("regulafalsi", regulafalsi);

        return "unit2/reglafalsa/formregulafalsi";
    }

    @PostMapping("/unit2/solveregulafalsi")
    public String solvebisection(ReglaFalsa regulafalsi, Model model) {

        var solveRegulaFalsi = regulafalsiservice.AlgoritmoReglaFalsa(regulafalsi);

        model.addAttribute("solveRegulaFalsi", solveRegulaFalsi);
        return "/unit2/reglafalsa/solveregulafalsi";
    }

}
