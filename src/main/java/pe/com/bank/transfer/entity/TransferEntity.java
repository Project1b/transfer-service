package pe.com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferEntity {

   private String transferFrom;
   private String transferTo;
   private Double amount;
   private String date;
   private String type;

}