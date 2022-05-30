package komplettuebungen.webproxy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PageCache {

    private HashMap<String, WebPage> cache;

    public PageCache() {
        this.cache = new HashMap<>();
    }

    public void warmUp(String pathToUrls){

        try(BufferedReader br = new BufferedReader(new FileReader(pathToUrls))) {
            String line;
            while((line = br.readLine()) != null){
                WebPage webPage = UrlLoader.loadWebPage(line);
                this.cache.put(webPage.getUrl(), webPage);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UrlLoaderException e) {
            System.out.println("Error while loading.");
        }


    }

    public WebPage readFromCache(String url) throws CacheMissException {

        if(this.cache.containsKey(url))
            return this.cache.get(url);
        else
            throw new CacheMissException("URL not in cache: " + url);

    }

    public void writeToCache(WebPage webPage){
        this.cache.put(webPage.getUrl(), webPage);
    }

    public HashMap<String, WebPage> getCache() {
        return cache;
    }
}
