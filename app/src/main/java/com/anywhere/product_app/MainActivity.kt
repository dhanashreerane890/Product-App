package com.anywhere.product_app

import android.R.attr.fragment
import android.app.Fragment
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.View
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.anywhere.product.presentation.ui.ProductsScreen
import com.anywhere.product.presentation.viewmodel.ProductViewModel
import com.anywhere.product.presentation.viewmodel.ProductViewModelFactory
import com.anywhere.product_app.ui.theme.ProductAppTheme


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








