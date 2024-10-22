package br.org.serratec.rede_social.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Esta classe representa um objeto usuário com seus detalhes e relacionamentos")
public class UsuarioInserirDTO {
	
	@Schema(description = "ID do usuário")
	private String nome;
	
	@Schema(description = "Sobrenome do usuário")
	private String sobrenome;
	
	@Schema(description = "Email do usuário")
	private String email;
	
	@Schema(description = "Data de nascimento do usuário")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@Schema(description = "Senha do usuário")
	private String senha;
	
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
