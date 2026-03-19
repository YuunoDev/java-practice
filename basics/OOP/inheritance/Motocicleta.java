package basics.OOP.inheritance;

public class Motocicleta extends Vehiculo{
    String tipoCasco;
   
    public Motocicleta(String marca, String modelo, int año, int numeroserie, String tipoCasco) {
        super(marca, modelo, año, numeroserie);
        this.tipoCasco = tipoCasco;
    }

    @Override
    public void show() {
        System.out.println("Marca: "+this.marca+" Modelo: "+this.modelo+" Año: "+this.año+" Casco: "+this.tipoCasco);
    }
}
