package assessmentAnswer;

import io.restassured.RestAssured;

public class ApiAutomation {

	PageConstants pgc = new PageConstants();

	public ApiAutomation() {

		// Below line will make a request to the server by specifying the method Type
		// and http method
		// This will return the Response from the server and Assert the status code as
		// 403 and extract the response body for printing
		String apiresponse = RestAssured.given().get(pgc.baseUrl + pgc.getLoginEndpoint).then().assertThat()
				.statusCode(403).extract().asString();
		System.out.println("Fetch api response ---- " + "\n" + apiresponse);
	}

	public static void main(String[] args) throws InterruptedException {

		new ApiAutomation();
	}

}
