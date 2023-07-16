select d.name as Department, e.name as Employee, e.Salary
from Employee e,
     Department d
where e.departmentId = d.id
  and (e.departmentId, Salary) in
      (select departmentId, max(e.salary)
       from Employee e
       group by e.departmentId
      );

select d.name as Department, e.name as Employee, e.Salary
from Employee e
         join
     Department d on e.departmentId = d.id
where (e.departmentId, Salary) in
      (select departmentId, max(e.salary)
       from Employee e
       group by e.departmentId
      );