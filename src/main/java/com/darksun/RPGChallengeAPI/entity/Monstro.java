package com.darksun.RPGChallengeAPI.entity;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Monstro implements Serializable {

	@Serial
	private static final long serialVersionUID = 3594452584970169358L;
	
	private String nome;

	private Integer nd;
}
