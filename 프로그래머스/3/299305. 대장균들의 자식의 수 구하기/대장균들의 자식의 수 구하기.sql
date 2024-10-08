SELECT
    A.ID, 
    CASE
        WHEN COUNT(B.ID) = 0 THEN 0
        ELSE COUNT(B.ID)
    END AS CHILD_COUNT

FROM    
    ECOLI_DATA A
    LEFT JOIN
    ECOLI_DATA B
    ON
    A.ID = B.PARENT_ID

GROUP BY
    A.ID
    
ORDER BY
    A.ID ASC;
