/*
 * package com.example.demo;
 * 
 * 
 * 
 * import static org.junit.Assert.assertEquals;
 * 
 * import org.junit.jupiter.api.Test; import org.junit.runner.RunWith; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.boot.test.web.client.TestRestTemplate; import
 * org.springframework.boot.test.web.server.LocalServerPort; import
 * org.springframework.http.HttpEntity; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.test.context.junit4.SpringRunner;
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 * class DemoControllerTest {
 * 
 * @LocalServerPort private int port;
 * 
 * TestRestTemplate restTemplate = new TestRestTemplate();
 * 
 * HttpHeaders headers = new HttpHeaders();
 * 
 * @Test void testGetLibararyName() {
 * 
 * HttpEntity<String> entity = new HttpEntity<String>(null, headers);
 * 
 * ResponseEntity<String> response = invokeGetApi("/library/ashish");
 * 
 * assertEquals("Your Library Name ashish",response.getBody());
 * 
 * }
 * 
 * @Test void testGetparallelTask() { ResponseEntity<String> response =
 * invokeGetApi("/parallelTask");
 * 
 * assertEquals(HttpStatus.OK,response.getStatusCode());
 * 
 * }
 * 
 * @Test void testGetindependentTask() { ResponseEntity<String> response =
 * invokeGetApi("/independentTask");
 * 
 * assertEquals(HttpStatus.OK,response.getStatusCode());
 * 
 * }
 * 
 * @Test void testGetSubject() { ResponseEntity<String> response =
 * restTemplate.exchange( createURLWithPort("/getSubject"), HttpMethod.GET,null,
 * String.class);
 * 
 * assertEquals(HttpStatus.OK,response.getStatusCode());
 * 
 * 
 * }
 * 
 * @Test void testGetSubject1() { ResponseEntity<String> response =
 * restTemplate.exchange( createURLWithPort("/getSubject1"),
 * HttpMethod.GET,null, String.class);
 * 
 * assertEquals(HttpStatus.OK,response.getStatusCode());
 * 
 * 
 * } private String createURLWithPort(String uri) { return "http://localhost:" +
 * port + uri; }
 * 
 * 
 * private ResponseEntity<String> invokeGetApi(String url) {
 * 
 * return restTemplate.exchange( createURLWithPort(url), HttpMethod.GET,null,
 * String.class);
 * 
 * 
 * } }
 */