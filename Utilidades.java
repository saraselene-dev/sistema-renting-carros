import java.util.Scanner;

public class Utilidades {

    public int opcionesMenuPrincipal(Scanner sc){
        System.out.println("1. GESTION DE CLIENTES.");
        System.out.println("2. GESTION DE VEHICULOS.");
        System.out.println("3. GESTION DE CONTRATOS DE RENTING.");
        System.out.println("4. IMPRIMIR INFORME GENERAL.");
        System.out.println("5. SALIR DEL SISTEMA.");
        System.out.print("Ingrese una opción: ");
        int opc = sc.nextInt();
        System.out.println("-------------------------------------");
        return opc;
    }

    public int opcionesGestionClientes(Scanner sc){
        System.out.println("1. GESTION DE CLIENTES.");
        System.out.println("1.1. Registrar cliente.");
        System.out.println("1.2. Modificar cliente.");
        System.out.println("1.3. Eliminar cliente.");
        System.out.println("1.4. Buscar cliente.");
        System.out.println("1.5 Volver al menu principal.");
        System.out.print("Ingrese una opción: 1.");
        int opc = sc.nextInt();
        return opc;
    }

    public int opcionesGestionVehiculos(Scanner sc){
        System.out.println("2. GESTION DE VEHICULOS.");
        System.out.println("2.1. Registrar vehiculo.");
        System.out.println("2.2. Modificar vehiculo.");
        System.out.println("2.3. Eliminar vehiculo.");
        System.out.println("2.4. Buscar vehiculo.");
        System.out.println("2.5 Volver al menu principal.");
        System.out.print("Ingrese una opción: 2.");
        int opc = sc.nextInt();
        return opc;
    }

    public int opcionesGestionContratos(Scanner sc){
        System.out.println("3. GESTION DE CONTRATOS DE RENTING.");
        System.out.println("3.1. Registrar nuevo contrato.");
        System.out.println("3.2. Modificar contrato.");
        System.out.println("3.3. Finalizar contrato (devolución).");
        System.out.println("3.4. Buscar contrato.");
        System.out.println("3.5 Volver al menu principal.");
        System.out.print("Ingrese una opción: 3.");
        int opc = sc.nextInt();
        return opc;
    }

    // TODO: decidir el retorno de los metodos selectore, por el momento quedan con int
    public int selectorGestionClientes(Scanner sc){

        boolean salir = false;
        int opc = 0;
        while (!salir) {
            opc = opcionesGestionClientes(sc);
            switch (opc) {
                case 1:
                    System.out.println("Pagina en mantenimiento");
                    break;
                case 2:
                    System.out.println("Pagina en mantenimiento");
                    break;
                case 3:
                    System.out.println("Pagina en mantenimiento");
                    break;
                case 4:
                    System.out.println("Pagina en mantenimiento");
                    break;
                case 5:
                    System.out.println("Volviendo al menu principal...");
                    salir = true;
                    break;
                default:
                    System.out.println("Digite un valor correcto (1-5)");
                    break;
            }
        }
        return opc;
    }

    public int selectorGestionVehiculos(Scanner sc){
        boolean salir = false;
        int opc = 0;
        while (!salir) {
            opc = opcionesGestionVehiculos(sc);
            switch (opc) {
                case 1:
                    System.out.println("Pagina en mantenimiento");
                    break;
                case 2:
                    System.out.println("Pagina en mantenimiento");
                    break;
                case 3:
                    System.out.println("Pagina en mantenimiento");
                    break;
                case 4:
                    System.out.println("Pagina en mantenimiento");
                    break;
                case 5:
                    System.out.println("Volviendo al menu principal...");
                    salir = true;
                    break;
                default:
                    System.out.println("Digite un valor correcto (1-5)");
                    break;
            }
        }
        return opc;
    }

    public int selectorGestionContratos(Scanner sc){
        boolean salir = false;
        int opc = 0;
        while (!salir) {
            opc = opcionesGestionContratos(sc);
            switch (opc) {
                case 1:
                    System.out.println("Pagina en mantenimiento");
                    break;
                case 2:
                    System.out.println("Pagina en mantenimiento");
                    break;
                case 3:
                    System.out.println("Pagina en mantenimiento");
                    break;
                case 4:
                    System.out.println("Pagina en mantenimiento");
                    break;
                case 5:
                    System.out.println("Volviendo al menu principal...");
                    salir = true;
                    break;
                default:
                    System.out.println("Digite un valor correcto (1-5)");
                    break;
            }
        }
        return opc;
    }
}

