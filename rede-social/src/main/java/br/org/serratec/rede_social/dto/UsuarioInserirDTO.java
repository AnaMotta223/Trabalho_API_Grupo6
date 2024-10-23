package br.org.serratec.rede_social.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Esta classe representa um objeto usuário com seus detalhes e relacionamentos")
public class UsuarioInserirDTO {

	@NotBlank(message = "Preencha o nome do usuário!")
	@Size(max = 50, min = 3, message = "O tamanho do nome deve ter entre {min} a {max} caracteres.")
	@Schema(description = "Nome do usuário")
	private String nome;

	@NotBlank(message = "Preencha o sobrenome do Usuário!")
	@Size(max = 100, min = 2, message = "O tamanho do sobrenome deve ter entre {min} a {max} caracteres.")
	@Schema(description = "Sobrenome do usuário")
	private String sobrenome;

	@NotBlank(message = "Preencha o email!")
	@Email(regexp = "[a-z0-9.-]+@[a-z.]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Insira um email válido")
	@Schema(description = "Email do usuário")
	private String email;

	@NotNull(message = "Preencha a data de nascimento!")
	@Schema(description = "Data de nascimento do usuário")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@NotNull(message = "Preencha a senha!")
	@Size(max = 128, min = 6, message = "O tamanho da senha deve ter entre {min} e {min} caracteres. ")
	@Schema(description = "Senha do usuário")
	private String senha;

	@NotNull(message = "Preencha a confirmação de senha!")
	@Size(max = 128, min = 6, message = "O tamanho da senha deve ter entre {min} e {min} caracteres. ")
	@Schema(description = "Confirma senha do usuário")
	private String confirmaSenha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
