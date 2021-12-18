/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.bigCorp;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public interface Messages {
  public static final org.apache.avro.Protocol PROTOCOL = org.apache.avro.Protocol.parse("{\"protocol\":\"Messages\",\"namespace\":\"com.bigCorp\",\"types\":[{\"type\":\"fixed\",\"name\":\"UID\",\"doc\":\"Unique identifier. Will be cast to a UUID.\",\"size\":16},{\"type\":\"enum\",\"name\":\"Category\",\"doc\":\"Category of a product. A product can only have zero of one category.\",\"symbols\":[\"FASHION\",\"VEGETABLES\",\"BEAUTY\",\"SPORTSWEAR\"]},{\"type\":\"record\",\"name\":\"Product\",\"doc\":\"Description of a product. Only fields `id` and `retailPrice` are required.\",\"fields\":[{\"name\":\"id\",\"type\":\"UID\",\"doc\":\"Identifier of this product. Will be cast to a UUID.\"},{\"name\":\"retailPrice\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":8,\"scale\":1},\"doc\":\"What a customer must pay to get one item. Gross profit for each sold product.\"},{\"name\":\"warehouseCount\",\"type\":[\"null\",\"int\"],\"doc\":\"If known, count of available items of this product from our big warehouse.\",\"default\":null},{\"name\":\"familyVariants\",\"type\":{\"type\":\"array\",\"items\":\"UID\"},\"doc\":\"Ids of all the variants of this product family, including this very product id.\",\"default\":[]},{\"name\":\"category\",\"type\":[\"null\",\"Category\"],\"doc\":\"Category of this product, if relevant\",\"default\":null}]}],\"messages\":{}}");

  @SuppressWarnings("all")
  public interface Callback extends Messages {
    public static final org.apache.avro.Protocol PROTOCOL = com.bigCorp.Messages.PROTOCOL;
  }
}