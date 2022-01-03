# practice-01

Practice project with spring boot, postgresql etc

URL[`POST`]: `http://localhost:8888/customer/get-all`

```graphql
{
	customers {
		name,
		age
		presentAddress {
			roadNumber
			city
		}
		contacts {
			phoneNumber
		}
	}
}
```
