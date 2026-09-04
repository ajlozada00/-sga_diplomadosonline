import java.util.Scanner;
import java.io.FileWriter;   // Herramienta para escribir archivos
import java.io.IOException;  // Herramienta para atrapar errores si el archivo falla

public class Main {
    public static void main(String[] args) {
        
        // Inicializamos el lector del teclado (Equivalente al input de Python)
        Scanner teclado = new Scanner(System.in);
        
        // Nuestro interruptor para el ciclo while
        boolean sistemaEncendido = true;

        while (sistemaEncendido) {
            
            // ===================================================================
            // MENÚ PRINCIPAL
            // ===================================================================
            System.out.println("\n=========================================");
            System.out.println("   SISTEMA DE GESTIÓN ACADÉMICA (SGA)    ");
            System.out.println("=========================================");
            System.out.println("1. Registrar Alumno");
            System.out.println("2. Registrar Profesor");
            System.out.println("7. Salir");
            System.out.println("=========================================");
            System.out.print("Seleccione una opción: ");

            // Leemos lo que escribe el usuario
            String opcion = teclado.nextLine();

            // ===================================================================
            // OPCIÓN 1: REGISTRAR ALUMNO
            // ===================================================================
            if (opcion.equals("1")) {
                System.out.println("\n--- Usted ha seleccionado: Registrar Alumno ---");
                
                System.out.print("Ingrese la cédula del alumno: ");
                String cedula = teclado.nextLine();
                
                System.out.print("Ingrese el nombre del alumno: ");
                String nombre = teclado.nextLine();
                
                System.out.print("Ingrese el correo del alumno: ");
                String correo = teclado.nextLine();
                
                System.out.print("Ingrese el programa del alumno: ");
                String programa = teclado.nextLine();

                // 1. Usamos la clase Alumno para empaquetar los datos
                Alumno nuevo_alumno = new Alumno(cedula, nombre, correo, programa);

                // 2. Bloque Try-Catch obligatorio para manejo de archivos en Java
                try {
                    // El 'true' significa "Append" (Agregar al final del archivo sin borrar)
                    FileWriter archivo = new FileWriter("alumnos.txt", true);
                    
                    // Armamos la línea separada por comas y con un salto de línea al final (\n)
                    archivo.write(nuevo_alumno.cedula + "," + nuevo_alumno.nombre + "," + nuevo_alumno.correo + "," + nuevo_alumno.programa + "\n");
                    
                    archivo.close();
                    System.out.println("¡Alumno registrado y guardado exitosamente!");
                    
                } catch (IOException e) {
                    System.out.println("Hubo un error al guardar el archivo del alumno.");
                }

            // ===================================================================
            // OPCIÓN 2: REGISTRAR PROFESOR
            // ===================================================================
            } else if (opcion.equals("2")) {
                System.out.println("\n--- Usted ha seleccionado: Registrar Profesor ---");
                
                System.out.print("Ingrese la cédula del profesor: ");
                String cedula = teclado.nextLine();
                
                System.out.print("Ingrese el nombre del profesor: ");
                String nombre = teclado.nextLine();
                
                System.out.print("Ingrese el correo del profesor: ");
                String correo = teclado.nextLine();
                
                System.out.print("Ingrese la materia del profesor: ");
                String materia = teclado.nextLine();

                // 1. Usamos la clase Profesor para empaquetar
                Profesor nuevo_profesor = new Profesor(cedula, nombre, correo, materia);

                // 2. Guardamos en su archivo correspondiente
                try {
                    FileWriter archivo = new FileWriter("profesores.txt", true);
                    archivo.write(nuevo_profesor.cedula + "," + nuevo_profesor.nombre + "," + nuevo_profesor.correo + "," + nuevo_profesor.materia + "\n");
                    archivo.close();
                    System.out.println("¡Profesor registrado y guardado exitosamente!");
                    
                } catch (IOException e) {
                    System.out.println("Hubo un error al guardar el archivo del profesor.");
                }

            // ===================================================================
            // OPCIÓN 7: SALIR
            // ===================================================================
            } else if (opcion.equals("7")) {
                System.out.println("\nGuardando cambios pendientes...");
                System.out.println("Cerrando el Sistema de Gestión Académica. ¡Hasta luego!");
                
                // Apagamos el interruptor para que el ciclo while termine naturalmente
                sistemaEncendido = false;
                
            // ===================================================================
            // MANEJO DE ERRORES
            // ===================================================================
            } else {
                System.out.println("\n¡Error! Opción no válida. Por favor, ingrese un número del menú.");
            }
        }
        
        // Siempre es buena práctica cerrar el teclado al salir del sistema
        teclado.close();
    }
}