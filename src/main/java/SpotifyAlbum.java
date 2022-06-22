import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.browse.GetListOfNewReleasesRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpotifyAlbum {
    static final String clientId = "2bcb05b4c8094c44958c34ecb9391751";
    static final String clientSecret = "273ab3cb8a6a46b9a027a539a260b237";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();

    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
            .build();


    public String getBearer() throws IOException, ParseException, SpotifyWebApiException {
        ClientCredentials clientCredentials = clientCredentialsRequest.execute();
        System.out.println(clientCredentials.getAccessToken());
        return clientCredentials.getAccessToken();
    }


    public GetListOfNewReleasesRequest releasesRequestPL() throws IOException, ParseException, SpotifyWebApiException {
        spotifyApi.setAccessToken(getBearer());
        return spotifyApi.getListOfNewReleases()
                .country(CountryCode.PL)
                .limit(5)
                .offset(0)
                .build();
    }

    public List<String> getArtist() throws IOException, ParseException, SpotifyWebApiException {
        final Paging<AlbumSimplified> album = releasesRequestPL().execute();
        StringBuilder artists = new StringBuilder();
        List<String> artistsNames = new ArrayList<>();
        for (AlbumSimplified albumItems : album.getItems()
        ) {
            for (ArtistSimplified artist : albumItems.getArtists()
            ) {
                if (albumItems.getArtists().length > 1)
                    artists.append(artist.getName()).append(", ");
                else
                    artistsNames.add(artist.getName());

            }
            if (artists.toString().length() > 0) {
                artists.delete(artists.toString().length() - 2, artists.toString().length());
                artistsNames.add(artists.toString());
                artists = new StringBuilder();
            }
        }

        return artistsNames;
    }

    public List<String> getAlbumType() throws IOException, ParseException, SpotifyWebApiException {
        final Paging<AlbumSimplified> album = releasesRequestPL().execute();
        List<String> albumTypesList = new ArrayList<>();
        for (AlbumSimplified albumItems :
                album.getItems()) {
//            if (albumItems.getReleaseDate().equals(LocalDate.now().toString()))
            albumTypesList.add(albumItems.getAlbumType().getType());
        }
        return albumTypesList;
    }

    public String getImgAddress() throws IOException, ParseException, SpotifyWebApiException {
        final Paging<AlbumSimplified> album = releasesRequestPL().execute();
        for (AlbumSimplified albumItems :
                album.getItems()) {
            for (Image img :
                    albumItems.getImages()
            ) {
//                if (albumItems.getReleaseDate().equals(LocalDate.now().toString()))
                return img.getUrl();
            }
        }
        return null;
    }

    public List<String> getSpotifyHref() throws IOException, ParseException, SpotifyWebApiException {
        final Paging<AlbumSimplified> album = releasesRequestPL().execute();
        List<String> spotifyAlbumUrls = new ArrayList<>();
        for (AlbumSimplified albumItems :
                album.getItems()) {
//            if (albumItems.getReleaseDate().equals(LocalDate.now().toString()))
            albumItems.getExternalUrls().getExternalUrls().forEach((s, s2) -> spotifyAlbumUrls.add(s2));

        }
        return spotifyAlbumUrls;
    }

    public List<String> getAlbumName() throws IOException, ParseException, SpotifyWebApiException {
        final Paging<AlbumSimplified> albumSimplifiedPaging = releasesRequestPL().execute();
        List<String> spotifyAlbumNames = new ArrayList<>();
        for (AlbumSimplified albumItems :
                albumSimplifiedPaging.getItems()) {
//            if (albumItems.getReleaseDate().equals(LocalDate.now().toString()))
            spotifyAlbumNames.add(albumItems.getName());

        }
        return spotifyAlbumNames;
    }


}
