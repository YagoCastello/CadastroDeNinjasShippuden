package dev.java10x.CadastroDeNinjasShippuden.Missoes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUi {

    private final MissaoService missaoService;

    public MissoesControllerUi(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model){
        List<MissaoDTO> missoes = missaoService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "listarMissoes";
    }

    @GetMapping("/listar/{id}")
    public String listarPorId(@PathVariable Long id, Model model){
        MissaoDTO missao = missaoService.listarMissoesPorId(id);

        if(missao != null){
            model.addAttribute("missao", missao);
            return "detalhesMissoes";
        }else  {
            model.addAttribute("mensagem", "Nenhum Missao encontrada");
            return "listarMissoes";
        }
    }

    // Deletar ninja(DELETE)
    @GetMapping("/deletarid/{id}")
    public String deletarMissaoPorId(@PathVariable Long id) {
        missaoService.deletarMissaoPorId(id);
        return "redirect:/missoes/ui/listar";
    }


    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarMissao(Model model) {
        model.addAttribute("missao", new MissaoDTO());
        return "adicionarMissoes";
    }

    @PostMapping("/salvar")
    public String salvarMissao(@ModelAttribute MissaoDTO missaoDTO, RedirectAttributes redirectAttributes) {
        missaoService.criarMissao(missaoDTO);
        redirectAttributes.addFlashAttribute("mensagem", " Missao cadastrado com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

    @PostMapping("/salvarAlteracao")
    public String salvarAlteracao(@ModelAttribute MissaoDTO mISSAO) {
        missaoService.criarMissao(mISSAO);

        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/alterar/{id}")
    public String abrirPaginaAlteracao(@PathVariable Long id,
                                       Model model){

        MissaoDTO missao = missaoService.listarMissoesPorId(id);

        model.addAttribute("missao", missao);

        return "alterarMissoes";
    }


}