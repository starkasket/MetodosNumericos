package mx.edu.itses.ojdl.MetodosNumericos;

import org.mariuszgromada.math.mxparser.License;
import org.mariuszgromada.math.mxparser.mXparser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MetodosNumericosApplication {

    public static void main(String[] args) {
        boolean isCallSuccessful = License.iConfirmNonCommercialUse(" Materia Métodos Numéricos");
        String message = License.getUseTypeConfirmationMessage();

        mXparser.consolePrintln("message: " + message);
        
        SpringApplication.run(MetodosNumericosApplication.class, args);
    
        
        
    
    }

}
