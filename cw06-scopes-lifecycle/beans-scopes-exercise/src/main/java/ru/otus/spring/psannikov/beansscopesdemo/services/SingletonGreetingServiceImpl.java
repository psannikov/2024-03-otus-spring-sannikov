package ru.otus.spring.psannikov.beansscopesdemo.services;

import org.springframework.stereotype.Service;

@Service("singletonGreetingService")
public class SingletonGreetingServiceImpl extends AbstractGreetingServiceImpl {
}
