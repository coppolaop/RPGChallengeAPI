package com.darksun.RPGChallengeAPI.service;

import com.darksun.RPGChallengeAPI.controller.ChallengeController;
import com.darksun.RPGChallengeAPI.entity.Monstro;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class MonstroService {

	@Value( "${monstreador.url.monstro.getAll}" )
	private String url;

	public List< Monstro > chamadaMostreador( ) {
		List< Monstro > monstros = new ArrayList<>( );
		try {
			URL               obj = new URL( url );
			HttpURLConnection con = ( HttpURLConnection ) obj.openConnection( );

			con.setRequestMethod( "GET" );

			con.setRequestProperty( "User-Agent", "Mozilla/5.0" );

			con.setRequestProperty( "Authorization", ChallengeController.getToken( ) );

			BufferedReader in = new BufferedReader(
					new InputStreamReader( con.getInputStream( ) ) );
			String       inputLine;
			StringBuffer response = new StringBuffer( );

			while ( ( inputLine = in.readLine( ) ) != null ) {
				response.append( inputLine );
			}
			in.close( );

			Gson gson     = new Gson( );
			Type listType = new TypeToken< List< Monstro > >( ) {
			}.getType( );
			monstros = gson.fromJson( response.toString( ), listType );

		} catch ( Exception ex ) {
			ex.printStackTrace( );
		}
		return monstros;
	}
}
