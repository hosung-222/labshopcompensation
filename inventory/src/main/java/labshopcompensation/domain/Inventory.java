package labshopcompensation.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import labshopcompensation.InventoryApplication;
import lombok.Data;

@Entity
@Table(name = "Inventory_table")
@Data
//<<< DDD / Aggregate Root
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long stock;

    @PostPersist
    public void onPostPersist() {}

    public static InventoryRepository repository() {
        InventoryRepository inventoryRepository = InventoryApplication.applicationContext.getBean(
            InventoryRepository.class
        );
        return inventoryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void decreaseStock(OrderPlaced orderPlaced) {
        //implement business logic here:

        /** Example 1:  new item 
        Inventory inventory = new Inventory();
        repository().save(inventory);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(inventory->{
            
            inventory // do something
            repository().save(inventory);


         });
        */

    }

    public static void increaseStock(OrderCancelled orderCancelled) {
        repository().findById(Long.valueOf(orderCancelled.getProductId())).ifPresent(inventory ->{
			inventory.setStock(inventory.getStock() + orderCancelled.getQty());
		    repository().save(inventory);
        });

    }
}
//>>> DDD / Aggregate Root
