package dev.jpedrosnts.portifolio.controller;

import dev.jpedrosnts.portifolio.dto.form.AtualizarPerfilForm;
import dev.jpedrosnts.portifolio.model.Usuario;
import dev.jpedrosnts.portifolio.repository.ContatoRepository;
import dev.jpedrosnts.portifolio.service.UsuarioService;
import dev.jpedrosnts.portifolio.util.EnviarEmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsuarioService service;
    private final EnviarEmailUtil enviarEmailUtil;
    private final String emailAdmin;
    private final ContatoRepository contatoRepository;

    @Autowired
    public AdminController(
            UsuarioService service,
            EnviarEmailUtil enviarEmailUtil,
            @Value("${email.admin}") String emailAdmin,
            ContatoRepository contatoRepository) {
        this.service = service;
        this.enviarEmailUtil = enviarEmailUtil;
        this.emailAdmin = emailAdmin;
        this.contatoRepository = contatoRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/perfil")
    public String perfil(Principal principal, Model model, AtualizarPerfilForm atualizarPerfilForm) {
        Usuario usuario = service.findByEmail(principal.getName());
        atualizarPerfilForm.setNome(usuario.getNome());
        model.addAttribute("email", principal.getName());
        return "perfil";
    }

    @GetMapping("/login-error")
    public String login(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("nova-senha")
    public String enviarNovaSenha() {
        UUID novaSenha = UUID.randomUUID();
        service.atualizarSenha(emailAdmin, novaSenha.toString());
        enviarEmailUtil.enviar("Nova senha", "Sua nova senha é: " + novaSenha, new String[]{emailAdmin});
        return "redirect:login";
    }

    @GetMapping("emails")
    public String emails(Model model) {
        model.addAttribute("emails", contatoRepository.findAll());
        return "emails";
    }

    @PostMapping("/perfil")
    public String atualizarPerfil(@Valid AtualizarPerfilForm form, BindingResult result, Principal principal, Model model) {
        model.addAttribute("email", principal.getName());

        Usuario usuario = service.findByEmail(principal.getName());
        boolean senhaValida = new BCryptPasswordEncoder().matches(form.getSenha(), usuario.getSenha());

        if (!senhaValida && !form.getSenha().isEmpty()) {
            FieldError error = new FieldError("atualizarPerfilForm", "senha", "Senha inválida!");
            result.addError(error);
        }

        if (result.hasErrors()) return "perfil";

        usuario.setNome(form.getNome());
        if (!form.getNovaSenha().isEmpty()) usuario.setSenha(new BCryptPasswordEncoder().encode(form.getNovaSenha()));
        service.atualizarUsuario(usuario);

        return "redirect:perfil";
    }
}
