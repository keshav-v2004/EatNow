package com.example.eatnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.eatnow.db.Food
import com.example.eatnow.screens.AppViewModel
import com.example.eatnow.screens.ProdList
import com.example.eatnow.screens.StartScreen
import com.example.eatnow.ui.theme.EatNowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(owner = this)[AppViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            EatNowTheme {
                MasterApp(viewModel)
            }
        }
    }
}

