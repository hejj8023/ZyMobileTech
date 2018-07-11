package com.example.comicbook.util;

import com.example.comicbook.bean.Comic;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 数据转换引擎
 */
public class DataConvertEngine {
    /**
     * 强推漫画
     */
    public static List<Comic> convertRecommendData(Document document) {
        List<Comic> list = new ArrayList<>();
        List<Element> elements = getElementsByAttr(document, "class", "in-anishe-text");
        Random random = new Random();
        int result = random.nextInt(5);
        Comic comic = null;
        for (int i = (result * 6); i < (result + 1); i++) {
            comic = new Comic();
            Element element1 = elements.get(i);
            comic.setTile(getTitle(element1));
            comic.setCover(getCover(element1));

            Elements elementDescribes = getElementsByAttr(document, "class",
                    "mod-cover-list-intro");
            if (elementDescribes != null && elementDescribes.size() > 0) {
                Element element = elementDescribes.get(0);
                comic.setDescribe(getDescribe(element));
                comic.setId(getId(element));
                list.add(comic);
            }
        }
        return list;
    }

    /**
     * 获取id
     */
    private static String getID(String idStr) {
        String[] ids = idStr.split("/");
        return ids[ids.length - 1];
    }

    /**
     * 从属性中获取元素
     */
    private static Elements getElementsByAttr(Document document, String key, String value) {
        return document.getElementsByAttributeValue(key, value);
    }

    /**
     * 获取属性
     */
    private static String getAttr(Element element, String cssQuery, String
            attributeKey) {
        if (element == null)
            return "";
        Elements select = element.select(cssQuery);
        if (select == null)
            return "";
        return select.attr(attributeKey);
    }

    /**
     * 热门连载
     */
    public static List<Comic> convertSerialData(Document document) {
        List<Comic> list = new ArrayList<>();
        List<Element> elements = getElementsByAttr(document, "class", "in-anishe-list clearfix " +
                "in-anishe-ul");
        Random random = new Random();
        int result = random.nextInt(7);
        Comic comic = null;
        /*随机取数据*/
        Element element = elements.get(result);
        List<Element> hots = element.getElementsByTag("li");
        for (int i = 0; i < 4; i++) {
            comic = new Comic();
            Element element1 = hots.get(i);
            comic.setTile(getAttr(element1, "img", "alt"));
            comic.setCover(getCover(element1));

            Elements elementDescribes = getElementsByAttr(document, "class",
                    "mod-cover-list-intro");
            if (elementDescribes != null && elementDescribes.size() > 0) {
                Element subEle = elementDescribes.get(0);
                comic.setDescribe(getDescribe(subEle));
                comic.setId(getId(element1));
                list.add(comic);
            }
        }
        return list;
    }

    /**
     * 男生榜
     */
    public static List<Comic> convertBRankData(Document document) {
        List<Comic> list = new ArrayList<>();
        List<Element> elements = getElementsByAttr(document, "class", "in-teen-list " +
                "mod-cover-list clearfix");

        Element element = elements.get(0);

        List<Element> boys = element.getElementsByTag("li");
        Element girlEle;
        Comic comic = null;

        for (int i = 0; i < boys.size(); i++) {
            girlEle = boys.get(i);
            comic = new Comic();
            comic.setTile(getAttr(girlEle, "img", "alt"));
            comic.setCover(getCover(girlEle));

            Elements elementDescribes = getElementsByAttr(document, "class",
                    "mod-cover-list-intro");
            if (elementDescribes != null && elementDescribes.size() > 0) {
                Element describesElement = elementDescribes.get(0);
                comic.setDescribe(getDescribe(describesElement));
                comic.setId(getId(girlEle));
                list.add(comic);
            }
        }
        return list;
    }

    /**
     * 女生榜
     */
    public static List<Comic> convertGRankData(Document document) {
        List<Comic> list = new ArrayList<>();
        List<Element> elements = getElementsByAttr(document, "class", "in-girl-list " +
                "mod-cover-list clearfix");

        Element element = elements.get(0);
        List<Element> girls = element.getElementsByTag("li");
        Element girlEle;
        Comic comic = null;
        for (int i = 0; i < girls.size(); i++) {
            girlEle = girls.get(i);
            comic = new Comic();
            comic.setTile(getAttr(girlEle, "img", "alt"));
            comic.setCover(getCover(girlEle));

            Elements elementDescribes = getElementsByAttr(document, "class",
                    "mod-cover-list-intro");
            if (elementDescribes != null && elementDescribes.size() > 0) {
                Element describesElement = elementDescribes.get(0);
                comic.setDescribe(getDescribe(describesElement));
                comic.setId(getId(girlEle));
                list.add(comic);
            }
        }
        return list;
    }

    /**
     * 处理漫画列表
     */
    public static List<Comic> convertJapanData(Document document) {
        List<Comic> list = new ArrayList<>();
        List<Element> details = getElementsByAttr(document, "class", "ret-works-cover");
        List<Element> infos = getElementsByAttr(document, "class", "ret-works-info");
        Comic comic;
        for (int i = 0; i < 3; i++) {
            comic = new Comic();
            if (details != null && details.size() > 0) {
                Element detailEle = details.get(i);
                comic.setTile(getTitle(detailEle));
                comic.setCover(getCover(detailEle));
            }
            if (infos != null && infos.size() > 0) {
                Element infoEle = infos.get(i);
                comic.setAuthor(getAttr(infoEle, "p", "title"));
                comic.setDescribe(getDescribe(infoEle));
                comic.setId(getId(infoEle));
            }
            list.add(comic);
        }
        return list;
    }

    private static String getTitle(Element detailEle) {
        return getAttr(detailEle, "a", "title");
    }

    private static String getCover(Element detailEle) {
        return getAttr(detailEle, "img", "data-original");
    }

    private static String getDescribe(Element infoEle) {
        return infoEle.select("p").text();
    }

    private static long getId(Element infoEle) {
        return Long.parseLong(getID(getAttr(infoEle, "a", "href")));
    }

    public static List<Comic> convertRankListData(Document document) {
        List<Comic> list = new ArrayList<>();
        List<Element> details = getElementsByAttr(document, "class", "ret-works-cover");
        List<Element> infos = getElementsByAttr(document, "class", "ret-works-info");
        Comic comic;
        for (int i = 0; i < details.size(); i++) {
            comic = new Comic();
            if (details != null && details.size() > 0) {
                Element detailEle = details.get(i);
                if (detailEle != null) {
                    comic.setTile(getTitle(detailEle));
                    comic.setCover(getCover(detailEle));
                }
            }
            if (infos != null && infos.size() > 0) {
                Element infoEle = infos.get(i);
                if (infoEle != null) {
                    comic.setId(getId(infoEle));
                }
            }
            list.add(comic);
        }
        return list;
    }
}
