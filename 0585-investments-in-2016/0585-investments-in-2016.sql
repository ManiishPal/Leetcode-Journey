# Write your MySQL query statement below

SELECT
    ROUND(SUM(tiv_2016), 2) AS tiv_2016
FROM Insurance i
WHERE
    (
        SELECT COUNT(*)
        FROM Insurance
        WHERE tiv_2015 = i.tiv_2015
    ) > 1

    AND

    (
        SELECT COUNT(*)
        FROM Insurance
        WHERE lat = i.lat
          AND lon = i.lon
    ) = 1;
