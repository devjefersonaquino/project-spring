package entities;

import enums.PerfilEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "funcionario")
@Data
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 5754246207015712518L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "senha", nullable = false)
    private String senha;
    @Column(name = "cpf", nullable = false)
    private String cpf;
    @Column(name = "valor_hora", nullable = true)
    private BigDecimal valorHora;
    @Column(name = "qtd_horas_trabalho_dia", nullable = true)
    private float qtdHorasTrabalhoDia;
    @Column(name = "qtd_horas_almoco", nullable = true)
    private float qtdHorasAlmoco;
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private PerfilEnum perfil;
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;
    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;
    @ManyToOne(fetch = FetchType.EAGER)
    private Empresa empresa;
    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private transient List<Lancamento> lancamentos;

    @PreUpdate
    public void preUpdate(){
        dataAtualizacao = new Date();
    }

    @PrePersist
    public void prePersist(){
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }

}
