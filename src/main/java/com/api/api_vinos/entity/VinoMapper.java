package com.api.api_vinos.entity;

public class VinoMapper {
	
	public static ModeloVino toModeloVino(ResponseDTO modeloVinoDTO) {
		ModeloVino modeloVino = new ModeloVino();
		modeloVino.setModelo(modeloVinoDTO.getModeloVino());
		modeloVino.setUrl(modeloVinoDTO.getUrl());
		return modeloVino;
	}
	
	public static ResponseDTO toResponseDTO(ModeloVino modeloVino) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setModeloVino(modeloVino.getModelo());
		responseDTO.setUrl(modeloVino.getUrl());
		return responseDTO;
	}
}