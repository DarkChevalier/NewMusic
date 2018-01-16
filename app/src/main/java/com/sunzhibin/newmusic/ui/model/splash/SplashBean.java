package com.sunzhibin.newmusic.ui.model.splash;

import android.text.TextUtils;

import com.sunzhibin.newmusic.base.mode.BaseBean;

import java.util.List;
import java.util.Locale;

/**
 * @author: sunzhibin
 * @date: 2018/1/15.
 * @description:
 * @e-mail:
 */

public class SplashBean extends BaseBean{
    private static final String URL = "http://cn.bing.com%s_720x1280.jpg";
    private List<ImagesBean> images;

    public String getUrl() {
        if (images != null && !images.isEmpty()) {
            String baseUrl = images.get(0).urlbase;
            if (!TextUtils.isEmpty(baseUrl)) {
                return String.format(Locale.getDefault(), URL, baseUrl);
            }
        }
        return null;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean {
        /**
         * bot : 1
         * copyright : 印度尼西亚海岸附近的狮子鱼 (© David Fleetham/Visuals Unlimited, Inc.)
         * copyrightlink : http://www.bing.com/search?q=%E7%8B%AE%E5%AD%90%E9%B1%BC&form=hpcapt&mkt=zh-cn
         * drk : 1
         * enddate : 20180116
         * fullstartdate : 201801151600
         * hs : []
         * hsh : 83298c16324534a4a16638b3da5456a0
         * quiz : /search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20180115_LionFish%22&FORM=HPQUIZ
         * startdate : 20180115
         * top : 1
         * url : /az/hprichbg/rb/LionFish_ZH-CN6318723202_1920x1080.jpg
         * urlbase : /az/hprichbg/rb/LionFish_ZH-CN6318723202
         * wp : false
         */

        private int bot;
        private String copyright;
        private String copyrightlink;
        private int drk;
        private String enddate;
        private String fullstartdate;
        private String hsh;
        private String quiz;
        private String startdate;
        private int top;
        private String url;
        private String urlbase;
        private boolean wp;
        private List<?> hs;

        public int getBot() {
            return bot;
        }

        public void setBot(int bot) {
            this.bot = bot;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getCopyrightlink() {
            return copyrightlink;
        }

        public void setCopyrightlink(String copyrightlink) {
            this.copyrightlink = copyrightlink;
        }

        public int getDrk() {
            return drk;
        }

        public void setDrk(int drk) {
            this.drk = drk;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getFullstartdate() {
            return fullstartdate;
        }

        public void setFullstartdate(String fullstartdate) {
            this.fullstartdate = fullstartdate;
        }

        public String getHsh() {
            return hsh;
        }

        public void setHsh(String hsh) {
            this.hsh = hsh;
        }

        public String getQuiz() {
            return quiz;
        }

        public void setQuiz(String quiz) {
            this.quiz = quiz;
        }

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlbase() {
            return urlbase;
        }

        public void setUrlbase(String urlbase) {
            this.urlbase = urlbase;
        }

        public boolean isWp() {
            return wp;
        }

        public void setWp(boolean wp) {
            this.wp = wp;
        }

        public List<?> getHs() {
            return hs;
        }

        public void setHs(List<?> hs) {
            this.hs = hs;
        }
    }
}
