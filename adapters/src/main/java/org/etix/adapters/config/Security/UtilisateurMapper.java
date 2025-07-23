package org.etix.adapters.config.Security;

import com.itcentrex.adapter.entities.Security.UtilisateurEntity;
import org.modelmapper.ModelMapper;

public class UtilisateurMapper {

    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
    }

    public static UtilisateurDto mapToDto(UtilisateurEntity utilisateur) {

        return modelMapper.map(utilisateur, UtilisateurDto.class);
    }

}
