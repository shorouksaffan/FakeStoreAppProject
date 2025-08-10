package com.example.fakestoreappproject.ui.screens
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.data.model.Category
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fakestoreappproject.ui.viewmodels.CategoryProductState
import com.example.fakestoreappproject.ui.viewmodels.CategoryProductViewModel
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
@Composable
fun CategoryProductsScreen(
    viewModel: CategoryProductViewModel = koinViewModel()
) {
    val state by viewModel.categoryProductState.collectAsStateWithLifecycle()
    CategoryProductsScreenContent(
        state = state,
        onProductClick = viewModel::onProductClick,
        onAddToCart = viewModel::onAddToCart
    )
}

@Composable
private fun CategoryProductsScreenContent(
    state: CategoryProductState,
    onProductClick: (Product) -> Unit,
    onAddToCart: (Product) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        when (state) {
            is CategoryProductState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is CategoryProductState.Error -> {
                LaunchedEffect(Unit) {
                    snackbarHostState.showSnackbar(
                        message = "Error loading products"
                    )
                }
            }

            is CategoryProductState.Success -> {
                CategoryProductsScreenSuccessContent(
                    categoryName = state.categoryName,
                    products = state.products,
                    onProductClick = onProductClick,
                    onAddToCart = onAddToCart
                )
            }
        }
    }
}

@Composable
private fun CategoryProductsScreenSuccessContent(
    categoryName: String,
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    onAddToCart: (Product) -> Unit
) {
    Scaffold(
        containerColor = Color(0xFFE8EAF6)
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            item {
                Text(
                    text = categoryName,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 8.dp),
                    textAlign = TextAlign.Center,
                    color = Color(0xFF4A148C)
                )
            }
            items(products) { product ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { onProductClick(product) },
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
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp)),
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
                                contentDescription = null,
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
fun CategoryProductsScreenPreview() {
    val sampleCategoryName = "Electronics"

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
                id = 1,
                name = "Electronics",
                image = "",
                creationAt = "",
                updatedAt = "",
                slug = ""
            ),
            updatedAt = ""
        )
    )

    CategoryProductsScreenSuccessContent(
        categoryName = sampleCategoryName,
        products = sampleProducts,
        onProductClick = {},
        onAddToCart = {}
    )
}
