import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.browse.GetListOfNewReleasesRequest;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SpotifyAlbum {
    final static String BEARER = "BQCtJTKhLFuCzu-GScRAqy1yQl4W4IPAOZdakl9fxywCYcL5Wj0XCwgIdn1YfBDKZF-d5rjB7rruKgcNgKr6XivKytppEuV41QJulesG0ow1vwUu7QVGcaTydmtMCs9LmsoehcDZ8ahtXlUJ06kNWOQ3vqp5zSgKAe_8QDRB13Ky9P9QvRH6gaW78pAvLg7vEkLHG_xEyQZ3aCWWzWk";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId("2bcb05b4c8094c44958c34ecb9391751")
            .setClientSecret("273ab3cb8a6a46b9a027a539a260b237")
            .setAccessToken(BEARER)
            .build();


    private static final GetListOfNewReleasesRequest getListOfNewReleasesRequest = spotifyApi.getListOfNewReleases()
            .country(CountryCode.PL)
            .limit(5)
            .offset(1)
            .build();

    public List<String> getArtist() throws IOException, ParseException, SpotifyWebApiException {
        final Paging<AlbumSimplified> album = getListOfNewReleasesRequest.execute();
        List<String> artistsNames = new ArrayList<>();
        for (AlbumSimplified items : album.getItems()
        ) {
            for (ArtistSimplified artist : items.getArtists()
            ) {
                if (items.getReleaseDate().equals(LocalDate.now().toString()))
                    artistsNames.add(artist.getName());
            }

        }
        return artistsNames;
    }

    public List<String> getAlbumType() throws IOException, ParseException, SpotifyWebApiException {
        final Paging<AlbumSimplified> album = getListOfNewReleasesRequest.execute();
        List<String> albumTypesList = new ArrayList<>();
        for (AlbumSimplified albumItems :
                album.getItems()) {
            if (albumItems.getReleaseDate().equals(LocalDate.now().toString()))
                albumTypesList.add(albumItems.getAlbumType().getType());
        }
        return albumTypesList;
    }

    public String getImgAdress() throws IOException, ParseException, SpotifyWebApiException {
        final Paging<AlbumSimplified> album = getListOfNewReleasesRequest.execute();
        for (AlbumSimplified albumItems :
                album.getItems()) {
            for (Image img :
                    albumItems.getImages()
            ) {
                if (albumItems.getReleaseDate().equals(LocalDate.now().toString()))
                    return img.getUrl();
            }
        }
        return null;
    }

    public List<String> getSpotifyHref() throws IOException, ParseException, SpotifyWebApiException {
        final Paging<AlbumSimplified> album = getListOfNewReleasesRequest.execute();
        List<String> spotifyAlbumUrls = new ArrayList<>();
        for (AlbumSimplified albumItems :
                album.getItems()) {
            if (albumItems.getReleaseDate().equals(LocalDate.now().toString()))
                albumItems.getExternalUrls().getExternalUrls().forEach((s, s2) -> spotifyAlbumUrls.add(s2));

        }
        return spotifyAlbumUrls;
    }


}
