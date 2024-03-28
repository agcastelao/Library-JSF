package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DAO;
import model.Autor;
import model.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();

	private Integer autorId;

	private List<Livro> livros;

	private List<Autor> autores;

	public LivroBean() {
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		livros = dao.listaTodos();
		autores = new DAO<Autor>(Autor.class).listaTodos();
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Livro escrito por " + autor.getNome());
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		FacesContext context = FacesContext.getCurrentInstance();

		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
		}

		/*
		 * if (this.livro.getDataLancamento() == null) {
		 * this.livro.setDataLancamento(Calendar.getInstance()); }
		 * 
		 * if (this.livro.getDataModificacao() == null) {
		 * this.livro.setDataModificacao(new Date()); }
		 * 
		 * if (this.livro.getDataCriacao() == null) {
		 * this.livro.setDataCriacao(Calendar.getInstance()); }
		 */

		if (this.livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);

			context.addMessage("livroMessage",
					new FacesMessage("Livro Gravado", "O livro " + livro.getTitulo() + " foi gravado com sucesso!"));

		} else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);

			context.addMessage("livroMessage",
					new FacesMessage("Livro Alterado", "O livro " + livro.getTitulo() + " foi alterado com sucesso!"));

		}

		this.livro = new Livro();
	}

	public void carregar(Livro livro) {
		System.out.println("Carregando livro " + livro.getTitulo());
		this.livro = livro;
	}

	public void remover(Livro livro) {
		FacesContext context = FacesContext.getCurrentInstance();

		System.out.println("Removendo livro " + livro.getTitulo());
		new DAO<Livro>(Livro.class).remove(livro);

		context.addMessage("livroMessage",
				new FacesMessage("Livro removido", "O livro " + livro.getTitulo() + " foi removido com sucesso!"));
	}

	public String formPrincipal() {
		System.out.println("Chamando o formulário principal");
		return "livro?faces-redirect=true";
	}

	public String formAutor() {
		System.out.println("Chamando o formulário do Autor");
		return "autor?faces-redirect=true";
	}

	public void teste() {
		System.out.println("teste");
	}
}
