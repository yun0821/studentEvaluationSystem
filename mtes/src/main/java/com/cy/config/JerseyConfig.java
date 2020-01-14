package com.cy.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {

		//控制层类所在的包路径
		packages("com.yskj.controller");

		// 注册 JSON 转换器
		register(JacksonFeature.class);
		//文件上传转换器
		register(MultiPartFeature.class);
	}
}
