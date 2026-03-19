package basics.OOP.abstract_p;



public class EmpleadoFijo extends Empleado{
    double salarioMensual;

    public EmpleadoFijo(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }

    @Override
    public double calcularSalario() {
        System.out.println("Salario Mensual:  "+salarioMensual);
        return salarioMensual;
    }

}
