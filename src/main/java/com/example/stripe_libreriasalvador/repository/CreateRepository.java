package com.example.stripe_libreriasalvador.repository;

import com.example.stripe_libreriasalvador.model.Create;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CreateRepository extends JpaRepository<Create, Long> {
    //Usando Query Method (Keywords)

    boolean existsByEmail(String email);
    @Query("SELECT c FROM Create c WHERE c.name = :name")
    Create findByName(@Param("name") String name);

    @Query("SELECT c FROM Create c WHERE c.email = :email")
    Create findByEmail(@Param("email") String email);


}
