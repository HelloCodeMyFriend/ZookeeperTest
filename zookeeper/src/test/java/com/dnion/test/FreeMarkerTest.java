package com.dnion.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerTest {
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void freeMarkerTest() throws IOException, TemplateException{
				Configuration cfg = null; 
				// 创建一个FreeMarker实例  
				cfg = new Configuration();
			    // 指定模板的加载目录
				cfg.setDirectoryForTemplateLoading(new File("F:\\NginxTemplates"));
				cfg.setDefaultEncoding("UTF-8");
				cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
				
				//获取模板
				Template temp = cfg.getTemplate("nginxA.ftl");
				//获得模板的文件名称
				String fileName = temp.getName();
				//System.out.println(temp.getName());
				
				Map data = new HashMap();
				Map test = new HashMap();
				test.put("name", "liuhaidong");
				test.put("age", 25);
				data.put("person",test);
				
				
				data.put("user", "root");
				data.put("worker_processes", "100");
				data.put("annotation", "#注释:以下为nginx配置文件的配置内容");
				List<String> ipList = new ArrayList<String>();
				ipList.add("192.168.1.1	weight=1");
				ipList.add("192.168.1.2	weight=1");
				ipList.add("192.168.1.3	weight=1");
				
				data.put("ipList",ipList);
				fileName = fileName.substring(0, fileName.lastIndexOf(".") + 1) + "conf";
				FileOutputStream fos = new FileOutputStream(new File("F:/Nginx/" + fileName));
			    Writer out = new OutputStreamWriter(fos);
			    
			    temp.process(data, out);
	}
}
