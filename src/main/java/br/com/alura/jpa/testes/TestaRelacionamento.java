package br.com.alura.jpa.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;
import br.com.alura.jpa.modelo.TipoMovimentacao;

public class TestaRelacionamento {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Conta conta = em.find(Conta.class, 2L);

		Movimentacao mov = new Movimentacao();
		mov.setData(LocalDateTime.now());
		mov.setDescricao("Churrascaria");
		mov.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		mov.setValor(new BigDecimal(200.0));
		mov.setConta(conta);

		em.persist(mov);
		em.getTransaction().commit();
		em.close();
	}
}
