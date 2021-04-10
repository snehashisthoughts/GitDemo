package basic_rest;

import file.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		
		JsonPath js2 = new JsonPath(Payload.ComplexAPI());
		int coursecount = js2.getInt("courses.size()");
		System.out.println(coursecount);
		int totalamount_eachcourse = 0;
		int purchaseamount = js2.getInt("dashboard.purchaseAmount");
		for(int i=0;i<coursecount;i++)
		{
			String Course_Title = js2.getString("courses["+i+"].title");
			int Course_Price = js2.getInt("courses["+i+"].price");
			int Course_copies = js2.getInt("courses["+i+"].copies");
			if(Course_Title.equalsIgnoreCase("RPA"))
			{
				System.out.println("No of course copies for RPA : "+Course_copies);
			}
			
			System.out.println("Course Title: "+Course_Title + " Course Price: "+Course_Price + " Course Copies: "+Course_copies);
			totalamount_eachcourse = totalamount_eachcourse + (Course_Price*Course_copies);
			
		}
		if(purchaseamount == totalamount_eachcourse)
		{
			System.out.println("Total amount of each courses is equal to purchase amount :" +totalamount_eachcourse );
		}
		else
		{
			System.out.println("Total amount of each courses is not equal to purchase amount :" +totalamount_eachcourse );
		}
		

	}

}
