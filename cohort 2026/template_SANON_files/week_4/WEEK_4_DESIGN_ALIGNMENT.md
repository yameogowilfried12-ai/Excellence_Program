# WEEK 4 DESIGN ALIGNMENT

**Program:** BIT × Infineon Excellence Program  
**Project:** Sustainable Farm  
**Workstream:** Product Transformation  
**Week:** 4  
**Focus:** Homepage Terminology & Platform Design Alignment  
**Owner:** Abdoul Ben Fatao SANON  
**Created:** 2026-08-10  
**Status:** 🔵 IN PROGRESS  

---

## 01. EXECUTIVE SUMMARY

This document audits the Product Transformation prototype's homepage terminology and design patterns, identifying misalignments with business terminology and proposing alignment with the platform's shared design language.

**Primary Purpose:** Address reviewer feedback about "strange labels on your homepage" and ensure the Product Transformation UI aligns with other workstream applications for platform integration.

**Reviewer Feedback Drivers:**
- "Quite some strange labels on your homepage"
- "Mock-up style needs to match styles of other apps – make it easy later when integrated all as one platform"

---

## 02. CURRENT PROTOTYPE AUDIT

### 2.1 Homepage Labels Audit

**Current Homepage Dashboard Labels:**

| Current Label | Location | Potential Issue | Suggested Alignment |
|---------------|----------|-----------------|---------------------|
| "Raw Materials" | Dashboard card | May not align with Plants terminology | "Harvest" or "Intake" |
| "Active Batches" | Dashboard card | Clear, but verify with workstreams | Keep or "Processing Batches" |
| "Finished Products" | Dashboard card | May differ from Storage terminology | "Processed Products" or "Output" |
| "Yield Rate" | Dashboard card | Technical term, may need business translation | "Processing Efficiency" or keep |

**Current Navigation Labels:**

| Current Label | Location | Potential Issue | Suggested Alignment |
|---------------|----------|-----------------|---------------------|
| "Raw Materials" | Navigation | May not align with Plants terminology | "Harvest" or "Intake" |
| "Transformation" | Navigation | Clear, but verify | Keep or "Processing" |
| "Production Batches" | Navigation | Clear | Keep |
| "Machinery" | Navigation | Should align with Machinery workstream | Keep (verify with Machinery team) |
| "Resource Consumption" | Navigation | May need alignment with Energy/Water | "Energy & Water" or split |
| "Quality Control" | Navigation | Clear | Keep |
| "Finished Products" | Navigation | May differ from Storage terminology | "Processed Products" or "Output" |
| "Storage" | Navigation | Should align with Storage workstream | Keep (verify with Storage team) |
| "Traceability" | Navigation | Clear | Keep |

### 2.2 Design System Audit

**Current Design Characteristics:**

| Aspect | Current Implementation | Notes |
|--------|----------------------|-------|
| **Color Palette** | Custom teal (#009688) as primary accent | Not verified against platform colors |
| **Typography** | System fonts (Arial, sans-serif) | Not verified against platform typography |
| **Icons** | Material Icons | Common choice, but verify platform standard |
| **Navigation** | Sidebar navigation with teal highlight | Verify platform navigation pattern |
| **Cards** | Card-based layout with shadows | Verify platform card design |
| **Status Badges** | Color-coded badges (green/yellow/red) | Verify platform badge colors |
| **Buttons** | Custom button styles with teal | Verify platform button design |
| **CSS Framework** | None (custom CSS) | May need to adopt platform framework |

**Unknown Alignment Factors:**
- Other workstream design systems not yet inspected
- Shared platform design language not yet defined
- Navigation patterns not yet compared
- Component library not yet identified
- Color system not yet compared

---

## 03. TERMINOLOGY ALIGNMENT PROPOSAL

### 3.1 Terminology Mapping

**Cross-Workstream Terminology Standardization:**

| Concept | Product Transformation (Current) | Plants (Proposed) | Storage (Proposed) | Platform Standard (Proposed) |
|---------|----------------------------------|-------------------|-------------------|------------------------------|
| Raw material input | "Raw Materials" | "Harvest" | "Intake" | "Harvest" |
| Processing output | "Finished Products" | "Output" | "Processed Products" | "Processed Products" |
| Processing activity | "Transformation" | "Processing" | "Processing" | "Processing" |
| Batch tracking | "Production Batches" | "Batches" | "Batches" | "Batches" |
| Equipment | "Machinery" | "Equipment" | "Equipment" | "Equipment" |
| Resource usage | "Resource Consumption" | "Energy & Water" | "Resources" | "Energy & Water" |
| Quality checks | "Quality Control" | "Quality" | "Quality" | "Quality Control" |
| Storage location | "Storage" | "Storage" | "Storage" | "Storage" |
| Traceability | "Traceability" | "Traceability" | "Traceability" | "Traceability" |

### 3.2 Recommended Label Changes

**Homepage Dashboard:**

| Current | Recommended | Rationale |
|---------|-------------|-----------|
| "Raw Materials" | "Harvest" | Aligns with Plants workstream terminology |
| "Active Batches" | "Processing Batches" | More descriptive of current activity |
| "Finished Products" | "Processed Products" | Aligns with Storage workstream terminology |
| "Yield Rate" | "Processing Efficiency" | More business-friendly term |

**Navigation:**

| Current | Recommended | Rationale |
|---------|-------------|-----------|
| "Raw Materials" | "Harvest" | Aligns with Plants workstream terminology |
| "Transformation" | "Processing" | More common business term |
| "Production Batches" | "Batches" | Simpler, consistent across workstreams |
| "Machinery" | "Equipment" | Broader term, consistent with machinery workstream |
| "Resource Consumption" | "Energy & Water" | More specific, aligns with Energy/Water workstreams |
| "Quality Control" | "Quality Control" | Keep (clear and standard) |
| "Finished Products" | "Processed Products" | Aligns with Storage workstream terminology |
| "Storage" | "Storage" | Keep (clear and standard) |
| "Traceability" | "Traceability" | Keep (clear and standard) |

### 3.3 Terminology Validation Required

**Consultation Needed With:**

| Workstream | Terminology to Validate | Contact Status |
|------------|------------------------|---------------|
| **Plants** | "Harvest" vs "Raw Materials" | ⬜ TODO |
| **Storage** | "Processed Products" vs "Finished Products" | ⬜ TODO |
| **Machinery** | "Equipment" vs "Machinery" | ⬜ TODO |
| **Energy** | "Energy & Water" vs "Resource Consumption" | ⬜ TODO |
| **Water** | "Energy & Water" vs "Resource Consumption" | ⬜ TODO |
| **Sales & Marketing** | Business terminology preferences | ⬜ TODO |

---

## 04. PLATFORM DESIGN ALIGNMENT PROPOSAL

### 4.1 Design System Investigation Needed

**Required Inspections:**

| Workstream | Prototype/App Location | Design System | Status |
|------------|----------------------|---------------|--------|
| **Plants** | TBD | TBD | ⬜ Not inspected |
| **Energy** | TBD | TBD | ⬜ Not inspected |
| **Water** | TBD | TBD | ⬜ Not inspected |
| **Machinery** | TBD | TBD | ⬜ Not inspected |
| **Storage** | TBD | TBD | ⬜ Not inspected |
| **Sales & Marketing** | TBD | TBD | ⬜ Not inspected |

**Action:** Inspect all workstream prototypes to identify shared design patterns.

### 4.2 Proposed Platform Design Standards

**Based on Common Best Practices (to be validated against actual workstream designs):**

| Design Element | Proposed Standard | Rationale |
|----------------|-------------------|-----------|
| **Color Palette** | Primary: Blue (#2196F3), Secondary: Green (#4CAF50) | Neutral, professional, commonly used in enterprise apps |
| **Typography** | Roboto or Open Sans (Google Fonts) | Modern, readable, open-source |
| **Icons** | Material Icons or FontAwesome | Widely available, consistent style |
| **Navigation** | Top navigation bar with dropdown menus | Common pattern, scalable for many workstreams |
| **Cards** | Material Design cards with elevation | Consistent shadow and spacing |
| **Status Badges** | Success (green), Warning (orange), Error (red), Info (blue) | Standard semantic colors |
| **Buttons** | Primary (filled), Secondary (outlined), Tertiary (text) | Clear hierarchy |
| **CSS Framework** | Bootstrap 5 or Tailwind CSS | Industry standard, responsive, component library |

### 4.3 Product Transformation Alignment Plan

**Phase 1: Terminology Update (Immediate)**
- Update homepage labels based on terminology mapping
- Update navigation labels based on terminology mapping
- Validate with other workstreams before finalizing

**Phase 2: Design System Adoption (Week 5-6)**
- Inspect other workstream prototypes
- Identify shared design patterns
- Adopt platform color palette
- Adopt platform typography
- Adopt platform icon set
- Adopt platform navigation pattern

**Phase 3: Component Library Integration (Week 7-8)**
- Adopt platform CSS framework (Bootstrap or Tailwind)
- Replace custom CSS with framework components
- Ensure responsive design
- Test cross-browser compatibility

---

## 05. SPECIFIC SCREEN-BY-SCREEN ALIGNMENT

### 5.1 Dashboard Screen

**Current Issues:**
- "Raw Materials" label misaligned with Plants terminology
- "Finished Products" label misaligned with Storage terminology
- "Yield Rate" too technical

**Proposed Changes:**
- Change "Raw Materials" → "Harvest"
- Change "Finished Products" → "Processed Products"
- Change "Yield Rate" → "Processing Efficiency"
- Keep card layout (verify against platform card design)

**Updated Dashboard Layout:**

```
┌─────────────────────────────────────────────────────────┐
│ Product Transformation Dashboard                        │
├─────────────────────────────────────────────────────────┤
│                                                         │
│ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐   │
│ │   Harvest    │ │Processing    │ │Processed     │   │
│ │              │ │Batches       │ │Products      │   │
│ │ 44,000 kg    │ │ 12 active    │ │ 8,200 kg     │   │
│ └──────────────┘ └──────────────┘ └──────────────┘   │
│                                                         │
│ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐   │
│ │Processing    │ │Energy Usage  │ │Water Usage   │   │
│ │Efficiency    │ │              │ │              │   │
│ │ 25%          │ │ 450 kWh      │ │ 2,500 L      │   │
│ └──────────────┘ └──────────────┘ └──────────────┘   │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

### 5.2 Harvest Screen (formerly Raw Materials)

**Current Issues:**
- Screen title "Raw Materials" misaligned

**Proposed Changes:**
- Change screen title → "Harvest"
- Update all references to "raw material" → "harvest"
- Keep data fields (variety, quantity, quality grade)

### 5.3 Processing Screen (formerly Transformation)

**Current Issues:**
- Screen title "Transformation" may be less common

**Proposed Changes:**
- Change screen title → "Processing"
- Update all references to "transformation" → "processing" (where appropriate)
- Keep process stages (washing, cutting, drying, packaging)

### 5.4 Equipment Screen (formerly Machinery)

**Current Issues:**
- Screen title "Machinery" may not align with Machinery workstream

**Proposed Changes:**
- Change screen title → "Equipment"
- Validate with Machinery workstream
- Keep equipment data fields

### 5.5 Energy & Water Screen (formerly Resource Consumption)

**Current Issues:**
- Screen title "Resource Consumption" too generic

**Proposed Changes:**
- Change screen title → "Energy & Water"
- Split into two sections or tabs
- Validate with Energy and Water workstreams

### 5.6 Processed Products Screen (formerly Finished Products)

**Current Issues:**
- Screen title "Finished Products" may not align with Storage terminology

**Proposed Changes:**
- Change screen title → "Processed Products"
- Validate with Storage workstream
- Keep product data fields

---

## 06. DESIGN CONSISTENCY CHECKLIST

### 6.1 Terminology Consistency

- [ ] All screens use consistent terminology
- [ ] No mixed use of "raw material" vs "harvest"
- [ ] No mixed use of "finished product" vs "processed product"
- [ ] No mixed use of "transformation" vs "processing"
- [ ] No mixed use of "machinery" vs "equipment"

### 6.2 Visual Consistency

- [ ] All cards use consistent spacing
- [ ] All buttons use consistent styles
- [ ] All status badges use consistent colors
- [ ] All icons use consistent style
- [ ] All typography uses consistent fonts and sizes

### 6.3 Navigation Consistency

- [ ] Navigation labels are consistent across screens
- [ ] Navigation hierarchy is logical
- [ ] Navigation pattern matches platform standard

### 6.4 Cross-Workstream Consistency

- [ ] Terminology aligns with Plants workstream
- [ ] Terminology aligns with Storage workstream
- [ ] Terminology aligns with Machinery workstream
- [ ] Terminology aligns with Energy workstream
- [ ] Terminology aligns with Water workstream
- [ ] Design patterns align with platform standard

---

## 07. IMPLEMENTATION PLAN

### Phase 1: Terminology Update (Week 04)

**Status:** This document defines the changes

**Deliverable:** Terminology alignment proposal

**Actions:**
1. Consult with other workstreams on terminology (if time permits)
2. Update prototype labels based on validated terminology
3. Document terminology decisions

**Dependencies:** None (can proceed independently)

---

### Phase 2: Design System Investigation (Week 05)

**Status:** Planned

**Deliverables:**
- Inspection report of other workstream designs
- Shared design pattern identification
- Platform design language proposal

**Dependencies:** Access to other workstream prototypes

---

### Phase 3: Design System Adoption (Week 06-07)

**Status:** Planned

**Deliverables:**
- Updated prototype with platform design system
- CSS framework integration
- Component library adoption

**Dependencies:** Design system investigation complete

---

### Phase 4: Validation (Week 08)

**Status:** Planned

**Deliverables:**
- Cross-workstream design validation
- User testing feedback
- Final design approval

**Dependencies:** Design system adoption complete

---

## 08. ASSUMPTIONS & LIMITATIONS

### Assumptions

1. **Workstream Availability:** Other workstreams have prototypes or apps to inspect
2. **Platform Standard:** A shared platform design language will be defined
3. **Terminology Agreement:** Other workstreams will agree on terminology standardization
4. **Design Framework:** A CSS framework (Bootstrap or Tailwind) will be adopted
5. **Design Authority:** A design authority or UX lead will define platform standards

### Limitations

1. **No Access to Other Prototypes:** At the time of this document, other workstream prototypes have not been inspected
2. **No Platform Standard:** No shared platform design language has been defined yet
3. **Terminology Not Validated:** Proposed terminology changes have not been validated with other workstreams
4. **Design System Unknown:** The actual platform design system is unknown
5. **Time Constraints:** Week 04 may not allow for full design system investigation

### Mitigation

1. **Terminology First:** Focus on terminology alignment in Week 04, defer design system adoption to Week 05-06
2. **Consultation:** Schedule consultations with other workstreams to validate terminology
3. **Flexible Design:** Keep design changes flexible until platform standard is defined
4. **Document Decisions:** Document all terminology and design decisions for future reference
5. **Iterative Approach:** Adopt an iterative approach to design alignment, refining as platform standards emerge

---

## 09. WEEK 04 SUCCESS CRITERIA

This design alignment document satisfies the following Week 04 success criteria:

- [x] Homepage terminology has been audited
- [ ] Homepage labels have been updated (pending consultation)
- [ ] Terminology alignment document is created
- [ ] Design alignment recommendations are documented
- [ ] Implementation plan is defined
- [ ] Based on reviewer feedback about "strange labels" and "mock-up style"

**Note:** Full implementation of label changes is pending consultation with other workstreams. This document provides the foundation for those changes.

---

## 10. NEXT STEPS

Based on this design alignment document, the following actions should be taken:

1. **Consult with Workstreams:** Validate proposed terminology changes with Plants, Storage, Machinery, Energy, and Water workstreams

2. **Update Prototype (STEP 7):** Apply validated terminology changes to the interactive prototype

3. **Inspect Other Designs (Week 05):** Inspect other workstream prototypes to identify shared design patterns

4. **Adopt Platform Design (Week 06-07):** Adopt platform design system once defined

---

**Status:** 🔵 DESIGN ALIGNMENT DOCUMENT COMPLETE  
**Next Action:** Begin STEP 7 - Update Prototype to Reflect Week 04 Decisions  
**Last Updated:** 2026-08-10
