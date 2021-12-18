/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package dove;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class UnionRecord extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 9039648113878838681L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"UnionRecord\",\"namespace\":\"dove\",\"fields\":[{\"name\":\"nullBoolean\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"booleanInt\",\"type\":[\"boolean\",\"int\"]},{\"name\":\"intBytes\",\"type\":[\"int\",\"bytes\"]},{\"name\":\"stringLong\",\"type\":[\"string\",\"long\"]},{\"name\":\"someEnumDouble\",\"type\":[{\"type\":\"enum\",\"name\":\"SomeEnum\",\"symbols\":[\"ONE\",\"Two\",\"three\"]},\"double\"]},{\"name\":\"fixed8Float\",\"type\":[{\"type\":\"fixed\",\"name\":\"Fixed8\",\"size\":8},\"float\"]},{\"name\":\"parentRecordEmptyRecord\",\"type\":[{\"type\":\"record\",\"name\":\"ParentRecord\",\"fields\":[{\"name\":\"child\",\"type\":{\"type\":\"record\",\"name\":\"PrimitiveTypes\",\"fields\":[{\"name\":\"aNull\",\"type\":\"null\"},{\"name\":\"aBoolean\",\"type\":\"boolean\"},{\"name\":\"aInt\",\"type\":\"int\"},{\"name\":\"aLong\",\"type\":\"long\"},{\"name\":\"aFloat\",\"type\":\"float\"},{\"name\":\"aDouble\",\"type\":\"double\"},{\"name\":\"aBytes\",\"type\":\"bytes\"},{\"name\":\"aString\",\"type\":\"string\"}]}}]},{\"type\":\"record\",\"name\":\"EmptyRecord\",\"fields\":[]}]},{\"name\":\"nullSomeEnumCardSuitFixed8Fixed16Int\",\"type\":[\"null\",\"SomeEnum\",{\"type\":\"enum\",\"name\":\"CardSuit\",\"symbols\":[\"CLUB\",\"DIAMOND\",\"HEART\",\"SPADE\"]},\"Fixed8\",{\"type\":\"fixed\",\"name\":\"Fixed16\",\"size\":16},\"int\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<UnionRecord> ENCODER =
      new BinaryMessageEncoder<UnionRecord>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<UnionRecord> DECODER =
      new BinaryMessageDecoder<UnionRecord>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<UnionRecord> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<UnionRecord> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<UnionRecord>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this UnionRecord to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a UnionRecord from a ByteBuffer. */
  public static UnionRecord fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.Boolean nullBoolean;
  @Deprecated public java.lang.Object booleanInt;
  @Deprecated public java.lang.Object intBytes;
  @Deprecated public java.lang.Object stringLong;
  @Deprecated public java.lang.Object someEnumDouble;
  @Deprecated public java.lang.Object fixed8Float;
  @Deprecated public java.lang.Object parentRecordEmptyRecord;
  @Deprecated public java.lang.Object nullSomeEnumCardSuitFixed8Fixed16Int;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public UnionRecord() {}

  /**
   * All-args constructor.
   * @param nullBoolean The new value for nullBoolean
   * @param booleanInt The new value for booleanInt
   * @param intBytes The new value for intBytes
   * @param stringLong The new value for stringLong
   * @param someEnumDouble The new value for someEnumDouble
   * @param fixed8Float The new value for fixed8Float
   * @param parentRecordEmptyRecord The new value for parentRecordEmptyRecord
   * @param nullSomeEnumCardSuitFixed8Fixed16Int The new value for nullSomeEnumCardSuitFixed8Fixed16Int
   */
  public UnionRecord(java.lang.Boolean nullBoolean, java.lang.Object booleanInt, java.lang.Object intBytes, java.lang.Object stringLong, java.lang.Object someEnumDouble, java.lang.Object fixed8Float, java.lang.Object parentRecordEmptyRecord, java.lang.Object nullSomeEnumCardSuitFixed8Fixed16Int) {
    this.nullBoolean = nullBoolean;
    this.booleanInt = booleanInt;
    this.intBytes = intBytes;
    this.stringLong = stringLong;
    this.someEnumDouble = someEnumDouble;
    this.fixed8Float = fixed8Float;
    this.parentRecordEmptyRecord = parentRecordEmptyRecord;
    this.nullSomeEnumCardSuitFixed8Fixed16Int = nullSomeEnumCardSuitFixed8Fixed16Int;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return nullBoolean;
    case 1: return booleanInt;
    case 2: return intBytes;
    case 3: return stringLong;
    case 4: return someEnumDouble;
    case 5: return fixed8Float;
    case 6: return parentRecordEmptyRecord;
    case 7: return nullSomeEnumCardSuitFixed8Fixed16Int;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: nullBoolean = (java.lang.Boolean)value$; break;
    case 1: booleanInt = (java.lang.Object)value$; break;
    case 2: intBytes = (java.lang.Object)value$; break;
    case 3: stringLong = (java.lang.Object)value$; break;
    case 4: someEnumDouble = (java.lang.Object)value$; break;
    case 5: fixed8Float = (java.lang.Object)value$; break;
    case 6: parentRecordEmptyRecord = (java.lang.Object)value$; break;
    case 7: nullSomeEnumCardSuitFixed8Fixed16Int = (java.lang.Object)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'nullBoolean' field.
   * @return The value of the 'nullBoolean' field.
   */
  public java.lang.Boolean getNullBoolean() {
    return nullBoolean;
  }

  /**
   * Sets the value of the 'nullBoolean' field.
   * @param value the value to set.
   */
  public void setNullBoolean(java.lang.Boolean value) {
    this.nullBoolean = value;
  }

  /**
   * Gets the value of the 'booleanInt' field.
   * @return The value of the 'booleanInt' field.
   */
  public java.lang.Object getBooleanInt() {
    return booleanInt;
  }

  /**
   * Sets the value of the 'booleanInt' field.
   * @param value the value to set.
   */
  public void setBooleanInt(java.lang.Object value) {
    this.booleanInt = value;
  }

  /**
   * Gets the value of the 'intBytes' field.
   * @return The value of the 'intBytes' field.
   */
  public java.lang.Object getIntBytes() {
    return intBytes;
  }

  /**
   * Sets the value of the 'intBytes' field.
   * @param value the value to set.
   */
  public void setIntBytes(java.lang.Object value) {
    this.intBytes = value;
  }

  /**
   * Gets the value of the 'stringLong' field.
   * @return The value of the 'stringLong' field.
   */
  public java.lang.Object getStringLong() {
    return stringLong;
  }

  /**
   * Sets the value of the 'stringLong' field.
   * @param value the value to set.
   */
  public void setStringLong(java.lang.Object value) {
    this.stringLong = value;
  }

  /**
   * Gets the value of the 'someEnumDouble' field.
   * @return The value of the 'someEnumDouble' field.
   */
  public java.lang.Object getSomeEnumDouble() {
    return someEnumDouble;
  }

  /**
   * Sets the value of the 'someEnumDouble' field.
   * @param value the value to set.
   */
  public void setSomeEnumDouble(java.lang.Object value) {
    this.someEnumDouble = value;
  }

  /**
   * Gets the value of the 'fixed8Float' field.
   * @return The value of the 'fixed8Float' field.
   */
  public java.lang.Object getFixed8Float() {
    return fixed8Float;
  }

  /**
   * Sets the value of the 'fixed8Float' field.
   * @param value the value to set.
   */
  public void setFixed8Float(java.lang.Object value) {
    this.fixed8Float = value;
  }

  /**
   * Gets the value of the 'parentRecordEmptyRecord' field.
   * @return The value of the 'parentRecordEmptyRecord' field.
   */
  public java.lang.Object getParentRecordEmptyRecord() {
    return parentRecordEmptyRecord;
  }

  /**
   * Sets the value of the 'parentRecordEmptyRecord' field.
   * @param value the value to set.
   */
  public void setParentRecordEmptyRecord(java.lang.Object value) {
    this.parentRecordEmptyRecord = value;
  }

  /**
   * Gets the value of the 'nullSomeEnumCardSuitFixed8Fixed16Int' field.
   * @return The value of the 'nullSomeEnumCardSuitFixed8Fixed16Int' field.
   */
  public java.lang.Object getNullSomeEnumCardSuitFixed8Fixed16Int() {
    return nullSomeEnumCardSuitFixed8Fixed16Int;
  }

  /**
   * Sets the value of the 'nullSomeEnumCardSuitFixed8Fixed16Int' field.
   * @param value the value to set.
   */
  public void setNullSomeEnumCardSuitFixed8Fixed16Int(java.lang.Object value) {
    this.nullSomeEnumCardSuitFixed8Fixed16Int = value;
  }

  /**
   * Creates a new UnionRecord RecordBuilder.
   * @return A new UnionRecord RecordBuilder
   */
  public static dove.UnionRecord.Builder newBuilder() {
    return new dove.UnionRecord.Builder();
  }

  /**
   * Creates a new UnionRecord RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new UnionRecord RecordBuilder
   */
  public static dove.UnionRecord.Builder newBuilder(dove.UnionRecord.Builder other) {
    return new dove.UnionRecord.Builder(other);
  }

  /**
   * Creates a new UnionRecord RecordBuilder by copying an existing UnionRecord instance.
   * @param other The existing instance to copy.
   * @return A new UnionRecord RecordBuilder
   */
  public static dove.UnionRecord.Builder newBuilder(dove.UnionRecord other) {
    return new dove.UnionRecord.Builder(other);
  }

  /**
   * RecordBuilder for UnionRecord instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<UnionRecord>
    implements org.apache.avro.data.RecordBuilder<UnionRecord> {

    private java.lang.Boolean nullBoolean;
    private java.lang.Object booleanInt;
    private java.lang.Object intBytes;
    private java.lang.Object stringLong;
    private java.lang.Object someEnumDouble;
    private java.lang.Object fixed8Float;
    private java.lang.Object parentRecordEmptyRecord;
    private java.lang.Object nullSomeEnumCardSuitFixed8Fixed16Int;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(dove.UnionRecord.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.nullBoolean)) {
        this.nullBoolean = data().deepCopy(fields()[0].schema(), other.nullBoolean);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.booleanInt)) {
        this.booleanInt = data().deepCopy(fields()[1].schema(), other.booleanInt);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.intBytes)) {
        this.intBytes = data().deepCopy(fields()[2].schema(), other.intBytes);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.stringLong)) {
        this.stringLong = data().deepCopy(fields()[3].schema(), other.stringLong);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.someEnumDouble)) {
        this.someEnumDouble = data().deepCopy(fields()[4].schema(), other.someEnumDouble);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.fixed8Float)) {
        this.fixed8Float = data().deepCopy(fields()[5].schema(), other.fixed8Float);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.parentRecordEmptyRecord)) {
        this.parentRecordEmptyRecord = data().deepCopy(fields()[6].schema(), other.parentRecordEmptyRecord);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.nullSomeEnumCardSuitFixed8Fixed16Int)) {
        this.nullSomeEnumCardSuitFixed8Fixed16Int = data().deepCopy(fields()[7].schema(), other.nullSomeEnumCardSuitFixed8Fixed16Int);
        fieldSetFlags()[7] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing UnionRecord instance
     * @param other The existing instance to copy.
     */
    private Builder(dove.UnionRecord other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.nullBoolean)) {
        this.nullBoolean = data().deepCopy(fields()[0].schema(), other.nullBoolean);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.booleanInt)) {
        this.booleanInt = data().deepCopy(fields()[1].schema(), other.booleanInt);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.intBytes)) {
        this.intBytes = data().deepCopy(fields()[2].schema(), other.intBytes);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.stringLong)) {
        this.stringLong = data().deepCopy(fields()[3].schema(), other.stringLong);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.someEnumDouble)) {
        this.someEnumDouble = data().deepCopy(fields()[4].schema(), other.someEnumDouble);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.fixed8Float)) {
        this.fixed8Float = data().deepCopy(fields()[5].schema(), other.fixed8Float);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.parentRecordEmptyRecord)) {
        this.parentRecordEmptyRecord = data().deepCopy(fields()[6].schema(), other.parentRecordEmptyRecord);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.nullSomeEnumCardSuitFixed8Fixed16Int)) {
        this.nullSomeEnumCardSuitFixed8Fixed16Int = data().deepCopy(fields()[7].schema(), other.nullSomeEnumCardSuitFixed8Fixed16Int);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'nullBoolean' field.
      * @return The value.
      */
    public java.lang.Boolean getNullBoolean() {
      return nullBoolean;
    }

    /**
      * Sets the value of the 'nullBoolean' field.
      * @param value The value of 'nullBoolean'.
      * @return This builder.
      */
    public dove.UnionRecord.Builder setNullBoolean(java.lang.Boolean value) {
      validate(fields()[0], value);
      this.nullBoolean = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'nullBoolean' field has been set.
      * @return True if the 'nullBoolean' field has been set, false otherwise.
      */
    public boolean hasNullBoolean() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'nullBoolean' field.
      * @return This builder.
      */
    public dove.UnionRecord.Builder clearNullBoolean() {
      nullBoolean = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'booleanInt' field.
      * @return The value.
      */
    public java.lang.Object getBooleanInt() {
      return booleanInt;
    }

    /**
      * Sets the value of the 'booleanInt' field.
      * @param value The value of 'booleanInt'.
      * @return This builder.
      */
    public dove.UnionRecord.Builder setBooleanInt(java.lang.Object value) {
      validate(fields()[1], value);
      this.booleanInt = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'booleanInt' field has been set.
      * @return True if the 'booleanInt' field has been set, false otherwise.
      */
    public boolean hasBooleanInt() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'booleanInt' field.
      * @return This builder.
      */
    public dove.UnionRecord.Builder clearBooleanInt() {
      booleanInt = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'intBytes' field.
      * @return The value.
      */
    public java.lang.Object getIntBytes() {
      return intBytes;
    }

    /**
      * Sets the value of the 'intBytes' field.
      * @param value The value of 'intBytes'.
      * @return This builder.
      */
    public dove.UnionRecord.Builder setIntBytes(java.lang.Object value) {
      validate(fields()[2], value);
      this.intBytes = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'intBytes' field has been set.
      * @return True if the 'intBytes' field has been set, false otherwise.
      */
    public boolean hasIntBytes() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'intBytes' field.
      * @return This builder.
      */
    public dove.UnionRecord.Builder clearIntBytes() {
      intBytes = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'stringLong' field.
      * @return The value.
      */
    public java.lang.Object getStringLong() {
      return stringLong;
    }

    /**
      * Sets the value of the 'stringLong' field.
      * @param value The value of 'stringLong'.
      * @return This builder.
      */
    public dove.UnionRecord.Builder setStringLong(java.lang.Object value) {
      validate(fields()[3], value);
      this.stringLong = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'stringLong' field has been set.
      * @return True if the 'stringLong' field has been set, false otherwise.
      */
    public boolean hasStringLong() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'stringLong' field.
      * @return This builder.
      */
    public dove.UnionRecord.Builder clearStringLong() {
      stringLong = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'someEnumDouble' field.
      * @return The value.
      */
    public java.lang.Object getSomeEnumDouble() {
      return someEnumDouble;
    }

    /**
      * Sets the value of the 'someEnumDouble' field.
      * @param value The value of 'someEnumDouble'.
      * @return This builder.
      */
    public dove.UnionRecord.Builder setSomeEnumDouble(java.lang.Object value) {
      validate(fields()[4], value);
      this.someEnumDouble = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'someEnumDouble' field has been set.
      * @return True if the 'someEnumDouble' field has been set, false otherwise.
      */
    public boolean hasSomeEnumDouble() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'someEnumDouble' field.
      * @return This builder.
      */
    public dove.UnionRecord.Builder clearSomeEnumDouble() {
      someEnumDouble = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'fixed8Float' field.
      * @return The value.
      */
    public java.lang.Object getFixed8Float() {
      return fixed8Float;
    }

    /**
      * Sets the value of the 'fixed8Float' field.
      * @param value The value of 'fixed8Float'.
      * @return This builder.
      */
    public dove.UnionRecord.Builder setFixed8Float(java.lang.Object value) {
      validate(fields()[5], value);
      this.fixed8Float = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'fixed8Float' field has been set.
      * @return True if the 'fixed8Float' field has been set, false otherwise.
      */
    public boolean hasFixed8Float() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'fixed8Float' field.
      * @return This builder.
      */
    public dove.UnionRecord.Builder clearFixed8Float() {
      fixed8Float = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'parentRecordEmptyRecord' field.
      * @return The value.
      */
    public java.lang.Object getParentRecordEmptyRecord() {
      return parentRecordEmptyRecord;
    }

    /**
      * Sets the value of the 'parentRecordEmptyRecord' field.
      * @param value The value of 'parentRecordEmptyRecord'.
      * @return This builder.
      */
    public dove.UnionRecord.Builder setParentRecordEmptyRecord(java.lang.Object value) {
      validate(fields()[6], value);
      this.parentRecordEmptyRecord = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'parentRecordEmptyRecord' field has been set.
      * @return True if the 'parentRecordEmptyRecord' field has been set, false otherwise.
      */
    public boolean hasParentRecordEmptyRecord() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'parentRecordEmptyRecord' field.
      * @return This builder.
      */
    public dove.UnionRecord.Builder clearParentRecordEmptyRecord() {
      parentRecordEmptyRecord = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'nullSomeEnumCardSuitFixed8Fixed16Int' field.
      * @return The value.
      */
    public java.lang.Object getNullSomeEnumCardSuitFixed8Fixed16Int() {
      return nullSomeEnumCardSuitFixed8Fixed16Int;
    }

    /**
      * Sets the value of the 'nullSomeEnumCardSuitFixed8Fixed16Int' field.
      * @param value The value of 'nullSomeEnumCardSuitFixed8Fixed16Int'.
      * @return This builder.
      */
    public dove.UnionRecord.Builder setNullSomeEnumCardSuitFixed8Fixed16Int(java.lang.Object value) {
      validate(fields()[7], value);
      this.nullSomeEnumCardSuitFixed8Fixed16Int = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'nullSomeEnumCardSuitFixed8Fixed16Int' field has been set.
      * @return True if the 'nullSomeEnumCardSuitFixed8Fixed16Int' field has been set, false otherwise.
      */
    public boolean hasNullSomeEnumCardSuitFixed8Fixed16Int() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'nullSomeEnumCardSuitFixed8Fixed16Int' field.
      * @return This builder.
      */
    public dove.UnionRecord.Builder clearNullSomeEnumCardSuitFixed8Fixed16Int() {
      nullSomeEnumCardSuitFixed8Fixed16Int = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public UnionRecord build() {
      try {
        UnionRecord record = new UnionRecord();
        record.nullBoolean = fieldSetFlags()[0] ? this.nullBoolean : (java.lang.Boolean) defaultValue(fields()[0]);
        record.booleanInt = fieldSetFlags()[1] ? this.booleanInt : (java.lang.Object) defaultValue(fields()[1]);
        record.intBytes = fieldSetFlags()[2] ? this.intBytes : (java.lang.Object) defaultValue(fields()[2]);
        record.stringLong = fieldSetFlags()[3] ? this.stringLong : (java.lang.Object) defaultValue(fields()[3]);
        record.someEnumDouble = fieldSetFlags()[4] ? this.someEnumDouble : (java.lang.Object) defaultValue(fields()[4]);
        record.fixed8Float = fieldSetFlags()[5] ? this.fixed8Float : (java.lang.Object) defaultValue(fields()[5]);
        record.parentRecordEmptyRecord = fieldSetFlags()[6] ? this.parentRecordEmptyRecord : (java.lang.Object) defaultValue(fields()[6]);
        record.nullSomeEnumCardSuitFixed8Fixed16Int = fieldSetFlags()[7] ? this.nullSomeEnumCardSuitFixed8Fixed16Int : (java.lang.Object) defaultValue(fields()[7]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<UnionRecord>
    WRITER$ = (org.apache.avro.io.DatumWriter<UnionRecord>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<UnionRecord>
    READER$ = (org.apache.avro.io.DatumReader<UnionRecord>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}