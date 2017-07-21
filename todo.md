Cleanup TODO
----------------
[X] Standardize Scala.js generation via SBT plugin
    - output "application.js" to "jvm/src/main/resources/app/" path
    - convenience functionality to inject Scala.js file into a handler
[ ] Add better start / restart functionality to YouIPlugin to allow removal of sbt-revolver
[X] Communication.connection should have a default implementation to avoid expecting it as an implementation
[ ] Remove the necessity of SinglePageApplication by making optional configuration in the application
[ ] Provide a mechanism to need only extend ExampleApplication and Client/Server Application
[ ] Remove necessary `cached` method implementation in ClientApplication
[ ] Remove "main" method requirement entirely and use Profig as launching point
[ ] Provide support in Screen to leverage io.youi.http for path matching functionality (URLMatcher)