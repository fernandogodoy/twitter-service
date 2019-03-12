package br.com.twtter.filter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.twtter.filter.entity.BaseEntity;

/**
 * Generic Repository
 * 
 * @author Fernando
 *
 */
@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

}
