package br.org.serratec.rede_social.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
@Schema(description = "Representa um usuário da rede social")
public class Usuario implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "ID do usuário")
	private Long id;

	@NotBlank(message = "Preencha o nome do usuário!")
	@Size(max = 50, min = 3, message = "O tamanho do nome deve ter entre {min} a {max} caracteres.")
	@Column(name = "nome", nullable = false, length = 50)
	@Schema(description = "Nome do usuário")
	private String nome;

	@NotBlank(message = "Preencha o sobrenome do Usuário!")
	@Size(max = 100, min = 2, message = "O tamanho do sobrenome deve ter entre {min} a {max} caracteres.")
	@Column(name = "sobrenome", nullable = false, length = 100)
	@Schema(description = "Sobrenome do usuário")
	private String sobrenome;

	@NotBlank(message = "Preencha o email!")
	@Email(regexp = "[a-z0-9.-]+@[a-z.]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Insira um email válido")
	@Column(name = "email", nullable = false, unique = true, length = 50)
	@Schema(description = "Email do usuário")
	private String email;

	@NotNull(message = "Preencha a senha!")
	@Size(max = 128, min = 6, message = "O tamanho da senha deve ter entre {min} e {min} caracteres. ")
	@Column(name = "senha", nullable = false, length = 128)
	@Schema(description = "Senha do usuário")
	private String senha;

	@NotNull(message = "Preencha a data de nascimento!")
	@Column(name = "data_nascimento", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Schema(description = "Data de nascimento do usuário")
	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "id.seguidor")
	@Schema(description = "Seguidores do usuário")
	private Set<Relacionamento> seguidores = new HashSet<>();

	@OneToMany(mappedBy = "id.seguindo")
	private Set<Relacionamento> seguindo = new HashSet<>();

	@OneToMany(mappedBy = "autor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Schema(description = "Pessoas que seguem o usuário")
	private Set<Postagem> postagens = new HashSet<>();

	public Usuario() {
	}

	public Usuario(Long id, String nome, String sobrenome, String email, String senha, LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}

	public Set<Relacionamento> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(Set<Relacionamento> seguidores) {
		this.seguidores = seguidores;
	}

	public Set<Relacionamento> getSeguindo() {
		return seguindo;
	}

	public void setSeguindo(Set<Relacionamento> seguindo) {
		this.seguindo = seguindo;
	}

	public Set<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(Set<Postagem> postagens) {
		this.postagens = postagens;
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

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

}
