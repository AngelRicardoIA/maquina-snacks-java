package snack_machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main(String[] args) {
        maquinaSnacks();
    }

    public static void maquinaSnacks(){
        var salir = false;
        var consola = new Scanner(System.in);
        //Crear la lista de productos de tipo Snack
        List<Snack> productos = new ArrayList<>();
        System.out.println("============ Maquina De Snacks ============");
        Snacks.mostrarSnacks(); //Mostrar inventario snacks disponibles
        while (!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecturarOpciones(opcion, consola, productos);
            }catch (Exception e){
                System.out.println("Ocurrio un error: " + e);
            }finally {
                System.out.println("\n");
            }
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                Menu:
                1. Comprar Snack
                2. Mostrar Ticket
                3. Agregar nuevo Snack
                4. Salir
                
                Elige una opcion: \s""");
        //Leemos y retornamos la opcion
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecturarOpciones(int opcion, Scanner consola,
                                             List<Snack> productos){
        var salir = false;
        switch (opcion){
            case 1 -> comprarSnack(consola, productos);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(consola);
            case 4 -> {
                System.out.println("¡Hasta luego!");
                salir = true;
            }default -> System.out.println("Opcion invalida: " + opcion);
        }
        return salir;
    }

    private static void comprarSnack(Scanner consola,
                                     List<Snack> productos){
        System.out.print("¿Qué Snack quieres comprar (id)? ");
        var idSnack = Integer.parseInt(consola.nextLine());
        //Validar que exista en la lista
        var snackEncontrado = false;
        for(var snack: Snacks.getSnacks()){
            if(idSnack == snack.getIdSnack()){
                //Agregamos el snack a la lista
                productos.add(snack);
                System.out.println("OK, snack agregado correctamente: " + snack);
                snackEncontrado = true;
                break;
            }
        }
        if (!snackEncontrado){
            System.out.println("No se ha encontrado el ID: " + idSnack);
            System.out.println("Verifica tu opcion");
        }
    }

    private static void mostrarTicket(List<Snack> productos){
        var ticket = "*** TICKET DE VENTA ***";
        var total = 0.0;
        for(var producto: productos){
            ticket += "\n\t- " + producto.getNombre() + " - $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        ticket += "\n\tTotal -> $" + total;
        System.out.println(ticket);
    }

    private static void agregarSnack(Scanner consola){
        System.out.print("Nombre del snack: ");
        var nombre = consola.nextLine();
        System.out.print("Precio del snack: ");
        var precio = Double.parseDouble(consola.nextLine());
        Snacks.agregarSnacks(new Snack(nombre, precio));
        System.out.println("Se agrego correctamente");
        Snacks.mostrarSnacks();
    }
}
