package vvchn.at.composempuisamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import vvchn.at.composempuisamples.navigation.RootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appNavComponent = RootComponent(defaultComponentContext())
        setContent {
            App(appNavComponent)
        }
    }
}