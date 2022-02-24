/*
package com.stockpredict.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author wangzhewen
 * @time 2022/2/23 10:21
 * @description: TODO
 *//*

@Configuration
public class RestTemplateAutoConfiguration {

    */
/**
     * 连接超时时间
     *//*

    @Value("${rest.connection.timeout}")
    private Integer connectionTimeout;

    */
/**
     * 信息读取超时时间
     *//*

    @Value("${rest.read.timeout}")
    private Integer readTimeout;

    */
/**
     * 声明RestTemplate服务
     * @return RestTemplate
     *//*

    @Bean
    public RestTemplate registerTemplate(ClientHttpRequestFactory simileClientHttpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate();
        //配置自定义的message转换器
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        messageConverters.add(new RestTemplateHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        //配置自定义的interceptor拦截器
        List<ClientHttpRequestInterceptor> interceptors= new ArrayList<>();
        interceptors.add(new HeadClientHttpRequestInterceptor());
        interceptors.add(new TrackLogClientHttpRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        //配置自定义的异常处理
        restTemplate.setErrorHandler(new RestTemplateExceptionHandle());
        restTemplate.setRequestFactory(simileClientHttpRequestFactory);
        return restTemplate;
    }

    */
/**
     * 初始化请求工厂
     * @return SimpleClientHttpRequestFactory
     *//*

    @Bean
    public SimpleClientHttpRequestFactory getFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }

    */
/**
     * 数据转换器
     *//*

    static class RestTemplateHttpMessageConverter extends MappingJackson2HttpMessageConverter {
        public RestTemplateHttpMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            mediaTypes.add(MediaType.TEXT_HTML);  //加入text/html类型的支持
            setSupportedMediaTypes(mediaTypes);// tag6
        }

    }

}
*/
