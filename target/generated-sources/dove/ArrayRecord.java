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
public class ArrayRecord extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -826005322057446660L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ArrayRecord\",\"namespace\":\"dove\",\"fields\":[{\"name\":\"arrayNull\",\"type\":{\"type\":\"array\",\"items\":\"null\"}},{\"name\":\"arrayBoolean\",\"type\":{\"type\":\"array\",\"items\":\"boolean\"}},{\"name\":\"arrayInt\",\"type\":{\"type\":\"array\",\"items\":\"int\"}},{\"name\":\"arrayLong\",\"type\":{\"type\":\"array\",\"items\":\"long\"}},{\"name\":\"arrayFloat\",\"type\":{\"type\":\"array\",\"items\":\"float\"}},{\"name\":\"arrayDouble\",\"type\":{\"type\":\"array\",\"items\":\"double\"}},{\"name\":\"arrayBytes\",\"type\":{\"type\":\"array\",\"items\":\"bytes\"}},{\"name\":\"arrayString\",\"type\":{\"type\":\"array\",\"items\":\"string\"}},{\"name\":\"arraySomeEnum\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"enum\",\"name\":\"SomeEnum\",\"symbols\":[\"ONE\",\"Two\",\"three\"]}}},{\"name\":\"arrayEmptyRecord\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"EmptyRecord\",\"fields\":[]}}},{\"name\":\"arrayFixed8\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"fixed\",\"name\":\"Fixed8\",\"size\":8}}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<ArrayRecord> ENCODER =
      new BinaryMessageEncoder<ArrayRecord>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<ArrayRecord> DECODER =
      new BinaryMessageDecoder<ArrayRecord>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<ArrayRecord> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<ArrayRecord> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<ArrayRecord>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this ArrayRecord to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a ArrayRecord from a ByteBuffer. */
  public static ArrayRecord fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.util.List<java.lang.Void> arrayNull;
  @Deprecated public java.util.List<java.lang.Boolean> arrayBoolean;
  @Deprecated public java.util.List<java.lang.Integer> arrayInt;
  @Deprecated public java.util.List<java.lang.Long> arrayLong;
  @Deprecated public java.util.List<java.lang.Float> arrayFloat;
  @Deprecated public java.util.List<java.lang.Double> arrayDouble;
  @Deprecated public java.util.List<java.nio.ByteBuffer> arrayBytes;
  @Deprecated public java.util.List<java.lang.CharSequence> arrayString;
  @Deprecated public java.util.List<dove.SomeEnum> arraySomeEnum;
  @Deprecated public java.util.List<dove.EmptyRecord> arrayEmptyRecord;
  @Deprecated public java.util.List<dove.Fixed8> arrayFixed8;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public ArrayRecord() {}

  /**
   * All-args constructor.
   * @param arrayNull The new value for arrayNull
   * @param arrayBoolean The new value for arrayBoolean
   * @param arrayInt The new value for arrayInt
   * @param arrayLong The new value for arrayLong
   * @param arrayFloat The new value for arrayFloat
   * @param arrayDouble The new value for arrayDouble
   * @param arrayBytes The new value for arrayBytes
   * @param arrayString The new value for arrayString
   * @param arraySomeEnum The new value for arraySomeEnum
   * @param arrayEmptyRecord The new value for arrayEmptyRecord
   * @param arrayFixed8 The new value for arrayFixed8
   */
  public ArrayRecord(java.util.List<java.lang.Void> arrayNull, java.util.List<java.lang.Boolean> arrayBoolean, java.util.List<java.lang.Integer> arrayInt, java.util.List<java.lang.Long> arrayLong, java.util.List<java.lang.Float> arrayFloat, java.util.List<java.lang.Double> arrayDouble, java.util.List<java.nio.ByteBuffer> arrayBytes, java.util.List<java.lang.CharSequence> arrayString, java.util.List<dove.SomeEnum> arraySomeEnum, java.util.List<dove.EmptyRecord> arrayEmptyRecord, java.util.List<dove.Fixed8> arrayFixed8) {
    this.arrayNull = arrayNull;
    this.arrayBoolean = arrayBoolean;
    this.arrayInt = arrayInt;
    this.arrayLong = arrayLong;
    this.arrayFloat = arrayFloat;
    this.arrayDouble = arrayDouble;
    this.arrayBytes = arrayBytes;
    this.arrayString = arrayString;
    this.arraySomeEnum = arraySomeEnum;
    this.arrayEmptyRecord = arrayEmptyRecord;
    this.arrayFixed8 = arrayFixed8;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return arrayNull;
    case 1: return arrayBoolean;
    case 2: return arrayInt;
    case 3: return arrayLong;
    case 4: return arrayFloat;
    case 5: return arrayDouble;
    case 6: return arrayBytes;
    case 7: return arrayString;
    case 8: return arraySomeEnum;
    case 9: return arrayEmptyRecord;
    case 10: return arrayFixed8;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: arrayNull = (java.util.List<java.lang.Void>)value$; break;
    case 1: arrayBoolean = (java.util.List<java.lang.Boolean>)value$; break;
    case 2: arrayInt = (java.util.List<java.lang.Integer>)value$; break;
    case 3: arrayLong = (java.util.List<java.lang.Long>)value$; break;
    case 4: arrayFloat = (java.util.List<java.lang.Float>)value$; break;
    case 5: arrayDouble = (java.util.List<java.lang.Double>)value$; break;
    case 6: arrayBytes = (java.util.List<java.nio.ByteBuffer>)value$; break;
    case 7: arrayString = (java.util.List<java.lang.CharSequence>)value$; break;
    case 8: arraySomeEnum = (java.util.List<dove.SomeEnum>)value$; break;
    case 9: arrayEmptyRecord = (java.util.List<dove.EmptyRecord>)value$; break;
    case 10: arrayFixed8 = (java.util.List<dove.Fixed8>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'arrayNull' field.
   * @return The value of the 'arrayNull' field.
   */
  public java.util.List<java.lang.Void> getArrayNull() {
    return arrayNull;
  }

  /**
   * Sets the value of the 'arrayNull' field.
   * @param value the value to set.
   */
  public void setArrayNull(java.util.List<java.lang.Void> value) {
    this.arrayNull = value;
  }

  /**
   * Gets the value of the 'arrayBoolean' field.
   * @return The value of the 'arrayBoolean' field.
   */
  public java.util.List<java.lang.Boolean> getArrayBoolean() {
    return arrayBoolean;
  }

  /**
   * Sets the value of the 'arrayBoolean' field.
   * @param value the value to set.
   */
  public void setArrayBoolean(java.util.List<java.lang.Boolean> value) {
    this.arrayBoolean = value;
  }

  /**
   * Gets the value of the 'arrayInt' field.
   * @return The value of the 'arrayInt' field.
   */
  public java.util.List<java.lang.Integer> getArrayInt() {
    return arrayInt;
  }

  /**
   * Sets the value of the 'arrayInt' field.
   * @param value the value to set.
   */
  public void setArrayInt(java.util.List<java.lang.Integer> value) {
    this.arrayInt = value;
  }

  /**
   * Gets the value of the 'arrayLong' field.
   * @return The value of the 'arrayLong' field.
   */
  public java.util.List<java.lang.Long> getArrayLong() {
    return arrayLong;
  }

  /**
   * Sets the value of the 'arrayLong' field.
   * @param value the value to set.
   */
  public void setArrayLong(java.util.List<java.lang.Long> value) {
    this.arrayLong = value;
  }

  /**
   * Gets the value of the 'arrayFloat' field.
   * @return The value of the 'arrayFloat' field.
   */
  public java.util.List<java.lang.Float> getArrayFloat() {
    return arrayFloat;
  }

  /**
   * Sets the value of the 'arrayFloat' field.
   * @param value the value to set.
   */
  public void setArrayFloat(java.util.List<java.lang.Float> value) {
    this.arrayFloat = value;
  }

  /**
   * Gets the value of the 'arrayDouble' field.
   * @return The value of the 'arrayDouble' field.
   */
  public java.util.List<java.lang.Double> getArrayDouble() {
    return arrayDouble;
  }

  /**
   * Sets the value of the 'arrayDouble' field.
   * @param value the value to set.
   */
  public void setArrayDouble(java.util.List<java.lang.Double> value) {
    this.arrayDouble = value;
  }

  /**
   * Gets the value of the 'arrayBytes' field.
   * @return The value of the 'arrayBytes' field.
   */
  public java.util.List<java.nio.ByteBuffer> getArrayBytes() {
    return arrayBytes;
  }

  /**
   * Sets the value of the 'arrayBytes' field.
   * @param value the value to set.
   */
  public void setArrayBytes(java.util.List<java.nio.ByteBuffer> value) {
    this.arrayBytes = value;
  }

  /**
   * Gets the value of the 'arrayString' field.
   * @return The value of the 'arrayString' field.
   */
  public java.util.List<java.lang.CharSequence> getArrayString() {
    return arrayString;
  }

  /**
   * Sets the value of the 'arrayString' field.
   * @param value the value to set.
   */
  public void setArrayString(java.util.List<java.lang.CharSequence> value) {
    this.arrayString = value;
  }

  /**
   * Gets the value of the 'arraySomeEnum' field.
   * @return The value of the 'arraySomeEnum' field.
   */
  public java.util.List<dove.SomeEnum> getArraySomeEnum() {
    return arraySomeEnum;
  }

  /**
   * Sets the value of the 'arraySomeEnum' field.
   * @param value the value to set.
   */
  public void setArraySomeEnum(java.util.List<dove.SomeEnum> value) {
    this.arraySomeEnum = value;
  }

  /**
   * Gets the value of the 'arrayEmptyRecord' field.
   * @return The value of the 'arrayEmptyRecord' field.
   */
  public java.util.List<dove.EmptyRecord> getArrayEmptyRecord() {
    return arrayEmptyRecord;
  }

  /**
   * Sets the value of the 'arrayEmptyRecord' field.
   * @param value the value to set.
   */
  public void setArrayEmptyRecord(java.util.List<dove.EmptyRecord> value) {
    this.arrayEmptyRecord = value;
  }

  /**
   * Gets the value of the 'arrayFixed8' field.
   * @return The value of the 'arrayFixed8' field.
   */
  public java.util.List<dove.Fixed8> getArrayFixed8() {
    return arrayFixed8;
  }

  /**
   * Sets the value of the 'arrayFixed8' field.
   * @param value the value to set.
   */
  public void setArrayFixed8(java.util.List<dove.Fixed8> value) {
    this.arrayFixed8 = value;
  }

  /**
   * Creates a new ArrayRecord RecordBuilder.
   * @return A new ArrayRecord RecordBuilder
   */
  public static dove.ArrayRecord.Builder newBuilder() {
    return new dove.ArrayRecord.Builder();
  }

  /**
   * Creates a new ArrayRecord RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new ArrayRecord RecordBuilder
   */
  public static dove.ArrayRecord.Builder newBuilder(dove.ArrayRecord.Builder other) {
    return new dove.ArrayRecord.Builder(other);
  }

  /**
   * Creates a new ArrayRecord RecordBuilder by copying an existing ArrayRecord instance.
   * @param other The existing instance to copy.
   * @return A new ArrayRecord RecordBuilder
   */
  public static dove.ArrayRecord.Builder newBuilder(dove.ArrayRecord other) {
    return new dove.ArrayRecord.Builder(other);
  }

  /**
   * RecordBuilder for ArrayRecord instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ArrayRecord>
    implements org.apache.avro.data.RecordBuilder<ArrayRecord> {

    private java.util.List<java.lang.Void> arrayNull;
    private java.util.List<java.lang.Boolean> arrayBoolean;
    private java.util.List<java.lang.Integer> arrayInt;
    private java.util.List<java.lang.Long> arrayLong;
    private java.util.List<java.lang.Float> arrayFloat;
    private java.util.List<java.lang.Double> arrayDouble;
    private java.util.List<java.nio.ByteBuffer> arrayBytes;
    private java.util.List<java.lang.CharSequence> arrayString;
    private java.util.List<dove.SomeEnum> arraySomeEnum;
    private java.util.List<dove.EmptyRecord> arrayEmptyRecord;
    private java.util.List<dove.Fixed8> arrayFixed8;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(dove.ArrayRecord.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.arrayNull)) {
        this.arrayNull = data().deepCopy(fields()[0].schema(), other.arrayNull);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.arrayBoolean)) {
        this.arrayBoolean = data().deepCopy(fields()[1].schema(), other.arrayBoolean);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.arrayInt)) {
        this.arrayInt = data().deepCopy(fields()[2].schema(), other.arrayInt);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.arrayLong)) {
        this.arrayLong = data().deepCopy(fields()[3].schema(), other.arrayLong);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.arrayFloat)) {
        this.arrayFloat = data().deepCopy(fields()[4].schema(), other.arrayFloat);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.arrayDouble)) {
        this.arrayDouble = data().deepCopy(fields()[5].schema(), other.arrayDouble);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.arrayBytes)) {
        this.arrayBytes = data().deepCopy(fields()[6].schema(), other.arrayBytes);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.arrayString)) {
        this.arrayString = data().deepCopy(fields()[7].schema(), other.arrayString);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.arraySomeEnum)) {
        this.arraySomeEnum = data().deepCopy(fields()[8].schema(), other.arraySomeEnum);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.arrayEmptyRecord)) {
        this.arrayEmptyRecord = data().deepCopy(fields()[9].schema(), other.arrayEmptyRecord);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.arrayFixed8)) {
        this.arrayFixed8 = data().deepCopy(fields()[10].schema(), other.arrayFixed8);
        fieldSetFlags()[10] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing ArrayRecord instance
     * @param other The existing instance to copy.
     */
    private Builder(dove.ArrayRecord other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.arrayNull)) {
        this.arrayNull = data().deepCopy(fields()[0].schema(), other.arrayNull);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.arrayBoolean)) {
        this.arrayBoolean = data().deepCopy(fields()[1].schema(), other.arrayBoolean);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.arrayInt)) {
        this.arrayInt = data().deepCopy(fields()[2].schema(), other.arrayInt);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.arrayLong)) {
        this.arrayLong = data().deepCopy(fields()[3].schema(), other.arrayLong);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.arrayFloat)) {
        this.arrayFloat = data().deepCopy(fields()[4].schema(), other.arrayFloat);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.arrayDouble)) {
        this.arrayDouble = data().deepCopy(fields()[5].schema(), other.arrayDouble);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.arrayBytes)) {
        this.arrayBytes = data().deepCopy(fields()[6].schema(), other.arrayBytes);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.arrayString)) {
        this.arrayString = data().deepCopy(fields()[7].schema(), other.arrayString);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.arraySomeEnum)) {
        this.arraySomeEnum = data().deepCopy(fields()[8].schema(), other.arraySomeEnum);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.arrayEmptyRecord)) {
        this.arrayEmptyRecord = data().deepCopy(fields()[9].schema(), other.arrayEmptyRecord);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.arrayFixed8)) {
        this.arrayFixed8 = data().deepCopy(fields()[10].schema(), other.arrayFixed8);
        fieldSetFlags()[10] = true;
      }
    }

    /**
      * Gets the value of the 'arrayNull' field.
      * @return The value.
      */
    public java.util.List<java.lang.Void> getArrayNull() {
      return arrayNull;
    }

    /**
      * Sets the value of the 'arrayNull' field.
      * @param value The value of 'arrayNull'.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder setArrayNull(java.util.List<java.lang.Void> value) {
      validate(fields()[0], value);
      this.arrayNull = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'arrayNull' field has been set.
      * @return True if the 'arrayNull' field has been set, false otherwise.
      */
    public boolean hasArrayNull() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'arrayNull' field.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder clearArrayNull() {
      arrayNull = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'arrayBoolean' field.
      * @return The value.
      */
    public java.util.List<java.lang.Boolean> getArrayBoolean() {
      return arrayBoolean;
    }

    /**
      * Sets the value of the 'arrayBoolean' field.
      * @param value The value of 'arrayBoolean'.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder setArrayBoolean(java.util.List<java.lang.Boolean> value) {
      validate(fields()[1], value);
      this.arrayBoolean = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'arrayBoolean' field has been set.
      * @return True if the 'arrayBoolean' field has been set, false otherwise.
      */
    public boolean hasArrayBoolean() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'arrayBoolean' field.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder clearArrayBoolean() {
      arrayBoolean = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'arrayInt' field.
      * @return The value.
      */
    public java.util.List<java.lang.Integer> getArrayInt() {
      return arrayInt;
    }

    /**
      * Sets the value of the 'arrayInt' field.
      * @param value The value of 'arrayInt'.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder setArrayInt(java.util.List<java.lang.Integer> value) {
      validate(fields()[2], value);
      this.arrayInt = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'arrayInt' field has been set.
      * @return True if the 'arrayInt' field has been set, false otherwise.
      */
    public boolean hasArrayInt() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'arrayInt' field.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder clearArrayInt() {
      arrayInt = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'arrayLong' field.
      * @return The value.
      */
    public java.util.List<java.lang.Long> getArrayLong() {
      return arrayLong;
    }

    /**
      * Sets the value of the 'arrayLong' field.
      * @param value The value of 'arrayLong'.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder setArrayLong(java.util.List<java.lang.Long> value) {
      validate(fields()[3], value);
      this.arrayLong = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'arrayLong' field has been set.
      * @return True if the 'arrayLong' field has been set, false otherwise.
      */
    public boolean hasArrayLong() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'arrayLong' field.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder clearArrayLong() {
      arrayLong = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'arrayFloat' field.
      * @return The value.
      */
    public java.util.List<java.lang.Float> getArrayFloat() {
      return arrayFloat;
    }

    /**
      * Sets the value of the 'arrayFloat' field.
      * @param value The value of 'arrayFloat'.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder setArrayFloat(java.util.List<java.lang.Float> value) {
      validate(fields()[4], value);
      this.arrayFloat = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'arrayFloat' field has been set.
      * @return True if the 'arrayFloat' field has been set, false otherwise.
      */
    public boolean hasArrayFloat() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'arrayFloat' field.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder clearArrayFloat() {
      arrayFloat = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'arrayDouble' field.
      * @return The value.
      */
    public java.util.List<java.lang.Double> getArrayDouble() {
      return arrayDouble;
    }

    /**
      * Sets the value of the 'arrayDouble' field.
      * @param value The value of 'arrayDouble'.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder setArrayDouble(java.util.List<java.lang.Double> value) {
      validate(fields()[5], value);
      this.arrayDouble = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'arrayDouble' field has been set.
      * @return True if the 'arrayDouble' field has been set, false otherwise.
      */
    public boolean hasArrayDouble() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'arrayDouble' field.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder clearArrayDouble() {
      arrayDouble = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'arrayBytes' field.
      * @return The value.
      */
    public java.util.List<java.nio.ByteBuffer> getArrayBytes() {
      return arrayBytes;
    }

    /**
      * Sets the value of the 'arrayBytes' field.
      * @param value The value of 'arrayBytes'.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder setArrayBytes(java.util.List<java.nio.ByteBuffer> value) {
      validate(fields()[6], value);
      this.arrayBytes = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'arrayBytes' field has been set.
      * @return True if the 'arrayBytes' field has been set, false otherwise.
      */
    public boolean hasArrayBytes() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'arrayBytes' field.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder clearArrayBytes() {
      arrayBytes = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'arrayString' field.
      * @return The value.
      */
    public java.util.List<java.lang.CharSequence> getArrayString() {
      return arrayString;
    }

    /**
      * Sets the value of the 'arrayString' field.
      * @param value The value of 'arrayString'.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder setArrayString(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[7], value);
      this.arrayString = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'arrayString' field has been set.
      * @return True if the 'arrayString' field has been set, false otherwise.
      */
    public boolean hasArrayString() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'arrayString' field.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder clearArrayString() {
      arrayString = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'arraySomeEnum' field.
      * @return The value.
      */
    public java.util.List<dove.SomeEnum> getArraySomeEnum() {
      return arraySomeEnum;
    }

    /**
      * Sets the value of the 'arraySomeEnum' field.
      * @param value The value of 'arraySomeEnum'.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder setArraySomeEnum(java.util.List<dove.SomeEnum> value) {
      validate(fields()[8], value);
      this.arraySomeEnum = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'arraySomeEnum' field has been set.
      * @return True if the 'arraySomeEnum' field has been set, false otherwise.
      */
    public boolean hasArraySomeEnum() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'arraySomeEnum' field.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder clearArraySomeEnum() {
      arraySomeEnum = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the 'arrayEmptyRecord' field.
      * @return The value.
      */
    public java.util.List<dove.EmptyRecord> getArrayEmptyRecord() {
      return arrayEmptyRecord;
    }

    /**
      * Sets the value of the 'arrayEmptyRecord' field.
      * @param value The value of 'arrayEmptyRecord'.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder setArrayEmptyRecord(java.util.List<dove.EmptyRecord> value) {
      validate(fields()[9], value);
      this.arrayEmptyRecord = value;
      fieldSetFlags()[9] = true;
      return this;
    }

    /**
      * Checks whether the 'arrayEmptyRecord' field has been set.
      * @return True if the 'arrayEmptyRecord' field has been set, false otherwise.
      */
    public boolean hasArrayEmptyRecord() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the 'arrayEmptyRecord' field.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder clearArrayEmptyRecord() {
      arrayEmptyRecord = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /**
      * Gets the value of the 'arrayFixed8' field.
      * @return The value.
      */
    public java.util.List<dove.Fixed8> getArrayFixed8() {
      return arrayFixed8;
    }

    /**
      * Sets the value of the 'arrayFixed8' field.
      * @param value The value of 'arrayFixed8'.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder setArrayFixed8(java.util.List<dove.Fixed8> value) {
      validate(fields()[10], value);
      this.arrayFixed8 = value;
      fieldSetFlags()[10] = true;
      return this;
    }

    /**
      * Checks whether the 'arrayFixed8' field has been set.
      * @return True if the 'arrayFixed8' field has been set, false otherwise.
      */
    public boolean hasArrayFixed8() {
      return fieldSetFlags()[10];
    }


    /**
      * Clears the value of the 'arrayFixed8' field.
      * @return This builder.
      */
    public dove.ArrayRecord.Builder clearArrayFixed8() {
      arrayFixed8 = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ArrayRecord build() {
      try {
        ArrayRecord record = new ArrayRecord();
        record.arrayNull = fieldSetFlags()[0] ? this.arrayNull : (java.util.List<java.lang.Void>) defaultValue(fields()[0]);
        record.arrayBoolean = fieldSetFlags()[1] ? this.arrayBoolean : (java.util.List<java.lang.Boolean>) defaultValue(fields()[1]);
        record.arrayInt = fieldSetFlags()[2] ? this.arrayInt : (java.util.List<java.lang.Integer>) defaultValue(fields()[2]);
        record.arrayLong = fieldSetFlags()[3] ? this.arrayLong : (java.util.List<java.lang.Long>) defaultValue(fields()[3]);
        record.arrayFloat = fieldSetFlags()[4] ? this.arrayFloat : (java.util.List<java.lang.Float>) defaultValue(fields()[4]);
        record.arrayDouble = fieldSetFlags()[5] ? this.arrayDouble : (java.util.List<java.lang.Double>) defaultValue(fields()[5]);
        record.arrayBytes = fieldSetFlags()[6] ? this.arrayBytes : (java.util.List<java.nio.ByteBuffer>) defaultValue(fields()[6]);
        record.arrayString = fieldSetFlags()[7] ? this.arrayString : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[7]);
        record.arraySomeEnum = fieldSetFlags()[8] ? this.arraySomeEnum : (java.util.List<dove.SomeEnum>) defaultValue(fields()[8]);
        record.arrayEmptyRecord = fieldSetFlags()[9] ? this.arrayEmptyRecord : (java.util.List<dove.EmptyRecord>) defaultValue(fields()[9]);
        record.arrayFixed8 = fieldSetFlags()[10] ? this.arrayFixed8 : (java.util.List<dove.Fixed8>) defaultValue(fields()[10]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<ArrayRecord>
    WRITER$ = (org.apache.avro.io.DatumWriter<ArrayRecord>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<ArrayRecord>
    READER$ = (org.apache.avro.io.DatumReader<ArrayRecord>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}