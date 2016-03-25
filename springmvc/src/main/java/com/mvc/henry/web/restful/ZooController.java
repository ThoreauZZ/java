package com.mvc.henry.web.restful;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.henry.entity.Zoo;

@Controller
@RequestMapping("/zooinfo")
public class ZooController {
	private static Logger log = LoggerFactory.getLogger(ZooController.class);
	private static List<Zoo> zoos = new ArrayList<Zoo>();
	
	static{
		zoos.add(new Zoo("1","Xiang Shan","beijing"));
		zoos.add(new Zoo("2","xiao xiao","si chuan"));
		zoos.add(new Zoo("3","Hua Shan","gui zhou"));
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){ 
		log.debug("list zoos --->{}",zoos);
		return "zooinfo";
	}
	
	//GET /zoos：列出所有动物园
	@RequestMapping(value="/zoos",method=RequestMethod.GET)
	@ResponseBody
	public List<Zoo> listZoos(){
		log.debug("list zoos --->{}",zoos);
		return zoos;
	}
	//POST /zoos：新建一个动物园
	@RequestMapping(value="/zoos",method=RequestMethod.POST)
	@ResponseBody
	public List<Zoo> addZoo(Zoo z){
		log.debug("add zoos --->{}",zoos);
		zoos.add(new Zoo(z.getId(),z.getZooName(),z.getAddress()));
		return zoos;
	}
}
