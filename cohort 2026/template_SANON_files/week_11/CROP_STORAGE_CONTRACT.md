# Product Transformation → Crop Storage Contract Draft

**Contract ID:** API-005  
**Source Module:** Product Transformation  
**Consumer Module:** Crop Storage  
**Version:** 1.0 (DRAFT)  
**Date:** 2026-09-29  
**Status:** For Alignment with Crop Storage Team

---

## Overview

This contract defines the data exchange between Product Transformation and Crop Storage modules. Product Transformation produces dried mango as output, which becomes input for Crop Storage storage management.

---

## Integration Direction

**Product Transformation → Crop Storage**

```text
Packaging (Product Transformation)
        ↓
Storage Entry (Crop Storage)
        ↓
Storage Location
        ↓
Storage Conditions
        ↓
Storage Exit (Crop Storage)
        ↓
Shipment (Sales & Marketing)
```

---

## Conventions (Aligned with Plants Integration)

Based on the conventions established with the Plants team and agreed across the project:

### 1. Field Naming Convention
- **Format:** `snake_case` (lowercase with underscores)
- **Example:** `dried_quantity_kg`, `packaging_date`, `storage_location_id`
- **Rule:** Do NOT auto-convert to `camelCase` on either side

### 2. Null Handling Convention
- **`null`:** Information unavailable / not applicable
- **`0`:** Actual zero value
- **Rule:** Distinction must be preserved in data processing

### 3. Date Format Convention
- **Format:** ISO 8601 (YYYY-MM-DDTHH:MM:SSZ)
- **Example:** `2026-09-29T14:30:00Z`
- **Rule:** All timestamps in UTC timezone

### 4. Quantity Convention
- **Unit:** kilograms (kg)
- **Rule:** All quantities expressed in kg
- **Example:** `dried_quantity_kg`, `waste_quantity_kg`

### 5. Identifier Convention
- **Format:** Stable identifiers only (no display names)
- **Example:** `batch_id`, `storage_id`, `packaging_id`
- **Rule:** Never join modules using display names

---

## Proposed API Contract

### Endpoint 1: Create Storage Entry

**Purpose:** Product Transformation notifies Crop Storage when packaging is complete and dried mango is ready for storage.

```http
POST /api/crop-storage/entries
```

#### Request Body

```json
{
  "batch_id": "string",
  "packaging_id": "string",
  "dried_quantity_kg": "number",
  "waste_quantity_kg": "number",
  "packaging_date": "string (ISO 8601)",
  "packaging_operator_id": "string",
  "packaging_location": "string",
  "target_storage_location_id": "string",
  "product_grade": "string",
  "harvest_id": "string",
  "farm_id": "string",
  "block_id": "string",
  "variety_id": "string"
}
```

#### Field Descriptions

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| `batch_id` | string | Yes | Product Transformation batch identifier |
| `packaging_id` | string | Yes | Packaging record identifier |
| `dried_quantity_kg` | number | Yes | Quantity of dried mango ready for storage (kg) |
| `waste_quantity_kg` | number | No | Quantity of waste/by-product from drying process (kg) |
| `packaging_date` | string (ISO 8601) | Yes | Date when packaging was completed |
| `packaging_operator_id` | string | Yes | ID of operator who completed packaging |
| `packaging_location` | string | Yes | Location where packaging occurred |
| `target_storage_location_id` | string | No | Preferred storage location (if known) |
| `product_grade` | string | Yes | Product grade (A, B, C, etc.) |
| `harvest_id` | string | No | Plants harvest ID for traceability (if available) |
| `farm_id` | string | Yes | Farm identifier (from Plants) |
| `block_id` | string | Yes | Block/parcel identifier (from Plants) |
| `variety_id` | string | Yes | Variety identifier (from Plants) |

#### Response

**Status Code:** `201 Created`

```json
{
  "storage_entry_id": "string",
  "storage_location_id": "string",
  "entry_timestamp": "string (ISO 8601)",
  "available_capacity_kg": "number",
  "status": "string"
}
```

---

### Endpoint 2: Query Storage Entry Status

**Purpose:** Product Transformation queries the status of a storage entry.

```http
GET /api/crop-storage/entries/{storage_entry_id}
```

#### Response

**Status Code:** `200 OK`

```json
{
  "storage_entry_id": "string",
  "batch_id": "string",
  "storage_location_id": "string",
  "current_quantity_kg": "number",
  "entry_timestamp": "string (ISO 8601)",
  "status": "string",
  "storage_conditions": {
    "temperature_c": "number",
    "humidity_percent": "number"
  }
}
```

---

### Endpoint 3: Query Storage by Batch

**Purpose:** Product Transformation queries all storage entries for a specific batch.

```http
GET /api/crop-storage/entries/batch/{batch_id}
```

#### Response

**Status Code:** `200 OK`

```json
{
  "data": [
    {
      "storage_entry_id": "string",
      "batch_id": "string",
      "storage_location_id": "string",
      "entry_quantity_kg": "number",
      "current_quantity_kg": "number",
      "entry_timestamp": "string (ISO 8601)",
      "status": "string"
    }
  ]
}
```

---

## Traceability Support

### Traceability Chain

```text
Plants (harvest_id, farm_id, block_id, variety_id)
  ↓
Product Transformation (batch_id, packaging_id)
  ↓
Crop Storage (storage_entry_id, storage_location_id)
  ↓
Sales & Marketing (shipment_id, order_id)
```

### Recommended Traceability Fields

Product Transformation will provide the following traceability identifiers to Crop Storage:

- `harvest_id` - From Plants (when available via Harvest task)
- `farm_id` - From Plants
- `block_id` - From Plants (format: single character A, B, C, ...)
- `variety_id` - From Plants
- `batch_id` - Generated by Product Transformation
- `packaging_id` - Generated by Product Transformation

---

## Error Codes

Crop Storage should return appropriate HTTP status codes:

| Code | Meaning | Description |
|------|---------|-------------|
| `200` | OK | Request successful |
| `201` | Created | Storage entry created successfully |
| `400` | Bad Request | Invalid request data |
| `404` | Not Found | Storage entry or location not found |
| `409` | Conflict | Insufficient storage capacity |
| `422` | Unprocessable Entity | Business rule violation |
| `500` | Internal Server Error | Server error |

---

## Questions for Crop Storage Team

1. **Storage Entry Format:** Does the proposed JSON structure align with your expectations for storage entries?

2. **Stock Movement Types:** You mentioned IN/OUT/TRANSFER. Should Product Transformation always trigger an IN movement, or can it be other types?

3. **Batch Code Format:** You mentioned "B26-01" format. Should Product Transformation use this format for batch_id, or is this internal to Crop Storage?

4. **Capacity Check:** Should Product Transformation check storage capacity before sending storage entry, or should Crop Storage handle this and return error code 409 if capacity insufficient?

5. **Return Information:** What information does Product Transformation need from Crop Storage after storage entry? (e.g., storage_entry_id, storage_location_id, capacity status)

6. **Storage Exit:** Who initiates storage exit - Crop Storage or Sales & Marketing? How should Product Transformation be notified?

7. **By-Product Tracking:** Is the waste_quantity_kg field sufficient for by-product tracking, or do you need additional by-product information?

---

## Next Steps

1. **Review Contract:** Crop Storage team reviews this draft contract
2. **Provide Feedback:** Crop Storage team provides feedback on structure, fields, and conventions
3. **Revise Contract:** Product Transformation revises based on feedback
4. **Finalize Contract:** Both teams agree on final contract
5. **Implement Integration:** Product Transformation implements API calls to Crop Storage endpoints

---

**Contact:** Abdoul Ben Fatao SANON (Product Transformation)  
**Contact:** Christelle Alvine Dehoumon (Crop Storage)  

---

**Last Updated:** 2026-09-29  
**Status:** - Awaiting Crop Storage Team Feedback
