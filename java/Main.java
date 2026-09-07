import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
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
            System.out.println("3. Registrar Notas");
            System.out.println("4. Consultar Alumno y Notas");
            System.out.println("7. Salir");
            System.out.println("=========================================");
            System.out.print("Seleccione una opcion: ");

            // Leemos lo que escribe el usuario
            String opcion = teclado.nextLine();

            // ===================================================================
            // OPCIÓN 1: REGISTRAR ALUMNO
            // ===================================================================
            if (opcion.equals("1")) {
                System.out.println("\n--- Usted ha seleccionado: Registrar Alumno ---");
                
                System.out.print("Ingrese la cedula del alumno: ");
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
                
                System.out.print("Ingrese la cedula del profesor: ");
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
            // OPCIÓN 3: REGISTRAR NOTAS A UN ALUMNO
            // ===================================================================
            } else if (opcion.equals("3")) {
                System.out.println("\n--- Usted ha seleccionado: Registrar Notas a un Alumno ---");
                
                System.out.print("Ingrese la cédula del alumno a calificar: ");
                String cedula_buscada = teclado.nextLine();
                
                // 1. Pedimos la primera nota y usamos la clase Notas
                System.out.print("Ingrese la nota: ");
                String valor_nota = teclado.nextLine();
                Notas nota_estudiante = new Notas(cedula_buscada, valor_nota);
                nota_estudiante.mostrar_Notas(); 
                
                // 2. Acumulamos la nota (Usamos 'valorNota' porque así lo llamaste en tu archivo Notas.java)
                String texto_notas = "," + nota_estudiante.valorNota;
                
                // 3. Ciclo para preguntar si desea agregar más notas
                System.out.print("¿Quiere subir otra nota? (si/no): ");
                String respuesta = teclado.nextLine();
                
                // En Java los textos se comparan con .equals, NO con ==
                while (respuesta.equals("si")) {
                    System.out.print("Ingrese la nota: ");
                    valor_nota = teclado.nextLine();
                    
                    Notas nota_nueva = new Notas(cedula_buscada, valor_nota);
                    nota_nueva.mostrar_Notas();
                    
                    texto_notas = texto_notas + "," + nota_nueva.valorNota;
                    
                    System.out.print("¿Quiere subir otra nota? (si/no): ");
                    respuesta = teclado.nextLine();
                }

                // 4. Actualizamos el archivo del alumno
                ArrayList<String> lineas = new ArrayList<>();
                
                try {
                    // LECTURA (Equivale a open("alumnos.txt", "r") y archivo.readlines())
                    BufferedReader lector = new BufferedReader(new FileReader("alumnos.txt"));
                    String linea;
                    while ((linea = lector.readLine()) != null) {
                        lineas.add(linea); // Guardamos cada línea en nuestra lista virtual
                    }
                    lector.close();

                    // ESCRITURA (Equivale a open("alumnos.txt", "w"))
                    FileWriter escritor = new FileWriter("alumnos.txt");
                    
                    for (String lineaActual : lineas) {
                        String[] datos = lineaActual.split(","); 
                        String cedula_en_archivo = datos[0]; 

                        if (cedula_en_archivo.equals(cedula_buscada)) {
                            // IMPORTANTE: En Java, lector.readLine() ya elimina el "enter" invisible automáticamente.
                            // Por eso NO usamos .replace("\n", ""). Simplemente pegamos las notas y agregamos el \n al final.
                            String linea_nueva = lineaActual + texto_notas + "\n";
                            escritor.write(linea_nueva);
                        } else {
                            // A las líneas normales hay que volver a ponerles el \n para que no queden pegadas
                            escritor.write(lineaActual + "\n");
                        }
                    }
                    escritor.close();
                    System.out.println("Notas registradas correctamente.");

                } catch (IOException e) {
                    System.out.println("Error al acceder a la base de datos.");
                }
                
            // ===================================================================
            // OPCIÓN 4: DESHACER ÚLTIMO REGISTRO DE NOTA (LIFO)
            // ===================================================================
            } else if (opcion.equals("4")) {
                System.out.println("\n--- Usted ha seleccionado: Deshacer Última Nota ---");
                
                System.out.print("Ingresa la cédula del alumno para eliminar su última nota: ");
                String cedula_buscada = teclado.nextLine();

                ArrayList<String> lineas = new ArrayList<>();
                
                try {
                    // LECTURA: Equivalente a archivo = open("alumnos.txt", "r") y readlines()
                    BufferedReader lector = new BufferedReader(new FileReader("alumnos.txt"));
                    String linea;
                    while ((linea = lector.readLine()) != null) {
                        lineas.add(linea); // Guardamos cada línea
                    }
                    lector.close();

                    // ESCRITURA: Equivalente a archivo = open("alumnos.txt", "w")
                    FileWriter escritor = new FileWriter("alumnos.txt");
                    
                    for (String lineaActual : lineas) {
                        String[] datos = lineaActual.split(","); 
                        String cedula_en_archivo = datos[0]; 

                        if (cedula_en_archivo.equals(cedula_buscada)) {
                            
                            // Agregamos un filtro de seguridad por si el alumno no tiene notas
                            if (datos.length > 4) {
                                // Empezamos la línea nueva con la cédula
                                String linea_nueva = datos[0];
                                
                                // Lógica de Pila (LIFO) artesanal para reemplazar a pop() y join()
                                // El ciclo llega hasta "datos.length - 1" para dejar por fuera la última nota
                                for (int i = 1; i < datos.length - 1; i++) {
                                    linea_nueva = linea_nueva + "," + datos[i];
                                }
                                
                                escritor.write(linea_nueva + "\n");
                            } else {
                                // Si no hay notas, escribimos la línea normal sin hacerle daño
                                escritor.write(lineaActual + "\n");
                            }
                            
                        } else {
                            escritor.write(lineaActual + "\n");
                        }
                    }
                    
                    escritor.close();
                    System.out.println("Última nota eliminada con éxito.");

                } catch (IOException e) {
                    System.out.println("Error al acceder a la base de datos.");
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