package org.cenidet.cc.publictransit.dto;

public class Route {

	private int routeId;
    private String name;
    private String color;
    private String companyId;
    
    

    public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "Route [name=" + name + ", color=" + color + ", companyId=" + companyId + "]";
	}




}

