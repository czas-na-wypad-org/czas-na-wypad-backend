package sggw.wzim.czasnawypad;

import sggw.wzim.czasnawypad.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class CzasNaWypadBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CzasNaWypadBackendApplication.class, args);
    }
}
