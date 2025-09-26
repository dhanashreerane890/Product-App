package com.anywhere.product_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.lifecycle.ViewModelProvider
import com.anywhere.product.presentation.ui.ProductsScreen
import com.anywhere.product.presentation.viewmodel.ProductViewModel
import com.anywhere.product.presentation.viewmodel.ProductViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val productViewModel: ProductViewModel by lazy {
            ViewModelProvider(this, ProductViewModelFactory())[ProductViewModel::class.java]
        }
        setContent {
            enableEdgeToEdge()
            ProductsScreen(productViewModel)
        }
    }
}








