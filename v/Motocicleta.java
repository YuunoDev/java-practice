package v;

public class Motocicleta extends Vehiculo{
    String tipoCasco;

    
    public Motocicleta(String marca, String modelo, int año, String tipoCasco) {
        super(marca, modelo, año);
        this.tipoCasco = tipoCasco;
    }

    @Override
    public void show() {
        System.out.println("Marca: "+this.marca+" Modelo: "+this.modelo+" Año: "+this.año+" Casco: "+this.tipoCasco);
    }
}
