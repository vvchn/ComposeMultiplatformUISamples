package vvchn.at.composempuisamples.previews

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import vvchn.at.composempuisamples.screens.main.MainScreen
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.HostTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@CreatePreviews
@Composable
fun MainScreenPreview() {
    HostTheme {
        Scaffold(
            topBar = {
                HostTopBar(false) { }
            }
        ) {
            MainScreen { }
        }
    }
}