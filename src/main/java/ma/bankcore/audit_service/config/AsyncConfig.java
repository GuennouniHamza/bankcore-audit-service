package ma.bankcore.audit_service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync // active le support @Async dans tout le projet
public class AsyncConfig {

    private static final Logger log = LoggerFactory.getLogger(AsyncConfig.class);

    @Bean(name = "auditTaskExecutor")
    public Executor auditTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);      // 5 threads toujours actifs
        executor.setMaxPoolSize(10);      // max 10 threads en cas de pic
        executor.setQueueCapacity(25);    // 25 tâches en attente max
        executor.setThreadNamePrefix("Audit-Async-"); // nom des threads dans les logs

        executor.initialize();
        log.info("AuditTaskExecutor initialisé ✅");
        return executor;
    }
}