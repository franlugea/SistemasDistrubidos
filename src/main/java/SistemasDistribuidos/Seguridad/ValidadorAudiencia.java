package SistemasDistribuidos.Seguridad;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class ValidadorAudiencia implements OAuth2TokenValidator<Jwt> {
    private final String audiencia;

    ValidadorAudiencia(String expectedAudience) {
        this.audiencia = expectedAudience;
    }

    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        OAuth2Error error = new OAuth2Error("Audiencia invalida", "Falta la audiencia requerida", null);

        if (jwt.getAudience().contains(this.audiencia)) {
            return OAuth2TokenValidatorResult.success();
        }
        return OAuth2TokenValidatorResult.failure(error);
    }
}
