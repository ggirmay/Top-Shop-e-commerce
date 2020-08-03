package com.top.shop.user.api.server;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health check API
 */
@RestController
public class HealthCheckApi {

	@GetMapping(value = "/ping", produces = MediaType.TEXT_PLAIN_VALUE)
	public String healthCheck() {
		return "UP";
	}
	@GetMapping(value = "/test",produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Sample User API", description = "<p>This sample User API will return String "
			+ "if the input parameter is invalid.</p>")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Show parameter values"),
			@ApiResponse(responseCode = "400", description = "If <code>param_one</code> or <code>param_two</code> is invalid.", content = {
					@Content(examples = { @ExampleObject(value = "{\r\n" + "    \"message\": \"string\",\r\n"
							+ "    \"reccomendation\": \"string\"\r\n" + "}") }, mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "If <code>use_file</code> is <code>true</code>.", content = {
					@Content(examples = { @ExampleObject(value = "{\r\n" + "    \"message\": \"string\",\r\n"
							+ "    \"reccomendation\": \"string\"\r\n" + "}") }, mediaType = "application/json") }) })

	public ResponseEntity<String> test(){
//        String message = restTemplate.getForObject(url,String.class);
		return ResponseEntity.ok().body("Hello world from User micro Service --- " );
	}
}
