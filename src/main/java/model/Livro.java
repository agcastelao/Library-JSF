package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Livro {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titulo;
	private String isbn;
	private double preco;
	@Temporal(TemporalType.DATE)
	private Calendar dataLancamento;
	private Date dataModificacao;
	private Calendar dataCriacao;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Autor> autores;
	
	public List<Autor> getAutores() {
		return autores;
	}

	public void adicionaAutor(Autor autor) {
		if(this.autores == null) {
			this.autores = new ArrayList<>();
		}
		
		this.autores.add(autor);
		
	}

	public Livro() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public Calendar getDataLancamento() {
        if (dataLancamento == null) {
            dataLancamento = Calendar.getInstance();
        }
        return dataLancamento;
    }

    public void setDataLancamento(Calendar dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Date getDataModificacao() {
        if (dataModificacao == null) {
            dataModificacao = new Date(); 
        }
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }	
    
    @PreUpdate
    protected void onUpdate() {
        dataModificacao = new Date(); 
    }

    public Calendar getDataCriacao() {
        if (dataCriacao == null) {
            dataCriacao = Calendar.getInstance();
        }
        return dataCriacao;
    }

    
    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

	public void removeAutor(Autor autor) {
		this.autores.remove(autor);		
	}

}
