# OrderSystem — Java Practice 4

Three-module Maven project, Java 17+.

- `order.processing` — `Product`, `Electronics`, `Clothing`, `Order<T>`, `OrderProcessor<T>`.
- `order.storage` — synchronized in-memory order storage.
- `order.threads` — JavaFaker, lambdas, method references, and threads.

## Build and run

From the `OrderSystem` directory:

```bash
mvn clean install
mvn -pl order-threads exec:exec
```

Expected: 4 submitted orders, 3 saved orders, one validation error.
Output order may differ between runs because threads run concurrently.
