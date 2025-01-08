package vvchn.at.composempuisamples

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import vvchn.at.composempuisamples.navigation.root.AppNavigation
import vvchn.at.composempuisamples.navigation.root.RootComponent

@Composable
@Preview
fun App(appNavController: RootComponent) {
    /*MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }*/
    AppNavigation(appNavController)
}