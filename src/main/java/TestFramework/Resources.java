package TestFramework;

public class Resources {
	public static String placePostData(){
		String res = "/maps/api/place/add/json";
		return res;
	}
	public static String placeSearchData(){
		String resSearch = "/maps/api/place/nearbysearch/json";
		return resSearch;
	}
}
