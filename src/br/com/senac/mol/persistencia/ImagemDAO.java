package br.com.senac.mol.persistencia;

import br.com.senac.mol.entidades.Imagem;
import java.util.Date;

import javax.persistence.EntityManager;

import java.util.List;
import javax.persistence.Query;

public class ImagemDAO extends DAO {

    public void insert(Imagem imagem) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(imagem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            
            //em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void update(Imagem imagem) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            Imagem imagemAtu = em.find(Imagem.class, imagem.getId());
            imagemAtu.setIdHashImagem(imagem.getIdHashImagem());
            imagemAtu.setArquivo(imagem.getArquivo());
            imagemAtu.setDataCadastro(new Date());

            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Imagem> getImagensProduto (String idHashImagem) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Imagem a WHERE a.idHashImagem = :HASH");
        query.setParameter("HASH", idHashImagem);
        List<Imagem> listaImagens = query.getResultList();
        return listaImagens;
    }
    
    public Imagem getById(long id) {
        Imagem imagem = null;
        EntityManager em = getEntityManager();
        try {
            imagem = em.find(Imagem.class, id);
        } finally {
            em.close();
        }
        return imagem;
    }
}
