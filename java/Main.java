import java.util.ArrayList;    // Herramienta para crear listas dinámicas (como las de Python)
import java.util.Scanner;      // Herramienta para leer lo que el usuario escribe en el teclado
import java.io.BufferedReader; // Herramienta para leer archivos de texto línea por línea
import java.io.FileReader;     // Herramienta para abrir los archivos físicos del disco duro
import java.io.FileWriter;     // Herramienta para escribir archivos
import java.io.IOException;    // Herramienta para atrapar errores si el archivo falla

public class Main {
    public static void main(String[] args) {
        
        // Inicializamos el lector para capturar los datos que el usuario escriba
        Scanner teclado = new Scanner(System.in);
        
        // Interruptor principal para mantener el menú repitiéndose
        boolean sistemaEncendido = true;

        while (sistemaEncendido) {
            
            
            // INTERFAZ DEL MENÚ PRINCIPAL
            
            System.out.println("\n=========================================");
            System.out.println("   SISTEMA DE GESTION ACADEMICA (SGA)    ");
            System.out.println("=========================================");
            System.out.println("1. Registrar Alumno");
            System.out.println("2. Registrar Profesor");
            System.out.println("3. Registrar Notas");
            System.out.println("4. Deshacer la ultima nota");
            System.out.println("5. Generar Cola de Certificados");
            System.out.println("6. Mostrar Reporte General");
            System.out.println("7. Salir");
            System.out.println("=========================================");
            System.out.print("Seleccione una opcion: ");

            // Capturamos la opción elegida por el usuario
            String opcion = teclado.nextLine();

            
            // OPCIÓN 1: REGISTRO DE NUEVOS ALUMNOS
            
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

                // Empaquetamos los datos capturados utilizando la clase modelo Alumno
                Alumno nuevo_alumno = new Alumno(cedula, nombre, correo, programa);

                // Intentamos guardar los datos en el disco duro
                try {
                    // El parámetro 'true' asegura que escribimos al final sin borrar el historial
                    FileWriter archivo = new FileWriter("alumnos.txt", true);
                    
                    // Concatenamos los datos separados por comas y agregamos un salto de línea al final
                    archivo.write(nuevo_alumno.cedula + "," + nuevo_alumno.nombre + "," + nuevo_alumno.correo + "," + nuevo_alumno.programa + "\n");
                    
                    archivo.close(); // Siempre cerramos el archivo para liberar memoria
                    System.out.println("¡Alumno registrado y guardado exitosamente!");
                    
                } catch (IOException e) {
                    // Si el archivo está bloqueado o falla, mostramos este error sin cerrar el programa
                    System.out.println("Error: No se pudo acceder al archivo de alumnos.");
                }

            
            // OPCIÓN 2: REGISTRO DE NUEVOS PROFESORES
            
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

                // Empaquetamos los datos en la clase modelo Profesor
                Profesor nuevo_profesor = new Profesor(cedula, nombre, correo, materia);

                try {
                    FileWriter archivo = new FileWriter("profesores.txt", true);
                    archivo.write(nuevo_profesor.cedula + "," + nuevo_profesor.nombre + "," + nuevo_profesor.correo + "," + nuevo_profesor.materia + "\n");
                    archivo.close();
                    System.out.println("¡Profesor registrado y guardado exitosamente!");
                    
                } catch (IOException e) {
                    System.out.println("Error: No se pudo acceder al archivo de profesores.");
                }

            
            // OPCIÓN 3: AÑADIR NOTAS A UN ALUMNO EXISTENTE
            
            } else if (opcion.equals("3")) {
                System.out.println("\n--- Usted ha seleccionado: Registrar Notas a un Alumno ---");
                
                System.out.print("Ingrese la cedula del alumno a calificar: ");
                String cedula_buscada = teclado.nextLine();
                
                // Pedimos la primera nota
                System.out.print("Ingrese la nota: ");
                String valor_nota = teclado.nextLine();
                Notas nota_estudiante = new Notas(cedula_buscada, valor_nota); 
                
                // Iniciamos la cadena de texto con la primera nota precedida por una coma
                String texto_notas = "," + nota_estudiante.valorNota;
                
                // Mini-ciclo para permitir el ingreso de múltiples notas
                System.out.print("¿Quiere subir otra nota? (si/no): ");
                String respuesta = teclado.nextLine();
                
                while (respuesta.equals("si")) {
                    System.out.print("Ingrese la nota: ");
                    valor_nota = teclado.nextLine();
                    
                    Notas nota_nueva = new Notas(cedula_buscada, valor_nota);
                    texto_notas = texto_notas + "," + nota_nueva.valorNota; // Acumulamos la nueva nota
                    
                    System.out.print("¿Quiere subir otra nota? (si/no): ");
                    respuesta = teclado.nextLine();
                }

                // Fase de Lectura y Sobrescritura
                ArrayList<String> lineas = new ArrayList<>();
                
                try {
                    // 1. Leemos todo el archivo y lo guardamos en la memoria temporal (ArrayList)
                    BufferedReader lector = new BufferedReader(new FileReader("alumnos.txt"));
                    String linea;
                    while ((linea = lector.readLine()) != null) {
                        lineas.add(linea); 
                    }
                    lector.close();

                    // 2. Sobrescribimos el archivo completo desde cero (sin el 'true')
                    FileWriter escritor = new FileWriter("alumnos.txt");
                    
                    for (String lineaActual : lineas) {
                        String[] datos = lineaActual.split(","); // Cortamos la línea por las comas
                        String cedula_en_archivo = datos[0]; 

                        if (cedula_en_archivo.equals(cedula_buscada)) {
                            // Si es el alumno buscado, le pegamos las notas al final de su línea
                            String linea_nueva = lineaActual + texto_notas + "\n";
                            escritor.write(linea_nueva);
                        } else {
                            // Si es otro alumno, reescribimos su línea tal cual estaba
                            escritor.write(lineaActual + "\n");
                        }
                    }
                    escritor.close();
                    System.out.println("Notas registradas correctamente.");

                } catch (IOException e) {
                    System.out.println("Error: Base de datos no encontrada.");
                }
                
            
            // OPCIÓN 4: DESHACER LA ÚLTIMA NOTA (LIFO)
            
            } else if (opcion.equals("4")) {
                System.out.println("\n--- Usted ha seleccionado: Deshacer Ultima Nota ---");
                
                System.out.print("Ingresa la cedula del alumno para eliminar su ultima nota: ");
                String cedula_buscada = teclado.nextLine();

                ArrayList<String> lineas = new ArrayList<>();
                
                try {
                    // Copiamos el archivo a la memoria temporal
                    BufferedReader lector = new BufferedReader(new FileReader("alumnos.txt"));
                    String linea;
                    while ((linea = lector.readLine()) != null) {
                        lineas.add(linea); 
                    }
                    lector.close();

                    // Empezamos la sobrescritura
                    FileWriter escritor = new FileWriter("alumnos.txt");
                    
                    for (String lineaActual : lineas) {
                        String[] datos = lineaActual.split(","); 
                        String cedula_en_archivo = datos[0]; 

                        if (cedula_en_archivo.equals(cedula_buscada)) {
                            
                            // Verificamos que el alumno realmente tenga notas (más de 4 datos básicos)
                            if (datos.length > 4) {
                                String linea_nueva = datos[0];
                                
                                // Reconstruimos la línea pero nos detenemos 1 posición antes del final (datos.length - 1)
                                for (int i = 1; i < datos.length - 1; i++) {
                                    linea_nueva = linea_nueva + "," + datos[i];
                                }
                                
                                escritor.write(linea_nueva + "\n");
                            } else {
                                // Si no tiene notas, mantenemos la línea original
                                escritor.write(lineaActual + "\n");
                            }
                            
                        } else {
                            escritor.write(lineaActual + "\n");
                        }
                    }
                    
                    escritor.close();
                    System.out.println("Última nota eliminada con éxito.");

                } catch (IOException e) {
                    System.out.println("Error: Base de datos no encontrada.");
                }

            
            // OPCIÓN 5: GENERAR COLA DE CERTIFICADOS (EVALUACIÓN)
            
            } else if (opcion.equals("5")) {
                System.out.println("\n--- Generando Cola de Certificados ---");

                // Lista virtual para almacenar a los alumnos que cumplan los requisitos
                ArrayList<String[]> cola_certificados = new ArrayList<>(); 

                try {
                    BufferedReader lector = new BufferedReader(new FileReader("alumnos.txt"));
                    String linea;

                    while ((linea = lector.readLine()) != null) {
                        String[] datos = linea.split(",");
                        
                        // Solo evaluamos a estudiantes que tengan al menos una nota registrada
                        if (datos.length > 4) {
                            String cedula = datos[0];
                            String nombre = datos[1];
                            String programa = datos[3];
                            
                            // Variables para calcular el promedio matemático
                            float suma_notas = 0;
                            int cantidad_notas = 0;
                            
                            for (int i = 4; i < datos.length; i++) {
                                suma_notas = suma_notas + Float.parseFloat(datos[i]);
                                cantidad_notas = cantidad_notas + 1;
                            }
                            
                            float promedio = suma_notas / cantidad_notas;
                            boolean aprobado = false;
                            String motivo = "";

                            // Reglas de negocio para aprobar según el programa
                            if (programa.equalsIgnoreCase("Curso")) {
                                if (promedio >= 10.0) {
                                    aprobado = true;
                                    motivo = "APROBADO";
                                }
                            } else if (programa.equalsIgnoreCase("Diplomado")) {
                                if (promedio >= 14.0) {
                                    aprobado = true;
                                    motivo = "APROBADO";
                                }
                            } else if (programa.equalsIgnoreCase("Bootcamp")) {
                                boolean tiene_nota_baja = false;
                                for (int i = 4; i < datos.length; i++) {
                                    if (Float.parseFloat(datos[i]) < 14.0) {
                                        tiene_nota_baja = true;
                                    }
                                }
                                if (tiene_nota_baja == false) {
                                    aprobado = true;
                                    motivo = "APROBADO";
                                }
                            }
                            
                            // Si el alumno aprobó, lo agregamos a nuestra lista (cola)
                            if (aprobado == true) {
                                double promedio_redondeado = Math.round(promedio * 10.0) / 10.0;
                                String promedio_texto = String.valueOf(promedio_redondeado);
                                
                                String[] datos_aprobado = {cedula, nombre, programa, promedio_texto, motivo};
                                cola_certificados.add(datos_aprobado);
                            }
                        }
                    }
                    lector.close();

                    // Generamos un nuevo archivo de texto con los resultados finales
                    FileWriter reporte = new FileWriter("certificados_pendientes.txt");
                    
                    reporte.write("=========================================\n");
                    reporte.write("REPORTE DE CERTIFICADOS PENDIENTES\n");
                    reporte.write("=========================================\n");
                    reporte.write("Total de graduandos en cola: " + cola_certificados.size() + "\n\n");

                    // Imprimimos la lista de aprobados en el documento
                    int numero = 1;
                    for (String[] alumno : cola_certificados) {
                        String cedula_alumno = alumno[0];
                        String nombre_alumno = alumno[1];
                        String programa_alumno = alumno[2];
                        String promedio_alumno = alumno[3];
                        String motivo_alumno = alumno[4]; 
                        
                        reporte.write(numero + ". [V-" + cedula_alumno + "] " + nombre_alumno + "\n");
                        reporte.write("  - Programa: " + programa_alumno + "\n");
                        reporte.write("  - Promedio Final: " + promedio_alumno + "\n");
                        reporte.write("  - Estatus: " + motivo_alumno + "\n\n");
                        
                        numero = numero + 1;
                    }

                    reporte.write("=========================================\n");
                    reporte.write("* Fin del reporte - Generado por SGA-DO *\n");
                    reporte.close();
                    
                    System.out.println("¡Reporte generado! Revisa tu archivo 'certificados_pendientes.txt'.");

                } catch (IOException e) {
                    System.out.println("Error: Archivos de notas no encontrados.");
                }

            
            // OPCIÓN 6: MOSTRAR REPORTE GENERAL EN PANTALLA
            
            } else if (opcion.equals("6")) {
                System.out.println("\n=========================================");
                System.out.println("          REPORTE GENERAL SGA            ");
                System.out.println("=========================================\n");

                try {
                    // Lectura del archivo de Profesores
                    System.out.println("--- LISTA DE PROFESORES ACTIVOS ---");
                    BufferedReader lectorProfesores = new BufferedReader(new FileReader("profesores.txt"));
                    String lineaProfesor;

                    while ((lineaProfesor = lectorProfesores.readLine()) != null) {
                        String[] datos = lineaProfesor.split(",");
                        
                        if (datos.length >= 4) {
                            String cedula_prof = datos[0];
                            String nombre_prof = datos[1];
                            String materia_prof = datos[3];
                            System.out.println("Profesor: " + nombre_prof + " | Cedula: " + cedula_prof + " | Materia: " + materia_prof + " | Estatus: Activo");
                        }
                    }
                    lectorProfesores.close();

                    // Lectura del archivo de Alumnos
                    System.out.println("\n--- LISTA DE ALUMNOS REGISTRADOS ---");
                    BufferedReader lectorAlumnos = new BufferedReader(new FileReader("alumnos.txt"));
                    String lineaAlumno;

                    while ((lineaAlumno = lectorAlumnos.readLine()) != null) {
                        String[] datos = lineaAlumno.split(",");
                        
                        if (datos.length >= 4) {
                            String cedula_alum = datos[0];
                            String nombre_alum = datos[1];
                            String programa_alum = datos[3];
                            
                            // Ajuste visual: Forzamos la primera letra a mayúscula y el resto a minúscula
                            String programa_capitalizado = programa_alum.substring(0, 1).toUpperCase() + programa_alum.substring(1).toLowerCase();
                            
                            // Determinamos el estatus contando la cantidad de datos en la línea
                            String estatus = "";
                            if (datos.length > 4) {
                                estatus = "Con notas registradas";
                            } else {
                                estatus = "Registrado (Sin notas)";
                            }
                            
                            System.out.println("Alumno: " + nombre_alum + " | Cedula: " + cedula_alum + " | Programa: " + programa_capitalizado + " | Estatus: " + estatus);
                        }
                    }
                    lectorAlumnos.close();

                } catch (IOException e) {
                    System.out.println("\nError: Asegúrese de que los archivos profesores.txt y alumnos.txt ya estén creados.");
                }

                System.out.println("\n=========================================");
                System.out.println("         FIN DEL REPORTE                 ");
                System.out.println("=========================================\n");

            
            // OPCIÓN 7: SALIR Y APAGAR EL SISTEMA
            
            } else if (opcion.equals("7")) {
                System.out.println("\nGuardando cambios pendientes...");
                System.out.println("Cerrando el Sistema de Gestión Académica. ¡Hasta luego!");
                
                // Cambiamos el interruptor a falso para romper el ciclo while de forma limpia
                sistemaEncendido = false;
                
            
            // MANEJO DE ERRORES (Cualquier tecla que no sea del 1 al 7)
            
            } else {
                System.out.println("\n¡Error! Opción no válida. Por favor, ingrese un número del menú.");
            }
        }
        
        // Cerramos el lector de teclado al finalizar todo el programa
        teclado.close();
    }
}