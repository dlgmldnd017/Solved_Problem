SELECT
    year(A.DIFFERENTIATION_DATE) YEAR,
    (B.SIZE - A.SIZE_OF_COLONY) `YEAR_DEV`,
    A.ID

FROM
    ECOLI_DATA A
    LEFT JOIN
    (
        SELECT 
            year(DIFFERENTIATION_DATE) YEAR,
            MAX(SIZE_OF_COLONY) SIZE
            
        FROM
            ECOLI_DATA
        
        GROUP BY
            year(DIFFERENTIATION_DATE)
    ) B
    ON
        year(A.DIFFERENTIATION_DATE) = B.YEAR

ORDER BY
    YEAR, YEAR_DEV