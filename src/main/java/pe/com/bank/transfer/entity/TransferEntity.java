package pe.com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferEntity {

   private String transferFrom;
   private String transferTo;
   private Double amount;
   private Date date;
   private String type;

}