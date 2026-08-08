from models import Alumno, Profesor, Programa, Notas


print ("===============================")
print ("1. Registrar Alumno")
print ("2. Registrar Profesor")
print ("3. Registrar Notas a un Alumno")
print ("4. Deshacer Último Registro de Nota")
print ("5. Generar Cola de Certificados")
print ("6. Mostrar Reporte General")
print ("7. Salir")
print ("===============================")


opcion = str (input("Seleccione una opción: "))
while True:

    if opcion == "1":
        print (f" Usted ha seleccionado Registrar Alumno")

        cedula = str (input("Ingrese la cédula del alumno: "))
        nombre = str (input("Ingrese el nombre del alumno: "))
        correo = str (input("Ingrese el correo del alumno: "))
        programa = str (input("Ingrese el programa del alumno: "))
        notas = []
        
        nuevo_alumno = Alumno(cedula, nombre, correo, notas, programa)
        
        archivo = open("alumnos.txt", "a")
                
        archivo.write(f"{nuevo_alumno.cedula},{nuevo_alumno.nombre},{nuevo_alumno.correo},{nuevo_alumno.notas},{nuevo_alumno.programa}\n")
        archivo.close()
                
        print("\n¡Alumno registrado y guardado en el archivo!")
        

    elif opcion == "2":
        print (f" Usted ha seleccionado Registrar Profesor")

        cedula = str (input("Ingrese la cédula del profesor: "))
        nombre = str (input("Ingrese el nombre del profesor: "))
        correo = str (input("Ingrese el correo del profesor: "))
        materia = str (input("Ingrese la materia del profesor: "))
        
            
        nuevo_profesor = Profesor(cedula, nombre, correo, materia)
            
        archivo = open("profesores.txt", "a")
                    
        archivo.write(f"{nuevo_profesor.cedula},{nuevo_profesor.nombre},{nuevo_profesor.correo},{nuevo_profesor.materia}\n")
        archivo.close()
                    
        print("\n¡Profesor registrado y guardado en el archivo!")    


    elif opcion == "3":
        print (f" Usted ha seleccionado Registrar Notas a un Alumno")

    elif opcion == "4":
        print (f" Usted ha seleccionado Deshacer Último Registro de Nota")

    elif opcion == "5":
        print (f" Usted ha seleccionado Generar Cola de Certificados")

    elif opcion == "6":
        print (f" Usted ha seleccionado Mostrar Reporte General")

    elif opcion == "7":
        print (f" Usted ha seleccionado Salir")
    break
    