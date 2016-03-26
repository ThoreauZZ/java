package com.henry.test;


import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.henry.domain.Book;
import com.henry.domain.Person;

import freemarker.template.Configuration;
import freemarker.template.Template;
 
public class FreeMarkerGenrateXMLFromObject {
	
	private static Configuration cfg = null;
	
	static{		
		// Freemarker below configuration object deprecated
		//Configuration cfg = new Configuration();
		//Please use this. To make it backward compatible. Please visit here for more info: 
		//http://freemarker.org/docs/api/freemarker/template/Configuration.html
		cfg = new Configuration();
		cfg.setEncoding(Locale.getDefault(), "UTF-8");
	}
	
	public static void main(String[] args) {
 
		try {
			// Load template
			Template template = cfg.getTemplate("src/main/resources/template/xml_templete.ftl");
			// Create data for template
			Map<String, Object> templateData = new HashMap<String, Object>();
			templateData.put("name", "二道岩");
			
			List<Person> personDetails = Arrays.asList(
				       new Person( "二道岩", "贵州" ), 
				       new Person( "二道岩", "四川" ) );
 
			templateData.put("personDetails", personDetails);
			List<Book> books = Arrays.asList(
				       new Book( "聊斋志异", 22.00 ), 
				       new Book( "棋王",32.00  ) );
			templateData.put("Book",books);
			
			// Write output on console example 1
			StringWriter out = new StringWriter();
			template.process(templateData, out);
			System.out.println( out.getBuffer().toString() );
			out.flush();
			
			// Write output on console example 2
			/*Writer out = new OutputStreamWriter(System.out);
			template.process(templateData, out);
			out.flush();*/
 
			// Write data to the file
			Writer file = new FileWriter(new File("src/main/resources/output/outXML.xml"));
			template.process(templateData, file);
			file.flush();
			file.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}