package com.infy.api;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.CabBookingDTO;
import com.infy.exception.InfyCabException;
import com.infy.service.BookingService;

@RestController
@RequestMapping(value = "/Booking")
public class CabBookingAPI {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private Environment environment;

/*This is a REST controller method to book a cab. It accepts booking details as part of HTTP request body.
It invokes the bookCab() method of BookingServiceImpl, which returns a booking id.
It returns an object of ResponseEntity created using success message and HTTP status code as CREATED.
If any exception occurs its catch them and throw ResponseStatusException with HTTP status code as BAD_REQUEST along with message and the exception. */
	@PostMapping(value = "/customers")
	public ResponseEntity<String> bookCab(@RequestBody CabBookingDTO cabBookingDTO) throws InfyCabException {
		Integer	bookingId=bookingService.bookCab(cabBookingDTO);
		String message=environment.getProperty("API.BOOKING_SUCCESSFUL")+bookingId;
		return new ResponseEntity<>(message,HttpStatus.OK);
	}

/*This is a REST controller method to get cab booking details based on mobile number of user. 
It accepts mobileNo as path variable.
It invokes the getDetails() methods of BookingService which returns a List<CabBooking>.
It returns an object of ResponseEntity created using List<CabBooking> and HTTP status code as OK.
If any exception occurs it catches them and throw ResponseStatusException with HTTP status code as BAD_REQUEST along with message and the exception. */
	@GetMapping(value = "/customers/{mobileNo}")
	public ResponseEntity<List<CabBookingDTO>> getBookingDetails(@PathVariable Long mobileNo) throws InfyCabException {
		List<CabBookingDTO> cabBookingList= bookingService.getBookingDetails(mobileNo);
		return new ResponseEntity<>(cabBookingList, HttpStatus.OK);
		
		
		
	}

/*This is a REST controller method to cancel cab booking based on bookingId. 
It accepts bookingId as path variable.
It invokes cancelBooking() method of BookingService.
It returns an object of ResponseEntity created using success message and HTTP status code as OK. 
If any exception occurs it catches them and throw ResponseStatusException with HTTP status code as BAD_REQUEST along with message and the exception.*/
	@PutMapping(value = "/customers/{bookingId}")
	public ResponseEntity<String> cancelBooking(@PathVariable Integer bookingId) throws InfyCabException {
		bookingService.cancelBooking(bookingId);
		String message=environment.getProperty("API.BOOKING_CANCELLED")+bookingId;
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
