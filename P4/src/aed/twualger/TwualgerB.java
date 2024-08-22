package aed.twualger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TwualgerB extends Twualger {

    HashMap<String, UserCacheB> cache;


    public TwualgerB(String path)
    {
        super(path);
        //TODO: implement
    }

	public List<UserCacheB> getCaches()
    {
        return new ArrayList<UserCacheB>(this.cache.values());
    }

    public static UserCacheB readUserTweetsFromFile(String path, String userHandle)
    {
        //TODO: implement
        return null;
    }

    public UserCacheB getUserCache(String userHandle)
    {
        //TODO: implement
        return null;
    }

    public List<Tweet> buildTimeLine(List<String> following, OffsetDateTime from, OffsetDateTime to)
    {
        //TODO: implement
        return null;
    }

    public int totalSearches()
    {
        //TODO: implement
        return 0;
    }

    public float cacheHitRatio()
    {
        //TODO: implement
        return 0;
    }

    public void downsizeCache()
    {
        //TODO: implement
        return;
    }

    public static void main(String[] args)
    {

        aed.twualger.TwualgerB twualger = new aed.twualger.TwualgerB("data");

        ArrayList<String> following = new ArrayList<String>(Arrays.asList("elonmusk","robertdowneyjr","cristiano"));

        List<Tweet> tweets = twualger.buildTimeLine(
                following,
                OffsetDateTime.of(2022,4,1,0,0,0,0, ZoneOffset.UTC),
                OffsetDateTime.of(2022,4,29,23,59,0,0,ZoneOffset.UTC));

        printTweets(tweets);

    }

}
