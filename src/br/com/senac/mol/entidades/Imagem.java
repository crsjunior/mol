package br.com.senac.mol.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "imagens")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "id_hash_imagem", nullable = false)
    private String idHashImagem;
    
    @Column(name = "arquivo", nullable = false)
    private String arquivo;

    @Temporal(TemporalType.DATE)
    @Column(name = "data")
    private Date dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_produto")
    private Produto produto;
    
    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getIdHashImagem() {
        return idHashImagem;
    }

    public void setIdHashImagem(String idHashImagem) {
        this.idHashImagem = idHashImagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
