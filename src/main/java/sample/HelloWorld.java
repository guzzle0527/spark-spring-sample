package sample;

import java.util.Arrays;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import sample.service.TestService;
import sample.service.TestServiceImpl;

@SpringBootApplication
public class HelloWorld implements CommandLineRunner {

	@Autowired
	private TestService testService;

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(HelloWorld.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JavaSparkContext sc = new JavaSparkContext("local[*]", "Hello");
		JavaRDD<String> rdd = sc.parallelize(Arrays.asList("a", "b", "c", "d", "e"));

		rdd.foreach(data -> {
			testService.test(data); // Task not serializableエラー
			TestService t1 = context.getBean(TestService.class); // これもTask not serializableエラー
			TestService t2 = new TestServiceImpl(); // これは動作する
		});
	}
}