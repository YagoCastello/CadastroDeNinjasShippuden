package dev.java10x.CadastroDeNinjasShippuden.Missoes;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    // Get -- Mandar uma requisição para mostrar as missoes
    @GetMapping("/listar")
    public  String listarMissao(){
        return  "Missoes listadas com sucesso";
    }

    // POST-- Mandar uma requisição para CRIAR as missoes
    @PostMapping("/criar")
    public String criarMissao(){
        return  "Missao criada com sucesso";
    }

    // PUT -- Mandar uma requisição para alterar as missoes
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada com sucesso";
    }

    // DELETE -- Mandar uma requisição para deletar uma missão
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missao deletada com sucesso";
    }
}
