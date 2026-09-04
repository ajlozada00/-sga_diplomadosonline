// Clase modelo (POJO) para representar a los profesores activos
public class Profesor {
    
    // Espacios de memoria donde guardaremos los datos en forma de texto (String)
    public String cedula;
    public String nombre;
    public String correo;
    public String materia; // La materia que imparte el profesor

    // Constructor: Se ejecuta automáticamente cada vez que hacemos un 'new Profesor'
    public Profesor(String cedula, String nombre, String correo, String materia) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.materia = materia;
    }
}