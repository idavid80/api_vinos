package com.api.api_vinos.repository;

import java.util.List;
import com.api.api_vinos.entity.ModeloVino;

public interface ModeloVinoDAO {
	
		public ModeloVino findBymodelo(String modelo);
		
	    public List<ModeloVino> getModeloVino();

	    public void guardarModeloVino(ModeloVino vino);

}
