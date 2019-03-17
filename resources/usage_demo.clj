(ns dove.usage-demo
  (:require [dove.core :as dove]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.test.check.generators :as test.g]
            [clj-uuid :as uuid])
  ;; These schemas are for the purpose of the demo.
  (:import
    (com.bigCorp UID ;; Unique identifier. Will be cast to a
                     ;; java.util.UUID.
                 Category ;; Category of a product. A product can only
                          ;; have zero of one category.
                 Product ;; Description of a product. Only fields `id`
                         ;; and `retailPrice` are required.
                 )
    (java.nio ByteBuffer)
    (java.util UUID)))

(dove/to-spec! (Category/getClassSchema) {})
=> :com.bigCorp/Category

(gen/generate (s/gen :com.bigCorp/Category))
=> "VEGETABLES"

(gen/generate (s/gen :com.bigCorp/Category))
=> "BEAUTY"

@dove/ignored-specs
=> #{:com.bigCorp/Category}

(dove/to-spec! (UID/getClassSchema) {})
=> :com.bigCorp/UID

(gen/generate (s/gen :com.bigCorp/UID))
=> #object["[B" 0x5b37ba4c "[B@5b37ba4c"]

(defn bytes->uuid-v4
  ^UUID [^bytes b]
  (let [buffer ^ByteBuffer (ByteBuffer/wrap b)]
    (uuid/v4 (.getLong buffer) (.getLong buffer))))

(s/def :com.bigCorp/UID
  (s/with-gen
    uuid/uuid?
    #(test.g/fmap bytes->uuid-v4 (s/gen (dove/->avro-fixed? 16)))))

(gen/generate (s/gen :com.bigCorp/UID))
=> #uuid"d28df78a-8aa2-4b8c-b261-b0286581c865"

@dove/ignored-specs
=> #{:com.bigCorp/Category :com.bigCorp/UID}

(dove/to-spec! (Product/getClassSchema) {})
=> :com.bigCorp/Product

(gen/generate (s/gen :com.bigCorp/Product))
=> {:category "BEAUTY",
    :warehouseCount 3
    :familyVariants [#uuid"de3ad2cd-551e-4b5d-a144-6f018ea38450"],
    :retailPrice 12.4M,
    :id #uuid"44857f13-f64c-4c16-9c33-bc83b6602213"}

(gen/generate (s/gen :com.bigCorp/Product))
=> {:familyVariants [],
    :retailPrice 3476.3M,
    :id #uuid"9ab4e463-14fc-4226-ad26-ccdf22e77263"}

(reset! dove/ignored-specs #{:com.bigCorp/UID})
;; => #{:com.bigCorp/UID}

(dove/to-spec! (Product/getClassSchema)
               {:dry-run? false
                :ns-keys? true
                :enum-obj? true
                :required-union-nil-value? true
                :dove.spec/keyword dove/dove-spec-keyword})

(gen/generate (s/gen :com.bigCorp/Product))
=> {:com.bigCorp.Product/category #object[com.bigCorp.Category 0x24246526 "VEGETABLES"],
    :com.bigCorp.Product/familyVariants [#uuid"182f170e-86d8-4c82-b067-999593756478"
                                         #uuid"b7c292ee-a74e-48c4-8d5e-489d636d56d4"
                                         #uuid"ea8284a0-c6e4-4690-8783-7dcd50d5e9bd"],
    :com.bigCorp.Product/warehouseCount 73,
    :com.bigCorp.Product/retailPrice 0.2M,
    :com.bigCorp.Product/id #uuid"f3ef308b-2bce-4664-95b9-eb7a1aa78fd6",
    :dove.spec/name :com.bigCorp/Product}
