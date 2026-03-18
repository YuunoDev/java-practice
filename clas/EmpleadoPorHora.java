package clas;

public class EmpleadoPorHora extends Empleado{
    double tarifaPorHora;
    int horasTrabajadas;

    public EmpleadoPorHora(double tarifaPorHora, int horasTrabajadas) {
        this.tarifaPorHora = tarifaPorHora;
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularSalario() {
        System.out.println("Tarifa por hora: "+(tarifaPorHora*horasTrabajadas)+" horas trabajadas: "+horasTrabajadas);
        return tarifaPorHora*horasTrabajadas;
    }
    
}
