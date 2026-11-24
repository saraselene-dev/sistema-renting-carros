public class ContratoRenting {
    private final String IdContrato;
    private final String CedulaCliente;
    private final String PlacaVehiculo;
    private String FechaInicio;
    private String FechaFin;
    private int TotalDias;
    private float ValorTotal;

    public ContratoRenting(String idContrato, String cedulaCliente, String placaVehiculo, String fechaInicio,
            String fechaFin, int totalDias, float valorTotal) {
        IdContrato = idContrato;
        CedulaCliente = cedulaCliente;
        PlacaVehiculo = placaVehiculo;
        FechaInicio = fechaInicio;
        FechaFin = fechaFin;
        TotalDias = totalDias;
        ValorTotal = valorTotal;
    }

    public String getIdContrato() {
        return IdContrato;
    }

    public String getCedulaCliente() {
        return CedulaCliente;
    }

    public String getPlacaVehiculo() {
        return PlacaVehiculo;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public int getTotalDias() {
        return TotalDias;
    }

    public float getValorTotal() {
        return ValorTotal;
    }

    public void setFechaInicio(String fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        FechaFin = fechaFin;
    }

    public void setTotalDias(int totalDias) {
        TotalDias = totalDias;
    }

    public void setValorTotal(float valorTotal) {
        ValorTotal = valorTotal;
    }

}