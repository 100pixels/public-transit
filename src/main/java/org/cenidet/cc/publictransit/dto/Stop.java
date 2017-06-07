package org.cenidet.cc.publictransit.dto;

import java.util.ArrayList;

public class Stop{
            

    private int id;
    private Stop sigParada /*= new Stop()*/;
    private double latitude;    
    private double longitude;
    private String polyline;
    private double distance_mts;
    private String description;   
    private int journeyId;
    private ArrayList<Stop> paradasAdyacentes;    
    private int idSigParada;
    
    
     /*Constructor para hacer pruebas*/ 
    public Stop(int id, int idSigParada, double latitude, double longitude, double distance_mts, int journeyId) {
		super();
		this.id = id;
		this.idSigParada = idSigParada;
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance_mts = distance_mts;
		this.journeyId = journeyId;
	}
    
	public Stop(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Stop() {
    	paradasAdyacentes = new ArrayList<Stop>();    	
	}
   
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Stop getSigParada() {
		return sigParada;
	}

	public void setSigParada(Stop sigParada){
		if(sigParada != null){
			this.sigParada = sigParada;
			//addParadaAdyacente(this.sigParada);
		}

	}

	public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
        
    public String getPolyline() {
		return polyline;
	}
	public void setPolyline(String polyline) {
		this.polyline = polyline;
	}
			
	public double getDistance_mts() {
		return distance_mts;
	}

	public void setDistance_mts(double distance_mts) {
		this.distance_mts = distance_mts;
	}

	public double getDistanceMts() {
		return distance_mts;
	}
	public void setDistanceMts(double distance_mts) {
		this.distance_mts = distance_mts;
	}
	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }
              
	public ArrayList<Stop> getParadasAdyacentes() {
		return paradasAdyacentes;
	}

	public void setParadasAdyacentes(ArrayList<Stop> verticesAdyacentes) {
		this.paradasAdyacentes = verticesAdyacentes;
	}
	
	public void addParadaAdyacente(Stop stop){
			//System.out.println("-_-" + stop);
			paradasAdyacentes.add(stop);		
		
	//boolean agregar = true;
		/*
		if(this.paradasAdyacentes.isEmpty()){
			paradasAdyacentes.add(stop);
		}*/
		
		/*for(Stop parada: paradasAdyacentes){
			if(parada.getDistanceMts() == stop.getDistanceMts()){
				agregar = false;
			}
		}//*/
		
		/*
		if(agregar){
			paradasAdyacentes.add(stop);
		}//*/
		//if( ! this.paradasAdyacentes.contains(stop)){
			
		//}
	}
			
	public int getIdSigParada() {
		return idSigParada;
	}

	public void setIdSigParada(int idSigParada) {
		this.idSigParada = idSigParada;
	}

	
	@Override
	public String toString() {
		return "Stop [id=" + id + ", journeyId=" + journeyId + ", idSigParada=" + idSigParada + "]";
	}
	//*/
	
	

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Stop){
			Stop stop = (Stop)obj;
			return ((this.latitude == stop.getLatitude())&&(this.longitude == stop.getLongitude()));
		}
		return false;
	}

	/*
	@Override
	public String toString() {
		return "Stop [id=" + id + ", sigParada=" + sigParada + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", polyline=" + polyline + ", distance_mts=" + distance_mts + ", description=" + description
				+ ", journeyId=" + journeyId + ", paradasAdyacentes=" + paradasAdyacentes + ", idSigParada="
				+ idSigParada + "]";
	}	
	//*/
        

}