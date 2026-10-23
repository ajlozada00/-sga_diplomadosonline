// =================================================================
// ARCHIVO: Notas.hpp (El "Menu" de las Notas)
// Proposito: Declarar los datos de la calificacion y anunciar que acciones puede hacer.
// =================================================================

// Guardia de seguridad para evitar que el archivo se lea multiples veces por accidente.
#ifndef NOTAS_HPP
#define NOTAS_HPP

// Pedimos permiso al sistema para usar cadenas de texto
#include <string>

class Notas {
public:
    // Datos basicos que componen el registro de una nota
    std::string cedula;
    std::string valorNota;

    // Constructor: Solo anunciamos que necesitamos estos dos datos para crear una nota
    Notas(std::string cedula, std::string valorNota);

    // Metodo de accion: Como no devuelve calculos matematicos, usamos "void".
    // Aqui SOLO anunciamos que el metodo existe. Las llaves { } y la logica van en el .cpp
    void mostrar_Notas();

}; // SUPER IMPORTANTE! El punto y coma obligatorio al final de la clase en C++.

#endif // Cierre del guardia de seguridad