package com.example.tpspringmono.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tpspringmono.dto.ProduitEvt;
import com.example.tpspringmono.repos.ProduitRepo;

@Service
public class RabbitService {
	
@Autowired	
	
	private ProduitRepo prodRepo;
	
	@RabbitListener(queues="QU1")
	public void affichage(ProduitEvt pevt) {
		//chercher le produit (p)pevt.getId()
		//calculer la nouvelle valeur de qte (qte-pevt.getQte())
		//prodRepo.save(p)
		
	}

}
