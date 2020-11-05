package com.watersolution.inventory.component.management.role.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.role.model.db.Module;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ModuleList extends ResponseDefault {
    @Valid
    private List<Module> modules;

    public ModuleList(List<Module> modules) {
        this.modules = modules;
    }
}
