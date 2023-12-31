package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.CabBookingDTO;
import com.infy.entity.CabBooking;
import com.infy.exception.InfyCabException;
import com.infy.repository.BookingRepository;
import com.infy.repository.FareRepository;

@Service("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	FareRepository fareRepository;

	@Override
	public Integer bookCab(CabBookingDTO bookingDTO) throws InfyCabException {
		Float fare = fareRepository.findFareBySourceAndDestination(bookingDTO.getSource(), bookingDTO.getDestination());
		if (fare == null) {
			throw new InfyCabException("BookingService.INVALID_SERVICE_AREA");
		}
		bookingDTO.setFare(fare);
		CabBooking cabBooking = new CabBooking();
		cabBooking.setDestination(bookingDTO.getDestination());
		cabBooking.setSource(bookingDTO.getSource());
		cabBooking.setFare(bookingDTO.getFare());
		cabBooking.setTravelDate(bookingDTO.getTravelDate());
		cabBooking.setUserMobile(bookingDTO.getUserMobile());
		cabBooking.setStatus(bookingDTO.getStatus());
		return bookingRepository.save(cabBooking).getBookingId();
	}

	@Override
	public List<CabBookingDTO> getBookingDetails(Long mobileNo) throws InfyCabException {
		List<CabBookingDTO> cabBookingDTOs = new ArrayList<>();
		List<CabBooking> cabBookings = bookingRepository.findByUserMobile(mobileNo);
		if (cabBookings.isEmpty())
			throw new InfyCabException("BookingService.BOOKINGS_NOT_FOUND");
		cabBookings.forEach( cabBooking -> {
			CabBookingDTO cabBookingDTO = new CabBookingDTO();
			cabBookingDTO.setBookingId(cabBooking.getBookingId());
			cabBookingDTO.setDestination(cabBooking.getDestination());
			cabBookingDTO.setSource(cabBooking.getSource());
			cabBookingDTO.setFare(cabBooking.getFare());
			cabBookingDTO.setStatus(cabBooking.getStatus());
			cabBookingDTO.setTravelDate(cabBooking.getTravelDate());
			cabBookingDTO.setUserMobile(cabBooking.getUserMobile());
			cabBookingDTOs.add(cabBookingDTO);
		});
		
		return cabBookingDTOs;
	}

	@Override
	public Integer cancelBooking(Integer bookingId) throws InfyCabException {
		Optional<CabBooking> optional = bookingRepository.findById(bookingId);
		optional.orElseThrow(() -> new InfyCabException("BookingService.BOOKINGS_NOT_FOUND"));
		optional.get().setStatus('C');
		return optional.get().getBookingId();
	}

}
