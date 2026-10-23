// =================================================================
// ARCHIVO: Alumno.cpp (La "Cocina" o Archivo de Implementacion)
// Proposito: Escribir la logica real y darle vida a lo que prometimos en el Menu.
// =================================================================

// Lo primero y mas importante es traer nuestro propio menu para saber en que estamos trabajando.
#include "Alumno.hpp" 

// Los dos puntos dobles (::) se llaman "Operador de Resolucion de Ambito".
// Le dicen a la maquina: "Atencion, este constructor no esta suelto por ahi, 
// le pertenece especificamente a la familia de la clase Alumno".
Alumno::Alumno(std::string cedula, std::string nombre, std::string correo, std::string programa) {
    
    // OJO AQUI (Diferencia clave con Java): 
    // En Java usabamos un simple punto para asignar el dato a la clase (this.cedula).
    // En C++, como "this" representa una direccion de memoria (un puntero), 
    // estamos obligados a usar una flechita (this->cedula) para guardar el dato.
    this->cedula = cedula;
    this->nombre = nombre;
    this->correo = correo;
    this->programa = programa;
}