package csf.day37.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class City {
    private String code;
    private String name;

    public City() {}
    public City(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static City populate(ResultSet rs) throws SQLException {
        City c = new City(rs.getString("code"),
                        rs.getString("city_name"));

        return c;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("code", code)
                .add("name", name)
                .build();
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
