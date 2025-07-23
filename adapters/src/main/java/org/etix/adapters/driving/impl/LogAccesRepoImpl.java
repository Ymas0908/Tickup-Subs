package org.etix.adapters.driving.impl;



import lombok.AllArgsConstructor;
import org.etix.adapters.driving.repositories.LogAccesRepository;
import org.etix.adapters.driving.repositories.UtilisateurRepository;
import org.etix.adapters.entities.Security.LogAccesEntity;
import org.etix.domain.exceptions.EntityNotExistsException;
import org.etix.domain.exceptions.RequiredParamsNotFoundException;
import org.etix.domain.models.Security.LogAcces;
import org.etix.domain.models.enumerations.TypeLog;
import org.etix.domain.ports.driving.LogAccesRepo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class LogAccesRepoImpl implements LogAccesRepo {

    private final LogAccesRepository logAccesRepository;
    private final UtilisateurRepository utilisateurRepository;
/*
    private final AuthenticationService authenticationService;
*/

    @Override
    public List<LogAcces> getLogAccess() {
        return logAccesRepository.findAll().stream().map(LogAccesEntity::toDomain).toList();
    }

    @Override
    public LogAcces createLogAcces(LogAcces logAcces) {
     /*   Principal principal = authenticationService.getPrincipal();
        Utilisateur utilisateur = utilisateurRepository.getById(principal.getId()).toDomain();
        logAcces.setAuteur(utilisateur.fullName());*/
        logAcces.setCreatedAt(LocalDateTime.now());
        return logAccesRepository.save(LogAccesEntity.toEntity(logAcces)).toDomain();
    }


    @Override
    public void deleteLogAcces(LogAcces logAcces) {
        Optional<LogAcces> optionalLogAcces = logAccesRepository.findById(logAcces.getId()).map(LogAccesEntity::toDomain);
        if (optionalLogAcces.isEmpty()) {
            throw new EntityNotExistsException("Ce log d'acces n'existe pas.");
        }
        logAccesRepository.delete(LogAccesEntity.toEntity(logAcces));
    }

    @Override
    public List<LogAcces> getLogAccessDtoByDateBetweenAndTypeLog(LocalDateTime dateA, LocalDateTime dateB, TypeLog typeLog) {

        if (!hasValidInterval(dateA, dateB)) {
            throw new RequiredParamsNotFoundException("La date de debut et de fin doivent être valides.");
        }
        if (typeLog == null) {
            return logAccesRepository.findLogAccessDtoByDateBetween(dateA, dateB).stream()
                    .map(LogAccesEntity::toDomain)
                    .collect(Collectors.toList());
        } else {
            return logAccesRepository.findLogAccessDtoByDateBetweenAndTypeLog(dateA, dateB, typeLog).stream()
                    .map(LogAccesEntity::toDomain)
                    .collect(Collectors.toList());
        }
    }

    private boolean hasValidInterval(LocalDateTime dateA, LocalDateTime dateB) {
        return dateA != null && dateB != null && (dateA.isEqual(dateB) || dateA.isBefore(dateB));
    }

}
