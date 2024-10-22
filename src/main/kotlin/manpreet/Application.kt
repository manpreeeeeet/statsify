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


val clientId = System.getenv("SPOTIFY_CLIENT_ID") ?: throw Error("Missing ENV variable: SPOTIFY_CLIENT_ID")
val clientSecret = System.getenv("SPOTIFY_CLIENT_SECRET") ?: throw Error("Missing ENV variable: SPOTIFY_CLIENT_SECRET")
val redirectUri = System.getenv("REDIRECT_URI") ?: throw Error("Missing ENV variable: REDIRECT_URI")
val serverPort = System.getenv("SERVER_PORT")?.toInt() ?: throw Error("Missing ENV variable: SERVER_PORT")

fun main() {
    embeddedServer(Netty, port = serverPort, host = "0.0.0.0", module = Application::module)
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
