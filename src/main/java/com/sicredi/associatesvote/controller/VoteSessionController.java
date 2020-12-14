package com.sicredi.associatesvote.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sicredi.associatesvote.dto.CreateVoteSessionRequestDTO;
import com.sicredi.associatesvote.service.VoteSessionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class VoteSessionController {

	@Autowired
	private VoteSessionService service;

	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Voting Session created"),
			@ApiResponse(code = 400, message = "Bad request. Wrong param"),
			@ApiResponse(code = 500, message = "Internal server error")			
	})
	@ApiOperation(value = "Create a session to associates vote in a theme/topic")
	@PostMapping(value = "/vote_session", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createVoteSession(@Valid @RequestBody CreateVoteSessionRequestDTO dto) {
		Long id = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}

}
