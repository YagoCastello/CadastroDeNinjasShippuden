package dev.java10x.CadastroDeNinjasShippuden.Missoes;

import dev.java10x.CadastroDeNinjasShippuden.Ninjas.NinjaModel;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissaoService {

    private MissoesRepository missoesRepository;

    public MissaoService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    //Listar todos as minhas missoes
    public List<MissaoModel> listarMissoes(){

        return missoesRepository.findAll();
    }


    public MissaoModel listarMissoesPorId(Long id){
        Optional<MissaoModel> missaoPorId = missoesRepository.findById(id);
        return  missaoPorId.orElse(null);
    }


    public MissaoModel criarMissao(MissaoModel missao){
        return  missoesRepository.save(missao);
    }

    // Deletar a missao - Tem que ser um retorno VOID
    public void deletarMissaoPorId(Long id){
        missoesRepository.deleteById(id);
    }
}
