package vvchn.at.composempuisamples

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform