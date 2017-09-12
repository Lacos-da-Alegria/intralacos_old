package com.lacosdaalegria.intra.model;

import java.util.UUID;

public class CodigoAtivacao {
	
	public static String codigoAtivacao = "lacos"+UUID.randomUUID().toString().substring(0, 4);
	
}
