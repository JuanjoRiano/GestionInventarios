package model

// Modelo que representa un producto dentro del inventario
data class Product(
    val id: Int,         // Identificador único del producto
    var name: String,    // Nombre del producto
    var quantity: Int,   // Cantidad disponible en inventario
    var price: Double    // Precio unitario del producto
) {
    // Calcula el precio total del producto basado en la cantidad y el precio unitario
    fun totalPrice(): Double {
        return quantity * price
    }
}
