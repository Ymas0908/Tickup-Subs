package org.etix.adapters.driving.repositories;




import org.etix.adapters.entities.Security.LogAccesEntity;
import org.etix.domain.models.enumerations.TypeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ITCENTREX
 */

@Repository
public interface LogAccesRepository extends JpaRepository<LogAccesEntity, Integer> {

    @Query("SELECT l FROM LogAccesEntity l WHERE DATE(l.createdAt) between DATE(:dateA) AND DATE(:dateB) AND l.type=:typeLog ORDER BY l.createdAt DESC")
    List<LogAccesEntity> findLogAccessDtoByDateBetweenAndTypeLog(@Param("dateA") LocalDateTime dateA,
                                                                 @Param("dateB") LocalDateTime dateB, @Param("typeLog") TypeLog typeLog);

    @Query("SELECT l FROM LogAccesEntity l WHERE DATE(l.createdAt) between DATE(:dateA) AND DATE(:dateB) ORDER BY l.createdAt DESC")
    List<LogAccesEntity> findLogAccessDtoByDateBetween(@Param("dateA") LocalDateTime dateA, @Param("dateB") LocalDateTime dateB);

}