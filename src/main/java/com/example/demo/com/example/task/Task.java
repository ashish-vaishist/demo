package com.example.demo.com.example.task;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Task<R,T extends TaskType,S> {
	



@SuppressWarnings("unchecked")
public S executeParallelTask(R request, T taskType) throws InterruptedException, ExecutionException {
	
	CompletableFuture<S> future1  =  CompletableFuture.supplyAsync(() -> function(request,taskType));

	CompletableFuture<S> future2  = CompletableFuture.supplyAsync(() -> function(request,taskType));
	
	CompletableFuture<S> future3  =  CompletableFuture.supplyAsync(() -> function(request,taskType));

	
	@SuppressWarnings("rawtypes")
	List<CompletableFuture> list = List.of(future1,future2,future3);
	
	CompletableFuture<Void> combinedFuture = CompletableFuture.allOf( list.toArray(new CompletableFuture[list.size()]));
	
	combinedFuture.get(); 
	
	return (S) ("T1: "+future1.isDone()+ future1.get()+"\n T2: "+future2.isDone()+future2.get()+"\n T3: "+future3.isDone()+future3.get());
	
	
}

	@SuppressWarnings("unchecked")
	public S independentTask(R request, TaskType taskType) throws InterruptedException, ExecutionException {

		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> request)
		  .thenCombine(CompletableFuture.supplyAsync(
		    () -> " World"), (s1, s2) -> s1 + s2);
		
		return (S) completableFuture.get();
		
	}


	public S function(R r,T t) {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		S result = (S) (r+" "+t.name() +" "+ Thread.currentThread().getName());
		
		return result;
	}
}