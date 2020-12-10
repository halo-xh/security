package com.xh.config.mp;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * AOP 事务 配置
 */
@Configuration
@EnableAspectJAutoProxy
public class TxAdviceInterceptor {
	
	private static final String AOP_POINTCUT_EXPRESSION = "execution(* *..service.*Service+.*(..))";
    
	@Resource
	private DataSourceTransactionManager transactionManager;
    
    @Bean
    public TransactionInterceptor txAdvice(){
    	NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
    	
    	RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
    	readOnlyTx.setReadOnly(true);
    	
    	RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
    	requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	
    	
    	Map<String, TransactionAttribute> txMap = new HashMap<>();
    	txMap.put("get*", readOnlyTx);
        txMap.put("find*", readOnlyTx);
        txMap.put("prepare**", readOnlyTx);
        txMap.put("*", requiredTx);
        source.setNameMap(txMap);
		return new TransactionInterceptor(transactionManager, source);
      
    }
    
    @Bean
    public Advisor txAdviceAdvisor(){
    	AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    	pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
    	return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}