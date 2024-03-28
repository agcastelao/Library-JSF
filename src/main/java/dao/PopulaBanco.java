package dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.EntityManager;
import model.Autor;
import model.Livro;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Autor assis = geraAutor("Machado de Assis");
		em.persist(assis);

		Autor amado = geraAutor("Jorge Amado");
		em.persist(amado);

		Autor coelho = geraAutor("Paulo Coelho");
		em.persist(coelho);

		Livro casmurro = geraLivro("9788525044648", "Dom Casmurro",
				"10/01/1899", 24.90, assis);
		Livro memorias = geraLivro("9788508154159",
				"Memorias Postumas de Bras Cubas", "01/01/1881", 19.90, assis);
		Livro quincas = geraLivro("9788508040841", "Quincas Borba",
				"01/01/1891", 16.90, assis);

		em.persist(casmurro);
		em.persist(memorias);
		em.persist(quincas);

		Livro alquemista = geraLivro("9788575427583", "O Alquimista",
				"01/01/1988", 19.90, coelho);
		Livro brida = geraLivro("9788505672587", "Brida", "01/01/1990",
				12.90, coelho);
		Livro valkirias = geraLivro("9788528124588", "As Valkirias",
				"01/01/1992", 29.90, coelho);
		Livro maao = geraLivro("9788518922389", "O Diario de um Mago",
				"01/01/1987", 9.90, coelho);

		em.persist(alquemista);
		em.persist(brida);
		em.persist(valkirias);
		em.persist(maao);

		Livro capitaes = geraLivro("9788508311691", "Capitaes da Areia",
				"01/01/1937", 6.90, amado);
		Livro flor = geraLivro("9788535925699",
				"Dona Flor e Seus Dois Maridos", "01/01/1966", 18.90, amado);

		em.persist(capitaes);
		em.persist(flor);

		em.getTransaction().commit();
		em.close();

	}

	private static Autor geraAutor(String nome) {
		Autor autor = new Autor();
		autor.setNome(nome);
		return autor;
	}

	private static Livro geraLivro(String isbn, String titulo, String data,
			double preco, Autor autor) {
		Livro livro = new Livro();
		livro.setIsbn(isbn);
		livro.setTitulo(titulo);
		livro.setDataLancamento(parseData(data));
		livro.setPreco(preco);
		livro.adicionaAutor(autor);
		return livro;
	}

	@SuppressWarnings("unused")
	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
}