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
public class NestedSchema extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5212674777667854331L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NestedSchema\",\"namespace\":\"dove\",\"fields\":[{\"name\":\"nestedIp\",\"type\":[\"null\",{\"type\":\"fixed\",\"name\":\"IPv4\",\"size\":4},{\"type\":\"fixed\",\"name\":\"IPv6\",\"size\":16}],\"default\":null},{\"name\":\"nestedRecursiveArrayUnion\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"NestedSchema\"}],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<NestedSchema> ENCODER =
      new BinaryMessageEncoder<NestedSchema>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<NestedSchema> DECODER =
      new BinaryMessageDecoder<NestedSchema>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<NestedSchema> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<NestedSchema> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<NestedSchema>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this NestedSchema to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a NestedSchema from a ByteBuffer. */
  public static NestedSchema fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.Object nestedIp;
  @Deprecated public java.util.List<dove.NestedSchema> nestedRecursiveArrayUnion;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public NestedSchema() {}

  /**
   * All-args constructor.
   * @param nestedIp The new value for nestedIp
   * @param nestedRecursiveArrayUnion The new value for nestedRecursiveArrayUnion
   */
  public NestedSchema(java.lang.Object nestedIp, java.util.List<dove.NestedSchema> nestedRecursiveArrayUnion) {
    this.nestedIp = nestedIp;
    this.nestedRecursiveArrayUnion = nestedRecursiveArrayUnion;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return nestedIp;
    case 1: return nestedRecursiveArrayUnion;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: nestedIp = (java.lang.Object)value$; break;
    case 1: nestedRecursiveArrayUnion = (java.util.List<dove.NestedSchema>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'nestedIp' field.
   * @return The value of the 'nestedIp' field.
   */
  public java.lang.Object getNestedIp() {
    return nestedIp;
  }

  /**
   * Sets the value of the 'nestedIp' field.
   * @param value the value to set.
   */
  public void setNestedIp(java.lang.Object value) {
    this.nestedIp = value;
  }

  /**
   * Gets the value of the 'nestedRecursiveArrayUnion' field.
   * @return The value of the 'nestedRecursiveArrayUnion' field.
   */
  public java.util.List<dove.NestedSchema> getNestedRecursiveArrayUnion() {
    return nestedRecursiveArrayUnion;
  }

  /**
   * Sets the value of the 'nestedRecursiveArrayUnion' field.
   * @param value the value to set.
   */
  public void setNestedRecursiveArrayUnion(java.util.List<dove.NestedSchema> value) {
    this.nestedRecursiveArrayUnion = value;
  }

  /**
   * Creates a new NestedSchema RecordBuilder.
   * @return A new NestedSchema RecordBuilder
   */
  public static dove.NestedSchema.Builder newBuilder() {
    return new dove.NestedSchema.Builder();
  }

  /**
   * Creates a new NestedSchema RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new NestedSchema RecordBuilder
   */
  public static dove.NestedSchema.Builder newBuilder(dove.NestedSchema.Builder other) {
    return new dove.NestedSchema.Builder(other);
  }

  /**
   * Creates a new NestedSchema RecordBuilder by copying an existing NestedSchema instance.
   * @param other The existing instance to copy.
   * @return A new NestedSchema RecordBuilder
   */
  public static dove.NestedSchema.Builder newBuilder(dove.NestedSchema other) {
    return new dove.NestedSchema.Builder(other);
  }

  /**
   * RecordBuilder for NestedSchema instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<NestedSchema>
    implements org.apache.avro.data.RecordBuilder<NestedSchema> {

    private java.lang.Object nestedIp;
    private java.util.List<dove.NestedSchema> nestedRecursiveArrayUnion;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(dove.NestedSchema.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.nestedIp)) {
        this.nestedIp = data().deepCopy(fields()[0].schema(), other.nestedIp);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.nestedRecursiveArrayUnion)) {
        this.nestedRecursiveArrayUnion = data().deepCopy(fields()[1].schema(), other.nestedRecursiveArrayUnion);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing NestedSchema instance
     * @param other The existing instance to copy.
     */
    private Builder(dove.NestedSchema other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.nestedIp)) {
        this.nestedIp = data().deepCopy(fields()[0].schema(), other.nestedIp);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.nestedRecursiveArrayUnion)) {
        this.nestedRecursiveArrayUnion = data().deepCopy(fields()[1].schema(), other.nestedRecursiveArrayUnion);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'nestedIp' field.
      * @return The value.
      */
    public java.lang.Object getNestedIp() {
      return nestedIp;
    }

    /**
      * Sets the value of the 'nestedIp' field.
      * @param value The value of 'nestedIp'.
      * @return This builder.
      */
    public dove.NestedSchema.Builder setNestedIp(java.lang.Object value) {
      validate(fields()[0], value);
      this.nestedIp = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'nestedIp' field has been set.
      * @return True if the 'nestedIp' field has been set, false otherwise.
      */
    public boolean hasNestedIp() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'nestedIp' field.
      * @return This builder.
      */
    public dove.NestedSchema.Builder clearNestedIp() {
      nestedIp = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'nestedRecursiveArrayUnion' field.
      * @return The value.
      */
    public java.util.List<dove.NestedSchema> getNestedRecursiveArrayUnion() {
      return nestedRecursiveArrayUnion;
    }

    /**
      * Sets the value of the 'nestedRecursiveArrayUnion' field.
      * @param value The value of 'nestedRecursiveArrayUnion'.
      * @return This builder.
      */
    public dove.NestedSchema.Builder setNestedRecursiveArrayUnion(java.util.List<dove.NestedSchema> value) {
      validate(fields()[1], value);
      this.nestedRecursiveArrayUnion = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'nestedRecursiveArrayUnion' field has been set.
      * @return True if the 'nestedRecursiveArrayUnion' field has been set, false otherwise.
      */
    public boolean hasNestedRecursiveArrayUnion() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'nestedRecursiveArrayUnion' field.
      * @return This builder.
      */
    public dove.NestedSchema.Builder clearNestedRecursiveArrayUnion() {
      nestedRecursiveArrayUnion = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NestedSchema build() {
      try {
        NestedSchema record = new NestedSchema();
        record.nestedIp = fieldSetFlags()[0] ? this.nestedIp : (java.lang.Object) defaultValue(fields()[0]);
        record.nestedRecursiveArrayUnion = fieldSetFlags()[1] ? this.nestedRecursiveArrayUnion : (java.util.List<dove.NestedSchema>) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<NestedSchema>
    WRITER$ = (org.apache.avro.io.DatumWriter<NestedSchema>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<NestedSchema>
    READER$ = (org.apache.avro.io.DatumReader<NestedSchema>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}