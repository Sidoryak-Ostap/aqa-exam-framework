package api.templates;

import java.util.Objects;

public class LanguageTemplate {
    private String title;
    private String description;
    private String shortDescription;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguageTemplate that = (LanguageTemplate) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(shortDescription, that.shortDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, shortDescription);
    }

    @Override
    public String toString() {
        return "LanguageTemplate{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                '}';
    }
}
