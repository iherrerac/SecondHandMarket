package com.secondhandmarket.spring.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing

/*
 * Las entidades marcados con @EntityListener y atributos @CreateDate y @Temporal, 
 * se le insertará la marca de tiempo del sistema automaticamente con el @EnableJpaAuditing
 * 
 */

public class ConfiguracionAuditoria {

}
