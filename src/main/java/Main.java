
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, SpotifyWebApiException {


        SpotifyAlbum spotifyAlbum = new SpotifyAlbum();
        CreateTweet tweet = new CreateTweet();
        System.out.println(spotifyAlbum.getArtist());
        System.out.println(spotifyAlbum.getAlbumName());
        System.out.println(spotifyAlbum.getAlbumType());
        System.out.println(spotifyAlbum.getImgAddress());
        System.out.println(spotifyAlbum.getSpotifyHref());
        System.out.println(tweet.postTweet(spotifyAlbum.getImgAddress()));



    }
}
