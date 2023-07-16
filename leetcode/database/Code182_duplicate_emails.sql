select email
from (select email, count(id) as cnt from person group by email) c
where c.cnt > 1;

select email
from person
group by email
having count(email) > 1;