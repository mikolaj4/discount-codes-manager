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
	"price": {product_price},
	"currency": "price_currency"
}
```


### GET "/api/products"
- Returns a list off all products in JSON format:
 ```json
{
	"id": {product_id},
	"name": "product_name",
	"description": "product_description",
	"price": {product_price},
	"currency": "price_currency"
}
```

### PUT "/api/products"
- Updates product data based on product unique id.
- In request body provide JSON of the following format:
 ```json
{
	"id": {product_id},
	"name": "product_name",
	"description": "product_description",
	"price": {product_price},
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
	"discountAmmount": {discount_ammount},
	"currency": {code_currency},
	"maxUsages": {max_usages}
}
```


### GET "/api/codes"
- Returns a list off all codes in JSON format:
 ```json
{
	"id": {code_id},
	"name": "code_name",
	"discountAmmount": {discount_ammount},
	"currency": "code_currency",
	"maxUsages": {max_usages},
	"currentUsages": {current_usages}
}
```


### GET "/api/codes/{code_name}"
- Returns code details as JSON based on code name.
 ```json
{
	"id": {code_id},
	"name": "code_name",
	"discountAmmount": {discount_ammount},
	"currency": "code_currency",
	"maxUsages": {max_usages},
	"currentUsages": {current_usages}
}
```


### POST "/api/calculatediscount"
- Returns calculated discount based on given promo code name and product name.
- In request body provide JSON of the following format:
 ```json
{
	"codeName": "code_name",
	"productName": "product_name"
}
```


### POST "/api/purchase"
- Simulates a purchase.
- In request body provide JSON of the following format:
 ```json
{
	"codeName": "code_name",
	"productName": "product_name"
}
```


### GET "/api/report"
- Returns sales report by currency.




