package com.cat.test.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class JSONUtil {

    private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);

    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
    }

    public static String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T toObject(String str, Class<T> clazz) throws IOException {
        return objectMapper.readValue(str, clazz);
    }

    @Test
    public void two() throws Exception {
        User user = new User("zsy", 33);
        String str = toJson(user);
        Map<String, Object> map = toObject(str, Map.class);
        map.forEach((k, v) -> System.out.println(k + "-" + v));
    }

    @Test
    public void one() throws Exception {
        User user = new User("zsy", 33);
        String str = toJson(user);
        System.out.println(str);

        Student student = new Student(1L, "cjj", "XiaMen");
        System.out.println(toJson(student));

        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        System.out.println(objectMapper.writeValueAsString(student));
    }

    @Test
    public void three() throws Exception {
        Student student = new Student(1L, "crg", "ShanTou");
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        String s1 = objectMapper.writerWithView(Student.BaseView.class).writeValueAsString(student);
        System.out.println(s1);
        String s2 = objectMapper.writerWithView(Student.APPView.class).writeValueAsString(student);
        System.out.println(s2);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties({"name"})
    public static class User {
        //        @JsonIgnore
        private String name;
        private int age;
    }

    public static class Student {
        public interface BaseView {
        }

        public interface APPView extends BaseView {
        }

        //@JsonIgnore
        private long id;
        @JsonView(BaseView.class)
        private String name;
        @JsonView(APPView.class)
        private String address;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Student(long id, String name, String address) {
            this.id = id;
            this.name = name;
            this.address = address;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

}
