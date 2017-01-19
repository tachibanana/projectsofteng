package com.app.dao;

import com.app.model.hibernate.Id;

public interface IDGeneratorDAO {

	public Id getNext();
	
}
