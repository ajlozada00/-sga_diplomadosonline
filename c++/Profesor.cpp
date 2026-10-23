#include "Profesor.hpp"

// Conectamos el constructor usando Profesor:: y la flecha -> para los atributos
Profesor::Profesor(std::string cedula, std::string nombre, std::string correo, std::string materia) {
    this->cedula = cedula;
    this->nombre = nombre;
    this->correo = correo;
    this->materia = materia;
}