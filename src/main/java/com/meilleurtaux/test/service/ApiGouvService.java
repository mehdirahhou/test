package com.meilleurtaux.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.meilleurtaux.test.dto.CommuneDto;
import com.meilleurtaux.test.exception.CodePostalInvalidException;
import com.meilleurtaux.test.exception.CommuneNotFoundException;
import com.meilleurtaux.test.exception.ExternalServiceException;


@Service
public class ApiGouvService {
	
	@Autowired
	RestTemplate restTemplate;
	
	
	String baseURI = "https://geo.api.gouv.fr/";
	
	
	public List<CommuneDto> getCommunes(String codePostal) {
		List<CommuneDto> communes = new ArrayList<CommuneDto>();
		
		if (codePostal.length() != 5 || !codePostal.matches("^[0-9]{5}$")) {
            throw new CodePostalInvalidException(codePostal + " is not a codePostal");
        }

		
		String url = baseURI + "communes?codePostal={codePostal}&fields={fields}";		
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("codePostal", codePostal);
		vars.put("fields", "nom,population");
		
		try {
			communes = restTemplate.getForObject(url, List.class, vars);
			if (communes.size() == 0) {
				throw new CommuneNotFoundException("No Commune found for postal code: " + codePostal);
			}
		} catch (RestClientException e) {
			throw new ExternalServiceException("Error occurred while calling external service geo.api.gouv.fr", e);
		}
		
		return communes;
	}

}
