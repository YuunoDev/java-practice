package v;

public class Auto extends Vehiculo{
    int numPuertas;
    
    public Auto(String marca, String modelo, int año, int numPuertas) {
        super(marca, modelo, año);
        this.numPuertas = numPuertas;
    }


    @Override
    public void show() {
        System.out.println("Marca: "+this.marca+" Modelo: "+this.modelo+" Año: "+this.año+" Numero de puertas: "+this.numPuertas);
    }
}