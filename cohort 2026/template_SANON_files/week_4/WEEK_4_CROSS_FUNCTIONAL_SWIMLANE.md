# WEEK 4 CROSS-FUNCTIONAL SWIMLANE

**Program:** BIT × Infineon Excellence Program  
**Project:** Sustainable Farm  
**Workstream:** Product Transformation  
**Week:** 4  
**Focus:** Process & Dependency Visualization  
**Owner:** Abdoul Ben Fatao SANON  
**Created:** 2026-08-10  
**Status:** 🔵 IN PROGRESS  

---

## 01. EXECUTIVE SUMMARY

This swimlane diagram visualizes the cross-functional process flow across all seven workstreams in the Sustainable Farm ecosystem, showing how data, materials, and decisions flow from mango harvest to commercial sales.

**Primary Purpose:** Answer the Week 04 strategic question: "How do the different workstreams interact, and how can Product Transformation integrate into one coherent platform?"

**Source of Truth:** Week 3 Cross-Functional Requirements Matrix (validated by all teams)

---

## 02. SWIMLANE STRUCTURE

### Lanes (7 Total)

| Lane | Responsibility | Key Outputs |
|------|----------------|--------------|
| **PLANTS** | Raw material production and harvest | Harvest data, batch IDs, quality grades |
| **PRODUCT TRANSFORMATION** | Processing and traceability | Transformed products, quality records, batch history |
| **ENERGY** | Power generation and distribution | Energy availability, consumption data |
| **WATER** | Water management and quality | Water availability, consumption data, quality standards |
| **MACHINERY** | Equipment and maintenance | Equipment capacity, utilization data, maintenance schedules |
| **STORAGE** | Pre/post-transformation storage | Storage capacity, inventory, conditions |
| **SALES & MARKETING** | Market access and customer relationships | Market requirements, sales orders, customer feedback |

---

## 03. COMPLETE PROCESS FLOW

### Phase 1: Harvest & Intake

```
┌─────────────────────────────────────────────────────────────────────────────────────────────────┐
│ PHASE 1: HARVEST & INTAKE                                                                       │
└─────────────────────────────────────────────────────────────────────────────────────────────────┘

PLANTS                    PRODUCT TRANSFORMATION    ENERGY    WATER     MACHINERY    STORAGE    SALES
│                          │                          │         │          │           │          │
│ Record Harvest           │                          │         │          │           │          │
│ - Variety: Keitt         │                          │         │          │           │          │
│ - Quantity: 44,000 kg    │                          │         │          │           │          │
│ - Date: 2026-01-15      │                          │         │          │           │          │
│ - Block: B-01           │                          │         │          │           │          │
│ - Quality: Grade A      │                          │         │          │           │          │
│ ↓                         │                          │         │          │           │          │
│ Generate Batch ID        │                          │         │          │           │          │
│ B-2026-001              │                          │         │          │           │          │
│ ↓                         │                          │         │          │           │          │
│ Transmit Harvest Data ───→│ Receive Harvest Data     │         │          │           │          │
│                          │ - Validate batch ID       │         │          │           │          │
│                          │ - Record intake           │         │          │           │          │
│                          │ - Create BATCH record     │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Check Storage Capacity    │         │          │           │          │
│                          │ ←──────────────────────────│         │          │           │          │
│                          │ Storage available: 50,000 kg│         │          │           │          │
│                          │ ✓ Capacity OK              │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Batch Ready for Processing │         │          │           │          │
```

---

### Phase 2: Washing & Sorting

```
┌─────────────────────────────────────────────────────────────────────────────────────────────────┐
│ PHASE 2: WASHING & SORTING                                                                      │
└─────────────────────────────────────────────────────────────────────────────────────────────────┘

PLANTS                    PRODUCT TRANSFORMATION    ENERGY    WATER     MACHINERY    STORAGE    SALES
│                          │                          │         │          │           │          │
│                          │ Assign to Wash Station   │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Request Water Availability│         │          │           │          │
│                          │ ←──────────────────────────────────│          │           │          │
│                          │ Water available: 5,000 L/day│      │          │           │          │
│                          │ ✓ Water OK                │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Request Equipment          │         │          │           │          │
│                          │ ←──────────────────────────────────────────────│           │          │
│                          │ Washing Station 2 available│         │          │           │          │
│                          │ Capacity: 200 kg/h         │         │          │           │          │
│                          │ ✓ Equipment OK             │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Execute Washing Process    │         │          │           │          │
│                          │ - Record water usage: 500 L │         │          │           │          │
│                          │ - Record energy usage: 2 kWh│←────────│          │           │          │
│                          │ - Record equipment: WS-002   │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Record WASH_SORT_RECORD    │         │          │           │          │
│                          │ - Input: 1,000 kg          │         │          │           │          │
│                          │ - Output: 950 kg           │         │          │           │          │
│                          │ - Waste: 50 kg (5%)        │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ QC Checkpoint #1           │         │          │           │          │
│                          │ - Visual inspection         │         │          │           │          │
│                          │ - Foreign material check     │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Pass/Fail Decision         │         │          │           │          │
│                          │ ✓ Pass                     │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Transmit Water Usage ───────────────────────────│           │          │
│                          │ Transmit Energy Usage ──────│                    │           │          │
│                          │ Transmit Equipment Usage ─────────────────────────────────────│          │
```

---

### Phase 3: Cutting & Preparation

```
┌─────────────────────────────────────────────────────────────────────────────────────────────────┐
│ PHASE 3: CUTTING & PREPARATION                                                                  │
└─────────────────────────────────────────────────────────────────────────────────────────────────┘

PLANTS                    PRODUCT TRANSFORMATION    ENERGY    WATER     MACHINERY    STORAGE    SALES
│                          │                          │         │          │           │          │
│                          │ Assign to Cutting Station│         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Request Equipment          │         │          │           │          │
│                          │ ←──────────────────────────────────────────────│           │          │
│                          │ Slicing Machine available  │         │          │           │          │
│                          │ Capacity: 100 kg/h         │         │          │           │          │
│                          │ ✓ Equipment OK             │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Execute Cutting Process    │         │          │           │          │
│                          │ - Record energy usage: 1.5 kWh│←───────│          │           │          │
│                          │ - Record equipment: SM-001   │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Record CUTTING_RECORD      │         │          │           │          │
│                          │ - Input: 950 kg            │         │          │           │          │
│                          │ - Output: 800 kg           │         │          │           │          │
│                          │ - Waste: 150 kg (peels/seeds)│         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ QC Checkpoint #2           │         │          │           │          │
│                          │ - Slice thickness check     │         │          │           │          │
│                          │ - Uniformity check          │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Pass/Fail Decision         │         │          │           │          │
│                          │ ✓ Pass                     │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Transmit Energy Usage ──────│                    │           │          │
│                          │ Transmit Equipment Usage ─────────────────────────────────────│          │
```

---

### Phase 4: Drying (Core Transformation)

```
┌─────────────────────────────────────────────────────────────────────────────────────────────────┐
│ PHASE 4: DRYING (CORE TRANSFORMATION)                                                           │
└─────────────────────────────────────────────────────────────────────────────────────────────────┘

PLANTS                    PRODUCT TRANSFORMATION    ENERGY    WATER     MACHINERY    STORAGE    SALES
│                          │                          │         │          │           │          │
│                          │ Check Energy Availability │         │          │           │          │
│                          │ ←─────────────────────────│         │          │           │          │
│                          │ Solar output: High (peak)  │         │          │           │          │
│                          │ Grid available: Yes         │         │          │           │          │
│                          │ ✓ Energy OK                │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Assign to Solar Dryer      │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Request Equipment          │         │          │           │          │
│                          │ ←──────────────────────────────────────────────│           │          │
│                          │ Solar Dryer A available     │         │          │           │          │
│                          │ Capacity: 500 kg           │         │          │           │          │
│                          │ Power: 5 kW (solar)         │         │          │           │          │
│                          │ ✓ Equipment OK             │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Execute Drying Process     │         │          │           │          │
│                          │ - Select energy source: Solar│         │          │           │          │
│                          │ - Record temperature: 60°C   │         │          │           │          │
│                          │ - Record humidity: 20%       │         │          │           │          │
│                          │ - Record duration: 18h      │         │          │           │          │
│                          │ - Record energy: 90 kWh     │←────────│          │           │          │
│                          │ - Record equipment: SD-001   │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ CCP Monitoring (HACCP)     │         │          │           │          │
│                          │ - Moisture check every 2h   │         │          │           │          │
│                          │ - Temperature log           │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Record DRYING_RUN          │         │          │           │          │
│                          │ - Input: 800 kg            │         │          │           │          │
│                          │ - Output: 200 kg           │         │          │           │          │
│                          │ - Yield: 25%               │         │          │           │          │
│                          │ - Final moisture: 12%       │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ QC Checkpoint #3 (CCP)      │         │          │           │          │
│                          │ - Moisture content: 12%     │         │          │           │          │
│                          │ - Spec: 10-15%             │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Pass/Fail Decision         │         │          │           │          │
│                          │ ✓ Pass                     │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Transmit Energy Usage ──────│                    │           │          │
│                          │ Transmit Equipment Usage ─────────────────────────────────────│          │
│                          │ Record COMPLIANCE_RECORD    │         │          │           │          │
│                          │ (HACCP CCP documentation)   │         │          │           │          │
```

---

### Phase 5: Cooling & Quality Control

```
┌─────────────────────────────────────────────────────────────────────────────────────────────────┐
│ PHASE 5: COOLING & QUALITY CONTROL                                                              │
└─────────────────────────────────────────────────────────────────────────────────────────────────┘

PLANTS                    PRODUCT TRANSFORMATION    ENERGY    WATER     MACHINERY    STORAGE    SALES
│                          │                          │         │          │           │          │
│                          │ Execute Cooling Process    │         │          │           │          │
│                          │ - Duration: 2h             │         │          │           │          │
│                          │ - Temperature: 25°C        │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ QC Checkpoint #4           │         │          │           │          │
│                          │ - Color check              │         │          │           │          │
│                          │ - Texture check            │         │          │           │          │
│                          │ - Weight check              │         │          │           │          │
│                          │ - Microbial test (sample)   │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Pass/Fail Decision         │         │          │           │          │
│                          │ ✓ Pass                     │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Record QC_CHECKPOINT       │         │          │           │          │
│                          │ - All parameters within spec │         │          │           │          │
│                          │ - Certificate: QC-001      │         │          │           │          │
```

---

### Phase 6: Packaging

```
┌─────────────────────────────────────────────────────────────────────────────────────────────────┐
│ PHASE 6: PACKAGING                                                                              │
└─────────────────────────────────────────────────────────────────────────────────────────────────┘

PLANTS                    PRODUCT TRANSFORMATION    ENERGY    WATER     MACHINERY    STORAGE    SALES
│                          │                          │         │          │           │          │
│                          │ Check Packaging Requirements│         │          │           │          │
│                          │ ←──────────────────────────────────────────────────────────────────│
│                          │ Market: Germany/EU          │         │          │           │          │
│                          │ Material: Food-grade vacuum  │         │          │           │          │
│                          │ Labeling: Lot code, expiry   │         │          │           │          │
│                          │ ✓ Requirements OK          │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Execute Packaging Process  │         │          │           │          │
│                          │ - Package size: 250g        │         │          │           │          │
│                          │ - Units: 800 packs          │         │          │           │          │
│                          │ - Generate lot code: L-2026-001│         │          │           │          │
│                          │ - Calculate expiry: 2026-07-15│         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Record PACKAGING_RECORD    │         │          │           │          │
│                          │ - Product: Dried Mango      │         │          │           │          │
│                          │ - Quantity: 200 kg          │         │          │           │          │
│                          │ - Lot code: L-2026-001      │         │          │           │          │
│                          │ - Best before: 2026-07-15    │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ QC Checkpoint #5           │         │          │           │          │
│                          │ - Seal integrity            │         │          │           │          │
│                          │ - Label accuracy            │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Pass/Fail Decision         │         │          │           │          │
│                          │ ✓ Pass                     │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Update BATCH status         │         │          │           │          │
│                          │ Status: COMPLETED           │         │          │           │          │
```

---

### Phase 7: Storage Handoff

```
┌─────────────────────────────────────────────────────────────────────────────────────────────────┐
│ PHASE 7: STORAGE HANDOFF                                                                        │
└─────────────────────────────────────────────────────────────────────────────────────────────────┘

PLANTS                    PRODUCT TRANSFORMATION    ENERGY    WATER     MACHINERY    STORAGE    SALES
│                          │                          │         │          │           │          │
│                          │ Check Storage Capacity    │         │          │           │          │
│                          │ ←──────────────────────────────────────────────────────────────────│
│                          │ Finished goods capacity: 10,000 kg│         │          │           │          │
│                          │ Current inventory: 8,200 kg │         │          │           │          │
│                          │ Available: 1,800 kg         │         │          │           │          │
│                          │ ✓ Capacity OK              │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Check Storage Conditions   │         │          │           │          │
│                          │ ←──────────────────────────────────────────────────────────────────│
│                          │ Temperature: 18°C (spec: 15-20°C)│         │          │           │          │
│                          │ Humidity: 60% (spec: 50-70%) │         │          │           │          │
│                          │ ✓ Conditions OK             │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Transmit Product Data ──────────────────────────────────────────────────│
│                          │ - Product: Dried Mango      │         │          │           │          │
│                          │ - Quantity: 200 kg          │         │          │           │          │
│                          │ - Lot code: L-2026-001      │         │          │           │          │
│                          │ - Quality: Pass              │         │          │           │          │
│                          │ - Expiry: 2026-07-15        │         │          │           │          │
│                          │ - Storage req: 18°C, 60% RH │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Receive Storage Confirmation│         │          │           │          │
│                          │ ←──────────────────────────────────────────────────────────────────│
│                          │ Location: Zone A-12          │         │          │           │          │
│                          │ Confirmed: 200 kg stored     │         │          │           │          │
│                          │ ✓ Storage confirmed         │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Update BATCH status         │         │          │           │          │
│                          │ Status: STORAGE_READY       │         │          │           │          │
```

---

### Phase 8: Commercial Readiness & Sales

```
┌─────────────────────────────────────────────────────────────────────────────────────────────────┐
│ PHASE 8: COMMERCIAL READINESS & SALES                                                           │
└─────────────────────────────────────────────────────────────────────────────────────────────────┘

PLANTS                    PRODUCT TRANSFORMATION    ENERGY    WATER     MACHINERY    STORAGE    SALES
│                          │                          │         │          │           │          │
│                          │ Transmit Commercial Data ──────────────────────────────────────────────────│
│                          │ - Product: Dried Mango      │         │          │           │          │
│                          │ - Lot code: L-2026-001      │         │          │           │          │
│                          │ - Quantity: 200 kg          │         │          │           │          │
│                          │ - Quality certificate: QC-001│         │          │           │          │
│                          │ - Traceability record: Complete│         │          │           │          │
│                          │ - EU compliance: Documented  │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │                          │         │          │          │ Receive Product Data
│                          │                          │         │          │          │ ↓
│                          │                          │         │          │          │ Update Inventory
│                          │                          │         │          │          │ Available: 200 kg
│                          │                          │         │          │          │ ↓
│                          │                          │         │          │          │ Match to Orders
│                          │                          │         │          │          │ Order #ORD-001: 150 kg
│                          │                          │         │          │          │ ↓
│                          │                          │         │          │          │ Allocate Stock
│                          │                          │         │          │          │ Lot L-2026-001: 150 kg
│                          │                          │         │          │          │ ↓
│                          │                          │         │          │          │ Generate Shipment
│                          │                          │         │          │          │ ↓
│                          │                          │         │          │          │ Transmit Shipment Info
│                          │ ←──────────────────────────────────────────────────────────────────│
│                          │ Update BATCH status         │         │          │           │          │
│                          │ Status: SHIPPED             │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Record Final Traceability   │         │          │           │          │
│                          │ - Harvest: B-2026-001       │         │          │           │          │
│                          │ - Process: Complete         │         │          │           │          │
│                          │ - Quality: Pass              │         │          │           │          │
│                          │ - Storage: Zone A-12         │         │          │           │          │
│                          │ - Shipment: ORD-001         │         │          │           │          │
│                          │ ↓                         │         │          │           │          │
│                          │ Close BATCH                 │         │          │           │          │
│                          │ Status: CLOSED              │         │          │           │          │
```

---

## 04. DATA EXCHANGE SUMMARY

### Plants → Product Transformation

| Data Field | Format | Trigger | Frequency |
|------------|--------|---------|-----------|
| Batch ID | Alphanumeric | Harvest recorded | Per harvest |
| Mango variety | Text | Harvest recorded | Per harvest |
| Harvest quantity | Number (kg) | Harvest recorded | Per harvest |
| Harvest date | Date | Harvest recorded | Per harvest |
| Farm/block | Text/code | Harvest recorded | Per harvest |
| Quality grade | Code | Harvest recorded | Per harvest |

### Product Transformation → Plants

| Data Field | Format | Trigger | Frequency |
|------------|--------|---------|-----------|
| Intake quantity | Number (kg) | Batch created | Per batch |
| Quality outcome | Pass/fail + reason | QC completed | Per batch |
| Yield percentage | Percentage | Batch completed | Per batch |

### Product Transformation → Energy

| Data Field | Format | Trigger | Frequency |
|------------|--------|---------|-----------|
| Energy consumption | kWh | Process completed | Per batch/stage |
| Peak demand usage | kWh + timestamp | Process completed | Per batch/stage |
| Production schedule | Schedule object | Weekly | Weekly |
| Energy efficiency | kWh/kg | Batch completed | Per batch |

### Product Transformation → Water

| Data Field | Format | Trigger | Frequency |
|------------|--------|---------|-----------|
| Water consumption | Liters | Process completed | Per batch/stage |
| Wastewater volume | Liters | Process completed | Per batch/stage |
| Wastewater quality | Quality metrics | Process completed | Per batch/stage |
| Production schedule | Schedule object | Weekly | Weekly |
| Water efficiency | L/kg | Batch completed | Per batch |

### Product Transformation → Machinery

| Data Field | Format | Trigger | Frequency |
|------------|--------|---------|-----------|
| Machine assignment | Machine ID + batch ID | Process started | Per batch/stage |
| Actual usage hours | Number | Process completed | Per batch/stage |
| Performance metrics | Metrics object | Process completed | Per batch/stage |

### Product Transformation → Storage

| Data Field | Format | Trigger | Frequency |
|------------|--------|---------|-----------|
| Product specifications | Specifications object | Packaging completed | Per batch |
| Expected quantity | Volume | Packaging completed | Per batch |
| Quality status | Status code | QC completed | Per batch |
| Shelf life | Date | Packaging completed | Per batch |
| Production schedule | Schedule object | Weekly | Weekly |

### Product Transformation → Sales & Marketing

| Data Field | Format | Trigger | Frequency |
|------------|--------|---------|-----------|
| Product specifications | Specifications object | Packaging completed | Per product |
| Quality certificates | Certificate object | QC completed | Per batch |
| Traceability records | Traceability object | Batch completed | Per batch |
| Available inventory | Volume | Storage confirmed | Daily |
| Sustainability metrics | Metrics object | Batch completed | Per batch |

### Energy → Product Transformation

| Data Field | Format | Trigger | Frequency |
|------------|--------|---------|-----------|
| Energy source mix | Percentage | Static | Static/seasonal |
| Solar output profile | Time series | Daily | Daily |
| Peak demand windows | Time ranges | Weekly | Weekly |

### Water → Product Transformation

| Data Field | Format | Trigger | Frequency |
|------------|--------|---------|-----------|
| Water availability | Volume/day | Static | Static/seasonal |
| Water quality standards | Specifications | Static | Static |

### Machinery → Product Transformation

| Data Field | Format | Trigger | Frequency |
|------------|--------|---------|-----------|
| Machine ID | Code | Static | Static |
| Machine type | Text | Static | Static |
| Capacity (kg/hour) | Number | Static | Static |
| Batch size (kg) | Number | Static | Static |
| Energy consumption (kWh/kg) | Number | Static | Static |
| Operating parameters | JSON/object | Static | Static |
| Maintenance schedule | Date range | Weekly | Weekly |

### Storage → Product Transformation

| Data Field | Format | Trigger | Frequency |
|------------|--------|---------|-----------|
| Raw material capacity | Volume | Static | Static |
| Finished product capacity | Volume | Static | Static |
| Storage conditions | Temp/humidity | Static | Static |
| Storage location | Location code | Static | Static |
| Current inventory | Volume | Daily | Daily |

### Sales & Marketing → Product Transformation

| Data Field | Format | Trigger | Frequency |
|------------|--------|---------|-----------|
| Target market | Market code | Static | Static |
| Quality standards | Specifications object | Static | Static |
| Packaging requirements | Specifications object | Static | Static |
| Demand forecast | Volume | Monthly | Monthly |
| Import regulations | Regulatory object | Static | Static |
| Traceability requirements | Requirements object | Static | Static |

---

## 05. DECISION POINTS

### DP-1: Storage Capacity Check (Phase 1)

**Location:** After harvest data receipt

**Decision:** Is storage capacity available for incoming harvest?

**Criteria:**
- Available capacity ≥ harvest quantity
- Storage conditions meet requirements

**Outcomes:**
- ✓ YES → Proceed to processing
- ✗ NO → Hold harvest, alert Storage team

**Owner:** Product Transformation Manager

---

### DP-2: Water Availability Check (Phase 2)

**Location:** Before washing process

**Decision:** Is water available for washing?

**Criteria:**
- Daily water availability ≥ process requirement
- Water quality meets standards

**Outcomes:**
- ✓ YES → Proceed to washing
- ✗ NO → Delay processing, alert Water team

**Owner:** Wash Station Operator

---

### DP-3: Equipment Availability Check (Phase 2-6)

**Location:** Before each process stage

**Decision:** Is required equipment operational?

**Criteria:**
- Equipment status = Operational
- Equipment not in maintenance
- Capacity available

**Outcomes:**
- ✓ YES → Proceed to process
- ✗ NO -> Reassign to alternative equipment or delay

**Owner:** Production Supervisor

---

### DP-4: Energy Availability Check (Phase 4)

**Location:** Before drying process

**Decision:** Is energy available for drying?

**Criteria:**
- Solar output sufficient OR grid available
- Peak demand window not exceeded

**Outcomes:**
- ✓ YES → Proceed to drying
- ✗ NO -> Schedule drying during off-peak or use grid

**Owner:** Drying Operator

---

### DP-5: QC Checkpoint #1 - Washing (Phase 2)

**Location:** After washing process

**Decision:** Does washed material pass quality check?

**Criteria:**
- Visual inspection: Pass
- Foreign material check: Pass

**Outcomes:**
- ✓ YES → Proceed to cutting
- ✗ NO -> Re-wash or reject batch

**Owner:** QC Inspector

---

### DP-6: QC Checkpoint #2 - Cutting (Phase 3)

**Location:** After cutting process

**Decision:** Do cut pieces meet specifications?

**Criteria:**
- Slice thickness within spec
- Uniformity within tolerance

**Outcomes:**
- ✓ YES → Proceed to drying
- ✗ NO -> Adjust cutting parameters or re-cut

**Owner:** QC Inspector

---

### DP-7: QC Checkpoint #3 - Drying CCP (Phase 4)

**Location:** After drying process (Critical Control Point)

**Decision:** Does dried product meet moisture specification?

**Criteria:**
- Moisture content: 10-15%
- Temperature log within range

**Outcomes:**
- ✓ YES → Proceed to cooling
- ✗ NO -> Extend drying or reject batch (CCP breach)

**Owner:** QC Inspector

**CCP Action:** If moisture > 15%, extend drying and re-test. If still > 15% after 2 extensions, reject batch and document corrective action.

---

### DP-8: QC Checkpoint #4 - Final Quality (Phase 5)

**Location:** After cooling process

**Decision:** Does product meet all quality specifications?

**Criteria:**
- Color within spec
- Texture within spec
- Weight within spec
- Microbial test: Pass

**Outcomes:**
- ✓ YES → Proceed to packaging
- ✗ NO -> Hold for investigation or reject

**Owner:** QC Inspector

---

### DP-9: QC Checkpoint #5 - Packaging (Phase 6)

**Location:** After packaging process

**Decision:** Is packaging correct and intact?

**Criteria:**
- Seal integrity: Pass
- Label accuracy: Pass
- Lot code present

**Outcomes:**
- ✓ YES → Proceed to storage
- ✗ NO -> Re-package or reject

**Owner:** QC Inspector

---

### DP-10: Storage Capacity Check (Phase 7)

**Location:** Before storage handoff

**Decision:** Is storage capacity available for finished goods?

**Criteria:**
- Available capacity ≥ finished product quantity
- Storage conditions meet requirements

**Outcomes:**
- ✓ YES → Proceed to storage
- ✗ NO -> Hold product, alert Storage team

**Owner:** Product Transformation Manager

---

### DP-11: Storage Conditions Check (Phase 7)

**Location:** Before storage handoff

**Decision:** Do storage conditions meet product requirements?

**Criteria:**
- Temperature: 15-20°C
- Humidity: 50-70%

**Outcomes:**
- ✓ YES → Proceed to storage
- ✗ NO -> Adjust conditions or use alternative storage

**Owner:** Storage Manager

---

## 06. RESOURCE DEPENDENCIES

### Energy Dependencies

| Process Stage | Energy Required | Source | Dependency |
|--------------|----------------|--------|------------|
| Washing | 2 kWh per batch | Grid | Energy team availability |
| Cutting | 1.5 kWh per batch | Grid | Energy team availability |
| Drying | 90 kWh per batch | Solar (preferred) / Grid | Solar output profile |
| Cooling | 5 kWh per batch | Grid | Energy team availability |
| Packaging | 3 kWh per batch | Grid | Energy team availability |

**Critical Dependency:** Drying process requires solar availability during peak hours for optimal sustainability.

---

### Water Dependencies

| Process Stage | Water Required | Quality Standard | Dependency |
|--------------|----------------|------------------|------------|
| Washing | 500 L per batch | Food-grade | Water team availability |
| Cleaning | 100 L per batch | Potable | Water team availability |

**Critical Dependency:** Washing process requires water quality certification from Water team.

---

### Machinery Dependencies

| Process Stage | Equipment Required | Capacity | Dependency |
|--------------|-------------------|----------|------------|
| Washing | Washing Station 2 | 200 kg/h | Machinery team maintenance |
| Cutting | Slicing Machine SM-001 | 100 kg/h | Machinery team maintenance |
| Drying | Solar Dryer A | 500 kg | Machinery team maintenance |
| Packaging | Packaging Machine | 300 kg/h | Machinery team maintenance |

**Critical Dependency:** All equipment must be operational and not in maintenance window.

---

### Storage Dependencies

| Storage Type | Capacity | Conditions | Dependency |
|--------------|----------|------------|------------|
| Raw materials | 50,000 kg | Ambient | Storage team capacity |
| Finished goods | 10,000 kg | 18°C, 60% RH | Storage team conditions |

**Critical Dependency:** Storage capacity must be available before processing begins.

---

## 07. HANDOFF POINTS

### Handoff 1: Plants → Product Transformation

**Trigger:** Harvest recorded and batch ID generated

**Data Transferred:** Harvest data (variety, quantity, date, block, quality grade, batch ID)

**Confirmation:** Product Transformation confirms batch creation

**Failure Mode:** If data invalid, Plants resends with corrections

---

### Handoff 2: Product Transformation → Energy

**Trigger:** Process stage completed

**Data Transferred:** Energy consumption (kWh), peak demand usage

**Confirmation:** Energy team records consumption

**Failure Mode:** If data missing, Product Transformation resends

---

### Handoff 3: Product Transformation → Water

**Trigger:** Washing process completed

**Data Transferred:** Water consumption (L), wastewater volume, wastewater quality

**Confirmation:** Water team records consumption

**Failure Mode:** If data missing, Product Transformation resends

---

### Handoff 4: Product Transformation → Machinery

**Trigger:** Process stage completed

**Data Transferred:** Equipment usage (hours), performance metrics

**Confirmation:** Machinery team records usage

**Failure Mode:** If data missing, Product Transformation resends

---

### Handoff 5: Product Transformation → Storage

**Trigger:** Packaging completed and QC passed

**Data Transferred:** Product specifications, quantity, quality status, shelf life, storage requirements

**Confirmation:** Storage team confirms storage location and capacity

**Failure Mode:** If storage unavailable, Product Transformation holds product

---

### Handoff 6: Product Transformation → Sales & Marketing

**Trigger:** Storage confirmed

**Data Transferred:** Product specifications, quality certificates, traceability records, available inventory

**Confirmation:** Sales & Marketing updates inventory

**Failure Mode:** If data invalid, Product Transformation resends

---

### Handoff 7: Sales & Marketing → Customer

**Trigger:** Order matched and allocated

**Data Transferred:** Product, lot code, quantity, quality certificates, traceability records

**Confirmation:** Customer acknowledges receipt

**Failure Mode:** If shipment issue, Sales & Marketing coordinates resolution

---

## 08. EXCEPTION HANDLING

### Exception 1: QC Failure at Any Stage

**Trigger:** QC checkpoint fails

**Immediate Action:**
- Hold batch at current stage
- Record failure details in QC_CHECKPOINT
- Notify Product Transformation Manager

**Decision Path:**
- If rework possible → Execute rework, re-test
- If rework not possible → Reject batch, document in COMPLIANCE_RECORD

**Communication:**
- Notify Plants team (if raw material issue)
- Notify Machinery team (if equipment issue)
- Notify Energy/Water teams (if resource issue)

---

### Exception 2: CCP Breach (Drying Moisture)

**Trigger:** Moisture content exceeds 15% after drying

**Immediate Action:**
- Hold batch at drying stage
- Record CCP breach in COMPLIANCE_RECORD
- Notify Product Transformation Manager and Compliance Auditor

**Corrective Action:**
- Extend drying by 2 hours
- Re-test moisture
- If still > 15%, extend by another 2 hours
- If still > 15% after 2 extensions, reject batch

**Documentation:**
- Complete corrective action report
- Update HACCP records
- Review drying process parameters

---

### Exception 3: Equipment Failure During Processing

**Trigger:** Equipment becomes inoperative during process

**Immediate Action:**
- Stop process immediately
- Record equipment status
- Notify Machinery team

**Decision Path:**
- If alternative equipment available → Reassign batch
- If no alternative available → Hold batch, reschedule

**Communication:**
- Notify Product Transformation Manager
- Update production schedule

---

### Exception 4: Resource Shortage (Energy/Water)

**Trigger:** Energy or water unavailable during processing

**Immediate Action:**
- Pause process if safe to do so
- Record resource status
- Notify respective team (Energy or Water)

**Decision Path:**
- If resource will be available within 2h → Hold batch
- If resource unavailable > 2h → Reschedule batch

**Communication:**
- Notify Product Transformation Manager
- Update production schedule

---

### Exception 5: Storage Capacity Unavailable

**Trigger:** Storage team reports no capacity for finished goods

**Immediate Action:**
- Hold product at packaging stage
- Notify Storage team

**Decision Path:**
- If alternative storage available → Use alternative
- If no alternative available → Delay packaging

**Communication:**
- Notify Product Transformation Manager
- Notify Sales & Marketing (potential delay)

---

## 09. TRACEABILITY FLOW

### End-to-End Traceability Example

**Batch ID:** B-2026-001

**Traceability Chain:**
1. **Harvest:** Plants team records harvest (44,000 kg, Keitt, Block B-01, Grade A)
2. **Intake:** Product Transformation creates BATCH record B-2026-001
3. **Washing:** WASH_SORT_RECORD created (input: 1,000 kg, output: 950 kg)
4. **Cutting:** CUTTING_RECORD created (input: 950 kg, output: 800 kg)
5. **Drying:** DRYING_RUN created (input: 800 kg, output: 200 kg, moisture: 12%)
6. **QC:** QC_CHECKPOINT records (all pass)
7. **Packaging:** PACKAGING_RECORD created (lot code: L-2026-001, expiry: 2026-07-15)
8. **Storage:** Storage location assigned (Zone A-12)
9. **Sales:** Shipment allocated (Order #ORD-001, 150 kg)
10. **Customer:** Customer receives product with full traceability record

**Query Capability:**
- By Batch ID: Complete process history
- By Lot Code: Packaging and quality information
- By Harvest Date: All batches from that harvest
- By Farm/Block: All batches from specific location
- By Customer: All batches shipped to specific customer

---

## 10. INTEGRATION POINTS SUMMARY

### Primary Integration Points

| Integration | Type | Trigger | Data | Direction |
|-------------|------|---------|------|-----------|
| Plants → PT | Data transfer | Harvest recorded | Harvest data | One-way |
| PT → Energy | Consumption reporting | Process completed | Energy usage | One-way |
| PT → Water | Consumption reporting | Process completed | Water usage | One-way |
| PT → Machinery | Usage reporting | Process completed | Equipment usage | One-way |
| PT → Storage | Product handoff | Packaging completed | Product data | Two-way |
| PT → Sales | Product availability | Storage confirmed | Inventory data | Two-way |
| Energy → PT | Availability check | Process scheduled | Energy profile | One-way |
| Water → PT | Availability check | Process scheduled | Water profile | One-way |
| Machinery → PT | Equipment status | Static | Equipment data | One-way |
| Storage → PT | Capacity check | Process scheduled | Storage data | One-way |
| Sales → PT | Requirements | Static | Market requirements | One-way |

### Integration Mechanisms

**Synchronous (Real-time):**
- Equipment availability checks
- Resource availability checks
- QC checkpoint decisions

**Asynchronous (Batch):**
- Harvest data transfer
- Consumption reporting
- Product handoff
- Inventory updates

**Static (One-time):**
- Equipment specifications
- Storage conditions
- Market requirements
- Quality standards

---

## 11. WEEK 04 SUCCESS CRITERIA

This swimlane diagram satisfies the following Week 04 success criteria:

- [x] Cross-functional swimlane is complete
- [x] All six external workstream dependencies are represented
- [x] Process steps are mapped from harvest to sales
- [x] Data inputs and outputs are defined
- [x] Handoffs are documented
- [x] Decision points are identified
- [x] Resource dependencies are mapped
- [x] Storage transition is shown
- [x] Commercial transition is shown
- [x] Based on validated Week 3 requirements

---

## 12. NEXT STEPS

Based on this swimlane diagram, the following Week 04 deliverables can now be completed:

1. **WEEK_4_DATA_INTEGRATION_SPEC.md** - Use the data exchange summary to define the Plants → Product Transformation integration contract

2. **WEEK_4_FORECASTING_SPEC.md** - Use the historical harvest data structure from the Plants → PT integration to define forecasting

3. **WEEK_4_DESIGN_ALIGNMENT.md** - Use the process flow to identify UI screens and terminology alignment needs

4. **Updated Prototype** - Use the swimlane to validate and improve the interactive prototype

---

**Status:** 🔵 SWIMLANE DIAGRAM COMPLETE  
**Next Action:** Begin STEP 4 - Define Plants → Product Transformation Integration Specification  
**Last Updated:** 2026-08-10
