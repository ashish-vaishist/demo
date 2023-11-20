package com.example.demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.com.example.mapper.Mapper;
import com.example.demo.com.example.task.Task;
import com.example.demo.com.example.task.TaskType;
import com.example.demo.com.example.task.Ticker;
import com.example.demo.service.FailSafeService;
import com.example.library.LibraryService;
import com.example.library.SubjectService;
import com.example.library.model.English;
import com.example.library.model.Hindi;
import com.example.library.model.Subject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import reactor.core.publisher.Mono;


@RestController
public class DemoController {
	
	@Autowired
	LibraryService librarySercive;
	@Autowired
	SubjectService<Hindi> subjectService;
	
	
	@Autowired
	Ticker ticker;
	
	
	@Autowired
	SubjectService<English> subjectService1;
	
	@Autowired
    private Validator validator;
	
	@Autowired
	FailSafeService failSafeService;
	
	@Autowired
	@Qualifier("createRequestMapper")
	Mapper<String,Object> request;
    
	@Autowired
	@Qualifier("responseMapper")  // @Qualifier("createResponseMapper")
	Mapper<String,Object> response;
	
	@GetMapping("library/{name}")
	public String getLibararyName(@PathVariable  String name ) {
		return  librarySercive.getLibraryName(name);
		
		
	}
	
	@GetMapping("parallelTask")
	public String getparallelTask() throws InterruptedException, ExecutionException {
		
		Task<String,TaskType,String> t= new Task<>();
		
		return t.executeParallelTask("Hello World", TaskType.DELIVERED);
		
		
		
	}
	
	@GetMapping("independentTask")
	public String getindependentTask() throws InterruptedException, ExecutionException {
		
		Task<String,TaskType,String> t= new Task<>();
		
		return  t.independentTask("Hello World", TaskType.DELIVERED);
		
		
		
	}
	
	@GetMapping("getSubject")
	public Object getSubject() throws JsonProcessingException  {
		

		 
		ObjectMapper o= new ObjectMapper();
		return o.writeValueAsString(subjectService.getSubject(new Hindi()));
		
		
		
		
	}
	
	@GetMapping("getSubject1")
	public Object getSubject1()  {
		

		 
		
		return subjectService1.getSubject(new English());
		
		
		
	}
	
	
	@PostMapping("getSubject2")
	public Object getSubject2( @RequestBody Subject s )  {
		
		Set<ConstraintViolation<Subject>> violations = validator.validate(s);

		if(violations.size()>0) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Subject> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            return "Error occurred: " + sb.toString() + violations;
        
		}
		
		
		return subjectService1.getSubject(new English());
		
		
		
	}
	
	
	@PostMapping("getSubject3")
	public Object getSubject3( @RequestBody @Valid Subject s )  {
		
		
		
		return subjectService1.getSubject(new English());
		
		
		
	}
	
	@GetMapping("mapper")
	public String  getMapper ()  {
		
		
		String j= (String) request.convert("12");
		
		int k= (int) response.convert("34");
		
		
		return  ("requestMapper "+j +" responseMapper "+ k);
		
		
		
	}
	
	
	@GetMapping("getStatus")
	public String getStatus() throws JsonProcessingException, InterruptedException  {
		
		int x=100;
		String s="";
		
		 int failcount=0;
		 
		 
		 int counter=0;
		
		while(x-->0) {
			
			Thread.sleep(2000);
			s= failSafeService.getStatus();
			counter++;
			
			
			if(s.equalsIgnoreCase("DOWN")) {
				failcount++;
				
			}
			
			
			
			if(counter>=5 && failcount >=3 ) {
				
				counter=0;
				failcount=0;
				
				System.out.println("INCONSISTENT-DOWN");
				
				s= "DOWN";

			}
			
			if(counter>=5) {
				counter=0;  // 10 sec 
				
			}
			
			if(failcount>=3) {
				failcount=0;
			}
			
		}
		
		
		return s;
	
		
	}
	
	
	@GetMapping("mono")
	Mono<Emp> testGetparallelTask() throws URISyntaxException {
		
		
		Emp e= new Emp("test","23","123");
		
		Mono<Emp>eem= Mono.just(e);
		
		
		eem.delayElement(Duration.ofMillis(2000)).subscribe(n->{
			
			System.out.println(n.age);
			System.out.println(n.name);
			System.out.println(n.salary);
		});
		
		
		 WebClient.create().post().uri(new URI("https://dummy.restapiexample.com/api/v1/create")).bodyValue(e)
		.retrieve().bodyToMono(String.class)
		.doOnError(n->System.out.println("Error "+n.getMessage().substring(0,10)))
		
				.subscribe(n->System.out.println("Api Called "+n));
		
		return eem;
		
	
	}
}


class Emp{
	
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}



	String name;
	String age;
	String salary;
	
	public Emp(String name, String age, String salary) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAge() {
		return age;
	}



	public void setAge(String age) {
		this.age = age;
	}



	public String getSalary() {
		return salary;
	}



	public void setSalary(String salary) {
		this.salary = salary;
	}



	@Override
	public String toString() {
		return "Emp [name=" + name + ", age=" + age + ", salary=" + salary + "]";
	}
	
	
}
