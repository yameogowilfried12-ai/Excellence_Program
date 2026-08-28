-- ============================================================
-- Sales & Marketing — Full Database Schema (PostgreSQL)
-- Consolidated final version — all 22 tables in one file
-- BIT x Infineon Excellence Program 2026 — Mariata Nikiema
-- ============================================================
-- This is the single, up-to-date schema for the Sales & Marketing
-- module, covering all 9 mockup pages (Dashboard, Forecasting,
-- Demand Alerts, Demand Reports, Customers, Sales Channels,
-- Pricing, Campaigns, Delivery Tracking).
--
-- Run this on a FRESH database. If you already ran Parts 1-4
-- separately on your Neon project, you do NOT need to run this
-- too — it recreates the same 22 tables from scratch and would
-- conflict with what you already have. Use this file only when
-- setting up a new/clean database (e.g. a teammate's environment,
-- or moving to the shared project database).
-- ============================================================

-- ============================================================
-- 1. CUSTOMERS
-- ============================================================
CREATE TABLE customers (
    customer_id     SERIAL PRIMARY KEY,
    customer_code   VARCHAR(20) UNIQUE,               -- e.g. CUST-8892-GER
    company_name    VARCHAR(150) NOT NULL,
    customer_type   VARCHAR(30)  NOT NULL
                     CHECK (customer_type IN ('distributor', 'retailer', 'direct_export', 'other')),
    country         VARCHAR(80),
    region          VARCHAR(80),                       -- e.g. Bavaria, Rhineland
    city            VARCHAR(80),
    status          VARCHAR(20)  NOT NULL DEFAULT 'prospect'
                     CHECK (status IN ('active', 'inactive', 'prospect')),
    payment_terms   VARCHAR(50),
    created_at      TIMESTAMP NOT NULL DEFAULT now(),
    updated_at      TIMESTAMP NOT NULL DEFAULT now()
);

-- ============================================================
-- 2. CUSTOMER CONTACTS
-- ============================================================
CREATE TABLE customer_contacts (
    contact_id      SERIAL PRIMARY KEY,
    customer_id     INTEGER NOT NULL REFERENCES customers(customer_id) ON DELETE CASCADE,
    full_name       VARCHAR(120) NOT NULL,
    email           VARCHAR(150),
    phone           VARCHAR(40),
    role            VARCHAR(80),
    is_primary      BOOLEAN NOT NULL DEFAULT false,
    created_at      TIMESTAMP NOT NULL DEFAULT now()
);

-- ============================================================
-- 3. CERTIFICATIONS (lookup)
-- ============================================================
CREATE TABLE certifications (
    certification_id SERIAL PRIMARY KEY,
    name              VARCHAR(100) NOT NULL UNIQUE
);

-- ============================================================
-- 4. CUSTOMER CERTIFICATIONS (junction)
-- ============================================================
CREATE TABLE customer_certifications (
    customer_id       INTEGER NOT NULL REFERENCES customers(customer_id) ON DELETE CASCADE,
    certification_id  INTEGER NOT NULL REFERENCES certifications(certification_id) ON DELETE CASCADE,
    PRIMARY KEY (customer_id, certification_id)
);

-- ============================================================
-- 5. SALES CHANNELS (lookup)
-- ============================================================
CREATE TABLE sales_channels (
    channel_id      SERIAL PRIMARY KEY,
    name            VARCHAR(60) NOT NULL UNIQUE
                    -- Online Store, Distributors, Retail Partners, Export Direct
);

-- ============================================================
-- 6. PRODUCTS (lightweight sales-facing reference)
-- ============================================================
-- Note: Product Transformation module owns the real batch/production
-- data. This is only the minimal product catalog Sales & Marketing
-- needs to reference (pricing, orders, forecasts).
CREATE TABLE products (
    product_id      SERIAL PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    variety         VARCHAR(80),
    unit            VARCHAR(10) NOT NULL DEFAULT 'kg',
    created_at      TIMESTAMP NOT NULL DEFAULT now()
);

-- ============================================================
-- 7. ORDERS
-- ============================================================
CREATE TABLE orders (
    order_id          SERIAL PRIMARY KEY,
    customer_id       INTEGER NOT NULL REFERENCES customers(customer_id) ON DELETE RESTRICT,
    sales_channel_id  INTEGER REFERENCES sales_channels(channel_id),
    order_date        DATE NOT NULL,
    total_volume_kg   NUMERIC(12,2),
    total_value_eur   NUMERIC(12,2),
    status            VARCHAR(30) DEFAULT 'pending'
);

-- ============================================================
-- 8. ORDER ITEMS
-- ============================================================
CREATE TABLE order_items (
    order_item_id   SERIAL PRIMARY KEY,
    order_id        INTEGER NOT NULL REFERENCES orders(order_id) ON DELETE CASCADE,
    product_id      INTEGER NOT NULL REFERENCES products(product_id),
    quantity_kg     NUMERIC(12,2) NOT NULL,
    unit_price_eur  NUMERIC(10,2) NOT NULL
);

-- ============================================================
-- 9. PRICING HISTORY
-- ============================================================
CREATE TABLE pricing_history (
    pricing_id             SERIAL PRIMARY KEY,
    product_id             INTEGER NOT NULL REFERENCES products(product_id),
    price_date              DATE NOT NULL,
    price_eur_per_kg        NUMERIC(8,2) NOT NULL,
    harvest_season_factor   NUMERIC(5,2),          -- numeric factor, e.g. 1.10
    season_label            VARCHAR(30),           -- e.g. 'Mid-Season'
    stock_level_tons        NUMERIC(10,2),
    stock_target_tons       NUMERIC(10,2),
    demand_factor            NUMERIC(5,2),
    demand_index_label       VARCHAR(20),          -- e.g. 'High'
    is_applied               BOOLEAN DEFAULT false, -- false = proposed price, true = applied
    created_at               TIMESTAMP NOT NULL DEFAULT now()
);

-- ============================================================
-- 10. COMPETITOR PRICES
-- ============================================================
CREATE TABLE competitor_prices (
    competitor_price_id SERIAL PRIMARY KEY,
    product_id      INTEGER REFERENCES products(product_id),
    competitor_name VARCHAR(50) NOT NULL,
    price_eur       NUMERIC(8,2) NOT NULL,
    price_date      DATE NOT NULL,
    is_target       BOOLEAN NOT NULL DEFAULT false,
    created_at      TIMESTAMP NOT NULL DEFAULT now()
);

-- ============================================================
-- 11. PRICING RECOMMENDATIONS
-- ============================================================
CREATE TABLE pricing_recommendations (
    recommendation_id   SERIAL PRIMARY KEY,
    product_id          INTEGER REFERENCES products(product_id),
    message              TEXT NOT NULL,
    suggested_price_eur  NUMERIC(8,2),
    created_at           TIMESTAMP NOT NULL DEFAULT now()
);

-- ============================================================
-- 12. SALES CHANNEL TARGETS
-- ============================================================
CREATE TABLE sales_channel_targets (
    target_id           SERIAL PRIMARY KEY,
    channel_id           INTEGER NOT NULL REFERENCES sales_channels(channel_id),
    period_start          DATE NOT NULL,
    period_end             DATE NOT NULL,
    revenue_target_eur     NUMERIC(12,2) NOT NULL
);

-- ============================================================
-- 13. CAMPAIGNS
-- ============================================================
CREATE TABLE campaigns (
    campaign_id     SERIAL PRIMARY KEY,
    name            VARCHAR(120) NOT NULL,
    status          VARCHAR(20) NOT NULL DEFAULT 'scheduled'
                    CHECK (status IN ('active', 'scheduled', 'ended', 'draft')),
    budget_eur      NUMERIC(10,2),
    budget_spent_eur NUMERIC(10,2),
    estimated_reach  INTEGER,
    currency         VARCHAR(3) DEFAULT 'EUR',
    start_date      DATE,
    end_date        DATE,
    created_at      TIMESTAMP NOT NULL DEFAULT now()
);
-- Note: mockup currently mixes "$" (Campaigns page) and "€" (Pricing,
-- Sales Channels) — flagged to the team; the currency column lets each
-- record its real currency once that's clarified.

-- ============================================================
-- 14. CAMPAIGN METRICS
-- ============================================================
CREATE TABLE campaign_metrics (
    metric_id       SERIAL PRIMARY KEY,
    campaign_id     INTEGER NOT NULL REFERENCES campaigns(campaign_id) ON DELETE CASCADE,
    metric_date     DATE NOT NULL,
    clicks          INTEGER DEFAULT 0,
    signups         INTEGER DEFAULT 0,
    reach           INTEGER DEFAULT 0
);

-- ============================================================
-- 15. SHIPMENTS
-- ============================================================
CREATE TABLE shipments (
    shipment_id             SERIAL PRIMARY KEY,
    shipment_code            VARCHAR(20) UNIQUE,        -- e.g. SHP-992-BFA-GER
    order_id                 INTEGER REFERENCES orders(order_id),
    origin                    VARCHAR(100),
    destination                VARCHAR(100),
    status                     VARCHAR(30) NOT NULL DEFAULT 'packed'
                               CHECK (status IN ('packed', 'shipped', 'port', 'in_transit', 'delivered', 'delayed')),
    eta                        DATE,
    vessel_name                VARCHAR(100),
    voyage_number               VARCHAR(30),
    delay_reason                VARCHAR(200),
    current_lat                 NUMERIC(9,6),
    current_lon                  NUMERIC(9,6),
    speed_knots                  NUMERIC(5,1),
    current_location_label        VARCHAR(150),
    distance_remaining_nm          NUMERIC(8,1),
    commodity_description           VARCHAR(150),
    container_count                  INTEGER,
    container_type                    VARCHAR(20),
    cargo_weight_kg                    NUMERIC(10,1),
    current_temp_c                      NUMERIC(4,1),
    temp_status                          VARCHAR(20),
    manifest_url                          VARCHAR(255),
    created_at                            TIMESTAMP NOT NULL DEFAULT now()
);

-- ============================================================
-- 16. SHIPMENT EVENTS (timeline)
-- ============================================================
CREATE TABLE shipment_events (
    event_id        SERIAL PRIMARY KEY,
    shipment_id     INTEGER NOT NULL REFERENCES shipments(shipment_id) ON DELETE CASCADE,
    step            VARCHAR(30) NOT NULL,   -- packed / shipped / port / in_transit / delivered
    event_time      TIMESTAMP NOT NULL DEFAULT now(),
    note            VARCHAR(200)
);

-- ============================================================
-- 17. DEMAND FORECASTS
-- ============================================================
CREATE TABLE demand_forecasts (
    forecast_id           SERIAL PRIMARY KEY,
    product_id            INTEGER REFERENCES products(product_id),
    forecast_month        DATE NOT NULL,          -- store as first-of-month date
    scenario               VARCHAR(20) NOT NULL DEFAULT 'base'
                           CHECK (scenario IN ('pessimistic', 'base', 'optimistic')),
    forecasted_volume_t    NUMERIC(10,2) NOT NULL,
    lower_bound_t           NUMERIC(10,2),
    upper_bound_t            NUMERIC(10,2),
    capacity_risk             VARCHAR(10) CHECK (capacity_risk IN ('low', 'medium', 'high')),
    is_actual                 BOOLEAN NOT NULL DEFAULT false,  -- true = past/proxy actual
    model_version               VARCHAR(30),
    created_at                  TIMESTAMP NOT NULL DEFAULT now()
);

-- ============================================================
-- 18. FORECAST MODEL RUNS
-- ============================================================
CREATE TABLE forecast_model_runs (
    run_id                   SERIAL PRIMARY KEY,
    crop_variety              VARCHAR(60),
    region                     VARCHAR(100),
    confidence_interval_pct    NUMERIC(5,2),
    use_weather_data           BOOLEAN DEFAULT false,
    use_commodity_prices       BOOLEAN DEFAULT false,
    use_geopolitical_index     BOOLEAN DEFAULT false,
    model_status                VARCHAR(30) DEFAULT 'cold_start',
    created_at                  TIMESTAMP NOT NULL DEFAULT now()
);

-- ============================================================
-- 19. ALERTS
-- ============================================================
-- alert_type is free text (VARCHAR) on purpose: real alert kinds
-- observed across pages are too varied for a fixed list (High Demand,
-- Low Demand, Deviation, Supply Chain Bottleneck, Quality Pending...).
CREATE TABLE alerts (
    alert_id        SERIAL PRIMARY KEY,
    alert_code      VARCHAR(20) UNIQUE,        -- e.g. ALT-9001
    alert_type      VARCHAR(50) NOT NULL,
    severity        VARCHAR(20) NOT NULL DEFAULT 'info'
                    CHECK (severity IN ('critical', 'warning', 'info')),
    message         VARCHAR(255) NOT NULL,
    region          VARCHAR(80),
    origin          VARCHAR(80),
    related_table   VARCHAR(50),      -- e.g. 'shipments', 'orders', 'demand_forecasts'
    related_id      INTEGER,          -- polymorphic link, no FK constraint
    status          VARCHAR(20) NOT NULL DEFAULT 'open'
                    CHECK (status IN ('open', 'acknowledged', 'resolved', 'dismissed')),
    created_at      TIMESTAMP NOT NULL DEFAULT now(),
    resolved_at     TIMESTAMP
);

-- ============================================================
-- 20. DEMAND REPORTS
-- ============================================================
-- report_type is free text: real categories (Forecasting, Regional,
-- Risk Analysis, Sales vs Actual...) are more varied than a fixed list.
CREATE TABLE demand_reports (
    report_id                 SERIAL PRIMARY KEY,
    report_type                VARCHAR(40) NOT NULL DEFAULT 'custom',
    period_start                 DATE NOT NULL,
    period_end                    DATE NOT NULL,
    total_forecasted_volume_t      NUMERIC(10,2),
    total_actual_volume_t           NUMERIC(10,2),   -- NULL until real sales data exists
    variance_pct                     NUMERIC(5,2),
    status                             VARCHAR(20) DEFAULT 'ready'
                                       CHECK (status IN ('ready', 'processing', 'scheduled', 'failed')),
    is_scheduled                        BOOLEAN DEFAULT false,
    next_run_at                          TIMESTAMP,
    schedule_frequency                    VARCHAR(20),
    summary                                 TEXT,
    generated_by                             VARCHAR(100),
    generated_at                              TIMESTAMP NOT NULL DEFAULT now()
);

-- ============================================================
-- 21. REPORT FILES
-- ============================================================
CREATE TABLE report_files (
    file_id     SERIAL PRIMARY KEY,
    report_id   INTEGER NOT NULL REFERENCES demand_reports(report_id) ON DELETE CASCADE,
    format      VARCHAR(10) NOT NULL,   -- 'PDF' or 'CSV'
    file_url    VARCHAR(255)
);

-- ============================================================
-- 22. PRODUCT BATCHES (local read-facing cache of Product Transformation data)
-- ============================================================
CREATE TABLE product_batches (
    batch_id     SERIAL PRIMARY KEY,
    batch_code    VARCHAR(20) UNIQUE NOT NULL,
    product_id     INTEGER REFERENCES products(product_id),
    stock_t         NUMERIC(10,2),
    status           VARCHAR(20),   -- e.g. Processed, In Transit, Scheduled, Delayed
    synced_at        TIMESTAMP NOT NULL DEFAULT now()
);

-- ============================================================
-- INDEXES
-- ============================================================
CREATE INDEX idx_customers_status        ON customers(status);
CREATE INDEX idx_customers_country       ON customers(country);
CREATE INDEX idx_customer_contacts_cust  ON customer_contacts(customer_id);
CREATE INDEX idx_orders_customer         ON orders(customer_id);
CREATE INDEX idx_orders_channel          ON orders(sales_channel_id);
CREATE INDEX idx_order_items_order       ON order_items(order_id);
CREATE INDEX idx_order_items_product     ON order_items(product_id);
CREATE INDEX idx_pricing_product_date    ON pricing_history(product_id, price_date);
CREATE INDEX idx_competitor_prices_product ON competitor_prices(product_id, price_date);
CREATE INDEX idx_channel_targets_channel ON sales_channel_targets(channel_id, period_start);
CREATE INDEX idx_campaign_metrics_camp   ON campaign_metrics(campaign_id);
CREATE INDEX idx_shipments_order         ON shipments(order_id);
CREATE INDEX idx_shipment_events_ship    ON shipment_events(shipment_id);
CREATE INDEX idx_forecasts_month         ON demand_forecasts(forecast_month, scenario);
CREATE INDEX idx_alerts_status           ON alerts(status);
CREATE INDEX idx_alerts_severity         ON alerts(severity);
CREATE INDEX idx_reports_period          ON demand_reports(period_start, period_end);
CREATE INDEX idx_report_files_report     ON report_files(report_id);
CREATE INDEX idx_product_batches_status  ON product_batches(status);

-- ============================================================
-- SEED DATA
-- ============================================================
INSERT INTO certifications (name) VALUES
    ('EU Organic'), ('HACCP'), ('Fair Trade'), ('Kosher'), ('USDA Organic'), ('BioSuisse');

INSERT INTO sales_channels (name) VALUES
    ('Online Store'), ('Distributors'), ('Retail Partners'), ('Export Direct');

INSERT INTO products (name, variety, unit) VALUES
    ('Dried Mango Slices', 'Kent', 'kg'),
    ('Dried Mango Slices', 'Amelie', 'kg');

INSERT INTO customers (customer_code, company_name, customer_type, country, region, city, status, payment_terms)
VALUES ('CUST-8892-GER', 'BioFrucht GmbH', 'distributor', 'Germany', 'Bavaria', 'Munich', 'active', 'Net 30');

INSERT INTO alerts (alert_code, alert_type, severity, message, region, origin, related_table, status) VALUES
    ('ALT-9001', 'production_delay', 'critical', 'Production delay — Batch #204: equipment maintenance required', 'Germany', 'Burkina Faso', 'shipments', 'open'),
    ('ALT-9002', 'quality_pending',  'warning',  'Quality check pending — Batch #207: awaiting lab results',       'Germany', 'Burkina Faso', 'shipments', 'open'),
    ('ALT-9003', 'logistics',        'warning',  'Logistics bottleneck — port congestion at origin',                 'Germany', 'Burkina Faso', 'shipments', 'open');
