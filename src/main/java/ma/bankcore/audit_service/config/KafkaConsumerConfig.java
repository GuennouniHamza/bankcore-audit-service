package ma.bankcore.audit_service.config;

import ma.bankcore.audit_service.dto.ClientCreeEvent;
import ma.bankcore.audit_service.dto.CompteBloqueEvent;
import ma.bankcore.audit_service.dto.VirementEffectueEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    // Config commune à tous les consumers
    private Map<String, Object> consumerConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return config;
    }

    // Factory pour VirementEffectueEvent
    @Bean
    public ConsumerFactory<String, VirementEffectueEvent> virementConsumerFactory() {
        JsonDeserializer<VirementEffectueEvent> deserializer =
            new JsonDeserializer<>(VirementEffectueEvent.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(
            consumerConfig(),
            new StringDeserializer(),
            deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, VirementEffectueEvent>
            kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, VirementEffectueEvent> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(virementConsumerFactory());
        return factory;
    }

    // Factory pour ClientCreeEvent
    @Bean
    public ConsumerFactory<String, ClientCreeEvent> clientConsumerFactory() {
        JsonDeserializer<ClientCreeEvent> deserializer =
            new JsonDeserializer<>(ClientCreeEvent.class);
        deserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(
            consumerConfig(),
            new StringDeserializer(),
            deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ClientCreeEvent>
            clientKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ClientCreeEvent> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(clientConsumerFactory());
        return factory;
    }

    // Factory pour CompteBloqueEvent
    @Bean
    public ConsumerFactory<String, CompteBloqueEvent> compteConsumerFactory() {
        JsonDeserializer<CompteBloqueEvent> deserializer =
            new JsonDeserializer<>(CompteBloqueEvent.class);
        deserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(
            consumerConfig(),
            new StringDeserializer(),
            deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CompteBloqueEvent>
            compteKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CompteBloqueEvent> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(compteConsumerFactory());
        return factory;
    }
}