package view

import controller.InventoryController
import model.Product

// Vista encargada de interactuar con el usuario, mostrando opciones y capturando entradas
class InventoryView {
    private val controller = InventoryController() // Instancia del controlador para manejar la lógica de negocio

    // Muestra el menú principal del sistema de inventario
    fun showMenu() {
        while (true) {
            println("\n GESTIÓN DE INVENTARIO")
            println("1. Agregar producto")
            println("2. Ver productos")
            println("3. Actualizar producto")
            println("4. Eliminar producto")
            println("5. Calcular total con IVA")
            println("6. Salir")

            val option = readPositiveInt("Seleccione una opción")

            when (option) {
                1 -> addProduct()
                2 -> listProducts()
                3 -> updateProduct()
                4 -> deleteProduct()
                5 -> calculateTotalWithIVA()
                6 -> {
                    println("¡Hasta luego!")
                    break
                }
                else -> println("Opción inválida. Intente de nuevo.")
            }
        }
    }

    // Solicita datos al usuario y agrega un nuevo producto al inventario
    private fun addProduct() {
        val id = readPositiveInt("Ingrese el ID del producto")
        val name = readNonEmptyString("Ingrese el nombre del producto")
        val quantity = readPositiveInt("Ingrese la cantidad")
        val price = readPositiveDouble("Ingrese el precio")

        controller.addProduct(Product(id, name, quantity, price))
    }

    // Muestra la lista de productos almacenados en el inventario
    private fun listProducts() {
        val products = controller.getProducts()
        if (products.isEmpty()) {
            println("No hay productos en el inventario.")
        } else {
            println("\n Listado de productos:")
            products.forEach { println(it) }
        }
    }

    // Solicita datos al usuario y actualiza un producto existente en el inventario
    private fun updateProduct() {
        val id = readExistingProductId() ?: return
        val name = readNonEmptyString("Nuevo nombre del producto")
        val quantity = readPositiveInt("Nueva cantidad")
        val price = readPositiveDouble("Nuevo precio")

        controller.updateProduct(id, name, quantity, price)
    }

    // Solicita un ID y elimina el producto correspondiente del inventario
    private fun deleteProduct() {
        val id = readExistingProductId() ?: return
        controller.deleteProduct(id)
    }

    // Solicita un ID y calcula el precio total con IVA del producto
    private fun calculateTotalWithIVA() {
        val id = readExistingProductId() ?: return
        controller.calculateTotalWithIVA(id)
    }

    // Metodo para leer un número entero positivo con validación
    private fun readPositiveInt(prompt: String): Int {
        while (true) {
            print("$prompt: ")
            val input = readlnOrNull()

            val number = input?.toIntOrNull()
            if (number == null) {
                println("Entrada inválida. Ingrese un número válido.")
                continue
            }

            if (number <= 0) {
                println("Error: el valor debe ser mayor a 0.")
                continue
            }

            return number
        }
    }

    // Metodo para leer un número decimal positivo con validación
    private fun readPositiveDouble(prompt: String): Double {
        while (true) {
            print("$prompt: ")
            val input = readlnOrNull()

            val number = input?.toDoubleOrNull()
            if (number == null) {
                println("Entrada inválida. Ingrese un número válido.")
                continue
            }

            if (number <= 0.0) {
                println("Error: el valor debe ser mayor a 0.")
                continue
            }

            return number
        }
    }

    // Metodo para leer una cadena de texto no vacía
    private fun readNonEmptyString(prompt: String): String {
        while (true) {
            print("$prompt: ")
            val input = readlnOrNull()?.trim()
            if (!input.isNullOrEmpty()) {
                return input
            }
            println("Error: el campo no puede estar vacío.")
        }
    }

    // Metodo que verifica si un producto existe antes de permitir continuar con la acción
    private fun readExistingProductId(): Int? {
        while (true) {
            val id = readPositiveInt("ID del producto")
            if (controller.productExists(id)) {
                return id
            }
            println("El producto con ID $id no existe. Intente nuevamente.")
        }
    }
}
