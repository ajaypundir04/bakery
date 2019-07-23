package com.hexad.bakery.service;

import com.hexad.bakery.model.*;

import java.util.*;

public class OrderProcessor {

    public static OrderProcessor instance=new OrderProcessor();
    private OrderProcessor(){

    }
    public static OrderProcessor getInstance()
    {
        return instance;
    }

    public List<Bill> processOrder(List<ItemOrder> items, Inventory inventory)
    {
     List<Bill> bills=new ArrayList<>();
     items.forEach(
             itemOrder -> {
                 Bill bill=calculateBill(itemOrder.getQuantity(),inventory.getProducts().get(itemOrder.getProductCode()));

                 if(Objects.nonNull(bill))
                 {
                     bill.setItemOrder(itemOrder);
                     bills.add(bill);
                 }
             }
     );
     return bills;
    }

   private  Bill calculateBill(int quantity, Product product) {
       Bill bill=new Bill();
       Map<Packs, Integer> productCount = new LinkedHashMap<>();
       List<Packs> packs = product.getPacks();
       Collections.sort(packs, Collections.reverseOrder());
       int i = 0;
       double billAmount=0.0d;
       while (quantity != 0) {
           Packs pack = packs.get(i);
           int qt = pack.getQuantity();
           if (quantity >= qt) {

               if (productCount.containsKey(pack)) {
                   productCount.put(pack, productCount.get(pack) + 1);
               } else {
                   productCount.put(pack, 1);

               }
               billAmount+=pack.getPrice();
           } else {
               i++;
           }
           quantity-=qt;

       }
       bill.setItems(productCount);
       bill.setBillAmount(billAmount);
       return bill;
   }
}
