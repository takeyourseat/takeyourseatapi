package com.stefanini.internship.oauthserver.config;

import com.stefanini.internship.oauthserver.authorization.OwaPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OwaPermissionEvaluator aclPermissionEvaluator;

    @Autowired
    private ApplicationContext context;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(aclPermissionEvaluator);
        expressionHandler.setApplicationContext(context);
        return expressionHandler;
    }


    @Bean
    public OwaPermissionEvaluator aclPermissionEvaluatorBean(){
        return new OwaPermissionEvaluator(restTemplate);
    }


}
