# nombre: models.py 
# autor:  Armando Lozada
# funcion:Este archivo contiene las clases que representan 
#          los modelos de datos para el sistema de gestión académica.

"""
    Esta es la clase principal sirve para 
    guardar los datos generales que todos tienen en común.
"""

class Persona: 
    
    def __init__(self, cedula, nombre, correo):
        # Aquí se guardan los datos iniciales al registrar a la persona
        self.cedula = cedula
        self.nombre = nombre
        self.correo = correo
        
    def mostrar_informacion(self):
        # Esta acción simplemente imprime los datos en la consola para poder verlos
        print(f" Cedula: {self.cedula}")
        print(f" Nombre: {self.nombre}")
        print(f" Correo: {self.correo}")


"""
    Clase para registrar a los estudiantes. 
    Guarda la información personal y el programa al que pertenecen.
"""

class Alumno:
    
    def __init__(self, cedula, nombre, correo, programa):
        self.cedula = cedula
        self.nombre = nombre
        self.correo = correo
        self.programa = programa # El curso o carrera que estudia el alumno
        
    def mostrar_informacion_Alumno(self):
        # Muestra en pantalla todos los datos del alumno, incluyendo su programa
        print(f" Cedula: {self.cedula}")
        print(f" Nombre: {self.nombre}")
        print(f" Correo: {self.correo}")
        print(f" Programa: {self.programa}")


"""
    Clase para registrar a los profesores del sistema.
    Guarda sus datos básicos y la materia que dictan.
"""

class Profesor:
    
    def __init__(self, cedula, nombre, correo, materia):
        self.cedula = cedula
        self.nombre = nombre
        self.correo = correo
        self.materia = materia # La clase que el profesor enseña
        
    def mostrar_informacion_Profesor(self):
        # Muestra en pantalla todos los datos del profesor
        print(f" Cedula: {self.cedula}")
        print(f" Nombre: {self.nombre}")
        print(f" Correo: {self.correo}")
        print(f" Materia: {self.materia}")


"""
    Clase para manejar las calificaciones.
    Es importante porque une la nota con la cédula del alumno para saber de quién es.
"""

class Notas:  
    
    def __init__(self, cedula, nota):
        self.notas = nota      # El valor de la calificación
        self.cedula = cedula   # La cédula para identificar al alumno
        
    def mostrar_Notas(self):
        # Muestra la nota registrada en la pantalla
        print(f" Notas: {self.notas}")