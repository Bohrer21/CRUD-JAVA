package model.dao;

import db.ConnectionForDB;
import model.dao.impl.DepartmentDaoImplements;
import model.dao.impl.SellerDaoImplements;

public class DaoInstatiate {
	
	public static SellerDao createSellerDao() {
		return new SellerDaoImplements(ConnectionForDB.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoImplements(ConnectionForDB.getConnection());
	}
}
