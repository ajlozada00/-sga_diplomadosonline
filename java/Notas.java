// Clase para empaquetar una calificación junto con la cédula de su dueño
public class Notas {
    
    public String cedula;
    public String valorNota; // Se guarda como texto para facilitar escribirlo en el .txt

    // Constructor: Une la cédula del estudiante con la nota obtenida
    public Notas(String cedula, String valorNota) {
        this.cedula = cedula;
        this.valorNota = valorNota;
    }

    // Método "void": Hace una acción (imprimir) pero no devuelve datos matemáticos al sistema
    public void mostrar_Notas() {
        // System.out.println es el equivalente directo al print() de Python
        System.out.println(" Notas: " + this.valorNota);
    }
}