package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostTemplate {

    private LanguageTemplate ukrainian;
    private LanguageTemplate english;
    private List<String> photos;
    private String _id;

    public LanguageTemplate getUkrainian() {
        return ukrainian;
    }

    public void setUkrainian(LanguageTemplate ukrainian) {
        this.ukrainian = ukrainian;
    }

    public LanguageTemplate getEnglish() {
        return english;
    }

    public void setEnglish(LanguageTemplate english) {
        this.english = english;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
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
        PostTemplate that = (PostTemplate) o;
        return Objects.equals(ukrainian, that.ukrainian) && Objects.equals(english, that.english) && Objects.equals(photos, that.photos)  && Objects.equals(_id, that._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ukrainian, english, photos, _id);
    }

    @Override
    public String toString() {
        return "PostTemplate{" +
                "ukrainian=" + ukrainian +
                ", english=" + english +
                ", photos=" + photos +
                ", _id='" + _id + '\'' +
                '}';
    }
}
