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
        
        nuevo_alumno = Alumno(cedula, nombre, correo, programa)
        
        archivo = open("alumnos.txt", "a")
                
        archivo.write(f"{nuevo_alumno.cedula},{nuevo_alumno.nombre},{nuevo_alumno.correo},{nuevo_alumno.programa}\n")
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
        print("--- Registrar Notas a un Alumno ---")
        
        if opcion == "3":
            cedula_buscada = input("Ingrese la cédula del alumno a calificar: ")
            
            # Pedimos la primera nota
            nueva_nota = input("Ingrese la nota: ")
            
            # Vamos a usar una variable de texto para acumular las notas
            # Le ponemos la coma de una vez para que quede lista para pegar
            texto_notas = "," + nueva_nota
            
            # Hacemos la pregunta que querías
            respuesta = input("¿quiere subir otra nota? si o no?: ")
            
            # Este while es como tu if, pero se repite mientras escriban "si"
            while respuesta == "si":
                nueva_nota = input("Ingrese la nota: ")
                
                # Acumulamos la nota nueva en nuestro texto
                texto_notas = texto_notas + "," + nueva_nota
                
                # Volvemos a preguntar para ver si el ciclo sigue o se rompe
                respuesta = input("¿quiere subir otra nota? si o no?: ")

            # 1. Leemos el archivo
            archivo = open("alumnos.txt", "r")
            lineas = archivo.readlines()
            archivo.close()

            # 2. Abrimos para reescribir
            archivo = open("alumnos.txt", "w")

            # 3. Buscamos y guardamos
            for linea in lineas:
                datos = linea.split(",") 
                cedula_en_archivo = datos[0] 

                if cedula_en_archivo == cedula_buscada:
                    linea_sin_enter = linea.replace("\n", "")
                    
                    # Le pegamos el texto_notas que ya tiene toooodas las notas acumuladas
                    linea_nueva = linea_sin_enter + texto_notas + "\n"
                    
                    archivo.write(linea_nueva)
                else:
                    archivo.write(linea)

            archivo.close()
            print("Notas registradas al alumno.")
            

    elif opcion == "4":
        print (f" Usted ha seleccionado Deshacer Último Registro de Nota")

    elif opcion == "5":
        print (f" Usted ha seleccionado Generar Cola de Certificados")

    elif opcion == "6":
        print (f" Usted ha seleccionado Mostrar Reporte General")

    elif opcion == "7":
        print (f" Usted ha seleccionado Salir")
    break
    