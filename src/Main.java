import twitter4j.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Main {


    public static void main( String[] args ) throws TwitterException{

        Twitter twitter = new TwitterFactory().getInstance();
        User user = twitter.verifyCredentials();

        //ユーザ情報取得
        System.out.println("ログインしているユーザー情報");
        System.out.println("名前　　　　：" + user.getName());
        System.out.println("表示名　　　：" + user.getScreenName());
        System.err.println("フォロー数　：" + user.getFriendsCount());
        System.out.println("フォロワー数：" + user.getFollowersCount());
        System.out.println("--------------------------------------");


        
        ResponseList<Status> fav = null;
        int page =1;
        int total = 0;


        //Paging paging = new Paging(page, 10);
        Paging paging = new Paging(page, 200);

        fav = twitter.getFavorites("soroshi_1419", paging);
        for (Status status : fav) {
            MediaEntity[] arrMedia = status.getMediaEntities();
            MediaEntity[] exMedia = status.getExtendedMediaEntities();

            for (MediaEntity media : exMedia) {

                //System.out.println(exMedia.length);
                //System.out.println(media.getMediaURL());
                try {

                    if(exMedia.length != 1) {
                        File newdir = new File("fav/"+status.getUser().getScreenName()+"_"+media.getId());
                        newdir.mkdir();

                        for(int i=0; i<exMedia.length; i++) {

                            URL url = new URL(media.getMediaURL());
                            URLConnection urlConnection = url.openConnection();
                            URLConnection urlcon = url.openConnection();

                            InputStream fileIS = urlcon.getInputStream();
                            File saveFile = new File("fav/" + status.getUser().getScreenName() + "_" + media.getId() + "/"+ i + ".jpg");
                            FileOutputStream fileOS = new FileOutputStream(saveFile);
                            int c;
                            while ((c = fileIS.read()) != -1) fileOS.write((byte) c);
                            fileOS.close();
                            fileIS.close();
                        }
                    }else {


                        URL url = new URL(media.getMediaURL());
                        URLConnection urlConnection = url.openConnection();
                        URLConnection urlcon = url.openConnection();

                        InputStream fileIS = urlcon.getInputStream();
                        File saveFile = new File("fav/" + status.getUser().getScreenName() + "_" + media.getId() + ".jpg");
                        FileOutputStream fileOS = new FileOutputStream(saveFile);
                        int c;
                        while ((c = fileIS.read()) != -1) fileOS.write((byte) c);
                        fileOS.close();
                        fileIS.close();
                    }

                }catch (java.net.MalformedURLException e){
                    e.printStackTrace();
                    System.out.print("無効なURL");
                }
                catch (java.io.IOException e){
                    e.printStackTrace();
                    System.out.print("入出力エラー");
                }
            }
        }






/*
画像をfavディレクトリに保存するのはできた
        現状の課題
        ・複数画像投稿
        ・取得できなかった時の例外処理
        ・取得しないブラックリスト処理
        */
    }
}