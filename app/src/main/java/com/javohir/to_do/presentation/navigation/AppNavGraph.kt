package com.javohir.to_do.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.javohir.to_do.presentation.screens.addtodo.AddTodoScreen
import com.javohir.to_do.presentation.screens.todolist.TodoListScreen

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.TODO_LIST
    ) {
        composable(Screens.TODO_LIST) {
            TodoListScreen(
                modifier = Modifier,
                navigateToAddTodo = {
                    navController.navigate(Screens.ADD_TODO) {
                        popUpTo(Screens.TODO_LIST) { inclusive = false }
                    }
                }
            )
        }

        composable(Screens.ADD_TODO) {
            AddTodoScreen(
                modifier = Modifier,
               onBack = {navController.popBackStack()}
            )
        }
    }
}