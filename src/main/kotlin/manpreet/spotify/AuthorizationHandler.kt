package manpreet.spotify

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
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import manpreet.UserSession
import manpreet.plugins.redirectUri
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.*


val clientId = "64bc2660bd464860a2dc36e3ac454383"
val clientSecret = System.getenv("SPOTIFY_CLIENT_SECRET") ?: throw Error("missing env property client secret")
@Serializable
data class SpotifyAccessToken(
    @SerialName("access_token") val accessToken: String,
    @SerialName("token_type") val tokenType: String,
    val scope: String,
    @SerialName("expires_in") val expiresIn: Int,
    @SerialName("refresh_token") val refreshToken: String
)

fun Route.spotifyCallback() {
    get("/callback") {
        val credentials = Base64.getEncoder().encodeToString("$clientId:$clientSecret".toByteArray())
        val code = call.request.queryParameters["code"] ?: ""
        val requestBody = listOf(
            "code" to code,
            "redirect_uri" to redirectUri,
            "grant_type" to "authorization_code"
        ).joinToString("&") { (key, value) ->
            "${URLEncoder.encode(key, StandardCharsets.UTF_8)}=${URLEncoder.encode(value, StandardCharsets.UTF_8)}"
        }

        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
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
        val topTracks = client.get("https://api.spotify.com/v1/me/top/tracks") {
            headers {
                append(HttpHeaders.Authorization, "Bearer ${accessToken.accessToken}")
            }
        }
        call.respondText("callback successful")
    }
}