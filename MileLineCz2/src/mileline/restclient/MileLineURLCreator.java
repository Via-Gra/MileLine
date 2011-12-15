package mileline.restclient;

public class MileLineURLCreator {

	/**Creates an Url to get all timeStones at once.
	 * 
	 * @return URL to get all timeStones at once
	 */
	public static String getAllTimeStonesURL(){
		return Setup.ALLTIMESTONES;
	}
	
	/**Creates an Url to get specific timeStone by his id key.
	 * 
	 * @param id id key
	 * @return URL to get timeStone by specific id key
	 */
	public static String getTimeStoneByIdURL(String id){
		return Setup.TIMESTONEBYKOD + id;
	}

}
