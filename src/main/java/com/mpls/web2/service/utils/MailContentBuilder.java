package com.mpls.web2.service.utils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

@Service
public class MailContentBuilder {
    @Autowired
    @Qualifier("freemarkerConf")
    private Configuration freemarkerConfiguration;

    public String getFreeMarkerTemplateContent(String templateName, Map<String, Object> model) {
        StringBuilder content = new StringBuilder();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate(templateName), model));
            return content.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return "";
    }
}