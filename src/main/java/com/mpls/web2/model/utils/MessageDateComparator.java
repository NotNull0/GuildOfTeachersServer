package com.mpls.web2.model.utils;

import com.mpls.web2.model.ForumMessage;

import java.util.Comparator;

public class MessageDateComparator implements Comparator<ForumMessage> {
    @Override
    public int compare(ForumMessage o1, ForumMessage o2) {
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