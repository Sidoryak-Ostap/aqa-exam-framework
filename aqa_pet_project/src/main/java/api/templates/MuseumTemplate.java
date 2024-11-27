package api.templates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)

public class MuseumTemplate {

    private MuseumLanguageTemplate ukrainian;
    private MuseumLanguageTemplate english;

    private String phone;
    private String email;
    private String link;

    private List<String> photo;

    private String _id;

    public MuseumLanguageTemplate getUkrainian() {
        return ukrainian;
    }

    public void setUkrainian(MuseumLanguageTemplate ukrainian) {
        this.ukrainian = ukrainian;
    }

    public MuseumLanguageTemplate getEnglish() {
        return english;
    }

    public void setEnglish(MuseumLanguageTemplate english) {
        this.english = english;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getPhoto() {
        return photo;
    }

    public void setPhoto(List<String> photo) {
        this.photo = photo;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MuseumTemplate that = (MuseumTemplate) o;
        return Objects.equals(ukrainian, that.ukrainian) && Objects.equals(english, that.english) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(link, that.link) && Objects.equals(photo, that.photo) && Objects.equals(_id, that._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ukrainian, english, phone, email, link, photo, _id);
    }

    @Override
    public String toString() {
        return "MuseumTemplate{" +
                "ukrainian=" + ukrainian +
                ", english=" + english +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", link='" + link + '\'' +
                ", photo=" + photo +
                ", _id='" + _id + '\'' +
                '}';
    }
}
