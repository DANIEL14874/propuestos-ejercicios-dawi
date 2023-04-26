package com.cibertec.propuestosdawi.repository;

import com.cibertec.propuestosdawi.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria,Integer> {
}
