import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class Validaciones {

    private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    private String leerNoVacio(Scanner sc) {
        String texto = sc.nextLine().trim();
        if (texto.isEmpty()) {
            System.out.print("Este campo no puede quedar vacío, ingréselo de nuevo: ");
            return leerNoVacio(sc);
        }
        return texto;
    }

    public int validarEntero(Scanner sc) {
        try {
            return Integer.parseInt(leerNoVacio(sc));
        } catch (NumberFormatException e) {
            System.out.print("Debe ingresar un número entero: ");
            return validarEntero(sc);
        }
    }

    public int validarRango(Scanner sc, int minimo, int maximo) {
        int numero = validarEntero(sc);
        if (numero < minimo || numero > maximo) {
            System.out.print("Valor no válido, ingrese un número entre " + minimo + " y " + maximo + ": ");
            return validarRango(sc, minimo, maximo);
        }
        return numero;
    }

    public int validarEnteroPositivo(Scanner sc) {
        int numero = validarEntero(sc);
        if (numero <= 0) {
            System.out.print("El valor debe ser mayor que cero: ");
            return validarEnteroPositivo(sc);
        }
        return numero;
    }

    public float validarDecimalPositivo(Scanner sc) {
        float numero;
        try {
            numero = Float.parseFloat(leerNoVacio(sc));
        } catch (NumberFormatException e) {
            System.out.print("Debe ingresar un valor numérico (use punto para decimales): ");
            return validarDecimalPositivo(sc);
        }
        if (Float.isNaN(numero) || Float.isInfinite(numero) || numero <= 0) {
            System.out.print("El valor debe ser un número mayor que cero: ");
            return validarDecimalPositivo(sc);
        }
        return numero;
    }

    public String validarTexto(Scanner sc) {
        String texto = leerNoVacio(sc);
        if (!texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+")) {
            System.out.print("Solo se permiten letras, sin números ni caracteres especiales: ");
            return validarTexto(sc);
        }
        return texto;
    }

    public String validarAlfanumerico(Scanner sc) {
        String texto = leerNoVacio(sc);
        if (!texto.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ ]+")) {
            System.out.print("No se permiten caracteres especiales: ");
            return validarAlfanumerico(sc);
        }
        return texto;
    }

    public String validarSoloNumeros(Scanner sc) {
        String texto = leerNoVacio(sc);
        if (!texto.matches("[0-9]+")) {
            System.out.print("Solo se permiten números, sin espacios ni guiones: ");
            return validarSoloNumeros(sc);
        }
        return texto;
    }

    public String validarDireccion(Scanner sc) {
        String texto = leerNoVacio(sc);
        if (!texto.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ #.,/-]+")) {
            System.out.print("Dirección no válida, solo se permiten letras, números y los símbolos # - . , /: ");
            return validarDireccion(sc);
        }
        return texto;
    }

    public LocalDate validarFecha(Scanner sc) {
        String texto = leerNoVacio(sc);
        try {
            return LocalDate.parse(texto, formatoFecha);
        } catch (DateTimeParseException e) {
            System.out.print("Fecha no válida, use el formato DD/MM/AAAA (ejemplo 15/12/2026): ");
            return validarFecha(sc);
        }
    }

    public boolean validarSiNo(Scanner sc) {
        String texto = leerNoVacio(sc);
        if (texto.equalsIgnoreCase("si") || texto.equalsIgnoreCase("sí")) {
            return true;
        }
        if (texto.equalsIgnoreCase("no")) {
            return false;
        }
        System.out.print("Ingrese una opción correcta (Si/No): ");
        return validarSiNo(sc);
    }
}