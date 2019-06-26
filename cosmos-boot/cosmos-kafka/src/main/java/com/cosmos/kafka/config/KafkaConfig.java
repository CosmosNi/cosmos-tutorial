package com.cosmos.kafka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.kafka.config
 * @ClassName: KafkaConfig
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/26 10:16
 * @Version: 1.0
 */
@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 批量消费,如果需要单个单个消费，则将此方法注释，不然会报错
     *
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory,
            KafkaTemplate<Object, Object> template) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        factory.setBatchListener(true);
        factory.setMessageConverter(batchConverter());
        return factory;
    }
    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    /**
     * 批量更新时的序列化
     *
     * @return
     */
    @Bean
    public BatchMessagingMessageConverter batchConverter() {
        return new BatchMessagingMessageConverter(converter());
    }
}
