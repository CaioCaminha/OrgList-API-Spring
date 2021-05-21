package caio.caminha.orglist.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import caio.caminha.orglist.models.Usuario;
import caio.caminha.orglist.repositories.UsuarioRepository;

public class AutenticationTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	public AutenticationTokenFilter(TokenService tokenService, UsuarioRepository userRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = userRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		
		boolean valid = tokenService.isTokenValid(token);
		if(valid) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}
	
	public void autenticarCliente(String token) {
		Long userId = tokenService.getUserId(token);
		Usuario user = usuarioRepository.findById(userId).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	public String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token==null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;
		}
		return token.substring(7, token.length());
	}
	
	
}
