package com.kursova.dan_kursova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DanKursovaApplicationTests {


	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void localhostCheck() throws Exception{

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("api is working fine");
	 
    }

	@Test
	void gameCheck() throws Exception{

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/game?name=Hades",
				String.class)).contains("Hades");
	 
    }

	@Test
	void usersCheck() throws Exception{

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/users",
				String.class)).contains("Ben01","Tomato11" );
	 
    }


}
