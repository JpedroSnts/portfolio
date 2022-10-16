package dev.jpedrosnts.portifolio.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jpedrosnts.portifolio.dto.EmailForm;

@RestController
@RequestMapping("/mock")
public class MockController {

    @GetMapping("home")
    public ResponseEntity<String> home() {
        String json = "{\"name\":[\"João\",\"Pedro\"],\"skills\":[\"Java\",\"C#\",\"React\"]}";
        return ResponseEntity.ok(json);
    }

    @GetMapping("about")
    public ResponseEntity<String> about() {
        String json = "{\"image\":\"https://github.com/jpedrosnts.png\",\"name\":[\"João Pedro\",\"de Siqueira Santos\"],\"skills\":[\"Java\",\"C#\",\"React\"],\"text\":\"Olá meu nome é João Pedro, tenho 17 anos, moro em São Vicente(Litoral de São Paulo) e atualmente estou cursando técnico em desenvolvimento de sistemas integrado ao ensino médio, ao entrar neste curso a cerca de 1 ano eu não fazia ideia do que iria fazer, e então me apaixonei por este mundo, o que me faz sempre querer aprender coisas novas.\"}";
        return ResponseEntity.ok(json);
    }

    @GetMapping("contato")
    public ResponseEntity<String> contato() {
        String json = "[{\"icon\":\"/icons/linkedin.svg\",\"link\":\"https://www.linkedin.com/in/jpedrosnts\",\"name\":\"LinkedIn\"},{\"icon\":\"/icons/github.svg\",\"link\":\"https://github.com/JpedroSnts\",\"name\":\"GitHub\"},{\"icon\":\"/icons/email.svg\",\"link\":\"mailto:jp.siq.snts@gmail.com\",\"name\":\"Email\"},{\"icon\":\"/icons/instagram.svg\",\"link\":\"https://www.instagram.com/jpedrosnts\",\"name\":\"Instagram\"}]";
        return ResponseEntity.ok(json);
    }

    @GetMapping("certificados")
    public ResponseEntity<String> certificados() {
        String json = "[{\"id\":1,\"name\":\"Forma\u00E7\u00E3o C# e Orienta\u00E7\u00E3o a Objetos\",\"icon\":\"https://www.svgrepo.com/show/373533/csharp2.svg\",\"technology\":{\"id\":\"csharp\",\"name\":\"C#\",\"icon\":\"https://www.svgrepo.com/show/373533/csharp2.svg\"},\"link\":\"https://cursos.alura.com.br/degree/certificate/ec08527e-fe14-4faa-adca-79e02e6b229f\"},{\"id\":2,\"name\":\"Curso React + Redux: Fundamentos e 2 Apps do Absoluto ZERO!\",\"icon\":\"https://www.svgrepo.com/show/354259/react.svg\",\"technology\":{\"id\":\"reactjs\",\"name\":\"React.js\",\"icon\":\"https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original.svg\"},\"link\":\"https://www.udemy.com/certificate/UC-854a247b-a700-45a4-9eab-d1a247550f54/\"},{\"id\":3,\"name\":\"Forma\u00E7\u00E3o Java e Orienta\u00E7\u00E3o a Objetos\",\"icon\":\"https://www.svgrepo.com/show/303388/java-4-logo.svg\",\"technology\":{\"id\":\"java\",\"name\":\"Java\",\"icon\":\"https://www.svgrepo.com/show/303388/java-4-logo.svg\"},\"link\":\"https://cursos.alura.com.br/degree/certificate/936ea4b3-1d4c-4ded-ba40-70418cf9f959\"}]";
        return ResponseEntity.ok(json);
    }

    @GetMapping("tecnologias")
    public ResponseEntity<String> tecnologias() {
        String json = "[{\"id\":\"java\",\"name\":\"Java\",\"icon\":\"https://www.svgrepo.com/show/303388/java-4-logo.svg\"},{\"id\":\"csharp\",\"name\":\"C#\",\"icon\":\"https://www.svgrepo.com/show/373533/csharp2.svg\"},{\"id\":\"reactjs\",\"name\":\"React.js\",\"icon\":\"https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original.svg\"}]";
        return ResponseEntity.ok(json);
    }

    @GetMapping("projetos")
    public ResponseEntity<String> projetos() {
        String json = "[{\"id\":1,\"name\":\"Rede Social\",\"description\":\"Projeto de rede Social feito em Java com Spring\",\"technologies\":[{\"id\":\"spring-boot\",\"name\":\"Spring Boot\",\"icon\":\"https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg\"}],\"image\":\"https://blog.geekhunter.com.br/wp-content/uploads/2019/10/spring-boot.png\",\"link\":\"https://github.com/jpedrosnts\",\"type\":{\"id\":1,\"name\":\"Backend\"}},{\"id\":2,\"name\":\"Blog\",\"description\":\"Blog feito com Next.js\",\"technologies\":[{\"id\":\"nextjs\",\"name\":\"Next.js\",\"icon\":\"https://cdn.jsdelivr.net/gh/devicons/devicon/icons/nextjs/nextjs-original.svg\"},{\"id\":\"dato-cms\",\"name\":\"Dato CMS\",\"icon\":\"https://www.svgrepo.com/show/353637/datocms-icon.svg\"}],\"image\":\"https://blog.codeminer42.com/wp-content/uploads/2021/02/nextjs-cover.jpg\",\"link\":\"https://github.com/jpedrosnts/jpedrosnts-blog\",\"type\":{\"id\":2,\"name\":\"Frontend\"}},{\"id\":3,\"name\":\"PokeCord\",\"description\":\"Chat feito em React.js usando supabase\",\"technologies\":[{\"id\":\"reactjs\",\"name\":\"React.js\",\"icon\":\"https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original.svg\"},{\"id\":\"supabase\",\"name\":\"Supabase\",\"icon\":\"https://www.svgrepo.com/show/233208/bolt-thunder.svg\"}],\"image\":\"https://d2gdtie5ivbdow.cloudfront.net/media/images/article_images/Jaysha/supabase_react_cover.png\",\"link\":\"https://github.com/jpedrosnts/pokecord\",\"type\":{\"id\":3,\"name\":\"FullStack\"}}]";
        return ResponseEntity.ok(json);
    }

    @PostMapping("email")
    public ResponseEntity<Void> email(@RequestBody @Valid EmailForm form) {
        System.out.println(form);
        return ResponseEntity.noContent().build();
    }
}
