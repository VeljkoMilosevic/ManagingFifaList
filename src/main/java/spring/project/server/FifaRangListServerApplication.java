package spring.project.server;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories("spring.project.server.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "spring.project.server.model")
@EnableAspectJAutoProxy()
@Profile("dev")
@EnableWebSecurity
@ConfigurationPropertiesScan(basePackages = "spring.project.server.security.PasswordConfig")
public class FifaRangListServerApplication {

    public static void main(final String[] args) {
        final String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        SpringApplication.run(FifaRangListServerApplication.class, args);
    }

}
