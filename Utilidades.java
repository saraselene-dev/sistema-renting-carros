import java.util.Scanner;
import java.util.Stack;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

public class Utilidades {

    private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int contadorContratos = 1;

    public int opcionesMenuPrincipal(Scanner sc) {
        System.out.println("1. GESTIÓN DE CLIENTES.");
        System.out.println("2. GESTIÓN DE VEHÍCULOS.");
        System.out.println("3. GESTIÓN DE CONTRATOS DE RENTING.");
        System.out.println("4. IMPRIMIR INFORME GENERAL.");
        System.out.println("5. SALIR DEL SISTEMA.");
        System.out.print("Ingrese una opción: ");
        int opc = sc.nextInt();
        sc.nextLine();
        System.out.println("-------------------------------------");
        return opc;
    }

    public int opcionesGestionClientes(Scanner sc) {
        System.out.println("1. GESTIÓN DE CLIENTES.");
        System.out.println("1.1. Registrar cliente.");
        System.out.println("1.2. Modificar cliente.");
        System.out.println("1.3. Eliminar cliente.");
        System.out.println("1.4. Buscar cliente.");
        System.out.println("1.5. Volver al menú principal.");
        System.out.print("Ingrese una opción: 1.");
        int opc = sc.nextInt();
        System.out.println("-------------------------------------");
        sc.nextLine();
        return opc;
    }

    public int opcionesGestionVehiculos(Scanner sc) {
        System.out.println("2. GESTIÓN DE VEHÍCULOS.");
        System.out.println("2.1. Registrar vehículo.");
        System.out.println("2.2. Modificar vehículo.");
        System.out.println("2.3. Eliminar vehículo.");
        System.out.println("2.4. Buscar vehículo.");
        System.out.println("2.5. Volver al menú principal.");
        System.out.print("Ingrese una opción: 2.");
        int opc = sc.nextInt();
        System.out.println("-------------------------------------");
        sc.nextLine();
        return opc;
    }

    public int opcionesGestionContratos(Scanner sc) {
        System.out.println("3. GESTIÓN DE CONTRATOS DE RENTING.");
        System.out.println("3.1. Registrar nuevo contrato.");
        System.out.println("3.2. Modificar contrato.");
        System.out.println("3.3. Finalizar contrato (devolución).");
        System.out.println("3.4. Buscar contrato.");
        System.out.println("3.5. Volver al menú principal.");
        System.out.print("Ingrese una opción: 3.");
        int opc = sc.nextInt();
        System.out.println("-------------------------------------");
        sc.nextLine();
        return opc;
    }

    public LinkedList<Cliente> selectorGestionClientes(LinkedList<Cliente> registroClientes,
            Queue<ContratoRenting> contratosActivos, Stack<ContratoRenting> contratosFinalizados,
            LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        boolean salir = false;
        int opc = 0;
        while (!salir) {
            opc = opcionesGestionClientes(sc);
            switch (opc) {
                case 1:
                    registroClientes = registrarCliente(registroClientes, sc);
                    break;
                case 2:
                    registroClientes = modificarCliente(registroClientes, sc);
                    break;
                case 3:
                    registroClientes = eliminarCliente(registroClientes, contratosActivos, contratosFinalizados,
                            registroVehiculos, sc);
                    break;
                case 4:
                    System.out.println(consultarCliente(registroClientes, sc));
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    salir = true;
                    break;
                default:
                    System.out.println("Ingrese un valor correcto (1-5).");
                    break;
            }
            System.out.println("-------------------------------------");
        }
        // este metodo retorna la estructura principal de su gestion (registroClientes),
        // la cola de contratosActivos y la lista registroVehiculos se modifican por
        // referencia
        // CAMBIO: la pila de contratosFinalizados ya no se modifica al eliminar un
        // cliente
        return registroClientes;
    }

    public LinkedList<Vehiculo> selectorGestionVehiculos(LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        boolean salir = false;
        int opc = 0;
        while (!salir) {
            opc = opcionesGestionVehiculos(sc);
            switch (opc) {
                case 1:
                    registroVehiculos = registrarVehiculo(registroVehiculos, sc);
                    break;
                case 2:
                    registroVehiculos = modificarVehiculo(registroVehiculos, sc);
                    break;
                case 3:
                    registroVehiculos = eliminarVehiculo(registroVehiculos, sc);
                    break;
                case 4:
                    System.out.println(consultarVehiculo(registroVehiculos, sc));
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    salir = true;
                    break;
                default:
                    System.out.println("Ingrese un valor correcto (1-5).");
                    break;
            }
            System.out.println("-------------------------------------");
        }
        return registroVehiculos;
    }

    public Queue<ContratoRenting> selectorGestionContratos(Queue<ContratoRenting> contratosActivos,
            Stack<ContratoRenting> contratosFinalizados, LinkedList<Cliente> registroClientes,
            LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        boolean salir = false;
        int opc = 0;
        while (!salir) {
            opc = opcionesGestionContratos(sc);
            switch (opc) {
                case 1:
                    contratosActivos = registrarContrato(contratosActivos, registroClientes, registroVehiculos, sc);
                    break;
                case 2:
                    contratosActivos = modificarContrato(contratosActivos, contratosFinalizados, registroVehiculos, sc);
                    break;
                case 3:
                    contratosActivos = finalizarContrato(contratosActivos, contratosFinalizados, registroVehiculos, sc);
                    break;
                case 4:
                    System.out.println(consultarContrato(contratosActivos, contratosFinalizados, sc));
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    salir = true;
                    break;
                default:
                    System.out.println("Ingrese un valor correcto (1-5).");
                    break;
            }
            System.out.println("-------------------------------------");
        }
        // este metodo retorna la estructura principal de su gestion (contratosActivos),
        // la pila de contratosFinalizados se modifica por referencia
        return contratosActivos;
    }

    public LinkedList<Cliente> registrarCliente(LinkedList<Cliente> registroClientes, Scanner sc) {
        Cliente nuevoCliente = new Cliente();
        System.out.println("Registrar cliente.");
        nuevoCliente.setCedula(pedirCedulaNueva(registroClientes, sc));
        System.out.print("Nombre: ");
        nuevoCliente.setNombre(sc.nextLine());
        System.out.print("Apellido: ");
        nuevoCliente.setApellido(sc.nextLine());
        System.out.print("Teléfono: ");
        nuevoCliente.setTelefono(sc.nextLine());
        System.out.print("Dirección: ");
        nuevoCliente.setDireccion(sc.nextLine());
        System.out.print("Licencia: ");
        nuevoCliente.setLicenciaConduccion(sc.nextLine());
        registroClientes.add(nuevoCliente);
        System.out.println("Cliente registrado correctamente.");
        return registroClientes;
    }

    public String pedirCedulaNueva(LinkedList<Cliente> registroClientes, Scanner sc) {
        boolean existe;
        String cedula;

        do {
            existe = false;
            System.out.print("Cédula: ");
            cedula = sc.nextLine();
            Cliente cliente = buscarCliente(registroClientes, cedula);

            if (cliente != null) {
                System.out.println("La cédula ingresada ya existe en nuestra base de datos.");
                System.out.println("Ingrese una cédula nueva.");
                existe = true;
            }

        } while (existe);
        return cedula;
    }

    public Cliente pedirClienteRegistrado(LinkedList<Cliente> registroClientes, Scanner sc) {
        System.out.print("Cédula: ");
        String cedula = sc.nextLine();
        Cliente cliente = buscarCliente(registroClientes, cedula);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return null;
        }
        return cliente;
    }

    public Cliente buscarCliente(LinkedList<Cliente> registroClientes, String cedula) {
        for (Cliente c : registroClientes) {
            if (cedula.equals(c.getCedula())) {
                return c;
            }
        }
        return null;
    }

    public LinkedList<Cliente> modificarCliente(LinkedList<Cliente> registroClientes, Scanner sc) {
        System.out.println("Modificar cliente.");
        Cliente cliente = pedirClienteRegistrado(registroClientes, sc);

        if (cliente == null) {
            return registroClientes;
        }

        boolean salir = false;
        while (!salir) {

            System.out.println(mostrar(cliente));
            System.out.println("Ingrese el campo a modificar: ");
            System.out.println("1. Nombre.");
            System.out.println("2. Apellido.");
            System.out.println("3. Teléfono.");
            System.out.println("4. Dirección.");
            System.out.println("5. Licencia.");
            System.out.println("6. Salir.");
            System.out.print("Ingrese una opción: ");
            int opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    cliente.setNombre(sc.nextLine());
                    System.out.println("Nombre actualizado.");
                    break;
                case 2:
                    System.out.print("Nuevo apellido: ");
                    cliente.setApellido(sc.nextLine());
                    System.out.println("Apellido actualizado.");
                    break;
                case 3:
                    System.out.print("Nuevo teléfono: ");
                    cliente.setTelefono(sc.nextLine());
                    System.out.println("Teléfono actualizado.");
                    break;
                case 4:
                    System.out.print("Nueva dirección: ");
                    cliente.setDireccion(sc.nextLine());
                    System.out.println("Dirección actualizada.");
                    break;
                case 5:
                    System.out.print("Nueva licencia: ");
                    cliente.setLicenciaConduccion(sc.nextLine());
                    System.out.println("Licencia actualizada.");
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Ingrese un valor correcto (1-6).");
                    break;
            }
            System.out.println("-------------------------------------");
        }
        return registroClientes;
    }

    public String mostrar(Cliente cliente) {
        String texto = "Cédula: " + cliente.getCedula() + "\n";
        texto += "Nombre: " + cliente.getNombre() + "\n";
        texto += "Apellido: " + cliente.getApellido() + "\n";
        texto += "Teléfono: " + cliente.getTelefono() + "\n";
        texto += "Dirección: " + cliente.getDireccion() + "\n";
        texto += "Licencia: " + cliente.getLicenciaConduccion();
        return texto;
    }

    public LinkedList<Cliente> eliminarCliente(LinkedList<Cliente> registroClientes,
            Queue<ContratoRenting> contratosActivos,
            Stack<ContratoRenting> contratosFinalizados, LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        System.out.println("Eliminar cliente.");
        Cliente cliente = pedirClienteRegistrado(registroClientes, sc);

        if (cliente == null) {
            return registroClientes;
        }

        String cedula = cliente.getCedula();
        int cantidadContratos = 0;
        // CAMBIO: solo se cuentan los contratos activos, que son los que se eliminan.
        for (ContratoRenting c : contratosActivos) {
            if (cedula.equals(c.getCedulaCliente())) {
                cantidadContratos++;
            }
        }

        boolean salir = false;
        while (!salir) {

            System.out.println("Cliente seleccionado: ");
            System.out.println(mostrar(cliente));
            if (cantidadContratos > 0) {
                System.out.println(
                        "ADVERTENCIA: El cliente tiene " + cantidadContratos + " contrato(s). Se eliminarán también.");
            }
            System.out.println("Está a punto de eliminar al cliente, ¿está seguro?");
            System.out.println("Una vez eliminado no podrá deshacer esta acción.");
            System.out.print("Ingrese una opción (Si/No): ");
            String opc = sc.nextLine();

            if (opc.equalsIgnoreCase("si")) {
                // Se guardan primero en una lista aparte, porque quitar elementos
                // mientras se recorre con for-each lanza ConcurrentModificationException.
                LinkedList<ContratoRenting> activosCliente = new LinkedList<>();
                for (ContratoRenting c : contratosActivos) {
                    if (cedula.equals(c.getCedulaCliente())) {
                        activosCliente.add(c);
                    }
                }
                for (ContratoRenting c : activosCliente) {
                    Vehiculo vehiculo = buscarPlaca(registroVehiculos, c.getPlacaVehiculo());
                    if (vehiculo != null) {
                        vehiculo.setEstado("Disponible");
                    }
                    contratosActivos.remove(c);

                }
                // CAMBIO: los contratos finalizados del cliente se conservan (historial e
                // ingresos)

                registroClientes.remove(cliente);
                System.out.println("Cliente eliminado correctamente.");
                salir = true;
            } else if (opc.equalsIgnoreCase("no")) {
                System.out.println("Acción cancelada.");
                salir = true;
            } else {
                System.out.println("Ingrese una opción correcta (si/no).");
            }
            System.out.println("-------------------------------------");
        }
        return registroClientes;
    }

    public String consultarCliente(LinkedList<Cliente> registroClientes, Scanner sc) {
        System.out.println("Buscar cliente.");
        Cliente cliente = pedirClienteRegistrado(registroClientes, sc);
        String texto = "";
        if (cliente != null) {
            texto = mostrar(cliente);
        }
        return texto;
    }

    public LinkedList<Vehiculo> registrarVehiculo(LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        Vehiculo nuevoVehiculo;
        System.out.println("Registrar vehículo.");
        System.out.print("Tipo vehículo (1. Sedan, 2. Camioneta SUV): ");
        int tipo = sc.nextInt();
        sc.nextLine();

        if (!(tipo == 1 || tipo == 2)) {
            System.out.println("Tipo de vehículo no válido.");
            return registroVehiculos;
        }
        String placa = pedirPlacaNueva(registroVehiculos, sc);
        System.out.print("Marca: ");
        String marca = sc.nextLine();
        System.out.print("Modelo: ");
        int modelo = sc.nextInt();
        System.out.print("Precio diario: ");
        float precioDiario = sc.nextFloat();
        String estado = "Disponible";
        sc.nextLine();

        if (tipo == 1) {
            System.out.print("Tipo de combustible (Gasolina / Diésel / Eléctrico): ");
            String combustible = sc.nextLine();
            System.out.print("Tipo de transmisión (Automática / Manual): ");
            String transmision = sc.nextLine();
            nuevoVehiculo = new CarroSedan(placa, marca, modelo, precioDiario, estado, combustible, transmision);
        } else {
            System.out.print("Tipo tracción (4x2 / 4x4): ");
            String traccion = sc.nextLine();
            System.out.print("Capacidad del maletero (litros): ");
            float capacidadMaletero = sc.nextFloat();
            sc.nextLine();
            nuevoVehiculo = new CamionetaSUV(placa, marca, modelo, precioDiario, estado, traccion, capacidadMaletero);
        }

        registroVehiculos.add(nuevoVehiculo);
        System.out.println("Vehículo registrado correctamente.");
        return registroVehiculos;
    }

    public String pedirPlacaNueva(LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        boolean existe;
        String placa;

        do {
            existe = false;
            System.out.print("Placa: ");
            placa = sc.nextLine();

            for (Vehiculo v : registroVehiculos) {
                if (placa.equalsIgnoreCase(v.getPlaca())) {
                    System.out.println("La placa ingresada ya existe en nuestra base de datos.");
                    System.out.println("Ingrese una placa nueva.");
                    existe = true;
                    break;
                }
            }
        } while (existe);
        return placa;
    }

    public Vehiculo buscarPlaca(LinkedList<Vehiculo> registroVehiculos, String placa) {
        for (Vehiculo v : registroVehiculos) {
            if (placa.equalsIgnoreCase(v.getPlaca())) {
                return v;
            }
        }
        return null;
    }

    public String mostrar(Vehiculo vehiculo) {
        String texto = "Placa: " + vehiculo.getPlaca() + "\n";
        texto += "Marca: " + vehiculo.getMarca() + "\n";
        texto += "Modelo: " + vehiculo.getModelo() + "\n";
        texto += "Precio diario: " + vehiculo.getPrecioDiario() + "\n";
        texto += "Estado: " + vehiculo.getEstado() + "\n";
        texto += "Tipo: " + vehiculo.getTipo() + "\n";

        if (vehiculo instanceof CarroSedan) {
            CarroSedan sedan = (CarroSedan) vehiculo;
            texto += "Combustible: " + sedan.getTipoCombustible() + "\n";
            texto += "Transmisión: " + sedan.getTransmision();
        } else if (vehiculo instanceof CamionetaSUV) {
            CamionetaSUV suv = (CamionetaSUV) vehiculo;
            texto += "Tracción: " + suv.getTraccion() + "\n";
            texto += "Capacidad maletero: " + suv.getCapacidadMaletero();
        }
        return texto;
    }

    public String consultarVehiculo(LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        System.out.println("Buscar vehículo.");
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        Vehiculo vehiculo = buscarPlaca(registroVehiculos, placa);
        String texto;
        if (vehiculo == null) {
            texto = "Vehículo no encontrado.";
        } else {
            texto = mostrar(vehiculo);
        }
        return texto;
    }

    public LinkedList<Vehiculo> modificarVehiculo(LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        System.out.println("Modificar vehículo.");
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        Vehiculo vehiculo = buscarPlaca(registroVehiculos, placa);

        if (vehiculo == null) {
            System.out.println("Vehículo no encontrado.");
            return registroVehiculos;
        }

        boolean esSedan = vehiculo instanceof CarroSedan;
        boolean esSUV = vehiculo instanceof CamionetaSUV;
        boolean salir = false;
        while (!salir) {
            System.out.println(mostrar(vehiculo));
            System.out.println("Ingrese el campo a modificar: ");
            System.out.println("1. Marca.");
            System.out.println("2. Modelo.");
            System.out.println("3. Precio diario.");
            if (esSedan) {
                System.out.println("4. Tipo de combustible.");
                System.out.println("5. Transmisión.");
            } else if (esSUV) {
                System.out.println("4. Tracción.");
                System.out.println("5. Capacidad del maletero.");
            }
            System.out.println("6. Salir.");
            System.out.print("Ingrese una opción: ");
            int opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1:
                    System.out.print("Nueva marca: ");
                    vehiculo.setMarca(sc.nextLine());
                    System.out.println("Marca actualizada.");
                    break;
                case 2:
                    System.out.print("Nuevo modelo: ");
                    vehiculo.setModelo(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Modelo actualizado.");
                    break;
                case 3:
                    // CAMBIO: no se permite cambiar el precio de un vehículo alquilado,
                    // para respetar la tarifa pactada en su contrato activo
                    if (vehiculo.getEstado().equalsIgnoreCase("Alquilado")) {
                        System.out.println("No se puede cambiar el precio de un vehículo alquilado.");
                    } else {
                        System.out.print("Nuevo precio diario: ");
                        vehiculo.setPrecioDiario(sc.nextFloat());
                        sc.nextLine();
                        System.out.println("Precio diario actualizado.");
                    }
                    break;
                case 4:
                    if (esSedan) {
                        System.out.print("Nuevo tipo de combustible (Gasolina / Diésel / Eléctrico): ");
                        ((CarroSedan) vehiculo).setTipoCombustible(sc.nextLine());
                        System.out.println("Tipo de combustible actualizado.");
                    } else if (esSUV) {
                        System.out.print("Nueva tracción (4x2 / 4x4): ");
                        ((CamionetaSUV) vehiculo).setTraccion(sc.nextLine());
                        System.out.println("Tracción actualizada.");
                    }
                    break;
                case 5:
                    if (esSedan) {
                        System.out.print("Nueva transmisión (Automática / Manual): ");
                        ((CarroSedan) vehiculo).setTransmision(sc.nextLine());
                        System.out.println("Transmisión actualizada.");
                    } else if (esSUV) {
                        System.out.print("Nueva capacidad del maletero: ");
                        ((CamionetaSUV) vehiculo).setCapacidadMaletero(sc.nextFloat());
                        sc.nextLine();
                        System.out.println("Capacidad del maletero actualizada.");
                    }
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Ingrese un valor correcto (1-6).");
                    break;
            }
            System.out.println("-------------------------------------");
        }
        return registroVehiculos;
    }

    public LinkedList<Vehiculo> eliminarVehiculo(LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        System.out.println("Eliminar vehículo.");
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        Vehiculo vehiculo = buscarPlaca(registroVehiculos, placa);

        if (vehiculo == null) {
            System.out.println("Vehículo no encontrado.");
            return registroVehiculos;
        }

        if (vehiculo.getEstado().equalsIgnoreCase("Alquilado")) {
            System.out.println("No se puede eliminar: el vehículo tiene un contrato activo.");
            return registroVehiculos;
        }

        boolean salir = false;
        while (!salir) {

            System.out.println("Vehículo seleccionado: ");
            System.out.println(mostrar(vehiculo));
            System.out.println("Está a punto de eliminar el vehículo, ¿está seguro?");
            System.out.println("Una vez eliminado no podrá deshacer esta acción.");
            System.out.print("Ingrese una opción (Si/No): ");
            String opc = sc.nextLine();

            if (opc.equalsIgnoreCase("si")) {
                registroVehiculos.remove(vehiculo);
                System.out.println("Vehículo eliminado correctamente.");
                salir = true;
            } else if (opc.equalsIgnoreCase("no")) {
                System.out.println("Acción cancelada.");
                salir = true;
            } else {
                System.out.println("Ingrese una opción correcta (si/no).");
            }
            System.out.println("-------------------------------------");
        }
        return registroVehiculos;
    }

    public Queue<ContratoRenting> registrarContrato(Queue<ContratoRenting> contratosActivos,
            LinkedList<Cliente> registroClientes, LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        System.out.println("Registrar contrato.");
        Cliente cliente = pedirClienteRegistrado(registroClientes, sc);

        if (cliente == null) {
            return contratosActivos;
        }

        if (tieneContratoActivo(contratosActivos, cliente.getCedula())) {
            System.out.println("El cliente ya tiene un contrato activo.");
            return contratosActivos;
        }

        System.out.println("Cliente: ");
        System.out.println(mostrar(cliente));

        Vehiculo vehiculo = pedirVehiculoDisponible(registroVehiculos, sc);
        if (vehiculo == null) {
            return contratosActivos;
        }
        String placa = vehiculo.getPlaca();
        String cedula = cliente.getCedula();
        LocalDate fechaInicialLocalDate = pedirFecha(sc);
        int dias = pedirDias(sc);
        LocalDate fechaFinalLocalDate = fechaInicialLocalDate.plusDays(dias);
        String fechaInicial = fechaInicialLocalDate.format(formatoFecha);
        String fechaFinal = fechaFinalLocalDate.format(formatoFecha);
        float valorDia = vehiculo.getPrecioDiario();
        float valorTotal = calcularValor(dias, valorDia);
        String id = "C" + contadorContratos;
        ContratoRenting nuevoContratoRenting = new ContratoRenting(id, cedula, placa, fechaInicial, fechaFinal, dias,
                valorTotal);
        contratosActivos.offer(nuevoContratoRenting);
        vehiculo.setEstado("Alquilado");
        contadorContratos++;
        System.out.println("Contrato registrado correctamente.");
        System.out.println("ID del contrato: " + id);
        return contratosActivos;
    }

    public boolean tieneContratoActivo(Queue<ContratoRenting> contratosActivos, String cedula) {
        for (ContratoRenting c : contratosActivos) {
            if (cedula.equals(c.getCedulaCliente())) {
                return true;
            }
        }
        return false;
    }

    public Vehiculo pedirVehiculoDisponible(LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        Vehiculo vehiculo = buscarPlaca(registroVehiculos, placa);

        if (vehiculo == null) {
            System.out.println("Vehículo no encontrado.");
            return null;
        }

        if (vehiculo.getEstado().equalsIgnoreCase("Alquilado")) {
            System.out.println("El vehículo no se encuentra disponible.");
            return null;
        } else {
            System.out.println("Vehículo encontrado.");
            System.out.println(mostrar(vehiculo));
        }

        return vehiculo;
    }

    // TODO: Generar validación de fecha correcta en hu8, aplicando trycatch

    public LocalDate pedirFecha(Scanner sc) {
        System.out.print("Fecha de inicio (DD/MM/AAAA): ");
        String fechaTexto = sc.nextLine();
        LocalDate fecha = LocalDate.parse(fechaTexto, formatoFecha);

        return fecha;
    }

    // TODO: Validación con try catch
    public int pedirDias(Scanner sc) {
        int dias;
        do {
            System.out.print("Número de días: ");
            dias = sc.nextInt();
            sc.nextLine();
            if (dias < 1) {
                System.out.println("La cantidad mínima de días es 1.");
            }
        } while (dias < 1);

        return dias;
    }

    public float calcularValor(int cantidadDias, float precioDiario) {
        float valorTotal = precioDiario * cantidadDias;
        return valorTotal;
    }

    public ContratoRenting buscarContrato(Queue<ContratoRenting> contratosActivos,
            Stack<ContratoRenting> contratosFinalizados, String idContrato) {
        for (ContratoRenting c : contratosActivos) {
            if (c.getIdContrato().equalsIgnoreCase(idContrato)) {
                return c;
            }
        }

        for (ContratoRenting c : contratosFinalizados) {
            if (c.getIdContrato().equalsIgnoreCase(idContrato)) {
                return c;
            }
        }

        return null;
    }

    public String mostrar(ContratoRenting contrato, Queue<ContratoRenting> contratosActivos) {
        String texto = "Id contrato: " + contrato.getIdContrato() + "\n";
        texto += "Cédula del cliente: " + contrato.getCedulaCliente() + "\n";
        texto += "Placa del vehículo: " + contrato.getPlacaVehiculo() + "\n";
        texto += "Fecha de inicio: " + contrato.getFechaInicio() + "\n";
        texto += "Fecha de fin: " + contrato.getFechaFin() + "\n";
        texto += "Total días: " + contrato.getTotalDias() + "\n";
        texto += "Valor total: " + contrato.getValorTotal() + "\n";

        if (contratosActivos.contains(contrato)) {
            texto += "Estado: Activo";
        } else {
            texto += "Estado: Finalizado";
        }
        return texto;
    }

    public String consultarContrato(Queue<ContratoRenting> contratosActivos,
            Stack<ContratoRenting> contratosFinalizados, Scanner sc) {
        System.out.println("Buscar contrato.");
        System.out.print("Id del contrato (ej. C1): ");
        String idContrato = sc.nextLine();
        ContratoRenting contrato = buscarContrato(contratosActivos, contratosFinalizados, idContrato);
        String texto;
        if (contrato == null) {
            texto = "Contrato no encontrado.";
        } else {
            texto = mostrar(contrato, contratosActivos);
        }
        return texto;
    }

    public Queue<ContratoRenting> modificarContrato(Queue<ContratoRenting> contratosActivos,
            Stack<ContratoRenting> contratosFinalizados, LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        System.out.println("Modificar contrato.");
        System.out.print("Id del contrato (ej. C1): ");
        String idContrato = sc.nextLine();
        ContratoRenting contrato = buscarContrato(contratosActivos, contratosFinalizados, idContrato);

        if (contrato == null) {
            System.out.println("Contrato no encontrado.");
            return contratosActivos;
        }

        if (!contratosActivos.contains(contrato)) {
            System.out.println("El contrato ya está finalizado y no se puede modificar.");
            return contratosActivos;
        }

        Vehiculo vehiculo = buscarPlaca(registroVehiculos, contrato.getPlacaVehiculo());

        if (vehiculo == null) {
            System.out.println("No se encontró el vehículo del contrato.");
            return contratosActivos;
        }

        boolean salir = false;
        while (!salir) {

            System.out.println(mostrar(contrato, contratosActivos));
            System.out.println("Ingrese el campo a modificar: ");
            System.out.println("1. Fecha de inicio.");
            System.out.println("2. Días de renting.");
            System.out.println("3. Salir.");
            System.out.print("Ingrese una opción: ");
            int opc = sc.nextInt();
            sc.nextLine();

            LocalDate fechaInicialLocalDate = null;
            int dias = 0;
            boolean modificado;

            switch (opc) {
                case 1:
                    fechaInicialLocalDate = pedirFecha(sc);
                    dias = contrato.getTotalDias();
                    modificado = true;
                    break;
                case 2:
                    dias = pedirDias(sc);
                    fechaInicialLocalDate = LocalDate.parse(contrato.getFechaInicio(), formatoFecha);
                    modificado = true;
                    break;
                case 3:
                    salir = true;
                    modificado = false;
                    break;
                default:
                    System.out.println("Ingrese un valor correcto (1-3).");
                    modificado = false;
                    break;
            }

            if (modificado) {
                LocalDate fechaFinalLocalDate = fechaInicialLocalDate.plusDays(dias);
                float valorTotal = calcularValor(dias, vehiculo.getPrecioDiario());
                contrato.setFechaInicio(fechaInicialLocalDate.format(formatoFecha));
                contrato.setFechaFin(fechaFinalLocalDate.format(formatoFecha));
                contrato.setTotalDias(dias);
                contrato.setValorTotal(valorTotal);
                System.out.println("Contrato actualizado.");
            }
            System.out.println("-------------------------------------");
        }
        return contratosActivos;
    }

    public Queue<ContratoRenting> finalizarContrato(Queue<ContratoRenting> contratosActivos,
            Stack<ContratoRenting> contratosFinalizados, LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        System.out.println("Finalizar contrato.");
        System.out.print("Id del contrato (ej. C1): ");
        String idContrato = sc.nextLine();
        ContratoRenting contrato = buscarContrato(contratosActivos, contratosFinalizados, idContrato);

        if (contrato == null) {
            System.out.println("Contrato no encontrado.");
            return contratosActivos;
        }

        if (!contratosActivos.contains(contrato)) {
            System.out.println("El contrato ya ha sido finalizado.");
            return contratosActivos;
        }

        Vehiculo vehiculo = buscarPlaca(registroVehiculos, contrato.getPlacaVehiculo());
        contratosActivos.remove(contrato);
        contratosFinalizados.push(contrato);

        if (vehiculo != null) {
            vehiculo.setEstado("Disponible");
        }

        System.out.println("Contrato finalizado correctamente.");

        return contratosActivos;
    }

}
