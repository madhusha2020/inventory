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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public ItemList searchItems(PageDetails pageDetails) {
        customValidator.validateFoundNull(pageDetails.getSearchFilter(), ErrorCodes.SEARCH_FILTER);
        customValidator.validateNullOrEmpty(pageDetails.getSearchFilter().getStatusList(), ErrorCodes.STATUS_LIST);
        Page<Item> items = searchItemQuery(pageDetails);
        items.stream().map(this::setImage).collect(Collectors.toList());
        return new ItemList(items.getContent(), items.getTotalPages());
    }

    @Override
    public Item saveItem(Item item) {
        if (itemRepository.findByCode(item.getCode()).isPresent()) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "This item code is already exist", Collections.singletonList("This item code is already exist"));
        }
        item.setStatus(Status.ACTIVE.getValue());
        item.fillCompulsory(item.getUserId());
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
}
