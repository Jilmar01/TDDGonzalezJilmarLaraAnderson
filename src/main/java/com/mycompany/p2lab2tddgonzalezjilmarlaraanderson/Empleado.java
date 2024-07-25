
package com.mycompany.p2lab2tddgonzalezjilmarlaraanderson;

public abstract class Empleado implements Gestion{
    
    protected String nombre;
    protected String edad;
    protected String departamento;
    protected double sueldo;

    public Empleado(String nombre, String edad, String departamento, double sueldo) {
        this.nombre = nombre;
        this.edad = edad;
        this.departamento = departamento;
        this.sueldo = sueldo;
    }

    
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad + ", Departamento: " + departamento;
    }
       
}
