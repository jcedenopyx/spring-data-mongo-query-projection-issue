package me.jcedeno.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import me.jcedeno.demo.bmodels.ApiTransaction;
import me.jcedeno.demo.bmodels.ApiTransactionId;
import me.jcedeno.demo.models.Transaction;
import me.jcedeno.demo.models.TransactionFour;
import me.jcedeno.demo.models.TransactionThree;
import me.jcedeno.demo.models.TransactionTwo;

public interface TransactionRepo extends MongoRepository<ApiTransaction, ApiTransactionId>{

    @Query("{checkDate: ?0}")
    List<Transaction> getTransactions(String checkDate);
    
    
    @Query("{checkDate: ?0}")
    List<TransactionTwo> getTransactionsTwo(String checkDate);


    @Query("{checkDate: ?0}")
    List<TransactionThree> getTransactionsThree(String checkDate);

    @Query("{checkDate: ?0}")
    List<TransactionFour> getTransactionsFour(String checkDate);
}
