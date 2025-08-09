package com.example.fakestoreappproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fakestoreappproject.data.model.CartItem
import com.example.fakestoreappproject.data.model.Product

@Dao
interface CartDao {
    @Insert
    suspend fun addCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cart_items")
    suspend fun getCartItems(): List<CartItem>

    @Query("DELETE FROM cart_items WHERE product_id = :productId")
    suspend fun deleteCartItem(productId: Int)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    @Query("SELECT * FROM cart_items WHERE product_id = :productId")
    suspend fun getCartItemByProductId(productId: Int): CartItem

    @Query("SELECT COUNT(*) FROM cart_items WHERE product_id = :productId")
    suspend fun isProductInCart(productId: Int): Int

    @Query("UPDATE cart_items SET quantity = :quantity WHERE product_id = :productId")
    suspend fun updateCartItemQuantity(productId: Int, quantity: Int)

    @Query("SELECT SUM(quantity) FROM cart_items")
    suspend fun getTotalItemsInCart(): Int

    @Query("SELECT SUM(product_price * quantity) FROM cart_items")
    suspend fun getTotalPriceInCart(): Double
}

