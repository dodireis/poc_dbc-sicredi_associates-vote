package com.sicredi.associatesvote.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sicredi.associatesvote.domain.Theme;
import com.sicredi.associatesvote.dto.CreateThemeRequestDTO;
import com.sicredi.associatesvote.mapper.ThemeMapper;
import com.sicredi.associatesvote.repository.ThemeRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ThemeService.class })
public class ThemeServiceTest {

	@Autowired
	private ThemeService service;

	@MockBean
	private ThemeRepository repository;

	@MockBean
	private ThemeMapper themeMapper;

	private Theme entityMock;

	@Test
	public void givenNewTheme_whenCreateWithValidDto_thenSaveTheme() {
		Long expectedThemeId = 1L;
		entityMock = new Theme();
		entityMock.setId(1L);
		when(repository.save(any())).thenReturn(entityMock);

		CreateThemeRequestDTO requestDTO = new CreateThemeRequestDTO();
		requestDTO.setDescription("Teste Teste");

		Long actualThemeId = service.create(requestDTO);

		assertEquals(expectedThemeId, actualThemeId);
	}

	@Test
	public void givenNewTheme_whenCreateWithInvalidDescription_thenThrowAnException() {
		String expectedErrorMessage = "Theme must have a description.";

		CreateThemeRequestDTO requestDTO = new CreateThemeRequestDTO();
		requestDTO.setDescription("  ");

		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> service.create(requestDTO))
				.withMessage(expectedErrorMessage);
	}
	

	@Test
	public void givenNewTheme_whenCreateWithNullDescription_thenThrowAnException() {
		String expectedErrorMessage = "Theme must have a description.";

		CreateThemeRequestDTO requestDTO = new CreateThemeRequestDTO();

		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> service.create(requestDTO))
				.withMessage(expectedErrorMessage);
	}
}
