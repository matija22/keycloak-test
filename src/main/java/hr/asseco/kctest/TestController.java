package hr.asseco.kctest;

import hr.asseco.kctest.model.ResponseWrapper;
import hr.asseco.kctest.model.Tenant;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/roles")
	public ResponseEntity<List<String>> getRoles(final Principal principal) {
		final KeycloakPrincipal<? extends KeycloakSecurityContext> keycloakPrincipal = getKeycloakPrincipal(principal);
		final AccessToken accessToken = keycloakPrincipal.getKeycloakSecurityContext().getToken();
		final List<String> roles = new ArrayList<>(accessToken.getResourceAccess("rasp").getRoles());
		return ResponseEntity.ok(roles);
	}
	
	@GetMapping("/tenants")
	public ResponseEntity<ResponseWrapper<List<Tenant>>> getTenants() {
		final Tenant tenant = new Tenant();
		tenant.setId(1L);
		tenant.setName("Default");
		return ResponseEntity.ok(new ResponseWrapper<>(
				Collections.singletonList(tenant)));
	}
	
	private KeycloakPrincipal<? extends KeycloakSecurityContext> getKeycloakPrincipal(final Principal principal) {
		if (principal == null) {
			
			throw new IllegalStateException();
		}
		if (!(principal instanceof KeycloakAuthenticationToken)) {
			throw new IllegalStateException();
		}
		final KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) principal;
		
		return (KeycloakPrincipal<? extends KeycloakSecurityContext>) token.getPrincipal();
	}
}
