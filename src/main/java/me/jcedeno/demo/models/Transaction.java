package me.jcedeno.demo.models;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("transaction")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private TransactionId id;    
    private String checkDate;
    private String correlationId;
    private BigDecimal amount;
}
