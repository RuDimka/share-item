package com.project.share_item.Repository;

import com.project.share_item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    //Item saveItem(Item newItem);

    //List<Item> getAllItemsByOwner(Long ownerId);

    //Collection<Object> getAllItemsBySearch();

   // boolean existsItem(Long itemId);

    //Item findById(Long itemId);
}
