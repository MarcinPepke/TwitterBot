
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, SpotifyWebApiException, InterruptedException {


        SpotifyAlbum spotifyAlbum = new SpotifyAlbum();
        System.out.println(spotifyAlbum.getReleaseDate());
        CreateTweet tweet = new CreateTweet();
        while (true) {
            if (spotifyAlbum.getAlbumName() == null) {
                System.out.println("waiting for post");
                TimeUnit.MINUTES.sleep(1);
            }
            else {
                for (int i = 0, j = 0; i < spotifyAlbum.getAlbumName().size(); i++, j += 3) {
                    System.out.println(tweet.postTweet(spotifyAlbum.getImgAddress().get(j), spotifyAlbum.getArtist().get(i), spotifyAlbum.getAlbumName().get(i), spotifyAlbum.getAlbumType().get(i), spotifyAlbum.getSpotifyHref().get(i)));

                }
            }
        }


    }
}
