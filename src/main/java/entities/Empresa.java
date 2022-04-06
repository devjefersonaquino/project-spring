package entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "empresa")
@Data
public class Empresa implements Serializable {

    private static final long serialVersionUID = 4862991749577621407L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;
    @Column(name = "cnpj", nullable = false)
    private String cnpj;
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;
    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;
    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

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
