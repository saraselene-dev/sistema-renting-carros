# Sistema de Renting de Carros

Sistema de consola en Java para gestionar clientes, vehículos y contratos de renting (alquiler) de carros, aplicando programación orientada a objetos y estructuras de datos dinámicas (listas, colas y pilas).

Proyecto final de **Estructura de Datos y Laboratorio**, Instituto Tecnológico Metropolitano (ITM).
Docente: Dennis Arlex Garro Agudelo.

## Integrantes

- Juan David Torres Gómez
- Sara Selene Urrego Jiménez

## Cómo ejecutarlo

Requisitos: Java (JDK 8 o superior).

```
Abrir la carpeta en Visual Studio Code y ejecutar `Principal.java` con el botón **Run**.

## Estructura del proyecto

| Clase | Responsabilidad |
|---|---|
| `Principal` | Contiene únicamente el método `main`: crea las estructuras, mantiene el ciclo del menú y llama a los métodos de `Utilidades`. |
| `Cliente` | Datos del cliente: cédula, nombre, apellido, teléfono, dirección y licencia de conducción. |
| `Vehiculo` | Clase abstracta base: placa, marca, modelo, precio diario y estado (Disponible / Alquilado). |
| `CarroSedan` | Hereda de `Vehiculo` y agrega tipo de combustible y transmisión. |
| `CamionetaSUV` | Hereda de `Vehiculo` y agrega tracción y capacidad del maletero. |
| `ContratoRenting` | Contrato de alquiler: id, cédula del cliente, placa, fechas, total de días y valor total. |
| `Utilidades` | Lógica del sistema: menús, registro, modificación, eliminación, búsqueda, contratos e informe general. |
| `Validaciones` | Validaciones recursivas de todos los datos que ingresa el usuario. |

## Menú

```
1. GESTIÓN DE CLIENTES          (registrar, modificar, eliminar, buscar)
2. GESTIÓN DE VEHÍCULOS         (registrar, modificar, eliminar, buscar)
3. GESTIÓN DE CONTRATOS         (registrar, modificar, finalizar, buscar)
4. IMPRIMIR INFORME GENERAL
5. SALIR DEL SISTEMA
```

## Estructuras de datos

| Estructura | Tipo | Uso |
|---|---|---|
| `registroClientes` | `LinkedList<Cliente>` | Clientes registrados. |
| `registroVehiculos` | `LinkedList<Vehiculo>` | Sedanes y camionetas en una sola lista (polimorfismo). |
| `contratosActivos` | `Queue<ContratoRenting>` (cola) | Contratos en curso, en orden de registro. |
| `contratosFinalizados` | `Stack<ContratoRenting>` (pila) | Historial de contratos devueltos; el último devuelto queda arriba. |

Las estructuras crecen al registrar y se reducen al eliminar y se mantienen sincronizadas:

- Al registrar un contrato, el vehículo pasa a **Alquilado**.
- Al finalizar un contrato, pasa de la cola a la pila y el vehículo vuelve a **Disponible**.
- Al eliminar un cliente, se elimina su contrato activo y se libera el vehículo. Los contratos finalizados se conservan en el historial, porque representan ingresos ya recibidos.
- No se puede eliminar un vehículo que esté alquilado.

## Principios de la POO

- **Encapsulamiento:** todos los atributos son privados y se accede a ellos con getters y setters.
- **Herencia:** `CarroSedan` y `CamionetaSUV` heredan de `Vehiculo`.
- **Abstracción:** `Vehiculo` es una clase abstracta con el método abstracto `getTipo()`.
- **Polimorfismo:** cada subclase sobrescribe `getTipo()`, y una misma lista guarda los dos tipos de vehículo.
- **Sobrecarga:** los métodos `mostrar(Cliente)`, `mostrar(Vehiculo)` y `mostrar(ContratoRenting, Queue)` tienen el mismo nombre y Java escoge cuál usar según el tipo de parámetro.

## Reglas de negocio

- Un cliente solo puede tener un contrato activo a la vez.
- No se puede alquilar un vehículo en estado Alquilado.
- Un contrato solo se modifica en la fecha de inicio o en la duración; el valor total se recalcula como días × precio diario.
- El id del contrato, la cédula del cliente y la placa no se pueden modificar (atributos `final` sin setter).
- No se puede cambiar el precio diario de un vehículo alquilado, para respetar la tarifa pactada en su contrato.

## Validaciones

Todas las entradas se validan con métodos **recursivos** de la clase `Validaciones`: si el dato es incorrecto, el método muestra un mensaje y se llama a sí mismo hasta recibir un valor válido, sin cerrar el programa.

- Ningún campo puede quedar vacío.
- Números con `try-catch` (`NumberFormatException`) y fechas con `try-catch` (`DateTimeParseException`), en formato DD/MM/AAAA.
- Opciones de menú controladas por rango.
- Nombre, apellido y marca: solo letras. Cédula, placa y licencia: sin caracteres especiales. Teléfono: solo números. Dirección: letras, números y los símbolos `# - . , /`.
- Modelo: año entre 1990 y 2028. Precio diario, capacidad del maletero y días: mayores que cero.
- Tracción, combustible y transmisión se escogen de un menú, para guardar solo los valores permitidos.

## Informe general

La opción 4 muestra todos los clientes, vehículos, contratos activos y contratos finalizados, y calcula los ingresos recibidos (contratos finalizados), los ingresos por recibir (contratos activos) y el total de ingresos por renting.

## Historias de usuario y ramas

El proyecto se desarrolló siguiendo las historias de usuario en orden, con una rama por historia que se integró a `main` mediante pull request.

| Rama | Historia de usuario |
|---|---|
| `HU1` | Configuración inicial del proyecto |
| `HU2` | Creación de la clase Cliente |
| `HU3` | Creación de las clases de vehículos |
| `HU4` | Gestión de renting (clase ContratoRenting) |
| `HU5` | Creación del menú principal |
| `HU6` | Implementación de estructuras dinámicas |
| `HU6-correccion` | Ajustes a la eliminación de clientes y al precio de vehículos alquilados |
| `HU7` | Impresión de informes y consultas |
| `HU8` | Validaciones finales y control de flujo |
