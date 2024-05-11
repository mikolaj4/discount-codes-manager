# discount-codes-manager

## Project overview
Java Spring Boot api for managing discount codes.



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
	"expDate": "expiration_date",
	"discountAmount": `discount_amount`,
	"currency": "code_currency",
	"maxUsages": `max_usages`
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
	"currentUsages": `current_usages`
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
	"currentUsages": `current_usages`
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


### GET "/api/report"
- Returns sales report by currency.




