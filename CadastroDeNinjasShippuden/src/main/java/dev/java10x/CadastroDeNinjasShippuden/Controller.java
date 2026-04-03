package dev.java10x.CadastroDeNinjasShippuden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    @GetMapping("/boasvindas")
    public String Bemvindos(){
        return "Seja bem vindo!!";
    }

}
