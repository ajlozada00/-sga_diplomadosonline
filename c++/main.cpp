#include <iostream>
#include <string>
#include <vector>    // Para usar listas dinamicas (como ArrayList en Java)
#include <fstream>   // Para leer y escribir archivos .txt
#include <sstream>   // Para separar textos por comas (como el .split)
#include <cmath>     // Para usar la funcion de redondeo (round)
#include "Alumno.hpp"
#include "Notas.hpp"
#include "Profesor.hpp"

int main() {
    // Interruptor para mantener el menu vivo
    bool sistemaEncendido = true;

    while (sistemaEncendido) {
        // INTERFAZ DEL MENU PRINCIPAL
        std::cout << "\n=========================================" << std::endl;
        std::cout << "   SISTEMA DE GESTION ACADEMICA (SGA)     " << std::endl;
        std::cout << "=========================================" << std::endl;
        std::cout << "1. Registrar Alumno" << std::endl;
        std::cout << "2. Registrar Profesor" << std::endl;
        std::cout << "3. Registrar Notas" << std::endl;
        std::cout << "4. Deshacer la ultima nota" << std::endl;
        std::cout << "5. Generar Cola de Certificados" << std::endl;
        std::cout << "6. Mostrar Reporte General" << std::endl;
        std::cout << "7. Salir" << std::endl;
        std::cout << "=========================================" << std::endl;
        std::cout << "Seleccione una opcion: ";

        std::string opcion;
        std::getline(std::cin, opcion);

        // ==========================================================
        // OPCION 1: REGISTRO DE NUEVOS ALUMNOS
        // ==========================================================
        if (opcion == "1") {
            std::cout << "\n--- Usted ha seleccionado: Registrar Alumno ---" << std::endl;
            
            std::string cedula;
            std::cout << "Ingrese la cedula del alumno: ";
            std::getline(std::cin, cedula);

            bool cedulaRegistrada = false;

            std::ifstream lector("alumnos.txt");
            if (lector.is_open()) {
                std::string lineaActual;
                while (std::getline(lector, lineaActual)) {
                    std::stringstream ss(lineaActual);
                    std::string parteCedula;
                    
                    if (std::getline(ss, parteCedula, ',')) {
                        if (parteCedula == cedula) {
                            cedulaRegistrada = true; 
                        }
                    }
                }
                lector.close();
            }

            if (cedulaRegistrada) {
                std::cout << "\nError: La cedula " << cedula << " ya se encuentra registrada en el sistema." << std::endl;
            } else {
                std::string nombre, correo, programa;
                
                std::cout << "Ingrese el nombre del alumno: ";
                std::getline(std::cin, nombre);
                
                std::cout << "Ingrese el correo del alumno: ";
                std::getline(std::cin, correo);
                
                std::cout << "Ingrese el programa del alumno: ";
                std::getline(std::cin, programa);

                Alumno nuevo_alumno(cedula, nombre, correo, programa);

                std::ofstream escritor("alumnos.txt", std::ios::app);
                if (escritor.is_open()) {
                    escritor << nuevo_alumno.cedula << "," 
                             << nuevo_alumno.nombre << "," 
                             << nuevo_alumno.correo << "," 
                             << nuevo_alumno.programa << "\n";
                    escritor.close();
                    std::cout << "Alumno registrado y guardado exitosamente!" << std::endl;
                } else {
                    std::cout << "Error: No se pudo guardar la informacion en la base de datos." << std::endl;
                }
            }
        }
        // ==========================================================
        // OPCION 2: REGISTRO DE NUEVOS PROFESORES 
        // ==========================================================
        else if (opcion == "2") {
            std::cout << "\n--- Usted ha seleccionado: Registrar Profesor ---" << std::endl;
            
            std::string cedula;
            std::cout << "Ingrese la cedula del profesor: ";
            std::getline(std::cin, cedula);

            bool cedulaRegistrada = false;

            std::ifstream lector("profesores.txt");
            if (lector.is_open()) {
                std::string lineaActual;
                while (std::getline(lector, lineaActual)) {
                    std::stringstream ss(lineaActual);
                    std::string parteCedula;
                    if (std::getline(ss, parteCedula, ',')) {
                        if (parteCedula == cedula) {
                            cedulaRegistrada = true;
                        }
                    }
                }
                lector.close();
            }

            if (cedulaRegistrada) {
                std::cout << "\nError: La cedula " << cedula << " ya se encuentra registrada para otro profesor." << std::endl;
            } else {
                std::string nombre, correo, materia;
                
                std::cout << "Ingrese el nombre del profesor: ";
                std::getline(std::cin, nombre);
                
                std::cout << "Ingrese el correo del profesor: ";
                std::getline(std::cin, correo);
                
                std::cout << "Ingrese la materia del profesor: ";
                std::getline(std::cin, materia);

                Profesor nuevo_profesor(cedula, nombre, correo, materia);

                std::ofstream archivo("profesores.txt", std::ios::app);
                if (archivo.is_open()) {
                    archivo << nuevo_profesor.cedula << "," 
                            << nuevo_profesor.nombre << "," 
                            << nuevo_profesor.correo << "," 
                            << nuevo_profesor.materia << "\n";
                    archivo.close();
                    std::cout << "Profesor registrado y guardado exitosamente!" << std::endl;
                } else {
                    std::cout << "Error: No se pudo acceder al archivo de profesores." << std::endl;
                }
            }
        }
        // ==========================================================
        // OPCION 3: AÑADIR NOTAS 
        // ==========================================================
        else if (opcion == "3") {
            std::cout << "\n--- Usted ha seleccionado: Registrar Notas a un Alumno ---" << std::endl;
            
            std::string cedula_buscada;
            std::cout << "Ingrese la cedula del alumno a calificar: ";
            std::getline(std::cin, cedula_buscada);

            bool cedulaExiste = false;
            int notasExistentes = 0; 

            std::ifstream verificarLector("alumnos.txt");
            if (verificarLector.is_open()) {
                std::string lineaVerif;
                while (std::getline(verificarLector, lineaVerif)) {
                    std::stringstream ssVerif(lineaVerif);
                    std::vector<std::string> datosAlumno;
                    std::string segmento;
                    
                    while (std::getline(ssVerif, segmento, ',')) {
                        datosAlumno.push_back(segmento);
                    }

                    if (!datosAlumno.empty() && datosAlumno[0] == cedula_buscada) {
                        cedulaExiste = true;
                        
                        if (datosAlumno.size() > 4) {
                            notasExistentes = datosAlumno.size() - 4; 
                        }
                        break; 
                    }
                }
                verificarLector.close();
            }

            if (!cedulaExiste) {
                std::cout << "\nError: La cedula " << cedula_buscada << " no se encuentra registrada en el sistema." << std::endl;
            } 
            else if (notasExistentes >= 3) {
                std::cout << "\nError: Este alumno ya tiene el limite maximo de 3 notas registradas." << std::endl;
            } 
            else {
                int notasPermitidas = 3 - notasExistentes;
                std::cout << "\nEl alumno tiene " << notasExistentes << " nota(s). Puede registrar un maximo de " << notasPermitidas << " nueva(s) nota(s)." << std::endl;

                std::string texto_notas = "";
                int notasRegistradas = 0;

                while (notasRegistradas < notasPermitidas) {
                    std::string valor_nota;
                    std::cout << "Ingrese la nota " << (notasExistentes + notasRegistradas + 1) << " de 3 (Rango: 0 a 20): ";
                    std::getline(std::cin, valor_nota);

                    bool esNumeroValido = true;
                    float notaFloat = 0.0f;

                    try {
                        size_t caracteresLeidos;
                        notaFloat = std::stof(valor_nota, &caracteresLeidos); 
                        if (caracteresLeidos != valor_nota.length()) {
                            esNumeroValido = false;
                        }
                    } catch (...) {
                        esNumeroValido = false; 
                    }

                    if (!esNumeroValido || notaFloat < 0.0f || notaFloat > 20.0f) {
                        std::cout << "Error: Nota invalida. Debe ser un numero entre 0 y 20 (sin letras ni negativos)." << std::endl;
                        continue; 
                    }

                    texto_notas = texto_notas + "," + valor_nota;
                    notasRegistradas++;

                    if (notasRegistradas < notasPermitidas) {
                        std::cout << "Desea ingresar otra nota? (si/no): ";
                        std::string respuesta;
                        std::getline(std::cin, respuesta);
                        if (respuesta != "si" && respuesta != "SI" && respuesta != "Si") {
                            break; 
                        }
                    }
                }

                if (notasRegistradas > 0) {
                    std::vector<std::string> lineas; 
                    std::ifstream lector("alumnos.txt");

                    if (lector.is_open()) {
                        std::string linea;
                        while (std::getline(lector, linea)) {
                            lineas.push_back(linea); 
                        }
                        lector.close();

                        std::ofstream escritor("alumnos.txt");
                        for (const std::string& lineaActual : lineas) {
                            std::stringstream ss(lineaActual);
                            std::string cedula_en_archivo;
                            std::getline(ss, cedula_en_archivo, ',');

                            if (cedula_en_archivo == cedula_buscada) {
                                std::string linea_nueva = lineaActual + texto_notas + "\n";
                                escritor << linea_nueva;
                            } else {
                                escritor << lineaActual << "\n";
                            }
                        }
                        escritor.close();
                        std::cout << "Notas registradas y guardadas correctamente!" << std::endl;
                    }
                } else {
                    std::cout << "No se registraron notas nuevas." << std::endl;
                }
            }
        }
        // ==========================================================
        // OPCION 4: DESHACER LA ULTIMA NOTA (Con validaciones de existencia)
        // ==========================================================
        else if (opcion == "4") {
            std::cout << "\n--- Usted ha seleccionado: Deshacer Ultima Nota ---" << std::endl;
            
            std::string cedula_buscada;
            std::cout << "Ingresa la cedula del alumno para eliminar su ultima nota: ";
            std::getline(std::cin, cedula_buscada);

            std::vector<std::string> lineas;
            std::ifstream lector("alumnos.txt");
            
            // Banderas para saber que paso durante la busqueda
            bool alumnoEncontrado = false;
            bool notaEliminada = false;

            if (lector.is_open()) {
                std::string linea;
                while (std::getline(lector, linea)) {
                    lineas.push_back(linea);
                }
                lector.close();

                std::ofstream escritor("alumnos.txt");
                for (const std::string& lineaActual : lineas) {
                    std::stringstream ss(lineaActual);
                    std::vector<std::string> datos;
                    std::string segmento;
                    
                    while (std::getline(ss, segmento, ',')) {
                        datos.push_back(segmento);
                    }

                    if (!datos.empty() && datos[0] == cedula_buscada) {
                        alumnoEncontrado = true; // Si lo encontramos!

                        if (datos.size() > 4) {
                            // Tiene al menos 1 nota, la quitamos
                            std::string linea_nueva = datos[0];
                            for (size_t i = 1; i < datos.size() - 1; i++) {
                                linea_nueva = linea_nueva + "," + datos[i];
                            }
                            escritor << linea_nueva << "\n";
                            notaEliminada = true;
                        } else {
                            // Existe, pero no tiene notas para borrar
                            escritor << lineaActual << "\n"; 
                        }
                    } else {
                        // Es otro alumno, se copia igual
                        escritor << lineaActual << "\n";
                    }
                }
                escritor.close();

                // Mensajes de respuesta para el usuario
                if (!alumnoEncontrado) {
                    std::cout << "\nError: La cedula " << cedula_buscada << " no existe en el sistema." << std::endl;
                } else if (!notaEliminada) {
                    std::cout << "\nAviso: El alumno existe, pero no tiene ninguna nota registrada para eliminar." << std::endl;
                } else {
                    std::cout << "\nUltima nota eliminada con exito." << std::endl;
                }

            } else {
                std::cout << "Error: Base de datos no encontrada." << std::endl;
            }
        }
        // ==========================================================
        // OPCION 5: GENERAR COLA DE CERTIFICADOS 
        // ==========================================================
        else if (opcion == "5") {
            std::cout << "\n--- Generando Cola de Certificados ---" << std::endl;

            struct CertificadoInfo {
                std::string cedula;
                std::string nombre;
                std::string programa;
                std::string promedio;
                std::string motivo;
            };

            std::vector<CertificadoInfo> cola_certificados;
            std::ifstream lector("alumnos.txt");

            if (lector.is_open()) {
                std::string linea;
                while (std::getline(lector, linea)) {
                    std::stringstream ss(linea);
                    std::vector<std::string> datos;
                    std::string segmento;
                    while (std::getline(ss, segmento, ',')) {
                        datos.push_back(segmento);
                    }

                    if (datos.size() > 4) {
                        std::string cedula = datos[0];
                        std::string nombre = datos[1];
                        std::string programa = datos[3];

                        float suma_notas = 0;
                        int cantidad_notas = 0;

                        for (size_t i = 4; i < datos.size(); i++) {
                            suma_notas += std::stof(datos[i]);
                            cantidad_notas++;
                        }

                        float promedio = suma_notas / cantidad_notas;
                        bool aprobado = false;
                        std::string motivo = "";

                        if (programa == "Curso" || programa == "curso") {
                            if (promedio >= 10.0f) {
                                aprobado = true;
                                motivo = "APROBADO";
                            }
                        } else if (programa == "Diplomado" || programa == "diplomado") {
                            if (promedio >= 14.0f) {
                                aprobado = true;
                                motivo = "APROBADO";
                            }
                        } else if (programa == "Bootcamp" || programa == "bootcamp") {
                            bool tiene_nota_baja = false;
                            for (size_t i = 4; i < datos.size(); i++) {
                                if (std::stof(datos[i]) < 14.0f) {
                                    tiene_nota_baja = true;
                                }
                            }
                            if (!tiene_nota_baja) {
                                aprobado = true;
                                motivo = "APROBADO";
                            }
                        }

                        if (aprobado) {
                            double promedio_redondeado = std::round(promedio * 10.0) / 10.0;
                            std::string promedio_texto = std::to_string(promedio_redondeado);
                            cola_certificados.push_back({cedula, nombre, programa, promedio_texto, motivo});
                        }
                    }
                }
                lector.close();

                std::ofstream reporte("certificados_pendientes.txt");
                reporte << "=========================================\n";
                reporte << "REPORTE DE CERTIFICADOS PENDIENTES\n";
                reporte << "=========================================\n";
                reporte << "Total de graduandos en cola: " << cola_certificados.size() << "\n\n";

                int numero = 1;
                for (const auto& alum : cola_certificados) {
                    reporte << numero << ". [V-" << alum.cedula << "] " << alum.nombre << "\n";
                    reporte << "  - Programa: " << alum.programa << "\n";
                    reporte << "  - Promedio Final: " << alum.promedio << "\n";
                    reporte << "  - Estatus: " << alum.motivo << "\n\n";
                    numero++;
                }

                reporte << "=========================================\n";
                reporte << "* Fin del reporte - Generado por SGA-DO *\n";
                reporte.close();

                std::cout << "Reporte generado! Revisa tu archivo 'certificados_pendientes.txt'." << std::endl;
            } else {
                std::cout << "Error: Archivos de notas no encontrados." << std::endl;
            }
        }
        // ==========================================================
        // OPCION 6: MOSTRAR REPORTE GENERAL EN PANTALLA
        // ==========================================================
        else if (opcion == "6") {
            std::cout << "\n=========================================" << std::endl;
            std::cout << "          REPORTE GENERAL SGA            " << std::endl;
            std::cout << "=========================================\n" << std::endl;

            std::cout << "--- LISTA DE PROFESORES ACTIVOS ---" << std::endl;
            std::ifstream lectorProfesores("profesores.txt");
            if (lectorProfesores.is_open()) {
                std::string lineaProfesor;
                while (std::getline(lectorProfesores, lineaProfesor)) {
                    std::stringstream ss(lineaProfesor);
                    std::vector<std::string> datos;
                    std::string segmento;
                    while (std::getline(ss, segmento, ',')) {
                        datos.push_back(segmento);
                    }

                    if (datos.size() >= 4) {
                        std::cout << "Profesor: " << datos[1] << " | Cedula: " << datos[0] << " | Materia: " << datos[3] << " | Estatus: Activo" << std::endl;
                    }
                }
                lectorProfesores.close();
            } else {
                std::cout << "No hay profesores registrados aun." << std::endl;
            }

            std::cout << "\n--- LISTA DE ALUMNOS REGISTRADOS ---" << std::endl;
            std::ifstream lectorAlumnos("alumnos.txt");
            if (lectorAlumnos.is_open()) {
                std::string lineaAlumno;
                while (std::getline(lectorAlumnos, lineaAlumno)) {
                    std::stringstream ss(lineaAlumno);
                    std::vector<std::string> datos;
                    std::string segmento;
                    while (std::getline(ss, segmento, ',')) {
                        datos.push_back(segmento);
                    }

                    if (datos.size() >= 4) {
                        std::string cedula_alum = datos[0];
                        std::string nombre_alum = datos[1];
                        std::string programa_alum = datos[3];

                        std::string estatus = (datos.size() > 4) ? "Con notas registradas" : "Registrado (Sin notas)";

                        std::cout << "Alumno: " << nombre_alum << " | Cedula: " << cedula_alum << " | Programa: " << programa_alum << " | Estatus: " << estatus << std::endl;
                    }
                }
                lectorAlumnos.close();
            } else {
                std::cout << "No hay alumnos registrados aun." << std::endl;
            }

            std::cout << "\n=========================================" << std::endl;
            std::cout << "           FIN DEL REPORTE               " << std::endl;
            std::cout << "=========================================\n" << std::endl;
        }
        // ==========================================================
        // OPCION 7: SALIR Y APAGAR EL SISTEMA
        // ==========================================================
        else if (opcion == "7") {
            std::cout << "\nGuardando cambios pendientes..." << std::endl;
            std::cout << "Cerrando el Sistema de Gestion Academica. Hasta luego!" << std::endl;
            sistemaEncendido = false; 
        }
        // ==========================================================
        // MANEJO DE ERRORES 
        // ==========================================================
        else {
            std::cout << "\nError: Opcion no valida. Por favor, ingrese un numero del menu." << std::endl;
        }
    }

    return 0;
}