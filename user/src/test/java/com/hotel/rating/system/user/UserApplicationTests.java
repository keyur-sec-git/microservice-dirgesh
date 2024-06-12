package com.hotel.rating.system.user;

import com.hotel.rating.system.user.external.services.RatingServiceFeignClient;
import com.hotel.rating.system.user.model.RequestBean.RatingRequestBean;
import com.hotel.rating.system.user.model.ResponseBean.ServiceResponseBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingServiceFeignClient ratingServiceFeignClient;

//	@Test
//	void createRating() {
//		RatingRequestBean ratingRequestBean = RatingRequestBean.builder()
//				.userId("")
//				.hotelId("")
//				.rating(9)
//				.feedback("Excellent Service (Created using feign client)")
//				.build();
//		ServiceResponseBean savedRating = ratingServiceFeignClient.createRating(ratingRequestBean);
//		System.out.println("New rating created");
//	}

}
