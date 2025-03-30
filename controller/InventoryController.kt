package controller

import model.Product

// Controlador que maneja la lógica de negocio del inventario
class InventoryController {
    // Lista mutable que almacena los productos en el inventario
    private val products = mutableListOf<Product>()

    // Agrega un producto a la lista de inventario
    fun addProduct(product: Product) {
        products.add(product)
        println("Producto agregado: $product")
    }

    // Retorna la lista de productos almacenados
    fun getProducts(): List<Product> = products

    // Verifica si un producto existe en el inventario comparando su ID
    fun productExists(id: Int): Boolean {
        return products.any { it.id == id }
    }

    // Busca un producto por su ID y actualiza sus atributos si existe
    fun updateProduct(id: Int, name: String, quantity: Int, price: Double) {
        val product = products.find { it.id == id }
        if (product != null) {
            product.name = name
            product.quantity = quantity
            product.price = price
            println("Producto actualizado: $product")
        } else {
            println("Producto no encontrado.")
        }
    }

    // Elimina un producto del inventario por su ID
    fun deleteProduct(id: Int) {
        if (products.removeIf { it.id == id }) {
            println("Producto eliminado.")
        } else {
            println("Producto no encontrado.")
        }
    }

    // Calcula el precio total de un producto con IVA (19%) si existe
    fun calculateTotalWithIVA(id: Int) {
        val product = products.find { it.id == id }
        if (product != null) {
            val total = product.quantity * product.price
            val iva = total * 0.19
            println("Total con IVA: ${total + iva}")
        } else {
            println("Producto no encontrado.")
        }
    }
}
