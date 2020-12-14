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

import com.sicredi.associatesvote.dto.CreateVoteRequestDTO;
import com.sicredi.associatesvote.service.VoteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class VoteController {

	@Autowired
	private VoteService service;

	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Vote created"),
			@ApiResponse(code = 400, message = "Bad request. Wrong param"),
			@ApiResponse(code = 500, message = "Internal server error")			
	})
	@ApiOperation(value = "Create a vote of an associate into a voting session, bound a theme/topic")
	@PostMapping(value = "/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@Valid @RequestBody CreateVoteRequestDTO dto) {
		Long id = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}
}
