public abstract class Vehiculo {

    private String Placa;
    private String Marca;
    private int Modelo;
    private float PrecioDiario;
    private String Estado; // Disponible o alquilado

    public Vehiculo() {
    }

    public Vehiculo(String placa, String marca, int modelo, float precioDiario, String estado) {
        Placa = placa;
        Marca = marca;
        Modelo = modelo;
        PrecioDiario = precioDiario;
        Estado = estado;
    }

    public String getPlaca() {
        return Placa;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public int getModelo() {
        return Modelo;
    }

    public void setModelo(int modelo) {
        Modelo = modelo;
    }

    public float getPrecioDiario() {
        return PrecioDiario;
    }

    public void setPrecioDiario(float precioDiario) {
        PrecioDiario = precioDiario;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public abstract String getTipo();

}
