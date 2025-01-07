import UIKit

class AppDelegate: NSObject, UIApplicationDelegate {
    let root: HostComponent = DefaultRootComponent(
        componentContext: DefaultComponentContext(lifecycle: ApplicationLifecycle())
    )
}