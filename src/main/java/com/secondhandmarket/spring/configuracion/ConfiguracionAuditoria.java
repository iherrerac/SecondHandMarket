package com.secondhandmarket.spring.configuracion;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.secondhandmarket.spring.upload.StorageProperties;

@Configuration
@EnableJpaAuditing

/*
 * Las entidades marcados con @EntityListener y atributos @CreateDate y @Temporal, 
 * se le insertará la marca de tiempo del sistema automaticamente con el @EnableJpaAuditing
 * 
 */
@EnableConfigurationProperties(StorageProperties.class) // Habilitamos la configuracion para el almacenamiento
public class ConfiguracionAuditoria {

}
