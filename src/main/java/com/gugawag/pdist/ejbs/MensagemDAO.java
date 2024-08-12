package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;
import com.gugawag.pdist.model.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MensagemDAO {

    @PersistenceContext(unitName="bd2")
    private EntityManager em2;

    public void inserir(Mensagem novaMensagem) {
        if (contemPalavrao(novaMensagem.getMensagem())) {
            throw new IllegalArgumentException("Mensagem contém palavrões e não pode ser inserida.");
        }
        em2.persist(novaMensagem);
    }

    public List<Mensagem> listar() {
        return em2.createQuery("FROM Mensagem").getResultList();
    }

    private boolean contemPalavrao(String mensagem) {
        System.out.println("Mensagem recebida: " + mensagem);
        String[] palavroes = {"misera", "desgraca"};
        for (String palavrao : palavroes) {
            if (mensagem.toLowerCase().contains(palavrao)) {
                return true;
            }
        }
        return false;
    }
}
