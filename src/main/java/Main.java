import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, SpotifyWebApiException {
        SpotifyAlbum spotifyAlbum = new SpotifyAlbum();
        System.out.println(spotifyAlbum.getArtist());
        System.out.println(spotifyAlbum.getAlbumType());
        System.out.println(spotifyAlbum.getImgAdress());
        System.out.println(spotifyAlbum.getSpotifyHref());
    }
}
