from models import Alumno, Profesor, Notas


# CONFIGURACIÓN INICIAL DEL SISTEMA

# Usamos un "interruptor" para mantener el programa encendido.
# Cuando el usuario elija salir (Opción 7), lo cambiaremos a False.
sistema_encendido = True

while sistema_encendido:
    
    # MENÚ PRINCIPAL

    print("\n=========================================")
    print("   SISTEMA DE GESTIÓN ACADÉMICA (SGA)    ")
    print("=========================================")
    print("1. Registrar Alumno")
    print("2. Registrar Profesor")
    print("3. Registrar Notas a un Alumno")
    print("4. Deshacer Último Registro de Nota")
    print("5. Generar Cola de Certificados")
    print("6. Mostrar Reporte General")
    print("7. Salir")
    print("=========================================")

    # Pedimos la opción al usuario
    opcion = input("Seleccione una opción: ")


    # OPCIÓN 1: REGISTRAR ALUMNO

    if opcion == "1":
        print("\n--- Usted ha seleccionado: Registrar Alumno ---")

        cedula = input("Ingrese la cédula del alumno: ")
        nombre = input("Ingrese el nombre del alumno: ")
        correo = input("Ingrese el correo del alumno: ")
        programa = input("Ingrese el programa del alumno: ")
        
        # Usamos la clase Alumno para estructurar los datos
        nuevo_alumno = Alumno(cedula, nombre, correo, programa)
        
        # Guardamos en el archivo .txt en modo "a" (agregar al final)
        archivo = open("alumnos.txt", "a")
        archivo.write(f"{nuevo_alumno.cedula},{nuevo_alumno.nombre},{nuevo_alumno.correo},{nuevo_alumno.programa}\n")
        archivo.close()
                
        print("¡Alumno registrado y guardado exitosamente!")
        
    # OPCIÓN 2: REGISTRAR PROFESOR

    elif opcion == "2":
        print("\n--- Usted ha seleccionado: Registrar Profesor ---")

        cedula = input("Ingrese la cédula del profesor: ")
        nombre = input("Ingrese el nombre del profesor: ")
        correo = input("Ingrese el correo del profesor: ")
        materia = input("Ingrese la materia del profesor: ")
         
        # Usamos la clase Profesor
        nuevo_profesor = Profesor(cedula, nombre, correo, materia)
            
        archivo = open("profesores.txt", "a")
        archivo.write(f"{nuevo_profesor.cedula},{nuevo_profesor.nombre},{nuevo_profesor.correo},{nuevo_profesor.materia}\n")
        archivo.close()
                    
        print("¡Profesor registrado y guardado exitosamente!")    

    # OPCIÓN 3: REGISTRAR NOTAS A UN ALUMNO

    elif opcion == "3":
        print("\n--- Usted ha seleccionado: Registrar Notas a un Alumno ---")
        
        cedula_buscada = input("Ingrese la cédula del alumno a calificar: ")
        
        # 1. Pedimos la primera nota y usamos la clase Notas
        valor_nota = input("Ingrese la nota: ")
        nota_estudiante = Notas(cedula_buscada, valor_nota)
        nota_estudiante.mostrar_Notas() 
        
        # 2. Acumulamos la nota en una variable de texto
        texto_notas = "," + nota_estudiante.notas
        
        # 3. Ciclo para preguntar si desea agregar más notas
        respuesta = input("¿Quiere subir otra nota? (si/no): ")
        while respuesta == "si":
            valor_nota = input("Ingrese la nota: ")
            
            nota_nueva = Notas(cedula_buscada, valor_nota)
            nota_nueva.mostrar_Notas()
            
            texto_notas = texto_notas + "," + nota_nueva.notas
            respuesta = input("¿Quiere subir otra nota? (si/no): ")

        # 4. Actualizamos el archivo del alumno
        archivo = open("alumnos.txt", "r")
        lineas = archivo.readlines()
        archivo.close()

        archivo = open("alumnos.txt", "w")
        for linea in lineas:
            datos = linea.split(",") 
            cedula_en_archivo = datos[0] 

            if cedula_en_archivo == cedula_buscada:
                linea_sin_enter = linea.replace("\n", "")
                linea_nueva = linea_sin_enter + texto_notas + "\n"
                archivo.write(linea_nueva)
            else:
                archivo.write(linea)

        archivo.close()
        print("Notas registradas correctamente.")
            

    # OPCIÓN 4: DESHACER ÚLTIMO REGISTRO DE NOTA (LIFO)

    elif opcion == "4":
        print("\n--- Usted ha seleccionado: Deshacer Última Nota ---")
        
        cedula_buscada = input("Ingresa la cédula del alumno para eliminar su última nota: ")

        # Leemos los datos actuales
        archivo = open("alumnos.txt", "r")
        lineas = archivo.readlines()
        archivo.close()

        # Sobrescribimos el archivo con la corrección
        archivo = open("alumnos.txt", "w")
        for linea in lineas:
            linea_limpia = linea.replace("\n", "")
            datos = linea_limpia.split(",") 
            cedula_en_archivo = datos[0] 

            if cedula_en_archivo == cedula_buscada:
                # Aplicamos lógica de Pila (LIFO) eliminando el último elemento
                datos.pop()
                linea_nueva = ",".join(datos)
                archivo.write(linea_nueva + "\n")
            else:
                archivo.write(linea)

        archivo.close()
        print("Última nota eliminada con éxito.")


    # OPCIÓN 5: GENERAR COLA DE CERTIFICADOS

    elif opcion == "5":
        print("\n--- Generando Cola de Certificados ---")

        archivo_alumnos = open("alumnos.txt", "r")
        lineas = archivo_alumnos.readlines()
        archivo_alumnos.close()

        cola_certificados = [] # Aquí guardaremos a los aprobados

        # Evaluamos a cada alumno
        for linea in lineas:
            linea_limpia = linea.replace("\n", "")
            datos = linea_limpia.split(",")
            
            # Solo procesamos si el alumno tiene notas (más de 4 datos)
            if len(datos) > 4: 
                cedula = datos[0]
                nombre = datos[1]
                programa = datos[3].capitalize() # Normalizamos el texto (ej: curso -> Curso)
                
                # Cálculo de promedio
                suma_notas = 0
                cantidad_notas = 0
                for nota_texto in datos[4:]:
                    suma_notas = suma_notas + float(nota_texto)
                    cantidad_notas = cantidad_notas + 1
                
                promedio = suma_notas / cantidad_notas
                aprobado = False
                motivo = ""

                # --- APLICACIÓN DE REGLAS DE APROBACIÓN ---
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
                
                # Si cumple los requisitos, entra a la cola
                if aprobado == True:
                    datos_aprobado = [cedula, nombre, programa, round(promedio, 1), motivo]
                    cola_certificados.append(datos_aprobado)

        # Generación del archivo físico de reporte
        reporte = open("certificados_pendientes.txt", "w")
        
        reporte.write("=========================================\n")
        reporte.write("REPORTE DE CERTIFICADOS PENDIENTES\n")
        reporte.write("=========================================\n")
        reporte.write(f"Total de graduandos en cola: {len(cola_certificados)}\n\n")

        # Vaciamos la cola (FIFO)
        numero = 1
        for alumno in cola_certificados:
            cedula_alumno = alumno[0]
            nombre_alumno = alumno[1]
            programa_alumno = alumno[2]
            promedio_alumno = alumno[3]
            motivo_alumno = alumno[4] 
            
            reporte.write(f"{numero}. [V-{cedula_alumno}] {nombre_alumno}\n")
            reporte.write(f"  - Programa: {programa_alumno}\n")
            reporte.write(f"  - Promedio Final: {promedio_alumno}\n")
            reporte.write(f"  - Estatus: {motivo_alumno}\n\n")
            
            numero = numero + 1

        reporte.write("=========================================\n")
        reporte.write("* Fin del reporte - Generado por SGA-DO *\n")
        reporte.close()
        
        print("¡Reporte generado! Revisa tu archivo 'certificados_pendientes.txt'.")


    # OPCIÓN 6: MOSTRAR REPORTE GENERAL

    elif opcion == "6":
        print("\n=========================================")
        print("          REPORTE GENERAL SGA            ")
        print("=========================================\n")

        # --- PROFESORES ---
        print("--- LISTA DE PROFESORES ACTIVOS ---")
        archivo_profesores = open("profesores.txt", "r")
        lineas_profesores = archivo_profesores.readlines()
        archivo_profesores.close()

        for linea in lineas_profesores:
            linea_limpia = linea.replace("\n", "")
            datos = linea_limpia.split(",")
            
            if len(datos) >= 4:
                cedula_prof = datos[0]
                nombre_prof = datos[1]
                materia_prof = datos[3]
                print(f"Profesor: {nombre_prof} | Cédula: {cedula_prof} | Materia: {materia_prof} | Estatus: Activo")

        # --- ALUMNOS ---
        print("\n--- LISTA DE ALUMNOS REGISTRADOS ---")
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
                
                # Determinamos el estatus basado en si existen notas
                if len(datos) > 4:
                    estatus = "Con notas registradas"
                else:
                    estatus = "Registrado (Sin notas)"
                    
                print(f"Alumno: {nombre_alum} | Cédula: {cedula_alum} | Programa: {programa_alum} | Estatus: {estatus}")
        
        print("\n=========================================")
        print("         FIN DEL REPORTE                 ")
        print("=========================================\n")


    # OPCIÓN 7: SALIR DEL SISTEMA

    elif opcion == "7":
        print("\nGuardando cambios pendientes...")
        print("Limpiando memoria de la aplicación...")
        print("Cerrando el Sistema de Gestión Académica. ¡Hasta luego!")
        
        # Apagamos el interruptor para que el ciclo while termine naturalmente
        sistema_encendido = False


    # MANEJO DE OPCIONES INVÁLIDAS

    else:
        print("\n¡Error! Opción no válida. Por favor, ingrese un número del 1 al 7.")