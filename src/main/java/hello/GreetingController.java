package hello;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/invokeAsyncGreeting")
	public String asyncGreetingInvoker(@RequestParam(value = "name", defaultValue = "World") String name)
			throws InterruptedException, ExecutionException {

		Future<String> future = asyncGreeting();
		System.out.println();
		System.out.println(future.get());
		return future.get();

	}

	@Async
	public Future<String> asyncGreeting() throws InterruptedException {
		Thread.sleep(10000);
		return new AsyncResult<>("Hello World from async");

	}
}
