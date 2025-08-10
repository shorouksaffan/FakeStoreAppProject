package com.example.fakestoreappproject.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
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
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.fakestoreappproject.data.model.Category
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.ui.utils.Resource
import com.example.fakestoreappproject.ui.viewmodels.ProductListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    ProductListScreenContent(
        state = state,
        viewModel = viewModel
    )
}

@Composable
private fun ProductListScreenContent(
    state: Resource<Flow<PagingData<Product>>>,
    viewModel: ProductListViewModel
) {
    when (state) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error loading products")
            }
        }

        is Resource.Success -> {
            ProductListScreenSuccessContent(
                products = state.data,
                onProductClick = viewModel::onProductClick,
                onAddToCart = viewModel::onAddToCart,
                onCategoriesClick = viewModel::onCategoriesClick
            )
        }
    }
}

@Composable
fun ProductListScreenSuccessContent(
    products: Flow<PagingData<Product>>?,
    onProductClick: (Product) -> Unit = {},
    onAddToCart: (Product) -> Unit = {},
    onCategoriesClick: () -> Unit = {}
) {
    if (products == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No products available")
        }
        return
    }

    val lazyPagingItems = products.collectAsLazyPagingItems()
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
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(lazyPagingItems.itemCount) { index ->
                val product = lazyPagingItems[index]
                if (product != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onProductClick(product) }
                            .padding(horizontal = 12.dp),
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

    ProductListScreenSuccessContent(
        products = sampleProducts.asFlow().map { PagingData.from(listOf(it)) },
        onProductClick = {},
        onAddToCart = {},
        onCategoriesClick = {}
    )
}