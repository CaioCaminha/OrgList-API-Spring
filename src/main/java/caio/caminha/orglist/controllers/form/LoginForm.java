package caio.caminha.orglist.controllers.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class LoginForm {
	private String email;
	private String password;
	
	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}
