# Write your MySQL query statement below
SELECT 
    p.product_id,
    COALESCE(p.new_price, 10) AS price
FROM Products p
WHERE (p.product_id, p.change_date) IN (
    SELECT 
        product_id,
        MAX(change_date)
    FROM Products
    WHERE change_date <= '2019-08-16'
    GROUP BY product_id
)

UNION

SELECT 
    product_id,
    10 AS price
FROM Products
GROUP BY product_id
HAVING MIN(change_date) > '2019-08-16';