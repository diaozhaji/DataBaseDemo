package com.example.jiyuan.databasedemo.db;

import com.example.jiyuan.databasedemo.MyApplication;
import com.example.jiyuan.databasedemo.gen.DaoMaster;
import com.example.jiyuan.databasedemo.gen.DaoSession;
import com.example.jiyuan.databasedemo.model.Chapter;
import java.util.List;

public class GreenDaoManager {
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static volatile GreenDaoManager mInstance = null;

    private GreenDaoManager() {
        if (mInstance == null) {
            DaoMaster.DevOpenHelper devOpenHelper =
                new DaoMaster.DevOpenHelper(MyApplication.getContext(), "Chapter.db");
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    public void insertChapters(List<Chapter> chapterList) {
        mDaoSession.getChapterDao().insertInTx(chapterList);
    }

    public List<Chapter> getChapters() {
        return mDaoSession.getChapterDao().loadAll();
    }
}
