package FPJ.model;

import FPJ.Utils.Functor;
import FPJ.Utils.Mapping;

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
        pedro.setSueldo(new Float(500));

        Empleado juan = new Empleado();
        juan.setNombre("Juan");
        juan.setSueldo(new Float(600));

        Empleado jacobo = new Empleado();
        jacobo.setNombre("Jacobo");
        jacobo.setSueldo(new Float(700));

        empleados = new ArrayList();
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
        empleados = Mapping.getInstance().map(empleados, new Functor<Empleado, Empleado>() {
            @Override
            public Empleado fmap(Empleado empleado) {
                return incrementarSueldo(empleado);
            }
        });
    }

    public void incrementarSueldoEmpleados3(){
        
        empleados = Mapping.getInstance().map(empleado -> incrementarSueldo(empleado));
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
        Mapping.getInstance().map(empleados, new Functor<Empleado, Object>() {
            @Override
            public Object fmap(Empleado empleado) {
                System.out.println(empleado.getNombre() + " gana " + empleado.getSueldo());
                
                return null;
            }
        });
    }
}
