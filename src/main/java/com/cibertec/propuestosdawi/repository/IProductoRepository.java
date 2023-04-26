package com.cibertec.propuestosdawi.repository;

import com.cibertec.propuestosdawi.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto,String> {
}
