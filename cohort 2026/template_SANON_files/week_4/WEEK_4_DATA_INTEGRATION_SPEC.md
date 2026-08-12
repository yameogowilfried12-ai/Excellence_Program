# WEEK 4 DATA INTEGRATION SPECIFICATION

**Program:** BIT × Infineon Excellence Program  
**Project:** Sustainable Farm  
**Workstream:** Product Transformation  
**Week:** 4  
**Focus:** Plants → Product Transformation Integration  
**Owner:** Abdoul Ben Fatao SANON  
**Created:** 2026-08-10  
**Status:** 🔵 IN PROGRESS  

---

## 01. EXECUTIVE SUMMARY

This specification defines the data integration contract between the Plants workstream and the Product Transformation workstream, establishing how harvest data flows from raw material production to processing initiation.

**Primary Purpose:** Enable Product Transformation to receive accurate, timely harvest information from Plants to initiate processing batches with complete traceability.

**Reviewer Feedback Driver:** "Any possibility to get input from Plants app e.g. amount of Mango harvested..."

**Source of Truth:** Week 3 Cross-Functional Requirements Matrix, Week 4 Cross-Functional Swimlane Diagram

---

## 02. INTEGRATION OVERVIEW

### Integration Scope

**From:** Plants Workstream (Crop Production)  
**To:** Product Transformation Workstream (Processing)  
**Type:** One-way data transfer (Plants → Product Transformation)  
**Frequency:** Per harvest event  
**Priority:** P0 (Critical for Week 04)

### Integration Objectives

1. **Traceability:** Enable complete traceability from harvest to final product
2. **Planning:** Enable Product Transformation to plan processing capacity based on harvest data
3. **Quality:** Ensure quality grade information is preserved through the transformation process
4. **Efficiency:** Reduce manual data entry and errors through automated data transfer
5. **Scheduling:** Enable just-in-time processing scheduling based on harvest timing

---

## 03. DATA CONTRACT

### 3.1 Harvest Data Schema

**Entity Name:** `HARVEST_EVENT`  
**Direction:** Plants → Product Transformation  
**Trigger:** Harvest recorded in Plants system

| Field Name | Data Type | Format | Required | Description | Example |
|------------|-----------|--------|----------|-------------|---------|
| `harvest_id` | String | UUID | Yes | Unique identifier for harvest event | "H-2026-001" |
| `batch_id` | String | Alphanumeric | Yes | Batch identifier for traceability | "B-2026-001" |
| `harvest_date` | Date | ISO 8601 | Yes | Date of harvest | "2026-01-15" |
| `harvest_time` | Time | HH:MM:SS | No | Time of harvest (optional) | "08:30:00" |
| `mango_variety` | String | Enum | Yes | Mango variety code | "KEITT" |
| `farm_id` | String | Code | Yes | Farm identifier | "F-01" |
| `block_id` | String | Code | Yes | Farm block identifier | "B-01" |
| `harvest_quantity_kg` | Number | Decimal | Yes | Total harvest quantity in kilograms | 44000.0 |
| `quality_grade` | String | Enum | Yes | Quality grade code | "A" |
| `quality_grade_description` | String | Text | No | Quality grade description | "Premium - No defects" |
| `harvest_team_id` | String | Code | No | Harvest team identifier | "HT-01" |
| `harvest_supervisor` | String | Text | No | Supervisor name | "John Doe" |
| `weather_conditions` | String | Text | No | Weather during harvest | "Sunny, 28°C" |
| `storage_location` | String | Code | No | Initial storage location | "S-RAW-01" |
| `created_at` | Timestamp | ISO 8601 | Yes | Timestamp when record created | "2026-01-15T08:35:00Z" |
| `updated_at` | Timestamp | ISO 8601 | Yes | Timestamp when record last updated | "2026-01-15T08:35:00Z" |

### 3.2 Mango Variety Enum

| Code | Variety Name | Typical Use Case |
|------|--------------|------------------|
| `KEITT` | Keitt | Export (Germany/EU) |
| `KENT` | Kent | Export (Germany/EU) |
| `TOMMY` | Tommy Atkins | Local market |
| `AMÉLIE` | Amélie | Local market |
| `OTHER` | Other variety | Specify in description |

### 3.3 Quality Grade Enum

| Code | Grade Name | Description | Processing Priority |
|------|------------|-------------|---------------------|
| `A` | Premium | No defects, optimal ripeness | High |
| `B` | Standard | Minor defects, good ripeness | Medium |
| `C` | Processing | Visible defects, over/under ripe | Low |
| `D` | Reject | Significant defects, disease | Reject |

---

## 04. INTEGRATION MECHANISM

### 4.1 Integration Pattern

**Pattern:** Event-Driven Asynchronous Integration

**Rationale:**
- Harvest events occur at unpredictable times
- Product Transformation does not need real-time data
- Asynchronous pattern allows for retry and error handling
- Decouples systems for better resilience

### 4.2 Data Transfer Options

#### Option A: REST API (Recommended for MVP)

**Description:** Plants exposes a REST API endpoint that Product Transformation polls or subscribes to.

**Endpoint:** `GET /api/v1/harvest-events/{harvest_id}`

**Request:**
```http
GET /api/v1/harvest-events/H-2026-001 HTTP/1.1
Host: plants-system.sustainable-farm.local
Accept: application/json
Authorization: Bearer <token>
```

**Response:**
```json
{
  "harvest_id": "H-2026-001",
  "batch_id": "B-2026-001",
  "harvest_date": "2026-01-15",
  "harvest_time": "08:30:00",
  "mango_variety": "KEITT",
  "farm_id": "F-01",
  "block_id": "B-01",
  "harvest_quantity_kg": 44000.0,
  "quality_grade": "A",
  "quality_grade_description": "Premium - No defects",
  "harvest_team_id": "HT-01",
  "harvest_supervisor": "John Doe",
  "weather_conditions": "Sunny, 28°C",
  "storage_location": "S-RAW-01",
  "created_at": "2026-01-15T08:35:00Z",
  "updated_at": "2026-01-15T08:35:00Z"
}
```

**Polling Frequency:** Every 15 minutes during harvest season (June-September)

**Advantages:**
- Simple to implement
- Standard HTTP protocol
- Easy to debug
- No additional infrastructure

**Disadvantages:**
- Polling overhead
- Not truly real-time
- Requires Plants API to be built first

---

#### Option B: Message Queue (Recommended for Production)

**Description:** Plants publishes harvest events to a message queue (e.g., RabbitMQ, Kafka). Product Transformation subscribes to the queue.

**Topic:** `harvest.events`

**Message Format:**
```json
{
  "event_type": "harvest_recorded",
  "event_id": "E-2026-001",
  "timestamp": "2026-01-15T08:35:00Z",
  "data": {
    "harvest_id": "H-2026-001",
    "batch_id": "B-2026-001",
    "harvest_date": "2026-01-15",
    "mango_variety": "KEITT",
    "farm_id": "F-01",
    "block_id": "B-01",
    "harvest_quantity_kg": 44000.0,
    "quality_grade": "A"
  }
}
```

**Advantages:**
- Real-time event delivery
- Decouples systems completely
- Built-in retry and error handling
- Scalable architecture

**Disadvantages:**
- Requires message queue infrastructure
- More complex to implement
- Additional operational overhead

---

#### Option C: Shared Database (Fallback Option)

**Description:** Both workstreams read/write to a shared database table.

**Table:** `harvest_events`

**Advantages:**
- Simple conceptually
- No additional infrastructure
- Immediate consistency

**Disadvantages:**
- Tight coupling between systems
- Database becomes single point of failure
- Difficult to scale independently
- Security concerns with shared access

---

### 4.3 Recommended Approach for Week 04

**Recommendation:** Option A (REST API) for MVP, with Option B (Message Queue) as the production target.

**Rationale:**
- Week 04 is about defining the data contract, not implementing the full integration
- REST API is sufficient for the MVP demonstration
- Message Queue can be added in Week 5-6 (Technical Specification phase)
- The data contract remains the same regardless of integration mechanism

---

## 05. INTEGRATION TRIGGER

### 5.1 Trigger Event

**Event:** Harvest recorded in Plants system

**Trigger Conditions:**
1. Harvest quantity > 0
2. Quality grade assigned
3. Batch ID generated
4. Record saved to Plants database

### 5.2 Trigger Flow

```
Plants System
    ↓
Harvest recorded by field operator
    ↓
Plants validates harvest data
    ↓
Plants generates batch_id
    ↓
Plants saves harvest record
    ↓
Plants publishes harvest event (API or Queue)
    ↓
Product Transformation receives event
    ↓
Product Transformation validates data
    ↓
Product Transformation creates BATCH record
    ↓
Product Transformation sends confirmation
    ↓
Integration complete
```

### 5.3 Trigger Frequency

**Expected Frequency:**
- Harvest season: 1-3 events per day
- Off-season: 0 events per day

**Processing Window:**
- Product Transformation should process harvest events within 1 hour of receipt
- This allows same-day processing initiation

---

## 06. DATA VALIDATION

### 6.1 Product Transformation Validation Rules

**Required Fields:**
- All required fields must be present and non-null
- `harvest_id` must be unique (not previously received)
- `batch_id` must be unique (not previously used)

**Data Format Validation:**
- `harvest_date` must be a valid date (not in future)
- `harvest_quantity_kg` must be > 0
- `mango_variety` must be a valid enum value
- `quality_grade` must be a valid enum value

**Business Logic Validation:**
- `harvest_quantity_kg` should be reasonable (0 < quantity < 100,000 kg)
- `quality_grade` should match expected distribution (not all Grade A)
- `farm_id` and `block_id` should exist in reference data

### 6.2 Validation Error Handling

**Error Types:**

| Error Type | Severity | Action |
|------------|----------|--------|
| Missing required field | Critical | Reject event, log error, notify Plants team |
| Invalid data format | Critical | Reject event, log error, notify Plants team |
| Duplicate harvest_id | Critical | Reject event, log error, notify Plants team |
| Duplicate batch_id | Critical | Reject event, log error, notify Plants team |
| Business logic violation | Warning | Accept event with warning, log for review |

**Error Response Format:**
```json
{
  "status": "error",
  "error_code": "MISSING_REQUIRED_FIELD",
  "error_message": "Field 'mango_variety' is required",
  "harvest_id": "H-2026-001",
  "timestamp": "2026-01-15T08:36:00Z"
}
```

---

## 07. DATA OWNERSHIP

### 7.1 Ownership Matrix

| Data Field | Owner | Source of Truth | Update Rights | Read Rights |
|------------|-------|-----------------|---------------|-------------|
| `harvest_id` | Plants | Plants | Plants only | All teams |
| `batch_id` | Plants | Plants | Plants only | All teams |
| `harvest_date` | Plants | Plants | Plants only | All teams |
| `harvest_quantity_kg` | Plants | Plants | Plants only | All teams |
| `mango_variety` | Plants | Plants | Plants only | All teams |
| `farm_id` | Plants | Plants | Plants only | All teams |
| `block_id` | Plants | Plants | Plants only | All teams |
| `quality_grade` | Plants | Plants | Plants only | All teams |
| `intake_quantity_kg` | Product Transformation | Product Transformation | Product Transformation only | All teams |
| `processing_outcome` | Product Transformation | Product Transformation | Product Transformation only | All teams |
| `yield_percentage` | Product Transformation | Product Transformation | Product Transformation only | All teams |

### 7.2 Data Lifecycle

**Creation:** Plants creates harvest record  
**Transfer:** Plants transfers to Product Transformation  
**Processing:** Product Transformation creates processing records linked to harvest  
**Archival:** Both systems retain records for 7 years (EU traceability requirement)  
**Deletion:** No deletion allowed (audit trail requirement)

---

## 08. ERROR HANDLING & RETRY

### 8.1 Retry Strategy

**Retry Conditions:**
- Network timeout
- API unavailable (5xx error)
- Temporary database error

**Retry Configuration:**
- Max retries: 3
- Retry interval: Exponential backoff (1s, 2s, 4s)
- Total timeout: 10 seconds

**No Retry Conditions:**
- Validation errors (4xx)
- Duplicate data
- Invalid data format

### 8.2 Dead Letter Queue

**Purpose:** Store failed events for manual review and reprocessing

**Trigger:** Event fails after 3 retries

**Contents:**
- Original harvest event
- Error details
- Timestamp
- Retry count

**Handling:**
- Manual review by Product Transformation team
- If data error: Notify Plants team for correction
- If system error: Fix system issue, reprocess event

---

## 09. SECURITY & COMPLIANCE

### 9.1 Authentication

**Mechanism:** OAuth 2.0 Bearer Token

**Token Issuer:** Sustainable Farm Identity Provider

**Token Scope:** `read:harvest_events`

**Token Lifetime:** 1 hour

**Refresh:** Automatic token refresh before expiration

### 9.2 Authorization

**Role-Based Access Control:**

| Role | Access |
|------|--------|
| Product Transformation System | Read harvest events |
| Plants System | Create harvest events |
| Product Transformation Manager | Read harvest events, view audit logs |
| Plants Manager | Create harvest events, view audit logs |
| Auditor | Read harvest events, view audit logs |

### 9.3 Data Privacy

**Personal Data:** Minimal personal data (supervisor name only)

**Classification:** Internal business data

**Storage:** Encrypted at rest

**Transmission:** TLS 1.3 encryption

**Retention:** 7 years (EU food safety requirement)

---

## 10. MONITORING & LOGGING

### 10.1 Metrics to Monitor

**Integration Metrics:**
- Events received per day
- Events processed successfully per day
- Events failed per day
- Average processing time per event
- API response time (if using REST API)
- Queue depth (if using message queue)

**Business Metrics:**
- Harvest quantity received per day
- Harvest quantity processed per day
- Processing delay (harvest to processing start)
- Quality grade distribution

### 10.2 Logging Requirements

**Log Levels:**

| Level | When to Use |
|-------|-------------|
| ERROR | Integration failures, validation errors |
| WARN | Business logic violations, retry attempts |
| INFO | Successful event processing, trigger events |
| DEBUG | Detailed data flow, validation steps |

**Log Content:**
- Timestamp
- Event ID / Harvest ID
- Action performed
- Result (success/failure)
- Error details (if applicable)
- Processing time

**Log Retention:** 90 days

---

## 11. TESTING STRATEGY

### 11.1 Unit Tests

**Test Cases:**
- Validate required fields present
- Validate data formats (date, number, enum)
- Validate business logic (quantity ranges, grade distribution)
- Validate duplicate detection

### 11.2 Integration Tests

**Test Cases:**
- End-to-end harvest event transfer
- Error handling (missing fields, invalid data)
- Retry mechanism
- Dead letter queue

### 11.3 Contract Tests

**Test Cases:**
- API contract validation (schema matching)
- Enum value validation
- Field type validation

### 11.4 Sample Test Data

**Valid Harvest Event:**
```json
{
  "harvest_id": "H-TEST-001",
  "batch_id": "B-TEST-001",
  "harvest_date": "2026-01-15",
  "mango_variety": "KEITT",
  "farm_id": "F-01",
  "block_id": "B-01",
  "harvest_quantity_kg": 1000.0,
  "quality_grade": "A",
  "created_at": "2026-01-15T08:35:00Z",
  "updated_at": "2026-01-15T08:35:00Z"
}
```

**Invalid Harvest Event (Missing Field):**
```json
{
  "harvest_id": "H-TEST-002",
  "batch_id": "B-TEST-002",
  "harvest_date": "2026-01-15",
  "mango_variety": "KEITT",
  "farm_id": "F-01",
  "block_id": "B-01",
  "quality_grade": "A",
  "created_at": "2026-01-15T08:35:00Z",
  "updated_at": "2026-01-15T08:35:00Z"
}
```

**Expected Result:** Rejected with error "Field 'harvest_quantity_kg' is required"

---

## 12. IMPLEMENTATION PHASING

### Phase 1: Data Contract Definition (Week 04) ✅

**Status:** This document

**Deliverable:** Complete data contract specification

**Next:** Begin Phase 2

---

### Phase 2: API Specification (Week 05)

**Status:** Planned

**Deliverables:**
- OpenAPI/Swagger specification
- API endpoint documentation
- Authentication specification

**Dependencies:** Technical Specification phase

---

### Phase 3: Prototype Implementation (Week 07)

**Status:** Planned

**Deliverables:**
- Mock Plants API endpoint
- Product Transformation integration module
- End-to-end test with sample data

**Dependencies:** MVP Development phase

---

### Phase 4: Production Integration (Week 11)

**Status:** Planned

**Deliverables:**
- Real Plants API endpoint
- Production integration
- Monitoring and logging
- Error handling and retry

**Dependencies:** Full System Integration phase

---

## 13. ASSUMPTIONS & LIMITATIONS

### Assumptions

1. **Plants API Availability:** Plants workstream will implement a REST API or message queue by Week 07
2. **Batch ID Generation:** Plants will generate unique batch IDs for each harvest
3. **Quality Grading:** Plants will assign quality grades at harvest time
4. **Network Connectivity:** Reliable network connection between Plants and Product Transformation systems
5. **Data Consistency:** Harvest data will not be modified after transfer to Product Transformation

### Limitations

1. **Real-time Updates:** This specification does not support real-time updates to harvest data (e.g., quantity corrections)
2. **Bulk Transfers:** This specification handles one harvest event at a time (not bulk transfers)
3. **Historical Data:** This specification does not cover historical harvest data migration
4. **Offline Mode:** This specification assumes online operation (no offline mode)

### Future Enhancements

1. **Real-time Updates:** Add support for harvest data corrections and updates
2. **Bulk Transfers:** Add support for bulk harvest event transfer
3. **Historical Migration:** Add process for migrating historical harvest data
4. **Offline Mode:** Add support for offline operation with sync on reconnection
5. **Webhook Notifications:** Add webhook support for real-time notifications

---

## 14. WEEK 04 SUCCESS CRITERIA

This data integration specification satisfies the following Week 04 success criteria:

- [x] Plants → Product Transformation data flow is clearly defined
- [x] Data contract is specified with complete schema
- [x] Integration mechanism is proposed (REST API for MVP)
- [x] Integration trigger is defined (harvest recorded)
- [x] Data ownership is explicitly documented
- [x] Error handling and retry strategy is defined
- [x] Based on validated Week 3 requirements
- [x] Based on Week 4 swimlane diagram

---

## 15. NEXT STEPS

Based on this data integration specification, the following Week 04 deliverables can now be completed:

1. **WEEK_4_FORECASTING_SPEC.md** - Use the historical harvest data structure from this specification to define forecasting

2. **Data Model V3** - Add `HARVEST_EVENT` entity to Product Transformation data model

3. **API Specification (Week 05)** - Create detailed OpenAPI/Swagger specification

4. **Prototype Update (Week 07)** - Implement mock integration in MVP

---

**Status:** 🔵 DATA INTEGRATION SPECIFICATION COMPLETE  
**Next Action:** Begin STEP 5 - Define Forecasting Concept  
**Last Updated:** 2026-08-10
