package me.jcedeno.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import me.jcedeno.demo.bmodels.ApiTransaction;
import me.jcedeno.demo.bmodels.ApiTransactionId;
import me.jcedeno.demo.dao.TransactionRepo;
import me.jcedeno.demo.models.Transaction;
import me.jcedeno.demo.models.TransactionFour;
import me.jcedeno.demo.models.TransactionThree;
import me.jcedeno.demo.models.TransactionTwo;

@DataMongoTest(excludeAutoConfiguration = JmxAutoConfiguration.class)
@Testcontainers
@EnableMongoRepositories("me.jcedeno.demo.dao")
class DemoApplicationTests {
	@Container
	static MongoDBContainer container = new MongoDBContainer("mongo:4.4");

	@Autowired
	TransactionRepo repo;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry){
		registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl);
	}

	@BeforeEach
	public void dataSetup(){
		repo.deleteAll();
		var list = new ArrayList<ApiTransaction>();

		for (int i = 0; i < 20; i++) {
			var obj = new ApiTransaction();
			obj.setCorrelationId("ID");
			obj.setId(new ApiTransactionId(String.format("2023-10-%02d",(1+i)), UUID.randomUUID().toString()));
			obj.setCorrelationId(obj.getId().getTrackingId());
			obj.setCheckDate(obj.getId().getCheckDate());

			list.add(obj);
		}

		repo.insert(list);
	}
	
	@Test
	void test_failToConvertNonAnnotatedId() {
		List<Transaction> rest = repo.getTransactions("2023-10-01");
		
		assertEquals(1, rest.size());

	}


	@Test
	void test_failToConvertAnnotatedId() {
		List<TransactionTwo> rest = repo.getTransactionsTwo("2023-10-01");
		
		assertEquals(1, rest.size());

	}


	@Test
	void test_failToConvertAnnotatedMongoId() {
		List<TransactionThree> rest = repo.getTransactionsThree("2023-10-01");
		
		assertEquals(1, rest.size());

	}

	
	@Test
	void test_succeedsConvertionWithAnnotationField_id() {
		List<TransactionFour> rest = repo.getTransactionsFour("2023-10-01");
		
		assertEquals(1, rest.size());

	}
}
