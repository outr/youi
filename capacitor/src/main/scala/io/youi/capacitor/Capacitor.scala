package io.youi.capacitor

import scala.scalajs.js
import scala.scalajs.js.Promise
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("Capacitor")
object Capacitor extends js.Object {
  def isNative: Boolean = js.native
  def Plugins: Plugins = js.native

  trait Plugins extends js.Object {
    def Accessibility: Accessibility
    def App: App
    def Browser: Browser
    def Filesystem: Filesystem
    def Keyboard: Keyboard
    def Network: Network
    def PushNotifications: PushNotifications
    def SplashScreen: SplashScreen
    def StatusBar: StatusBar
    def Toast: Toast
  }

  trait Accessibility extends js.Object {
    def isScreenReaderEnabled(): Promise[ScreenReaderEnabledResult]
    def removeAllListeners(): Unit
    def speak(options: AccessibilitySpeakOptions): Promise[Unit]
    def addListener(eventName: String, listenerFunc: () => Unit): PluginListenerHandle
  }

  trait ScreenReaderEnabledResult extends js.Object {
    def value: Boolean
  }

  trait AccessibilitySpeakOptions extends js.Object {
    var language: js.UndefOr[String] = js.undefined
    var value: js.UndefOr[String] = js.undefined
  }

  trait App extends js.Object {
    def canOpenUrl(options: UrlOptions): Promise[CanOpenResult]
    def exitApp(): Unit
    def getLaunchUrl(): Promise[AppLaunchUrl]
    def getState(): Promise[AppState]
    def openUrl(options: UrlOptions): Promise[Opened]
    def removeAllListeners(): Unit
    def addListener(eventName: String, listenerFunc: js.Function1[js.Any, Unit]): PluginListenerHandle
  }

  trait Browser extends js.Object {
    def close(): Promise[Unit]
    def open(options: BrowserOpenOptions): Promise[Unit]
    def prefetch(options: BrowserOpenOptions): Promise[Unit]
    def removeAllListeners(): Unit
    def addListener(eventName: String, listenerFunc: js.Function1[js.Any, Unit]): PluginListenerHandle
  }

  trait BrowserOpenOptions extends js.Object {
    var presentationStyle: js.UndefOr[String] = js.undefined
    var toolbarColor: js.UndefOr[String] = js.undefined
    var url: js.UndefOr[String] = js.undefined
    var windowName: js.UndefOr[String] = js.undefined
  }

  trait UrlOptions extends js.Object {
    var url: js.UndefOr[String] = js.undefined
  }

  trait CanOpenResult extends js.Object {
    def value: Boolean
  }

  trait Opened extends js.Object {
    def completed: Boolean
  }

  trait AppLaunchUrl extends js.Object {
    def url: String
  }

  trait AppState extends js.Object {
    def isActive: Boolean
  }

  trait Filesystem extends js.Object {
    def appendFile(options: FileAppendOptions): Promise[FileAppendResult]
    def copy(options: CopyOptions): Promise[CopyResult]
    def deleteFile(options: FileDeleteOptions): Promise[FileDeleteResult]
    def getUri(options: GetUriOptions): Promise[GetUriResult]
    def mkdir(options: MkdirOptions): Promise[MkdirResult]
    def readFile(options: FileReadOptions): Promise[FileReadResult]
    def readdir(options: ReaddirOptions): Promise[ReaddirResult]
    def rename(options: RenameOptions): Promise[RenameResult]
    def rmdir(options: RmdirOptions): Promise[RmdirResult]
    def stat(options: StatOptions): Promise[StatResult]
    def writeFile(options: FileWriteOptions): Promise[FileWriteResult]
    def addListener(eventName: String, listenerFunc: js.Function1[js.Any, Unit]): PluginListenerHandle
  }

  trait FileAppendOptions extends js.Object {
    var data: js.UndefOr[String] = js.undefined
    var directory: js.UndefOr[String] = js.undefined
    var encoding: js.UndefOr[String] = js.undefined
    var path: js.UndefOr[String] = js.undefined
  }

  trait FileAppendResult extends js.Object {
  }

  trait CopyOptions extends js.Object {
    var directory: js.UndefOr[String] = js.undefined
    var from: js.UndefOr[String] = js.undefined
    var to: js.UndefOr[String] = js.undefined
    var toDirectory: js.UndefOr[String] = js.undefined
  }

  trait CopyResult extends js.Object {
  }

  trait FileDeleteOptions extends js.Object {
    var directory: js.UndefOr[String] = js.undefined
    var path: js.UndefOr[String] = js.undefined
  }

  trait FileDeleteResult extends js.Object {
  }

  trait GetUriOptions extends js.Object {
    var directory: js.UndefOr[String] = js.undefined
    var path: js.UndefOr[String] = js.undefined
  }

  trait GetUriResult extends js.Object {
    def uri: String
  }

  trait MkdirOptions extends js.Object {
    var directory: js.UndefOr[String] = js.undefined
    var path: js.UndefOr[String] = js.undefined
    var recursive: js.UndefOr[Boolean] = js.undefined
  }

  trait MkdirResult extends js.Object {
  }

  trait FileReadOptions extends js.Object {
    var directory: js.UndefOr[String] = js.undefined
    var encoding: js.UndefOr[String] = js.undefined
    var path: js.UndefOr[String] = js.undefined
  }

  trait FileReadResult extends js.Object {
    def data: String
  }

  trait ReaddirOptions extends js.Object {
    var directory: js.UndefOr[String] = js.undefined
    var path: js.UndefOr[String] = js.undefined
  }

  trait ReaddirResult extends js.Object {
    def files: js.Array[String]
  }

  trait RenameOptions extends js.Object {
    var directory: js.UndefOr[String] = js.undefined
    var from: js.UndefOr[String] = js.undefined
    var to: js.UndefOr[String] = js.undefined
    var toDirectory: js.UndefOr[String] = js.undefined
  }

  trait RenameResult extends js.Object {
  }

  trait RmdirOptions extends js.Object {
    var directory: js.UndefOr[String] = js.undefined
    var path: js.UndefOr[String] = js.undefined
    var recursive: js.UndefOr[Boolean] = js.undefined
  }

  trait RmdirResult extends js.Object {
  }

  trait StatOptions extends js.Object {
    var directory: js.UndefOr[String] = js.undefined
    var path: js.UndefOr[String] = js.undefined
  }

  trait StatResult extends js.Object {
    def ctime: Double
    def mtime: Double
    def size: Double
    def `type`: Double
    def uri: String
  }

  trait FileWriteOptions extends js.Object {
    var data: js.UndefOr[String] = js.undefined
    var directory: js.UndefOr[String] = js.undefined
    var encoding: js.UndefOr[String] = js.undefined
    var path: js.UndefOr[String] = js.undefined
    var recursive: js.UndefOr[Boolean] = js.undefined
  }

  trait FileWriteResult extends js.Object {
    def uri: String
  }

  trait Keyboard extends js.Object {
    def hide(): Promise[Unit]
    def removeAllListeners(): Unit
    def setAccessoryBarVisible(options: AccessoryBarOptions): Promise[Unit]
    def setResizeMode(options: KeyboardResizeOptions): Promise[Unit]
    def setScroll(options: KeyboardScrollOptions): Promise[Unit]
    def setStyle(options: KeyboardStyleOptions): Promise[Unit]
    def show(): Promise[Unit]
    def addListener(eventName: String, listenerFunc: js.Function1[js.Any, Unit]): PluginListenerHandle
  }

  trait AccessoryBarOptions extends js.Object {
    var isVisible: js.UndefOr[Boolean] = js.undefined
  }
  trait KeyboardResizeOptions extends js.Object
  trait KeyboardScrollOptions extends js.Object
  trait KeyboardStyleOptions extends js.Object
  trait KeyboardInfo extends js.Object {
    def keyboardHeight: Double
  }

  trait Network extends js.Object {
    def getStatus(): Promise[NetworkStatus]
    def removeAllListeners(): Unit
    def addListener(eventName: String, listenerFunc: js.Function1[NetworkStatus, Unit]): PluginListenerHandle
  }

  trait NetworkStatus extends js.Object {
    def connected: Boolean
    def connectionType: js.Any
  }

  trait PushNotifications extends js.Object {
    def createChannel(channel: NotificationChannel): Promise[Unit]
    def deleteChannel(channel: NotificationChannel): Promise[Unit]
    def getDeliveredNotifications(): Promise[PushNotificationDeliveredList]
    def listChannels(): Promise[NotificationChannelList]
    def register(): Promise[Unit]
    def removeAllDeliveredNotifications(): Promise[Unit]
    def removeAllListeners(): Unit
    def removeDeliveredNotifications(delivered: PushNotificationDeliveredList): Promise[Unit]
    def requestPermission(): Promise[Unit]
    def addListener(eventName: String, listenerFunc: js.Function1[js.Any, Unit]): PluginListenerHandle
  }

  trait SplashScreen extends js.Object {
    def show(): Unit
    def hide(): Unit
  }

  trait StatusBar extends js.Object {
    def getInfo(): Promise[StatusBarInfoResult]
    def hide(options: js.UndefOr[StatusBarAnimationOptions] = js.undefined): Promise[Unit]
    def setBackgroundColor(options: js.UndefOr[StatusBarBackgroundColorOptions] = js.undefined): Promise[Unit]
    def setOverlaysWebView(options: js.UndefOr[StatusBarOverlaysWebviewOptions] = js.undefined): Promise[Unit]
    def setStyle(options: StatusBarStyleOptions): Promise[Unit]
    def show(options: js.UndefOr[StatusBarAnimationOptions] = js.undefined): Promise[Unit]
  }

  trait NotificationChannel extends js.Object

  trait NotificationChannelList extends js.Object

  trait PushNotificationToken extends js.Object {
    def value: String
  }

  trait PushNotificationDeliveredList extends js.Object

  trait PluginListenerHandle extends js.Object

  trait StatusBarInfoResult extends js.Object {
    def color: String
    def overlays: Boolean
    def style: StatusBarStyle
    def visible: Boolean
  }

  trait StatusBarStyle extends js.Object

  trait StatusBarAnimationOptions extends js.Object {
    var animation: js.UndefOr[StatusBarAnimation] = js.undefined
  }

  trait StatusBarAnimation extends js.Object

  trait StatusBarBackgroundColorOptions extends js.Object

  trait StatusBarOverlaysWebviewOptions extends js.Object

  trait StatusBarStyleOptions extends js.Object

  trait Toast extends js.Object {
    def show(options: ToastShowOptions): Promise[Unit]
    def addListener(eventName: String, listenerFunc: js.Function): PluginListenerHandle
  }

  trait ToastShowOptions extends js.Object {
    var duration: js.UndefOr[String] = js.undefined
    var position: js.UndefOr[String] = js.undefined
    var text: js.UndefOr[String] = js.undefined
  }
}