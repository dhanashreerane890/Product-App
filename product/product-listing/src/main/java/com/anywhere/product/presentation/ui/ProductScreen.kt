package com.anywhere.product.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anywhere.design_system.component.AppCard
import com.anywhere.design_system.component.ErrorMessage
import com.anywhere.design_system.component.LoadingIndicator
import com.anywhere.product.presentation.model.ProductUi
import com.anywhere.product.presentation.model.ProductsUiState
import com.anywhere.product.presentation.viewmodel.ProductViewModel


@Composable
fun ProductsScreen(
    viewModel: ProductViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        when (uiState) {
            ProductsUiState.Loading -> {
                LoadingIndicator(
                    modifier = Modifier.fillMaxSize()
                )
            }

            is ProductsUiState.Error -> {
                val error = (uiState as ProductsUiState.Error).message
                ErrorMessage(
                    message = error,
                    onRetry = {

                    },
                    modifier = Modifier.padding(16.dp)
                )
            }

            is ProductsUiState.Success -> {
                val products = (uiState as ProductsUiState.Success).data
                ProductList(
                    products = products,
                    modifier = Modifier.fillMaxSize()
                )
            }

            is ProductsUiState.None -> {}
        }
    }
}

@Composable
private fun ProductList(
    products: List<ProductUi>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = products.size,
            key = {
                products[it].id
            }
        ) { productIndex ->

            val product = products[productIndex]
            ProductItem(product = product)
        }
    }
}

@Composable
private fun ProductItem(
    product: ProductUi,
    modifier: Modifier = Modifier
) {
    AppCard(modifier = modifier) {
        Row {

            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))


            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = product.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.price.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}