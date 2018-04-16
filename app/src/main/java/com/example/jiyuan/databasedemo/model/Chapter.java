package com.example.jiyuan.databasedemo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Chapter {

    @Id
    long id;

    String chapterId;

    boolean free;
    String name;
    String gl;
    boolean buy;
    String rmb;
    String url;

    @Generated(hash = 33406146)
    public Chapter(long id, String chapterId, boolean free, String name, String gl, boolean buy,
            String rmb, String url) {
        this.id = id;
        this.chapterId = chapterId;
        this.free = free;
        this.name = name;
        this.gl = gl;
        this.buy = buy;
        this.rmb = rmb;
        this.url = url;
    }

    @Generated(hash = 393170288)
    public Chapter() {
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGl() {
        return gl;
    }

    public void setGl(String gl) {
        this.gl = gl;
    }

    public String getRmb() {
        return rmb;
    }

    public void setRmb(String rmb) {
        this.rmb = rmb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean isBuy() {
        return buy;
    }

    public void setBuy(boolean buy) {
        this.buy = buy;
    }

    public boolean getFree() {
        return this.free;
    }

    public boolean getBuy() {
        return this.buy;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
