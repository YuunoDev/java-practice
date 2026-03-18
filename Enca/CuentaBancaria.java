package Enca;

public class CuentaBancaria {
    private String numeroCuenta;
    private double saldo;
    private String titular;

    public CuentaBancaria(String numeroCuenta, double saldo, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.titular = titular;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }



    public double getSaldo() {
        return saldo;
    }



    public String getTitular() {
        return titular;
    }



    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void depositar(double cantidad){
        if(cantidad < 0){
            System.out.println("No puede ser menor");
        }else{
            this.saldo+=cantidad;
        }
    }
    
}
