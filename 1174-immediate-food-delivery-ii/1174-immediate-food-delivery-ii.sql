# Write your MySQL query statement below
SELECT
    ROUND(
        100.0 * SUM(
            CASE
                WHEN d.order_date = d.customer_pref_delivery_date
                THEN 1
                ELSE 0
            END
        ) / COUNT(*),
        2
    ) AS immediate_percentage
FROM Delivery d
WHERE (d.customer_id, d.order_date) IN (
    SELECT customer_id, MIN(order_date)
    FROM Delivery
    GROUP BY customer_id
);