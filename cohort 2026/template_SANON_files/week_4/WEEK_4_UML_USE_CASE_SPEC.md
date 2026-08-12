# WEEK 4 — UML USE CASE SPECIFICATION

**Program:** BIT × Infineon Excellence Program  
**Project:** Sustainable Farm  
**Workstream:** Product Transformation  
**Week:** 4  
**Focus:** UML Use Case Specification  
**Owner:** Abdoul Ben Fatao SANON  
**Created:** 2026-08-10  
**Status:** 🔵 IN PROGRESS

---

## 01. EXECUTIVE SUMMARY

This specification documents the Product Transformation Use Case Diagram, defining all actors, use cases, relationships, and their traceability to validated Week 03/04 requirements.

**Primary Purpose:** Establish a clear, traceable use case model that represents WHO interacts with the Product Transformation system and WHAT they need to accomplish.

**Source of Truth:** Week 3 Requirements Matrix, Week 4 Cross-Functional Swimlane, Week 4 Modeling Audit

---

## 02. ACTOR DEFINITIONS

### 2.1 Primary Actors (Product Transformation)

| Actor ID | Actor Name | Role | Responsibilities | Source |
|----------|------------|------|------------------|--------|
| A-001 | Product Transformation Manager | System oversight | Monitor dashboard, approve batches, review quality reports, access forecasting | WEEK_4_BASELINE_AUDIT.md Section 5.2 |
| A-002 | Wash Station Operator | Washing stage | Execute washing process, record water usage, perform QC checkpoint #1 | WEEK_4_BASELINE_AUDIT.md Section 5.2 |
| A-003 | Drying Operator | Drying stage | Execute drying process, record energy usage, monitor solar output | WEEK_4_BASELINE_AUDIT.md Section 5.2 |
| A-004 | QC Inspector | Quality control | Execute QC checkpoints, record pass/fail results, document defects | WEEK_4_BASELINE_AUDIT.md Section 5.2 |
| A-005 | Packaging Operator | Packaging stage | Execute packaging, apply lot codes, record packaging data | WEEK_4_BASELINE_AUDIT.md Section 5.2 |
| A-006 | Compliance Auditor | Regulatory compliance | Review HACCP records, audit compliance documentation, verify export readiness | WEEK_4_BASELINE_AUDIT.md Section 5.2 |

### 2.2 External Actors (Cross-Functional)

| Actor ID | Actor Name | Workstream | Type | Interaction | Source |
|----------|------------|-----------|------|-------------|--------|
| A-007 | Plants Field Operator | Plants | Human | Triggers harvest data entry | Week 3 Requirements Matrix |
| A-008 | Plants System | Plants | System | Provides harvest data via integration | WEEK_4_DATA_INTEGRATION_SPEC.md |
| A-009 | Energy System | Energy | System | Provides energy availability, receives consumption data | Week 4 Swimlane Phase 4 |
| A-010 | Water System | Water | System | Provides water availability, receives consumption data | Week 4 Swimlane Phase 2 |
| A-011 | Machinery System | Machinery | System | Provides equipment capacity/status, receives usage data | Week 4 Swimlane All phases |
| A-012 | Storage System | Storage | System | Provides storage capacity, receives products, updates inventory | Week 4 Swimlane Phases 1, 7 |
| A-013 | Sales & Marketing System | Sales & Marketing | System | Provides market requirements, receives product availability | Week 4 Swimlane Phases 6, 8 |

### 2.3 Actor Generalization Decisions

**Decision:** No actor generalization applied.

**Rationale:** Each actor has distinct responsibilities and authorization levels that warrant separate representation. While some actors share capabilities (e.g., all operators update batch progress), their primary use cases and domain contexts differ significantly.

---

## 03. USE CASE DEFINITIONS

### 3.1 Harvest & Batch Intake

| Use Case ID | Use Case Name | Primary Actor | Secondary Actors | Objective | Source |
|-------------|---------------|---------------|------------------|-----------|--------|
| UC-001 | Receive Harvest Data | Plants Field Operator | Plants System | Capture harvest data from Plants workstream to initiate processing | WEEK_4_DATA_INTEGRATION_SPEC.md |
| UC-002 | Create Batch | System | Product Transformation Manager | Initialize batch record with harvest data for traceability | Week 3 FR-02 |
| UC-003 | Validate Storage Capacity | System | Storage System | Ensure sufficient storage capacity before batch processing | Week 4 Swimlane Phase 1 |

### 3.2 Batch Management

| Use Case ID | Use Case Name | Primary Actor | Secondary Actors | Objective | Source |
|-------------|---------------|---------------|------------------|-----------|--------|
| UC-004 | Monitor Batch Status | Product Transformation Manager | System | Track batch progress through processing stages | Week 3 FR-02 |
| UC-005 | Update Batch Progress | Wash/Drying/Packaging Operators | System | Record batch advancement through processing stages | Week 3 FR-03 |
| UC-006 | Query Batch History | QC Inspector, Compliance Auditor | System | Retrieve complete batch traceability from harvest to commercial | Week 3 FR-02 |

### 3.3 Processing Management

| Use Case ID | Use Case Name | Primary Actor | Secondary Actors | Objective | Source |
|-------------|---------------|---------------|------------------|-----------|--------|
| UC-007 | Execute Washing Process | Wash Station Operator | Water System, Machinery System | Perform washing and sorting of raw materials | Week 4 Swimlane Phase 2 |
| UC-008 | Execute Cutting Process | System | Machinery System | Perform cutting and preparation of washed materials | Week 4 Swimlane Phase 3 |
| UC-009 | Execute Drying Process | Drying Operator | Energy System, Machinery System | Perform solar drying as core transformation | Week 4 Swimlane Phase 4 |
| UC-010 | Execute Cooling Process | System | Energy System | Cool dried products before quality control | Week 4 Swimlane Phase 5 |

### 3.4 Resource Tracking

| Use Case ID | Use Case Name | Primary Actor | Secondary Actors | Objective | Source |
|-------------|---------------|---------------|------------------|-----------|--------|
| UC-011 | Track Water Consumption | Wash Station Operator | Water System | Record water usage per washing operation | Week 3 FR-04 |
| UC-012 | Track Energy Consumption | Drying Operator | Energy System | Record energy usage per drying operation | Week 3 FR-04 |
| UC-013 | Monitor Equipment Usage | Drying Operator | Machinery System | Track equipment utilization and performance | Week 3 Requirements Matrix |

### 3.5 Quality Management

| Use Case ID | Use Case Name | Primary Actor | Secondary Actors | Objective | Source |
|-------------|---------------|---------------|------------------|-----------|--------|
| UC-014 | Execute QC Checkpoint | QC Inspector, Wash Station Operator | System | Perform quality inspection at designated processing stages | Week 3 FR-05 |
| UC-015 | Record Quality Results | QC Inspector | System | Document pass/fail results and quality metrics | Week 3 FR-05 |
| UC-016 | Document Defects | QC Inspector | System | Record identified defects and rejection reasons | Week 3 FR-05 |

### 3.6 Packaging

| Use Case ID | Use Case Name | Primary Actor | Secondary Actors | Objective | Source |
|-------------|---------------|---------------|------------------|-----------|--------|
| UC-017 | Validate Packaging Requirements | Packaging Operator | Sales & Marketing System | Ensure packaging meets market and regulatory requirements | Week 4 Swimlane Phase 6 |
| UC-018 | Execute Packaging Process | Packaging Operator | Machinery System | Package processed products for storage and shipment | Week 3 FR-07 |
| UC-019 | Apply Lot Codes | Packaging Operator | System | Apply traceability lot codes to packaged products | Week 3 FR-07 |

### 3.7 Storage Management

| Use Case ID | Use Case Name | Primary Actor | Secondary Actors | Objective | Source |
|-------------|---------------|---------------|------------------|-----------|--------|
| UC-020 | Check Storage Capacity | System | Storage System | Verify storage availability before processing and handoff | Week 3 FR-06 |
| UC-021 | Transfer to Storage | System | Storage System | Move processed products to storage facility | Week 4 Swimlane Phase 7 |
| UC-022 | Update Inventory | System | Storage System | Update storage inventory with new product quantities | Week 3 FR-06 |

### 3.8 Commercial Readiness

| Use Case ID | Use Case Name | Primary Actor | Secondary Actors | Objective | Source |
|-------------|---------------|---------------|------------------|-----------|--------|
| UC-023 | Generate Export Documentation | System | Sales & Marketing System | Create export documentation for international shipment | Week 3 FR-08 |
| UC-024 | Validate Market Requirements | System | Sales & Marketing System | Ensure products meet target market quality and regulatory standards | Week 4 Swimlane Phase 8 |
| UC-025 | Report Product Availability | System | Sales & Marketing System | Communicate available product quantities to sales team | Week 3 Requirements Matrix |

### 3.9 Forecasting

| Use Case ID | Use Case Name | Primary Actor | Secondary Actors | Objective | Source |
|-------------|---------------|---------------|------------------|-----------|--------|
| UC-026 | Generate Harvest Forecast | Product Transformation Manager | System | Predict future harvest volumes using historical data | WEEK_4_FORECASTING_SPEC.md |
| UC-027 | Analyze Historical Data | System | Plants System | Process historical harvest data for forecasting | WEEK_4_FORECASTING_SPEC.md |

### 3.10 Reporting & Monitoring

| Use Case ID | Use Case Name | Primary Actor | Secondary Actors | Objective | Source |
|-------------|---------------|---------------|------------------|-----------|--------|
| UC-028 | View Dashboard | Product Transformation Manager | System | Monitor real-time system status and KPIs | Prototype Dashboard |
| UC-029 | Generate Production Reports | Product Transformation Manager, Compliance Auditor | System | Create production, quality, and compliance reports | Week 3 Requirements |
| UC-030 | Monitor KPIs | Product Transformation Manager | System | Track key performance indicators for process optimization | Week 3 Requirements |

---

## 04. RELATIONSHIPS

### 4.1 Include Relationships

| Including Use Case | Included Use Case | Rationale |
|---------------------|-------------------|-----------|
| UC-001 Receive Harvest Data | UC-002 Create Batch | Batch creation is mandatory after receiving harvest data |
| UC-002 Create Batch | UC-003 Validate Storage Capacity | Storage validation is mandatory before batch processing |
| UC-007 Execute Washing Process | UC-014 Execute QC Checkpoint | QC checkpoint is mandatory after washing |
| UC-009 Execute Drying Process | UC-014 Execute QC Checkpoint | QC checkpoint is mandatory after drying |
| UC-018 Execute Packaging Process | UC-017 Validate Packaging Requirements | Packaging validation is mandatory before packaging |
| UC-018 Execute Packaging Process | UC-019 Apply Lot Codes | Lot coding is mandatory part of packaging |
| UC-021 Transfer to Storage | UC-022 Update Inventory | Inventory update is mandatory after storage transfer |
| UC-026 Generate Harvest Forecast | UC-027 Analyze Historical Data | Historical analysis is mandatory for forecasting |

### 4.2 Extend Relationships

| Base Use Case | Extending Use Case | Rationale |
|---------------|-------------------|-----------|
| UC-029 Generate Production Reports | UC-006 Query Batch History | Batch history query is optional for specific report types |
| UC-030 Monitor KPIs | UC-028 View Dashboard | Dashboard viewing is optional for KPI monitoring |

### 4.3 Actor-Use Case Relationships

#### Product Transformation Manager (A-001)
- **Primary:** UC-028 View Dashboard, UC-004 Monitor Batch Status, UC-006 Query Batch History, UC-029 Generate Production Reports, UC-030 Monitor KPIs
- **Secondary:** UC-026 Generate Harvest Forecast

#### Wash Station Operator (A-002)
- **Primary:** UC-007 Execute Washing Process, UC-011 Track Water Consumption, UC-005 Update Batch Progress
- **Secondary:** UC-014 Execute QC Checkpoint

#### Drying Operator (A-003)
- **Primary:** UC-009 Execute Drying Process, UC-012 Track Energy Consumption, UC-005 Update Batch Progress
- **Secondary:** UC-013 Monitor Equipment Usage

#### QC Inspector (A-004)
- **Primary:** UC-014 Execute QC Checkpoint, UC-015 Record Quality Results, UC-016 Document Defects
- **Secondary:** UC-006 Query Batch History

#### Packaging Operator (A-005)
- **Primary:** UC-018 Execute Packaging Process, UC-019 Apply Lot Codes, UC-005 Update Batch Progress
- **Secondary:** UC-017 Validate Packaging Requirements

#### Compliance Auditor (A-006)
- **Primary:** UC-006 Query Batch History, UC-029 Generate Production Reports
- **Secondary:** UC-023 Generate Export Documentation

#### Plants Field Operator (A-007)
- **Triggers:** UC-001 Receive Harvest Data

#### External Systems (A-008 through A-013)
- **Data Providers/Consumers:** As documented in Section 2.2

---

## 05. PRECONDITIONS

### 5.1 Harvest & Batch Intake

| Use Case | Preconditions |
|----------|---------------|
| UC-001 Receive Harvest Data | Harvest event recorded in Plants system; Batch ID generated by Plants |
| UC-002 Create Batch | Harvest data received and validated |
| UC-003 Validate Storage Capacity | Storage system accessible and responsive |

### 5.2 Processing Management

| Use Case | Preconditions |
|----------|---------------|
| UC-007 Execute Washing Process | Batch created and assigned to washing station; Water availability confirmed |
| UC-008 Execute Cutting Process | Washing completed; Equipment available |
| UC-009 Execute Drying Process | Cutting completed; Energy availability confirmed |
| UC-010 Execute Cooling Process | Drying completed; Cooling equipment available |

### 5.3 Quality Management

| Use Case | Preconditions |
|----------|---------------|
| UC-014 Execute QC Checkpoint | Processing stage completed; QC criteria defined |
| UC-015 Record Quality Results | QC checkpoint executed; Results determined |
| UC-016 Document Defects | Quality defects identified; Defect classification system available |

### 5.4 Packaging & Storage

| Use Case | Preconditions |
|----------|---------------|
| UC-017 Validate Packaging Requirements | Market requirements received from Sales & Marketing |
| UC-018 Execute Packaging Process | Quality control passed; Packaging equipment available |
| UC-019 Apply Lot Codes | Packaging executed; Lot coding system configured |
| UC-020 Check Storage Capacity | Storage system accessible |
| UC-021 Transfer to Storage | Packaging completed; Storage capacity confirmed |
| UC-022 Update Inventory | Storage transfer completed |

### 5.5 Forecasting

| Use Case | Preconditions |
|----------|---------------|
| UC-026 Generate Harvest Forecast | Minimum 3 years of historical data available |
| UC-027 Analyze Historical Data | Historical harvest data received from Plants system |

---

## 06. MAIN OBJECTIVES

### 6.1 Harvest & Batch Intake

- **UC-001:** Ensure accurate and timely capture of harvest data from Plants workstream
- **UC-002:** Establish complete traceability from harvest through all processing stages
- **UC-003:** Prevent processing delays due to insufficient storage capacity

### 6.2 Batch Management

- **UC-004:** Provide real-time visibility into batch processing status
- **UC-005:** Maintain accurate batch progress records for traceability
- **UC-006:** Enable complete batch history queries for compliance and analysis

### 6.3 Processing Management

- **UC-007:** Ensure proper washing and sorting to meet quality standards
- **UC-008:** Prepare materials for drying through proper cutting
- **UC-009:** Execute core transformation (drying) with optimal energy efficiency
- **UC-010:** Ensure proper cooling to preserve product quality

### 6.4 Resource Tracking

- **UC-011:** Monitor water consumption for sustainability and cost management
- **UC-012:** Track energy consumption for solar drying optimization
- **UC-013:** Monitor equipment utilization for maintenance planning

### 6.5 Quality Management

- **UC-014:** Ensure products meet quality standards at critical control points
- **UC-015:** Maintain accurate quality records for compliance
- **UC-016:** Document defects for process improvement and root cause analysis

### 6.6 Packaging & Storage

- **UC-017:** Ensure packaging meets market and regulatory requirements
- **UC-018:** Package products efficiently for storage and transport
- **UC-019:** Apply traceability lot codes for recall capability
- **UC-020:** Prevent storage overflow and capacity issues
- **UC-021:** Ensure smooth product handoff to storage
- **UC-022:** Maintain accurate inventory records

### 6.7 Commercial Readiness

- **UC-023:** Generate required export documentation for international shipment
- **UC-024:** Ensure products meet target market requirements
- **UC-025:** Enable sales team to plan based on product availability

### 6.8 Forecasting

- **UC-026:** Provide reliable harvest volume forecasts for capacity planning
- **UC-027:** Process historical data to support forecasting methodology

### 6.9 Reporting & Monitoring

- **UC-028:** Provide real-time system visibility for management
- **UC-029:** Generate required reports for compliance and decision-making
- **UC-030:** Monitor KPIs for continuous process improvement

---

## 07. REQUIREMENT TRACEABILITY

### 7.1 Functional Requirements Mapping

| Requirement ID | Requirement | Use Cases | Status |
|----------------|-------------|-----------|--------|
| FR-01 | Raw material intake tracking | UC-001, UC-002, UC-003 | ✅ Mapped |
| FR-02 | Batch creation and traceability | UC-002, UC-004, UC-005, UC-006 | ✅ Mapped |
| FR-03 | Process stage recording | UC-007, UC-008, UC-009, UC-010, UC-018 | ✅ Mapped |
| FR-04 | Resource consumption tracking | UC-011, UC-012, UC-013 | ✅ Mapped |
| FR-05 | Quality control checkpoints | UC-014, UC-015, UC-016 | ✅ Mapped |
| FR-06 | Storage integration | UC-020, UC-021, UC-022 | ✅ Mapped |
| FR-07 | Packaging and lot coding | UC-017, UC-018, UC-019 | ✅ Mapped |
| FR-08 | Export readiness documentation | UC-023, UC-024 | ✅ Mapped |
| FR-09 | Historical data analysis | UC-026, UC-027 | ✅ Mapped |

### 7.2 Non-Functional Requirements Mapping

| Requirement Category | Requirement | Use Cases | Status |
|----------------------|-------------|-----------|--------|
| Availability | System uptime ≥ 99% during processing hours | All operational use cases | ✅ Addressed |
| Interoperability | API-based data exchange with all workstreams | UC-001, UC-011, UC-012, UC-020, UC-021, UC-023, UC-025 | ✅ Addressed |
| Scalability | Support 100+ concurrent batches | UC-004, UC-005 | ✅ Addressed |
| Security | Role-based access control | All actor-specific use cases | ✅ Addressed |
| Auditability | Complete audit trail for compliance | UC-005, UC-015, UC-016, UC-029 | ✅ Addressed |

### 7.3 Cross-Functional Dependencies Mapping

| Dependency | Use Cases | Status |
|------------|-----------|--------|
| Plants → Product Transformation | UC-001, UC-002 | ✅ Mapped |
| Product Transformation → Water | UC-003, UC-011 | ✅ Mapped |
| Product Transformation → Energy | UC-009, UC-012 | ✅ Mapped |
| Product Transformation → Machinery | UC-007, UC-008, UC-009, UC-013, UC-018 | ✅ Mapped |
| Product Transformation → Storage | UC-003, UC-020, UC-021, UC-022 | ✅ Mapped |
| Product Transformation → Sales & Marketing | UC-017, UC-023, UC-024, UC-025 | ✅ Mapped |

---

## 08. ASSUMPTIONS

### 8.1 Business Process Assumptions

| ID | Assumption | Impact | Validation Required |
|----|------------|--------|---------------------|
| UC-A-001 | Harvest data is provided automatically via Plants integration | Reduces manual data entry risk | Validate with Plants team |
| UC-A-002 | QC checkpoints are mandatory after washing and drying | Ensures quality control coverage | Validate with quality team |
| UC-A-003 | Storage capacity must be validated before batch creation | Prevents processing bottlenecks | Validate with Storage team |
| UC-A-004 | Lot coding is mandatory part of packaging process | Ensures traceability compliance | Validate with Sales & Marketing team |
| UC-A-005 | Historical data for forecasting is available for minimum 3 years | Enables forecasting methodology | Validate with Plants team |

### 8.2 Technical Assumptions

| ID | Assumption | Impact | Validation Required |
|----|------------|--------|---------------------|
| UC-T-001 | REST API integration with Plants for MVP | Simpler implementation for Week 4 | Validate with architecture team |
| UC-T-002 | Message Queue integration for production | Scalable architecture for future | Week 5-6 decision |
| UC-T-003 | Real-time dashboard updates are technically feasible | Enables management visibility | Validate with infrastructure team |
| UC-T-004 | Role-based access control can be implemented at use case level | Ensures security compliance | Validate with security team |

### 8.3 Data Assumptions

| ID | Assumption | Impact | Validation Required |
|----|------------|--------|---------------------|
| UC-D-001 | Batch ID is unique and generated by Plants system | Ensures traceability | Validate with Plants team |
| UC-D-002 | Quality grade classification uses standard enum (A, B, C, D) | Standardizes quality assessment | Validate with Plants team |
| UC-D-003 | Historical harvest data includes year, month, week, variety, quantity | Enables accurate forecasting | Validate data structure |
| UC-D-004 | Equipment capacity data is static and available | Enables resource planning | Validate with Machinery team |

---

## 09. UML NOTATION USED

### 9.1 Actor Notation

- **Stick figure** for human actors
- **System boundary** for external systems
- **Actor name** below stick figure/system box

### 9.2 Use Case Notation

- **Oval** for use cases
- **Verb-based naming** (e.g., "Execute Washing Process" not "Washing")
- **Grouping** into packages by functional area

### 9.3 Relationship Notation

- **Solid line with arrow** for association (actor → use case)
- **<<include>>** for mandatory inclusion
- **<<extend>>** for optional extension
- **<<primary>>** for primary actor relationships
- **<<secondary>>** for secondary actor relationships
- **<<triggers>>** for actor that initiates process
- **<<provides data>>** for system data provider
- **<<receives usage>>** for system data consumer

### 9.4 Package Notation

- **Rectangle with tab** for package grouping
- **Functional area labels** for organization
- **Logical grouping** of related use cases

---

## 10. CONSISTENCY WITH WEEK 04 TERMINOLOGY

### 10.1 Terminology Alignment

| Week 04 Term | Use Case Term | Status |
|--------------|---------------|--------|
| Harvest | Harvest & Batch Intake package, UC-001 | ✅ Aligned |
| Processing | Processing Management package | ✅ Aligned |
| Batches | Batch Management package | ✅ Aligned |
| Equipment | Referenced in use cases (not as actor) | ✅ Aligned |
| Energy & Water | Resource Tracking package | ✅ Aligned |
| Processed Products | Commercial Readiness context | ✅ Aligned |
| Processing Efficiency | Monitored via KPIs | ✅ Aligned |

### 10.2 Actor Naming Consistency

| Audit Actor | Use Case Actor | Status |
|-------------|---------------|--------|
| Product Transformation Manager | Product Transformation Manager | ✅ Consistent |
| Wash Station Operator | Wash Station Operator | ✅ Consistent |
| Drying Operator | Drying Operator | ✅ Consistent |
| QC Inspector | QC Inspector | ✅ Consistent |
| Packaging Operator | Packaging Operator | ✅ Consistent |
| Compliance Auditor | Compliance Auditor | ✅ Consistent |

---

## 11. DIAGRAM QUALITY CHARACTERISTICS

### 11.1 Readability

- **Clear separation** between human actors, external systems, and use cases
- **Logical grouping** of use cases into functional packages
- **Minimal line crossing** through careful actor placement
- **Adequate white space** for visual clarity

### 11.2 Completeness

- **All FR-01 through FR-09** mapped to use cases
- **All 6 cross-functional dependencies** represented
- **All 16 actors** from audit included and justified
- **Core business processes** from swimlane represented

### 11.3 Correctness

- **UML notation** follows standard conventions
- **Include/extend relationships** used appropriately
- **Actor relationships** clearly labeled with purpose
- **No use case incorrectly modeled as actor**

### 11.4 Traceability

- **Every use case** traces to Week 3/4 requirements
- **Every actor** traces to audit or requirements matrix
- **Every relationship** traces to swimlane or integration spec
- **Every assumption** documented with validation requirement

---

## 12. NEXT STEPS

### 12.1 Immediate Actions

1. ✅ Use Case Diagram created
2. ✅ Use Case Specification documented
3. ⏸️ Create Use Case Validation document
4. ⏸️ Proceed to Activity Diagram after validation

### 12.2 Pending Actions

- Validate use case model against requirements
- Create Activity Diagram based on validated swimlane
- Perform UML Foundation consistency check
- Generate Phase 2 completion report

---

**END OF USE CASE SPECIFICATION**

**Status:** ✅ COMPLETE - Awaiting Validation