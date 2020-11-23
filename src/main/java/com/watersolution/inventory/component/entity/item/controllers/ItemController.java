package com.watersolution.inventory.component.entity.item.controllers;

import com.sun.istack.NotNull;
import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.common.model.api.SearchFilter;
import com.watersolution.inventory.component.entity.item.model.api.ItemList;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import com.watersolution.inventory.component.entity.item.service.ItemService;
import com.watersolution.inventory.component.management.inventory.model.api.InventoryItem;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available items", response = ItemList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<ItemList> getAllItems(@NotNull @Min(0) @ApiParam(value = "The number of items to skip before starting to collect the result set.", required = true, defaultValue = "0") @Valid @RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset, @NotNull @Min(1) @Max(100) @ApiParam(value = "The numbers of items to return.", required = true, defaultValue = "10") @Valid @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit) {
        return ResponseCreator.successfulResponse(itemService.getAllItems(new PageDetails(new SearchFilter(), offset, limit)));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available active items", response = ItemList.class)
    @CrossOrigin
    @GetMapping(value = "/active", produces = "application/json")
    public ResponseEntity<ItemList> getAllActiveItems(@NotNull @Min(0) @ApiParam(value = "The number of items to skip before starting to collect the result set.", required = true, defaultValue = "0") @Valid @RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset, @NotNull @Min(1) @Max(100) @ApiParam(value = "The numbers of items to return.", required = true, defaultValue = "10") @Valid @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit) {
        return ResponseCreator.successfulResponse(itemService.getAllActiveItems(new PageDetails(new SearchFilter(), offset, limit)));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get item by id", response = Item.class)
    @CrossOrigin
    @GetMapping(value = "/{itemId}", produces = "application/json")
    public ResponseEntity<Item> getItemById(@PathVariable("itemId") String itemId) {
        return ResponseCreator.successfulResponse(itemService.getItemById(itemId));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Search items", response = ItemList.class)
    @CrossOrigin
    @PostMapping(value = "search", produces = "application/json")
    public ResponseEntity<ItemList> searchItems(@RequestBody SearchFilter searchFilter, @NotNull @Min(0) @ApiParam(value = "The number of items to skip before starting to collect the result set.", required = true, defaultValue = "0") @Valid @RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset, @NotNull @Min(1) @Max(100) @ApiParam(value = "The numbers of items to return.", required = true, defaultValue = "10") @Valid @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit) {
        return ResponseCreator.successfulResponse(itemService.searchItems(new PageDetails(searchFilter, offset, limit)));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Save inventory item", response = InventoryItem.class)
    @CrossOrigin
    @PostMapping(produces = "application/json")
    public ResponseEntity<InventoryItem> saveInventoryItem(@Valid @RequestBody InventoryItem inventoryItem) {
        return ResponseCreator.successfulResponse(itemService.saveInventoryItem(inventoryItem));
    }
}
