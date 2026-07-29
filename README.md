# Fitness Tracker API

Spring Boot REST backend for recording meals, workouts, and daily calories.

## Run locally

Requires Java 21 and Maven.

```powershell
mvn spring-boot:run
```

The API is at `http://localhost:8080`. It uses an in-memory H2 database locally.

## API

| Action | Method and path |
|---|---|
| Add/list meals | `POST` / `GET` `/api/meals` |
| Add/list workouts | `POST` / `GET` `/api/workouts` |
| Update/delete record | `PUT` / `DELETE` `/api/meals/{id}` or `/api/workouts/{id}` |
| Daily totals | `GET /api/summary?date=2026-07-29` |
| Health check | `GET /actuator/health` |

Create a meal:

```powershell
Invoke-RestMethod http://localhost:8080/api/meals -Method Post -ContentType 'application/json' -Body '{"name":"Chicken rice bowl","mealType":"LUNCH","calories":650,"proteinGrams":42,"carbohydrateGrams":70,"fatGrams":18,"consumedAt":"2026-07-29T13:00:00"}'
```

Create a workout:

```powershell
Invoke-RestMethod http://localhost:8080/api/workouts -Method Post -ContentType 'application/json' -Body '{"name":"Evening run","category":"CARDIO","durationMinutes":35,"caloriesBurned":380,"performedAt":"2026-07-29T18:30:00"}'
```

## Docker and Kubernetes

Build and tag the image:

```powershell
docker build -t YOUR_REGISTRY/fitness-tracker:1.0.0 .
docker push YOUR_REGISTRY/fitness-tracker:1.0.0
```

In `k8s/fitness-tracker.yaml`, replace `YOUR_REGISTRY/fitness-tracker:1.0.0` and change the PostgreSQL password. Then deploy:

```powershell
kubectl apply -f k8s/fitness-tracker.yaml
kubectl -n fitness rollout status deployment/fitness-tracker
kubectl -n fitness port-forward service/fitness-tracker 8080:80
```

With port-forward running, use the same `http://localhost:8080` endpoints. For a public endpoint, create an Ingress or change the API service type to `LoadBalancer`.
