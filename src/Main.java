import twitter4j.*;

import java.io.Serializable;

public class Main {

    public static void main( String[] args ) throws TwitterException
    {
        Twitter twitter = new TwitterFactory().getInstance();
        User user = twitter.verifyCredentials();
        Paging paging = new Paging(1,200);

        //ユーザ情報取得
        System.out.println("名前　　　　：" + user.getName());
        System.out.println("表示名　　　：" + user.getScreenName());
        System.err.println("フォロー数　：" + user.getFriendsCount());
        System.out.println("フォロワー数：" + user.getFollowersCount());

        //つぶやきの実行
        //Status status = twitter.updateStatus("test for twitter4J");



        System.out.print(twitter.getFavorites("soroshi_1419",paging)+"/n");

    }
}