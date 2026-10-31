# Write your MySQL query statement below
SELECT c.customer_id
FROM Customer c
CROSS JOIN Product p
GROUP BY c.customer_id
HAVING COUNT(DISTINCT c.product_key) = COUNT(DISTINCT p.product_key);