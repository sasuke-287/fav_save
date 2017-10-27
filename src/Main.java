import twitter4j.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


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
        for (Status status : fav) {
            MediaEntity[] arrMedia = status.getMediaEntities();

            for (MediaEntity media : arrMedia) {


                System.out.println(media.getMediaURL());
                try {
                    URL url = new URL(media.getMediaURL());
                    URLConnection urlConnection = url.openConnection();
                    URLConnection urlcon =url.openConnection();

                    InputStream fileIS =urlcon.getInputStream();
                    File saveFile = new File("fav/"+media.getId()+".jpg");
                    FileOutputStream fileOS = new FileOutputStream(saveFile);
                    int c;
                    while((c =fileIS.read()) != -1) fileOS.write((byte) c);
                    fileOS.close();
                    fileIS.close();

                }catch (java.net.MalformedURLException e){
                    e.printStackTrace();
                }
                catch (java.io.IOException e){
                    e.printStackTrace();
                }
            }
        }






/*
画像をfavディレクトリに保存するのはできた
        現状の課題
        ・複数画像投稿
        ・取得できなかった時の例外処理
        ・取得しないブラックリスト処理
    }
}