package com.codenotfound.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import java.net.SocketOptions;

@Configuration
public class ClientConfig {

  @Value("${client.default-uri}")
  private String defaultUri;

  @Bean
  Jaxb2Marshaller jaxb2Marshaller() {
    Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
    jaxb2Marshaller.setContextPath("com.codenotfound.types.helloworld");

    return jaxb2Marshaller;
  }

//  @Bean
//  public WebServiceTemplate webServiceTemplate() {
//    WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
//    webServiceTemplate.setMarshaller(jaxb2Marshaller());
//    webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
//    webServiceTemplate.setDefaultUri(defaultUri);
//
//    return webServiceTemplate;
//  }

  @Bean
  public WebServiceTemplate webServiceTemplate() {
    SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory();
    messageFactory.setSoapVersion(SoapVersion.SOAP_11);
    messageFactory.afterPropertiesSet();

    WebServiceMessageSender sender = new HttpComponentsMessageSender(httpClient());

    WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
    webServiceTemplate.setMarshaller(jaxb2Marshaller());
    webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
    webServiceTemplate.setDefaultUri(defaultUri);
    webServiceTemplate.setMessageFactory(messageFactory);
    webServiceTemplate.setMessageSender(sender);

    return webServiceTemplate;
  }

  @Bean
  public HttpClient httpClient() {
    Registry<ConnectionSocketFactory> csf = RegistryBuilder.<ConnectionSocketFactory>create()
            .register("http", PlainConnectionSocketFactory.INSTANCE)
            .build();

    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(csf);
    cm.setMaxTotal(2000);
    cm.setDefaultMaxPerRoute(200);
    SocketConfig socketConfig = SocketConfig.custom()
            /* Determines the maximum queue length for incoming connection indications
             * (a request to connect) also known as server socket backlog.*/
            //.setBacklogSize(100)
            /* Определяет значение по умолчанию параметра SocketOptions.SO_RCVBUF для вновь создаваемых сокетов. */
//            .setRcvBufSize(SocketOptions.SO_RCVBUF)
            /* Determines the default value of the SocketOptions.SO_SNDBUF
             * parameter for newly created sockets. */
            //.setSndBufSize(SocketOptions.SO_SNDBUF)
            /* Determines the default value of the SocketOptions.SO_LINGER
             * parameter for newly created sockets. */
            //.setSoLinger(SocketOptions.SO_LINGER)
            /* Определяет значение по умолчанию параметра SocketOptions.SO_REUSEADDR для вновь создаваемых сокетов. */
//            .setSoReuseAddress(false)
            /* Определяет значение SocketOptions.SO_TIMEOUT времени ожидания сокета по умолчанию для неблокирующих операций ввода-вывода. */
            .setSoTimeout(5000)
            /* Определяет значение по умолчанию параметра SocketOptions.TCP_NODELAY для вновь создаваемых сокетов. */
//            .setTcpNoDelay(false)
            .build();

    int connTimeoutMilis = 5000;
    RequestConfig reqConfig = RequestConfig.custom()
            /* Тайм-аут в миллисекундах, используемый при запросе соединения из диспетчера соединений. */
            .setConnectionRequestTimeout(connTimeoutMilis)
            /* Determines the timeout in milliseconds until a connection is established. */
            .setConnectTimeout(connTimeoutMilis)
            .build();

    /**
     * Http client sets default Content-Length etc headers on immutable object. Setting headers
     * later in the WebServiceTemplate again result in exception. HttpRequestInterceptor
     * interceptor is added to prevent apache client from setting any default headers.
     * @see https://jira.spring.io/browse/SWS-835
     */
    HttpRequestInterceptor noHeader = new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor();

    CloseableHttpClient client = HttpClients.custom()
            .setConnectionManager(cm)
            .setDefaultSocketConfig(socketConfig)
            .addInterceptorFirst(noHeader)
            .setDefaultRequestConfig(reqConfig)
            .build();
    return client;
  }
}
