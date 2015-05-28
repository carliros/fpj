package FPJ;

import FPJ.model.Empresa;

public class App {
    public static void main( String[] args ) {
        Empresa empresa = new Empresa();
        empresa.imprimirEmpleados1();
        Float sueldoBase = (float)500;
        System.out.println("*** Empleados con sueldo base mayor a " + sueldoBase);
        empresa.mostrarEmpleadosConSueldoMayorA(sueldoBase);
        System.out.println("***");
        empresa.incrementarSueldoEmpleados3();

        empresa.imprimirEmpleados2();
        System.out.println("*** Empleados con sueldo base mayor a " + sueldoBase + " (despues del incremento)");
        empresa.mostrarEmpleadosConSueldoMayorA(sueldoBase);
        System.out.println("***");
    }
}
