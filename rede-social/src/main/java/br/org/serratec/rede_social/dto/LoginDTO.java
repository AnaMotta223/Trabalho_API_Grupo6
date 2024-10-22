package br.org.serratec.rede_social.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Classe para autenticação do usuário")
public class LoginDTO {
	
	@Schema(description = "Login do usuário")
	private String username;
	
	@Schema(description = "Senha do usuário")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
