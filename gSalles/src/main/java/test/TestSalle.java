package test;

import beans.Salle;
import service.SalleService;

public class TestSalle {

	public static void main(String[] args) {
		SalleService ss=new SalleService();
		ss.create(new Salle("s2", 50, "theorique"));
	}

}
