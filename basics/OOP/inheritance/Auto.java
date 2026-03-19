package basics.OOP.inheritance;

public class Auto extends Vehiculo{
    int numPuertas;
    
    public Auto(String marca, String modelo, int año, int numeroserie, int numPuertas) {
        super(marca, modelo, año, numeroserie);
        this.numPuertas = numPuertas;
    }

    @Override
    public void show() {
        System.out.println("Marca: "+this.marca+" Modelo: "+this.modelo+" Año: "+this.año+" Numero de puertas: "+this.numPuertas);
    }
}