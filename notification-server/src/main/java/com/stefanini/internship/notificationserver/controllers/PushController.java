package com.stefanini.internship.notificationserver.controllers;

import com.stefanini.internship.notificationserver.model.dto.OurSubscription;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
This is hardcoded example:
 */

@RestController
public class PushController {

	@GetMapping("/test")
	public HttpResponse pushTest() throws Exception {

		String payload = "{\n" +
				"    \"notification\": {\n" +
				"        \"title\": \"Place request approved\",\n" +
				"        \"image\": \"https://www.intheblack.com/-/media/intheblack/allimages/sponsored-content/2018/dexus-office-space.jpg\",\n" +
				"        \"icon\": \"https://png.pngtree.com/svg/20160308/3cb5ad269d.png\",\n" +
				"        \"body\": \"Manager MPECARI has approved your place request in office 406 on place (60-30)\",\n" +
				"        \"silent\": true,\n" +
				"        \"data\": {\n" +
				"            \"actionLinks\":{\n" +
				"                \"default\":\"requestedplace#PlaceRequest14\",\n" +
				"                \"approve\":\"requestedplace/14/approve\",\n" +
				"                \"decline\":\"requestedplace/14/decline\"\n" +
				"            }\n" +
				"        \n" +
				"        },\n" +
				"        \"actions\":[]\n" +
				"    }\n" +
				"}\n";

		OurSubscription sub = new OurSubscription("https://fcm.googleapis.com/fcm/send/ddzw196twLk:APA91bHa8Rf7bsKMiThlGF0IuQbxoXE4N9EkrdkQXzsJFQpmMywAC0JPM-rxJZMU44u5-ixbypu3XT9r2AvzEDaYed1rHwKJcJOGl5kEeSSUv08cEziB9gTqzTFZXqa4NyOrttw2XeX0",
				new OurSubscription.Keys("BOt9DS8mlCYjE-Rz2ZygFRs0krG_nv-jZC0OaoeiK3Ri4rlJqWSK_hMcq262xqFNzIAY_ZY84qP1487RdEKZYIw",
						"MuUlXuxS2f4ddjAFWp7nZg"));

			Notification notification = new Notification(sub, payload);
			PushService pushService = new PushService("BIo4B1bsWsS3fDQZJjFo3k_M9C5sMm929H5EJMbqcYicjCiseaYeCDsE6dIB5NNw4u6rlW8YUWhs-evYAwa2mOM","dw1-Fz9_bD1aX9OAZ8uRt8c5p-CNNczirkGBiMYTUVM", "");
			HttpResponse response = pushService.send(notification);

		return response;
	}
}
