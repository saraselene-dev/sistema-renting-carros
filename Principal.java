import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Utilidades u = new Utilidades();
        int opc;
        boolean continuar = true;

        while (continuar) {
            System.out.println("-------------------------------------");
            System.out.println("SISTEMA GESTIÓN DE RENTING DE CARROS");
            System.out.println("-------------------------------------");
            opc = u.opcionesMenuPrincipal(sc);
            switch (opc) {
                case 1:
                    u.selectorGestionClientes(sc);
                    break;
                case 2:
                    u.selectorGestionVehiculos(sc);
                    break;
                case 3:
                    u.selectorGestionContratos(sc);
                    break;
                case 4:
                    System.out.println("Pagina en mantenimiento.");
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Digite un valor correcto (1-5)");
                    break;
            }
        }
    }
}
