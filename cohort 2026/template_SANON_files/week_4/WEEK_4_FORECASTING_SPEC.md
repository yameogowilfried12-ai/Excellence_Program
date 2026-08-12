# WEEK 4 FORECASTING SPECIFICATION

**Program:** BIT × Infineon Excellence Program  
**Project:** Sustainable Farm  
**Workstream:** Product Transformation  
**Week:** 4  
**Focus:** Historical Harvest Forecasting  
**Owner:** Abdoul Ben Fatao SANON  
**Created:** 2026-08-10  
**Status:** 🔵 IN PROGRESS  

---

## 01. EXECUTIVE SUMMARY

This specification defines a transparent baseline forecasting method for predicting mango harvest volumes based on historical data, enabling Product Transformation to plan processing capacity and resource allocation.

**Primary Purpose:** Provide a simple, explainable forecast of expected harvest volumes to support transformation planning and resource scheduling.

**Reviewer Feedback Driver:** "Make a forecast based on historical values."

**Design Principle:** "The initial model should prioritize: Transparency, Simplicity, Explainability, Reproducibility."

---

## 02. FORECASTING OBJECTIVES

### 2.1 Business Objectives

1. **Capacity Planning:** Enable Product Transformation to plan drying capacity based on expected harvest
2. **Resource Allocation:** Enable efficient scheduling of energy, water, and machinery resources
3. **Storage Planning:** Enable Storage team to plan capacity for raw materials and finished goods
4. **Sales Planning:** Enable Sales & Marketing to forecast product availability for market commitments
5. **Sustainability Optimization:** Enable optimal scheduling of solar drying based on harvest timing

### 2.2 Technical Objectives

1. **Transparency:** Forecast method must be easily understandable by non-technical stakeholders
2. **Simplicity:** Forecast method must be implementable with basic statistical methods
3. **Explainability:** Forecast results must be explainable with clear rationale
4. **Reproducibility:** Forecast results must be reproducible given the same inputs
5. **Accuracy:** Forecast should achieve reasonable accuracy for planning purposes

---

## 03. HISTORICAL DATA STRUCTURE

### 3.1 Historical Harvest Data Schema

**Entity Name:** `HISTORICAL_HARVEST`  
**Source:** Plants workstream (via integration specification)  
**Retention:** 7 years (minimum for meaningful forecasting)

| Field Name | Data Type | Format | Description | Example |
|------------|-----------|--------|-------------|---------|
| `year` | Integer | YYYY | Calendar year | 2023 |
| `month` | Integer | 1-12 | Calendar month | 6 |
| `week` | Integer | 1-52 | Week number | 24 |
| `mango_variety` | String | Enum | Mango variety | "KEITT" |
| `farm_id` | String | Code | Farm identifier | "F-01" |
| `harvest_quantity_kg` | Number | Decimal | Total harvest quantity | 42000.0 |
| `quality_grade_a_pct` | Number | Percentage | Percentage of Grade A | 65.0 |
| `quality_grade_b_pct` | Number | Percentage | Percentage of Grade B | 25.0 |
| `quality_grade_c_pct` | Number | Percentage | Percentage of Grade C | 10.0 |
| `weather_condition` | String | Text | Weather summary | "Normal" |
| `rainfall_mm` | Number | Decimal | Total rainfall in mm | 120.5 |
| `temperature_avg_c` | Number | Decimal | Average temperature in °C | 28.5 |

### 3.2 Sample Historical Data

**Year 2021 (Historical):**

| Month | Week | Variety | Quantity (kg) | Grade A % | Grade B % | Grade C % |
|-------|------|---------|---------------|-----------|-----------|-----------|
| 6 | 24 | KEITT | 38000 | 60 | 30 | 10 |
| 6 | 25 | KEITT | 42000 | 65 | 25 | 10 |
| 6 | 26 | KEITT | 45000 | 70 | 20 | 10 |
| 7 | 27 | KEITT | 48000 | 68 | 22 | 10 |
| 7 | 28 | KEITT | 46000 | 65 | 25 | 10 |
| 7 | 29 | KEITT | 44000 | 62 | 28 | 10 |
| 8 | 30 | KEITT | 40000 | 60 | 30 | 10 |
| 8 | 31 | KEITT | 38000 | 58 | 32 | 10 |
| 8 | 32 | KEITT | 35000 | 55 | 35 | 10 |
| **Total** | | | **376,000** | **62.3** | **27.7** | **10.0** |

**Year 2022 (Historical):**

| Month | Week | Variety | Quantity (kg) | Grade A % | Grade B % | Grade C % |
|-------|------|---------|---------------|-----------|-----------|-----------|
| 6 | 24 | KEITT | 40000 | 62 | 28 | 10 |
| 6 | 25 | KEITT | 44000 | 66 | 24 | 10 |
| 6 | 26 | KEITT | 47000 | 70 | 20 | 10 |
| 7 | 27 | KEITT | 50000 | 68 | 22 | 10 |
| 7 | 28 | KEITT | 48000 | 65 | 25 | 10 |
| 7 | 29 | KEITT | 46000 | 63 | 27 | 10 |
| 8 | 30 | KEITT | 42000 | 60 | 30 | 10 |
| 8 | 31 | KEITT | 40000 | 58 | 32 | 10 |
| 8 | 32 | KEITT | 37000 | 55 | 35 | 10 |
| **Total** | | | **394,000** | **63.0** | **27.0** | **10.0** |

**Year 2023 (Historical):**

| Month | Week | Variety | Quantity (kg) | Grade A % | Grade B % | Grade C % |
|-------|------|---------|---------------|-----------|-----------|-----------|
| 6 | 24 | KEITT | 42000 | 64 | 26 | 10 |
| 6 | 25 | KEITT | 46000 | 68 | 22 | 10 |
| 6 | 26 | KEITT | 49000 | 72 | 18 | 10 |
| 7 | 27 | KEITT | 52000 | 70 | 20 | 10 |
| 7 | 28 | KEITT | 50000 | 67 | 23 | 10 |
| 7 | 29 | KEITT | 48000 | 65 | 25 | 10 |
| 8 | 30 | KEITT | 44000 | 62 | 28 | 10 |
| 8 | 31 | KEITT | 42000 | 60 | 30 | 10 |
| 8 | 32 | KEITT | 39000 | 57 | 33 | 10 |
| **Total** | | | **412,000** | **63.8** | **26.2** | **10.0** |

---

## 04. BASELINE FORECASTING METHOD

### 4.1 Method Selection

**Selected Method:** 3-Year Simple Moving Average with Seasonal Adjustment

**Rationale:**
- **Transparency:** Moving average is easily understood by all stakeholders
- **Simplicity:** Requires only basic arithmetic, no complex algorithms
- **Explainability:** Forecast is the average of the last 3 years for the same week
- **Reproducibility:** Same inputs always produce the same output
- **Accuracy:** Sufficient for planning purposes given the stability of mango production

**Alternative Methods Considered:**
- Linear Regression: More complex, assumes linear trend which may not hold
- Exponential Smoothing: More complex, harder to explain
- ARIMA: Too complex for baseline, requires statistical expertise
- Machine Learning: Premature for Week 04, violates "no premature AI/ML complexity" principle

### 4.2 Forecasting Algorithm

**Step 1: Calculate 3-Year Moving Average**

For each week of the harvest season:

```
Forecast_Quantity(Year, Week, Variety) = 
    (Quantity(Year-3, Week, Variety) + 
     Quantity(Year-2, Week, Variety) + 
     Quantity(Year-1, Week, Variety)) / 3
```

**Step 2: Apply Seasonal Adjustment Factor**

```
Seasonal_Factor = 
    Total_Quantity(Year-1) / Total_Quantity(Year-2)

Adjusted_Forecast = Forecast_Quantity × Seasonal_Factor
```

**Step 3: Apply Quality Grade Distribution**

```
Forecast_Grade_A = Adjusted_Forecast × Avg_Grade_A_Percentage
Forecast_Grade_B = Adjusted_Forecast × Avg_Grade_B_Percentage
Forecast_Grade_C = Adjusted_Forecast × Avg_Grade_C_Percentage
```

### 4.3 2026 Forecast Calculation

**Input Data (Years 2021-2023):**

| Week | 2021 (kg) | 2022 (kg) | 2023 (kg) | 3-Year Avg | Seasonal Factor | 2026 Forecast |
|------|-----------|-----------|-----------|------------|-----------------|----------------|
| 24 | 38000 | 40000 | 42000 | 40000 | 1.046 | 41840 |
| 25 | 42000 | 44000 | 46000 | 44000 | 1.046 | 46024 |
| 26 | 45000 | 47000 | 49000 | 47000 | 1.046 | 49162 |
| 27 | 48000 | 50000 | 52000 | 50000 | 1.046 | 52300 |
| 28 | 46000 | 48000 | 50000 | 48000 | 1.046 | 50208 |
| 29 | 44000 | 46000 | 48000 | 46000 | 1.046 | 48116 |
| 30 | 40000 | 42000 | 44000 | 42000 | 1.046 | 43932 |
| 31 | 38000 | 40000 | 42000 | 40000 | 1.046 | 41840 |
| 32 | 35000 | 37000 | 39000 | 37000 | 1.046 | 38702 |
| **Total** | **376,000** | **394,000** | **412,000** | **394,000** | **1.046** | **412,124** |

**Seasonal Factor Calculation:**
- 2023 Total: 412,000 kg
- 2022 Total: 394,000 kg
- Seasonal Factor: 412,000 / 394,000 = 1.046 (4.6% growth)

**Quality Grade Distribution (3-Year Average):**
- Grade A: 63.0%
- Grade B: 27.0%
- Grade C: 10.0%

**2026 Forecast by Quality Grade:**

| Week | Total Forecast | Grade A (kg) | Grade B (kg) | Grade C (kg) |
|------|----------------|--------------|--------------|--------------|
| 24 | 41840 | 26359 | 11297 | 4184 |
| 25 | 46024 | 28975 | 12426 | 4602 |
| 26 | 49162 | 30972 | 13274 | 4916 |
| 27 | 52300 | 32949 | 14121 | 5230 |
| 28 | 50208 | 31631 | 13556 | 5021 |
| 29 | 48116 | 30313 | 12991 | 4812 |
| 30 | 43932 | 27677 | 11862 | 4393 |
| 31 | 41840 | 26359 | 11297 | 4184 |
| 32 | 38702 | 24382 | 10450 | 3870 |
| **Total** | **412,124** | **259,707** | **111,274** | **41,142** |

---

## 05. FORECAST → TRANSFORMATION PLANNING

### 5.1 Transformation Capacity Planning

**Drying Capacity Assumptions:**
- Solar Dryer A Capacity: 500 kg per batch
- Drying Duration: 18 hours per batch
- Batches per Day: 1 (solar drying limited to daylight hours)
- Drying Days per Week: 6 days (Sunday rest)
- Weekly Drying Capacity: 3,000 kg (500 kg × 6 days)

**2026 Weekly Processing Plan:**

| Week | Harvest Forecast (kg) | Drying Capacity (kg) | Capacity Utilization | Action Required |
|------|----------------------|----------------------|---------------------|-----------------|
| 24 | 41840 | 18000 | 232% | Add capacity or delay processing |
| 25 | 46024 | 18000 | 256% | Add capacity or delay processing |
| 26 | 49162 | 18000 | 273% | Add capacity or delay processing |
| 27 | 52300 | 18000 | 291% | Add capacity or delay processing |
| 28 | 50208 | 18000 | 279% | Add capacity or delay processing |
| 29 | 48116 | 18000 | 267% | Add capacity or delay processing |
| 30 | 43932 | 18000 | 244% | Add capacity or delay processing |
| 31 | 41840 | 18000 | 232% | Add capacity or delay processing |
| 32 | 38702 | 18000 | 215% | Add capacity or delay processing |

**Capacity Gap Analysis:**
- Peak Week 27: 52,300 kg forecast vs 18,000 kg capacity
- Gap: 34,300 kg (191% over capacity)
- **Recommendation:** Add 2 additional solar dryers (500 kg each) to reach 30,000 kg weekly capacity
- **Remaining Gap:** 22,300 kg → Use mechanical drying as backup or extend drying season

### 5.2 Resource Planning

**Energy Planning (Week 27 Peak):**

| Resource | Requirement | Availability | Gap |
|----------|-------------|-------------|-----|
| Solar Energy | 90 kWh per batch × 60 batches = 5,400 kWh | 5,000 kWh (peak) | 400 kWh |
| Grid Energy | Backup for 400 kWh | Available | ✓ |

**Water Planning (Week 27 Peak):**

| Resource | Requirement | Availability | Gap |
|----------|-------------|-------------|-----|
| Washing Water | 500 L per batch × 60 batches = 30,000 L | 35,000 L/day | ✓ |
| Cleaning Water | 100 L per batch × 60 batches = 6,000 L | 10,000 L/day | ✓ |

**Machinery Planning:**

| Equipment | Required | Available | Gap |
|-----------|----------|-----------|-----|
| Washing Stations | 2 | 2 | ✓ |
| Slicing Machines | 3 | 2 | 1 |
| Solar Dryers | 3 | 1 | 2 |
| Packaging Machines | 2 | 1 | 1 |

### 5.3 Storage Planning

**Raw Material Storage:**

| Week | Harvest Forecast (kg) | Processing Capacity (kg) | Accumulation (kg) | Storage Required |
|------|----------------------|-------------------------|-------------------|-------------------|
| 24 | 41840 | 18000 | +23840 | 23,840 |
| 25 | 46024 | 18000 | +51864 | 51,864 |
| 26 | 49162 | 18000 | +83026 | 83,026 |
| 27 | 52300 | 18000 | +117326 | 117,326 |
| 28 | 50208 | 18000 | +149534 | 149,534 |
| 29 | 48116 | 18000 | +179650 | 179,650 |
| 30 | 43932 | 18000 | +205582 | 205,582 |
| 31 | 41840 | 18000 | +229422 | 229,422 |
| 32 | 38702 | 18000 | +250124 | 250,124 |

**Peak Storage Requirement:** 250,124 kg (Week 32)
**Current Raw Material Storage Capacity:** 50,000 kg
**Gap:** 200,124 kg
**Recommendation:** Expand raw material storage or implement just-in-time processing with additional drying capacity

**Finished Product Storage:**

| Week | Dried Output (kg) | Sales Shipment (kg) | Accumulation (kg) | Storage Required |
|------|-------------------|---------------------|-------------------|-------------------|
| 24 | 10460 | 8000 | +2460 | 2,460 |
| 25 | 11506 | 8000 | +5966 | 5,966 |
| 26 | 12291 | 8000 | +10257 | 10,257 |
| 27 | 13075 | 8000 | +15332 | 15,332 |
| 28 | 12552 | 8000 | +19884 | 19,884 |
| 29 | 12029 | 8000 | +23913 | 23,913 |
| 30 | 10983 | 8000 | +26896 | 26,896 |
| 31 | 10460 | 8000 | +29356 | 29,356 |
| 32 | 9676 | 8000 | +31032 | 31,032 |

**Peak Finished Product Storage:** 31,032 kg (Week 32)
**Current Finished Product Storage Capacity:** 10,000 kg
**Gap:** 21,032 kg
**Recommendation:** Expand finished product storage or increase sales shipments

---

## 06. FORECAST VISUALIZATION

### 6.1 Forecast Dashboard Concept

**Dashboard Components:**

1. **Harvest Forecast Chart**
   - Line chart showing historical data (2021-2023) and forecast (2026)
   - X-axis: Weeks 24-32
   - Y-axis: Harvest quantity (kg)
   - Legend: Historical years, Forecast

2. **Quality Grade Distribution**
   - Stacked bar chart showing Grade A, B, C distribution by week
   - X-axis: Weeks 24-32
   - Y-axis: Quantity (kg)
   - Legend: Grade A (green), Grade B (yellow), Grade C (red)

3. **Capacity Utilization**
   - Bar chart showing harvest forecast vs drying capacity
   - X-axis: Weeks 24-32
   - Y-axis: Quantity (kg)
   - Legend: Harvest forecast (blue), Drying capacity (orange)
   - Warning indicator when utilization > 100%

4. **Resource Requirements**
   - Summary cards showing:
     - Total energy required (kWh)
     - Total water required (L)
     - Equipment gaps
     - Storage gaps

5. **Forecast Accuracy (Historical)**
   - Table showing actual vs forecast for previous years (if available)
   - Metrics: MAPE (Mean Absolute Percentage Error), RMSE

### 6.2 Forecast Table View

| Week | Forecast (kg) | Grade A (kg) | Grade B (kg) | Grade C (kg) | Drying Capacity (kg) | Utilization % | Energy (kWh) | Water (L) |
|------|---------------|--------------|--------------|--------------|---------------------|---------------|--------------|-----------|
| 24 | 41,840 | 26,359 | 11,297 | 4,184 | 18,000 | 232% | 7,531 | 20,920 |
| 25 | 46,024 | 28,975 | 12,426 | 4,602 | 18,000 | 256% | 8,284 | 23,012 |
| 26 | 49,162 | 30,972 | 13,274 | 4,916 | 18,000 | 273% | 8,849 | 24,581 |
| 27 | 52,300 | 32,949 | 14,121 | 5,230 | 18,000 | 291% | 9,414 | 26,150 |
| 28 | 50,208 | 31,631 | 13,556 | 5,021 | 18,000 | 279% | 9,037 | 25,104 |
| 29 | 48,116 | 30,313 | 12,991 | 4,812 | 18,000 | 267% | 8,661 | 24,058 |
| 30 | 43,932 | 27,677 | 11,862 | 4,393 | 18,000 | 244% | 7,908 | 21,966 |
| 31 | 41,840 | 26,359 | 11,297 | 4,184 | 18,000 | 232% | 7,531 | 20,920 |
| 32 | 38,702 | 24,382 | 10,450 | 3,870 | 18,000 | 215% | 6,966 | 19,351 |
| **Total** | **412,124** | **259,707** | **111,274** | **41,142** | **162,000** | **254%** | **74,082** | **206,062** |

---

## 07. ASSUMPTIONS & LIMITATIONS

### 7.1 Assumptions

1. **Historical Data Availability:** 3 years of historical harvest data are available from Plants workstream
2. **Data Quality:** Historical data is accurate and complete
3. **Seasonal Pattern:** Mango harvest follows a predictable seasonal pattern (June-August)
4. **Growth Trend:** Historical growth trend (4.6% per year) will continue
5. **Quality Distribution:** Quality grade distribution remains stable (63% A, 27% B, 10% C)
6. **Weather Conditions:** Normal weather conditions (no extreme events)
7. **No Disease Outbreaks:** No major disease outbreaks affecting yield
8. **Farm Capacity:** Farm capacity remains constant (no expansion)

### 7.2 Limitations

1. **External Factors:** Does not account for weather anomalies, disease outbreaks, or market changes
2. **Short-Term Variability:** Does not capture week-to-week variability due to weather
3. **Single Variety:** Forecast is for Keitt variety only (other varieties not included)
4. **No Leading Indicators:** Does not use leading indicators (e.g., flowering data, weather forecasts)
5. **Static Parameters:** Assumes static drying capacity, storage capacity, and resource availability
6. **No Scenario Analysis:** Does not provide best-case/worst-case scenarios
7. **No Confidence Intervals:** Does not provide statistical confidence intervals

### 7.3 Known Sources of Error

1. **Weather Variability:** Rainfall and temperature variations can affect yield by ±10-15%
2. **Pest/Disease:** Pest outbreaks can reduce yield by up to 30%
3. **Labor Availability:** Labor shortages can delay harvest and affect quality
4. **Market Conditions:** Market price changes can affect harvest timing and quantity
5. **Data Entry Errors:** Manual data entry errors in historical data

**Expected Forecast Accuracy:** ±15% (based on agricultural forecasting benchmarks)

---

## 08. FUTURE ENHANCEMENTS

### 8.1 Short-Term Enhancements (Week 5-10)

1. **Multiple Varieties:** Extend forecast to include Kent, Tommy, Amélie varieties
2. **Scenario Analysis:** Add best-case, worst-case, and baseline scenarios
3. **Confidence Intervals:** Add statistical confidence intervals using standard deviation
4. **Leading Indicators:** Incorporate flowering data and weather forecasts
5. **Interactive Dashboard:** Build interactive forecast dashboard with drill-down capability

### 8.2 Medium-Term Enhancements (Week 11-20)

1. **Advanced Forecasting Methods:** Implement exponential smoothing or ARIMA
2. **Machine Learning:** Implement simple ML model (e.g., random forest) if sufficient data
3. **Real-Time Updates:** Update forecast based on actual harvest data as season progresses
4. **Resource Optimization:** Optimize resource allocation based on forecast
5. **Risk Analysis:** Add risk assessment for capacity gaps

### 8.3 Long-Term Enhancements (Beyond Program)

1. **Predictive Analytics:** Use satellite imagery and weather data for yield prediction
2. **Supply Chain Integration:** Integrate forecast with logistics and market demand
3. **Climate Modeling:** Incorporate climate change scenarios
4. **Economic Modeling:** Incorporate price elasticity and market dynamics
5. **AI/ML Models:** Deploy advanced ML models with sufficient historical data

---

## 09. IMPLEMENTATION PLAN

### Phase 1: Data Collection (Week 04) ✅

**Status:** This document defines data structure and sample data

**Deliverable:** Historical data schema and sample data

**Next:** Begin Phase 2

---

### Phase 2: Algorithm Implementation (Week 05)

**Status:** Planned

**Deliverables:**
- Python/JavaScript implementation of moving average algorithm
- Unit tests for forecast calculation
- Sample forecast output

**Dependencies:** Technical Specification phase

---

### Phase 3: Dashboard Development (Week 07)

**Status:** Planned

**Deliverables:**
- Forecast dashboard in MVP
- Visualization of forecast vs capacity
- Resource requirement summary

**Dependencies:** MVP Development phase

---

### Phase 4: Integration with Plants Data (Week 11)

**Status:** Planned

**Deliverables:**
- Integration with Plants historical data API
- Automated forecast calculation
- Real-time forecast updates

**Dependencies:** Full System Integration phase

---

## 10. WEEK 04 SUCCESS CRITERIA

This forecasting specification satisfies the following Week 04 success criteria:

- [x] Historical harvest data structure is defined
- [x] Baseline forecasting method is selected (3-year moving average)
- [x] Forecast is transparent and explainable
- [x] Forecast is simple and reproducible
- [x] Forecast → transformation planning link is established
- [x] Assumptions and limitations are documented
- [x] Sample forecast is calculated for 2026
- [x] Capacity and resource planning is demonstrated
- [x] Based on validated Week 3 requirements (FR-09: Historical data analysis)

---

## 11. NEXT STEPS

Based on this forecasting specification, the following actions can be taken:

1. **Data Model V3:** Add `HISTORICAL_HARVEST` entity to Product Transformation data model

2. **Algorithm Implementation (Week 05):** Implement the moving average algorithm in code

3. **Dashboard Development (Week 07):** Add forecast visualization to MVP dashboard

4. **Plants Integration (Week 11):** Integrate with Plants historical data API for automated forecasting

---

**Status:** 🔵 FORECASTING SPECIFICATION COMPLETE  
**Next Action:** Begin STEP 6 - Audit Homepage Terminology and Align UI with Platform Design  
**Last Updated:** 2026-08-10
