package manpreet.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import manpreet.UserSession
import manpreet.clientId
import manpreet.redirectUri
import manpreet.spotify.spotifyCallback
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
fun Application.configureRouting() {
    routing {
        spotifyCallback()

        get("/api/login") {
            val queryString = listOf(
                "response_type" to "code",
                "client_id" to clientId,
                "scope" to "user-read-email user-top-read",
                "redirect_uri" to "$redirectUri/api/callback",
            ).joinToString("&") { (key, value) ->
                "${URLEncoder.encode(key, StandardCharsets.UTF_8)}=${URLEncoder.encode(value, StandardCharsets.UTF_8)}"
            }
            call.respondRedirect("https://accounts.spotify.com/authorize?$queryString")
        }

        // Static plugin. Try to access `/static/index.html`
        staticResources("/", "static")
    }

}
