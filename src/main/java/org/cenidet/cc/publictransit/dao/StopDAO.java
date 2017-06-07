
package org.cenidet.cc.publictransit.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.cenidet.cc.publictransit.db.DBConnection;
import org.cenidet.cc.publictransit.db.PublicTransitDB;
import org.cenidet.cc.publictransit.dto.Stop;
import org.cenidet.cc.publictransit.interfaces.StopInterface;

public class StopDAO extends PublicTransitDB implements StopInterface{
	
	private static StopDAO stopDAO;
	
	private StopDAO(){}
	
	public static StopDAO getInstance(){
		if(stopDAO == null){
			stopDAO = new StopDAO();
		}
		return stopDAO;
	}

	public ArrayList<Stop> getStopsByJourneyId(int id) throws SQLException {
        connection = DBConnection.getInstance().getConnection();
        sqlStatement ="SELECT * FROM parada WHERE id_viaje = ?";       
        preparedStatement = connection.prepareStatement(sqlStatement);        
        preparedStatement.setInt(1, id);        
        resultSet = preparedStatement.executeQuery();
        
        ArrayList<Stop> stops = new ArrayList<Stop>();
        
        while(resultSet.next()){             
            Stop stop = new Stop(); 
            
            stop.setId(resultSet.getInt("id_parada"));
            stop.setIdSigParada(resultSet.getInt("id_sig_parada"));
            stop.setLatitude(resultSet.getDouble("latitud"));
            stop.setLongitude(resultSet.getDouble("longitud"));
            stop.setPolyline(resultSet.getString("polilinea"));
            stop.setDistanceMts(resultSet.getInt("distancia_mts"));
            stop.setDescription(resultSet.getString("descripcion"));
            stop.setJourneyId(resultSet.getInt("id_viaje"));                                                                                            
            System.out.println(stop);
           stops.add(stop);            
        }       
        closeResources();        
        return stops;
	}
		
	public Stop getStopById(int id) throws SQLException {
        connection = DBConnection.getInstance().getConnection();
        sqlStatement ="SELECT * FROM parada WHERE id_parada = ?";       
        preparedStatement = connection.prepareStatement(sqlStatement);        
        preparedStatement.setInt(1, id);        
        resultSet = preparedStatement.executeQuery();
        
        Stop stop = new Stop();
        
        while(resultSet.next()){                                     
            stop.setId(resultSet.getInt("id_parada"));
            stop.setIdSigParada(resultSet.getInt("id_sig_parada"));
            stop.setLatitude(resultSet.getDouble("latitud"));
            stop.setLongitude(resultSet.getDouble("longitud"));
            stop.setPolyline(resultSet.getString("polilinea"));
            stop.setDistanceMts(resultSet.getInt("distancia_mts"));
            stop.setDescription(resultSet.getString("descripcion"));
            stop.setJourneyId(resultSet.getInt("id_viaje"));                                                                                            
            System.out.println(stop);           
        }       
        closeResources();        
        return stop;
	}
	
	public ArrayList<Stop> getVerticesGrafo() throws SQLException{
		ArrayList<Stop> allStops = getAllStops();		
		allStops = setParadasAdyacentes(allStops);		
		ArrayList<Stop> verticesGrafo = getAllDistinctElements(allStops);
		
		for(Stop vertice: verticesGrafo){
			for(Stop parada: allStops){
				if( vertice.equals(parada) ){
					if(parada.getSigParada() != null){
						Stop paradaAdyacente = parada.getSigParada();
						paradaAdyacente.setDistanceMts(parada.getDistanceMts());
						vertice.addParadaAdyacente(paradaAdyacente );						
					}
				}	
			}	
		}				
		return verticesGrafo;
	}
	
	public ArrayList<Stop> getAllDistinctElements(ArrayList<Stop> allStops){
		ArrayList<Stop> verticesGrafo = new ArrayList<Stop>();
		for(Stop stop: allStops){
			if( ! verticesGrafo.contains(stop)){
				verticesGrafo.add(stop);			
			}			
		}				
		return verticesGrafo;	
	}
		
	public ArrayList<Stop> setParadasAdyacentes(ArrayList<Stop> allStops) throws SQLException{			 		 
		for(Stop stop: allStops){
			if(stop.getIdSigParada() > 0){
				Stop adyacente = getParadaAdyacente(stop);
				stop.setSigParada(adyacente);
			}
		}
		return allStops;
	}
		
	public ArrayList<Stop> getAllStops() throws SQLException{
        connection = DBConnection.getInstance().getConnection();
        //sqlStatement ="SELECT * FROM parada ORDER BY id_viaje";
        sqlStatement ="SELECT id_parada, id_sig_parada, latitud, longitud, polilinea, distancia_mts, descripcion, parada.id_viaje"+ 
									" FROM parada, viaje"+ 
									" WHERE parada.id_viaje = viaje.id_viaje"+
									" AND viaje.direccion = 'ida'";
        preparedStatement = connection.prepareStatement(sqlStatement);                   
        resultSet = preparedStatement.executeQuery();
        
        ArrayList<Stop> stops = new ArrayList<Stop>();
        
        while(resultSet.next()){             
            Stop stop = new Stop();                       
            stop.setId(resultSet.getInt("id_parada"));
            stop.setIdSigParada(resultSet.getInt("id_sig_parada"));
            stop.setLatitude(resultSet.getDouble("latitud"));
            stop.setLongitude(resultSet.getDouble("longitud"));
            stop.setPolyline(resultSet.getString("polilinea"));
            stop.setDistanceMts(resultSet.getInt("distancia_mts"));
            stop.setDescription(resultSet.getString("descripcion"));
            stop.setJourneyId(resultSet.getInt("id_viaje"));                                                                                            
//            System.out.println(stop);
           stops.add(stop);            
        }          
        closeResources();        
        return stops;		
	}
	
	private Stop getParadaAdyacente(Stop stop) throws SQLException{
        connection = DBConnection.getInstance().getConnection();
        sqlStatement ="SELECT * FROM parada WHERE id_parada = ?";       
        preparedStatement = connection.prepareStatement(sqlStatement);        
        preparedStatement.setInt(1, stop.getIdSigParada());        
        resultSet = preparedStatement.executeQuery();
        
        Stop adyacente = new Stop();
        
        while(resultSet.next()){                                     
        	adyacente.setId(resultSet.getInt("id_parada"));
        	adyacente.setIdSigParada(resultSet.getInt("id_sig_parada"));
        	adyacente.setLatitude(resultSet.getDouble("latitud"));
        	adyacente.setLongitude(resultSet.getDouble("longitud"));
        	adyacente.setPolyline(resultSet.getString("polilinea"));
        	adyacente.setDistanceMts(resultSet.getInt("distancia_mts"));
        	adyacente.setDescription(resultSet.getString("descripcion"));
        	adyacente.setJourneyId(resultSet.getInt("id_viaje"));                                                                                            
            //System.out.println(stop);           
        }       
        closeResources();        
        return adyacente;		
	}
	
	private void insertarParada(Stop stop) throws SQLException{
        connection = DBConnection.getInstance().getConnection();
        sqlStatement ="INSERT INTO parada_test VALUES(?, ?, ?, ?, ?, ?)";       
        preparedStatement = connection.prepareStatement(sqlStatement);
        preparedStatement.setInt(1, stop.getId());
        preparedStatement.setInt(2, stop.getIdSigParada());
        preparedStatement.setDouble(3, stop.getLatitude());
        preparedStatement.setDouble(4, stop.getLongitude());
        preparedStatement.setDouble(5, stop.getDistanceMts());
        preparedStatement.setInt(6, stop.getJourneyId());
        
        preparedStatement.executeUpdate();
        
        closeResources();
	}
	
	
	/*
	public void updateStop(int stopId, String polilinea, String distancia) throws SQLException{
        connection = DBConnection.getInstance().getConnection();
        sqlStatement ="UPDATE parada SET polilinea = ? , distancia_mts = ? WHERE id_parada = ?";
        //sqlStatement ="UPDATE parada SET polilinea = ? WHERE id_parada = ?";
        preparedStatement = connection.prepareStatement(sqlStatement);
        preparedStatement.setString(1, polilinea);
        preparedStatement.setString(2, distancia);
        preparedStatement.setInt(3, stopId);
        preparedStatement.executeUpdate();
        		
        closeResources();
	}
	//*/		
	
	
	public static void main(String[] args) {		
		try {
			//actualizarParadas();
			insertParadasFromFile();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	//*/
	
	/*
	public static void actualizarParadas() throws Exception{
		String fileName = "C:\\Users\\J.Ivan\\Google Drive\\residencia_cenidet\\base_de_datos\\polilineas_y_distancias\\public_transit_reducida\\p_d_id_viaje_5";
		// String fileName = "C:\\Users\\J.Ivan\\Google Drive\\residencia_cenidet\\base_de_datos\\polilineas_y_distancias\\public_transit_reducida\\polilineas_id_viaje_2";
		
		StopDAO stopDAO = StopDAO.getInstance();
		ArrayList<Stop> stops = stopDAO.getStopsByJourneyId(5);
		
	      FileReader fr = new FileReader(fileName);
	      BufferedReader br = new BufferedReader(fr);
	      
	      for (int i = 0; i < stops.size(); i++) {
			if( i != stops.size()-1){
				int stopId = stops.get(i).getStopId();
				String[] linea = br.readLine().split(":");
				
				stopDAO.updateStop(stopId, linea[0], linea[1]);
				
				System.out.println(linea[0]+" ---> "+ linea[1]);
			}
		}
	      fr.close();
	}
	//*/
	
	
	public static void insertParadasFromFile() throws Exception{
		String fileName = "C:\\Users\\J.Ivan\\Google Drive\\residencia_cenidet\\base_de_datos\\paradas_test_excel_4_camino_1";
										
	      FileReader fr = new FileReader(fileName);
	      BufferedReader br = new BufferedReader(fr);
	      String linea;
	      
	      while((linea=br.readLine()) != null){
	    	  String[] params = linea.split(":");
	    	  
	    	  int id = Integer.parseInt(params[0]);
	    	  int idSigParada = Integer.parseInt(params[1]);
	    	  double latitude= Integer.parseInt(params[2]);
	    	  double longitud= Integer.parseInt(params[3]);
	    	  double distanciaMts= Integer.parseInt(params[4]);
	    	  int idViaje= Integer.parseInt(params[5]);
	    	  
	    	  Stop stop = new Stop(id, idSigParada, latitude, longitud, distanciaMts, idViaje);	    	  
	    	  StopDAO.getInstance().insertarParada(stop);	    	
	    	  System.out.println(stop);
	    	  
	      }
	      
	      fr.close();
	}
	//*/
    
}






