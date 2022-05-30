package komplettuebungen.webproxy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WebProxy {

    private PageCache cache;
    private int numCacheHits;
    private int numCacheMisses;

    public WebProxy() {
        this.cache = new PageCache();
        this.numCacheHits = 0;
        this.numCacheMisses = 0;
    }

    public WebProxy(PageCache cache) {
        this.cache = cache;
        this.numCacheHits = 0;
        this.numCacheMisses = 0;
    }

    public WebPage fetch(String url) throws CacheMissException {

        if(this.cache.getCache().containsKey(url)) {
            this.numCacheHits++;
            return this.cache.readFromCache(url);
        }
        else{
            numCacheMisses++;
            try{
                WebPage page = UrlLoader.loadWebPage(url);
                this.cache.writeToCache(page);
                return page;
            } catch (UrlLoaderException e) {
                throw new CacheMissException(e);
            }
        }

    }

    public String statsHits(){
        return "stats hits: " + this.numCacheHits;
    }

    public String statsMisses(){
        return "stats misses: " + this.numCacheMisses;
    }

    public boolean writePageCacheToFile(String pathToFile){

        boolean result = true;

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFile))) {
            HashMap<String, WebPage> cache = this.cache.getCache();

            for (String key : cache.keySet()) {
                WebPage page = cache.get(key);
                String line = key + ";" + page.getContent();
                bw.write(line);
                bw.newLine();
            }
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

}
