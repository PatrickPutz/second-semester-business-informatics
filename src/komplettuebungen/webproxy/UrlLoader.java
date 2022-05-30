package komplettuebungen.webproxy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlLoader {

    public static void main(String[] args) throws IOException {
        URL myUrl = null;
        try {
            myUrl = new URL(
                    "https://www.campus02.at:443");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.println("myUrl.protocol = " + myUrl.getProtocol());
        System.out.println("myUrl.hostname = " + myUrl.getHost());
        System.out.println("myUrl.port = " + myUrl.getPort());
        System.out.println("myUrl.getContent() = " + myUrl.getContent());

    }

    public static WebPage loadWebPage(String url) throws UrlLoaderException {
        WebPage result;

        try {
            URL loaded = new URL(url);
            result = new WebPage(loaded.getHost(), loaded.getContent().toString());
        } catch (Exception e) {
            throw new UrlLoaderException("URL: " + url, e);
        }

        return result;
    }

}
