import twitter4j.*;
import java.io.FileOutputStream;
import  java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import twitter4j.MediaEntity;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.Serializable;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main {
    static final String TARGET_EXTENSION = ".jpg";

    public static void main( String[] args ) throws TwitterException{

        Twitter twitter = new TwitterFactory().getInstance();
        User user = twitter.verifyCredentials();
        //Paging paging = new Paging(1,200);

        //ユーザ情報取得
        System.out.println("名前　　　　：" + user.getName());
        System.out.println("表示名　　　：" + user.getScreenName());
        System.err.println("フォロー数　：" + user.getFriendsCount());
        System.out.println("フォロワー数：" + user.getFollowersCount());

        //つぶやきの実行
        //Status status = twitter.updateStatus("test for twitter4J");


        /*for(int i=1;i<3;i++){
            Paging paging = new Paging(i,4);
            System.out.print(twitter.getFavorites("soroshi_1419",paging)+"\n");
    }
    */
        ResponseList<Status> fav = null;
        int page =1;
        int total = 0;



            Paging paging = new Paging(page, 200);

            fav = twitter.getFavorites("soroshi_1419", paging);
        for (Status status : fav){
            String userName = status.getUser().getScreenName();
            System.out.println(userName);
        }





//最新ツイート　(時刻が最新に近いもの)から探すっぽい? 直観的な　現在Twitterクライアントで見ることができる""ふぁぼした順ではない""

        //拾ってきたものをjsonに変換→extended_entities.media[0]を抽出すれば画像URLを取得できる　すれをwgetなどで保存?
        //APIに対して画像ふぁぼできるのが少なそう
        //最近ふぁぼった順で探したいよね
            //ふぁぼった順は無理そう IFTTTもそうなってた
    }
}