package org.etix.adapters.config.Security;


import java.io.IOException;

public interface AuthenticationService {

    boolean isAuthenticated();

    boolean login(String username, String password);

    UtilisateurDto getPrincipal();

    /**
     * @param password
     * @param newPassword
     * @param passwordConfirmed
     * @return
     */
    boolean updatePassword(String password, String newPassword, String passwordConfirmed);

    /**
     * @param email
     * @return
     * @throws IOException
     **/
    boolean initPasswordResetRequest(String email) throws Exception;

    /**
     * @param password
     * @param password2
     * @param token
     * @return
     * @throws IOException
     */
    boolean confirmPasswordResetRequest(String password, String password2, String token) throws Exception;
}
