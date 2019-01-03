package com.codenotfound.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.PropertyResourceBundle;

/**
 * https://stackoverflow.com/questions/27953261/wildfly-reading-properties-from-configuration-directory
 * https://stackoverflow.com/questions/8285595/reading-properties-file-in-java
 * https://coderanch.com/t/433152/application-servers/Path-JBoss-Server-deployment-folder
 *
 * ( https://www.protechtraining.com/content/jboss_admin_tutorial-directory_structure )
 * ( http://www.mastertheboss.com/jboss-server/jboss-deploy/jboss-deployment-directory-configuration )
 */
public class JmsQueue {

    static {
        final String propFileName = "fasttack-jms-application.properties";
        final String keyProperty = "fasttack.jms.queue.concurrentConsumers";
        int getConcurrentConsumersFromJboss = getConcurrentConsumersFromJboss(propFileName, keyProperty);
        int getConcurrentConsumers = getConcurrentConsumers(propFileName, keyProperty);
        int defaultConcurrentConsumers = 1;

        CONCURRENT_CONSUMERS = getConcurrentConsumersFromJboss!=0
                ? getConcurrentConsumersFromJboss
                : getConcurrentConsumers!=0
                    ? getConcurrentConsumers
                    : defaultConcurrentConsumers;
    }

    public static int CONCURRENT_CONSUMERS; //todo:  by default concurrentConsumers=1

    private static int getConcurrentConsumers(String propFileName, String keyProperty) {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName)) {
            PropertyResourceBundle prop = new PropertyResourceBundle(is);
            String concurrentConsumers = prop.getString(keyProperty);
            return concurrentConsumers != null ? Integer.valueOf(concurrentConsumers) : 0;
        } catch (Exception ex) {
            return 0;
        }
    }

    private static int getConcurrentConsumersFromJboss(String propFileName, String keyProperty) {
        String fileName = System.getProperty("jboss.server.config.dir") + "/" + propFileName;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            Properties prop = new Properties();
            prop.load(fis);
            String concurrentConsumers = prop.getProperty(keyProperty);
            return concurrentConsumers != null ? Integer.valueOf(concurrentConsumers) : 0;
        } catch (IOException ex) {
            return 0;
        }
    }
}
