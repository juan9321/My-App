package com.example.myapp.navView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.roomDB.Pessoa
import com.example.myapp.viewModel.PessoaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadScreen(navController: NavController, viewModel: PessoaViewModel) {
    val pessoas by viewModel.getPessoa().observeAsState(emptyList())

    val customColor = Color(0xFF9C27B0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Pessoas") },
                actions = {
                    IconButton(onClick = { navController.navigate("create") }) {
                        Icon(Icons.Default.Add, contentDescription = "Adicionar")
                    }
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .background(customColor)
                    .padding(16.dp)
            ) {
                items(pessoas) { pessoa ->
                    PessoaItem(pessoa = pessoa)
                    HorizontalDivider()
                }
            }
        }
    )
}

@Composable
fun PessoaItem(pessoa: Pessoa) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = pessoa.nome,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = pessoa.telefone,
                fontSize = 16.sp
            )
        }
    }
}
