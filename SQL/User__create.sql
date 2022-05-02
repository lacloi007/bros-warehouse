CREATE TABLE COMMON__USER {
  ID                VARCHAR(30) PRIMARY KEY
  , CREATED_BY      VARCHAR(30)
  , CREATED_DATE    DATETIME
  , UPDATED_BY      VARCHAR(30)
  , UPDATED_DATE    DATETIME

  , EMAIL VARCHAR(1000)
  , PASSWORD VARCHAR(255)
  , FIRST_NAME VARCHAR(255)
  , MIDDLE_NAME VARCHAR(255)
  , LAST_NAME VARCHAR(255)
  , PHONE_NUMBER VARCHAR(255)
  , ZALO_NUMBER VARCHAR(255)
  , ROLES VARCHAR(2000)
}