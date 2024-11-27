package api.templates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PartnerTemplate {
    String name;
    private List<String> logo;
    String link;
    private String _id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLogo() {
        return logo;
    }

    public void setLogo(List<String> logo) {
        this.logo = logo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
        PartnerTemplate that = (PartnerTemplate) o;
        return Objects.equals(name, that.name) && Objects.equals(logo, that.logo) && Objects.equals(link, that.link) && Objects.equals(_id, that._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, logo, link, _id);
    }

    @Override
    public String toString() {
        return "PartnerTemplate{" +
                "name='" + name + '\'' +
                ", logo=" + logo +
                ", link='" + link + '\'' +
                ", _id='" + _id + '\'' +
                '}';
    }
}
