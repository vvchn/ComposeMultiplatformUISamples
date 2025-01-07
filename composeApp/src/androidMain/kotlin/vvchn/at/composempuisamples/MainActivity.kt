package vvchn.at.composempuisamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import vvchn.at.composempuisamples.navigation.root.HostComponent
import vvchn.at.composempuisamples.navigation.root.HostComponentImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appNavController: HostComponent = HostComponentImpl(componentContext = defaultComponentContext())
        setContent {
            App(appNavController)
        }
    }
}