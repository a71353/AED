package aed.twualger;

import aed.tables.Treap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TwualgerC extends Twualger {

    //ideally we would use current datetime, but we only have tweet data until Jully 11th
    private static final OffsetDateTime CURRENT_DATE = OffsetDateTime.of(2022,07,11,23,59,59,0,ZoneOffset.UTC);
    private static final OffsetDateTime CURRENT_MINUS_72H = CURRENT_DATE.minusHours(72);

    HashMap<String,UserCacheB> cache;


    public TwualgerC(String path)
    {
        super(path);
        //TODO: implement
    }
	
	public List<UserCacheB> getCaches()
    {
        return new ArrayList<UserCacheB>(this.cache.values());
    }


    public static UserCacheB readUserTweetsFromFile(String path, String userHandle, OffsetDateTime oldest)
    {
        //TODO: implement
        return null;
    }

    public static void updateUserCacheFromFile(UserCacheB userCache, String path, String userHandle, OffsetDateTime oldest)
    {
        //TODO: implement

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

        TwualgerC twualger = new TwualgerC("data");

        ArrayList<String> following = new ArrayList<String>(Arrays.asList("elonmusk","RobertDowneyJr","Cristiano"));

        List<Tweet> tweets = twualger.buildTimeLine(
                following,
                OffsetDateTime.of(2022,4,1,0,0,0,0, ZoneOffset.UTC),
                OffsetDateTime.of(2022,4,29,23,59,0,0,ZoneOffset.UTC));

        printTweets(tweets);

    }

}
