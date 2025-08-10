package com.example.fakestoreappproject.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.fakestoreappproject.data.model.Category
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.ui.viewmodels.ProductDetailViewModel
import com.example.fakestoreappproject.ui.viewmodels.ProductDetailsState
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProductDetailScreenContent(
        state = state,
        onAddToCart = viewModel::onAddToCart
    )
}

@Composable
private fun ProductDetailScreenContent(
    state: ProductDetailsState,
    onAddToCart: (Product) -> Unit
) {
    when (state) {
        is ProductDetailsState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ProductDetailsState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error loading product details")
            }
        }

        is ProductDetailsState.Success -> {
            ProductDetailScreenSuccessContent(
                product = state.product,
                onAddToCart = onAddToCart
            )
        }
    }
}

@Composable
fun ProductDetailScreenSuccessContent(
    product: Product,
    onAddToCart: (Product) -> Unit
) {
    Scaffold(
        containerColor = Color(0xFFE8EAF6)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = product.images.firstOrNull(),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = product.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color(0xFF4A148C)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$${product.price}",
                fontSize = 20.sp,
                color = Color(0xFFFB8C00),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = product.description,
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF4A148C)
            )
            Spacer(modifier = Modifier.height(100.dp))
            Button(
                onClick = { onAddToCart(product) },
                modifier = Modifier.fillMaxWidth(0.6f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF3E0))
            ) {
                Icon(
                    Icons.Default.AddShoppingCart,
                    contentDescription = null,
                    tint = Color(0xFF7B1FA2)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    "Add to Cart",
                    color = Color(0xFF7B1FA2),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductDetailScreenPreview() {
    val sampleProduct = Product(
        id = 101,
        title = "Wireless Bluetooth Headphones",
        price = 199,
        description = "Experience crystal-clear sound with these noise-cancelling Bluetooth headphones. " +
                "Up to 30 hours of battery life and comfortable ear cups for all-day use.",
        images = listOf("https://via.placeholder.com/400"),
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

    ProductDetailScreenSuccessContent(
        product = sampleProduct,
        onAddToCart = {}
    )
}

