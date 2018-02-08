package com.rain.netdemo.bean;

import java.util.List;

/**
 * Author:rain
 * Date:2018/2/8 10:55
 * Description:
 */

public class NewsList {
    /**
     * data : {"newsList":[{"digest":"央视网消息：世界历史，卷帙浩繁，化繁为简，是为妙喻。 ","img_url":"28f7caefedda4ca984b485073f3d2744","news_id":"28f7caefedda4ca984b485073f3d2744","time":"2017-05-09 14:46:34","title":"习近平十个比喻描绘\u201c一带一路\u201d蓝图"},{"digest":"　在饮食上，空腹一般指胃内的食物全部排到十二指肠内，即胃排空的状态，一般在饭后4～5个小时就达到空腹状态。网络盛传的\u201c不能空腹吃\u201d的食物中，哪些其实是可以空腹吃的，哪些是真的不能空腹吃的？让专家来给您解答。","img_url":"f12dc42c56ea422484df2c15a469de4f","news_id":"f12dc42c56ea422484df2c15a469de4f","time":"2017-05-25 15:03:29","title":"哪些食物不能空腹吃678"},{"digest":"嫉妒的人总是拿别人的优点来折磨自己。别人年轻他嫉妒，另人长相好他嫉妒，别人身材高他嫉妒，别人风度潇洒他嫉妒，别人有才学他嫉妒，别人富有他嫉妒，别人的妻子漂亮他嫉妒，别人学历高他嫉妒。好嫉妒的人总是40岁的脸上就写满50岁的沧桑。","news_id":"be516e87b17540d39b2ead5bc810b79a","time":"2017-05-26 15:08:10","title":"嫉妒是心理的地狱 如何远离嫉妒"},{"digest":"你是否越来越习惯晚睡？","img_url":"eb08397d34634dbb9a257b74b9decf5a","news_id":"eb08397d34634dbb9a257b74b9decf5a","time":"2017-06-14 11:45:18","title":"晚上几点入睡是最佳时间"},{"digest":"让你吃起来更健康","news_id":"c94f04c69f5e4117985f535808cc49f1","time":"2017-06-14 11:47:41","title":"夏季吃那些水果对身体有好处"},{"digest":"采访记录\u201c双绿\u201d时代的真实脉动。 从8月7日起，澎湃新闻陆续刊发\u201c双绿\u201d系列报道，今天讲述的是福建长汀水土治理的故事","img_url":"b3384ff18e0149b79d7dc5a4b56d7554","news_id":"b3384ff18e0149b79d7dc5a4b56d7554","time":"2017-08-21 10:11:19","title":"\u201c双绿\u201d脉动"},{"digest":"国家卫计委体改司司长梁万年对未来几年内医改政策的新风向做出了一些分析和预测","news_id":"ba3b52994dda48a49a904e331e76d2b9","time":"2018-01-10 18:55:20","title":"2018年，医改将出现8大动向，医疗机构将面临6大发展机遇"},{"digest":"《深圳市家庭医生服务管理办法（试行）》","news_id":"610e0ac8328848bd81ae3d242db2e44d","time":"2017-12-01 18:59:31","title":"国内首个家庭医生服务管理办法出台 社会办医疗机构也可开展 每人补助120"},{"digest":"近日，天津海河医院发布公告称，因儿科医生超负荷工作，目前均已病倒，儿科不得不停诊。","news_id":"6e0221e3f4074e00ad39470beb84deaf","time":"2018-01-09 19:02:04","title":"流感大爆发！天津一医院儿科医生病倒被迫停诊"}]}
     * error_code : 0
     * msg : Success
     */
    private DataBean data;
    private String error_code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<NewsListBean> newsList;

        public List<NewsListBean> getNewsList() {
            return newsList;
        }

        public void setNewsList(List<NewsListBean> newsList) {
            this.newsList = newsList;
        }

        public static class NewsListBean {
            /**
             * digest : 央视网消息：世界历史，卷帙浩繁，化繁为简，是为妙喻。
             * img_url : 28f7caefedda4ca984b485073f3d2744
             * news_id : 28f7caefedda4ca984b485073f3d2744
             * time : 2017-05-09 14:46:34
             * title : 习近平十个比喻描绘“一带一路”蓝图
             */

            private String digest;
            private String img_url;
            private String news_id;
            private String time;
            private String title;

            public String getDigest() {
                return digest;
            }

            public void setDigest(String digest) {
                this.digest = digest;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getNews_id() {
                return news_id;
            }

            public void setNews_id(String news_id) {
                this.news_id = news_id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
