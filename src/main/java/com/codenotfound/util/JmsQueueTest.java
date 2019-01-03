//package com.codenotfound.util;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.Properties;
//
//public class JmsQueueTest {
//
////    @PropertySource(value = "classpath:fasttack-core-application.properties", ignoreResourceNotFound = true, factory = DecryptingPropertySourceFactory.class)
////    @PropertySource(value = "file:${catalina.home}/conf/fasttack-core-application.properties", ignoreResourceNotFound = true, factory = DecryptingPropertySourceFactory.class)
////    @PropertySource(value = "file:${jboss.home.dir}/standalone/configuration/fasttack-core-application.properties", ignoreResourceNotFound = true, factory = DecryptingPropertySourceFactory.class)
////    @PropertySource(value = "file:${fasttack.home.dir}/conf/fasttack-core-application.properties", ignoreResourceNotFound = true, factory = DecryptingPropertySourceFactory.class)
//
//    public static void main(String[] args) throws IOException {
//        System.out.println( "CONCURRENT_CONSUMERS = " + JmsQueue.CONCURRENT_CONSUMERS );
//
////        System.out.println( "catalina.home = " + System.getProperty("catalina.home") );
////        System.out.println( "jboss.home.dir = " + System.getProperty("jboss.home.dir") );
////        System.out.println( "fasttack.home.dir = " + System.getProperty("fasttack.home.dir") );
//
//        URL url = Thread.currentThread().getContextClassLoader().getResource("fasttack-jms-application.properties"); //todo:  file:/home/alexandr/devel/IdeaProjects/demo5/spring-bean-scopes-prototype/target/classes/fasttack-jms-application.properties
//        System.out.println(url);
//
//        Properties prop = new Properties();
//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        InputStream stream = loader.getResourceAsStream("_fasttack-jms-application.properties");
//        System.out.println(stream);
////        prop.load(stream);
//    }
//
//}
