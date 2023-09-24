select a.id
from weather as a
         cross join weather as b on datediff(a.recordDate, b.recordDate) = 1
where a.Temperature > b.Temperature;