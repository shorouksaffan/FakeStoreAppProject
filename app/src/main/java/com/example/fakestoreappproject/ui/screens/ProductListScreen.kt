package com.example.fakestoreappproject.ui.screens
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.data.model.Category

@Composable
fun ProductListScreen(
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    onAddToCart: (Product) -> Unit,
    onCategoriesClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCategoriesClick,
                containerColor = Color(0xFF7B1FA2)
            ) {
                Icon(Icons.Default.List, contentDescription = "Categories", tint = Color.White)
            }
        },
        containerColor = Color(0xFFE8EAF6)
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(products) { product ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { onProductClick(product) }.padding(horizontal = 12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = product.images.firstOrNull(),
                            contentDescription = product.title,
                            modifier = Modifier.size(80.dp).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                product.title,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                color = Color(0xFF4A148C)
                            )
                            Text(
                                "$${product.price}",
                                color = Color(0xFFFB8C00)
                            )
                        }
                        IconButton(onClick = { onAddToCart(product) }) {
                            Icon(
                                Icons.Default.AddShoppingCart,
                                contentDescription = "Add to Cart",
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
fun ProductListScreenPreview() {
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

    ProductListScreen(
        products = sampleProducts,
        onProductClick = {},
        onAddToCart = {},
        onCategoriesClick = {}
    )
}

