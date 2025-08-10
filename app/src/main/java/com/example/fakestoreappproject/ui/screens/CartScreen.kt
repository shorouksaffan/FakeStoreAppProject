package com.example.fakestoreappproject.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.fakestoreappproject.data.model.CartItem
import com.example.fakestoreappproject.data.model.Category
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.ui.viewmodels.CartState
import com.example.fakestoreappproject.ui.viewmodels.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    viewModel: CartViewModel = koinViewModel()
) {
    val state by viewModel.cartState.collectAsStateWithLifecycle()
    CartScreenContent(state = state, onRemove = viewModel::deleteCartItem)
}

@Composable
private fun CartScreenContent(state: CartState, onRemove: (CartItem) -> Unit) {

    when (state) {
        is CartState.Error -> {
            // Handle error state, show a message or retry button
            Text(text = "Error loading cart: ${state.message}")
        }

        CartState.Loading -> {
            // Show a loading indicator while cart is being fetched
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CartState.Success -> {
            // Render the cart items
            CartScreenSuccessContent(
                cartItems = state.cartItems,
                onRemove = onRemove
            )
        }
    }
}

@Composable
fun CartScreenSuccessContent(cartItems: List<CartItem>, onRemove: (CartItem) -> Unit) {
    val total = cartItems.sumOf { it.product.price * it.quantity }

    Scaffold(
        containerColor = Color(0xFFE8EAF6),
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFFFF3E0),
                tonalElevation = 4.dp
            ) {
                Text(
                    text = "Total: $${"%.2f".format(total)}",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4A148C),
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = { /* Checkout, go to another screen */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B1FA2))
                ) {
                    Text(
                        "Checkout",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(cartItems) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        AsyncImage(
                            model = item.product.images.firstOrNull(),
                            contentDescription = item.product.title,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                item.product.title,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                color = Color(0xFF4A148C)
                            )
                            Text(
                                "$${item.product.price} x ${item.quantity}",
                                color = Color(0xFFFB8C00)
                            )
                        }
                        IconButton(onClick = { onRemove(item) }) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Remove",
                                tint = Color(0xFF7B1FA2)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CartScreenPreview() {
    val sampleProducts = listOf(
        Product(
            id = 1,
            title = "Smartphone",
            price = 599,
            description = "Latest 5G smartphone with amazing camera.",
            images = listOf("https://via.placeholder.com/150"),
            creationAt = "",
            slug = "",
            category = Category(
                id = 1,
                name = "Electronics",
                image = "",
                creationAt = "",
                updatedAt = "",
                slug = ""
            ),
            updatedAt = ""
        ),
        Product(
            id = 2,
            title = "Wireless Headphones",
            price = 199,
            description = "Noise-cancelling headphones with 30 hours battery.",
            images = listOf("https://via.placeholder.com/150"),
            creationAt = "",
            slug = "",
            category = Category(
                id = 2,
                name = "Audio",
                image = "",
                creationAt = "",
                updatedAt = "",
                slug = ""
            ),
            updatedAt = ""
        )
    )

    val sampleCartItems = listOf(
        CartItem(product = sampleProducts[0], quantity = 2),
        CartItem(product = sampleProducts[1], quantity = 1)
    )

    CartScreenSuccessContent(
        cartItems = sampleCartItems,
        onRemove = {}
    )
}

