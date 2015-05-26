package FPJ;

import FPJ.model.Empresa;

public class App {
    public static void main( String[] args ) {
        Empresa empresa = new Empresa();
        empresa.imprimirEmpleados1();
        
        empresa.incrementarSueldoEmpleados2();

        empresa.imprimirEmpleados2();
    }
}
