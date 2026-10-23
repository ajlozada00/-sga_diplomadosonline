#ifndef PROFESOR_HPP
#define PROFESOR_HPP

#include <string>

class Profesor {
public:
    std::string cedula;
    std::string nombre;
    std::string correo;
    std::string materia;

    // El Constructor que recibe los 4 datos del profesor
    Profesor(std::string cedula, std::string nombre, std::string correo, std::string materia);
};

#endif