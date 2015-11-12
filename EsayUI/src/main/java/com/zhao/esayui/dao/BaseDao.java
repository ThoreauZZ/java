package com.zhao.esayui.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>描述信息：基本数据访问对象</p>
 * @author henry.zhao
 * @version v1.0 2015年11月12日 上午8:46:47
 */
public class BaseDao {
	
	@Autowired
	protected SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
}
