package dev.java10x.CadastroDeNinjasShippuden.Missoes;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissaoService {

    private final MissoesRepository missoesRepository;
    private final MissaoMapper missaoMapper;


    public MissaoService(MissaoMapper missaoMapper, MissoesRepository missoesRepository) {
        this.missaoMapper = missaoMapper;
        this.missoesRepository = missoesRepository;
    }

    //Listar todos as minhas missoes
    public List<MissaoDTO> listarMissoes(){
        List<MissaoModel> missoes = missoesRepository.findAll();
        return missoes.stream().map(missaoMapper::map).
                collect(Collectors.toList());
    }


    public MissaoDTO listarMissoesPorId(Long id){
        Optional<MissaoModel> missaoPorId = missoesRepository.findById(id);
        return  missaoPorId.map(missaoMapper::map).orElse(null);
    }


    public MissaoDTO criarMissao(MissaoDTO missaoDTO){
        MissaoModel missao = missaoMapper.map(missaoDTO);
        missao = missoesRepository.save(missao);
        return missaoMapper.map(missao);
    }

    // Deletar a missao - Tem que ser um retorno VOID
    public void deletarMissaoPorId(Long id){
        missoesRepository.deleteById(id);
    }

    // Atualizar MISSAO
    public MissaoDTO atualizarMissao(Long id, MissaoDTO missaoDTO){
        Optional<MissaoModel> missaoExistente = missoesRepository.findById(id);
        if(missaoExistente.isPresent()){
            MissaoModel missaoAtualizado = missaoMapper.map(missaoDTO);
            missaoAtualizado.setId(id);
            MissaoModel missaoSalvo = missoesRepository.save(missaoAtualizado);
            return missaoMapper.map(missaoSalvo);
        }
        return  null;
    }
}
