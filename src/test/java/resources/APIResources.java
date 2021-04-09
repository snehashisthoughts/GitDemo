package resources;
//enum is a special class in java which has a collection of constants and methods
public enum APIResources {

	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resources;
	APIResources(String resources)
	{
		this.resources = resources;
	}
	public String getResource()
	{
		return resources;
	}
}
