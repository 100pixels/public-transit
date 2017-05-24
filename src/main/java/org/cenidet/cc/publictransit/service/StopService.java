package org.cenidet.cc.publictransit.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.cenidet.cc.publictransit.dao.StopDAO;
import org.cenidet.cc.publictransit.dto.Stop;

public class StopService {
	
	private StopDAO stopDAO;
	private static StopService instance;
	
	private StopService(){
		stopDAO = StopDAO.getInstance();
	}
	
	public static StopService getInstance(){
		if(instance == null){
			instance = new StopService();
		}
		return instance;
	}
			
	public ArrayList<Stop> getVerticesGrafo() throws SQLException{		
		return stopDAO.getVerticesGrafo();
	}

}
