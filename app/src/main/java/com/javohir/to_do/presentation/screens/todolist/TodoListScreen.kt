package com.javohir.to_do.presentation.screens.todolist
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.javohir.to_do.presentation.screens.components.SwipeToDeleteContainer
import com.javohir.to_do.presentation.screens.components.TodoItemCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    modifier: Modifier = Modifier,
    navigateToAddTodo: () -> Unit,
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val todos by viewModel.todos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Vazifalar ro'yxati") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAddTodo
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Vazifa qo'shish")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                todos.isEmpty() -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Vazifalar yo'q",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "➕ tugmachani bosing va vazifa qo'shing",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = todos,
                            key = { it.id }
                        ) { todo ->
                            SwipeToDeleteContainer(
                                onDelete = { viewModel.deleteTodo(todo) }
                            ) {
                                TodoItemCard(
                                    todo = todo,
                                    onToggleComplete = { viewModel.toggleTodoCompletion(todo) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}