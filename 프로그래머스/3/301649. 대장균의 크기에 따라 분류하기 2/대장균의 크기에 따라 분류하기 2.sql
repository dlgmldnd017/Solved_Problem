SELECT 
    B.ID, 
    (
        CASE
        WHEN B.COLONY_LEVEL = 1 THEN 'CRITICAL'
        WHEN B.COLONY_LEVEL = 2 THEN 'HIGH'
        WHEN B.COLONY_LEVEL = 3 THEN 'MEDIUM'
        ELSE 'LOW'
    END
    ) 'COLONY_NAME'

FROM
    (
        SELECT 
            ID, 
            NTILE(4) OVER(ORDER BY SIZE_OF_COLONY DESC) "COLONY_LEVEL"

        FROM 
            ECOLI_DATA
    ) B

ORDER BY 
    B.ID