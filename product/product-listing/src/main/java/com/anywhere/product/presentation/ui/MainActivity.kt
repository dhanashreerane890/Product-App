package com.anywhere.product.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import com.anywhere.product.presentation.viewmodel.ProductViewModel
import com.anywhere.product.presentation.viewmodel.ProductViewModelFactory

class MainActivity : ComponentActivity() {

    private val productViewModel: ProductViewModel by lazy {
        ViewModelProvider(this, ProductViewModelFactory())[ProductViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            ComposeView(this).apply {
                setContent {
                    ProductsScreen(productViewModel)
                }
            }
        )
    }
}