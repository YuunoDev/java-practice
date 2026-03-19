package v;

public class Vehiculo{
    String marca;
    String modelo;
    int año;
    
    public Vehiculo(String marca, String modelo, int año) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
    }

    public void show() {
        System.out.println("Marca: "+this.marca+" Modelo: "+this.modelo+" Año: "+this.año);
    }
}