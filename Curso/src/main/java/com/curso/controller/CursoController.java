package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.entities.Curso;
import com.curso.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Curso", description = "API REST DE CURSOS")
@RestController
@RequestMapping("/curso")
public class CursoController {
	
	private final CursoService cursoServ;
	
	@Autowired
    public CursoController(CursoService cursoService) {
        this.cursoServ = cursoService;
    }

	@GetMapping("/{id}")
	@Operation(summary = "Localiza curso por ID")
    public ResponseEntity<Curso> buscaCursoControlId(@PathVariable Long id) {
		Curso curso = cursoServ.buscaCursoId(id); 
        if (curso != null) {
            return ResponseEntity.ok(curso);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@GetMapping("/")
    @Operation(summary = "Apresenta todos os cursos")
    public ResponseEntity<List<Curso>> buscaTodosCursoControl() { 
        List<Curso> curso = cursoServ.buscaTodosCurso();
        return ResponseEntity.ok(curso);

	}
	
	@PostMapping("/")
    public ResponseEntity<Curso> salvaCursoControl(@RequestBody Curso curso) { 
    	Curso salvaCurso = cursoServ.salvaCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaCurso);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera um curso")
    public ResponseEntity<Curso> alteraCursoControl(@PathVariable Long id, @RequestBody Curso curso) { 
    	Curso alteraCurso = cursoServ.alterarCurso(id, curso);
        if (alteraCurso != null) {
            return ResponseEntity.ok(curso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um curso")
    public ResponseEntity<String> apagaCursoControl(@PathVariable Long id) { 
        boolean apagar = cursoServ.apagarCurso(id);
        if (apagar) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
