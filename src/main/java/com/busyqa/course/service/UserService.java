package com.busyqa.course.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.busyqa.course.jpa.User;
import com.busyqa.course.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	UserRepository userRepository;

    @Transactional(readOnly=true)
    public Optional<User> findUser(int idUser){
        return this.userRepository.findById(idUser);
    }

    @Transactional(readOnly=true)
    public List<User> listUsers() {
        return this.userRepository.findAll();
    }	
    
    @Transactional(propagation=Propagation.REQUIRED)
    public User updateUser(User newUser,int idUser){

    	return this.userRepository.findById(idUser)
    			.map(user -> {
    				
    				user.setFirstName(newUser.getFirstName());
    				user.setLastName(newUser.getLastName());
    				user.setUsername(newUser.getUsername());
    				user.setPassword(newUser.getPassword());
    				user.setBirth(newUser.getBirth());
    				
    				return this.userRepository.save(user);    				
    			}).get();
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public User createUser(User user){
		
		user.setStatus("A"); /* Always created as Active */
		
		return this.userRepository.save(user);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void deleteUser(int idUser){   	
        this.userRepository.deleteById(idUser);
    }
    
    @SuppressWarnings("unused")
	private Date parseDate(String date) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(date);
    }    
}
