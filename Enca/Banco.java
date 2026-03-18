package Enca;

public class Banco {
    public static void main(String[] args) {
        CuentaBancaria cb = new CuentaBancaria("123456",100,"Adrian");

        System.out.println("Estado de cuenta: "+cb.getSaldo());

        cb.setTitular("Alonso");

        cb.depositar(200);

        System.out.println("Estado de cuenta: "+cb.getSaldo());

    }
}
