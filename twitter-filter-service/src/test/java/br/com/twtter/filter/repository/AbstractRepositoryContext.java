package br.com.twtter.filter.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 
 * @author Fernando
 *
 */

@EnableJpaRepositories
@ExtendWith(SpringExtension.class)
@EntityScan(basePackages = { "br.com.twtter.filter.entity" })
public abstract class AbstractRepositoryContext {

}
