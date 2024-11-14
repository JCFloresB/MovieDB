package com.juan.carlos.flores.moviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.juan.carlos.flores.moviedb.presentation.custom.TopBar
import com.juan.carlos.flores.moviedb.presentation.login.AuthState
import com.juan.carlos.flores.moviedb.presentation.login.LoginViewModel
import com.juan.carlos.flores.moviedb.presentation.navigation.AppNavigation
import com.juan.carlos.flores.moviedb.presentation.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val snackbarHostState = remember { SnackbarHostState() }

            // Agregamos el AuthViewModel
            val authViewModel: LoginViewModel = hiltViewModel()
            val authState by authViewModel.authState.collectAsState()

            // Observar la ruta actual
            val currentRoute by navController.currentBackStackEntryAsState()
            Timber.d("Current route: ${currentRoute?.destination?.route}")

            LaunchedEffect(authState) {
                when(authState) {
                    AuthState.Authenticated -> {
                        Timber.i("Se ha logeado")
                    }
                    AuthState.Unauthenticated -> {
                        Timber.i("Presiono logout...")

                    }
                    AuthState.Loading -> {
                        Timber.i("Loding...")
                    }
                }
            }

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        // Modificamos la condición para mostrar la TopBar
                        when (currentRoute?.destination?.route) {
                            "login" -> { /* No mostrar TopBar */ }
                            null -> { /* No mostrar TopBar */ }
                            else -> {
                                TopBar(
                                    navController = navController,
                                    onLogoutClick = {
                                        Timber.d("Logout triggered")
                                        authViewModel.logout()
                                        navController.navigate(Screen.Login.route) {
                                            popUpTo(0) { inclusive = true }
                                        }
                                    }
                                )
                            }
                        }
                    },
                    snackbarHost = {
                        SnackbarHost(snackbarHostState)
                    }
                ) { padding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        AppNavigation(
                            navController = navController,
                            snackbarHostState = snackbarHostState
                        )
                    }
                }
            }
        }
    }
}