import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Utilidades u = new Utilidades();
        int opc;
        boolean continuar = true;
        LinkedList<Cliente> registroClientes = new LinkedList<>();
        LinkedList<Vehiculo> registroVehiculos = new LinkedList<>();
        Queue<ContratoRenting> contratosActivos = new LinkedList<>();
        Stack<ContratoRenting> contratosFinalizados = new Stack<>();

        while (continuar) {
            System.out.println("-------------------------------------");
            System.out.println("SISTEMA GESTIÓN DE RENTING DE CARROS");
            System.out.println("-------------------------------------");
            opc = u.opcionesMenuPrincipal(sc);
            switch (opc) {
                case 1:
                    registroClientes = u.selectorGestionClientes(registroClientes, contratosActivos,
                            contratosFinalizados, registroVehiculos, sc);
                    break;
                case 2:
                    registroVehiculos = u.selectorGestionVehiculos(registroVehiculos, sc);
                    break;
                case 3:
                    contratosActivos = u.selectorGestionContratos(contratosActivos, contratosFinalizados,
                            registroClientes, registroVehiculos, sc);
                    break;
                case 4:
                    System.out.println("Página en mantenimiento.");
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Ingrese un valor correcto (1-5).");
                    break;
            }
        }
        sc.close();
    }
}
