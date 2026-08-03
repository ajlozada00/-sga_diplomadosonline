print ("===============================")
print ("1. Registrar Alumno")
print ("2. Registrar Profesor")
print ("3. Registrar Notas a un Alumno")
print ("4. Deshacer Último Registro de Nota")
print ("5. Generar Cola de Certificados")
print ("6. Mostrar Reporte General")
print ("7. Salir")
print ("===============================")

while True:


    opcion = str (input("Seleccione una opción:: "))

    if opcion == "1":
        
        print (f" Usted ha seleccionado Registrar Alumno")

    elif opcion == "2":
        opcion = input("opcion: ")
        print (f" Usted ha seleccionado Registrar Profesor")

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