package dev.java10x.CadastroDeNinjasShippuden.Ninjas;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String Bemvindos(){
        return "Seja bem vindo!!";
    }



    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: "+ novoNinja.getNome()+ " ID: "+novoNinja.getId());
    }

    //Procurar Ninja por ID(CREATE)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();

        return ResponseEntity.ok(ninjas);
    }

    //Mostrar todos os ninjas (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id){

        NinjaDTO ninja= ninjaService.listarNinjasPorId(id);

        if (ninja != null){
            return ResponseEntity.ok(ninja);
        }else {return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja com o id: "+ id + " não existe.");
        }
    }


    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);

        if (ninja != null){
            return ResponseEntity.ok(ninja);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o id: "+id +" não existe nos nossos registros");
        }
    }


    // Deletar ninja(DELETE)
    @DeleteMapping("/deletarid/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){
        if( ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
        return  ResponseEntity.ok("Ninja com o ID "+ id + " deletado com sucesso");
    } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja com o id "+id+", não foi encontrado. Tente outro id. ");
        }

}

    @GetMapping("/alterar/{id}")
    public String abrirPaginaAlteracao(@PathVariable Long id,
                                       Model model){

        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        model.addAttribute("ninja", ninja);

        return "alterarNinja";
    }


    @PostMapping("/salvarAlteracao")
    public String salvarAlteracao(@ModelAttribute NinjaDTO ninjaDTO){

        ninjaService.criarNinja(ninjaDTO);

        return "redirect:/ninjas/ui/listar";
    }


}
