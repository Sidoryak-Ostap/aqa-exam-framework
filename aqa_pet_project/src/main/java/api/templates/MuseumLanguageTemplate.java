package api.templates;

import java.util.Objects;

public class MuseumLanguageTemplate {
    String title;
    String workingHours;
    String address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MuseumLanguageTemplate that = (MuseumLanguageTemplate) o;
        return Objects.equals(title, that.title) && Objects.equals(workingHours, that.workingHours) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, workingHours, address);
    }

    @Override
    public String toString() {
        return "MuseumLanguageTemplate{" +
                "title='" + title + '\'' +
                ", workingHours='" + workingHours + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

