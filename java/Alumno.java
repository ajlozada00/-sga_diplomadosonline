// Clase modelo (POJO) para representar a los estudiantes en el sistema
public class Alumno {
    
    // Variables públicas para que el archivo Main pueda acceder a ellas fácilmente
    public String cedula;
    public String nombre;
    public String correo;
    public String programa; // Ejemplo: Curso, Diplomado o Bootcamp

    // Constructor: Es el "molde" que nos obliga a dar estos 4 datos al crear un alumno nuevo
    public Alumno(String cedula, String nombre, String correo, String programa) {
        // "this" significa: guarda este dato en la caja de ESTE alumno en particular
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.programa = programa;
    }
}