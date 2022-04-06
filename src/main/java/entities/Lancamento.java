package entities;

import enums.TipoEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lancamento")
@Data
public class Lancamento implements Serializable {

    private static final long serialVersionUID = -6396755950434951961L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data", nullable = false)
    private Date data;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Column(name = "localizacao", nullable = false)
    private String localizacao;
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;
    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoEnum tipo;
    @ManyToOne(fetch = FetchType.EAGER)
    private Funcionario funcionario;

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
