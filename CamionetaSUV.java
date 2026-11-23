public class CamionetaSUV extends Vehiculo {

    private String Traccion; // 4x2 o 4x4
    private float CapacidadMaletero; // litros.

    public CamionetaSUV() {

    }

    public CamionetaSUV(String placa, String marca, int modelo, float precioDiario, String estado, String traccion,
            float capacidadMaletero) {
        super(placa, marca, modelo, precioDiario, estado);
        Traccion = traccion;
        CapacidadMaletero = capacidadMaletero;
    }

    public String getTraccion() {
        return Traccion;
    }

    public void setTraccion(String traccion) {
        Traccion = traccion;
    }

    public float getCapacidadMaletero() {
        return CapacidadMaletero;
    }

    public void setCapacidadMaletero(float capacidadMaletero) {
        CapacidadMaletero = capacidadMaletero;
    }

    @Override
    public String getTipo() {
        return "Camioneta SUV";
    }

}
