# Sales & Marketing Backend — Guide de démarrage

Backend Spring Boot pour le module Sales & Marketing (BIT x Infineon Excellence Program 2026).

## Ce que contient ce projet

**Les 22 tables de ta base ont chacune leur module complet** (Entity →
Repository → Service → Controller), organisés par domaine :

| Package | Tables couvertes |
|---|---|
| `customer` | customers, customer_contacts, certifications, customer_certifications |
| `channel` | sales_channels, sales_channel_targets |
| `product` | products, product_batches |
| `order` | orders, order_items |
| `pricing` | pricing_history, competitor_prices, pricing_recommendations |
| `campaign` | campaigns, campaign_metrics |
| `delivery` | shipments, shipment_events |
| `forecast` | demand_forecasts, forecast_model_runs |
| `alert` | alerts |
| `report` | demand_reports, report_files |

Le module **`customer/Customer.java`** est le plus commenté — lis-le en
premier pour comprendre le principe, les autres suivent exactement le même
schéma avec des commentaires plus légers.

**Cas particulier** : `customer_certifications` est une table de liaison
(many-to-many) sans clé simple — elle utilise une "clé composée"
(`CustomerCertificationId`) et des endpoints différents (lier/délier plutôt
que CRUD classique). Regarde `CustomerCertificationController.java` pour
comprendre ce cas particulier.

## Simplification volontaire (à faire évoluer plus tard)

Les clés étrangères (ex: `customerId` dans `Order`) sont stockées comme de
simples nombres (`Integer`), pas comme de vraies relations JPA
(`@ManyToOne`). C'est plus simple à comprendre pour commencer, et
totalement fonctionnel — on pourra faire évoluer vers de vraies relations
une fois à l'aise avec les bases.

## Prérequis à installer sur ton ordinateur

1. **Java 21** (JDK) — vérifie avec `java -version`
2. **Maven** — vérifie avec `mvn -version`
3. Un IDE — **IntelliJ IDEA** (Community Edition, gratuit) est le plus simple pour Spring Boot
4. Ta base **PostgreSQL** déjà installée et lancée, avec les 22 tables déjà créées

## Étape 1 — Configurer la connexion à ta base

Ouvre `src/main/resources/application.properties` et remplace :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sales_marketing_db
spring.datasource.username=postgres
spring.datasource.password=CHANGE_ME
```

- `sales_marketing_db` → le nom exact de ta base PostgreSQL
- `postgres` → ton nom d'utilisateur PostgreSQL
- `CHANGE_ME` → ton vrai mot de passe

## Étape 2 — Ouvrir le projet dans ton IDE

Dans IntelliJ : `File > Open` → sélectionne le dossier `sales-marketing-backend`.
Il va automatiquement détecter le `pom.xml` et télécharger toutes les
dépendances Spring Boot (ça prend 1-2 minutes la première fois, il te faut
une connexion internet).

## Étape 3 — Lancer le backend

Dans IntelliJ : ouvre `SalesMarketingBackendApplication.java` → clique sur le
triangle ▶️ vert à côté de `public class SalesMarketingBackendApplication`.

Ou en ligne de commande, depuis le dossier du projet :
```bash
mvn spring-boot:run
```

Si tout fonctionne, tu verras dans le terminal quelque chose comme :
```
Tomcat started on port 8080
Started SalesMarketingBackendApplication
```

## Étape 4 — Tester que ça marche

Backend lancé ? Ouvre ton navigateur (ou Postman) sur :
```
http://localhost:8080/api/customers
```

Tu devrais voir le client "BioFrucht GmbH" (celui qu'on avait inséré comme
donnée de test) au format JSON. Si tu vois ça, ton backend fonctionne et
parle bien à ta base PostgreSQL. 🎉

## Endpoints disponibles

Chaque table a son propre groupe d'endpoints, sur le même modèle que Customers :

| Table | Route de base |
|---|---|
| customers | `/api/customers` |
| customer_contacts | `/api/customer-contacts` |
| certifications | `/api/certifications` |
| customer_certifications | `/api/customers/{id}/certifications` |
| sales_channels | `/api/sales-channels` |
| sales_channel_targets | `/api/sales-channel-targets` |
| products | `/api/products` |
| product_batches | `/api/product-batches` |
| orders | `/api/orders` |
| order_items | `/api/order-items` |
| pricing_history | `/api/pricing-history` |
| competitor_prices | `/api/competitor-prices` |
| pricing_recommendations | `/api/pricing-recommendations` |
| campaigns | `/api/campaigns` |
| campaign_metrics | `/api/campaign-metrics` |
| shipments | `/api/shipments` |
| shipment_events | `/api/shipment-events` |
| demand_forecasts | `/api/demand-forecasts` |
| forecast_model_runs | `/api/forecast-model-runs` |
| alerts | `/api/alerts` |
| demand_reports | `/api/demand-reports` |
| report_files | `/api/report-files` |

Chaque route (sauf certifications) suit le même schéma que Customers :
`GET` (liste), `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}`.

## Prochaine étape

Une fois que tout compile et que tu peux tester quelques endpoints, on passe
aux **tests** (unitaires avec JUnit/Mockito pour les Services, et
d'intégration pour les Controllers).

## Tests unitaires (déjà inclus)

Chaque table a maintenant son fichier de test dans `src/test/java/...`,
avec le même découpage que le code principal (customer, order, pricing...).

**Lis `customer/CustomerServiceTest.java` en premier** — il est très
commenté et explique le principe. Les 20 autres suivent exactement le
même schéma (6 tests chacun : getAll, getById trouvé, getById non trouvé,
create, update, delete), avec des commentaires plus légers.

### Comment lancer les tests

Dans IntelliJ/VS Code/NetBeans : clic droit sur le dossier `src/test/java`
→ "Run Tests" (le nom exact du menu varie selon l'IDE).

En ligne de commande, depuis le dossier du projet :
```bash
mvn test
```

Tu devrais voir un résumé du type :
```
Tests run: 126, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### Ce que ces tests vérifient (et ce qu'ils ne vérifient PAS)

✅ Que la logique de chaque Service fonctionne correctement, **indépendamment
de la base de données** (le repository est "simulé"/mocké, pas réel)
✅ Que `update()` copie bien les bons champs
✅ Que `getById()` lève une erreur claire quand l'élément n'existe pas

❌ Ils ne vérifient PAS que ta vraie base PostgreSQL répond bien, que les
contraintes SQL (CHECK, FK) sont respectées, ou que l'API complète
fonctionne de bout en bout (requête HTTP → réponse JSON) — ça, ce sont des
**tests d'intégration**, une étape suivante possible si tu veux aller plus loin.

## Validation des données et gestion des erreurs (nouveau)

Deux ajouts pour rendre le backend plus solide avant que React ne l'appelle :

**1. Validation automatique** — les champs importants (noms obligatoires,
quantités/prix qui ne peuvent pas être négatifs, dates obligatoires...) sont
maintenant vérifiés automatiquement sur `POST` et `PUT`. Si tu essaies de
créer une commande sans `customerId` par exemple, l'API refuse avec un
message clair au lieu de planter.

**2. Gestion centralisée des erreurs** — un seul fichier
(`common/GlobalExceptionHandler.java`) intercepte toutes les erreurs de
toute l'application et les transforme en JSON propre, du type :
```json
{
  "timestamp": "2026-08-31T17:10:00",
  "status": 404,
  "error": "Not Found",
  "message": "Order not found with id: 999"
}
```
au lieu d'un stack trace Java brut — beaucoup plus facile à exploiter côté
React la semaine prochaine.

### Pour tester que ça marche

Essaie de créer une commande incomplète (`POST /api/orders` avec un corps
vide `{}`) — tu devrais recevoir une erreur 400 avec le détail des champs
manquants, au lieu d'un plantage.

Essaie aussi `GET /api/orders/99999` (un id qui n'existe pas) — tu devrais
recevoir une erreur 404 propre.

## Erreurs courantes

- **"Connection refused"** → ta base PostgreSQL n'est pas démarrée, ou le
  port/nom de base est incorrect dans `application.properties`
- **"password authentication failed"** → mauvais mot de passe dans
  `application.properties`
- **"relation customers does not exist"** → tu n'as pas encore exécuté le
  script `.sql` sur cette base précise
