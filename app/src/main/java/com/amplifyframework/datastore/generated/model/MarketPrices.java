package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the MarketPrices type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "MarketPrices", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class MarketPrices implements Model {
  public static final QueryField ID = field("MarketPrices", "id");
  public static final QueryField DATE = field("MarketPrices", "Date");
  public static final QueryField LOCATION = field("MarketPrices", "Location");
  public static final QueryField COUNT = field("MarketPrices", "Count");
  public static final QueryField PRICE = field("MarketPrices", "Price");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSDate") Temporal.Date Date;
  private final @ModelField(targetType="String") String Location;
  private final @ModelField(targetType="Int") Integer Count;
  private final @ModelField(targetType="Float") Double Price;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public Temporal.Date getDate() {
      return Date;
  }
  
  public String getLocation() {
      return Location;
  }
  
  public Integer getCount() {
      return Count;
  }
  
  public Double getPrice() {
      return Price;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private MarketPrices(String id, Temporal.Date Date, String Location, Integer Count, Double Price) {
    this.id = id;
    this.Date = Date;
    this.Location = Location;
    this.Count = Count;
    this.Price = Price;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      MarketPrices marketPrices = (MarketPrices) obj;
      return ObjectsCompat.equals(getId(), marketPrices.getId()) &&
              ObjectsCompat.equals(getDate(), marketPrices.getDate()) &&
              ObjectsCompat.equals(getLocation(), marketPrices.getLocation()) &&
              ObjectsCompat.equals(getCount(), marketPrices.getCount()) &&
              ObjectsCompat.equals(getPrice(), marketPrices.getPrice()) &&
              ObjectsCompat.equals(getCreatedAt(), marketPrices.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), marketPrices.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getDate())
      .append(getLocation())
      .append(getCount())
      .append(getPrice())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("MarketPrices {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Date=" + String.valueOf(getDate()) + ", ")
      .append("Location=" + String.valueOf(getLocation()) + ", ")
      .append("Count=" + String.valueOf(getCount()) + ", ")
      .append("Price=" + String.valueOf(getPrice()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static MarketPrices justId(String id) {
    return new MarketPrices(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      Date,
      Location,
      Count,
      Price);
  }
  public interface BuildStep {
    MarketPrices build();
    BuildStep id(String id);
    BuildStep date(Temporal.Date date);
    BuildStep location(String location);
    BuildStep count(Integer count);
    BuildStep price(Double price);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private Temporal.Date Date;
    private String Location;
    private Integer Count;
    private Double Price;
    @Override
     public MarketPrices build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new MarketPrices(
          id,
          Date,
          Location,
          Count,
          Price);
    }
    
    @Override
     public BuildStep date(Temporal.Date date) {
        this.Date = date;
        return this;
    }
    
    @Override
     public BuildStep location(String location) {
        this.Location = location;
        return this;
    }
    
    @Override
     public BuildStep count(Integer count) {
        this.Count = count;
        return this;
    }
    
    @Override
     public BuildStep price(Double price) {
        this.Price = price;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, Temporal.Date date, String location, Integer count, Double price) {
      super.id(id);
      super.date(date)
        .location(location)
        .count(count)
        .price(price);
    }
    
    @Override
     public CopyOfBuilder date(Temporal.Date date) {
      return (CopyOfBuilder) super.date(date);
    }
    
    @Override
     public CopyOfBuilder location(String location) {
      return (CopyOfBuilder) super.location(location);
    }
    
    @Override
     public CopyOfBuilder count(Integer count) {
      return (CopyOfBuilder) super.count(count);
    }
    
    @Override
     public CopyOfBuilder price(Double price) {
      return (CopyOfBuilder) super.price(price);
    }
  }
  
}
