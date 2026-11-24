import java.util.Scanner;
import java.util.Stack;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

public class Utilidades {

    private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int contadorContratos = 1;
    private Validaciones va = new Validaciones();

    public int opcionesMenuPrincipal(Scanner sc) {
        System.out.println("-------------------------------------");
        System.out.println("SISTEMA GESTIÓN DE RENTING DE CARROS");
        System.out.println("-------------------------------------");
        System.out.println("1. GESTIÓN DE CLIENTES.");
        System.out.println("2. GESTIÓN DE VEHÍCULOS.");
        System.out.println("3. GESTIÓN DE CONTRATOS DE RENTING.");
        System.out.println("4. IMPRIMIR INFORME GENERAL.");
        System.out.println("5. SALIR DEL SISTEMA.");
        System.out.print("Ingrese una opción: ");
        int opc = va.validarRango(sc, 1, 5);
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
        int opc = va.validarRango(sc, 1, 5);
        System.out.println("-------------------------------------");
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
        int opc = va.validarRango(sc, 1, 5);
        System.out.println("-------------------------------------");
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
        int opc = va.validarRango(sc, 1, 5);
        System.out.println("-------------------------------------");
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
        nuevoCliente.setNombre(va.validarTexto(sc));
        System.out.print("Apellido: ");
        nuevoCliente.setApellido(va.validarTexto(sc));
        System.out.print("Teléfono: ");
        nuevoCliente.setTelefono(va.validarSoloNumeros(sc));
        System.out.print("Dirección: ");
        nuevoCliente.setDireccion(va.validarDireccion(sc));
        System.out.print("Licencia: ");
        nuevoCliente.setLicenciaConduccion(va.validarAlfanumerico(sc));
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
            cedula = va.validarAlfanumerico(sc);
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
        String cedula = va.validarAlfanumerico(sc);
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
            System.out.println("-------------------------------------");
            System.out.println(mostrar(cliente));
            System.out.println("Ingrese el campo a modificar: ");
            System.out.println("1. Nombre.");
            System.out.println("2. Apellido.");
            System.out.println("3. Teléfono.");
            System.out.println("4. Dirección.");
            System.out.println("5. Licencia.");
            System.out.println("6. Salir.");
            System.out.print("Ingrese una opción: ");
            int opc = va.validarRango(sc, 1, 6);

            switch (opc) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    cliente.setNombre(va.validarTexto(sc));
                    System.out.println("Nombre actualizado.");
                    break;
                case 2:
                    System.out.print("Nuevo apellido: ");
                    cliente.setApellido(va.validarTexto(sc));
                    System.out.println("Apellido actualizado.");
                    break;
                case 3:
                    System.out.print("Nuevo teléfono: ");
                    cliente.setTelefono(va.validarSoloNumeros(sc));
                    System.out.println("Teléfono actualizado.");
                    break;
                case 4:
                    System.out.print("Nueva dirección: ");
                    cliente.setDireccion(va.validarDireccion(sc));
                    System.out.println("Dirección actualizada.");
                    break;
                case 5:
                    System.out.print("Nueva licencia: ");
                    cliente.setLicenciaConduccion(va.validarAlfanumerico(sc));
                    System.out.println("Licencia actualizada.");
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Ingrese un valor correcto (1-6).");
                    break;
            }
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
            boolean confirmar = va.validarSiNo(sc);

            if (confirmar) {
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
            } else {
                System.out.println("Acción cancelada.");
                salir = true;
            }
        }
        return registroClientes;
    }

    public String consultarCliente(LinkedList<Cliente> registroClientes, Scanner sc) {
        System.out.println("Buscar cliente.");
        System.out.print("Cédula: ");
        String cedula = va.validarAlfanumerico(sc);
        Cliente cliente = buscarCliente(registroClientes, cedula);
        String texto;
        if (cliente == null) {
            texto = "Cliente no encontrado.";
        } else {
            texto = mostrar(cliente);
        }
        return texto;
    }

    public LinkedList<Vehiculo> registrarVehiculo(LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        Vehiculo nuevoVehiculo;
        System.out.println("Registrar vehículo.");
        System.out.print("Tipo vehículo (1. Sedan, 2. Camioneta SUV): ");
        int tipo = va.validarRango(sc, 1, 2);
        String placa = pedirPlacaNueva(registroVehiculos, sc);
        System.out.print("Marca: ");
        String marca = va.validarTexto(sc);
        System.out.print("Modelo (año 1990-2028): ");
        int modelo = va.validarRango(sc, 1990, 2028); // Acepta solo años razonables para un carro: entre 1990 y 2028
        System.out.print("Precio diario (sin puntos ni comas, ej. 119500): ");
        float precioDiario = va.validarDecimalPositivo(sc);
        String estado = "Disponible";

        if (tipo == 1) {
            String combustible = pedirCombustible(sc);
            String transmision = pedirTransmision(sc);
            nuevoVehiculo = new CarroSedan(placa, marca, modelo, precioDiario, estado, combustible, transmision);
        } else {
            String traccion = pedirTraccion(sc);
            System.out.print("Capacidad del maletero (litros): ");
            float capacidadMaletero = va.validarDecimalPositivo(sc);
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
            placa = va.validarAlfanumerico(sc);

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
        String placa = va.validarAlfanumerico(sc);
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
        String placa = va.validarAlfanumerico(sc);
        Vehiculo vehiculo = buscarPlaca(registroVehiculos, placa);

        if (vehiculo == null) {
            System.out.println("Vehículo no encontrado.");
            return registroVehiculos;
        }

        boolean esSedan = vehiculo instanceof CarroSedan;
        boolean esSUV = vehiculo instanceof CamionetaSUV;
        boolean salir = false;
        while (!salir) {
            System.out.println("-------------------------------------");
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
            int opc = va.validarRango(sc, 1, 6);

            switch (opc) {
                case 1:
                    System.out.print("Nueva marca: ");
                    vehiculo.setMarca(va.validarTexto(sc));
                    System.out.println("Marca actualizada.");
                    break;
                case 2:
                    System.out.print("Nuevo modelo (año 1990-2028): ");
                    vehiculo.setModelo(va.validarRango(sc, 1990, 2028));
                    System.out.println("Modelo actualizado.");
                    break;
                case 3:
                    // CAMBIO: no se permite cambiar el precio de un vehículo alquilado,
                    // para respetar la tarifa pactada en su contrato activo
                    if (vehiculo.getEstado().equalsIgnoreCase("Alquilado")) {
                        System.out.println("No se puede cambiar el precio de un vehículo alquilado.");
                    } else {
                        System.out.print("Nuevo precio diario (sin puntos ni comas, ej. 119500): ");
                        vehiculo.setPrecioDiario(va.validarDecimalPositivo(sc));
                        System.out.println("Precio diario actualizado.");
                    }
                    break;
                case 4:
                    if (esSedan) {
                        ((CarroSedan) vehiculo).setTipoCombustible(pedirCombustible(sc));
                        System.out.println("Tipo de combustible actualizado.");
                    } else if (esSUV) {
                        ((CamionetaSUV) vehiculo).setTraccion(pedirTraccion(sc));
                        System.out.println("Tracción actualizada.");
                    }
                    break;
                case 5:
                    if (esSedan) {
                        ((CarroSedan) vehiculo).setTransmision(pedirTransmision(sc));
                        System.out.println("Transmisión actualizada.");
                    } else if (esSUV) {
                        System.out.print("Nueva capacidad del maletero: ");
                        ((CamionetaSUV) vehiculo).setCapacidadMaletero(va.validarDecimalPositivo(sc));
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
        }
        return registroVehiculos;
    }

    public LinkedList<Vehiculo> eliminarVehiculo(LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        System.out.println("Eliminar vehículo.");
        System.out.print("Placa: ");
        String placa = va.validarAlfanumerico(sc);
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
            boolean confirmar = va.validarSiNo(sc);

            if (confirmar) {
                registroVehiculos.remove(vehiculo);
                System.out.println("Vehículo eliminado correctamente.");
                salir = true;
            } else {
                System.out.println("Acción cancelada.");
                salir = true;
            }
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
        String placa = va.validarAlfanumerico(sc);
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

    public LocalDate pedirFecha(Scanner sc) {
        System.out.print("Fecha de inicio (DD/MM/AAAA): ");
        return va.validarFecha(sc);
    }

    public int pedirDias(Scanner sc) {
        System.out.print("Número de días: ");
        return va.validarEnteroPositivo(sc);

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
        String idContrato = va.validarAlfanumerico(sc);
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
        String idContrato = va.validarAlfanumerico(sc);
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
            System.out.println("-------------------------------------");
            System.out.println(mostrar(contrato, contratosActivos));
            System.out.println("Ingrese el campo a modificar: ");
            System.out.println("1. Fecha de inicio.");
            System.out.println("2. Días de renting.");
            System.out.println("3. Salir.");
            System.out.print("Ingrese una opción: ");
            int opc = va.validarRango(sc, 1, 3);

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
        }
        return contratosActivos;
    }

    public Queue<ContratoRenting> finalizarContrato(Queue<ContratoRenting> contratosActivos,
            Stack<ContratoRenting> contratosFinalizados, LinkedList<Vehiculo> registroVehiculos, Scanner sc) {
        System.out.println("Finalizar contrato.");
        System.out.print("Id del contrato (ej. C1): ");
        String idContrato = va.validarAlfanumerico(sc);
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

    // Informe general: con todos los registros, los contratos agrupados y los
    // ingresos
    public String generarInforme(LinkedList<Cliente> registroClientes, LinkedList<Vehiculo> registroVehiculos,
            Queue<ContratoRenting> contratosActivos, Stack<ContratoRenting> contratosFinalizados) {
        String texto = "========== INFORME GENERAL ==========\n";

        texto += "\n----- CLIENTES -----\n";
        if (registroClientes.isEmpty()) {
            texto += "No hay clientes registrados.\n";
        } else {
            for (Cliente c : registroClientes) {
                texto += mostrar(c) + "\n-------------------------------------\n";
            }
        }

        texto += "\n----- VEHÍCULOS -----\n";
        if (registroVehiculos.isEmpty()) {
            texto += "No hay vehículos registrados.\n";
        } else {
            for (Vehiculo v : registroVehiculos) {
                texto += mostrar(v) + "\n-------------------------------------\n";
            }
        }

        float ingresosPorRecibir = 0;
        texto += "\n----- CONTRATOS ACTIVOS -----\n";
        if (contratosActivos.isEmpty()) {
            texto += "No hay contratos activos.\n";
        } else {
            for (ContratoRenting c : contratosActivos) {
                texto += mostrar(c, contratosActivos) + "\n-------------------------------------\n";
                ingresosPorRecibir += c.getValorTotal();
            }
        }

        float ingresosRecibidos = 0;
        texto += "\n----- CONTRATOS FINALIZADOS -----\n";
        if (contratosFinalizados.isEmpty()) {
            texto += "No hay contratos finalizados.\n";
        } else {
            for (ContratoRenting c : contratosFinalizados) {
                texto += mostrar(c, contratosActivos) + "\n-------------------------------------\n";
                ingresosRecibidos += c.getValorTotal();
            }
        }

        texto += "\n----- INGRESOS -----\n";
        texto += "Ingresos recibidos (contratos finalizados): " + ingresosRecibidos + "\n";
        texto += "Ingresos por recibir (contratos activos): " + ingresosPorRecibir + "\n";
        texto += "Total de ingresos por renting: " + (ingresosRecibidos + ingresosPorRecibir) + "\n";
        texto += "=====================================";
        return texto;
    }

    public String pedirTraccion(Scanner sc) {
        System.out.print("Tipo tracción (1. 4x2 / 2. 4x4): ");
        int opc = va.validarRango(sc, 1, 2);
        if (opc == 1) {
            return "4x2";
        }
        return "4x4";
    }

    public String pedirCombustible(Scanner sc) {
        System.out.print("Tipo de combustible (1. Gasolina / 2. Diésel / 3. Eléctrico): ");
        int opc = va.validarRango(sc, 1, 3);
        switch (opc) {
            case 1:
                return "Gasolina";
            case 2:
                return "Diésel";
            default:
                return "Eléctrico";
        }
    }

    public String pedirTransmision(Scanner sc) {
        System.out.print("Tipo de transmisión (1. Automática / 2. Manual): ");
        int opc = va.validarRango(sc, 1, 2);
        if (opc == 1) {
            return "Automática";
        }
        return "Manual";
    }

}
