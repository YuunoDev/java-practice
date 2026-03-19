package Ex;

public class Empleado {
    public String nombre;
    public double salario;
    public String departamento;

    
    public Empleado(String nombre, double salario, String departamento) {
        this.nombre = nombre;
        this.salario = salario;
        this.departamento = departamento;
    }


    public Empleado(String nombre, double salario) {
        this.nombre = nombre;
        this.salario = salario;
        this.departamento = "RH";
    }


    @Override
    public String toString() {
        return "Empleado [nombre=" + nombre + ", salario=" + salario + ", departamento=" + departamento + "]";
    }

}