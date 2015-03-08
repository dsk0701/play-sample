package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Authentication entity managed by Ebean
 */
@Entity
public class Authentication extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    // @NotNull
    public String userId;
    public String accessToken;
    public String refreshToken;
    public Date expiredIn;

    /**
     * Generic query helper for entity Authentication with id Long
     */
    public static Model.Finder<Long,Authentication> find = new Model.Finder<Long,Authentication>(Long.class, Authentication.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Authentication c: Authentication.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.userId);
        }
        return options;
    }

}

