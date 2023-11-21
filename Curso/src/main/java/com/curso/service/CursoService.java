package com.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.entities.Curso;
import com.curso.repository.CursoRepository;

@Service
public class CursoService {
	
	private final CursoRepository cursoRepo;
	
	@Autowired
    public CursoService(CursoRepository cursoRepo) {
        this.cursoRepo = cursoRepo;
    }

	public List<Curso> buscaTodosCurso(){
        return cursoRepo.findAll();
    }

    public Curso buscaCursoId(Long id) {
        Optional <Curso> Curso = cursoRepo.findById(id);
        return Curso.orElse(null);
    }
    
    public Curso salvaCurso (Curso curso) {
        return cursoRepo.save(curso);
    }

    public Curso alterarCurso(Long id, Curso alterarCurso) {
        Optional <Curso> existeCurso = cursoRepo.findById(id);
        if (existeCurso.isPresent()) {
            alterarCurso.setId(id);
            return cursoRepo.save(alterarCurso);
        }
        return null;
    }
    
    public boolean apagarCurso (Long id) {
        Optional <Curso> existeCurso = cursoRepo.findById(id);
        if (existeCurso.isPresent()) {
        	cursoRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
