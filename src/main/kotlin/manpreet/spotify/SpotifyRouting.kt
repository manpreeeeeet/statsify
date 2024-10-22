package manpreet.spotify

import SpotifyAccessToken
import SpotifyTopArtists
import SpotifyTopTracks
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import manpreet.UserSession
import manpreet.clientId
import manpreet.clientSecret
import manpreet.redirectUri
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.*


val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}

fun Route.spotifyCallback() {
    get("/api/callback") {
        val credentials = Base64.getEncoder().encodeToString("$clientId:$clientSecret".toByteArray())
        val code = call.request.queryParameters["code"] ?: ""
        val requestBody = listOf(
            "code" to code,
            "redirect_uri" to "$redirectUri/api/callback",
            "grant_type" to "authorization_code"
        ).joinToString("&") { (key, value) ->
            "${URLEncoder.encode(key, StandardCharsets.UTF_8)}=${URLEncoder.encode(value, StandardCharsets.UTF_8)}"
        }


        val response = client.post("https://accounts.spotify.com/api/token") {
            headers {
                append(HttpHeaders.Authorization, "Basic $credentials")
                append(HttpHeaders.ContentType, "application/x-www-form-urlencoded")
            }
            setBody(requestBody)
        }


        val accessToken: SpotifyAccessToken = response.body()
        call.sessions.set(UserSession(accessToken))
        call.respondRedirect(redirectUri)
    }

    get("/api/top-medium-term") {
        val userSession = call.sessions.get<UserSession>()
        if (userSession == null) {
            call.respond(HttpStatusCode.Forbidden)
            return@get
        }


        val accessToken = userSession.spotifyAccessToken
        val topTracks: SpotifyTopTracks = client.get("https://api.spotify.com/v1/me/top/tracks?time_range=medium_term&limit=5") {
            headers {
                append(HttpHeaders.Authorization, "Bearer ${accessToken.accessToken}")
            }
        }.body()

        val topArtists: SpotifyTopArtists =
            client.get("https://api.spotify.com/v1/me/top/artists?time_range=medium_term&limit=5") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${accessToken.accessToken}")
                }
            }.body()

        call.respond(SpotifyTop(topTracks, topArtists))
    }
}

@Serializable
data class SpotifyTop(
    val topTracks: SpotifyTopTracks,
    val topArtists: SpotifyTopArtists
)
