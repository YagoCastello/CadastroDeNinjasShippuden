package dev.java10x.CadastroDeNinjasShippuden.Missoes;
import dev.java10x.CadastroDeNinjasShippuden.Ninjas.NinjaModel;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    private MissaoService missaoService;

    public MissoesController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }


    // Get -- Mandar uma requisição para mostrar as missoes
    @GetMapping("/listar")
    public List<MissaoModel> listarMissao()
    {
        return missaoService.listarMissoes();
    }


    //Mostrar missao por id
    @GetMapping("/listar/{id}")
    public MissaoModel listarMissoesPorId(@PathVariable Long id){
        return missaoService.listarMissoesPorId(id);
    }

    // POST-- Mandar uma requisição para CRIAR as missoes
    @PostMapping("/criar")
    public MissaoModel criarMissao(@RequestBody MissaoModel missaoModel){

        return  missaoService.criarMissao(missaoModel);
    }

    // PUT -- Mandar uma requisição para alterar as missoes
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada com sucesso";
    }

    // DELETE -- Mandar uma requisição para deletar uma missão
    @DeleteMapping("/deletar")
    public void deletarMissao(@PathVariable Long id){
        missaoService.deletarMissaoPorId(id);
    }





}
