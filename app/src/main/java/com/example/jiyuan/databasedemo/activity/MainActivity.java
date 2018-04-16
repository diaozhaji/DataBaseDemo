package com.example.jiyuan.databasedemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.example.jiyuan.databasedemo.db.GreenDaoManager;
import com.example.jiyuan.databasedemo.db.PlainDBManager;
import com.example.jiyuan.databasedemo.R;
import com.example.jiyuan.databasedemo.model.Book;
import com.example.jiyuan.databasedemo.model.Chapter;
import com.example.jiyuan.databasedemo.model.Chapters;
import com.example.jiyuan.databasedemo.model.Result;
import com.example.jiyuan.databasedemo.util.Utils;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);

        /*
        mRealm = Realm.getDefaultInstance();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            add(i);
        }
        long end = System.currentTimeMillis();
        Log.e("@@@", "插入" + (end - start) + "ms");

        StringBuilder sb = new StringBuilder();
        //for (Book book : select()) {
        //    sb.append(book.getBookId());
        //    sb.append("\n");
        //}
        for (Chapter chapter : selectChapters()) {
            sb.append(chapter.getChapterId());
            sb.append("\n");
        }
        textView.setText(sb.toString());*/
    /*
        initDB();

        long start = System.currentTimeMillis();
        List<PlainPerson> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            PlainPerson person = new PlainPerson();
            person.setName("隔壁老王" + i);
            person.setAddress("天使大街 " + i + " 号");
            list.add(person);
        }
        mDBManager.addPersonList(list);
        long end = System.currentTimeMillis();
        Log.e("@@@", "插入" + (end - start) + "ms");

        start = System.currentTimeMillis();
        List<PlainPerson> l = mDBManager.getPersonListData();
        end = System.currentTimeMillis();
        Log.e("@@@", "查询" + (end - start) + "ms");
        int i = 0;
        for (PlainPerson p : l) {
            i++;
            if (i % 1000 == 0) {
                Log.e("@@@", p.getAddress());
            }
        }*/

        long start = System.currentTimeMillis();
        String json = Utils.getFromAssets(this, "xiaohua.json", "utf-8");
        long end = System.currentTimeMillis();
        Log.e("@@@", "读入" + (end - start) + "ms");

        //Result r = JSON.parseObject(json, Result.class);
        //textView.setText(r.getResult()[0]);

        start = System.currentTimeMillis();
        Chapters chapters = Utils.parseChapter(json);
        end = System.currentTimeMillis();
        Log.e("@@@", "解析" + (end - start) + "ms");

        start = System.currentTimeMillis();
        GreenDaoManager.getInstance().insertChapters(chapters.getChapter());
        end = System.currentTimeMillis();
        Log.e("@@@", "插入GreenDAO:" + (end - start) + "ms");

        start = System.currentTimeMillis();
        List<Chapter> list = GreenDaoManager.getInstance().getChapters();
        end = System.currentTimeMillis();
        Log.e("@@@", "查询GreenDAO:" + (end - start) + "ms");

        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Chapter chapter : list) {
            i++;
            if (i % 100 == 1) {
                sb.append(chapter.getName()).append(chapter.isFree()).append("\n");
            }
            //sb.append(chapter.getName()).append(chapter.isFree()).append("\n");
        }
        textView.setText(sb.toString());
        end = System.currentTimeMillis();
        Log.e("@@@", "显示" + (end - start) + "ms");
    }

    private PlainDBManager mDBManager;

    private void initDB() {
        mDBManager = new PlainDBManager(MainActivity.this);
    }

    /**
     * realm
     */
    private void add(int key) {

        mRealm.beginTransaction();
        //Chapter chapter = new Chapter();
        //chapter.setChapterId("chapter" + key);
        //chapter.setName("第49章 想吃天鹅肉的癞蛤蟆");
        //chapter.setBuy(false);
        //chapter.setFree(false);
        //chapter.setRmb("0.00");

        Book book = new Book();
        book.setBookId("book" + key);
        book.setName("John");
        //mRealm.copyToRealmOrUpdate(chapter);
        mRealm.commitTransaction();
    }

    private void del() {
    }

    private void update() {

    }

    private RealmResults<Book> selectBooks() {
        return mRealm.where(Book.class).findAll();
    }

    private RealmResults<Chapter> selectChapters() {
        long start = System.currentTimeMillis();

        //RealmResults<Chapter> r = mRealm.where(Chapter.class).findAll();

        long end = System.currentTimeMillis();

        Log.e("@@@", "查询:" + (end - start) + "ms");
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
