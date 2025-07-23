package org.etix.domain.ports.driving;



import org.etix.domain.models.Security.LogAcces;
import org.etix.domain.models.enumerations.TypeLog;

import java.time.LocalDateTime;
import java.util.List;

public interface LogAccesRepo {
    /**
     * Retourne la liste des logs d'acces
     *
     * @return
     */

    List<LogAcces> getLogAccess();


    /**
     * Creer un log d'acces
     *
     * @param logAcces
     * @return
     */
    LogAcces createLogAcces(LogAcces logAcces);

    /**
     * Supprime un log d'acces
     *
     * @param logAcces
     */
    void deleteLogAcces(LogAcces logAcces);


    List<LogAcces> getLogAccessDtoByDateBetweenAndTypeLog(LocalDateTime dateA, LocalDateTime dateB, TypeLog typeLog);

}
