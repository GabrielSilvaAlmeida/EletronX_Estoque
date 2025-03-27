package com.api.estoque.model.historico;

import java.time.LocalDate;

import com.api.estoque.model.produto.Produto;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "funcionarioshistorico")
@Entity(name = "historico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Historico{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)  
  private Long id;

  @ManyToOne
  @JoinColumn(name = "produto_id", nullable = false)
  private Produto produto;

  private LocalDate dataAquisicao;
}
