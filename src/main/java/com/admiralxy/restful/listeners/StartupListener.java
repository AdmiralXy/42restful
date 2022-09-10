package com.admiralxy.restful.listeners;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Component
@AllArgsConstructor
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private final EntityManager entityManager;

    private final DataSource dataSource;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        Long obj = (Long) entityManager.createQuery("SELECT count(id) FROM User").getSingleResult();
        if (obj == 0) {
            ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
            rdp.addScript(new ClassPathResource("sql/data.sql"));
            rdp.execute(dataSource);
        }
    }

}
