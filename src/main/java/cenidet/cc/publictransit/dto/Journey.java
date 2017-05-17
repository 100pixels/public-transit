package cenidet.cc.publictransit.dto;


public class Journey {
       
    private int journeyId;   
    private String direction;    
    private int timeInterval;    
    private String startTime;    
    private String endTime;    
    private int routeId;
    

	public int getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(int journeyId) {
		this.journeyId = journeyId;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(int timeInterval) {
		this.timeInterval = timeInterval;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	@Override
	public String toString() {
		return "Journey [journeyId=" + journeyId + ", direction=" + direction + ", timeInterval=" + timeInterval
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", routeId=" + routeId + "]";
	}




}
