import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.tweet.MediaCategory;
import io.github.redouane59.twitter.dto.tweet.Tweet;
import io.github.redouane59.twitter.dto.tweet.TweetParameters;
import io.github.redouane59.twitter.dto.tweet.UploadMediaResponse;
import io.github.redouane59.twitter.signature.TwitterCredentials;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;

public class CreateTweet {
    final static TwitterClient getTwitterInstance = new TwitterClient(TwitterCredentials.builder()
            .accessToken("703135577831448577-FoAP0CcP4yaxzDdDGuHZYy0e7IvUYNi")
            .accessTokenSecret("gI3iEdR4OGDKjMGsECXUY5pRNeqsRabfntlcM7FNpWrCi")
            .apiKey("HhcyGXH9PVpbM30Hg3cQHH18t")
            .apiSecretKey("RurrzyKh2a4TnLw5McqT6fbN5HBkghAZxG0yG2FcWEtWFMShAx")
            .build());

    public String postTweet(String imgUrl,  String author, String albumName,  String albumType,  String albumLink) {
        UploadMediaResponse media = getTwitterInstance.uploadMedia("obrazek", getImageFromNetByUrl(imgUrl), MediaCategory.TWEET_IMAGE);
        Tweet tweet = getTwitterInstance.postTweet(TweetParameters.builder().media(TweetParameters.Media.builder()
                        .mediaIds(Collections.singletonList(media.getMediaId())).build())
                .text("New " + albumType + " - " + albumName + " by " + author + "\nSpotify Link - " + albumLink)
                .build());
        return tweet.getText();
    }


    public static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            return readInputStream(inStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();

    }
}
