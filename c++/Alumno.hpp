// =================================================================
// ARCHIVO: Alumno.hpp (El "Menu" o Tarjeta de Presentacion)
// Proposito: Decirle al sistema QUE tiene la clase, sin programar el COMO.
// =================================================================

// Estas dos lineas son el "Guardia de Seguridad" (Include Guard).
// Evitan que C++ lea este archivo dos veces si lo llamamos desde distintas partes del programa.
// Si no ponemos esto, el compilador daria un error por "clase duplicada".
#ifndef ALUMNO_HPP  
#define ALUMNO_HPP

// A diferencia de Java, los textos no vienen por defecto. Hay que invitar a la libreria <string>.
#include <string>   

class Alumno {
public:
    // Atributos de la clase (Datos basicos del alumno)
    // RECORDATORIO: En Java escribiamos "String" (con mayuscula). 
    // En C++ siempre usamos "std::string" (todo en minusculas).
    std::string cedula;
    std::string nombre;
    std::string correo;
    std::string programa;

    // El Constructor
    // En este archivo de "Menu", SOLO anunciamos que el constructor existe.
    // No abrimos llaves { } ni escribimos logica aqui adentro.
    Alumno(std::string cedula, std::string nombre, std::string correo, std::string programa);

}; // SUPER IMPORTANTE! A diferencia de Java, C++ te obliga a cerrar la clase con un punto y coma.

#endif // Fin del guardia de seguridad