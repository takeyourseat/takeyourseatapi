package com.stefanini.internship.notificationserver.controllers;

import com.stefanini.internship.notificationserver.model.dto.OurSubscription;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PushController {

	@GetMapping("/test")
	public void pushTest() throws Exception {

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
				"        \"actions\":[\n" +
				"            {\n" +
				"                \"action\": \"approve\",\n" +
				"                \"title\": \"approve\"\n" +
				"            },\n" +
				"            {\n" +
				"                \"action\":\"decline\",\n" +
				"                \"title\":\"decline\"\n" +
				"            }\n" +
				"        ]\n" +
				"    }\n" +
				"}\n";

		OurSubscription sub = new OurSubscription("https://fcm.googleapis.com/fcm/send/ck7v6F_eF9o:APA91bFz-cIN6UygwfZVfxjIf7JuLz9sekX14pKpeEK--NIPaGv0NU_mgGvsH8Iirldc11wE8LrAwTOXl2Vf0WPRmd0l5lO5ZWmBMwcYJKKpNRDx_GrJebZyH3h-OJSyHd3iWtZ-PnH6",
				new OurSubscription.Keys("BJQzgYBPnwG-oKOF7745VJ3naqLHGXLH2DYenJbiKBrZ10Gq6Fy9pztWZt8NMOhIduGlAlqtEINzGbYCkJPLd7Y",
                        "ZUwa0VYo8c_JKn2s7WgY0Q"));

			Notification notification = new Notification(sub, payload);
			PushService pushService = new PushService("BIo4B1bsWsS3fDQZJjFo3k_M9C5sMm929H5EJMbqcYicjCiseaYeCDsE6dIB5NNw4u6rlW8YUWhs-evYAwa2mOM","dw1-Fz9_bD1aX9OAZ8uRt8c5p-CNNczirkGBiMYTUVM", "");
			HttpResponse response = pushService.send(notification);
        System.out.println("g");




	}
}
