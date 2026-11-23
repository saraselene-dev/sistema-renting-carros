public class CarroSedan extends Vehiculo {
    
    private String TipoCombustible; // Gasolina, diésel o eléctrico
    private String Transmision;// Automática o manual

    public CarroSedan() {
    }

    public CarroSedan(String placa, String marca, int modelo, float precioDiario, String estado, String tipoCombustible,
            String transmision) {
        super(placa, marca, modelo, precioDiario, estado);
        TipoCombustible = tipoCombustible;
        Transmision = transmision;
    }

    public String getTipoCombustible() {
        return TipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        TipoCombustible = tipoCombustible;
    }

    public String getTransmision() {
        return Transmision;
    }

    public void setTransmision(String transmision) {
        Transmision = transmision;
    }

    @Override
    public String getTipo() {
        return "Sedan";
    }

}
