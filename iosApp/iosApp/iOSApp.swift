import SwiftUI

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate

    var body: some Scene {
        WindowGroup {
            ContentView(appNavComponent: appDelegate.appNavComponent)
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    let appNavComponent: RootComponent = RootComponent(
        componentContext: DefaultComponentContext(lifecycle: ApplicationLifecycle())
    )
}