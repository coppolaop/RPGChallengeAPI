package com.darksun.RPGChallengeAPI.service;

import com.darksun.RPGChallengeAPI.entity.Monstro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ChallengeService {

	@Autowired
	MonstroService monstroService;

	public String montarDesafio( Integer nivel ) {
		String          monstroEscolhido = "";
		Integer         quantidade       = 1;
		List< Monstro > monstros         = monstroService.chamadaMostreador( );

		Collections.shuffle( monstros );

		Optional< Monstro > nivelado = monstros.stream( )
											   .filter( monstro -> monstro.getNd( ) <= nivel )
											   .findAny( );
		if ( !nivelado.isEmpty( ) ) {
			Monstro monstro = nivelado.get( );
			quantidade       = getQuantidade( monstro.getNd( ), nivel );
			monstroEscolhido = monstro.getNome( );
			return quantidade + "x " + monstroEscolhido;
		}

		return "Nenhum monstro adequado foi encontrado";
	}

	private Integer getQuantidade( Integer nd, Integer nivel ) {
		if ( nd < 1 ) {
			return nd * -1 * nivel;
		}
		return nivel / nd;
	}
}
