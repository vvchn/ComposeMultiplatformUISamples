package vvchn.at.composempuisamples.navigation.root

import com.arkivanov.decompose.ComponentContext

fun provideRootComponent(componentContext: ComponentContext): RootComponent = RootComponentImpl(componentContext)