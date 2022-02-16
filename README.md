# Spring GraphQL

Just a demo project with Spring GraphQL.


## Relevant Links

* [Video Tutorial](https://youtu.be/kVSYVhmvNCI)

## Sample Queries

Search all customers

```graphql
{
  customers{
    id,
    name
  }
}
```

Search by customer name

```graphql
{
  customersByName(name:"Alice"){
    id,
    name
  }
}
```

Add new customer

```graphql
mutation {
  addCustomer(name: "Ivy"){
    id, name
  }
}
```

Subscribe to customer event.

```graphql
subscription {
  customerEvents(customerId: 1){
    event,
    customer {
      id,
      name
    }
  }
}
```