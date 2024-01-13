package com.tt.ecsrevision

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tt.ecsrevision.ui.WelcomeScreen
import com.tt.ecsrevision.ui.theme.ChooserScreen

enum class ECSRevisionScreen(@StringRes val title:Int) {
    Welcome(title = R.string.welcome),
    Chooser(title = R.string.chooser),
    Revision(title = R.string.revision),
    Test(title = R.string.test)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ECSRevisionAppBar(
    currentScreen: ECSRevisionScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    )
}


@Composable
fun ECSRevisionApp(
    navController: NavHostController = rememberNavController()
)
{
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = ECSRevisionScreen.valueOf(
        backStackEntry?.destination?.route ?: ECSRevisionScreen.Welcome.name
    )

    Scaffold(
        topBar = {
            ECSRevisionAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp()})
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = ECSRevisionScreen.Welcome.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = ECSRevisionScreen.Welcome.name){
                WelcomeScreen(
                    onClick = { navController.navigate(ECSRevisionScreen.Chooser.name) }
                )
            }

            composable(route = ECSRevisionScreen.Chooser.name){
                ChooserScreen(

                )
            }
        }
    }
}