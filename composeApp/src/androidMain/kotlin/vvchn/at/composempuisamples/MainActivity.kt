package vvchn.at.composempuisamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import vvchn.at.composempuisamples.navigation.root.provideRootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appNavController = provideRootComponent(defaultComponentContext())
        setContent {
            App(appNavController)
        }
    }
}