package aed.twualger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class TwualgerA extends Twualger{

    private ArrayList<UserCacheA> cache = new ArrayList<UserCacheA>();
    private int totalSearchescount;
    private int hitcount;


    public TwualgerA(String path)
    {
        super(path);
        List <String> celebs = Twualger.readTopCelebs(path);  //lê as top celebs
        for (int i =0; i < celebs.size();i++) {
            cache.add(readUserTweetsFromFile(path, celebs.get(i))); //adiciona à cache os tweets de cada celebridade
            cache.get(i).isTop = true;
        }
    }
	
	public List<UserCacheA> getCaches()
    {
        return this.cache;
    }

    public static UserCacheA readUserTweetsFromFile(String path, String userHandle) {
        UserCacheA uCache = null;
        ArrayList<Tweet> tweetList = null;
        try {
            File file = new File(path + "/" + userHandle + ".csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            br.readLine(); //para ignorar a primeira linha
            uCache = new UserCacheA(userHandle); //cache do user
            tweetList = new ArrayList<Tweet>(); //lista de tweets
            String lines;
            while ((lines = br.readLine()) != null) {
                String[] split = lines.split(",", 3); //3 pq os tweets podem ter virgulas
                Tweet tt = new Tweet(Long.parseLong(split[0]), OffsetDateTime.parse(split[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZZZZZ")), userHandle, split[2]);
                tweetList.add(tt); //adiciona a lista de tweets no formato certo
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        uCache.tweets = tweetList; //atribuir a tweetlist à lista de tweets da classe UserCaheA
        return uCache; //retorna a cache
    }


    public UserCacheA getUserCache(String userHandle)
    {
        UserCacheA uCache;
        int n = cache.size();
        totalSearchescount++; //funcao total searches
        for (int i = 0; i<n; i++) {
            if (cache.get(i).userName.equals(userHandle)) { //se o nome na cache for igual ao nome dado na funçao
                cache.get(i).useCount++;  //count para o hit
                cache.get(i).isTop = true; //está presente na cache
                hitcount++; //funcao cachehitratio
                return cache.get(i); //retorna a cache em que esta presente o nome do userhandle
            }
        }
        uCache = readUserTweetsFromFile(path, userHandle); //usar o ficheiro com os tweets do user dado
        uCache.useCount++; //count para o miss
        cache.add(uCache); //adicionar à cache esta cache que nao estava na cache
        return uCache; //retorna a cache
    }



    public List<Tweet> buildTimeLine(List<String> following, OffsetDateTime from, OffsetDateTime to)
    {
        List <UserCacheA> users = new ArrayList<>();
        for(String userName : following){
            UserCacheA user = getUserCache(userName);
            users.add(user);
        }
        /*for ( String userName :  following) { //vai andando de following em following
            UserCacheA user = readUserTweetsFromFile(path, userName);//guardar na UserCache os tweets de cada user com a funão de cima
            users.add(user);  //adicionar à lista a usercache dos users
        }*/
        List <Tweet> ttList = new ArrayList<>();
        for( UserCacheA user: users){ //por cada cache de user
            for(Tweet tt : user.tweets){ //percorre todos os tweets
                if(!(tt.date.isBefore(from)) && !(tt.date.isAfter(to)) ){ //vê se a data está entre o from e o to
                    ttList.add(tt); //adiciona à ttList os tweets
                }
            }
        }
        ttList.sort(TwualgerA::order);
        return ttList;
    }

    private static int order(Tweet a, Tweet b){
        return -(a.date.compareTo(b.date));
    } //retorna os tweets ordenados por ordem decrescente de data

    private static int order2(UserCacheA a, UserCacheA b){
        return (a.useCount - b.useCount);
    }

    public int totalSearches()
    {
        return totalSearchescount;
    }

    public float cacheHitRatio()
    {
        if(totalSearchescount != 0){
            return (float) hitcount/totalSearchescount;}         //se não dá NaN em vez de 0
        else {
            return 0;
        }
    }

    public void downsizeCache()
    {
        int j = 0;
        int n = cache.size()/2;
        cache.sort(TwualgerA::order2);                            //ordenar por use count
        for (int i =0;i<cache.size();i++){
            if (j < n && !cache.get(i).isTop) {               //se o j ainda nao tiver chegado a metade e não for top remove
                cache.remove(cache.get(i));
                i--;
            }
            else {                                                //se for top aumenta a metade
                n++;
            }
            j++;
        }
        for(UserCacheA userCacheA : cache) {                    //reset a todos os contadores
            userCacheA.useCount = 0;
        }
        hitcount=0;
        totalSearchescount=0;
    }

    public static void main(String[] args)
    {
        TwualgerA twualger = new TwualgerA("data");

        ArrayList<String> following = new ArrayList<String>(Arrays.asList("elonmusk","robertdowneyjr","cristiano"));

        List<Tweet> tweets = twualger.buildTimeLine(
                following,
                OffsetDateTime.of(2022,4,1,0,0,0,0, ZoneOffset.UTC),
                OffsetDateTime.of(2022,4,29,23,59,0,0,ZoneOffset.UTC));

        printTweets(tweets);

        System.out.println("Cache size: " + twualger.cache.size());
        twualger.downsizeCache();
        System.out.println("Cache size: " + twualger.cache.size());

    }

}
