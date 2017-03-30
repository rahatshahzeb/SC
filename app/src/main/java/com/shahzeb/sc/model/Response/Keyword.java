
package com.shahzeb.sc.model.Response;

import java.util.List;

public class Keyword {

    public String keyword_id;
    public String text;
    public String type;
    public int relevance;
    public List<String> entity_uris = null;
    public List<String> entity_types = null;

}
