package com.daniel.biblioteca_lpII.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;

@Configuration
public class MapperConfig {
	
	@Bean("clienteMapper")
	public ModelMapper clientMapper() {
		return new ModelMapper();
	}

	@Bean("tipoMapper")
	public ModelMapper tipoMapper(){
		return new ModelMapper();
	}

	@Bean("areaMapper")
	public ModelMapper areaMapper(){
		return new ModelMapper();
	}

	@Bean("autorMapper")
	public ModelMapper autorMapper(){
		return new ModelMapper();
	}

	@Bean("editorialMapper")
	public ModelMapper editorialMapper(){
		return new ModelMapper();
	}

	@Bean("paisMapper")
	public ModelMapper paisMapper(){
		return new ModelMapper();
	}

	@Bean("libroMapper")
	public ModelMapper libroMapper(){
		return new ModelMapper();
	}

	@Bean("ventaMapper")
	public ModelMapper ventaMapper(){
		return new ModelMapper();
	}
	
}
