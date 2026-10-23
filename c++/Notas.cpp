// =================================================================
// ARCHIVO: Notas.cpp (La "Cocina" de las Notas)
// Proposito: Escribir el codigo real que hace funcionar al constructor y a los metodos.
// =================================================================

#include "Notas.hpp" // Lo primero es traer el menu de la clase Notas
#include <iostream>  // SUPER IMPORTANTE! Libreria para usar "cout" (el equivalente a System.out en Java)

// Le damos vida al constructor conectandolo con los dos puntos dobles (Notas::)
// Esto le dice a C++: "Este constructor le pertenece a la familia Notas"
Notas::Notas(std::string cedula, std::string valorNota) {
    
    // RECORDATORIO: En Java se usa el punto (this.cedula). 
    // En C++ usamos la flecha (this->cedula) porque "this" maneja memoria directamente.
    this->cedula = cedula;
    this->valorNota = valorNota;
}

// Le damos vida al metodo de impresion (tambien conectado con Notas::)
void Notas::mostrar_Notas() {
    
    // TRADUCCION DE JAVA A C++ PARA IMPRIMIR:
    // 1. std::cout es la herramienta que empuja el texto a la pantalla.
    // 2. Las flechas (<<) sirven para "pegar" o concatenar pedazos (es como el signo "+" de Java).
    // 3. std::endl significa "End Line". Da el salto de linea final (es el "ln" de println).
    std::cout << " Notas: " << this->valorNota << std::endl;
}