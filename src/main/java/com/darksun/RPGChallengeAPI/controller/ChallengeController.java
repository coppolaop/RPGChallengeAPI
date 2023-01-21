package com.darksun.RPGChallengeAPI.controller;

import com.darksun.RPGChallengeAPI.service.ChallengeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( value = "/api" )
@Api( value = "API REST de Desafios para RPG" )
@CrossOrigin( origins = "*" )
public class ChallengeController {

	@Autowired
	ChallengeService challengeService;

	private static String token;

	@GetMapping( "/desafio" )
	@ApiOperation( value = "Retorna um monstro apropriado para o nivel do grupo e sua quantidade" )
	public ResponseEntity< String > montarDesafio( @RequestHeader( "Authorization" ) String token,
												   @RequestHeader( "nivel" ) Integer nivel ) {
		ChallengeController.token = token;
		return new ResponseEntity<>( challengeService.montarDesafio( nivel ), HttpStatus.OK );
	}

	public static String getToken( ) {
		return token;
	}
}
