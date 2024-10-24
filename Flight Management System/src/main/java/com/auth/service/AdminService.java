package com.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.entity.AdminAllDetail;
import com.auth.entity.AdminDetail;
import com.auth.entity.CurrentlyLogged;
import com.auth.entity.FlightsAvailable;
import com.auth.entity.PassengersBooked;
import com.auth.entity.UserDetail;
import com.auth.repository.AdminAllDetailRepository;
import com.auth.repository.AdminLoginRepository;
import com.auth.repository.AdminRepository;
import com.auth.repository.BookingsRepository;
import com.auth.repository.LoggedInRepository;
import com.auth.repository.PassengerBookedRepository;
import com.auth.repository.UserRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepo;
	
	
	@Autowired
	private AdminLoginRepository adminLoginRepo;
	
	@Autowired
	private AdminAllDetailRepository adminAllDetail;
	
	@Autowired
    private BCryptPasswordEncoder bp;
	
	
   
   //checking if already exist mail in admin
   public boolean isAlreadyRegistered(String registerEmail) {
	   List<AdminDetail> ans = adminLoginRepo.findByRegisterEmail(registerEmail);
	   
	   return ans.size()==0 ? false : true;
	   
   }
   
   public AdminDetail adminLogin(String username,String password) {
  	 List<AdminDetail> users = adminLoginRepo.findByRegisterAdminName(username);
       for (AdminDetail user : users) {
           if (bp.matches(password, user.getRegisterPassword())) {
               return user;
           }
       }
       return null; // Return null if no matching user or password found
  }
   
   
   
   
   
   
   
   
   // for performing flight book related search
   public List<FlightsAvailable> fetch(String currentLocation,String Destination, String departure_date, int seats) {
   	List<String> ans=adminRepo.find(departure_date);
   	List<FlightsAvailable> flight=adminRepo.findByLocationDestination(currentLocation,Destination);
   	List<FlightsAvailable> flights=adminRepo.findByLocation(currentLocation,Destination,departure_date);
   	
   	System.out.println(flight);
   	System.out.println(ans);
   	System.out.println(flights);
   	
   	List<FlightsAvailable> fligh=adminRepo.findByLocationDestinationAndSeatsAvailable(currentLocation,Destination,departure_date,seats);
   	if(flights!=null) {
   		return flights;
   	}
   	System.out.println(flights);
   	return new ArrayList<>();
   }
   
   //for performing admin update operation
   public boolean isUpdateDone(String flightName, String arrival, String departure, String seatsAvailable, int costPrice) {
   	System.out.println(flightName);
   	int ans = adminRepo.updateFlightDetails(flightName, arrival, departure, seatsAvailable, costPrice);
   	return ans>0?true:false;
   }
   
   //for perfroming admin view operation
   public List<FlightsAvailable> isViewAvailable(String currentLocation,String Destination) {
   	List<FlightsAvailable> flights = adminRepo.findByLocationDestination(currentLocation,Destination);
   	if(flights==null) {
   		return new ArrayList<>();
   	}
   	return flights;
   	
   }
   
   //for performing admin search operation
   public List<FlightsAvailable> isSearchAvailable(String currentLocation,String Destination,String departure_date) {
   	List<FlightsAvailable> flights=adminRepo.findByLocation(currentLocation,Destination,departure_date);
   	if(flights==null) {
   		return new ArrayList<>();
   	}
   	return flights;
   	
   }
   
   
   
   
   
   
  
   //to search for given flightName
   
   public boolean isFlightNameFound(String flightName) {
   	List<FlightsAvailable> ans = adminRepo.isFlightNameFound(flightName);
   	
   	if(ans.size()==0) {
   		return false;
   	}
   	
   	return true;
   }
   
   
   //delete the flight with that name
   public int deleteFlight(String flightName) {
   	int ans = adminRepo.deleteFlight(flightName);
   	
   	return ans;
   }
   
   //find the number of seats in flightname
   public String seats(String flightName) {
   	List<String> ans =  adminRepo.seatsCapacity(flightName);
   	return ans.get(0);
   }
   
   //updating the seats in flights
   public int updateSeats(String flightName,String seat) {
   	int ans = adminRepo.updateSeats(flightName, seat);
   	
   	return ans;
   }
   
   //find all details of admin using admin email
   
   public List<AdminAllDetail> findAllAdminDetails(String email) {
	   	List<AdminAllDetail> ans= adminAllDetail.findAllAdminDetailsByEmail(email);
	   	
	   	if(ans.size()==0) {
	   		return new ArrayList<>();
	   				
	   	}
	   	return ans;
   }
   
}
