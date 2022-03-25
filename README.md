# 🕊 dove

[![Build status](https://img.shields.io/github/workflow/status/piotr-yuxuan/dove/Walter%20CD)](https://github.com/piotr-yuxuan/dove/actions/workflows/walter-cd.yml)
[![Clojars badge](https://img.shields.io/clojars/v/dove.svg)](https://clojars.org/piotr-yuxuan/dove)
[![Clojars downloads](https://img.shields.io/clojars/dt/dove)](https://clojars.org/dove)
[![cljdoc badge](https://cljdoc.org/badge/dove)](https://cljdoc.org/d/piotr-yuxuan/dove/CURRENT)
[![GitHub license](https://img.shields.io/github/license/piotr-yuxuan/dove)](https://github.com/piotr-yuxuan/dove/blob/main/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/piotr-yuxuan/dove)](https://github.com/piotr-yuxuan/dove/issues)

# What does it do?

Infer specs from any Avro named type (record, fixed, or enum).

# What can I use it for?

Generative testing and pre-serialisation validation.

![avro aircraft](resources/avro.jpg)

Avro was a British aircraft manufacturer. Nowadays it is a data
serialization framework. A dove is smaller than an Avro aircraft, but
it's softer and you can hold it in your hand, which makes it much more
convenient when dealing with data manually.

# How to use it

Let's take a couple of simple schemas and infer spec from them.

``` avdl
// Namespace as Java-like package name
@namespace("com.bigCorp")

protocol Messages {

  /** Unique identifier. Will be cast to a UUID. */
  fixed UID(16);

  /** Category of a product. A product can only have zero of one category. */
  enum Category {
    FASHION, VEGETABLES, BEAUTY, SPORTSWEAR
  }

  /** Description of a product. Only fields `id` and `retailPrice` are required. */
  record Product {
    /** Identifier of this product. Will be cast to a UUID. */
    UID id;

    /** What a customer must pay to get one item. Gross profit for each sold product. */
    decimal(8,1) retailPrice;

    /** If known, count of available items of this product from our big warehouse. */
    union { null, int } warehouseCount = null;

    /** Ids of all the variants of this product family, including this very product id. */
    array<UID> familyVariants = [];

    /** Category of this product, if relevant */
    union { null, Category } category = null;
  }
}
```

I hope comments make it quite easy to understand.

Demo Avro schemas sources and classes are generated from IDL
files with this Maven command:

``` zsh
mvn clean compile
```

``` clojure
(ns dove.usage-demo
  (:require [dove.core :as dove]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.test.check.generators :as test.g]
            [clj-uuid :as uuid])
  ;; These schemas are for the purpose of the demo.
  (:import (com.bigCorp UID Category Product)
           (java.nio ByteBuffer)
           (java.util UUID)))
```

Turning a schema definition into a spec is straightforward with
`dove/to-spec!`. It takes two arguments:

- The schema which you want to recursively infer specs from.
- Spec generation parameters. Defaults are `dove/convenient-args`,
  which are equivalent to `{}`.

``` clojure
(dove/to-spec! (Category/getClassSchema) {})
=> :com.bigCorp/Category
```

This returns the spec keyword.

``` clojure
(gen/generate (s/gen :com.bigCorp/Category))
=> "VEGETABLES"

(gen/generate (s/gen :com.bigCorp/Category))
=> "BEAUTY"
```

Clojure spec use a global registry. Dove mimics this and will ignore
specs it has already infered.

``` clojure
@dove/ignored-specs
=> #{:com.bigCorp/Category}
```

Let's infer a spec for a named, fixed type:

``` clojure
(dove/to-spec! (UID/getClassSchema) {})
=> :com.bigCorp/UID
```

This spec matches the definition of a fixed type, so it's
valid. However it doesn't give useful sample.

``` clojure
(gen/generate (s/gen :com.bigCorp/UID))
=> #object["[B" 0x5b37ba4c "[B@5b37ba4c"]
```

Let's redefine this spec to something more idiomatic.

``` clojure
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
```

At this point two specs are known to have been infered and won't be
further altered. You can also use that to instruct `dove` to leave
some specs untouched if you are willing to define them yourself.

``` clojure
@dove/ignored-specs
=> #{:com.bigCorp/Category :com.bigCorp/UID}
```

Now let's infer the spec for something bigger.

``` clojure
(dove/to-spec! (Product/getClassSchema) {})
=> :com.bigCorp/Product
```

``` clojure
(gen/generate (s/gen :com.bigCorp/Product))
=> {:category "BEAUTY",
    :warehouseCount 3
    :familyVariants [#uuid"de3ad2cd-551e-4b5d-a144-6f018ea38450"],
    :retailPrice 12.4M,
    :id #uuid"44857f13-f64c-4c16-9c33-bc83b6602213"}
```

Some fields are not mandatory because of `union { null, … }`.
Likewise, they can be missing from a sample:

``` clojure
(gen/generate (s/gen :com.bigCorp/Product))
=> {:familyVariants [],
    :retailPrice 3476.3M,
    :id #uuid"9ab4e463-14fc-4226-ad26-ccdf22e77263"}
```

# Further parameters

The previous examples focus on the ease of use and keep things
simple. Here are parameters you can tune to adapt `dove` to your
needs:

``` clojure
;; in `dove.core`
(def convenient-args
  "These args are not meant to be your default choice, but they are
  somehow convenient to use."
  {:dry-run? false
   :ns-keys? false
   :enum-obj? false
   :required-union-nil-value? false
   :dove.spec/keyword dove-spec-keyword})
```

- When `:dry-run?` is `true`, `dove` will not define any spec but
  instead print its name.
- When `:ns-keys?` is `true`, keys of generated sample will be
  namespaced in a Datomic-like way.
- When `:enum-obj?` is `true`, generated sample of `enum` will be
  actual `Enum`f instances and not mere strings.
- When `:required-union-nil-value?` is `true`, all records fields will
  be present in a sample. Fields which type is `union { null, … }`
  could otherwise be missing.
- When `:dove.spec/keyword` is filled, each generated record sample is
  added its name under this key. This can be helpful to unambiguously
  generate Avro record from maps.

``` clojure
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
```

# Troubleshoot

Any behaviour different from [Avro 1.8.2
specification](https://avro.apache.org/docs/1.8.2/spec.html) should be
considered a bug.

This library is currently under development. Feel free to give me any
feedback and I'll be more than happy to help you.

# Related projects

I've got a great deal of inspiration from these projects. They might
suit your needs better than dove. Each of them is pretty impressive!

- [lancaster](https://github.com/deercreeklabs/lancaster) from [Chad
  Harrington](https://github.com/chadharrington)
- [spec-tools](https://github.com/metosin/spec-tools) from [Tommi
  Reiman](https://github.com/ikitommi)
- [spec-provider](https://github.com/stathissideris/spec-provider)
  from [Stathis Sideris](https://github.com/stathissideris)
