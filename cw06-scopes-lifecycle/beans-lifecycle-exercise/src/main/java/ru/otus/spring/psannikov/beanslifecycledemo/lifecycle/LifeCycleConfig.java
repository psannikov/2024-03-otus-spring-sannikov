package ru.otus.spring.psannikov.beanslifecycledemo.lifecycle;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(CustomBeanDefinitionRegistrar.class)
@Configuration
public class LifeCycleConfig {
/*
    @Bean
    public BeanFactoryPostProcessor customBeanFactoryPostProcessor() {
        return new CustomBeanFactoryPostProcessor();
    }

    @Bean
    public BeanPostProcessor customBeanPostProcessor() {
        return new CustomBeanPostProcessor();
    }

    @ConditionalOnProperty(name = "lifecycle.print.enabled", havingValue = "true")
    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public CustomLifeCycleBean customLifeCycleBean() {
        return new CustomLifeCycleBean();
    }
*/
}
