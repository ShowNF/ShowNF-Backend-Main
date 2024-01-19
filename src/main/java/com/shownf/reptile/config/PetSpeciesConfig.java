package com.shownf.reptile.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "species")
@Getter
@Setter
public class PetSpeciesConfig {

    private List<Species> species;

    @Getter
    @Setter
    public static class Species {
        private String name;
        private List<Subcategory> subcategories;
    }

    @Getter
    @Setter
    public static class Subcategory {
        private String name;
        private List<Item> items;
    }

    @Getter
    @Setter
    public static class Item {
        private String item;
    }
}
