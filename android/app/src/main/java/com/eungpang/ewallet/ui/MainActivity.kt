package com.eungpang.ewallet.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.eungpang.ewallet.ui.account.Account
import com.eungpang.ewallet.ui.home.Home
import com.eungpang.ewallet.ui.qrcode.QrCode
import com.eungpang.ewallet.ui.theme.KWalletTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KWalletApp()
        }
    }
}

@Composable
fun KWalletApp() {
    val allScreens = KWalletScreen.values().toList()
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = KWalletScreen.fromRoute(
        backstackEntry.value?.destination?.route
    )

    KWalletTheme {
        Scaffold(
            bottomBar = {
                KWalletBottomTabRow(
                    screens = allScreens.filter { it.isMain },
                    onTabSelected = { selectedScreen ->
                        navController.navigate(selectedScreen.path)
                    },
                    currentScreen = currentScreen
                )
            }
        ) {
            KWalletNavHost(
                navController = navController,
            )
        }
    }
}

@Composable
fun KWalletBottomTabRow(
    screens: List<KWalletScreen>,
    onTabSelected: (KWalletScreen) -> Unit,
    currentScreen: KWalletScreen,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .selectableGroup()
    ) {
        screens.forEach { screen ->
            KWalletBottomTabItem(
                screen = screen,
                selected = currentScreen == screen,
                onSelected = { onTabSelected(screen) }
            )
        }
    }
}

@Composable
fun RowScope.KWalletBottomTabItem(
    screen: KWalletScreen,
    selected: Boolean,
    onSelected: () -> Unit
) {
    Column(
        modifier = Modifier
            .animateContentSize()
            .weight(1f)
            .align(Alignment.CenterVertically)
            .height(40.dp)
            .padding(top = 5.dp)
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
            )
            .clearAndSetSemantics { contentDescription = screen.name },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier,
            imageVector = screen.icon,
            contentDescription = screen.name,
            tint = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
        )
        if (selected) {
            Spacer(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .width(30.dp)
                    .height(1.dp)
                    .background(MaterialTheme.colors.primary)
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun KWalletNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = KWalletScreen.Home.path,
        modifier = modifier
            .fillMaxWidth()
    ) {
        composable(
            KWalletScreen.Home.path
        ) {
            Home()
        }

        composable(
            KWalletScreen.QrCode.path
        ) {
            QrCode()
        }

        composable(
            KWalletScreen.Account.path
        ) {
            Account()
        }
    }
}

@Preview(name = "KWalletApp")
@Composable
fun KWalletAppPreview() {
    KWalletApp()
}