
package com.mycompany.p2lab2tddgonzalezjilmarlaraanderson;

public class EmpleadoTiempoCompleto extends Empleado {

    public EmpleadoTiempoCompleto(String nombre, String edad, String departamento, double sueldo) {
        super(nombre, edad, departamento, sueldo);
    }

    @Override
    public double calcularSalario() {
        return sueldo;
    }

    @Override
    public String tipoEmpleado() {
        return "Completo";
    }
    
}
