package pl.parser.nbp.client;

import com.jcabi.http.request.JdkRequest;

import java.io.IOException;

public class HttpClient {
    public static String getStringFromUri(String uri) throws IOException {
        return new String(new JdkRequest(uri).fetch().binary());
    }
}
