package br.com.senac.mol.entidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_produto")
    private Long id;

    @Column(name = "descricao", length = 100)
    private String descricao;

    @Column(name = "resenha", length = 255)
    private String resenha;

    @Column(name = "preco", precision = 15, scale = 2)
    private BigDecimal preco;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_cadastro")
    private Date dataCadastro;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_ultima_atualizacao")
    private Date dataUltimaAtualizacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "produto")
    private List<Imagem> imagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResenha() {
        return resenha;
    }

    public void setResenha(String resenha) {
        this.resenha = resenha;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object objProduto) {
        if (!(objProduto instanceof Produto)) {
            return false;
        }
        Produto produto = (Produto) objProduto;
        return (this.id != null || produto.id == null) && (this.id == null || this.id.equals(produto.id));
    }

    public List<Imagem> getImagem() {
        return imagem;
    }

    public void setImagem(List<Imagem> imagem) {
        this.imagem = imagem;
    }

}
