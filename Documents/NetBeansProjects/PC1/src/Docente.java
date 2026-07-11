public class Docente {

    enum Categoria {
        Principal, Asociado, Auxiliar
    }

    enum Postgrado {
        Doctorado, Maestria
    }

    private static int contador = 1;

    private String codigo;
    private String nombre;
    private Categoria categoria;
    private Postgrado postgrado;
    private int horasClase;
    private final double seguro = 0.03;

    public Docente(String nombre, Categoria categoria, Postgrado postgrado, int horasClase) {
        this.codigo = String.format("DOC%04d", contador++);
        this.nombre = nombre;
        this.categoria = categoria;
        this.postgrado = postgrado;
        this.horasClase = horasClase;
    }

    private double tarifaPorHora() {
        return switch (categoria) {
            case Principal -> 25.0;
            case Asociado -> 18.0;
            case Auxiliar -> 15.0;
        };
    }

    private double porcentajeBonificacion() {
        return switch (categoria) {
            case Principal -> (postgrado == Postgrado.Doctorado) ? 0.20 : 0.17;
            case Asociado -> (postgrado == Postgrado.Doctorado) ? 0.15 : 0.10;
            case Auxiliar -> (postgrado == Postgrado.Doctorado) ? 0.12 : 0.08;
        };
    }

    public double calcularPagoParcial() {
        return horasClase * tarifaPorHora();
    }

    public double calcularBonificacion() {
        return calcularPagoParcial() * porcentajeBonificacion();
    }

    public double calcularDescuentoSeguro() {
        return calcularPagoParcial() * seguro;
    }

    public double calcularSueldoFinal() {
        return calcularPagoParcial() + calcularBonificacion() - calcularDescuentoSeguro();
    }

    @Override
    public String toString() {
        return "\nCodigo: " + codigo +
               "\nNombre: " + nombre +
               "\nCategoria: " + categoria +
               "\nPostgrado: " + postgrado +
               "\nHoras: " + horasClase +
               "\nPago Parcial: " + String.format("%.2f", calcularPagoParcial()) +
               "\nBonificación: " + String.format("%.2f", calcularBonificacion()) +
               "\nDescuento Seguro: " + String.format("%.2f", calcularDescuentoSeguro()) +
               "\nSueldo Final: " + String.format("%.2f", calcularSueldoFinal()) +
               "\n---------------------------";
    }
}