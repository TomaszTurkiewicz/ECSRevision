package com.tt.ecsrevision

import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tt.ecsrevision.ui.screens.ChooserScreen
import com.tt.ecsrevision.ui.screens.RevisionScreen
import com.tt.ecsrevision.ui.screens.TestScreen
import com.tt.ecsrevision.ui.screens.WelcomeScreen
import com.tt.ecsrevision.ui.theme.myColors
import com.tt.ecsrevision.viewmodels.AppViewModel

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
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Text(stringResource(currentScreen.title),
            style = MaterialTheme.typography.labelLarge,
            modifier = modifier.fillMaxWidth()
                .padding(end = 10.dp),
                textAlign = TextAlign.Center
            )
                },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.myColors.primaryLight,
            titleContentColor = MaterialTheme.myColors.primaryDark
        ),
        modifier = modifier
            .fillMaxWidth()

    )
}


@Composable
fun ECSRevisionApp(
    viewModel: AppViewModel,
    navController: NavHostController = rememberNavController(),
    activity: MainActivity
)
{
    val context = LocalContext.current
    val link = context.getString(R.string.other_games_link)
    val intent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse(link))
    }

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = ECSRevisionScreen.valueOf(
        backStackEntry?.destination?.route ?: ECSRevisionScreen.Welcome.name
    )

    viewModel.getNumberFromSharedPreferences(context)
    viewModel.getOneAnswerBooleanFromSharedPreferences(context)



    Scaffold(
        topBar = {
            ECSRevisionAppBar(
                currentScreen = currentScreen)
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = ECSRevisionScreen.Welcome.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = ECSRevisionScreen.Welcome.name){
                WelcomeScreen(
                    databaseNumber = uiState.databaseVersion,
                    viewModel = viewModel,
                    databaseReady = uiState.revisionQuestionsReady && (uiState.testTimeReady && (uiState.testReady && (uiState.passMarkReady)))
                ){
                    navController.navigate(ECSRevisionScreen.Chooser.name)
                }
            }

            composable(route = ECSRevisionScreen.Chooser.name){
                ChooserScreen(
                    viewModel = viewModel,
                    moveToRevision = {
                        navController.navigate(ECSRevisionScreen.Revision.name)
                    },
                    moveToTest = {
                        navController.navigate(ECSRevisionScreen.Test.name)
                    }

                )
                {
                    context.startActivity(intent)
                }
            }

            composable(route = ECSRevisionScreen.Revision.name){
                RevisionScreen(
                    activity = activity,
                    viewModel = viewModel,
                    question = uiState.question,
                    oneAnswer = uiState.oneAnswer
                )
            }

            composable(route = ECSRevisionScreen.Test.name){
                TestScreen(
                    viewModel = viewModel,
                    navController = navController,
                    testState = uiState.testState,
                    activity = activity,
                    rewardedAdLoaded = uiState.rewardedApLoaded,
                    rewardedAdWatched = uiState.rewardedAdWatched,
                    testListReady = uiState.testListReady,
                    testQuestion = uiState.testQuestion,
                    selectedAnswer = uiState.testQuestion.userAnswer
                )
            }
        }
    }
}