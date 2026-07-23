## documentacion
##

class Persona: 
    def __init__ (self, cedula, nombre, correo):
        self_cedula = cedula
        self_nombre = nombre
        self_correo = correo

class Alumno:
    def __init__ (self, cedula, nombre, correo, notas, programa):
        self_cedula = cedula
        self_nombre = nombre
        self_correo = correo
        self_materia = notas
        self_materia = programa

class Profesor:
    def __init__ (self, cedula, nombre, correo, materia):
        self_cedula = cedula
        self_nombre = nombre
        self_correo = correo
        self_materia = materia

class Programa:  
    def __init__ (self, nombre):
        self_nombre = nombre