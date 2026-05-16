package dev.java10x.CadastroDeNinjasShippuden.Missoes;
import dev.java10x.CadastroDeNinjasShippuden.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjasShippuden.Ninjas.NinjaModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private MissaoService missaoService;

    public MissoesController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }


    // POST-- Mandar uma requisição para CRIAR as missoes
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissaoDTO missao) {
        MissaoDTO novaMissao = missaoService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada com sucesso: " + novaMissao.getNome() + " ID: " + novaMissao.getId());
    }

    // Get -- Mandar uma requisição para mostrar as missoes
    @GetMapping("/listar")
    public ResponseEntity<List<MissaoDTO>> listarMissao() {
        List<MissaoDTO> missoes = missaoService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }


    //Mostrar missao por id
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissoesPorId(@PathVariable Long id) {
        MissaoDTO missao = missaoService.listarMissoesPorId(id);
        if (missao != null) {
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum Missao encontrado com o id " + id);
        }

    }

    // PUT -- Mandar uma requisição para alterar as missoes
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissaoPorId(@PathVariable Long id, @RequestBody MissaoDTO missaoAtualizado) {
        MissaoDTO novaMissao = missaoService.atualizarMissao(id, missaoAtualizado);

        if (novaMissao != null) {
            return ResponseEntity.ok(novaMissao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missao com o id: " + id + " ,não foi encontrado");
        }
    }

    // DELETE -- Mandar uma requisição para deletar uma missão
    @DeleteMapping("/deletarid/{id}")
    public ResponseEntity<String> deletarMissaoPorId(@PathVariable Long id) {
        if (missaoService.listarMissoesPorId(id) != null) {
            missaoService.deletarMissaoPorId(id);
            return ResponseEntity.ok("Missao com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missao com o id " + id + ", não foi encontrada. Tente outro id. ");
        }






    }


    @GetMapping("/alterar/{id}")
    public String abrirPaginaAlteracao(@PathVariable Long id,
                                       Model model){

        MissaoDTO missao = missaoService.listarMissoesPorId(id);

        model.addAttribute("missao", missao);

        return "alterarMissao";
    }


    @PostMapping("/salvarAlteracao")
        public String salvarAlteracao(@ModelAttribute MissaoDTO missaoDTO){

        missaoService.criarMissao(missaoDTO);

        return "redirect:/missao/ui/listar";
    }

}