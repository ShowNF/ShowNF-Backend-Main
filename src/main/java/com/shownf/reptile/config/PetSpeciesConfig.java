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

    private List<FirstSpecies> firstSpecies;

    @Getter
    @Setter
    public static class FirstSpecies {
        private String name;
        private List<SecondSpecies> secondSpecies;
    }

    @Getter
    @Setter
    public static class SecondSpecies {
        private String name;
        private List<Morph> morph;
    }

    @Getter
    @Setter
    public static class Morph {
        private String item;
    }

    public void printConfig() {
        System.out.println("Pet Species Configuration:");
        for (FirstSpecies firstSpecies : this.firstSpecies) {
            System.out.println("First Species: " + firstSpecies.getName());
            for (SecondSpecies secondSpecies : firstSpecies.getSecondSpecies()) {
                System.out.println("\tSecond Species: " + secondSpecies.getName());
                for (Morph morph : secondSpecies.getMorph()) {
                    System.out.println("\t\tMorph: " + morph.getItem());
                }
            }
        }
    }
}
