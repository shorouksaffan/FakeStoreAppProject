package com.example.fakestoreappproject.ui.screens
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.example.fakestoreappproject.data.model.Category
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
@Composable
fun CategoriesScreen(categories: List<Category>, onCategoryClick: (Category) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFE8EAF6)).padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "All Categories",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A148C),
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(5.dp)
        ) {
            items(categories) { category ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { onCategoryClick(category) },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        AsyncImage(
                            model = category.image,
                            contentDescription = category.name,
                            modifier = Modifier.size(80.dp).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            category.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4A148C)
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoriesScreenPreview() {
    val sampleCategories = listOf(
        Category(
            id = 1,
            name = "Electronics",
            image = "https://via.placeholder.com/150",
            creationAt = "",
            updatedAt = "",
            slug = ""
        ),
        Category(
            id = 2,
            name = "Books",
            image = "https://via.placeholder.com/150",
            creationAt = "",
            updatedAt = "",
            slug = ""
        ),
        Category(
            id = 3,
            name = "Clothing",
            image = "https://via.placeholder.com/150",
            creationAt = "",
            updatedAt = "",
            slug = ""
        )
    )

    CategoriesScreen(
        categories = sampleCategories,
        onCategoryClick = {}
    )
}