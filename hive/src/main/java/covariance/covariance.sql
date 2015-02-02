-- http://www.edureka.co/blog/hive-commands/

select a.STOCK_SYMBOL, b.STOCK_SYMBOL, month(a.STOCK_DATE),
(AVG(a.STOCK_PRICE_HIGH*b.STOCK_PRICE_HIGH) -
(AVG(a.STOCK_PRICE_HIGH)*AVG(b.STOCK_PRICE_HIGH)))
from nyse a join nyse b on
a.STOCK_DATE=b.STOCK_DATE where a.STOCK_SYMBOL<b.STOCK_SYMBOL and
year(a.STOCK_DATE)=2008
group by a.STOCK_SYMBOL, b. STOCK_SYMBOL,
month(a.STOCK_DATE); 



--Save this script as .sql in some folder and call it in unix shell as below command
--Note: It's unix shell not hive CLI. see edureka doc. 

--hive -f /home/edureka/Lunoworkspace/hive/src/main/java/covariance/covariance.sql

############### 
DAY
MONTH
YEAR  
Functions in hive which gives direct value from the date(2010-02-08). 