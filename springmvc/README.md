##spring mvc
### 视图解析器

	`InternalResourceViewResolver`会把返回的视图名称都解析为InternalResourceView对象，InternalResourceView会把Controller处理器方法返回的模型属性都存放到对应的request属性中，然后通过RequestDispatcher在服务器端把请求forword重定向到目标URL。比如在InternalResourceViewResolver中定义了prefix=/WEB-INF/，suffix=.jsp，然后请求的Controller处理器方法返回的视图名称为test，那么这个时候InternalResourceViewResolver就会把test解析为一个InternalResourceView对象，先把返回的模型属性都存放到对应的HttpServletRequest属性中，然后利用RequestDispatcher在服务器端把请求forword到/WEB-INF/test.jsp。这就是InternalResourceViewResolver一个非常重要的特性，我们都知道存放在/WEB-INF/下面的内容是不能直接通过request请求的方式请求到的，为了安全性考虑，我们通常会把jsp文件放在WEB-INF目录下，而InternalResourceView在服务器端跳转的方式可以很好的解决这个问题
```
<bean id="internalResourceViewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<!-- 前缀 -->
		<property name="prefix" value="/WEB-INF/jsp/" />
		<!-- 后缀 -->
		<property name="suffix" value=".jsp" />
	</bean>
```