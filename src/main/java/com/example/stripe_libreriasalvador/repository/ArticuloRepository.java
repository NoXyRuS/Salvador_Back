package com.example.stripe_libreriasalvador.repository;

import com.example.stripe_libreriasalvador.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo,Long>{

}
