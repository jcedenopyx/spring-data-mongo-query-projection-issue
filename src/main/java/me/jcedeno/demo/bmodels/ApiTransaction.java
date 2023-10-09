package me.jcedeno.demo.bmodels;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("transaction")
@Getter
@Setter
@NoArgsConstructor
public class ApiTransaction{
    private ApiTransactionId id;    
    private String checkDate;
    private String correlationId;
    private BigDecimal amount;

    
}
