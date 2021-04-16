package de.telran.contacts_db.dto;

public class SearchFormDto {
    public String searchPattern;

    public SearchFormDto(String searchPattern) {
        this.searchPattern = searchPattern;
    }

    public String getSearchPattern() {
        return searchPattern;
    }
}
