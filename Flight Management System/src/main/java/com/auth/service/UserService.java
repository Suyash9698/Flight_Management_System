package com.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.entity.CurrentlyLogged;
import com.auth.entity.FlightsAvailable;
import com.auth.entity.PassengersBooked;
import com.auth.entity.UserDetail;
import com.auth.repository.BookingsRepository;
import com.auth.repository.LoggedInRepository;
import com.auth.repository.PassengerBookedRepository;
import com.auth.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	
	@Autowired
	private LoggedInRepository logRepo;
	
	
	@Autowired
    private BCryptPasswordEncoder bp;
	
	@Autowired
	private BookingsRepository bookRepo;
	
	@Autowired
	private PassengerBookedRepository passRepo;
    
    public UserDetail login(String username,String password) {
    	 List<UserDetail> users = repo.findByRegisterName(username);
         for (UserDetail user : users) {
             if (bp.matches(password, user.getRegisterPassword())) {
                 return user;
             }
         }
         return null; // Return null if no matching user or password found
    }
    
    
    
    
    
    public boolean isEmailRegistered(String email) {
    	List<UserDetail> users = repo.findByRegisterEmail(email);
    	System.out.println(users);
    	if(users.size()==0) {
    		return false;
    	}
    	return true;
    }
    
    //find all userDetails by email for resetting
    public UserDetail giveMeAllUsersByEmail(String email) {
    	List<UserDetail> users = repo.findByRegisterEmail(email);
    	if(users.size()==0) {
    		return null;
    	}
    	return users.get(0);
    }
    
    
    
    //to get all the passengers names registered so far
    public CurrentlyLogged fetchLogged() {
    	CurrentlyLogged curr = logRepo.select();
    	return curr;
    }
    
    
    // for finding all userId current user who booked
    public List<Integer> findAllCurrentUserId(String currentMail) {
    	List<Integer> ans = bookRepo.findAllIdsByRegisterEmail(currentMail);
    	if(ans == null || ans.size()==0) {
    		return new ArrayList<>();
    	}
    	
    	return ans;
    }
    
    //for deleting userId with specific userId who booked
    public int deleteUserBookId(int uid) {
    	int ans=bookRepo.deleteUid(uid);
    	return ans;
    }
    
    
    //find all passengers list with the userId
    public List<PassengersBooked> findAllPassengers(List<Integer> currentUserIds){
    	List<PassengersBooked> ans = passRepo.findByCurrentUserIds(currentUserIds);
    	if(ans == null || ans.size()==0) {
    		return new ArrayList<>();
    	}
    	
    	return ans;
    }
    
    //delete the all list with userId
    public int deleteAllPassengers(Integer currentUserIds){
    	int ans = passRepo.deleteByUserId(currentUserIds);
    	
    	return ans;
    }
    
    
    
    //updating the password for resetting
    public int updatingPassword(String mail,String pass) {
    	int ans = repo.updateByRegisterEmail(mail, pass);
    	 
    	return ans;
    }
    
    
    
    //updating the profile 
    public int updateEntry(String registerName,String registerEmail) {
    	int ans = bookRepo.updateEntry(registerName, registerEmail);
    	
    	return ans;
    }
    
  //updating the profile 
    public int updateDetailsEntry(String registerName,String registerEmail,String dob) {
    	int ans = repo.updateDetails(registerName, registerEmail,dob);
    	
    	return ans;
    }
    
    //find all bookings
    public List<PassengersBooked> findAllPassengersBookings(){
    	List<PassengersBooked> ans = passRepo.findByAllUserIds();
    	if(ans == null || ans.size()==0) {
    		return new ArrayList<>();
    	}
    	
    	return ans;
    }
    
    public int deleteAllPassengersSpecificToFlight(String flightName) {
    	String ids[] = flightName.split(" ");
    	String id = ids[1];
    	int ans=passRepo.deleteAllPassengersSpecificToFlight(id);
    	
    	return ans;
    }
    
    //finding all userIds specific to flight
    public List<Integer> findAllUserIdsSpecificToFlight(String flightName){
    	
    	String ids[] = flightName.split(" ");
    	String id = ids[1];
    	List<Integer> ans=passRepo.findAllIdsSpecificToFlight(id);
    	if(ans.size()==0) {
    		return new ArrayList<>();
    	}
    	return ans;
    }
    
    //deleting all ids booked
    public int deletedBookingCurrentUserIds(List<Integer> ids) {
    	int ans=bookRepo.deleteByCurrentUserIds(ids);
    	
    	return ans;
    	
    }
    
    public List<String> findAllEmailIdsWithThisFlight(String flightName){
    	List<String> ans = passRepo.findAllEmailIdsWithThisFlight(flightName);
    	
    	if(ans.size()==0)  return new ArrayList<>();
    	
    	return ans;
    }
    
    public String findFlightWithUserId(int uid) {
    	List<PassengersBooked> ans = passRepo.findFlightWithUserId(uid);
    	
    	return ans.get(0).getFlightId() + " " + ans.get(0).getFlightName();
    }

}
