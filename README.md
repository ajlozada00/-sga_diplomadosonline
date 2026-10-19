Sistema de Gestión Académica (SGA) - Evolución Multilenguaje

📖 ¿Qué es el programa?

El Sistema de Gestión Académica (SGA) es una aplicación de consola diseñada para administrar los registros de una institución educativa mediante persistencia de datos en archivos .txt.

Más allá de su funcionalidad, este repositorio representa un viaje arquitectónico. El proyecto fue desarrollado abordando tres paradigmas de programación distintos para comprender a fondo sus diferencias, ventajas y complejidades:

Fase 1 - Python (Prototipado rápido): El proyecto inició aquí debido a la simplicidad de su sintaxis y su alta abstracción. Permitió estructurar la lógica de negocio rápidamente en un entorno libre de restricciones estructurales (todas las clases y la lógica podían convivir en un solo archivo fluido).

Fase 2 - Java (Estructura y POO estricta): El sistema evolucionó hacia Java para aplicar principios de Programación Orientada a Objetos de nivel empresarial. Aquí el código se modularizó, obligando a usar un archivo por cada clase modelo (Alumno.java, Notas.java, etc.) y gestionando la memoria de forma automática mediante el Garbage Collector.

Fase 3 - C++ (Control nativo y Arquitectura): La migración final y más compleja. Se reestructuró el sistema para separar la interfaz de la implementación (creando archivos .hpp y .cpp como "menú" y "cocina"). Se pasó a un entorno de control manual de memoria y ejecución nativa de altísimo rendimiento.

⚙️ ¿Qué hace el sistema?

Independientemente del lenguaje en el que se ejecute (Python, Java o C++), el sistema cumple con las siguientes reglas de negocio unificadas:

Registrar Alumno / Profesor: Permite inscribir estudiantes y docentes solicitando sus datos básicos, con validación de lectura de archivos .txt para evitar cédulas duplicadas.

Registrar Notas: Permite cargar hasta un máximo de 3 notas por estudiante. Cuenta con validación estricta para rechazar letras, números negativos o calificaciones fuera del rango (0 a 20).

Deshacer la última nota: Funciona con lógica LIFO (Last In, First Out). Borra únicamente la última calificación registrada en la base de datos de un alumno específico.

Generar Cola de Certificados: Calcula los promedios y aplica reglas de aprobación según el programa (Curso, Diplomado o Bootcamp). Genera un archivo nuevo llamado certificados_pendientes.txt.

Mostrar Reporte General: Imprime un resumen de los profesores activos y los alumnos registrados, indicando su estatus.

🛠️ ¿Qué se instaló para hacerlo funcionar?

La configuración del entorno de desarrollo (usando VS Code) varió significativamente según el lenguaje:

Para Python: La experiencia más amigable. Solo requirió descargar el instalador oficial de Python, ejecutarlo y marcar la casilla para agregarlo al "PATH" de Windows.

Para Java: Requirió la instalación del Java Development Kit (JDK). Nota contextual: En ciertas regiones (como Venezuela), la descarga desde la página oficial de Oracle puede estar bloqueada, requiriendo el uso de una VPN para obtener las herramientas de desarrollo.

Para C++: La instalación más técnica. Requirió instalar MSYS2 (entorno UCRT64) para obtener el compilador g++ (MinGW-w64), el cual actúa como puente entre el código y la PC. Además, se instalaron extensiones específicas en VS Code para el enlazado de archivos.

Nota sobre C++ y Antivirus: Es común que Windows Defender identifique el archivo .exe recién compilado como un falso positivo y lo elimine, causando errores de ejecución. Se recomienda agregar exclusiones a la carpeta del proyecto.

🚀 ¿Cómo arrancar el programa?

Dependiendo de la versión del SGA que desees evaluar, las instrucciones de arranque son diferentes:

Versión Python

Abre la terminal en la carpeta de la versión Python.

Ejecuta el comando: python main.py

Versión Java

Abre la terminal en la carpeta de la versión Java.

Compila el proyecto ejecutando: javac Main.java (y los demás archivos si aplica).

Inicia la Máquina Virtual de Java con: java Main

Versión C++

Debido a la arquitectura multi-archivo (main.cpp, Alumno.cpp, Notas.cpp, Profesor.cpp), no se puede compilar un archivo aislado.

Opción Automática: En VS Code, abre main.cpp y presiona el botón de "Play" (o Ctrl + Shift + B) para usar la tarea configurada (tasks.json) que une todos los archivos.

Opción Manual: Abre la terminal en la carpeta del proyecto y ejecuta el script integrado: .\compilar.bat. Este archivo agrupa y compila a todos los "trabajadores" y lanza el programa de inmediato.
