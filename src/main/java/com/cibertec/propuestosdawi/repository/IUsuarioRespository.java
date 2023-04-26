package com.cibertec.propuestosdawi.repository;

import com.cibertec.propuestosdawi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRespository extends JpaRepository<Usuario,Integer> {

    Optional<Usuario> findByUsernameAndPassword(String username,String password);
}
