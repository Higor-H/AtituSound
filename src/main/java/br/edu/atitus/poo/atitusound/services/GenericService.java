package br.edu.atitus.poo.atitusound.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import br.edu.atitus.poo.atitusound.entities.GenericEntity;
import br.edu.atitus.poo.atitusound.repositories.GenericRepository;

public interface GenericService<TEntidade extends GenericEntity> {
	
	GenericRepository<TEntidade> getRepository();
	
	default void validate(TEntidade entity) throws Exception {
		if (entity.getName() == null || entity.getName().isEmpty())
			throw new Exception("Campo nome requerido!");
		if (entity.getUuid() == null) {
			if (getRepository().existsByName(entity.getName()))
				throw new Exception("Já exite registro com esse nome!");
		} else {
			if(!getRepository().existsById(entity.getUuid()))
				throw new Exception("Registro não encontrado com esse UUID!");
			if (getRepository().existsByNameAndUuidNot(entity.getName(), entity.getUuid()))
				throw new Exception("Já exite registro com esse nome!");
		}
	}

	default TEntidade save(TEntidade entity) throws Exception {
		validate(entity);
		return getRepository().save(entity);
	}

	default List<TEntidade> findAll() throws Exception {
		return getRepository().findAll();
	}

	default void validateFindByName(String name, Pageable pageable) throws Exception {
		
	}
	
	
	
	default Page<List<TEntidade>> findByName(String name, Pageable pageable ) throws Exception {
		validateFindByName(name, pageable);
		return getRepository().findByNameContainingIgnoreCase(name, pageable );
	}

	default Optional<TEntidade> findById(UUID uuid) {
		return getRepository().findById(uuid);
	}

	default void deleteById(UUID uuid) throws Exception {
		if (!getRepository().existsById(uuid))
			throw new Exception("Não existe registro com esse UUID!");
		getRepository().deleteById(uuid);
		
	}

	
}
