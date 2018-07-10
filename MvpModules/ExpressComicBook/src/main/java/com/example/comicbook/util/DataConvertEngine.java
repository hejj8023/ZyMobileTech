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
        List<Element> elements = getaElementsByAttr(document, "class", "in-anishe-text");
        Random random = new Random();
        int result = random.nextInt(5);
        Comic comic = null;
        for (int i = (result * 6); i < (result + 1); i++) {
            comic = new Comic();
            Element element1 = elements.get(i);
            comic.setTile(getAttr(element1, "a", "title"));
            comic.setCover(getAttr(element1, "img", "data-original"));

            Elements elementDescribes = getaElementsByAttr(document, "class",
                    "mod-cover-list-intro");
            if (elementDescribes != null && elementDescribes.size() > 0) {
                Element element = elementDescribes.get(0);
                comic.setDescribe(element.select("p").text());
                comic.setId(Long.parseLong(getID(getAttr(element, "a", "href"))));
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
    private static Elements getaElementsByAttr(Document document, String key, String value) {
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
        List<Element> elements = getaElementsByAttr(document, "class", "in-anishe-list clearfix " +
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
            comic.setCover(getAttr(element1, "img", "data-original"));

            Elements elementDescribes = getaElementsByAttr(document, "class",
                    "mod-cover-list-intro");
            if (elementDescribes != null && elementDescribes.size() > 0) {
                Element subEle = elementDescribes.get(0);
                comic.setDescribe(subEle.select("p").text());
                comic.setId(Long.parseLong(getID(getAttr(element1, "a", "href"))));
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
        List<Element> elements = getaElementsByAttr(document, "class", "in-teen-list " +
                "mod-cover-list clearfix");

        Element element = elements.get(0);

        List<Element> boys = element.getElementsByTag("li");
        Element girlEle;
        Comic comic = null;

        for (int i = 0; i < boys.size(); i++) {
            girlEle = boys.get(i);
            comic = new Comic();
            comic.setTile(getAttr(girlEle, "img", "alt"));
            comic.setCover(getAttr(girlEle, "img", "data-original"));

            Elements elementDescribes = getaElementsByAttr(document, "class",
                    "mod-cover-list-intro");
            if (elementDescribes != null && elementDescribes.size() > 0) {
                Element describesElement = elementDescribes.get(0);
                comic.setDescribe(describesElement.select("p").text());
                comic.setId(Long.parseLong(getID(getAttr(girlEle, "a", "href"))));
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
        List<Element> elements = getaElementsByAttr(document, "class", "in-girl-list " +
                "mod-cover-list clearfix");

        Element element = elements.get(0);
        List<Element> girls = element.getElementsByTag("li");
        Element girlEle;
        Comic comic = null;
        for (int i = 0; i < girls.size(); i++) {
            girlEle = girls.get(i);
            comic = new Comic();
            comic.setTile(getAttr(girlEle, "img", "alt"));
            comic.setCover(getAttr(girlEle, "img", "data-original"));

            Elements elementDescribes = getaElementsByAttr(document, "class",
                    "mod-cover-list-intro");
            if (elementDescribes != null && elementDescribes.size() > 0) {
                Element describesElement = elementDescribes.get(0);
                comic.setDescribe(describesElement.select("p").text());
                comic.setId(Long.parseLong(getID(getAttr(girlEle, "a", "href"))));
                list.add(comic);
            }
        }
        return list;
    }

    public static List<Comic> convertJapanData(Document document) {
        List<Comic> list = new ArrayList<>();

        return list;
    }

    public static List<Comic> convertRankListData(Document document) {
        List<Comic> list = new ArrayList<>();

        return list;
    }
}
