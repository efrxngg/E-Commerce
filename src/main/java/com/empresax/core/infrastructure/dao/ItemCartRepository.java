// package com.empresax.core.infrastructure.dao;

// import org.springframework.stereotype.Repository;

// import com.empresax.core.domain.model.Item;
// import com.empresax.core.domain.repository.IItemRepository;
// import com.empresax.core.infrastructure.config.Mapper;
// import com.empresax.core.infrastructure.repository.IItemEntityCrudRepository;

// import lombok.AllArgsConstructor;

// @Repository
// @AllArgsConstructor
// public class ItemCartRepository implements IItemRepository {

//     private IItemEntityCrudRepository itemEntityCrudRepository;
//     private Mapper mapper;

//     @Override
//     public Item updateQuantityById(Item entity) {

//         return mapper.to(itemEntityCrudRepository.updateQuantityById(entity.getId_item(),
//                 entity.getQuantity()).get());
//     }

// }
