package FPJ.model;

import FPJ.Utils.Map;
import FPJ.Utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private List<Empleado> empleados;
    private Float incremento;
    
    public Empresa(){
        initialize();
    }

    private void initialize(){
        Empleado pedro = new Empleado();
        pedro.setNombre("Pedro");
        pedro.setSueldo((float) 500);

        Empleado juan = new Empleado();
        juan.setNombre("Juan");
        juan.setSueldo((float) 600);

        Empleado jacobo = new Empleado();
        jacobo.setNombre("Jacobo");
        jacobo.setSueldo((float) 700);

        empleados = new ArrayList<Empleado>();
        empleados.add(pedro);
        empleados.add(juan);
        empleados.add(jacobo);
        
        // fijar el incremento
        incremento = new Float(8.5);
    }
    
    public void incrementarSueldoEmpleados1(){
        for (Empleado e : empleados){
            incrementarSueldo(e);
        }
    }

    public void incrementarSueldoEmpleados2(){
        empleados = ListUtils.getInstance().map(empleados, new Map<Empleado, Empleado>() {
            @Override
            public Empleado action(Empleado empleado) {
                return incrementarSueldo(empleado);
            }
        });
    }

    public void mostrarEmpleadosConSueldoMayorA(Float sueldoBase){
        List<Empleado> listaFiltrada = ListUtils.getInstance().filter(
                empleados, empleado -> empleado.getSueldo().compareTo(sueldoBase) > 0);
        imprimirEmpleados3(listaFiltrada);
    }

    public void incrementarSueldoEmpleados3(){
        empleados.forEach(this::incrementarSueldo);
    }
    
    private Empleado incrementarSueldo(Empleado e){
        Float sueldo = e.getSueldo();
        Float incrementoSueldo = sueldo * (incremento / 100);
        
        e.setSueldo(sueldo + incrementoSueldo);
        
        return e;
    }

    public void imprimirEmpleados1(){
        for (Empleado e: empleados){
            System.out.println(e.getNombre() + " gana " + e.getSueldo());
        }
    }

    public void imprimirEmpleados2(){
        ListUtils.getInstance().map(empleados, new Map<Empleado, Object>() {
            @Override
            public Object action(Empleado empleado) {
                System.out.println(empleado.getNombre() + " gana " + empleado.getSueldo());
                
                return null;
            }
        });
    }

    public void imprimirEmpleados3(List<Empleado> lista){
        ListUtils.getInstance().mapM_(lista, empleado -> System.out.println(empleado.getNombre() + " gana " + empleado.getSueldo()));
    }
}
