package manpreet

import SpotifyAccessToken
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.sessions.*
import kotlinx.serialization.Serializable
import manpreet.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}


@Serializable
data class UserSession(val spotifyAccessToken: SpotifyAccessToken)

fun Application.module() {
    configureRouting()
    install(Sessions) {
        cookie<UserSession>("user_session", SessionStorageMemory()) {
        }
    }
    install(ContentNegotiation) {
        json()
    }
}
