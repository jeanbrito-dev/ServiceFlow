package com.colonia.backend.Database.Repository;

import com.colonia.backend.Database.Entitty.ChamadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<ChamadoEntity, Integer> {


}
