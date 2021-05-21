package caio.caminha.orglist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import caio.caminha.orglist.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticationController {
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenService tokenService;
}
