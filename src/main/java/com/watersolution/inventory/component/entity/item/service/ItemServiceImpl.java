package com.watersolution.inventory.component.entity.item.service;

import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.entity.item.model.api.ItemList;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import com.watersolution.inventory.component.entity.item.repository.ItemRepository;
import com.watersolution.inventory.component.exception.CustomException;
import com.watersolution.inventory.component.management.image.util.ImageUtil;
import com.watersolution.inventory.component.management.inventory.model.api.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ImageUtil imageUtil;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public ItemList getAllItems(PageDetails pageDetails) {
        int page = pageDetails.getOffset() / pageDetails.getLimit();

        Page<Item> items = itemRepository.findAllByStatusIn(Status.getAllStatusAsList(), PageRequest.of(page, pageDetails.getLimit()));
        items.stream().map(this::setImage).collect(Collectors.toList());
        return new ItemList(items.getContent(), items.getTotalPages());
    }

    @Override
    public ItemList getAllActiveItems(PageDetails pageDetails) {
        int page = pageDetails.getOffset() / pageDetails.getLimit();

        Page<Item> items = itemRepository.findAllByStatus(Status.ACTIVE.getValue(), PageRequest.of(page, pageDetails.getLimit()));
        items.stream().map(this::setImage).collect(Collectors.toList());
        return new ItemList(items.getContent(), items.getTotalPages());
    }

    @Override
    public Item getItemById(String itemId) {
        customValidator.validateFoundNull(itemId, "itemId");
        Item item = itemRepository.findByIdAndStatusIn(Long.valueOf(itemId), Status.getAllStatusAsList());
        customValidator.validateFoundNull(item, "item");
        return item;
    }

    @Override
    public ItemList searchItems(PageDetails pageDetails) {
        customValidator.validateFoundNull(pageDetails.getSearchFilter(), ErrorCodes.SEARCH_FILTER);
        customValidator.validateNullOrEmpty(pageDetails.getSearchFilter().getStatusList(), ErrorCodes.STATUS_LIST);
        Page<Item> items = searchItemQuery(pageDetails);
        items.stream().map(this::setImage).collect(Collectors.toList());
        return new ItemList(items.getContent(), items.getTotalPages());
    }

    @Transactional
    @Override
    public InventoryItem saveInventoryItem(InventoryItem inventoryItem) {
        if (itemRepository.findByCode(inventoryItem.getItem().getCode()).isPresent()) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "This item code is already exist", Collections.singletonList("This item code is already exist"));
        }
        validateInventoryItem(inventoryItem);
        inventoryItem.getItem().setStatus(Status.ACTIVE.getValue());
        inventoryItem.getItem().fillCompulsory(inventoryItem.getItem().getUserId());

        inventoryItem.getInventory().setStatus(Status.ACTIVE.getValue());
        inventoryItem.getInventory().fillCompulsory(inventoryItem.getInventory().getUserId());

        inventoryItem.getInventory().setItem(inventoryItem.getItem());
        inventoryItem.getItem().setInventories(Arrays.asList(inventoryItem.getInventory()));

        itemRepository.save(inventoryItem.getItem());
        return inventoryItem;
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    private Item setImage(Item item) {
        if (item.getPhoto() != null) {
            item.setPhoto(imageUtil.decompressBytes(item.getPhoto()));
        }
        return item;
    }

    private Page<Item> searchItemQuery(PageDetails pageDetails) {
        int page = pageDetails.getOffset() / pageDetails.getLimit();

        if (pageDetails.getSearchFilter().getName() != null && pageDetails.getSearchFilter().getType() != null) {
            return itemRepository.findAllByNameLikeAndCodeLikeAndStatusIn(
                    pageDetails.getSearchFilter().getName(),
                    pageDetails.getSearchFilter().getType(),
                    pageDetails.getSearchFilter().getStatusList(),
                    PageRequest.of(page, pageDetails.getLimit()));
        } else if (pageDetails.getSearchFilter().getName() != null) {
            return itemRepository.findAllByNameLikeAndStatusIn(
                    pageDetails.getSearchFilter().getName(),
                    pageDetails.getSearchFilter().getStatusList(),
                    PageRequest.of(page, pageDetails.getLimit()));
        } else if (pageDetails.getSearchFilter().getType() != null) {
            return itemRepository.findAllByCodeLikeAndStatusIn(
                    pageDetails.getSearchFilter().getType(),
                    pageDetails.getSearchFilter().getStatusList(),
                    PageRequest.of(page, pageDetails.getLimit()));
        } else {
            return itemRepository.findAllByStatusIn(
                    pageDetails.getSearchFilter().getStatusList(),
                    PageRequest.of(page, pageDetails.getLimit()));
        }
    }

    private void validateInventoryItem(InventoryItem inventoryItem) {
        if (inventoryItem.getItem().getSprice() == 0) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Item sell price cannot be zero", Collections.singletonList("Item sell price cannot be zero"));
        }
        if (inventoryItem.getItem().getLastprice() == 0) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Item last price cannot be zero", Collections.singletonList("Item last price cannot be zero"));
        }
        if (inventoryItem.getItem().getLastprice() > inventoryItem.getItem().getSprice()) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Item last price cannot be greater than item sell price", Collections.singletonList("Item last price cannot be greater than item sell price"));
        }
        if (inventoryItem.getItem().getRop() > inventoryItem.getInventory().getInitqty()) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Item reorder point cannot be greater than initial quantity", Collections.singletonList("Item reorder point cannot be greater than initial quantity"));
        }
        if (inventoryItem.getInventory().getInitqty() == 0) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Item initial quantity cannot be zero", Collections.singletonList("Item initial quantity cannot be zero"));
        }
        if (inventoryItem.getInventory().getQty() == 0) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Item quantity cannot be zero", Collections.singletonList("Item quantity cannot be zero"));
        }
        if (inventoryItem.getInventory().getQty() > inventoryItem.getInventory().getInitqty()) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Item available quantity cannot be greater than to item initial quantity", Collections.singletonList("Item available quantity cannot be greater than to item initial quantity"));
        }
    }
}
