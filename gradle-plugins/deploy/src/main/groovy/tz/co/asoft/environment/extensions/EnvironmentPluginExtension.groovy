package tz.co.asoft.environment.extensions

class EnvironmentPluginExtension  {
    Map<String, Map<String, Object>> environments = [:];

    def methodMissing(String name, Object args) {
        environments[name] = ["name": name]
        def cl = (Closure<Map<String, Object>>) args[0]
        cl.delegate = environments[name]
        cl()
    }
}