package com.app.service;

import com.app.dao.impl.GeneratorDAOImpl;
import com.app.enumeration.OBJECT_ID;
import com.app.model.hibernate.Id;
import com.app.util.hibernate.HibernateUtil;

public abstract class IDGeneratorService {
	
	private static OBJECT_ID objectId = OBJECT_ID.DEFAULT;

	public static String generate(){
		return generate(objectId);
	}

	public static String generate(OBJECT_ID objectId){
		String prefix = objectId.getPrefix();
		int year = objectId.getYear();
		String suff="";
		Id id = (Id) new GeneratorDAOImpl(HibernateUtil.getSessionFactory()).getNext();
		if(prefix.equals("SID"))
			suff = id.getNextStudentId();
		else if(prefix.equals("CID"))
			suff = id.getNextCourseId();
		else
			suff = String.format("%04d", (int) (Math.random() * 9999) + 1);
		 return String.format("%s%d%s", prefix , year , suff);
	}

	public static void setObjectId(OBJECT_ID objectId) {
		IDGeneratorService.objectId = objectId;
	}
}
