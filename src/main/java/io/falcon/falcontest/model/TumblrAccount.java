package io.falcon.falcontest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = TumblrAccount.TABLE_NAME)
public class TumblrAccount {

  static final String TABLE_NAME = "fa_tumblr_account";

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "acc_name")
  private String name;

  @Column(name = "acc_type")
  private String accType;

  @Column(name = "popularity")
  private Integer popularity;

  public String getId() {
    return id;
  }

  public TumblrAccount setId(final String id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public TumblrAccount setName(final String name) {
    this.name = name;
    return this;
  }

  public String getAccType() {
    return accType;
  }

  public TumblrAccount setAccType(final String accType) {
    this.accType = accType;
    return this;
  }

  public Integer getPopularity() {
    return popularity;
  }

  public TumblrAccount setPopularity(final Integer popularity) {
    this.popularity = popularity;
    return this;
  }
}
