package com.mycompany.p2lab2tddgonzalezjilmarlaraanderson;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Servicio {

    private static int emp;
    private static Gestion gc = null;
    private static Empleado empleado;
    private static List<Empleado> listaEmpleados;
    public static Scanner sc;
    private static Validations valid=new Validations();

    public void servicio() {

        listaEmpleados = new ArrayList();
        sc = new Scanner(System.in);

        int opc;
        do {
            opc = mostrarMenu(sc);
            switch (opc) {
                case 1 ->
                    tipoEmpleado(sc);
                case 2 ->
                    buscarEmpleado();
                case 3 ->
                    listarEmpleados();
                case 4 ->
                    ActualizarEmpleado();
                case 5 ->
                     EliminarEmpleado();                  
                case 6 ->
                    System.out.println("\nVuelva pronto");
                default ->
                    System.err.println("\nOpcion Incorrecta. Intentelo de nuevo");
            }
        } while (opc != 6);
    }

    private static int mostrarMenu(Scanner sc) {
        int opc = 0;
        while (true) {
            try {
                System.out.println("\nUniversidad de las Fuerzas Armadas Espe");
                System.out.println("\nGestión de Empleados");
                System.out.print("""                   
                             \nMenu de Busqueda
                             1. Agregar nuevo Empleado
                             2. Buscar Empleado
                             3. Listar Empleados
                             4. Actualizar Informacion del Empleado
                             5. Eliminar Empleado
                             6. Salir
                             \nElegir una opcion: """);
                opc = sc.nextInt();
                sc.nextLine();
                if (opc >= 1 && opc <= 6) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.err.println("\nOpción Incorrecta. Inténtelo de nuevo");
                sc.next();
            }
        }
        return opc;
    }

    private static void tipoEmpleado(Scanner sc) {
        int opc = 0;
        while (true) {
            try {
                System.out.print("""                   
                             \nRegistro de Empleado
                             1. Tiempo Completo
                             2. Tiempo Parcial
                             3. Salir
                             \nElegir una opcion: """);
                opc = sc.nextInt();
                sc.nextLine();
                if (opc >= 1 && opc <= 3) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.err.println("\nOpción Incorrecta. Inténtelo de nuevo");
                sc.next();
            }
        }

        registrarEmpleado(opc);
    }

    public static Empleado buscarEmpleado() {
        if (listaEmpleados.isEmpty()) {
            System.out.println("\nNo hay Empleados registrados");
            return null;
        }
        System.out.println("\nBuscar Empleado");
        System.out.print("\nIngrese el nombre del empleado: ");
        String nombre = sc.nextLine();
        
        for (Empleado emp : listaEmpleados) {
            if (emp.nombre.toLowerCase().equals(nombre.toLowerCase())) {
                System.out.println("\nDatos del Empleado");
                System.out.println("\n----------------------------------");
                System.out.println("Nombre:       " + emp.nombre);
                System.out.println("Edad:         " + emp.edad);
                System.out.println("Departamento: " + emp.departamento);
                System.out.println("Tiempo:       " + emp.tipoEmpleado());
                System.out.println("Sueldo:       " + emp.calcularSalario());
                System.out.println("----------------------------------");
                empleado = emp;
                break;
            }
            System.out.println("\nEmpleado no existe");
        }
        return empleado;
    }

    public static void registrarEmpleado(int opc) {
         Scanner sc = new Scanner(System.in);
        
        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine();
        while (valid.validEmpty(nombre)) {
            System.out.print("\nIngrese un nombre: ");
            nombre = sc.nextLine();
        }
        System.out.print("Ingrese la edad: ");
        String edad = sc.nextLine();
        while (valid.validEmpty(edad) || !valid.validNumber(edad) ||
                valid.validAmougInt(edad, 18d, 80d)) {
            if (valid.validEmpty(edad)) {
                System.out.println("La edad no puede estar vacía.");
            } else if (!valid.validNumber(edad)) {
                System.out.println("Ingrese un número válido.");
            } else if (valid.validAmougInt(edad, 18d, 80d)) {
                System.out.println("La edad debe estar entre 18 y 80.");
            }
            System.out.print("Ingrese una edad: ");
            edad = sc.nextLine();
        }
        System.out.print("Ingrese el departamento: ");
        String depar = sc.nextLine();
        System.out.print("Ingrese salario: ");
        double sueldo = sc.nextDouble();
        while (valid.validEmptyDouble(sueldo) ||
                valid.validAmougDouble(sueldo, 200d, 800d)) {
            if (valid.validEmptyDouble(sueldo)) {
                System.out.println("\nEl salario no puede estar vacío.");
            } else if (valid.validAmougDouble(sueldo, 200d, 800d)) {
                System.out.println("\nSalario debe estar entre 200 y 800 $");
            }
            System.out.print("Ingrese salario: ");
            sueldo = sc.nextDouble();
        }
        if (opc == 1) {
            gc = new EmpleadoTiempoCompleto(nombre, edad, depar, sueldo);
        } else if (opc == 2) {
            gc = new EmpleadoTiempoParcial(nombre, edad, depar, sueldo);
        }
        listaEmpleados.add((Empleado) gc);
        System.out.println("Empleado registrado correctamente.");
    
    }

    public static void listarEmpleados() {
        if(listaEmpleados.isEmpty()){
            System.out.println("\nNo hay empleados registrados\n");
            return;
        }
        System.out.println("\nLista de todos los empleados\n");
        for (Empleado emp : listaEmpleados) {
            System.out.println("----------------------------------");
            System.out.println("Nombre:           " + emp.nombre);
            System.out.println("Edad:             " + emp.edad);
            System.out.println("Departamento:     " + emp.departamento);
            System.out.println("Timpo de trabajo: " + emp.tipoEmpleado());
            System.out.println("Sueldo:           " + emp.calcularSalario());
            System.out.println("----------------------------------");
        }
    }

    public static void ActualizarEmpleado() {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        if (listaEmpleados.isEmpty()) {
            System.out.println("\nNo hay Empleados registrados");
            return;
        }
        System.out.println("\nActualizar Información Empleado");
        System.out.print("\nIngrese el nombre del empleado: ");
        String nombre = sc.nextLine();
        for (Empleado emp : listaEmpleados) {
            if (emp.nombre.toLowerCase().equals(nombre.toLowerCase())) {
                System.out.println("\nDatos del Empleado");
                System.out.print("\nIngrese el nombre: ");
                String nombre2 = sc.nextLine();
                while (valid.validEmpty(nombre2)) {
                    System.out.print("\nIngrese un nombre: ");
                    nombre2 = sc.nextLine();
                }
                System.out.print("Ingrese la edad: ");
                String edad = sc.nextLine();
                while (valid.validEmpty(edad) || !valid.validNumber(edad) || valid.validAmougInt(edad, 18d, 80d)) {
                    if (valid.validEmpty(edad)) {
                        System.out.println("La edad no puede estar vacía.");
                    } else if (!valid.validNumber(edad)) {
                        System.out.println("Ingrese un número válido.");
                    } else if (valid.validAmougInt(edad, 18d, 80d)) {
                        System.out.println("La edad debe estar entre 18 y 80.");
                    }
                    System.out.print("Ingrese una edad: ");
                    edad = sc.nextLine();
                }
                System.out.print("Ingrese el departamento: ");
                String depar = sc.nextLine();
                System.out.print("Ingrese salario: ");
                double sueldo = sc.nextDouble();
                while (valid.validEmptyDouble(sueldo) || valid.validAmougDouble(sueldo, 200d, 800d)) {
                    if (valid.validEmptyDouble(sueldo)) {
                        System.out.println("\nEl salario no puede estar vacío.");
                    } else if (valid.validAmougDouble(sueldo, 200d, 800d)) {
                        System.out.println("\nSalario debe estar entre 200 y 800 $");
                    }
                    System.out.print("Ingrese salario: ");
                    sueldo = sc.nextDouble();
                }
                if (emp instanceof EmpleadoTiempoCompleto) {
                    EmpleadoTiempoCompleto aux1 = new EmpleadoTiempoCompleto(nombre2, edad, depar, sueldo);
                    listaEmpleados.set(i, aux1);
                } else if (emp instanceof EmpleadoTiempoParcial) {
                    EmpleadoTiempoParcial aux2 = new EmpleadoTiempoParcial(nombre2, edad, depar, sueldo);
                    listaEmpleados.set(i, aux2);
                }
                System.out.println("Empleado actualizado correctamente.");
                break;
            }
            i++;
        }
    }

    
    public static void EliminarEmpleado(){
        Scanner sc = new Scanner(System.in);
        int i=0;
        if (listaEmpleados.isEmpty()) {
            System.out.println("\nNo hay Empleados registrados");
            return;
        }
        System.out.println("\nEliminar Empleado");
        System.out.print("\nIngrese el nombre del empleado: ");
        String nombre = sc.nextLine();
        
        for (Empleado emp : listaEmpleados) {            
            if (emp.nombre.toLowerCase().equals(nombre.toLowerCase())) {
                listaEmpleados.remove(i);
                System.out.println("Empleado:"+emp.nombre+", Eliminado con éxito");
                break;                
            }
            i++;  
        }
       
    }
    
}
