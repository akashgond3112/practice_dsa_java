# Hotel Search & Price Display System - Complete Interview Guide

> **Interview Package**: Round 2 (System Design) + Round 3 (DSA Coding)  
> **Total Time**: 2.5-3 hours across both rounds

---

## 📋 Table of Contents

- [Round 2: System Design (60-90 mins)](#round-2-system-design)
  - [Interview Strategy](#interview-strategy)
  - [Clarifying Questions](#clarifying-questions)
  - [High-Level Design (HLD)](#high-level-design-hld)
  - [Low-Level Design (LLD)](#low-level-design-lld)
  - [Scalability & Trade-offs](#scalability--trade-offs)
- [Round 3: DSA Coding (90 mins)](#round-3-dsa-coding)
  - [Problem Statement](#problem-statement-round-3)
  - [Solution Approach](#solution-approach)
  - [Code Implementation](#code-implementation)
  - [Complexity Analysis](#complexity-analysis)
- [Design Patterns Used](#design-patterns-used)

---

## Round 2: System Design

### 🎯 Interview Strategy

#### Problem Statement
Design a hotel search system that displays prices for specific check-in and check-out dates with:
- Price breakdown (base price, taxes, fees)
- Multi-currency support with locale-specific formatting
- Scalable architecture handling high read loads

#### Time Breakdown (60-90 mins)
```
0-5 mins:   Acknowledge problem & propose structure
5-15 mins:  Clarifying questions
15-35 mins: High-Level Design (HLD)
35-65 mins: Low-Level Design (LLD) - Deep dive
65-85 mins: Scalability & trade-offs
85-90 mins: Extension points & wrap-up
```

---

### 🔍 Clarifying Questions

#### **1. Scale & Traffic**
```
Q: What's the expected scale?
- Number of hotels? (thousands, millions)
- Searches per second (QPS)?
- Read-heavy or write-heavy?

✓ Assumption: Read-heavy (100:1 read-to-write ratio)
✓ Scale: Millions of hotels, ~10,000 searches/minute
```

#### **2. Geographic Scope**
```
Q: Global system or specific region?
- Impacts: Currency, taxes, data center locations

✓ Assumption: Global system with multi-region support
```

#### **3. Pricing & Availability**
```
Q: How often do prices change?
- Daily, hourly, or real-time from partners?

Q: Availability update frequency?
- Real-time or can tolerate eventual consistency?

✓ Assumption: Daily price updates, near real-time availability
✓ Eventual consistency acceptable for search results
```

#### **4. Features & Scope**
```
Q: Search filters beyond location/dates?
- Amenities, star rating, price range?

Q: Is booking included or just search/display?

Q: Currency conversion requirements?
- Real-time or daily exchange rates?

✓ Assumption: Focus on search/display only
✓ Daily exchange rates for simplicity
```

#### **5. Performance Requirements**
```
Q: Latency requirements?
- Target: p95 < 200ms

Q: Consistency requirements?
- Strong vs eventual consistency for prices/availability

✓ Assumption: Eventual consistency OK, prioritize low latency
```

---

### 🏗️ High-Level Design (HLD)

#### System Architecture

```
┌─────────────────┐
│  Client         │
│ (Web/Mobile)    │
└────────┬────────┘
         │
         ▼
┌─────────────────────────────┐
│  API Gateway / LB           │
│  (Auth, Rate Limit, SSL)    │
└────────┬────────────────────┘
         │
         ▼
┌─────────────────────────────┐
│   Search Service            │
│   (Orchestrator)            │
└─┬───┬───┬───┬───────────────┘
  │   │   │   │
  ▼   ▼   ▼   ▼
┌───┐┌───┐┌───┐┌────────┐
│HTL││PRC││CUR││AVL     │
│SVC││SVC││SVC││SVC     │
└─┬─┘└─┬─┘└─┬─┘└─┬──────┘
  │    │    │    │
  ▼    ▼    ▼    ▼
┌────┐┌────┐┌────┐┌────┐
│NoSQL│RDBMS│K-V │Redis│
└────┘└────┘└────┘└────┘

Async Data Updates:
┌──────────────┐
│ Kafka Queue  │◄──Hotel Partners
└──────┬───────┘   Exchange Rate APIs
       │
       ▼
   (Updates Services)
```

#### Core Components

| Component | Responsibility | Technology Choice |
|-----------|---------------|-------------------|
| **API Gateway** | Entry point, auth, rate limiting, routing | NGINX, AWS API Gateway |
| **Search Service** | Orchestrates search queries, aggregates results | Node.js/Java microservice |
| **Hotel Data Service** | Static hotel metadata (name, location, amenities) | MongoDB, Cassandra (NoSQL) |
| **Pricing Service** | Price calculation, tax/fee logic | PostgreSQL, MySQL (RDBMS) |
| **Currency Service** | Exchange rates, conversion, formatting | Redis, PostgreSQL |
| **Availability Service** | Real-time room inventory | Redis, In-memory DB |
| **Cache Layer** | Reduce latency, DB load | Redis Cluster |
| **Message Queue** | Async updates, decoupling | Kafka, RabbitMQ |
| **Search Engine** | Full-text, geo-spatial queries | Elasticsearch |

#### Data Flow - Search Request

```
1. Client → API Gateway
   POST /api/v1/hotels/search
   {
     "location": "New York",
     "checkIn": "2025-11-15",
     "checkOut": "2025-11-18",
     "guests": 2,
     "currency": "EUR"
   }

2. API Gateway → Search Service

3. Search Service (Orchestration):
   a. Check Search Results Cache (Redis)
      └─ Cache Hit? → Return immediately ✓
   
   b. Cache Miss:
      i.   Query Hotel Data Service (location filter)
           └─ Returns: [H001, H002, H003...]
      
      ii.  Query Availability Service (dates, guests)
           └─ Filters: [H001, H003] (available)
      
      iii. Query Pricing Service (per hotel)
           └─ Returns: PriceBreakdown for each
      
      iv.  Query Currency Service (convert to EUR)
           └─ Converts all prices
      
      v.   Aggregate results
      vi.  Store in cache (TTL: 5 mins)

4. Search Service → Client
   {
     "hotels": [
       {
         "id": "H001",
         "name": "Grand Plaza",
         "priceBreakdown": {
           "basePrice": "€382.50",
           "tax": "€57.38",
           "serviceFee": "€19.13",
           "total": "€459.01"
         }
       }
     ]
   }
```

#### Database Schema (Simplified)

**Hotels (NoSQL - MongoDB)**
```javascript
{
  hotel_id: "H001",
  name: "Grand Plaza Hotel",
  location: {
    city: "New York",
    country: "USA",
    geo: { lat: 40.7128, lon: -74.0060 }
  },
  star_rating: 4.5,
  amenities: ["WiFi", "Pool", "Gym"],
  description: "...",
  images: ["url1", "url2"]
}
```

**Pricing (RDBMS - PostgreSQL)**
```sql
-- Base Rates Table
CREATE TABLE base_rates (
    id UUID PRIMARY KEY,
    hotel_id VARCHAR(50),
    room_type_id VARCHAR(50),
    start_date DATE,
    end_date DATE,
    day_of_week_mask INT,  -- Bit mask for days
    price_per_night_cents INT,
    min_stay_nights INT,
    INDEX idx_hotel_date (hotel_id, start_date, end_date)
);

-- Taxes & Fees Table
CREATE TABLE taxes_fees (
    id UUID PRIMARY KEY,
    hotel_id VARCHAR(50),
    name VARCHAR(100),
    type ENUM('PERCENTAGE', 'FIXED', 'PER_PERSON'),
    value DECIMAL(10,2),
    applies_to ENUM('ROOM', 'PERSON_PER_NIGHT'),
    is_inclusive BOOLEAN,
    start_date DATE,
    end_date DATE
);
```

**Currency Rates (RDBMS/Key-Value)**
```sql
CREATE TABLE currency_rates (
    from_currency CHAR(3),
    to_currency CHAR(3),
    rate DECIMAL(10,6),
    effective_date DATE,
    PRIMARY KEY (from_currency, to_currency, effective_date)
);
```

**Availability (In-Memory - Redis)**
```redis
Key: hotel:{hotel_id}:room:{room_type_id}:date:{YYYY-MM-DD}
Value: available_rooms (Integer)
TTL: 1 hour
```

#### Key Design Decisions

| Aspect | Decision | Rationale | Trade-off |
|--------|----------|-----------|-----------|
| **Hotel DB** | NoSQL (MongoDB) | Flexible schema, horizontal scaling | Complex joins harder |
| **Pricing DB** | RDBMS (PostgreSQL) | ACID for financial data, complex queries | Scaling requires sharding |
| **Search** | Elasticsearch | Full-text, geo-spatial, fast filtering | Eventual consistency |
| **Currency Storage** | Canonical (USD) | Single source of truth | Conversion on every read |
| **Caching** | Redis (multi-layer) | Low latency, reduced DB load | Stale data risk |
| **Updates** | Async (Kafka) | Decoupling, resilience | Eventual consistency |

---

### 🛠️ Low-Level Design (LLD)

#### Deep Dive: Pricing Service

**API Contract**
```http
GET /api/v1/pricing/{hotelId}

Query Parameters:
  roomTypeId: string
  checkIn: date (YYYY-MM-DD)
  checkOut: date (YYYY-MM-DD)
  guests: integer
  currency: string (optional, default: USD)

Response:
{
  "hotelId": "H001",
  "roomTypeId": "RT1",
  "checkIn": "2025-11-15",
  "checkOut": "2025-11-18",
  "nights": 3,
  "guests": 2,
  "currencyCode": "EUR",
  "basePrice": {
    "amount": "382.50",
    "currency": "EUR"
  },
  "taxAmount": {
    "amount": "57.38",
    "currency": "EUR"
  },
  "serviceFee": {
    "amount": "19.13",
    "currency": "EUR"
  },
  "totalPrice": {
    "amount": "459.01",
    "currency": "EUR"
  },
  "priceDetails": [
    {
      "type": "Base",
      "date": "2025-11-15",
      "amount": "127.50 EUR"
    },
    {
      "type": "City Tax",
      "date": "2025-11-15",
      "amount": "5.00 EUR"
    }
  ]
}
```

**Core Classes**

```java
// Value Object Pattern
class Money {
    private final BigDecimal amount;
    private final Currency currency;
    
    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.currency = currency;
    }
    
    public Money add(Money other) {
        validateSameCurrency(other);
        return new Money(amount.add(other.amount), currency);
    }
    
    public Money multiply(BigDecimal factor) {
        return new Money(amount.multiply(factor), currency);
    }
}

// DTO Pattern
class PriceBreakdown {
    private final Money basePrice;
    private final Money taxAmount;
    private final Money serviceFee;
    private final Money totalPrice;
    private final int nights;
    
    // Constructor, getters...
}

// Strategy Pattern
interface PricingStrategy {
    Money calculateBasePrice(Hotel hotel, SearchCriteria criteria);
    Money calculateTax(Money basePrice, TaxRule rule);
    Money calculateServiceFee(Money basePrice);
}

class StandardPricingStrategy implements PricingStrategy {
    // Implementation...
}

class SeasonalPricingStrategy implements PricingStrategy {
    // Peak season logic...
}
```

**Pricing Calculation Algorithm**

```java
class PricingService {
    private final PricingStrategy strategy;
    private final CurrencyConverter converter;
    private final Cache<String, PriceBreakdown> cache;
    
    public PriceBreakdown calculatePrice(
            String hotelId, 
            SearchRequest request) {
        
        // 1. Check cache
        String cacheKey = buildCacheKey(hotelId, request);
        PriceBreakdown cached = cache.get(cacheKey);
        if (cached != null) return cached;
        
        // 2. Calculate nights
        int nights = calculateNights(request.checkIn, request.checkOut);
        
        // 3. Get base rates for each night
        Money totalBase = Money.zero(USD);
        for (LocalDate date = request.checkIn; 
             date.isBefore(request.checkOut); 
             date = date.plusDays(1)) {
            
            BaseRate rate = getBaseRate(hotelId, date, request.guests);
            totalBase = totalBase.add(rate.getPricePerNight());
        }
        
        // 4. Apply rate plan discounts
        totalBase = applyDiscounts(totalBase, request.ratePlanId);
        
        // 5. Calculate taxes
        Money totalTax = Money.zero(USD);
        List<TaxRule> taxRules = getTaxRules(hotelId, request);
        for (TaxRule rule : taxRules) {
            Money tax = strategy.calculateTax(totalBase, rule);
            totalTax = totalTax.add(tax);
        }
        
        // 6. Calculate service fee
        Money serviceFee = strategy.calculateServiceFee(totalBase);
        
        // 7. Convert currency if needed
        if (!request.currency.equals(USD)) {
            totalBase = converter.convert(totalBase, request.currency);
            totalTax = converter.convert(totalTax, request.currency);
            serviceFee = converter.convert(serviceFee, request.currency);
        }
        
        // 8. Build result
        PriceBreakdown result = new PriceBreakdown(
            totalBase, totalTax, serviceFee, nights
        );
        
        // 9. Cache result (TTL: 30 mins)
        cache.put(cacheKey, result, Duration.ofMinutes(30));
        
        return result;
    }
}
```

**Currency Formatting**

```java
class PriceFormatter {
    public String format(Money money, Locale locale) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        formatter.setCurrency(money.getCurrency());
        return formatter.format(money.getAmount());
    }
}

// Examples:
// USD, en_US → $450.00
// EUR, de_DE → 382,50 €
// GBP, en_GB → £327.45
// JPY, ja_JP → ¥45,000
```

---

### 📈 Scalability & Trade-offs

#### Performance Optimizations

**1. Multi-Layer Caching Strategy**
```
Layer 1: CDN (static assets, hotel images)
Layer 2: Search Results Cache (Redis)
         └─ TTL: 5 minutes
         └─ Key: hash(location, dates, filters)

Layer 3: Pricing Cache (Redis)
         └─ TTL: 30 minutes
         └─ Key: hotel_id:dates:guests

Layer 4: Database Query Cache
         └─ PostgreSQL query cache
```

**2. Database Scaling**
- **Read Replicas**: 5-10 replicas for read-heavy queries
- **Sharding**: By geographic region or hotel_id range
- **Indexing**: Compound indexes on (hotel_id, date, room_type)

**3. Service Optimization**
- **Connection Pooling**: Reuse DB connections
- **Batch Queries**: Fetch multiple hotels in single query
- **Async Processing**: Non-blocking I/O for service calls

#### High Availability

```
Region 1 (US-East)          Region 2 (EU-West)
┌─────────────────┐         ┌─────────────────┐
│ Load Balancer   │         │ Load Balancer   │
│   (3 nodes)     │         │   (3 nodes)     │
└────────┬────────┘         └────────┬────────┘
         │                           │
    ┌────┴────┐                 ┌────┴────┐
    │ Service │                 │ Service │
    │ Cluster │                 │ Cluster │
    │(10 pods)│                 │(10 pods)│
    └────┬────┘                 └────┬────┘
         │                           │
    ┌────┴────┐                 ┌────┴────┐
    │Database │                 │Database │
    │ Master  │◄─── Repl ──────►│ Replica │
    └─────────┘                 └─────────┘
```

**Resilience Patterns**
- **Circuit Breaker**: Fail fast when service is down
- **Retry with Exponential Backoff**: Transient failures
- **Bulkhead Pattern**: Isolate thread pools per service
- **Graceful Degradation**: Show partial results if service fails

#### Scalability Targets

| Metric | Target | Strategy |
|--------|--------|----------|
| **QPS** | 10,000/min (167 QPS) | Horizontal scaling, caching |
| **Latency** | p95 < 200ms | Multi-layer cache, CDN |
| **Cache Hit Rate** | > 80% | Larger cache, intelligent TTLs |
| **Availability** | 99.9% | Multi-region, auto-failover |
| **Data Freshness** | < 5 min lag | Kafka for async updates |

---

### 💡 Extension Points

**1. Dynamic Pricing Engine**
```java
interface DynamicPricingRule {
    Money adjust(Money basePrice, Context context);
}

class DemandBasedPricing implements DynamicPricingRule {
    // Increase price when demand > threshold
}

class SeasonalPricing implements DynamicPricingRule {
    // Peak season adjustments
}

class LoyaltyPricing implements DynamicPricingRule {
    // Discounts for loyalty members
}
```

**2. Personalization**
- User search history
- Booking patterns
- Price sensitivity analysis
- ML-based recommendations

**3. A/B Testing Framework**
- Different pricing strategies
- UI/UX variations
- Search ranking algorithms

**4. Real-time Availability Integration**
- WebSocket connections to hotel PMS
- Event-driven availability updates
- Optimistic locking for bookings

---

### ⚖️ Critical Trade-offs Discussion

**1. Price Storage: USD vs Multi-Currency**

| Approach | Pros | Cons | Decision |
|----------|------|------|----------|
| Store in USD | Single source of truth, simpler | Conversion on every read | ✅ Choose for simplicity |
| Store all currencies | No conversion needed | Storage overhead, sync issues | ❌ Too complex |

**2. Caching: Aggressive vs Conservative**

| Strategy | Pros | Cons | Decision |
|----------|------|------|----------|
| Aggressive (long TTL) | Higher hit rate, lower latency | More stale data | ⚠️ Use with invalidation |
| Conservative (short TTL) | Fresher data | Lower hit rate | ❌ Defeats purpose |
| Event-driven invalidation | Best of both | Complex to implement | ✅ Ideal solution |

**3. Consistency: Strong vs Eventual**

| Component | Consistency Model | Rationale |
|-----------|------------------|-----------|
| Search Results | Eventual | Speed > accuracy for browsing |
| Price Calculation | Strong (within service) | Financial accuracy critical |
| Availability Check | Eventual in search | Real-time at booking |
| User Bookings | Strong | Money involved |

---

### 🗣️ Common Interviewer Questions

**Q: "How would you handle price changes?"**
```
Answer:
1. Hotel partner publishes price update to Kafka
2. Pricing Service consumes event
3. Updates PostgreSQL database
4. Publishes cache invalidation event
5. Redis cache invalidates affected entries
6. Next query fetches fresh price from DB

Alternative: Use versioned pricing with effective dates
```

**Q: "What if currency API fails?"**
```
Answer:
1. Primary: Use cached exchange rates (24hr TTL)
2. Fallback: Display prices in USD with disclaimer
3. Monitor: Alert on prolonged API failures
4. Graceful: Continue serving with slightly stale rates

Code:
try {
    rate = externalAPI.getRate(from, to);
    cache.set(key, rate, 24h);
} catch (APIException e) {
    rate = cache.get(key); // Use cached
    if (rate == null) {
        return displayInUSD(); // Fallback
    }
}
```

**Q: "How to prevent stale prices?"**
```
Answer:
1. Appropriate TTLs (5-30 mins based on component)
2. Event-driven cache invalidation
3. Version pricing with effective timestamps
4. At booking time: Always fetch real-time price
5. For critical paths: Bypass cache option
```

**Q: "Scale to 1M searches/minute?"**
```
Answer:
1. Horizontal Scaling:
   - Kubernetes auto-scaling (100+ pods)
   - Multiple regions (US, EU, APAC)

2. Database Sharding:
   - Shard by geographic region
   - OR by hotel_id hash
   - Use consistent hashing

3. Caching:
   - Redis cluster (100+ nodes)
   - 95%+ cache hit rate target
   - Distributed cache (Memcached + Redis)

4. Asynchronous Processing:
   - Queue non-critical operations
   - Batch database updates
   - Use read replicas (10-20 per shard)

5. Edge Computing:
   - CloudFlare Workers for geo-routing
   - Regional caching at edge

Architecture at 1M QPM:
- 100+ Search Service instances
- 50+ Pricing Service instances
- 20+ Database shards
- 100+ Redis cache nodes
- Multi-region deployment (5+ regions)
```

**Q: "How to handle concurrent bookings (race conditions)?"**
```
Answer:
1. Optimistic Locking:
   UPDATE availability 
   SET rooms_available = rooms_available - 1
   WHERE hotel_id = ? AND date = ? AND rooms_available > 0

2. Pessimistic Locking (for critical section):
   BEGIN TRANSACTION;
   SELECT * FROM availability WHERE ... FOR UPDATE;
   -- Check and update
   COMMIT;

3. Distributed Lock (Redis):
   SETNX lock:hotel:H001:date:2025-11-15 1 EX 10
   -- Process booking
   DEL lock:hotel:H001:date:2025-11-15

4. Event Sourcing:
   - Store all booking attempts as events
   - Replay to determine final state
   - Eventual consistency acceptable for availability display
```

---

## Round 3: DSA Coding

### Problem Statement (Round 3)

**Objective**: Filter hotels based on **continuous availability** between check-in and check-out dates, then calculate and display prices.

**Key Requirement**: Only hotels with availability for **ALL nights** in the date range are eligible.

**Interview Focus**:
- Implementation-heavy (coding style evaluation)
- Data structure design
- Algorithm optimization
- Time complexity analysis

---

### Solution Approach

#### Initial Complex Approach (Mention This!)
```
"I initially considered using an Interval Tree to store bookings,
which would allow O(log N + K) range queries. However, while 
implementing, I realized a simpler HashSet approach would be:
- Easier to code quickly
- Still efficient (O(D) per hotel where D = days)
- Good enough for typical hotel searches
- More maintainable
"
```

#### Final Simple Approach
```
1. Pre-process bookings into a Map<HotelId, Set<Date>>
2. For each hotel, check if ALL requested dates are free
3. Calculate price if available
4. Return results
```

---

### Code Implementation

```java
import java.time.LocalDate;
import java.util.*;

/**
 * Hotel Availability Filter - Round 3 DSA
 * 
 * Problem: Filter hotels with CONTINUOUS availability
 * Time Limit: 90 minutes (including explanation)
 */

// ========== Step 1: Data Models (10 mins) ==========

class Hotel {
    String id;
    String name;
    double pricePerNight;
    
    public Hotel(String id, String name, double pricePerNight) {
        this.id = id;
        this.name = name;
        this.pricePerNight = pricePerNight;
    }
}

class Booking {
    String hotelId;
    LocalDate checkIn;
    LocalDate checkOut;
    
    public Booking(String hotelId, LocalDate checkIn, LocalDate checkOut) {
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}

class HotelResult {
    Hotel hotel;
    boolean available;
    double totalPrice;
    long nights;
    
    public HotelResult(Hotel hotel, boolean available, 
                      double totalPrice, long nights) {
        this.hotel = hotel;
        this.available = available;
        this.totalPrice = totalPrice;
        this.nights = nights;
    }
}

// ========== Step 2: Core Logic (30 mins) ==========

class HotelAvailabilityChecker {
    
    // Map: hotelId -> Set of booked dates
    private Map<String, Set<LocalDate>> bookedDatesMap;
    
    public HotelAvailabilityChecker() {
        this.bookedDatesMap = new HashMap<>();
    }
    
    /**
     * Load bookings and build unavailable dates map
     * Time: O(B * D) where B=bookings, D=days per booking
     * Space: O(B * D)
     */
    public void loadBookings(List<Booking> bookings) {
        for (Booking booking : bookings) {
            bookedDatesMap.putIfAbsent(booking.hotelId, new HashSet<>());
            
            LocalDate date = booking.checkIn;
            while (!date.isAfter(booking.checkOut)) {
                bookedDatesMap.get(booking.hotelId).add(date);
                date = date.plusDays(1);
            }
        }
    }
    
    /**
     * Check continuous availability
     * Time: O(D) where D=days in range
     * Space: O(1)
     */
    public boolean isContinuouslyAvailable(
            String hotelId, 
            LocalDate checkIn, 
            LocalDate checkOut) {
        
        Set<LocalDate> bookedDates = 
            bookedDatesMap.getOrDefault(hotelId, new HashSet<>());
        
        LocalDate date = checkIn;
        while (date.isBefore(checkOut)) {
            if (bookedDates.contains(date)) {
                return false;
            }
            date = date.plusDays(1);
        }
        
        return true;
    }
    
    /**
     * Filter hotels and calculate prices
     * Time: O(H * D) where H=hotels, D=days
     */
    public List<HotelResult> filterAndPrice(
            List<Hotel> hotels,
            LocalDate checkIn,
            LocalDate checkOut) {
        
        List<HotelResult> results = new ArrayList<>();
        long nights = checkIn.until(checkOut).getDays();
        
        for (Hotel hotel : hotels) {
            boolean available = isContinuouslyAvailable(
                hotel.id, checkIn, checkOut
            );
            
            if (available) {
                double totalPrice = hotel.pricePerNight * nights;
                results.add(new HotelResult(
                    hotel, true, totalPrice, nights
                ));
            } else {
                results.add(new HotelResult(
                    hotel, false, 0, 0
                ));
            }
        }
        
        return results;
    }
}

// ========== Step 3: Demo & Testing (10 mins) ==========

public class HotelAvailabilityFilter {
    
    public static void main(String[] args) {
        // Setup
        List<Hotel> hotels = Arrays.asList(
            new Hotel("H1", "Grand Hotel", 150.0),
            new Hotel("H2", "Beach Resort", 200.0),
            new Hotel("H3", "City Inn", 100.0)
        );
        
        List<Booking> bookings = Arrays.asList(
            new Booking("H1", 
                LocalDate.of(2025, 11, 16), 
                LocalDate.of(2025, 11, 17)),
            new Booking("H2", 
                LocalDate.of(2025, 11, 14), 
                LocalDate.of(2025, 11, 15))
        );
        
        // Process
        HotelAvailabilityChecker checker = 
            new HotelAvailabilityChecker();
        checker.loadBookings(bookings);
        
        LocalDate checkIn = LocalDate.of(2025, 11, 15);
        LocalDate checkOut = LocalDate.of(2025, 11, 18);
        
        List<HotelResult> results = 
            checker.filterAndPrice(hotels, checkIn, checkOut);
        
        // Display
        displayResults(results, checkIn, checkOut);
    }
    
    private static void displayResults(
            List<HotelResult> results,
            LocalDate checkIn,
            LocalDate checkOut) {
        
        System.out.println("=== SEARCH RESULTS ===");
        System.out.println("Check-in:  " + checkIn);
        System.out.println("Check-out: " + checkOut);
        System.out.println("\nAVAILABLE:");
        
        for (HotelResult r : results) {
            if (r.available) {
                System.out.printf("%s: $%.2f/night x %d = $%.2f\n",
                    r.hotel.name, r.hotel.pricePerNight, 
                    r.nights, r.totalPrice);
            }
        }
        
        System.out.println("\nUNAVAILABLE:");
        for (HotelResult r : results) {
            if (!r.available) {
                System.out.println("✗ " + r.hotel.name);
            }
        }
    }
}
```

---

### Complexity Analysis

#### Time Complexity

| Operation | Complexity | Explanation |
|-----------|------------|-------------|
| **Load Bookings** | O(B × D) | B bookings, D avg days per booking |
| **Check Availability** | O(D) | D days in search range, HashSet lookup is O(1) |
| **Filter All Hotels** | O(H × D) | H hotels, checking D days each |
| **Total Search** | O(H × D) | Dominated by filtering step |

**Why HashSet is Efficient:**
- Insert: O(1) average
- Lookup: O(1) average
- For typical hotel searches (3-7 nights), very fast

#### Space Complexity

| Component | Complexity | Explanation |
|-----------|------------|-------------|
| **Booked Dates Map** | O(B × D) | All booked dates stored |
| **Result List** | O(H) | One result per hotel |
| **Total** | O(B × D + H) | Dominated by bookings |

#### Optimization Ideas

**1. Interval Tree Approach**
```java
// For many bookings (millions), use interval tree
class IntervalTree {
    // Store bookings as intervals
    // Query: O(log N + K) where K = overlapping intervals
}

// Trade-off:
// - Better for: Many bookings, long date ranges
// - Worse for: Simple cases, implementation complexity
```

**2. Segment Tree**
```java
// For range queries on availability
class SegmentTree {
    // Batch availability checks
    // Query: O(log N)
}

// Use when: Checking many date ranges frequently
```

**3. Caching Strategy**
```java
class CachedAvailabilityChecker {
    private Cache<String, Boolean> cache;
    
    public boolean isContinuouslyAvailable(...) {
        String key = buildKey(hotelId, checkIn, checkOut);
        
        Boolean cached = cache.get(key);
        if (cached != null) return cached;
        
        boolean result = computeAvailability(...);
        cache.put(key, result, Duration.ofMinutes(5));
        return result;
    }
}

// Reduces repeated checks for popular hotels/dates
```

**4. Database Optimization**
```sql
-- Index for fast lookup
CREATE INDEX idx_availability 
ON bookings (hotel_id, check_in, check_out);

-- Query with overlap check
SELECT COUNT(*) FROM bookings
WHERE hotel_id = ?
  AND NOT (check_out <= ? OR check_in >= ?);
-- If COUNT = 0, hotel is available
```

**5. Parallel Processing**
```java
// For checking 1000+ hotels
public List<HotelResult> filterAndPriceParallel(...) {
    return hotels.parallelStream()
        .map(hotel -> {
            boolean avail = isContinuouslyAvailable(...);
            return new HotelResult(...);
        })
        .collect(Collectors.toList());
}

// Reduces latency using multiple threads
```

---

### Extension Questions & Answers

#### **Q1: "Sort results by price"**
```java
public List<HotelResult> getAvailableSortedByPrice(...) {
    return filterAndPrice(hotels, checkIn, checkOut)
        .stream()
        .filter(r -> r.available)
        .sorted(Comparator.comparing(r -> r.totalPrice))
        .collect(Collectors.toList());
}

// Complexity: O(H × D + A log A) where A = available hotels
```

#### **Q2: "Handle cancellations"**
```java
public void cancelBooking(Booking booking) {
    Set<LocalDate> bookedDates = 
        bookedDatesMap.get(booking.hotelId);
    
    if (bookedDates != null) {
        LocalDate date = booking.checkIn;
        while (!date.isAfter(booking.checkOut)) {
            bookedDates.remove(date);
            date = date.plusDays(1);
        }
    }
}

// Time: O(D) where D = days in booking
```

#### **Q3: "Filter by amenities"**
```java
class Hotel {
    String id;
    String name;
    double pricePerNight;
    Set<String> amenities; // Added
}

public List<HotelResult> filterByAmenities(
        List<HotelResult> results,
        Set<String> requiredAmenities) {
    
    return results.stream()
        .filter(r -> r.hotel.amenities
            .containsAll(requiredAmenities))
        .collect(Collectors.toList());
}

// Time: O(H × A) where A = amenities to check
```

#### **Q4: "Handle partial availability"**
```java
public List<LocalDate> getAvailableNights(
        String hotelId,
        LocalDate checkIn,
        LocalDate checkOut) {
    
    Set<LocalDate> bookedDates = 
        bookedDatesMap.getOrDefault(hotelId, new HashSet<>());
    
    List<LocalDate> available = new ArrayList<>();
    LocalDate date = checkIn;
    
    while (date.isBefore(checkOut)) {
        if (!bookedDates.contains(date)) {
            available.add(date);
        }
        date = date.plusDays(1);
    }
    
    return available;
}

// Then suggest alternative dates to user
```

#### **Q5: "Scale to 10,000 hotels"**
```java
// 1. Use parallel streams
hotels.parallelStream()
    .map(hotel -> checkAvailability(hotel))
    .collect(Collectors.toList());

// 2. Batch database queries
List<String> hotelIds = hotels.stream()
    .map(h -> h.id)
    .collect(Collectors.toList());

Map<String, Set<LocalDate>> allBookings = 
    database.getBookingsForHotels(hotelIds);

// 3. Use caching aggressively
// 4. Consider async processing with CompletableFuture
```

---

## Design Patterns Used

### Round 2 (System Design)

#### **1. Strategy Pattern**
```java
interface PricingStrategy {
    Money calculateBasePrice(...);
    Money calculateTax(...);
}

class StandardPricingStrategy implements PricingStrategy { }
class SeasonalPricingStrategy implements PricingStrategy { }
class DemandBasedPricingStrategy implements PricingStrategy { }

// Usage
PricingStrategy strategy = getStrategy(context);
Money price = strategy.calculateBasePrice(hotel, criteria);
```
**Why**: Easily swap pricing algorithms without changing client code

#### **2. Facade Pattern**
```java
class HotelSearchService {
    // Simple interface hiding complex subsystems
    public List<HotelSearchResult> searchHotels(SearchRequest req) {
        // Coordinates: Hotel, Pricing, Currency, Availability services
        hotels = hotelService.findByLocation(req.location);
        prices = pricingService.calculatePrices(hotels, req.dates);
        converted = currencyService.convert(prices, req.currency);
        return aggregate(hotels, prices, converted);
    }
}
```
**Why**: Simplifies complex interactions between multiple services

#### **3. Repository Pattern**
```java
interface HotelRepository {
    List<Hotel> findByLocation(String location);
    Hotel findById(String id);
    void save(Hotel hotel);
}

class MongoHotelRepository implements HotelRepository {
    // MongoDB-specific implementation
}

class PostgresHotelRepository implements HotelRepository {
    // PostgreSQL-specific implementation
}
```
**Why**: Abstracts data access, easy to swap storage

#### **4. Adapter Pattern**
```java
interface CurrencyConverter {
    Money convert(Money amount, Currency target);
}

class ExternalAPIAdapter implements CurrencyConverter {
    private ExternalForexAPI externalAPI;
    
    public Money convert(Money amount, Currency target) {
        // Adapt external API to our interface
        ExternalRate rate = externalAPI.getRate(
            amount.getCurrency(), target
        );
        return new Money(
            amount.getAmount().multiply(rate.getValue()),
            target
        );
    }
}
```
**Why**: Integrate external APIs without coupling

#### **5. Value Object Pattern**
```java
class Money {
    private final BigDecimal amount;
    private final Currency currency;
    
    // Immutable
    // Compared by value, not reference
    // No identity
    
    @Override
    public boolean equals(Object obj) {
        Money other = (Money) obj;
        return amount.equals(other.amount) && 
               currency.equals(other.currency);
    }
}
```
**Why**: Ensures immutability and correctness for financial data

#### **6. Builder Pattern**
```java
class SearchRequest {
    private SearchRequest(Builder builder) {
        this.location = builder.location;
        this.checkIn = builder.checkIn;
        this.checkOut = builder.checkOut;
        // ...
    }
    
    public static class Builder {
        private String location;
        private LocalDate checkIn;
        private LocalDate checkOut;
        
        public Builder location(String location) {
            this.location = location;
            return this;
        }
        
        public SearchRequest build() {
            validate();
            return new SearchRequest(this);
        }
    }
}

// Usage
SearchRequest request = new SearchRequest.Builder()
    .location("New York")
    .checkIn(LocalDate.of(2025, 11, 15))
    .checkOut(LocalDate.of(2025, 11, 18))
    .currency(Currency.getInstance("EUR"))
    .build();
```
**Why**: Clean construction of complex objects with validation

#### **7. Observer Pattern (Event-Driven)**
```java
// Price update events
class PriceUpdateEvent {
    String hotelId;
    Money newPrice;
    LocalDate effectiveDate;
}

interface PriceUpdateListener {
    void onPriceUpdate(PriceUpdateEvent event);
}

class CacheInvalidationListener implements PriceUpdateListener {
    public void onPriceUpdate(PriceUpdateEvent event) {
        cache.invalidate("price:" + event.hotelId);
    }
}

class SearchIndexListener implements PriceUpdateListener {
    public void onPriceUpdate(PriceUpdateEvent event) {
        elasticsearch.updateDocument(event.hotelId, event.newPrice);
    }
}
```
**Why**: Decouple components, enable async updates

---

### Round 3 (DSA Coding)

#### **1. Repository Pattern (Lightweight)**
```java
class HotelAvailabilityChecker {
    private Map<String, Set<LocalDate>> bookedDatesMap;
    
    // Encapsulates data storage
    // Could swap HashMap for database later
}
```
**Why**: Simple data access abstraction for interview

#### **2. Data Transfer Object (DTO)**
```java
class HotelResult {
    Hotel hotel;
    boolean available;
    double totalPrice;
    long nights;
}
```
**Why**: Bundle related data for transfer between layers

#### **3. Service Layer Pattern**
```java
class HotelAvailabilityChecker {
    // Business logic separated from data models
    public boolean isContinuouslyAvailable(...) { }
    public List<HotelResult> filterAndPrice(...) { }
}
```
**Why**: Clear separation of concerns

#### **4. Factory Pattern (Implicit)**
```java
// Creating test data
private static List<Hotel> createHotels() {
    return Arrays.asList(
        new Hotel("H1", "Grand Hotel", 150.0),
        new Hotel("H2", "Beach Resort", 200.0)
    );
}
```
**Why**: Centralized object creation for testing

---

## Interview Tips & Best Practices

### Do's ✅

**Round 2 (System Design)**
- ✅ Start with clarifying questions (5-10 mins)
- ✅ Draw diagrams as you explain
- ✅ Discuss trade-offs explicitly
- ✅ Mention both pros and cons of decisions
- ✅ Think out loud throughout
- ✅ Ask for feedback: "Does this make sense?"
- ✅ Prioritize: "We can deep-dive into X or Y, which interests you?"
- ✅ Use real numbers: "10K QPS", "p95 < 200ms"
- ✅ Discuss failure scenarios
- ✅ Mention monitoring/observability

**Round 3 (DSA Coding)**
- ✅ Clarify inputs/outputs before coding
- ✅ Start with simple approach, optimize later
- ✅ Mention complex approach you considered
- ✅ Write clean, readable code
- ✅ Add comments explaining key logic
- ✅ Test with examples as you code
- ✅ Discuss time/space complexity
- ✅ Suggest optimizations even if no time
- ✅ Handle edge cases
- ✅ Use meaningful variable names

### Don'ts ❌

**Round 2 (System Design)**
- ❌ Jump to solution without clarifying
- ❌ Over-engineer for mentioned scale
- ❌ Ignore trade-offs
- ❌ Design in silence
- ❌ Forget about failure scenarios
- ❌ Use buzzwords without understanding
- ❌ Assume infinite resources
- ❌ Skip database schema discussion
- ❌ Ignore caching strategies
- ❌ Forget about monitoring

**Round 3 (DSA Coding)**
- ❌ Start coding immediately
- ❌ Write messy, unreadable code
- ❌ Ignore edge cases
- ❌ Over-optimize prematurely
- ❌ Use complex data structures unnecessarily
- ❌ Forget to explain your approach
- ❌ Skip testing your code
- ❌ Panic if stuck (think out loud)
- ❌ Hardcode values without constants
- ❌ Ignore interviewer hints

---

## Time Management Cheat Sheet

### Round 2: System Design (90 mins)

| Time | Activity | Key Deliverable |
|------|----------|-----------------|
| 0-5 | Introduction & Structure | Agreement on approach |
| 5-15 | Clarifying Questions | Requirements doc |
| 15-20 | HLD Overview | Architecture diagram |
| 20-30 | Component Details | Service responsibilities |
| 30-35 | Data Flow | Request/response flow |
| 35-50 | Database Schema | Table designs |
| 50-65 | LLD Deep-Dive | Detailed class/API design |
| 65-75 | Scalability | Scaling strategies |
| 75-85 | Trade-offs | Decision rationale |
| 85-90 | Q&A & Extensions | Future improvements |

### Round 3: DSA Coding (90 mins)

| Time | Activity | Key Deliverable |
|------|----------|-----------------|
| 0-5 | Understand Problem | Clarify inputs/outputs |
| 5-10 | Discuss Approach | Algorithm explanation |
| 10-20 | Design Data Models | Hotel, Booking classes |
| 20-50 | Core Implementation | Main algorithm code |
| 50-60 | Testing | Run test cases |
| 60-70 | Edge Cases | Handle special scenarios |
| 70-80 | Complexity Analysis | Time/space breakdown |
| 80-90 | Optimizations | Discuss improvements |

---

## Quick Reference Cards

### System Design Keywords to Use

- **Scalability**: Horizontal scaling, sharding, partitioning
- **Performance**: Caching, CDN, load balancing, indexing
- **Reliability**: Replication, failover, circuit breakers
- **Consistency**: Strong vs eventual, CAP theorem
- **Monitoring**: Metrics, logging, alerting, distributed tracing

### DSA Complexity Targets

| Data Structure | Access | Search | Insert | Delete |
|----------------|--------|--------|--------|--------|
| Array | O(1) | O(n) | O(n) | O(n) |
| HashSet | N/A | O(1) | O(1) | O(1) |
| HashMap | O(1) | O(1) | O(1) | O(1) |
| TreeMap | O(log n) | O(log n) | O(log n) | O(log n) |

---

## Final Checklist

### Before Interview
- [ ] Review core design patterns
- [ ] Practice drawing architecture diagrams
- [ ] Memorize complexity of common operations
- [ ] Prepare clarifying questions list
- [ ] Review trade-offs vocabulary

### During Interview
- [ ] Ask clarifying questions first
- [ ] Think out loud throughout
- [ ] Draw diagrams for complex ideas
- [ ] Discuss trade-offs explicitly
- [ ] Test your code with examples
- [ ] Handle edge cases
- [ ] Discuss scalability proactively
- [ ] Be open to feedback/hints

### After Interview
- [ ] Send thank you note
- [ ] Reflect on what went well
- [ ] Note areas for improvement
- [ ] Practice weak areas

---

## Additional Resources

### System Design
- **Books**: "Designing Data-Intensive Applications" by Martin Kleppmann
- **Websites**: System Design Primer (GitHub), ByteByteGo
- **Practice**: Design Instagram, YouTube, Uber, etc.

### DSA
- **Books**: "Cracking the Coding Interview" by Gayle McDowell
- **Platforms**: LeetCode, HackerRank, CodeSignal
- **Topics**: Arrays, HashMaps, Trees, Graphs, Dynamic Programming

---

**Good Luck! 🚀**

*Remember: Interviewers value clear communication and structured thinking more than perfect solutions.*