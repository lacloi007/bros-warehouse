CREATE TABLE BROS_RECEIVING_ORDER (
  ID                VARCHAR(30) PRIMARY KEY
  , CREATED_BY      VARCHAR(30)
  , CREATED_DATE    DATETIME
  , UPDATED_BY      VARCHAR(30)
  , UPDATED_DATE    DATETIME

  , BOX_ID VARCHAR(255)
  , NUMBER_OF_BOX SMALLINT
  , PRODUCT_DESCRIPTION VARCHAR(2000)
  , SKU VARCHAR(255)
  , QUANTITY NUMERIC(20, 2)
  , WEIGHT_PER_ITEM NUMERIC(20, 2)
  , WEIGHT_UNIT VARCHAR(255)
  , INCH_LENGTH NUMERIC(20, 2)
  , INCH_WIDTH NUMERIC(20, 2)
  , INCH_HEIGHT NUMERIC(20, 2)
  , SHIPING_DATE DATETIME(0)
  , TRACKING_NUMBER VARCHAR(255)
  , WAREHOUSE_ID VARCHAR(30)
  , NEW_SKU VARCHAR(255)
  , IS_FRAGILE_GOOD CHAR(1)
  , BUYER_INFORMATION VARCHAR(2000)
  , BUYER_ADDRESS VARCHAR(2000)
  , DATA_TYPE VARCHAR(255)
  , STATUS VARCHAR(255)
);