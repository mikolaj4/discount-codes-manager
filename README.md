# discount-codes-manager

## Project overview
- Java Spring Boot api for managing discount codes.
- I believe, that all edge cases are well-handled.
- Java version: 21

## Access the database
- Project uses H2 in memory database
- To access it, go to **/h2-console**
- JDBC URL: **jdbc:h2:mem:shop**
- User Name: **sa**
- Password: **leave empty**

## REST API endpoints

### POST "/api/products"
- Add new product
- In request body provide JSON of the following format:
 ```json
{
	"name": "product_name",
	"description": "product_description",
	"price": `product_price`,
	"currency": "price_currency"
}
```


### GET "/api/products"
- Returns a list off all products in JSON format:
 ```json
{
	"id": `product_id`,
	"name": "product_name",
	"description": "product_description",
	"price": `product_price`,
	"currency": "price_currency"
}
```

### PUT "/api/products"
- Updates product data based on product unique id.
- In request body provide JSON of the following format:
 ```json
{
	"id": `product_id`,
	"name": "product_name",
	"description": "product_description",
	"price": `product_price`,
	"currency": "price_currency"
}
```


### POST "/api/codes"
- Create new promo code
- In request body provide JSON of the following format:
 ```json
{
	"name": "code_name",
	"discountAmount": `discount_amount`,
	"currency": "code_currency",
	"maxUsages": `max_usages`,
    "expDate": "dd-MM-yyyy"
}
```


### GET "/api/codes"
- Returns a list off all codes in JSON format:
 ```json
{
	"name": "code_name",
	"discountAmount": `discount_amount`,
	"currency": "code_currency",
	"maxUsages": `max_usages`,
	"currentUsages": `current_usages`,
    "expDate": "dd-MM-yyyy"
}
```


### GET "/api/codes/{code_name}"
- Returns code details as JSON based on code name.
 ```json
{
	"name": "code_name",
	"discountAmount": `discount_amount`,
	"currency": "code_currency",
	"maxUsages": `max_usages`,
	"currentUsages": `current_usages`,
    "expDate": "dd-MM-yyyy"
}
```


### POST "/api/discount-price"
- Returns calculated discount based on given promo code name and product name.
- In request body provide JSON of the following format:
 ```json
{
	"codeName": "code_name",
	"productId": `product_id`
}
```


### POST "/api/purchase"
- Simulates a purchase.
- In request body provide JSON of the following format:
 ```json
{
	"codeName": "code_name",
	"productId": `product_id`
}
```


### GET "/api/sales-report"
- Returns html sales report by currency.



## Example correct queries

### Add product - POST /api/products
```json
{
    "name": "Milk 1 liter",
    "description": "0.5% fat",
    "price": 4.99, 
    "currency": "PLN"
}
```
```json
{
    "name": "Dishwasher soap",
    "description": null,
    "price": 28.0, 
    "currency": "PLN"
}
```
```json
{
    "name": "Impact drill",
    "description": "Brand: Parkside",
    "price": 70.0, 
    "currency": "EUR"
}
```
```json
{
    "name": "Casio watch",
    "description": "model: FW91Y gold edition",
    "price": 210.99, 
    "currency": "EUR"
}
```


### Add promo code - POST /api/codes
```json
{
    "name": "code5pln",
    "discountAmount": 5.0,
    "currency": "PLN",
    "maxUsages": 100,
    "expDate": "01-08-2024"
}
```
```json
{
    "name": "code5eur",
    "discountAmount": 5.0,
    "currency": "EUR",
    "maxUsages": 100,
    "expDate": "01-08-2024"
}
```

### Calculate discounted price - POST /api/discount-price
- Don't know product id? Send a GET request at /api/products to get a list of all products with their ids.
```json
{
    "codeName": "code5eur",
    "productId": 4
}
```
```json
{
    "codeName": "code5pln",
    "productId": 2
}
```

### Simulate purchase - POST /api/purchase
```json
{
    "codeName": "code5eur",
    "productId": 4
}
```
```json
{
    "codeName": "code5pln",
    "productId": 2
}
```

- Purchase can be done without applying promo code. 
```json
{
    "productId": 2
}
```






