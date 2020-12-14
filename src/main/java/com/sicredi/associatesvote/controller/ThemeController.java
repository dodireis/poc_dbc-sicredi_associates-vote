package com.sicredi.associatesvote.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sicredi.associatesvote.dto.CreateThemeRequestDTO;
import com.sicredi.associatesvote.dto.ThemeResponseDTO;
import com.sicredi.associatesvote.service.ThemeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@RestController
public class ThemeController {

	@Autowired
	private ThemeService service;

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Theme created"),
			@ApiResponse(code = 400, message = "Bad request. Wrong param"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Create a Theme (agenda for associates vote)")
	@PostMapping(value = "/theme", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createTheme(@RequestBody CreateThemeRequestDTO dto) {
		Long id = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Theme closed"),
			@ApiResponse(code = 400, message = "Bad request. Wrong param"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Close a Theme and process the votes, returning the result.")
	@PostMapping(value = "/theme/close/{id}")
	public ResponseEntity<ThemeResponseDTO> closeTheme(@PathVariable Long id) {
		ThemeResponseDTO responseDTO = service.closeTheme(id);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping(value = "/theme/{id}")
	public ResponseEntity<ThemeResponseDTO> getTheme(@PathVariable Long id) {
		ThemeResponseDTO responseDTO = service.getTheme(id);
		return ResponseEntity.ok().body(responseDTO);
	}

}
