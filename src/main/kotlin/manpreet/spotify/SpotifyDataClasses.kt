import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotifyAccessToken(
    @SerialName("access_token") val accessToken: String,
    @SerialName("token_type") val tokenType: String,
    val scope: String,
    @SerialName("expires_in") val expiresIn: Int,
    @SerialName("refresh_token") val refreshToken: String
)

@Serializable
data class SpotifyArtistImage(
    val url: String
)

@Serializable
data class SpotifyArtist(
    val name: String,
    val images: List<SpotifyArtistImage>
)

@Serializable
data class SpotifyTopArtists(
    @SerialName("items") val items: List<SpotifyArtist>
)

@Serializable
data class SpotifyTrackAlbum(
    val name: String
)

@Serializable
data class SpotifyTrack(
    val name: String,
    val album: SpotifyTrackAlbum
)

@Serializable
data class SpotifyTopTracks(
    @SerialName("items") val items: List<SpotifyTrack>
)