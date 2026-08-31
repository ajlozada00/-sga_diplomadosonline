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

        if opcion == "4":
                cedula_buscada = input("Ingresa la cédula del alumno para eliminar su última nota: ")

                # 1. Leemos el archivo completo
                archivo = open("alumnos.txt", "r")
                lineas = archivo.readlines()
                archivo.close()

                # 2. Lo abrimos en modo escritura para actualizarlo
                archivo = open("alumnos.txt", "w")

                # 3. Revisamos línea por línea
                for linea in lineas:
                    # Primero le quitamos el "enter" (\n) para que no nos estorbe al final
                    linea_limpia = linea.replace("\n", "")
                    
                    # Picamos la línea en una lista
                    datos = linea_limpia.split(",") 
                    
                    # La cédula siempre es el primer pedazo
                    cedula_en_archivo = datos[0] 

                    if cedula_en_archivo == cedula_buscada:
                        
                        # --- AQUÍ APLICAMOS EL LIFO ---
                        # Usamos .pop() para eliminar el último elemento de la lista 'datos'
                        datos.pop()
                        
                        # Volvemos a unir la lista en un solo texto, separado por comas
                        linea_nueva = ",".join(datos)
                        
                        # Le volvemos a poner el "enter" al final y guardamos
                        archivo.write(linea_nueva + "\n")
                        
                    else:
                        # Si no es el alumno que buscamos, guardamos su línea sin hacerle nada
                        archivo.write(linea)

                archivo.close()
                print("Última nota eliminada con éxito.")


    elif opcion == "5":

        print("--- Generando Cola de Certificados ---")

        # 1. Leemos el archivo de alumnos
        archivo_alumnos = open("alumnos.txt", "r")
        lineas = archivo_alumnos.readlines()
        archivo_alumnos.close()

        # Esta lista vacía será nuestra Cola (Queue)
        cola_certificados = []

        # 2. Revisamos a cada alumno
        for linea in lineas:
            linea_limpia = linea.replace("\n", "")
            datos = linea_limpia.split(",")
            
            # Verificamos que el alumno tenga al menos una nota
            if len(datos) > 4: 
                cedula = datos[0]
                nombre = datos[1]
                
                # TRUCO: .capitalize() convierte "curso" en "Curso" para evitar errores
                programa = datos[3].capitalize() 
                
                suma_notas = 0
                cantidad_notas = 0
                
                for nota_texto in datos[4:]:
                    suma_notas = suma_notas + float(nota_texto)
                    cantidad_notas = cantidad_notas + 1
                
                promedio = suma_notas / cantidad_notas
                
                aprobado = False
                motivo = ""

                # --- REGLAS ---
                if programa == "Curso":
                    if promedio >= 10.0:
                        aprobado = True
                        motivo = "APROBADO"

                elif programa == "Diplomado":
                    if promedio >= 14.0:
                        aprobado = True
                        motivo = "APROBADO"

                elif programa == "Bootcamp":
                    tiene_nota_baja = False
                    for nota_texto in datos[4:]:
                        if float(nota_texto) < 14.0:
                            tiene_nota_baja = True
                            
                    if tiene_nota_baja == False:
                        aprobado = True
                        motivo = "APROBADO"
                
                # --- DECISIÓN FINAL DEL PORTERO ---
                if aprobado == True:
                    # Empaquetamos al alumno (ahora también guardamos el motivo)
                    datos_aprobado = [cedula, nombre, programa, round(promedio, 1), motivo]
                    cola_certificados.append(datos_aprobado)

        # ====================================================================
        # AQUÍ EMPIEZA LA PARTE QUE TE FALTABA: GUARDAR EL REPORTE
        # ====================================================================
        
        # 4. Generamos el archivo físico
        reporte = open("certificados_pendientes.txt", "w")
        
        reporte.write("=========================================\n")
        reporte.write("REPORTE DE CERTIFICADOS PENDIENTES\n")
        reporte.write("=========================================\n")
        reporte.write(f"Total de graduandos en cola: {len(cola_certificados)}\n\n")

        # 5. Escribimos la cola uno por uno
        numero = 1
        for alumno in cola_certificados:
            cedula_alumno = alumno[0]
            nombre_alumno = alumno[1]
            programa_alumno = alumno[2]
            promedio_alumno = alumno[3]
            motivo_alumno = alumno[4] # Sacamos el texto que dice "APROBADO..."
            
            reporte.write(f"{numero}. [V-{cedula_alumno}] {nombre_alumno}\n")
            reporte.write(f"  - Programa: {programa_alumno}\n")
            reporte.write(f"  - Promedio Final: {promedio_alumno}\n")
            reporte.write(f"  - Estatus: {motivo_alumno}\n\n")
            
            numero = numero + 1

        reporte.write("=========================================\n")
        reporte.write("* Fin del reporte - Generado por SGA-DO *\n")
        
        reporte.close()
        
        print("¡Reporte generado con éxito! Revisa tu archivo certificados_pendientes.txt.")


    elif opcion == "6":
        print (f" Usted ha seleccionado Mostrar Reporte General")

        print("\n=========================================")
        print("          REPORTE GENERAL SGA            ")
        print("=========================================\n")

        # 1. LISTA DE PROFESORES
        print("--- LISTA DE PROFESORES ACTIVOS ---")
        
        # Abrimos el archivo de profesores
        archivo_profesores = open("profesores.txt", "r")
        lineas_profesores = archivo_profesores.readlines()
        archivo_profesores.close()

        for linea in lineas_profesores:
            linea_limpia = linea.replace("\n", "")
            datos = linea_limpia.split(",")
            
            # Asumiendo que el formato es: cedula, nombre, correo, materia
            if len(datos) >= 4:
                cedula_prof = datos[0]
                nombre_prof = datos[1]
                materia_prof = datos[3]
                print(f"Profesor: {nombre_prof} | Cédula: {cedula_prof} | Materia: {materia_prof} | Estatus: Activo")


        # 2. LISTA DE ALUMNOS
        print("\n--- LISTA DE ALUMNOS REGISTRADOS ---")
        
        # Abrimos el archivo de alumnos que ya conoces
        archivo_alumnos = open("alumnos.txt", "r")
        lineas_alumnos = archivo_alumnos.readlines()
        archivo_alumnos.close()

        for linea in lineas_alumnos:
            linea_limpia = linea.replace("\n", "")
            datos = linea_limpia.split(",")
            
            if len(datos) >= 4:
                cedula_alum = datos[0]
                nombre_alum = datos[1]
                programa_alum = datos[3].capitalize()
                
                # Definimos el estatus dependiendo de si tiene notas o no
                if len(datos) > 4:
                    estatus = "Con notas registradas"
                else:
                    estatus = "Registrado (Sin notas)"
                    
                print(f"Alumno: {nombre_alum} | Cédula: {cedula_alum} | Programa: {programa_alum} | Estatus: {estatus}")
        
        print("\n=========================================")
        print("         FIN DEL REPORTE                 ")
        print("=========================================\n")

    elif opcion == "7":
        
        print("\nGuardando cambios pendientes...")
        print("Limpiando memoria de la aplicación...")
        print("Cerrando el Sistema de Gestión Académica. ¡Hasta luego!")
        
        # La instrucción break destruye el ciclo while y termina el programa
        break


    break
    