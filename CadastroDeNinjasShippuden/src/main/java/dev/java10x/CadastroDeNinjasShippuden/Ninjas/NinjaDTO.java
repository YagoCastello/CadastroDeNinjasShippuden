package dev.java10x.CadastroDeNinjasShippuden.Ninjas;

import dev.java10x.CadastroDeNinjasShippuden.Missoes.MissaoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDTO {

    private Long id;
    private String nome;
    private String email;
    private String imgUrl;
    private int idade;
    private MissaoModel missoes;
    private String rank;

}
