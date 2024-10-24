package com.auth.component;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

class pair{
	String name;
	String seat;
	
	pair(String name,String seat){
		this.name=name;
		this.seat=seat;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSeat() {
		return seat;
	}
}

@Component
public class EmailHelper {
	
	//generate random price 
	public String generatePrice(int n) {
		Random random = new Random();
		String ans="";
		int val = random.nextInt(8) + 1;
		ans += val;
		for(int i=1;i<n;i++) {
			int dig = random.nextInt(10);
			ans += dig;
		}
		
		return ans;
	}
	
	//generate random seat for user
	public String generateSeat() {
		String alpha = "ABC";
		Random random = new Random();
		String ans="";
		int ind = random.nextInt(alpha.length());
		ans += alpha.charAt(ind);
		int dig = random.nextInt(15);
		ans += dig;
		
		return ans;
		
	}
	
	//generate available or not
	public String generateAvailability() {
		String[] options = {"Confirmed", "Pending", "On Hold",  "Waitlisted", "Completed"};
		Random random = new Random();
		int ind = random.nextInt(options.length);
		return options[ind];
	}
	
	//generate veg or non-veg
	public String generateMeal() {
		String arr[]= {"veg","non-veg"};
		Random random = new Random();
		int ind = random.nextInt(arr.length);
		return arr[ind];
	}
	
	//generate random number of mix 
	public String generate(int n) {
		
		String ans="";
		String chars ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		for(int i=0;i<n;i++) {
			int ind = random.nextInt(chars.length());
			ans += chars.charAt(ind);
			
		}
		
		return ans;
	}
	
	
	//generate random string of length n
	public String generateRandom(int n) {
		String ans="";
		Random random = new Random();
		for(int i=0;i<n;i++) {
			int dig = random.nextInt(10); // 0-9
			ans += dig;
		}
		
		return ans;
	}
	
	private static ArrayList<String> createAirportInfo(String code, String name) {
        ArrayList<String> info = new ArrayList<>();
        info.add(code);
        info.add(name);
        return info;
    }
	
	// if only 1 ticket booked
	public String help(
			ArrayList<String[]> passengers,
    		
    		
			String capturedFlightName,
	        String capturedFlightId,
	        String capturedPrice,
	        String from,
	        String to,
	        String arrivedTime,
	        String departTime,
	        
	        
	        
	        
	        Long travelHours,
	        Long travelDays,
	        String boardTime,
	        String parsedArrivalDate,
	        String parsedDepartDate,
	        
	        String totalPrice,
	        String seatPrice,
	        String foodPrice
			) 
	{
		HashMap<String, ArrayList<String>> airportMap = new HashMap<>();

        // Populate the airport map
        airportMap.put("Kannur", createAirportInfo("CNN", "Kannur International Airport"));
        airportMap.put("Agartala", createAirportInfo("IXA", "Maharaja Bir Bikram Airport"));
        airportMap.put("Agra", createAirportInfo("AGR", "Pandit Deen Dayal Upadhyay Airport"));
        airportMap.put("Ahmedabad", createAirportInfo("AMD", "Sardar Vallabhbhai Patel International Airport"));
        airportMap.put("Amritsar", createAirportInfo("ATQ", "Sri Guru Ram Dass Jee International Airport"));
        airportMap.put("Bagdogra", createAirportInfo("IXB", "Bagdogra Airport"));
        airportMap.put("Bengaluru", createAirportInfo("BLR", "Kempegowda International Airport"));
        airportMap.put("Bhubaneswar", createAirportInfo("BBI", "Biju Patnaik International Airport"));
        airportMap.put("Bhopal", createAirportInfo("BHO", "Raja Bhoj Airport"));
        airportMap.put("Chandigarh", createAirportInfo("IXC", "Chandigarh Airport"));
        airportMap.put("Chennai", createAirportInfo("MAA", "Chennai International Airport"));
        airportMap.put("Coimbatore", createAirportInfo("CJB", "Coimbatore International Airport"));
        airportMap.put("Dehradun", createAirportInfo("DED", "Jolly Grant Airport"));
        airportMap.put("Delhi", createAirportInfo("DEL", "Indira Gandhi International Airport"));
        airportMap.put("Dimapur", createAirportInfo("DMU", "Dimapur Airport"));
        airportMap.put("Goa", createAirportInfo("GOI", "Goa International Airport"));
        airportMap.put("Gorakhpur", createAirportInfo("GOP", "Gorakhpur Airport"));
        airportMap.put("Guwahati", createAirportInfo("GAU", "Lokpriya Gopinath Bordoloi International Airport"));
        airportMap.put("Hubli", createAirportInfo("HBX", "Hubli Airport"));
        airportMap.put("Hyderabad", createAirportInfo("HYD", "Rajiv Gandhi International Airport"));
        airportMap.put("Imphal", createAirportInfo("IMF", "Bir Tikendrajit International Airport"));
        airportMap.put("Indore", createAirportInfo("IDR", "Devi Ahilya Bai Holkar Airport"));
        airportMap.put("Jabalpur", createAirportInfo("JLR", "Jabalpur Airport"));
        airportMap.put("Jaipur", createAirportInfo("JAI", "Jaipur International Airport"));
        airportMap.put("Jammu", createAirportInfo("IXJ", "Jammu Airport"));
        airportMap.put("Kolkata", createAirportInfo("CCU", "Netaji Subhas Chandra Bose International Airport"));
        airportMap.put("Kozhikode", createAirportInfo("CCJ", "Calicut International Airport"));
        airportMap.put("Lucknow", createAirportInfo("LKO", "Chaudhary Charan Singh International Airport"));
        airportMap.put("Madurai", createAirportInfo("IXM", "Madurai Airport"));
        airportMap.put("Mangaluru", createAirportInfo("IXE", "Mangalore International Airport"));
        airportMap.put("Mumbai", createAirportInfo("BOM", "Chhatrapati Shivaji Maharaj International Airport"));
        airportMap.put("Nagpur", createAirportInfo("NAG", "Dr. Babasaheb Ambedkar International Airport"));
        airportMap.put("Patna", createAirportInfo("PAT", "Lok Nayak Jayaprakash Airport"));
        airportMap.put("Pune", createAirportInfo("PNQ", "Pune Airport"));
        airportMap.put("Raipur", createAirportInfo("RPR", "Swami Vivekananda Airport"));
        airportMap.put("Rajahmundry", createAirportInfo("RJA", "Rajahmundry Airport"));
        airportMap.put("Ranchi", createAirportInfo("IXR", "Birsa Munda Airport"));
        airportMap.put("Srinagar", createAirportInfo("SXR", "Sheikh ul-Alam International Airport"));
        airportMap.put("Surat", createAirportInfo("STV", "Surat International Airport"));
        airportMap.put("Thiruvananthapuram", createAirportInfo("TRV", "Trivandrum International Airport"));
        airportMap.put("Tiruchirappalli", createAirportInfo("TRZ", "Tiruchirappalli International Airport"));
        airportMap.put("Tuticorin", createAirportInfo("TCR", "Tuticorin Airport"));
        airportMap.put("Udaipur", createAirportInfo("UDR", "Maharana Pratap Airport"));
        airportMap.put("Vadodara", createAirportInfo("BDQ", "Vadodara Airport"));
        airportMap.put("Varanasi", createAirportInfo("VNS", "Lal Bahadur Shastri International Airport"));
        airportMap.put("Vijayawada", createAirportInfo("VGA", "Vijayawada Airport"));
        airportMap.put("Visakhapatnam", createAirportInfo("VTZ", "Visakhapatnam Airport"));
        airportMap.put("Gaya", createAirportInfo("GAY", "Gaya Airport"));
        airportMap.put("Jodhpur", createAirportInfo("JDH", "Jodhpur Airport"));
        airportMap.put("Silchar", createAirportInfo("IXS", "Silchar Airport"));
        airportMap.put("Belagavi", createAirportInfo("IXG", "Belagavi Airport"));
        airportMap.put("Gwalior", createAirportInfo("GWL", "Rajmata Vijaya Raje Scindia Airport"));

		
               
		        // Get the current instant
		        Instant now = Instant.now();
		        
		        // Convert to a specific time zone
		        ZonedDateTime indiaTime = now.atZone(ZoneId.of("Asia/Kolkata"));
		        
		        // Format the date and time
		        DateTimeFormatter format = DateTimeFormatter.ofPattern("E dd MMM yyyy hh:mm a");
		        String formattedDateTime = indiaTime.format(format);
		        
		        // Print the formatted date and time
		        System.out.println("Current Date and Time in Asia/Kolkata timezone: " + formattedDateTime);
		       
		        String companyName = "FlyHighHub.com";
		        String bookingID = "FLY"+generateRandom(10);
		        String bookingDate = formattedDateTime;
		        String route = from + " -> " + to;
		        String departureDate = parsedArrivalDate;
		        String flightName = capturedFlightId + " " + capturedFlightName;
		        String fromCode = airportMap.get(from).get(0);;
		        String arrivalDateTime = parsedArrivalDate + " " + arrivedTime;
		        String currentAirport =  airportMap.get(from).get(1);
		        String baggage = "Baggage : Cabin - 7Kg | Check-in - 15Kg |  Class: Business";
		        String pnr = "ABC123";
		        String toCode = airportMap.get(to).get(0);;
		        String departureTime = parsedDepartDate + " " + departTime;
		        String destinationAirport =  airportMap.get(to).get(1);
		        String basicFare = capturedPrice;
		        String seatFare = seatPrice;
		        String foodFare = foodPrice;
		        if(foodPrice == null || foodPrice.length()==0 || foodPrice.isBlank() || foodPrice.isEmpty()) {
		        	foodFare = String.valueOf(0);
		        }
		        String taxes = generatePrice(2);
		        String insuranceFee = generatePrice(1);
		        String rescheduleCharges = generatePrice(2);
		        Integer extra = Integer.valueOf(taxes) + Integer.valueOf(insuranceFee) + Integer.valueOf(rescheduleCharges);
		        Integer whole = extra + Integer.valueOf(totalPrice);
		        String totalFare = String.valueOf(whole);
		        String paymentStatus = "Paid";
		        String paymentMode = "Visa Card";
		        

		        // Sample passenger details
		        
		        

		        StringBuilder passengerDetails = new StringBuilder();
		        for (String[] passenger : passengers) {
		            passengerDetails.append("<tr style=\"border: 1px solid #dee2e6;\">\n")
		                .append("<td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">")
		                .append(passenger[0]).append("</td>\n")
		                .append("<td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">")
		                .append(passenger[1]).append("</td>\n")
		                .append("<td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">")
		                .append(passenger[2]).append("</td>\n")
		                .append("<td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">")
		                .append(passenger[3]).append("</td>\n")
		                .append("<td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">")
		                .append(passenger[4]).append("</td>\n")
		                .append("<td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">")
		                .append(passenger[5]).append("</td>\n")
		                .append("</tr>\n");
		        }

		        String htmlContent = 
		            "<!DOCTYPE html>\n" +
		            "<html lang=\"en\">\n" +
		            "<head>\n" +
		            "    <meta charset=\"UTF-8\">\n" +
		            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
		            "    <title>" + companyName + "</title>\n" +
		            "</head>\n" +
		            "<body style=\"background-color: #F6F6F6; margin: 0; padding: 0;\">\n" +
		            "    <div class=\"container\" style=\"width: 80%; margin-right: auto; margin-left: auto;\">\n" +
		            "        <div class=\"brand-section\" style=\"background-color: #0d1033; padding: 10px 40px;\">\n" +
		            "            <div class=\"row\" style=\"display: flex; flex-wrap: wrap;\">\n" +
		            "                <div class=\"col-6\" style=\"width: 50%; flex: 0 0 auto;\">\n" +
		            "                    <h1 class=\"text-white\" style=\"margin: 0; padding: 0; color: #fff;\">" + companyName + "</h1>\n" +
		            "                </div>\n" +
		            "                <div class=\"col-6\" style=\"width: 50%; flex: 0 0 auto;\">\n" +
		            "                    <div class=\"company-details\" style=\"float: right; text-align: right;\">\n" +
		            "                        <p class=\"text-white\" style=\"margin: 0; padding: 0; color: #fff;\">E-Ticket</p>\n" +
		            "                        <p class=\"text-white\" style=\"margin: 0; padding: 0; color: #fff;\">Booking ID - " + bookingID + "</p>\n" +
		            "                        <p class=\"text-white\" style=\"margin: 0; padding: 0; color: #fff;\">Booking on - " + bookingDate + "</p>\n" +
		            "                    </div>\n" +
		            "                </div>\n" +
		            "            </div>\n" +
		            "        </div>\n" +
		            "        <div class=\"body-section\" style=\"padding: 16px; border: 1px solid gray;\">\n" +
		            "            <div class=\"row\" style=\"display: flex; flex-wrap: wrap;\">\n" +
		            "                <div class=\"col-6\" style=\"width: 50%; flex: 0 0 auto;\">\n" +
		            "                    <h2 class=\"heading\" style=\"margin-bottom: 8px;\">" + route + "</h2>\n" +
		            "                    <p class=\"sub-heading\" style=\"color: #262626; margin-bottom: 5px; color:#2196f3; font-weight: bolder;\">" + departureDate + "</p>\n" +
		            "                </div>\n" +
		            "            </div>\n" +
		            "            <div class=\"row\" style=\"display: flex; flex-wrap: wrap;\">\n" +
		            "                <div class=\"col-6\" style=\"width: 50%; flex: 0 0 auto;\">\n" +
		            "                    <h2 class=\"heading\" style=\"font-size: 20px; margin-bottom: 8px;\">" + flightName + "</h2>\n" +
		            "                    <p class=\"sub-heading\" style=\"color: #262626; margin-bottom: 5px;font-weight:900;\">" + fromCode + ":" + arrivalDateTime + "</p>\n" +
		            "                    <p class=\"sub-heading\" style=\"color: #262626; margin-bottom: 5px;\">" + currentAirport + "</p>\n" +
		            "                    <p class=\"sub-heading\" style=\"color: #262626; margin-bottom: 5px; color:#2196f3;\">" + baggage + "</p>\n" +
		            "                </div>\n" +
		            "                <div class=\"col-6\" style=\"width: 50%; flex: 0 0 auto;\">\n" +
		            "                    <p class=\"sub-heading\" style=\"color: #262626; margin-bottom: 5px;font-weight:900;\">" + "PNR" + ": " + pnr + "</p>\n" +
		            "                    <p class=\"sub-heading\" style=\"color: #262626; margin-bottom: 5px; font-weight:900;\">" + toCode + ":" + departureTime + "</p>\n" +
		            "                    <p class=\"sub-heading\" style=\"color: #262626; margin-bottom: 5px;\">" + destinationAirport + "</p>\n" +
		            "                </div>\n" +
		            "            </div>\n" +
		            "        </div>\n" +
		            "        <div class=\"body-section\" style=\"padding: 16px; border: 1px solid gray;\">\n" +
		            "            <h3 class=\"heading\" style=\"font-size: 20px; margin-bottom: 8px;\">Traveller Details</h3>\n" +
		            "            <br>\n" +
		            "            <table class=\"table-bordered\" style=\"background-color: #fff; width: 100%; border-collapse: collapse;\">\n" +
		            "                <thead>\n" +
		            "                    <tr style=\"border: 1px solid #111; background-color: #f2f2f2;\">\n" +
		            "                        <th style=\"border: 1px solid #111; vertical-align: middle !important; text-align: center; padding-top: 8px; padding-bottom: 8px;\">Passengers</th>\n" +
		            "                        <th style=\"border: 1px solid #111; vertical-align: middle !important; text-align: center; padding-top: 8px; padding-bottom: 8px;\">PNR</th>\n" +
		            "                        <th style=\"border: 1px solid #111; vertical-align: middle !important; text-align: center; padding-top: 8px; padding-bottom: 8px;\">Ticket No.</th>\n" +
		            "                        <th style=\"border: 1px solid #111; vertical-align: middle !important; text-align: center; padding-top: 8px; padding-bottom: 8px;\">Seat</th>\n" +
		            "                        <th style=\"border: 1px solid #111; vertical-align: middle !important; text-align: center; padding-top: 8px; padding-bottom: 8px;\">Meal</th>\n" +
		            "                        <th style=\"border: 1px solid #111; vertical-align: middle !important; text-align: center; padding-top: 8px; padding-bottom: 8px;\">Status</th>\n" +
		            "                    </tr>\n" +
		            "                </thead>\n" +
		            "                <tbody>\n" +
		            passengerDetails.toString() +
		            "                </tbody>\n" +
		            "            </table>\n" +
		            "            <br>\n" +
		            "            <h3 class=\"heading\" style=\"font-size: 20px; margin-bottom: 8px;\">Fare Details</h3>\n" +
		            "            <table class=\"table-bordered\" style=\"background-color: #fff; width: 100%; border-collapse: collapse;\">\n" +
		            "                <thead>\n" +
		            "                    <tr style=\"border: 1px solid #111; background-color: #f2f2f2;\">\n" +
		            "                        <th style=\"border: 1px solid #111; vertical-align: middle !important; text-align: center; padding-top: 8px; padding-bottom: 8px;\">Fare Summary</th>\n" +
		            "                        <th style=\"border: 1px solid #111; vertical-align: middle !important; text-align: center; padding-top: 8px; padding-bottom: 8px;\">Amount</th>\n" +
		            "                    </tr>\n" +
		            "                </thead>\n" +
		            "                <tbody>\n" +
		            "                    <tr style=\"border: 1px solid #dee2e6;\">\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">Basic Fare</td>\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">" + "&#8377;" + basicFare + "</td>\n" +
		            "                    </tr>\n" +
		            "                    <tr style=\"border: 1px solid #dee2e6;\">\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">Seat Fare</td>\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">" + "&#8377;" + seatFare + "</td>\n" +
		            "                    </tr>\n" +
		            "                    <tr style=\"border: 1px solid #dee2e6;\">\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">Food Fare</td>\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">" + "&#8377;" + foodFare + "</td>\n" +
		            "                    </tr>\n" +
		            "                    <tr style=\"border: 1px solid #dee2e6;\">\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">Taxes</td>\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">" + "&#8377;" + taxes + "</td>\n" +
		            "                    </tr>\n" +
		            "                    <tr style=\"border: 1px solid #dee2e6;\">\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">Insurance Fee</td>\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">" + "&#8377;" + insuranceFee + "</td>\n" +
		            "                    </tr>\n" +
		            "                    <tr style=\"border: 1px solid #dee2e6;\">\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">Reschedule Charges</td>\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px;\">" + "&#8377;" + rescheduleCharges + "</td>\n" +
		            "                    </tr>\n" +
		            "                    <tr style=\"border: 1px solid #dee2e6;\">\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px; font-weight: bold;\">Total Fare</td>\n" +
		            "                        <td style=\"border: 1px solid #1111112d; vertical-align: middle !important; text-align: center; padding-top: 08px; padding-bottom: 08px; font-weight: bold;\">" + "&#8377;" + totalFare + "</td>\n" +
		            "                    </tr>\n" +
		            "                </tbody>\n" +
		            "            </table>\n" +
		            "            <br>\n" +
		            "            <h3 class=\"heading\" style=\"font-size: 20px; margin-bottom: 8px;\">Payment Details</h3>\n" +
		            "            <p class=\"sub-heading\" style=\"color: #262626; margin-bottom: 5px;\">Payment Status: " + paymentStatus + "</p>\n" +
		            "            <p class=\"sub-heading\" style=\"color: #262626; margin-bottom: 5px;\">Payment Mode: " + paymentMode + "</p>\n" +
		            "        </div>\n" +
		            "        <div class=\"body-section\" style=\"padding: 16px; border: 1px solid gray;\">\n" +
		            "            <p>&copy; " + companyName + " - All rights reserved.</p>\n" +
		            "        </div>\n" +
		            "    </div>\n" +
		            "</body>\n" +
		            "</html>";

		        return htmlContent;
		    }
	
	
	
	
	    
	
    public void sendEmail(String email,String userId,
    		String uf1,String ul1,Integer age_1,
    		String uf2,String ul2,Integer age_2,
    		String uf3,String ul3,Integer age_3,
    		String uf4,String ul4,Integer age_4,
    		
			String capturedFlightName,
	        String capturedFlightId,
	        String capturedPrice,
	        String from,
	        String to,
	        String arrivalTime,
	        String departTime,
	        
	        String seatIdName1,
	        String seatIdName2,
	        String seatIdName3,
	        String seatIdName4,
	        
	        
	        Long travelHours,
	        Long travelDays,
	        String boardingTime,
	        String parsedArrivalDate,
	        String parsedDepartDate,
	        
	        String totalPrice,
	        String seatPrice,
	        String foodPrice
    		
    		){
    	 try{
	            
	        	
    		 HashMap<String, ArrayList<String>> airportMap = new HashMap<>();

    	        // Populate the airport map
    	        airportMap.put("Kannur", createAirportInfo("CNN", "Kannur International Airport"));
    	        airportMap.put("Agartala", createAirportInfo("IXA", "Maharaja Bir Bikram Airport"));
    	        airportMap.put("Agra", createAirportInfo("AGR", "Pandit Deen Dayal Upadhyay Airport"));
    	        airportMap.put("Ahmedabad", createAirportInfo("AMD", "Sardar Vallabhbhai Patel International Airport"));
    	        airportMap.put("Amritsar", createAirportInfo("ATQ", "Sri Guru Ram Dass Jee International Airport"));
    	        airportMap.put("Bagdogra", createAirportInfo("IXB", "Bagdogra Airport"));
    	        airportMap.put("Bengaluru", createAirportInfo("BLR", "Kempegowda International Airport"));
    	        airportMap.put("Bhubaneswar", createAirportInfo("BBI", "Biju Patnaik International Airport"));
    	        airportMap.put("Bhopal", createAirportInfo("BHO", "Raja Bhoj Airport"));
    	        airportMap.put("Chandigarh", createAirportInfo("IXC", "Chandigarh Airport"));
    	        airportMap.put("Chennai", createAirportInfo("MAA", "Chennai International Airport"));
    	        airportMap.put("Coimbatore", createAirportInfo("CJB", "Coimbatore International Airport"));
    	        airportMap.put("Dehradun", createAirportInfo("DED", "Jolly Grant Airport"));
    	        airportMap.put("Delhi", createAirportInfo("DEL", "Indira Gandhi International Airport"));
    	        airportMap.put("Dimapur", createAirportInfo("DMU", "Dimapur Airport"));
    	        airportMap.put("Goa", createAirportInfo("GOI", "Goa International Airport"));
    	        airportMap.put("Gorakhpur", createAirportInfo("GOP", "Gorakhpur Airport"));
    	        airportMap.put("Guwahati", createAirportInfo("GAU", "Lokpriya Gopinath Bordoloi International Airport"));
    	        airportMap.put("Hubli", createAirportInfo("HBX", "Hubli Airport"));
    	        airportMap.put("Hyderabad", createAirportInfo("HYD", "Rajiv Gandhi International Airport"));
    	        airportMap.put("Imphal", createAirportInfo("IMF", "Bir Tikendrajit International Airport"));
    	        airportMap.put("Indore", createAirportInfo("IDR", "Devi Ahilya Bai Holkar Airport"));
    	        airportMap.put("Jabalpur", createAirportInfo("JLR", "Jabalpur Airport"));
    	        airportMap.put("Jaipur", createAirportInfo("JAI", "Jaipur International Airport"));
    	        airportMap.put("Jammu", createAirportInfo("IXJ", "Jammu Airport"));
    	        airportMap.put("Kolkata", createAirportInfo("CCU", "Netaji Subhas Chandra Bose International Airport"));
    	        airportMap.put("Kozhikode", createAirportInfo("CCJ", "Calicut International Airport"));
    	        airportMap.put("Lucknow", createAirportInfo("LKO", "Chaudhary Charan Singh International Airport"));
    	        airportMap.put("Madurai", createAirportInfo("IXM", "Madurai Airport"));
    	        airportMap.put("Mangaluru", createAirportInfo("IXE", "Mangalore International Airport"));
    	        airportMap.put("Mumbai", createAirportInfo("BOM", "Chhatrapati Shivaji Maharaj International Airport"));
    	        airportMap.put("Nagpur", createAirportInfo("NAG", "Dr. Babasaheb Ambedkar International Airport"));
    	        airportMap.put("Patna", createAirportInfo("PAT", "Lok Nayak Jayaprakash Airport"));
    	        airportMap.put("Pune", createAirportInfo("PNQ", "Pune Airport"));
    	        airportMap.put("Raipur", createAirportInfo("RPR", "Swami Vivekananda Airport"));
    	        airportMap.put("Rajahmundry", createAirportInfo("RJA", "Rajahmundry Airport"));
    	        airportMap.put("Ranchi", createAirportInfo("IXR", "Birsa Munda Airport"));
    	        airportMap.put("Srinagar", createAirportInfo("SXR", "Sheikh ul-Alam International Airport"));
    	        airportMap.put("Surat", createAirportInfo("STV", "Surat International Airport"));
    	        airportMap.put("Thiruvananthapuram", createAirportInfo("TRV", "Trivandrum International Airport"));
    	        airportMap.put("Tiruchirappalli", createAirportInfo("TRZ", "Tiruchirappalli International Airport"));
    	        airportMap.put("Tuticorin", createAirportInfo("TCR", "Tuticorin Airport"));
    	        airportMap.put("Udaipur", createAirportInfo("UDR", "Maharana Pratap Airport"));
    	        airportMap.put("Vadodara", createAirportInfo("BDQ", "Vadodara Airport"));
    	        airportMap.put("Varanasi", createAirportInfo("VNS", "Lal Bahadur Shastri International Airport"));
    	        airportMap.put("Vijayawada", createAirportInfo("VGA", "Vijayawada Airport"));
    	        airportMap.put("Visakhapatnam", createAirportInfo("VTZ", "Visakhapatnam Airport"));
    	        airportMap.put("Gaya", createAirportInfo("GAY", "Gaya Airport"));
    	        airportMap.put("Jodhpur", createAirportInfo("JDH", "Jodhpur Airport"));
    	        airportMap.put("Silchar", createAirportInfo("IXS", "Silchar Airport"));
    	        airportMap.put("Belagavi", createAirportInfo("IXG", "Belagavi Airport"));
    	        airportMap.put("Gwalior", createAirportInfo("GWL", "Rajmata Vijaya Raje Scindia Airport"));
	            
	        	
	            Properties props = new Properties();
	            props.put("mail.smtp.auth","true");
	            props.put("mail.smtp.starttls.enable","true");
	            props.put("mail.smtp.host","smtp.gmail.com");
	            props.put("mail.smtp.port","587");

	            Session session = Session.getInstance(props,new Authenticator()
	            {
	                protected PasswordAuthentication getPasswordAuthentication()
	                {
	                    return new PasswordAuthentication("suyashkhare9698@gmail.com","pugchqxeyiuzdagi");
	                }
	            });

	            Message msg = new MimeMessage(session);

	            msg.setFrom(new InternetAddress("suyashkhare9698@gmail.com",false));
	            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
	            msg.setSubject("Your Flight Tickets for "+ airportMap.get(from).get(0) + "-" + airportMap.get(to).get(0));
	            msg.setSentDate(new Date());


	            MimeBodyPart messageBody = new MimeBodyPart();
	            
	            ArrayList<String[]> passengers  = new ArrayList<>();
	            
	            if (uf1 != null && ul1 != null  && age_1 != null) {
	            	
	            	if(seatIdName1 != null && seatIdName1.length()==0 && seatIdName1.isEmpty()) {
	            		seatIdName1 = generateSeat();
	            	}
	            	String name = uf1+" "+ul1;
	            	String pnr = generate(6);
	            	String ticket = pnr + "/" + generateRandom(1) + "/" + generateRandom(1);
	            	String[] arr = {name,pnr,ticket,seatIdName1,generateMeal(),generateAvailability()};
	            	passengers.add(arr);
	            }
	            if (uf2 != null && ul2 != null  && age_2 != null) {
	            	if(seatIdName2 != null && seatIdName2.length()==0 && seatIdName2.isEmpty()) {
	            		seatIdName2 = generateSeat();
	            	}
	            	String name = uf2+" "+ul2;
	            	String pnr = generate(6);
	            	String ticket = pnr + "/" + generateRandom(1) + "/" + generateRandom(1);
	            	String[] arr = {name,pnr,ticket,seatIdName2,generateMeal(),generateAvailability()};
	            	passengers.add(arr);
	            }
	            if (uf3 != null && ul3 != null  && age_3 != null) {
	            	if(seatIdName3 != null && seatIdName3.length()==0 && seatIdName3.isEmpty()) {
	            		seatIdName3 = generateSeat();
	            	}
	            	String name = uf3+" "+ul3;
	            	String pnr = generate(6);
	            	String ticket = pnr + "/" + generateRandom(1) + "/" + generateRandom(1);
	            	String[] arr = {name,pnr,ticket,seatIdName3,generateMeal(),generateAvailability()};
	            	passengers.add(arr);
	            }
	            if (uf4 != null && ul4 != null  && age_4 != null) {
	            	if(seatIdName4 != null && seatIdName4.length()==0 && seatIdName4.isEmpty()) {
	            		seatIdName4 = generateSeat();
	            	}
	            	String name = uf4+" "+ul4;
	            	String pnr = generate(6);
	            	String ticket = pnr + "/" + generateRandom(1) + "/" + generateRandom(1);
	            	String[] arr = {name,pnr,ticket,seatIdName4,generateMeal(),generateAvailability()};
	            	passengers.add(arr);
	            }
	            
	            String content = help(passengers,capturedFlightName,capturedFlightId,
	            		capturedPrice,from,to,arrivalTime,departTime,travelHours,travelDays,boardingTime,parsedArrivalDate,
	            		parsedDepartDate,totalPrice,seatPrice,foodPrice);
	            

	            messageBody.setContent(content,"text/html");


	            Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(messageBody);
	            
	            

	            msg.setContent(multipart);
	            Transport.send(msg);


	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    }
    
    
}
