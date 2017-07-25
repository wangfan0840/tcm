package tcm.com.gistone;

import java.io.IOException;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@ServletComponentScan
@MapperScan("tcm.com.gistone")
public class TcmSampleApplication extends SpringBootServletInitializer {

	@RequestMapping(value="/")
    public void home(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.sendRedirect("/index.html");
		return;
    }
	
	@RequestMapping(value="")
    public void home_1(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.sendRedirect("/index.html");
		return;
    }
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TcmSampleApplication.class);
    }
	
	/**
	 * 文件上传临时路径
	 */
	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
	    factory.setLocation("/"); 
	    return factory.createMultipartConfig();
	}
	
	/**
	 * 自定义错误页面
	 * 错误页面需要放在Spring Boot web应用的static内容目录下，它的默认位置是：src/main/resources/static
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	    return new EmbeddedServletContainerCustomizer() {
	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {
//	            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
	            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
	            container.addErrorPages(error404Page, error500Page);
	        }
	    };
	}
	
    public static void main(String[] args) {
        SpringApplication.run(TcmSampleApplication.class, args);
    }
}
