package com.learning.fun.jokes.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Joke implements Serializable {
    private List<String> categories;
    private String created_at;
    private String icon_url;
    private String id;
    private String updated_at;
    private String url;
    private String value;
}
