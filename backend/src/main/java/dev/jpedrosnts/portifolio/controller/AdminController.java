package dev.jpedrosnts.portifolio.controller;

import dev.jpedrosnts.portifolio.service.UsuarioService;
import dev.jpedrosnts.portifolio.util.EnviarEmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final UsuarioService service;
	private final EnviarEmailUtil enviarEmailUtil;
	private final String emailAdmin;

	@Autowired
	public AdminController(
			UsuarioService service,
			EnviarEmailUtil enviarEmailUtil,
			@Value("${email.admin}") String emailAdmin
	) {
		this.service = service;
		this.enviarEmailUtil = enviarEmailUtil;
		this.emailAdmin = emailAdmin;
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("nova-senha")
	public String enviarNovaSenha() {
		UUID novaSenha = UUID.randomUUID();
		service.atualizarSenha(emailAdmin, novaSenha.toString());
		enviarEmailUtil.enviar("Nova senha", "Sua nova senha é: " +  novaSenha, new String[] {emailAdmin});
		return "redirect:login";
	}
}
