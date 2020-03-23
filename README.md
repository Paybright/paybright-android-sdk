PayBright Android SDK
==============

PayBright Android SDK is a library written in java that allows you to provide [PayBright](https://paybright.com/) payment option in your own app.

Installation
============

<strong> Download via Gradle </strong>

```groovy
implementation 'com.paybright.sdk:PayBright:0.1.3'
```

<strong> Download via Maven </strong>
```xml
<dependency>
<groupId>com.paybright.sdk</groupId>
<artifactId>PayBright</artifactId>
<version>0.1.3</version>
<type>pom</type>
</dependency>
```

Usage Overview
==============

PayBright integration consists of two steps: Configure and Checkout


## Configure

Set the API Key and token obtained from PayBright in PBConfig once (preferably in the Actvity's `onCreate` method) as follows

```
PBConfig.getInstance().initialize(PBConfig.PBEnvironment.Sandbox,
"API_KEY",
"API_TOKEN");
```

## Checkout

Checkout creation is the process in which a customer uses PayBright to pay for a purchase in your store. This process is governed by `PBInstance` object (which is set up in `PBConfig` shared object), and requires four parameters/objects:

- PBCustomer: details of the customer
- PBCustomerBilling: customer's billing address details
- PBCustomerShipping: customer's shipping address details
- PBProduct: item details


### Customer

```
PBCustomer customerObj = new PBCustomer("cs@paybright.com",
"James",
"Testhetfield",
null);
```

### Customer Billing

```
PBCustomerBilling customerBillingObj = new PBCustomerBilling("270 Rue Olier",
null,
"Chicoutimi",
null,
"CA", 
"+1-613-987-6543",
"QC",
"G7G 4J3");
```

### Customer Shipping

```
PBCustomerShipping customerShippingObj = new PBCustomerShipping("270 Rue Olier",
null,
"Chicoutimi",
"Shopify",
"CA",
"James", 
"Testhetfield",
"+1-613-987-6543",
"QC",
"G7G 4J3");

```

### Product

```
PBProduct productObj = new PBProduct(2625.0,
"CAD",
"PaymentGatewayTesting - #4682855809085",
"#4682855809085",
null,
"sdk",
"4682855809085",
"CA",
"PaymentGatewayTesting",
"https://checkout.shopify.com/services/ping/notify_integration/paybright/19629019",
"https://paymentgatewaytesting.myshopify.com/19629019/checkouts/84044f7a52ff18a84dd1f2b5cd46b387?key=0c4978718a87a00e5ac1456b577b5695",
"https://paymentgatewaytesting.myshopify.com/19629019/checkouts/84044f7a52ff18a84dd1f2b5cd46b387/offsite_gateway_callback");
```

### Instance

```
PBConfig.getInstance().instanceObj = new PBInstance(customerObj,
customerBillingObj,
customerShippingObj,
productObj);
```
### Using Activity

Add PBWebView into your AndroidManifest.xml
```xml
<activity
android:name="com.paybright.sdk.PBWebView"
android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"
android:screenOrientation="portrait" />
```

Once the `PBInstance` has been constructed, set it in `PBConfig` shared object and you may use `PBViewController`. This initiates the flow which guides the user through the PayBright checkout process. An example of how this is implemented is provided as follows

```
new PBViewController(this, this);
```

The flow ends once the user has successfully confirmed the checkout, canceled the checkout, or encountered an error in the process. In each of these cases, PayBright will send a message to the `PBWebViewDelegate` along with additional information about the result.


Example
=======

A demo app that integrates PayBright is included in the repo.


Requirements
=======

minSdkVersion 16 and AndroidX is required to use PayBright
