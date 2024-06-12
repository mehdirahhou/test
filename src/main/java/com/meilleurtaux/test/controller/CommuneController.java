package com.meilleurtaux.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meilleurtaux.test.dto.CommuneDto;
import com.meilleurtaux.test.service.ApiGouvService;



@RestController
public class CommuneController {
	@Autowired
	ApiGouvService apiGouvService;

	@GetMapping("/communes")
	public ResponseEntity<List<CommuneDto>> getCommune(@RequestParam(required=true) String codePostal) {
		List<CommuneDto> communes = apiGouvService.getCommunes(codePostal);
		return ResponseEntity.ok(communes);
	}
}
