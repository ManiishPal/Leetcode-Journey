# Write your MySQL query statement below
SELECT
    p.product_name,
    (
        SELECT SUM(o.unit)
        FROM Orders o
        WHERE o.product_id = p.product_id
          AND o.order_date >= '2020-02-01'
          AND o.order_date < '2020-03-01'
    ) AS unit
FROM Products p
WHERE (
    SELECT SUM(o.unit)
    FROM Orders o
    WHERE o.product_id = p.product_id
      AND o.order_date >= '2020-02-01'
      AND o.order_date < '2020-03-01'
) >= 100;