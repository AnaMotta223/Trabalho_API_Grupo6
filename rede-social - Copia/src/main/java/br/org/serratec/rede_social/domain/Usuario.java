package br.org.serratec.rede_social.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Preencha o campo nome")
	@Column(name = "nome", nullable = false, length = 50)
	private String nome;
	
	@NotBlank(message = "Preencha o campo sobrenome")
	@Column(name = "sobrenome", nullable = false, length = 100)
	private String sobrenome;
	
	@NotBlank(message = "Preencha o campo email")
	@Column(name = "email", nullable = false, unique = true, length = 50)
	private String email;
	
	@NotBlank(message = "Preencha o campo senha")
	@Size(min = 8, max = 12, message = "A senha deve ter entre {min} e {max} caracteres")
	@Column(name = "senha", nullable = false, length = 12)
	private String senha;
	
	@NotNull(message = "Preencha o campo dataNascimento no formato yyyy-MM-dd")
	@Past(message = "A data informada não pode exceder a data atual")
	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;
	
	//mappedby
	//@OneToMany
	
	@ManyToMany
	@JoinTable(name = "relacionamento_usuario", joinColumns 
	= { @JoinColumn(name = "id_usuario")}, inverseJoinColumns = 
	{@JoinColumn(name = "id_seguidor"), @JoinColumn(name = "id_seguindo")})
	private Set<Relacionamento> relacionamentos = new HashSet<>();
	
	@OneToMany(mappedBy = "autor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Postagem> postagens = new HashSet<>();

	public Usuario() { }

	public Usuario(Long id, String nome, String sobrenome, String email, String senha,LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}
	
	public Set<Relacionamento> getRelacionamentos() {
		return relacionamentos;
	}

	public void setRelacionamentos(Set<Relacionamento> relacionamentos) {
		this.relacionamentos = relacionamentos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
	
}
