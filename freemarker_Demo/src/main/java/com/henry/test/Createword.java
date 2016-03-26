package com.henry.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.henry.domain.WordPerson;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Createword {
	private static Configuration cfg = null;
	static{
		cfg = new Configuration();
		cfg.setEncoding(Locale.getDefault(), "UTF-8");
	}
	public static void main(String[] args) throws IOException, TemplateException {
//		cfg.setClassForTemplateLoading(Createword.class,"src/main/resources/template/");
		 //获取模板 
        Template template = cfg.getTemplate("src/main/resources/template/word_template.ftl");
        //输出文件
        
        //构造数据
        Map<String, Object> templateData = new HashMap<String, Object>();
        List<WordPerson> list = Arrays.asList(
         new WordPerson("1", "zhao", 22, "chian"),
         new WordPerson("2", "zhou", 22, "sichuang"),
         new WordPerson("3", "henry", 55, "yunnan"));
        templateData.put("key", list);
        
        StringWriter out = new StringWriter();
		template.process(templateData, out);
		System.out.println( out.getBuffer().toString() );
		out.flush();
        //生成文件
        Writer file = new FileWriter(new File("src/main/resources/output/word_out.doc"));
        template.process(templateData, file);
        file.flush();
        file.close();
       
	}
}
