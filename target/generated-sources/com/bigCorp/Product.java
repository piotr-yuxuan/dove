/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.bigCorp;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
/** Description of a product. Only fields `id` and `retailPrice` are required. */
@org.apache.avro.specific.AvroGenerated
public class Product extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -1686339238153197284L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Product\",\"namespace\":\"com.bigCorp\",\"doc\":\"Description of a product. Only fields `id` and `retailPrice` are required.\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"fixed\",\"name\":\"UID\",\"doc\":\"Unique identifier. Will be cast to a UUID.\",\"size\":16},\"doc\":\"Identifier of this product. Will be cast to a UUID.\"},{\"name\":\"retailPrice\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":8,\"scale\":1},\"doc\":\"What a customer must pay to get one item. Gross profit for each sold product.\"},{\"name\":\"warehouseCount\",\"type\":[\"null\",\"int\"],\"doc\":\"If known, count of available items of this product from our big warehouse.\",\"default\":null},{\"name\":\"familyVariants\",\"type\":{\"type\":\"array\",\"items\":\"UID\"},\"doc\":\"Ids of all the variants of this product family, including this very product id.\",\"default\":[]},{\"name\":\"category\",\"type\":[\"null\",{\"type\":\"enum\",\"name\":\"Category\",\"doc\":\"Category of a product. A product can only have zero of one category.\",\"symbols\":[\"FASHION\",\"VEGETABLES\",\"BEAUTY\",\"SPORTSWEAR\"]}],\"doc\":\"Category of this product, if relevant\",\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Product> ENCODER =
      new BinaryMessageEncoder<Product>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Product> DECODER =
      new BinaryMessageDecoder<Product>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Product> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Product> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Product>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Product to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Product from a ByteBuffer. */
  public static Product fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** Identifier of this product. Will be cast to a UUID. */
  @Deprecated public com.bigCorp.UID id;
  /** What a customer must pay to get one item. Gross profit for each sold product. */
  @Deprecated public java.math.BigDecimal retailPrice;
  /** If known, count of available items of this product from our big warehouse. */
  @Deprecated public java.lang.Integer warehouseCount;
  /** Ids of all the variants of this product family, including this very product id. */
  @Deprecated public java.util.List<com.bigCorp.UID> familyVariants;
  /** Category of this product, if relevant */
  @Deprecated public com.bigCorp.Category category;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Product() {}

  /**
   * All-args constructor.
   * @param id Identifier of this product. Will be cast to a UUID.
   * @param retailPrice What a customer must pay to get one item. Gross profit for each sold product.
   * @param warehouseCount If known, count of available items of this product from our big warehouse.
   * @param familyVariants Ids of all the variants of this product family, including this very product id.
   * @param category Category of this product, if relevant
   */
  public Product(com.bigCorp.UID id, java.math.BigDecimal retailPrice, java.lang.Integer warehouseCount, java.util.List<com.bigCorp.UID> familyVariants, com.bigCorp.Category category) {
    this.id = id;
    this.retailPrice = retailPrice;
    this.warehouseCount = warehouseCount;
    this.familyVariants = familyVariants;
    this.category = category;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return retailPrice;
    case 2: return warehouseCount;
    case 3: return familyVariants;
    case 4: return category;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  protected static final org.apache.avro.data.TimeConversions.DateConversion DATE_CONVERSION = new org.apache.avro.data.TimeConversions.DateConversion();
  protected static final org.apache.avro.data.TimeConversions.TimeConversion TIME_CONVERSION = new org.apache.avro.data.TimeConversions.TimeConversion();
  protected static final org.apache.avro.data.TimeConversions.TimestampConversion TIMESTAMP_CONVERSION = new org.apache.avro.data.TimeConversions.TimestampConversion();
  protected static final org.apache.avro.Conversions.DecimalConversion DECIMAL_CONVERSION = new org.apache.avro.Conversions.DecimalConversion();

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      null,
      DECIMAL_CONVERSION,
      null,
      null,
      null,
      null
  };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (com.bigCorp.UID)value$; break;
    case 1: retailPrice = (java.math.BigDecimal)value$; break;
    case 2: warehouseCount = (java.lang.Integer)value$; break;
    case 3: familyVariants = (java.util.List<com.bigCorp.UID>)value$; break;
    case 4: category = (com.bigCorp.Category)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return Identifier of this product. Will be cast to a UUID.
   */
  public com.bigCorp.UID getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * Identifier of this product. Will be cast to a UUID.
   * @param value the value to set.
   */
  public void setId(com.bigCorp.UID value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'retailPrice' field.
   * @return What a customer must pay to get one item. Gross profit for each sold product.
   */
  public java.math.BigDecimal getRetailPrice() {
    return retailPrice;
  }

  /**
   * Sets the value of the 'retailPrice' field.
   * What a customer must pay to get one item. Gross profit for each sold product.
   * @param value the value to set.
   */
  public void setRetailPrice(java.math.BigDecimal value) {
    this.retailPrice = value;
  }

  /**
   * Gets the value of the 'warehouseCount' field.
   * @return If known, count of available items of this product from our big warehouse.
   */
  public java.lang.Integer getWarehouseCount() {
    return warehouseCount;
  }

  /**
   * Sets the value of the 'warehouseCount' field.
   * If known, count of available items of this product from our big warehouse.
   * @param value the value to set.
   */
  public void setWarehouseCount(java.lang.Integer value) {
    this.warehouseCount = value;
  }

  /**
   * Gets the value of the 'familyVariants' field.
   * @return Ids of all the variants of this product family, including this very product id.
   */
  public java.util.List<com.bigCorp.UID> getFamilyVariants() {
    return familyVariants;
  }

  /**
   * Sets the value of the 'familyVariants' field.
   * Ids of all the variants of this product family, including this very product id.
   * @param value the value to set.
   */
  public void setFamilyVariants(java.util.List<com.bigCorp.UID> value) {
    this.familyVariants = value;
  }

  /**
   * Gets the value of the 'category' field.
   * @return Category of this product, if relevant
   */
  public com.bigCorp.Category getCategory() {
    return category;
  }

  /**
   * Sets the value of the 'category' field.
   * Category of this product, if relevant
   * @param value the value to set.
   */
  public void setCategory(com.bigCorp.Category value) {
    this.category = value;
  }

  /**
   * Creates a new Product RecordBuilder.
   * @return A new Product RecordBuilder
   */
  public static com.bigCorp.Product.Builder newBuilder() {
    return new com.bigCorp.Product.Builder();
  }

  /**
   * Creates a new Product RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Product RecordBuilder
   */
  public static com.bigCorp.Product.Builder newBuilder(com.bigCorp.Product.Builder other) {
    return new com.bigCorp.Product.Builder(other);
  }

  /**
   * Creates a new Product RecordBuilder by copying an existing Product instance.
   * @param other The existing instance to copy.
   * @return A new Product RecordBuilder
   */
  public static com.bigCorp.Product.Builder newBuilder(com.bigCorp.Product other) {
    return new com.bigCorp.Product.Builder(other);
  }

  /**
   * RecordBuilder for Product instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Product>
    implements org.apache.avro.data.RecordBuilder<Product> {

    /** Identifier of this product. Will be cast to a UUID. */
    private com.bigCorp.UID id;
    /** What a customer must pay to get one item. Gross profit for each sold product. */
    private java.math.BigDecimal retailPrice;
    /** If known, count of available items of this product from our big warehouse. */
    private java.lang.Integer warehouseCount;
    /** Ids of all the variants of this product family, including this very product id. */
    private java.util.List<com.bigCorp.UID> familyVariants;
    /** Category of this product, if relevant */
    private com.bigCorp.Category category;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.bigCorp.Product.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.retailPrice)) {
        this.retailPrice = data().deepCopy(fields()[1].schema(), other.retailPrice);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.warehouseCount)) {
        this.warehouseCount = data().deepCopy(fields()[2].schema(), other.warehouseCount);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.familyVariants)) {
        this.familyVariants = data().deepCopy(fields()[3].schema(), other.familyVariants);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.category)) {
        this.category = data().deepCopy(fields()[4].schema(), other.category);
        fieldSetFlags()[4] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Product instance
     * @param other The existing instance to copy.
     */
    private Builder(com.bigCorp.Product other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.retailPrice)) {
        this.retailPrice = data().deepCopy(fields()[1].schema(), other.retailPrice);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.warehouseCount)) {
        this.warehouseCount = data().deepCopy(fields()[2].schema(), other.warehouseCount);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.familyVariants)) {
        this.familyVariants = data().deepCopy(fields()[3].schema(), other.familyVariants);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.category)) {
        this.category = data().deepCopy(fields()[4].schema(), other.category);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * Identifier of this product. Will be cast to a UUID.
      * @return The value.
      */
    public com.bigCorp.UID getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * Identifier of this product. Will be cast to a UUID.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.bigCorp.Product.Builder setId(com.bigCorp.UID value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * Identifier of this product. Will be cast to a UUID.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * Identifier of this product. Will be cast to a UUID.
      * @return This builder.
      */
    public com.bigCorp.Product.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'retailPrice' field.
      * What a customer must pay to get one item. Gross profit for each sold product.
      * @return The value.
      */
    public java.math.BigDecimal getRetailPrice() {
      return retailPrice;
    }

    /**
      * Sets the value of the 'retailPrice' field.
      * What a customer must pay to get one item. Gross profit for each sold product.
      * @param value The value of 'retailPrice'.
      * @return This builder.
      */
    public com.bigCorp.Product.Builder setRetailPrice(java.math.BigDecimal value) {
      validate(fields()[1], value);
      this.retailPrice = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'retailPrice' field has been set.
      * What a customer must pay to get one item. Gross profit for each sold product.
      * @return True if the 'retailPrice' field has been set, false otherwise.
      */
    public boolean hasRetailPrice() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'retailPrice' field.
      * What a customer must pay to get one item. Gross profit for each sold product.
      * @return This builder.
      */
    public com.bigCorp.Product.Builder clearRetailPrice() {
      retailPrice = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'warehouseCount' field.
      * If known, count of available items of this product from our big warehouse.
      * @return The value.
      */
    public java.lang.Integer getWarehouseCount() {
      return warehouseCount;
    }

    /**
      * Sets the value of the 'warehouseCount' field.
      * If known, count of available items of this product from our big warehouse.
      * @param value The value of 'warehouseCount'.
      * @return This builder.
      */
    public com.bigCorp.Product.Builder setWarehouseCount(java.lang.Integer value) {
      validate(fields()[2], value);
      this.warehouseCount = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'warehouseCount' field has been set.
      * If known, count of available items of this product from our big warehouse.
      * @return True if the 'warehouseCount' field has been set, false otherwise.
      */
    public boolean hasWarehouseCount() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'warehouseCount' field.
      * If known, count of available items of this product from our big warehouse.
      * @return This builder.
      */
    public com.bigCorp.Product.Builder clearWarehouseCount() {
      warehouseCount = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'familyVariants' field.
      * Ids of all the variants of this product family, including this very product id.
      * @return The value.
      */
    public java.util.List<com.bigCorp.UID> getFamilyVariants() {
      return familyVariants;
    }

    /**
      * Sets the value of the 'familyVariants' field.
      * Ids of all the variants of this product family, including this very product id.
      * @param value The value of 'familyVariants'.
      * @return This builder.
      */
    public com.bigCorp.Product.Builder setFamilyVariants(java.util.List<com.bigCorp.UID> value) {
      validate(fields()[3], value);
      this.familyVariants = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'familyVariants' field has been set.
      * Ids of all the variants of this product family, including this very product id.
      * @return True if the 'familyVariants' field has been set, false otherwise.
      */
    public boolean hasFamilyVariants() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'familyVariants' field.
      * Ids of all the variants of this product family, including this very product id.
      * @return This builder.
      */
    public com.bigCorp.Product.Builder clearFamilyVariants() {
      familyVariants = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'category' field.
      * Category of this product, if relevant
      * @return The value.
      */
    public com.bigCorp.Category getCategory() {
      return category;
    }

    /**
      * Sets the value of the 'category' field.
      * Category of this product, if relevant
      * @param value The value of 'category'.
      * @return This builder.
      */
    public com.bigCorp.Product.Builder setCategory(com.bigCorp.Category value) {
      validate(fields()[4], value);
      this.category = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'category' field has been set.
      * Category of this product, if relevant
      * @return True if the 'category' field has been set, false otherwise.
      */
    public boolean hasCategory() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'category' field.
      * Category of this product, if relevant
      * @return This builder.
      */
    public com.bigCorp.Product.Builder clearCategory() {
      category = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Product build() {
      try {
        Product record = new Product();
        record.id = fieldSetFlags()[0] ? this.id : (com.bigCorp.UID) defaultValue(fields()[0], record.getConversion(0));
        record.retailPrice = fieldSetFlags()[1] ? this.retailPrice : (java.math.BigDecimal) defaultValue(fields()[1], record.getConversion(1));
        record.warehouseCount = fieldSetFlags()[2] ? this.warehouseCount : (java.lang.Integer) defaultValue(fields()[2], record.getConversion(2));
        record.familyVariants = fieldSetFlags()[3] ? this.familyVariants : (java.util.List<com.bigCorp.UID>) defaultValue(fields()[3], record.getConversion(3));
        record.category = fieldSetFlags()[4] ? this.category : (com.bigCorp.Category) defaultValue(fields()[4], record.getConversion(4));
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Product>
    WRITER$ = (org.apache.avro.io.DatumWriter<Product>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Product>
    READER$ = (org.apache.avro.io.DatumReader<Product>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}