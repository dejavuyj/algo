CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  SET N := N-1;
RETURN (
      # Write your MySQL query statement below.
      select distinct salary from employee order by salary desc limit N, 1
    );
END

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  SET N := N-1;
RETURN (
      # Write your MySQL query statement below.
      select salary from employee group by salary order by salary desc limit N, 1
    );
END