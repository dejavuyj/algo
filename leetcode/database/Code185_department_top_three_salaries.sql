select d.name as 'Department', e1.name as 'Employee', e1.salary
from employee e1
         join
     department d on e1.departmentId = d.id
where 3 > (select count(distinct e2.salary)
           from employee e2
           where e2.salary > e1.salary
             and e1.departmentId = e2.departmentId
)
;