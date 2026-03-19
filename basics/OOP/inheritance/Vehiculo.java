package basics.OOP.inheritance;

public class Vehiculo{
    public String marca;
    public String modelo;
    public int año;
    protected int numeroserie; // only accessible in the same package and subclasses. 
    
    public Vehiculo(String marca, String modelo, int año, int numeroserie) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.numeroserie=numeroserie;
    }

    public void show() {
        System.out.println("Marca: "+this.marca+" Modelo: "+this.modelo+" Año: "+this.año);
    }
}