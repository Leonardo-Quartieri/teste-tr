package br.com.tr.cartao.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTest {
	public static final String API_COTACOES = "/api/v1/cartoes/";


	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

/*

Vcs ate me deram bastante tempo, mas to super enrolado com meus clientes..... fica pra um proxima os testes

*/


	private String getServico() {
		return "http://localhost:" + port;
	}

}
