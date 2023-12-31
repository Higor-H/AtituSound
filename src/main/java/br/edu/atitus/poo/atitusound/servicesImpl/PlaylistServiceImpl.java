package br.edu.atitus.poo.atitusound.servicesImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.atitus.poo.atitusound.entities.PlaylistEntity;
import br.edu.atitus.poo.atitusound.entities.UserEntity;
import br.edu.atitus.poo.atitusound.repositories.GenericRepository;
import br.edu.atitus.poo.atitusound.repositories.PlaylistRepository;
import br.edu.atitus.poo.atitusound.services.PlaylistService;

@Service
public class PlaylistServiceImpl implements PlaylistService{

	private final PlaylistRepository repository;
	
	public PlaylistServiceImpl(PlaylistRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public GenericRepository<PlaylistEntity> getRepository() {
		return repository;
	}

	@Override
	public void validate(PlaylistEntity entity) throws Exception {
		PlaylistService.super.validate(entity);
		UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		entity.setUser(user);
	}

	/*@Override
	public Page<List<PlaylistEntity>> findByName(String name, Pageable pageable) throws Exception {
		return PlaylistService.super.findByName(name, pageable);
	}*/
	
	/*
	@Override
	public Page<List<PlaylistEntity>> findByNameContainingIgoreCase(String name, Pageable pageable) throws Exception {
		
		UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return repository.findByNameContainingIgnoreCaseAndUserOrPublicshare(name, user, true, pageable);
	}
	*/
	
	@Override
	public Page<List<PlaylistEntity>> findByName(String name, Pageable pageable) throws Exception {
		
		UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return repository.findByNameContainingIgnoreCaseAndUserOrPublicshare(name, user, true, pageable);
	}
	
	
	

}
