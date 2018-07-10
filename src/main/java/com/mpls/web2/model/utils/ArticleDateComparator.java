package com.mpls.web2.model.utils;

import com.mpls.web2.model.Article;

import java.util.Comparator;

public class ArticleDateComparator implements Comparator<Article> {
    @Override
    public int compare(Article o1, Article o2) {
        if (o1 == null || o2 == null)
            return 0;
        if(o1.getDatetime()==null || o2.getDatetime()==null)
            return 0;
        if (o1.getDatetime().toLocalDateTime().isBefore(o2.getDatetime().toLocalDateTime())) {
            return 1;
        } else if (o1.getDatetime().toLocalDateTime().isAfter(o2.getDatetime().toLocalDateTime()))
            return -1;
        return 0;
    }
}
